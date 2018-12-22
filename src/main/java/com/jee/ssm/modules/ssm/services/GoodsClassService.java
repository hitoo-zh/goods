package com.jee.ssm.modules.ssm.services;


import com.github.pagehelper.PageInfo;
import com.jee.ssm.model.GoodsClass;
import com.jee.ssm.model.Store;
import com.jee.ssm.modules.ssm.dao.GoodsClassDao;
import com.jee.ssm.modules.ssm.dao.StoreDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 账户 Service
 * @author 王冲
 * @version 1.0
 */
@Service
public class GoodsClassService extends BaseService<GoodsClass>{

    @Resource
    private GoodsClassDao goodsClassDao;

    /**
     * 保存数据
     * @param goodsClass 实体对象
     * @return 实体id
     * @throws Exception 数据保存异常
     */
    public Integer insert(GoodsClass goodsClass) throws Exception {
        return goodsClassDao.insert(goodsClass);
    }

    /**
     * 根据 id 修改
     * @param goodsClass 带id的实体对象
     * @return 受影响的行数
     * @throws Exception 数据修改异常
     */
    public Integer updateById(GoodsClass goodsClass) throws Exception {
        return goodsClassDao.updateById(goodsClass);
    }

    /**
     * 根据 id 删除
     * @param id 数据id
     * @return 受影响的行数
     * @throws Exception 数据删除异常
     */
    public Integer deleteById(String id) throws Exception {
        goodsClassDao.deleteById(id);
        return goodsClassDao.deleteById(id);
    }

    /**
     * 根据 id 查找
     * @param id 实体id
     * @return 实体
     * @throws Exception 查询异常
     */
    public GoodsClass selectById(String id)  {

        return goodsClassDao.selectById(id);
    }

    /**
     * 查询全部
     * @throws Exception 查询异常
     */
    public List<GoodsClass> selectAll()  {

        return goodsClassDao.selectAll();
    }

    /**
     * 根据 id 批量删除
     * @param ids 要删除的id
     * @return 影响的行数
     * @throws Exception 数据删除异常
     */
    public Integer deleteByIds(List<String> ids) throws Exception {
        for (String id : ids) {
            goodsClassDao.deleteById(id);
        }
        return goodsClassDao.deleteByIds(ids);
    }

    /**
     * 查询列表
     * @param map 参数
     * @return 列表
     * @throws Exception 数据返回异常
     */
    public PageInfo<GoodsClass> list(Map map)   {
        return goodsClassDao.list(map);
    }

    /**
     * 查询列表 带分页
     * @param map 参数
     * @param page 页码
     * @param size 每页大小
     * @return 列表
     * @throws Exception 数据返回异常
     */
    public PageInfo<GoodsClass> list(Map map,int page,int size) throws Exception {
        return goodsClassDao.list(map,page,size);
    }

}
