package com.jee.ssm.modules.ssm.services;

import com.github.pagehelper.PageInfo;
import com.jee.ssm.model.Account;
import com.jee.ssm.model.ArticleCat;
import com.jee.ssm.modules.ssm.dao.AccountDao;
import com.jee.ssm.modules.ssm.dao.ArticleCatDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 账户 Service
 * @author GaoXiang
 * @version 1.0
 */
@Service
public class ArticleCatService extends BaseService<ArticleCat>{

    @Resource
    private ArticleCatDao articleCatDao;

    /**
     * 查询列表
     * @param map 参数
     * @return 列表
     * @throws Exception 数据返回异常
     */
    public PageInfo<ArticleCat> list(Map map) {
        return articleCatDao.list(map);
    }


}
