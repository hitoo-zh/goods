package com.jee.ssm.modules.ssm.dao;

import com.github.pagehelper.PageInfo;
import com.jee.ssm.model.Account;
import com.jee.ssm.model.Article;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 账户 Dao
 * @author GaoXiang
 * @version 1.0
 */
@Repository
public class ArticleDao extends LzDao<Article> {

   /* public PageInfo<Article> list(Map map) {
        return list("ArticleMapper.list",map);
    }*/

    public PageInfo<Article> list(Map map,int page,int size) {

        return list("ArticleMapper.list",map,new RowBounds(page,size));
    }
    public Integer insert(Article article) throws Exception {
        return insert("ArticleMapper.insert",article);
    }

    public Integer updateById(Article article) throws Exception {

        return update("ArticleMapper.updateById",article);
    }

    public Article selectById(String id) throws Exception{

        return selectOne("ArticleMapper.selectById",id);
    }
}
