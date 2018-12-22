package com.jee.ssm.modules.ssm.dao;

import com.github.pagehelper.PageInfo;
import com.jee.ssm.model.BannerCat;
import com.jee.ssm.model.Class;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 账户 Dao
 * @author GaoXiang
 * @version 1.0
 */
@Repository
public class ClassDao extends LzDao<Class> {


    /**
     * 查询列表
     * @param map 参数
     * @return 列表
     * @throws Exception 数据返回异常
     */
    public PageInfo<Class> list(Map map) {
        return list("ClassMapper.list",map);
    }

    public List<Class> selectBy(String id){
        return arrayList("ClassMapper.selectByGradeId",id);
    }
    public Class selectById(String id){
        return selectOne("ClassMapper.selectById",id);
    }
}
