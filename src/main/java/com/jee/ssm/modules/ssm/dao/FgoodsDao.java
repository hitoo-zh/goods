package com.jee.ssm.modules.ssm.dao;

import com.github.pagehelper.PageInfo;
import com.jee.ssm.model.Fgoods;
import com.jee.ssm.model.Goods;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public class FgoodsDao extends LzDao<Fgoods> {

    /**
     * 查询列表
     * @param map 参数
     * @return 列表
     */
    public PageInfo<Fgoods> list(Map map){
        return list("FgoodsMapper.list",map);
    }

    /**
     * 查询列表 带分页
     * @param map 参数
     * @param page 页码
     * @param size 每页大小
     * @return 列表
     * @throws Exception 数据返回异常
     */
    public PageInfo<Fgoods> list(Map map, int page, int size) throws Exception {
        return list("FgoodsMapper.list",map,new RowBounds(page,size));
    }

    /**
     * 根据 id 查找
     * @param id 实体id
     * @return 实体
     */
    public Fgoods selectById(String id){
        return selectOne("FgoodsMapper.selectById",id);
    }

    public List<Fgoods> selectByIds(String id){
        return arrayList("FgoodsMapper.selectByClassId",id);
    }

    public Integer insert(Fgoods fgoods) throws Exception {
        return insert("FgoodsMapper.insert",fgoods);
    }

    public Integer updateById(Fgoods fgoods) throws Exception {
        return update("FgoodsMapper.updateById",fgoods);
    }

    public Integer deleteById(String id) throws Exception {
        return delete("FgoodsMapper.deleteById",id);
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

    public List<Fgoods> selectId(){

        return arrayList("FgoodsMapper.selectId",null);
    }

}
