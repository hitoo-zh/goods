package com.jee.ssm.modules.ssm.services;


import com.github.pagehelper.PageInfo;
import com.jee.ssm.model.Fkucun;
import com.jee.ssm.model.Store;
import com.jee.ssm.model.param.ParamMap;
import com.jee.ssm.modules.ssm.dao.FkucunDao;
import com.jee.ssm.modules.ssm.dao.StoreDao;
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
public class FkucunService extends BaseService<Fkucun> {

    @Resource
    private FkucunDao fkucunDao;

    /**
     * 保存数据
     *
     * @param fkucun 实体对象
     * @return 实体id
     * @throws Exception 数据保存异常
     */
    public Integer insert(Fkucun fkucun) throws Exception {
        return fkucunDao.insert(fkucun);
    }

    /**
     * 根据 id 修改
     *
     * @param fkucun 带id的实体对象
     * @return 受影响的行数
     * @throws Exception 数据修改异常
     */
    public Integer updateById(Fkucun fkucun) throws Exception {
        return fkucunDao.updateById(fkucun);
    }

    /**
     * 根据 id 删除
     *
     * @param id 数据id
     * @return 受影响的行数
     * @throws Exception 数据删除异常
     */
    public Integer deleteById(String id) throws Exception {
        fkucunDao.deleteById(id);
        return fkucunDao.deleteById(id);
    }

    /**
     * 根据 id 查找
     *
     * @param id 实体id
     * @return 实体
     * @throws Exception 查询异常
     */
    public Fkucun selectById(String id) {

        return fkucunDao.selectById(id);
    }

    /**
     * 根据 id 批量删除
     *
     * @param ids 要删除的id
     * @return 影响的行数
     * @throws Exception 数据删除异常
     */
    public Integer deleteByIds(List<String> ids) throws Exception {
        for (String id : ids) {
            fkucunDao.deleteById(id);
        }
        return fkucunDao.deleteByIds(ids);
    }

    /**
     * 查询列表
     *
     * @param map 参数
     * @return 列表
     * @throws Exception 数据返回异常
     */
    public PageInfo<Fkucun> list(Map map) {
        return fkucunDao.list(map);
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
        return fkucunDao.list(map, page, size);
    }

    /**
     * 通过storeId查询库存表
     *
     * @return 列表
     * @throws Exception 数据返回异常
     */
    public PageInfo<Fkucun> selectGoodsByStoreId(ParamMap storeId, int page, int size) {

        return fkucunDao.selectGoodsByStoreId(storeId,page,size);
    }
}
