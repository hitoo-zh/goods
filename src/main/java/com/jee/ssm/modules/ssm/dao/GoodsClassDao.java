package com.jee.ssm.modules.ssm.dao;


import com.github.pagehelper.PageInfo;
import com.jee.ssm.model.GoodsClass;
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
public class GoodsClassDao extends LzDao<GoodsClass> {

    /**
     * 查询列表
     * @param map 参数
     * @return 列表
     */
    public PageInfo<GoodsClass> list(Map map){
        return list("GoodsClassMapper.list",map);
    }

    /**
     * 查询列表 带分页
     * @param map 参数
     * @param page 页码
     * @param size 每页大小
     * @return 列表
     * @throws Exception 数据返回异常
     */
    public PageInfo<GoodsClass> list(Map map, int page, int size) throws Exception {
        return list("GoodsClassMapper.list",map,new RowBounds(page,size));
    }

    /**
     * 根据 id 查找
     * @param id 实体id
     * @return 实体
     */
    public GoodsClass selectById(String id){
        return selectOne("GoodsClassMapper.selectById",id);
    }

    /**
     * 根据 id 查找
     * @param id 实体id
     * @return 实体
     */
    public List<GoodsClass> selectByIds(String id){
        return arrayList("GoodsClassMapper.selectById",id);
    }

    /**
     * 查询全部
     * @return 实体
     */
    public List<GoodsClass> selectAll(){
        return arrayList("GoodsClassMapper.selectAll","");
    }

    public Integer insert(GoodsClass goodsClass) throws Exception {
        return insert("GoodsClassMapper.insert",goodsClass);
    }

    public Integer updateById(GoodsClass goodsClass) throws Exception {
        return update("GoodsClassMapper.updateById",goodsClass);
    }

    public Integer deleteById(String id) throws Exception {
        return delete("GoodsClassMapper.deleteById",id);
    }

    /**
     * 根据 id 批量删除
     * @param ids 要删除的id
     * @return 影响的行数
     * @throws Exception 数据删除异常
     */
    public Integer deleteByIds(List<String> ids) throws Exception {
        return delete("GoodsClassMapper.deleteByIds",ids);
    }

}
