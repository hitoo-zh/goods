package com.jee.ssm.Utils;


import com.jee.ssm.Utils.qiniu.RemoteFileService;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
public class UtilsUpload {
    /**
     * 通过远程请求的64Byte加密的字符串获取文件
     * @param data
     * @return
     */
    public static String uploadImgByStringData(String data){
        ByteArrayInputStream is = null;
        try {
            is = new ByteArrayInputStream(data.getBytes("ISO-8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return  uploadImg(is,"remote/pic.jpg",true);
    }


    /**
     * 上传一个MultipartFile
     * @param file
     * @return
     */
    public static String uploadImgByMultipartFile(MultipartFile file, boolean isRemote){
        try {
            return uploadImg(file.getInputStream(),file.getOriginalFilename(),isRemote);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 默认上传到image文件夹下面
     * @param inputStream 要上传的文件流
     * @param remotePath 要上传到七牛的路径以及文件名  example: product/img.jpg
     * @param isRemote 是否远程云端存储
     * @return
     */
    public static String uploadImg(InputStream inputStream, String remotePath, boolean isRemote){
        RemoteFileService remoteFileService = UtilsSpringContext.getBean(RemoteFileService.class);
        String path ="";
        try {
            path = remoteFileService.upload(inputStream,remotePath,"image",isRemote);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }

}
