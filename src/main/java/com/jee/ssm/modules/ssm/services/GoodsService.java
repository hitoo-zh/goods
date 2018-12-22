package com.jee.ssm.modules.ssm.services;

import com.github.pagehelper.PageInfo;
import com.jee.ssm.Utils.UUIDUtils;
import com.jee.ssm.Utils.UtilsUpload;
import com.jee.ssm.model.Goods;
import com.jee.ssm.model.Out;
import com.jee.ssm.model.Student;
import com.jee.ssm.modules.ssm.dao.GoodsDao;
import com.jee.ssm.modules.ssm.dao.OutDao;
import com.jee.ssm.modules.ssm.dao.StudentDao;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 账户 Service
 * @author GaoXiang
 * @version 1.0
 */
@Service
public class GoodsService extends BaseService<Goods>{

    @Resource
    private GoodsDao goodsDao;

    @Resource
    private OutDao outDao;

    public List<Goods> rukuByIds(List<String> ids) throws Exception {
        return goodsDao.selectByIds(ids);
    }

    public PageInfo<Out> outList(Map map, int page, int size) throws Exception {
        System.out.println();
        return outDao.list(map,page,size);
    }
    /**
     * 查询列表 带分页
     * @param map 参数
     * @param page 页码
     * @param size 每页大小
     * @return 列表
     * @throws Exception 数据返回异常
     */
    public PageInfo<Goods> list(Map map,int page,int size) throws Exception {
        System.out.println();
        return goodsDao.list(map,page,size);
    }

    public void save(MultipartFile file,Goods goods){
        if (!file.isEmpty()) {
            String imgPath = UtilsUpload.uploadImgByMultipartFile(file, false);
            goods.setPhoto(imgPath);
        }
        if (StringUtils.isEmpty(goods.getId())) {
            //goods.setId(UUIDUtils.getUUID());
            int id=Integer.parseInt(goodsDao.selectId().get(0).getId())+1;
            goods.setId(id+"");
            goods.setCreateTime(new Date());
            goods.setCreateBy("");
            try{
                int a= goodsDao.insert(goods);
            }catch (Exception e){

            }
        }else{
            try{
                int a=goodsDao.updateById(goods);
            }catch (Exception e){

            }
        }
    }

}
