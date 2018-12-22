package com.jee.ssm.modules.ssm.dao;


import com.github.pagehelper.PageInfo;
import com.jee.ssm.model.Fkucun;
import com.jee.ssm.model.Store;
import com.jee.ssm.model.param.ParamMap;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 账户 Dao
 *
 * @author 王冲
 * @version 1.0
 */
@Repository
public class FkucunDao extends LzDao<Fkucun> {

    /**
     * 查询列表
     *
     * @param map 参数
     * @return 列表
     */
    public PageInfo<Fkucun> list(Map map) {
        return list("FkucunMapper.list", map);
    }

    /**
     * 查询列表 带分页
     *
     * @param map  参数
     * @param page 页码
     * @param size 每页大小
     * @return 列表
     * @throws Exception 数据返回异常
     */
    public PageInfo<Fkucun> list(Map map, int page, int size) throws Exception {
        return list("FkucunMapper.list", map, new RowBounds(page, size));
    }

    /**
     * 根据 id 查找
     *
     * @param id 实体id
     * @return 实体
     */
    public Fkucun selectById(String id) {
        return selectOne("FkucunMapper.selectById", id);
    }

    /**
     * 根据 id 查找
     *
     * @param storeId 实体id
     * @return 实体
     */
    public PageInfo<Fkucun> selectGoodsByStoreId(ParamMap storeId, int page, int size) {
        return list("FkucunMapper.selectGoodsByStoreId", storeId, new RowBounds(page, size));
    }

    /**
     * 根据 添加
     *
     * @return 实体
     */
    public Integer insert(Fkucun fkucun) throws Exception {
        return insert("FkucunMapper.insert", fkucun);
    }

    /**
     * 根据 id 修改
     *
     * @return 实体
     */
    public Integer updateById(Fkucun fkucun) throws Exception {
        return update("FkucunMapper.updateById", fkucun);
    }

    /**
     * 根据 id 删除
     *
     * @param id 实体id
     * @return 实体
     */
    public Integer deleteById(String id) throws Exception {
        return delete("FkucunMapper.deleteById", id);
    }

    /**
     * 根据 id 批量删除
     *
     * @param ids 要删除的id
     * @return 影响的行数
     * @throws Exception 数据删除异常
     */
    public Integer deleteByIds(List<String> ids) throws Exception {
        return delete("FkucunMapper.deleteByIds", ids);
    }

}
