package com.jee.ssm.modules.ssm.dao;


import com.github.pagehelper.PageInfo;
import com.jee.ssm.model.Store;
import com.jee.ssm.model.Student;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 账户 Dao
 * @author 王冲
 * @version 1.0
 */
@Repository
public class StoreDao extends LzDao<Store> {

    /**
     * 查询列表
     * @param map 参数
     * @return 列表
     */
    public PageInfo<Store> list(Map map){
        return list("StoreMapper.list",map);
    }

    /**
     * 查询列表 带分页
     * @param map 参数
     * @param page 页码
     * @param size 每页大小
     * @return 列表
     * @throws Exception 数据返回异常
     */
    public PageInfo<Store> list(Map map, int page, int size) throws Exception {
        return list("StoreMapper.list",map,new RowBounds(page,size));
    }

    /**
     * 根据 id 查找
     * @param id 实体id
     * @return 实体
     */
    public Store selectById(String id){
        return selectOne("StoreMapper.selectById",id);
    }

    public Integer insert(Store store) throws Exception {
        return insert("StoreMapper.insert",store);
    }

    public Integer updateById(Store store) throws Exception {
        return update("StoreMapper.updateById",store);
    }

    public Integer deleteById(String id) throws Exception {
        return delete("StoreMapper.deleteById",id);
    }

    /**
     * 根据 id 批量删除
     * @param ids 要删除的id
     * @return 影响的行数
     * @throws Exception 数据删除异常
     */
    public Integer deleteByIds(List<String> ids) throws Exception {
        return delete("StoreMapper.deleteByIds",ids);
    }

}
