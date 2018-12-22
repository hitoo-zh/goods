package com.jee.ssm.modules.ssm.dao;

import com.github.pagehelper.PageInfo;
import com.jee.ssm.model.Goods;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public class GoodsDao extends LzDao<Goods> {

    public PageInfo<Goods> list(Map map,int page,int size) throws Exception {
        return list("GoodsMapper.list",map,new RowBounds(page,size));
    }
    public Integer insert(Goods goods) throws Exception {
        return insert("GoodsMapper.insert",goods);
    }

    public Integer updateById(Goods goods) throws Exception {

        return update("GoodsMapper.updateById",goods);
    }

    public Goods selectById(String id) throws Exception{

        return selectOne("GoodsMapper.selectById",id);
    }
    public Integer deleteById(String id) throws Exception {
        return delete("GoodsMapper.deleteById",id);
    }

    public List<Goods> selectByIds(List<String> ids){

        return arrayList("GoodsMapper.selectByIds",ids);
    }

    public List<Goods> selectId(){

        return arrayList("GoodsMapper.selectId",null);
    }


}
