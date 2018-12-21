package com.jee.ssm.modules.ssm.services;

import com.github.pagehelper.PageInfo;
import com.jee.ssm.Utils.UUIDUtils;
import com.jee.ssm.Utils.UtilsUpload;
import com.jee.ssm.model.Fgoods;
import com.jee.ssm.model.Goods;
import com.jee.ssm.model.Out;
import com.jee.ssm.modules.ssm.dao.FgoodsDao;
import com.jee.ssm.modules.ssm.dao.GoodsDao;
import com.jee.ssm.modules.ssm.dao.OutDao;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 账户 Service
 *
 * @author GaoXiang
 * @version 1.0
 */
@Service
public class FgoodsService extends BaseService<Fgoods> {
    @Resource
    private FgoodsDao fgoodsDao;

    /**
     * 保存数据
     * @param fgoods 实体对象
     * @return 实体id
     * @throws Exception 数据保存异常
     */
    public Integer insert(MultipartFile file,Fgoods fgoods) throws Exception {
        if (!file.isEmpty()) {
            String imgPath = UtilsUpload.uploadImgByMultipartFile(file, false);
            fgoods.setImg(imgPath);
        }
      //  fgoods.setId(UUIDUtils.getUUID());
        return fgoodsDao.insert(fgoods);
    }

    /**
     * 根据 id 修改
     * @param fgoods 带id的实体对象
     * @return 受影响的行数
     * @throws Exception 数据修改异常
     */
    public Integer updateById(Fgoods fgoods) throws Exception {
        return fgoodsDao.updateById(fgoods);
    }

    /**
     * 根据 id 删除
     * @param id 数据id
     * @return 受影响的行数
     * @throws Exception 数据删除异常
     */
    public Integer deleteById(String id) throws Exception {
        fgoodsDao.deleteById(id);
        return fgoodsDao.deleteById(id);
    }

    /**
     * 根据 id 批量删除
     * @param ids 要删除的id
     * @return 影响的行数
     * @throws Exception 数据删除异常
     */
    public Integer deleteByIds(List<String> ids) throws Exception {
        for (String id : ids) {
            fgoodsDao.deleteById(id);
        }
        return fgoodsDao.deleteByIds(ids);
    }

    /**
     * 根据 id 查找
     * @param id 实体id
     * @return 实体
     * @throws Exception 查询异常
     */
    public Fgoods selectById(String id)  {

        return fgoodsDao.selectById(id);
    }

    /**
     * 查询列表
     * @param map 参数
     * @return 列表
     * @throws Exception 数据返回异常
     */
    public PageInfo<Fgoods> list(Map map)   {
        return fgoodsDao.list(map);
    }

    /**
     * 查询列表 带分页z
     * @param map 参数
     * @param page 页码
     * @param size 每页大小
     * @return 列表
     * @throws Exception 数据返回异常
     */
    public PageInfo<Fgoods> list(Map map,int page,int size) throws Exception {
        return fgoodsDao.list(map,page,size);
    }

}
