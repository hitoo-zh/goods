package com.jee.ssm.modules.ssm.controller;


import com.github.pagehelper.PageInfo;
import com.jee.ssm.common.config.Const;
import com.jee.ssm.common.log.AdminControllerLog;
import com.jee.ssm.common.utils.UUIDFactory;
import com.jee.ssm.model.*;
import com.jee.ssm.model.json.Tip;
import com.jee.ssm.model.param.ParamMap;
import com.jee.ssm.modules.ssm.dao.FgoodsDao;
import com.jee.ssm.modules.ssm.dao.FkucunDao;
import com.jee.ssm.modules.ssm.dao.GoodsClassDao;
import com.jee.ssm.modules.ssm.dao.StoreDao;
import com.jee.ssm.modules.ssm.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 账户 Controller
 *
 * @author 王冲
 * @version 1.0
 */
@Controller
@RequestMapping("/fkucun")
public class FkucunController extends AdminBaseController<Fkucun> {

    @Resource
    private FkucunService fkucunService;

    @Resource
    private FkucunDao fkucunDao;

    @Resource
    private FgoodsService fgoodsService;

    @Resource
    private StoreService storeService;

    @Resource
    private FgoodsinService fgoodsinService;

    @Resource
    private GoodsClassService goodsClassService;

    @Resource
    private GoodsClassDao goodsClassDao;

    @Resource
    private FgoodsDao fgoodsDao;

    @Resource
    private GoodsClassService goodsclassService;

    //----------------------- property ----------------------

    /**
     * 获取店铺列表 获取全部 不分页
     *
     * @param request 请求参数
     * @return 店铺列表页面
     * @throws Exception 获取店铺列表失败
     */
    @RequestMapping(value = "/all")
    public String all(HttpServletRequest request, ModelMap modelMap) throws Exception {
        ParamMap paramMap = new ParamMap(request);
        PageInfo<Fkucun> pageInfo = fkucunService.list(paramMap);
        paramMap.put("pageInfo", pageInfo);
        modelMap.putAll(paramMap);
        return "manager/fkucun/list";
    }

    /**
     * 获取列表 分页
     *
     * @return 列表页面
     * @throws Exception 获取列表失败
     */
    @RequestMapping(value = "/list")
    public String list(HttpServletRequest request, ModelMap modelMap, Integer page, Integer size) throws Exception {
        ParamMap paramMap = new ParamMap(request);
        PageInfo<Fkucun> pageInfo = fkucunService.list(paramMap, page, size);
        modelMap.put("pageInfo", pageInfo);
        modelMap.putAll(paramMap);
        return "manager/fkucun/list";
    }

    /**
     * 获取列表 分页
     *
     * @return 列表页面
     * @throws Exception 获取列表失败
     */
    @RequestMapping(value = "/xiangqing")
    public String xiangqing(HttpServletRequest request, ModelMap modelMap, Integer page, Integer size) throws Exception {
//        page = 0;
//        size = 10;
        ParamMap paramMap = new ParamMap(request);
        PageInfo<Fgoodsin> pageInfo = fgoodsinService.selectXiangQingByGoodsId(paramMap, page, size);
        modelMap.put("pageInfo", pageInfo);
        modelMap.putAll(paramMap);
        return "manager/fkucun/xiangqing";
    }

    /**
     * 进入店铺
     */
    @RequestMapping(value = "/selectGoodsByStoreId")
    public String selectGoodsByStoreId(HttpServletRequest request, ModelMap model, Integer page, Integer size) {
        ParamMap paramMap = new ParamMap(request);
        PageInfo<Fkucun> kcList = fkucunService.selectGoodsByStoreId(paramMap, page, size);
        for (Fkucun fk : kcList.getList()) {
            Fgoods goods = fgoodsService.selectById(fk.getGoodsId());
            Store store = storeService.selectById(fk.getStoreId());
            if (goods != null) {
                fk.setGoodsName(goods.getName());
            }
            if (store != null) {
                fk.setStoreName(store.getName());
            }

        }
        model.put("pageInfo", kcList);
        return "manager/fkucun/list";
    }

    /**
     * 入库
     * 通过rukuid查询对象
     */
    @RequestMapping(value = "/ruku")
    public String ruku(String id, ModelMap model) {
        Fkucun kc = fkucunService.selectById(id);
        model.put("data", kc);
        return "manager/fkucun/ruku";

    }

    @RequestMapping("/selectGoodsClass")
    public ModelAndView selectGoodsClass(Model model, HttpServletRequest request) {

        List goodsClasses = goodsClassDao.selectAll();
        model.addAttribute("goodsClasses", goodsClasses);
        model.addAttribute("storeId", request.getParameter("storeId"));
        return new ModelAndView("manager/fkucun/add");
    }

