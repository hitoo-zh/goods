package com.jee.ssm.modules.log.dao;

import com.jee.ssm.model.Log;
import com.jee.ssm.modules.ssm.dao.LzDao;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

/**
* 日志管理 Dao
* @author GaoXiang
* @version 1.0
*/
@Repository
public class LogDao extends LzDao<Log> {



    /**
    * 保存数据
    * @param log 实体对象
    * @return 实体id
    * @throws Exception 数据保存异常
    */
    public Integer insert(Log log) throws Exception {
        return insert("LogMapper.insert",log);
    }

    /**
    * 根据 id 修改
    * @param log 带id的实体对象
    * @return 受影响的行数
    * @throws Exception 数据修改异常
    */
    public Integer updateById(Log log) throws Exception {
        return update("LogMapper.updateById",log);
    }

    /**
    * 根据 id 删除
    * @param id 数据id
    * @return 受影响的行数
    * @throws Exception 数据删除异常
    */
    public Integer deleteById(String id) throws Exception {
        return delete("LogMapper.deleteById",id);
    }

    /**
    * 根据 id 查找
    * @param id 实体id
    * @return 实体
    */
    public Log selectById(String id) {
        return selectOne("LogMapper.selectById",id);
    }

    /**
    * 根据 id 批量删除
    * @param ids 要删除的id
    * @return 影响的行数
    * @throws Exception 数据删除异常
    */
    public Integer deleteByIds(List<String> ids) throws Exception {
        return delete("LogMapper.deleteByIds",ids);
    }

    /**
    * 查询列表
    * @param map 参数
    * @return 列表
    */
    public PageInfo<Log> list(Map map) {
        return list("LogMapper.list",map);
    }

    /**
    * 查询列表 带分页
    * @param map 参数
    * @param page 页码
    * @param size 每页大小
    * @return 列表
    */
    public PageInfo<Log> list(Map map,int page,int size) {
        return list("LogMapper.list",map,new RowBounds(page,size));
    }

}
