package com.jee.ssm.modules.ssm.services;


import com.github.pagehelper.PageInfo;
import com.jee.ssm.model.Fgoodsin;
import com.jee.ssm.model.Fkucun;
import com.jee.ssm.model.param.ParamMap;
import com.jee.ssm.modules.ssm.dao.FgoodsinDao;
import com.jee.ssm.modules.ssm.dao.FkucunDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 账户 Service
 *
 * @author 王冲
 * @version 1.0
 */
@Service
public class FgoodsinService extends BaseService<Fgoodsin> {

    @Resource
    private FgoodsinDao fgoodsinDao;

    /**
     * 保存数据
     * @param fgoodsin 实体对象
     * @return 实体id
     * @throws Exception 数据保存异常
     */
    public Integer insert(Fgoodsin fgoodsin) throws Exception {
        return fgoodsinDao.insert(fgoodsin);
    }

    /**
     * 查询列表 带分页
     * @param map  参数
     * @param page 页码
     * @param size 每页大小
     * @return 列表
     * @throws Exception 数据返回异常
     */
    public PageInfo<Fgoodsin> selectXiangQingByGoodsId(Map map, int page, int size) throws Exception {
        return fgoodsinDao.selectXiangQingByGoodsId(map, page, size);
    }


}
