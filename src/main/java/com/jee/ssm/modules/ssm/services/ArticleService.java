package com.jee.ssm.modules.ssm.services;

import com.github.pagehelper.PageInfo;
import com.jee.ssm.Utils.UUIDUtils;
import com.jee.ssm.Utils.UtilsUpload;
import com.jee.ssm.model.Account;
import com.jee.ssm.model.Article;
import com.jee.ssm.modules.ssm.dao.AccountDao;
import com.jee.ssm.modules.ssm.dao.ArticleDao;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 账户 Service
 * @author GaoXiang
 * @version 1.0
 */
@Service
public class ArticleService extends BaseService<Article>{

    @Resource
    private ArticleDao articleDao;

    public void save(MultipartFile file,Article article){
        if (!file.isEmpty()) {
            String imgPath = UtilsUpload.uploadImgByMultipartFile(file, false);
            article.setImg(imgPath);
        }
        if (StringUtils.isEmpty(article.getId())) {
            article.setId(UUIDUtils.getUUID());
            article.setCreateTime(new Date());
            article.setCreateBy("888888");
            try{
                int a= articleDao.insert(article);
            }catch (Exception e){

            }
        }else{
            try{
                int a=articleDao.updateById(article);
            }catch (Exception e){

            }
        }
    }


    /**
     * 查询列表 带分页
     * @param map 参数
     * @param page 页码
     * @param size 每页大小
     * @return 列表
     * @throws Exception 数据返回异常
     */
    public PageInfo<Article> list(Map map,int page,int size) {
        return articleDao.list(map,page,size);
    }

}
