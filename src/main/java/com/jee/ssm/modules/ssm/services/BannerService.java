package com.jee.ssm.modules.ssm.services;

import com.github.pagehelper.PageInfo;
import com.jee.ssm.Utils.UUIDUtils;
import com.jee.ssm.Utils.UtilsUpload;
import com.jee.ssm.model.Article;
import com.jee.ssm.model.Banner;
import com.jee.ssm.modules.ssm.dao.ArticleDao;
import com.jee.ssm.modules.ssm.dao.BannerDao;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 账户 Service
 * @author GaoXiang
 * @version 1.0
 */
@Service
public class BannerService extends BaseService<Banner>{

    @Resource
    private BannerDao bannerDao;


    public PageInfo<Banner> list(Map map,int page,int size) {
        return bannerDao.list(map,page,size);
    }

    public void save(MultipartFile[] files,Banner banner){
        if (files != null &&files.length>0) {
           String paths="";
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                //System.out.println("qqqqqqqqqqqqq"+file.getOriginalFilename());
                String imgPath = UtilsUpload.uploadImgByMultipartFile(file, false);
                paths+=imgPath+",";
            }
           // System.out.println("aaaaaaaaaaaaaaaaaaaa"+paths);
            banner.setImgs(paths);
        }
        if (StringUtils.isEmpty(banner.getId())) {
            banner.setId(UUIDUtils.getUUID());
            try{
                int a= bannerDao.insert(banner);
            }catch (Exception e){

            }
        }else{
            try{
                int a=bannerDao.updateById(banner);
            }catch (Exception e){

            }
        }
    }

}
