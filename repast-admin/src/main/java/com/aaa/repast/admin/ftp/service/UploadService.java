package com.aaa.repast.admin.ftp.service;


import com.aaa.repast.admin.ftp.config.FtpProperties;
import com.aaa.repast.admin.ftp.util.FileNameUtil;
import com.aaa.repast.admin.ftp.util.FtpUtil;
import com.aaa.repast.admin.project.system.productCategory.mapper.ProductCategoryMapper;
import com.aaa.repast.admin.project.system.shopInformation.domain.ShopInformation;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/10/21 9:49
 * @Description
 **/
@Service
public class UploadService {

    @Autowired
    private FtpProperties ftpProperties;
    @Autowired
    private ProductCategoryMapper picMapper;//无视报错信息，说我没有放把他放入bean容器中，application已经mapperscan扫描接口了

    public Map<String, Object> uploadShopPicAjax(ShopInformation shopInformation,HttpServletRequest request, Long shopId) {
        //首先判断循环次数，看看关于图片的属性字段是否为空
        List<Map<String,Object>> rows = new ArrayList<>();
        MultipartFile imagesImg = shopInformation.getImagesImg();
        MultipartFile businessLicenseImg = shopInformation.getBusinessLicenseImg();
        MultipartFile foodLicenseImg = shopInformation.getFoodLicenseImg();
        MultipartFile sanitationLicenseImg = shopInformation.getSanitationLicenseImg();
        MultipartFile assessImg = shopInformation.getAssessImg();

        System.out.println("关键的一步!!查看源文件名称的字符长度!!："+imagesImg.getOriginalFilename().length());


        /*if (imagesImg.getOriginalFilename()!=null&&imagesImg.getOriginalFilename()!=""){
            System.out.println("进入"+"imagesImg"+"不为空中");
            Map<String, Object> imagesImgmap = new HashMap<>();
            imagesImgmap.put("row","images");
            imagesImgmap.put("data",imagesImg);
            rows.add(imagesImgmap);
        }if (businessLicenseImg.getOriginalFilename()!=null&&businessLicenseImg.getOriginalFilename()!=""){
            System.out.println("进入"+"businessLicenseImg"+"不为空中");
            Map<String, Object> businessLicenseImgmap = new HashMap<>();
            businessLicenseImgmap.put("row","business_license");
            businessLicenseImgmap.put("data",businessLicenseImg);
            rows.add(businessLicenseImgmap);
        }if (foodLicenseImg.getOriginalFilename()!=null&&foodLicenseImg.getOriginalFilename()!=""){
            System.out.println("进入"+"foodLicenseImg"+"不为空中");
            Map<String, Object> foodLicenseImgmap = new HashMap<>();
            foodLicenseImgmap.put("row","food_license");
            foodLicenseImgmap.put("data",foodLicenseImg);
            rows.add(foodLicenseImgmap);
        }if (sanitationLicenseImg.getOriginalFilename()!=null&&sanitationLicenseImg.getOriginalFilename()!=""){
            System.out.println("进入"+"sanitationLicenseImg"+"不为空中");
            Map<String, Object> sanitationLicenseImgmap = new HashMap<>();
            sanitationLicenseImgmap.put("row","sanitation_license");
            sanitationLicenseImgmap.put("data",sanitationLicenseImg);
            rows.add(sanitationLicenseImgmap);
        }if (assessImg.getOriginalFilename()!=null&&assessImg.getOriginalFilename()!=""){
            System.out.println("进入"+"assessImg"+"不为空中");
            Map<String, Object> assessImgmap = new HashMap<>();
            assessImgmap.put("row","assess");
            assessImgmap.put("data",assessImg);
            rows.add(assessImgmap);
        }*/

        if (imagesImg.getOriginalFilename()!=null&&imagesImg.getOriginalFilename().length()!=0){
            System.out.println("进入"+"imagesImg"+"不为空中");
            Map<String, Object> imagesImgmap = new HashMap<>();
            imagesImgmap.put("row","images");
            imagesImgmap.put("data",imagesImg);
            rows.add(imagesImgmap);
        }if (businessLicenseImg.getOriginalFilename()!=null&&businessLicenseImg.getOriginalFilename().length()!=0){
            System.out.println("进入"+"businessLicenseImg"+"不为空中");
            Map<String, Object> businessLicenseImgmap = new HashMap<>();
            businessLicenseImgmap.put("row","business_license");
            businessLicenseImgmap.put("data",businessLicenseImg);
            rows.add(businessLicenseImgmap);
        }if (foodLicenseImg.getOriginalFilename()!=null&&foodLicenseImg.getOriginalFilename().length()!=0){
            System.out.println("进入"+"foodLicenseImg"+"不为空中");
            Map<String, Object> foodLicenseImgmap = new HashMap<>();
            foodLicenseImgmap.put("row","food_license");
            foodLicenseImgmap.put("data",foodLicenseImg);
            rows.add(foodLicenseImgmap);
        }if (sanitationLicenseImg.getOriginalFilename()!=null&&sanitationLicenseImg.getOriginalFilename().length()!=0){
            System.out.println("进入"+"sanitationLicenseImg"+"不为空中");
            Map<String, Object> sanitationLicenseImgmap = new HashMap<>();
            sanitationLicenseImgmap.put("row","sanitation_license");
            sanitationLicenseImgmap.put("data",sanitationLicenseImg);
            rows.add(sanitationLicenseImgmap);
        }if (assessImg.getOriginalFilename()!=null&&assessImg.getOriginalFilename().length()!=0){
            System.out.println("进入"+"assessImg"+"不为空中");
            Map<String, Object> assessImgmap = new HashMap<>();
            assessImgmap.put("row","assess");
            assessImgmap.put("data",assessImg);
            rows.add(assessImgmap);
        }

        Map<String, Object> resultMap = new HashMap<String, Object>();
        //确定文件数量=确定循环次数
        int ii=0;
        System.out.println("查看list长度"+rows.size());
        for (int i=0;i<rows.size();i++) {

            ii++;

            //获得该次循环需要插入的字段名称
            String rowWord = (String)rows.get(i).get("row");
            //获得该次循环存入ftp服务器文件的原文件名
            MultipartFile file = (MultipartFile)rows.get(i).get("data");
            String oldFileName=file.getOriginalFilename();
            System.out.println(oldFileName);


            try {
                // 2.生成新的文件名
                // id + 随机数 + 时间戳 作为新的文件名
                // 因为需要id，所以必须要从session中获取
                // getSession():是需要传参的，参数是Boolean，默认值为true
                // true和false有什么区别？
                // 如果传入值为true，在获取系统session的时候，如果session为null，也就是说系统中没有session则会默认自动创建一个
                // 如果传入的值为false，不会创建，直接返回为null

                String newFileName = FileNameUtil.getFileName(shopId);
                System.out.println("FileName工具类传入shopId返回的新文件名"+newFileName);
                // 3.获取原始文件的后缀名
                String substring = oldFileName.substring(oldFileName.lastIndexOf("."));
                System.out.println("原始文件名的后缀："+substring);

                // 4.完成新的文件名
                newFileName = newFileName + substring;
                // 5.生成文件上传路径
                String filePath="/shopPic/shopId="+shopId+"/";
                filePath += new DateTime().toString("yyyy/MM/dd");//设置格式
                ///shopPic/shopId=1/2019/12/06


                System.out.println("格式之后的日期，也就是ftp存放文件的地址路径："+filePath);
                // 6.调用上传工具类
                System.out.println("查看ftp服务器路径信息"+ftpProperties);
                System.out.println("查看ftp服务器host地址"+ftpProperties.getHost());
                Boolean ifSuccess = FtpUtil.uploadFile(ftpProperties.getHost(), Integer.parseInt(ftpProperties.getPort()), ftpProperties.getUsername(),
                        ftpProperties.getPassword(), ftpProperties.getBasePath(), filePath, newFileName, file.getInputStream());
                // 7.判断是否上传成功
                if(ifSuccess) {
                    // 说明上传成功，把文件的路径和文件新的名称以及文件的原始名称更新进数据库
                    // 通过id进行更新，也就是说只需要获取到headPicPath,newFilename,originalName就可以了
                    // headPicPath:http://ip地址/2019/10/21/文件的新名称
                    // http://ip地址:ftpProperties.getHttpPath()
                    // 2019/10/21:filePath
                    // 文件的新名称:newFileName;
                    String PicPath = ftpProperties.getHttpPath() + "/" + filePath + "/" + newFileName;//IP地址+日期+随机生成的文件名
               /* User u = new User();
                u.setId(shopId);
                u.setHeadPicPath(headPicPath);
                u.setNewFileName(newFileName);
                u.setOriginalFileName(oldFileName);
                Integer updateResult = userMapper.updateHeadPicAndFileNameAndOldFileNameById(u);*/
                    HashMap<String, String> m = new HashMap<String, String>();
                    m.put("PicPath",PicPath);
                    m.put("id",shopId.toString());
                    m.put("rowWord",rowWord);
                    Integer updateResult = picMapper.updateShopImagesById(m);

                }
            } catch (Exception e) {
                resultMap.put("code", "404");
                resultMap.put("msg", "更新失败，出现异常！");
                e.printStackTrace();
            }
        }
        System.out.println("查看循环的次数"+ii);


        resultMap.put("code", "200");
        resultMap.put("msg", "更新成功");






        return resultMap;
    }




