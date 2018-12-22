package com.jee.ssm.modules.ssm.dao;



import com.github.pagehelper.PageInfo;
import com.jee.ssm.model.Fgoodsin;
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
public class FgoodsinDao extends LzDao<Fgoodsin> {

    /**
     * 根据 添加
     *
     * @return 实体
     */
    public Integer insert(Fgoodsin fgoodsin) throws Exception {
        return insert("FgoodsinMapper.insert", fgoodsin);
    }

    public PageInfo<Fgoodsin> selectXiangQingByGoodsId(Map map, int page, int size) throws Exception {
        return list("FgoodsinMapper.selectXiangQingByGoodsId",map,new RowBounds(page,size));
    }


}
