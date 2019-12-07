package com.aaa.repast.admin.ftp.util;

import java.util.Random;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/10/21 9:52
 * @Description
 **/
public class FileNameUtil {

    public static String getFileName(Long id) {
        // 1.获取当前系统时间的毫秒数
        long currentTimeMillis = System.currentTimeMillis();
        // 2.创建随机数对象
        Random random = new Random();
        // 3.生成0-999随机数
        int randomNum = random.nextInt(999);
        // 随机出的数字是否为三位数？0,999,21...
        // 4.%:占位符 d:数字 3:三位 0:如果不够三位，自动向前补0
        String format = String.format("%03d", randomNum);
        // 5.生成最终的文件名
        String fileName = id + currentTimeMillis + format;
        // 6.返回文件名
        return fileName;
    }

    public static void main(String[] args) {
        String fileName = FileNameUtil.getFileName(1L);
        System.out.println(fileName);
    }

}