    @RequestMapping("/selectGoods")
    @ResponseBody
    public ModelAndView selectGoods(String id, Model model) {
        List goodses = fgoodsDao.selectByIds(id);//通过分类id查询商品
        model.addAttribute("goodses", goodses);
        return new ModelAndView("manager/fkucun/option");
    }

    /**
     * 进入商品添加页面 携带一个生成的id --> longId
     *
     * @param model 返回的实体容器
     * @return 添加页面
     */
    @RequestMapping(value = "/add")
    public ModelAndView add(ModelMap model) {
        model.put("longId", UUIDFactory.getStringId());
        return new ModelAndView("manager/fkucun/add", model);
    }

    /**
     * 商品添加
     *
     * @return 成功状态
     * @throws Exception 数据添加异常
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public Tip save(String goodsId, String storeId) throws Exception {
        Fkucun fkucun = new Fkucun();
        fkucun.setId(UUIDFactory.getStringId());
        fkucun.setGoodsId(goodsId);
        fkucun.setStoreId(storeId);
        fkucunService.insert(fkucun);
        return new Tip();
    }


    /**
     * 根据 id 删除
     *
     * @param id id
     * @return 成功状态
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    @AdminControllerLog(description = "删除")
    public Tip delete(@RequestParam String id) {
        try {
            fkucunService.deleteById(id);
            return new Tip();
        } catch (Exception e) {
            //e.printStackTrace();
            return new Tip(1);
        }
    }

    /**
     * 根据 id 列表批量删除
     *
     * @param ids 店铺id List
     * @return 成功状态
     */
    @RequestMapping(value = "/deleteByIds")
    @ResponseBody
    @AdminControllerLog(description = "批量删除")
    public Tip deleteByIds(@RequestParam("ids") List<String> ids) {
        try {
            fkucunService.deleteByIds(ids);
            return new Tip();
        } catch (Exception e) {
            //e.printStackTrace();
            return new Tip(1);
        }
    }

    /**
     * 进入店铺编辑页面
     *
     * @param model 返回店铺的容器
     * @param id    店铺id
     * @return 编辑页面
     * @throws Exception 进入编辑页面失败
     */
    @RequestMapping(value = "/edit")
    public ModelAndView edit(ModelMap model, @RequestParam String id) {
        model.put("data", fkucunService.selectById(id));
        return new ModelAndView("manager/fkucun/edit", model);
    }


    /**
     * 根据 id 修改入库 剩余数量
     *
     * @param fkucun 带id的店铺对象
     * @return 成功状态
     * @throws Exception 数据修改异常
     */
    @RequestMapping(value = "/updateNum")
    @ResponseBody
    @AdminControllerLog(description = "修改")
    public Tip updateNum(Fkucun fkucun, String mark, HttpServletRequest request) throws Exception {
        Fkucun fk = fkucunService.selectById(fkucun.getId());
        String num = "0";
        String shuliang = "0";
        if (fk != null) {
            num = fk.getGoodsNum();
            shuliang = fkucun.getGoodsNum();
        }
        Long nums = Long.valueOf(num) + Long.valueOf(shuliang);
        String tonNum = String.valueOf(nums);
        fkucun.setGoodsNum(tonNum);
        fkucunService.updateById(fkucun);

        //插入记录表
        Fgoodsin fd = new Fgoodsin();
        Account account = (Account) request.getSession().getAttribute(Const.ACCOUNT);
        fd.setId(UUIDFactory.getStringId());
        fd.setGoodsId(fk.getGoodsId());
        fd.setStoreId(fk.getStoreId());
        fd.setGoodsNum(shuliang);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String dateString = formatter.format(new Date());
        fd.setCreateTime(dateString);
        fd.setCreateManagerId(account.getUserName());
        fd.setMark(mark);
        fgoodsinService.insert(fd);
        return new Tip();
    }

    /**
     * 根据 id 修改
     *
     * @param fkucun 带id的店铺对象
     * @return 成功状态
     * @throws Exception 数据修改异常
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    @AdminControllerLog(description = "修改")
    public Tip update(Fkucun fkucun) throws Exception {
        fkucunService.updateById(fkucun);
        return new Tip();
    }

    /**
     * 根据 id 查找
     *
     * @param id id
     * @return 对象 jsonj
     * @throws Exception 查找店铺失败
     */
    @RequestMapping(value = "/findJson")
    @ResponseBody
    public Fkucun find(@RequestParam String id) {

        return fkucunService.selectById(id);
    }

}
