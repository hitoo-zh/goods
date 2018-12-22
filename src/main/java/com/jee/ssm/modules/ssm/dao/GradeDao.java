package com.jee.ssm.modules.ssm.dao;

import com.github.pagehelper.PageInfo;
import com.jee.ssm.model.Grade;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 账户 Dao
 * @author GaoXiang
 * @version 1.0
 */
@Repository
public class GradeDao extends LzDao<Grade> {


    /**
     * 查询列表
     * @param map 参数
     * @return 列表
     * @throws Exception 数据返回异常
     */
    public PageInfo<Grade> list(Map map) {
        return list("GradeMapper.list",map);
    }
}
