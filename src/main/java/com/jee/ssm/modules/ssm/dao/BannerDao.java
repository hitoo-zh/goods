package com.jee.ssm.modules.ssm.dao;

import com.github.pagehelper.PageInfo;
import com.jee.ssm.model.Article;
import com.jee.ssm.model.Banner;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 账户 Dao
 * @author GaoXiang
 * @version 1.0
 */
@Repository
public class BannerDao extends LzDao<Banner> {


    public PageInfo<Banner> list(Map map,int page,int size) {

        return list("BannerMapper.list",map,new RowBounds(page,size));
    }

    public Integer insert(Banner banner) throws Exception {
        return insert("BannerMapper.insert",banner);
    }

    public Integer updateById(Banner banner) throws Exception {

        return update("BannerMapper.updateById",banner);
    }

    public Banner selectById(String id) throws Exception{

        return selectOne("BannerMapper.selectById",id);
    }
}