    public Map<String, Object> uploadProductPicAjax(MultipartFile file, HttpServletRequest request, Long productId) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // 1.获取原始文件名(需要获取文件的后缀名)
        String oldFileName = file.getOriginalFilename();
        System.out.println(oldFileName);



        try {
            // 2.生成新的文件名
            // id + 随机数 + 时间戳 作为新的文件名
            // 因为需要id，所以必须要从session中获取
            // getSession():是需要传参的，参数是Boolean，默认值为true
            // true和false有什么区别？
            // 如果传入值为true，在获取系统session的时候，如果session为null，也就是说系统中没有session则会默认自动创建一个
            // 如果传入的值为false，不会创建，直接返回为null

            String newFileName = FileNameUtil.getFileName(productId);
            System.out.println("FileName工具类传入shopId返回的新文件名"+newFileName);
            // 3.获取原始文件的后缀名
            String substring = oldFileName.substring(oldFileName.lastIndexOf("."));
            System.out.println("原始文件名的后缀："+substring);

            // 4.完成新的文件名
            newFileName = newFileName + substring;
            // 5.生成文件上传路径
            String filePath="/productPic/productId="+productId+"/";
            filePath += new DateTime().toString("yyyy/MM/dd");//设置格式
            ///shopPic/shopId=1/2019/12/06

            System.out.println("格式之后的日期，也就是ftp存放文件的地址路径："+filePath);
            // 6.调用上传工具类
            System.out.println("查看ftp服务器路径信息"+ftpProperties);
            System.out.println("查看ftp服务器host地址"+ftpProperties.getHost());
            Boolean ifSuccess = FtpUtil.uploadFile(ftpProperties.getHost(), Integer.parseInt(ftpProperties.getPort()), ftpProperties.getUsername(),
                    ftpProperties.getPassword(), ftpProperties.getBasePath(), filePath, newFileName, file.getInputStream());
            // 7.判断是否上传成功
            if(ifSuccess) {
                // 说明上传成功，把文件的路径和文件新的名称以及文件的原始名称更新进数据库
                // 通过id进行更新，也就是说只需要获取到headPicPath,newFilename,originalName就可以了
                // headPicPath:http://ip地址/2019/10/21/文件的新名称
                // http://ip地址:ftpProperties.getHttpPath()
                // 2019/10/21:filePath
                // 文件的新名称:newFileName;
                String PicPath = ftpProperties.getHttpPath() + "/" + filePath + "/" + newFileName;//IP地址+日期+随机生成的文件名
               /* User u = new User();
                u.setId(shopId);
                u.setHeadPicPath(headPicPath);
                u.setNewFileName(newFileName);
                u.setOriginalFileName(oldFileName);
                Integer updateResult = userMapper.updateHeadPicAndFileNameAndOldFileNameById(u);*/
                HashMap<String, String> m = new HashMap<String, String>();
                m.put("PicPath",PicPath);
                m.put("id",productId.toString());

                Integer updateResult = picMapper.updateShopImagesById(m);

                if(updateResult > 0) {
                    // 说明更新成功，需要把头像图片显示在页面上，也就是说必须要把headPicPath返回给controller
                    resultMap.put("code", "200");
                    resultMap.put("data", PicPath);
                } else {
                    resultMap.put("code", "404");
                    resultMap.put("msg", "头像保存失败");
                }
            } else {
                resultMap.put("code", "404");
                resultMap.put("msg", "头像上传失败");
            }
        } catch (Exception e) {
            resultMap.put("code", "404");
            resultMap.put("msg", "用户获取失败");
            e.printStackTrace();
        }
        return resultMap;
    }


}
