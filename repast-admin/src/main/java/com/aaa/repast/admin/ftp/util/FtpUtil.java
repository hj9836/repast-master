package com.aaa.repast.admin.ftp.util;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/7/30 12:30
 * @Description
 *      Ftp上传工具类
 **/
public class FtpUtil {
    /*参数需要：
        ip地址，端口号。
        用户名，密码。
        文件名（随机名+原名后缀）
        文件路径（文件根目录+以日期进行命名的文件夹层级格式）
        还有一个引用类型的I流 InputStream input（通过文件file.getInputStream得到）



    */
    public static Boolean uploadFile(String host, int port, String username, String password, String basePath,
                                     String filePath, String filename, InputStream input) throws IOException {
        // 1.创建临时目录
        String tempPath = "";
        // 2.创建FTPClient对象:作用就是通过FTPClient对象实现对ftp服务器的连接以及上传和下载
        FTPClient ftp = new FTPClient();
        try {
            // 3.定义返回码
            int reply;
            // 4.连接ftp，需要传入ftp服务器的IP地址以及端口号
            ftp.connect(host, port);
            // 5.登录ftp服务器
            ftp.login(username, password);
            // 6.如果连接登录成功后则返回230，如果连接和登录失败则返回503
            reply = ftp.getReplyCode();
            System.out.println("ftp.getReplyCode得到的状态码"+reply);
            // 7.判断状态码
                // 通过isPositiveCompletion()方法判断状态码：返回值为Boolean类型，如果返回为true则说明连接和登录成功，如果返回false则失败
            if (!FTPReply.isPositiveCompletion(reply)) {
               /*
               !!为什么不用230.equals？!!
               if里面这个方法的源码很有意思，列出了全部的状态code
               而且用200~300这种区间形式来判断是否登陆成功
               return reply >= 200 && reply < 300;
               可以参考作为二期项目开发的code设计

               */
                // 8.代码不是200~300之间，说明登陆失败了
                // 为了确保已经退出连接，再次手动执行失去连接
                ftp.disconnect();//退出连接
                return false;//返回给service层false结果，表明登录失败
            }
            //      如果是我会加个else if，但李老师更喜欢直接用下一个if
            // 表示连接ftp服务器并且已经处于登录成功状态
            // 9.创建所需要上传文件夹
                // 调用changeWorkingDirectory():判断所要上传的路径是否存在
                // 2019/10/21
                // 返回值类型为Boolean类型，true：该目录存在，false:该目录不存在，需要创建
            /*！！

                        /home/ftp/www/2019/10/23
                        思路：先判断完成版本的文件夹是否存在
                        如果不存在分层判断+创建
                        先看结果，如果结果出现问题再穷举找负责人
                        其实还是正着判断的，感觉尚可优化



            ！！*/
            if (!ftp.changeWorkingDirectory(basePath +"/"+ filePath)) {
                //如果不存在就创建
                // 10.进行分割:
                    // 2019/10/21-->因为要一级一级的创建，必须要分割目录["2019","10","21"]
                String[] dirs = filePath.split("/");//dirs={2019,10,21}
                // basePath:/home/ftp/www
                tempPath = basePath;//临时目录先等于上根路径
                // 做循环创建目录，相当于在Linux服务器上使用mkdir命令
                for (String dir : dirs) {
                    // 第一次循环dir是2019
                    // 第二次循环dir是10
                    // 第三次循环dir是21
                    // tempPath:/home/ftp/www
                    // tempPath:/home/ftp/www/2019
                    tempPath += "/" + dir;
                    // 11.再次判断该路径是否存在/home/ftp/www/2019
                    if (!ftp.changeWorkingDirectory(tempPath)) {
                        // 12.调用makeDirectory()方法创建目录:相当于在Linux中执行mkdir命令
                            // 返回值仍然为Boolean类型，true:创建成功，false:创建失败
                        if (!ftp.makeDirectory(tempPath)) {
                            return false;
                        }
                    }
                }
            }
                                                         /*至此，创建文件夹完毕*/


            // 13.开启以二进制的形式进行上传文件(速度会非常快)
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            // ！！！！真正的上传：14.调用storeFile()方法实现文件的上传
                // 返回值类型是Boolean类型，true:上传成功，false:上传失败
            if (!ftp.storeFile(filename, input)) {
                //此文件专属的input（通过file.getInputStream得到）
                return false;
            }
            // 14.关闭输入流
            input.close();
            // 15.退出ftp(相当于数据库中释放资源)
            ftp.logout();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException(e);
        } finally {
            if (ftp.isConnected()) {
                try {
                    // 16.断开连接
                    ftp.disconnect();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new DateTime().toString("yyyy/MM/dd"));
    }
}
