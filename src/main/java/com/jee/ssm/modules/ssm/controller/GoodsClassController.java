package com.jee.ssm.modules.ssm.controller;


import com.github.pagehelper.PageInfo;
import com.jee.ssm.common.config.Const;
import com.jee.ssm.common.log.AdminControllerLog;
import com.jee.ssm.common.utils.UUIDFactory;
import com.jee.ssm.model.Account;
import com.jee.ssm.model.GoodsClass;
import com.jee.ssm.model.json.Tip;
import com.jee.ssm.model.param.ParamMap;
import com.jee.ssm.modules.ssm.dao.FgoodsDao;
import com.jee.ssm.modules.ssm.dao.GoodsClassDao;
import com.jee.ssm.modules.ssm.services.GoodsClassService;
import javafx.scene.input.DataFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 账户 Controller
 *
 * @author 王冲
 * @version 1.0
 */
@Controller
@RequestMapping("/goodsclass")
public class GoodsClassController extends AdminBaseController<GoodsClass> {

    /**
     * 获取商品列表 获取全部 不分页
     *
     * @param request 请求参数
     * @return 商品列表页面
     * @throws Exception 获取商品列表失败
     */
    @RequestMapping(value = "/all")
//    @RequiresPermissions("store:list")
    public String all(HttpServletRequest request, ModelMap modelMap) throws Exception {

        ParamMap paramMap = new ParamMap(request);

        PageInfo<GoodsClass> pageInfo = goodsclassService.list(paramMap);
        paramMap.put("pageInfo", pageInfo);
        modelMap.putAll(paramMap);
        return "manager/goodsclass/list";
    }

    /**
     * 获取商品列表 分页
     *
     * @return 商品列表页面
     * @throws Exception 获取商品列表失败
     */
    @RequestMapping(value = "/list")
//    @RequiresPermissions("store:list")
    public String list(HttpServletRequest request, ModelMap modelMap, Integer page, Integer size) throws Exception {
        if (page == null) {
            page = 0;
        }
        if (size == null) {
            size = 10;
        }
        ParamMap paramMap = new ParamMap(request);
        PageInfo<GoodsClass> pageInfo = goodsclassService.list(paramMap, page, size);
        modelMap.put("pageInfo", pageInfo);
        modelMap.putAll(paramMap);
        return "manager/goodsclass/list";
    }



    /**
     * 进入商品添加页面 携带一个生成的id --> longId
     *
     * @param model 返回的实体容器
     * @return 添加页面
     */
    @RequestMapping(value = "/add")
//    @RequiresPermissions("store:add")
    public ModelAndView add(ModelMap model) {
        model.put("longId", UUIDFactory.getStringId());
        return new ModelAndView("manager/goodsclass/add", model);
    }

    /**
     * 商品添加
     * @param goodsClass 带id的商品对象
     * @return 成功状态
     * @throws Exception 数据添加异常
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    @AdminControllerLog(description = "添加商品")
//    @RequiresPermissions("store:add")
    public Tip save(GoodsClass goodsClass,HttpServletRequest request) throws Exception {

        goodsClass.setCreateTime(new Date());
        Account account= (Account) request.getSession().getAttribute(Const.ACCOUNT);
        goodsClass.setCreateBy(account.getInfoId());
        goodsclassService.insert(goodsClass);
        return new Tip();
    }


    /**
     * 根据 id 删除商品
     *
     * @param id 商品id
     * @return 成功状态
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    @AdminControllerLog(description = "删除商品")
//    @RequiresPermissions("store:delete")
    public Tip delete(@RequestParam String id) {

        try {
            goodsclassService.deleteById(id);
            return new Tip();
        } catch (Exception e) {
            //e.printStackTrace();
            return new Tip(1);
        }
    }

    /**
     * 根据 id 列表批量删除
     *
     * @param ids 商品id List
     * @return 成功状态
     */
    @RequestMapping(value = "/deleteByIds")
    @ResponseBody
    @AdminControllerLog(description = "批量删除商品")
//    @RequiresPermissions("role:delete")
    public Tip deleteByIds(@RequestParam("ids") List<String> ids) {

        try {
            goodsclassService.deleteByIds(ids);
            return new Tip();
        } catch (Exception e) {
            //e.printStackTrace();
            return new Tip(1);
        }
    }

    /**
     * 进入店铺编辑页面
     *
     * @param model 返回商品的容器
     * @param id    商品id
     * @return 编辑页面
     * @throws Exception 进入编辑页面失败
     */
    @RequestMapping(value = "/edit")
//    @RequiresPermissions("store:edit")
    public ModelAndView edit(ModelMap model, @RequestParam String id) {
        model.put("data", goodsclassService.selectById(id));
        return new ModelAndView("manager/goodsclass/edit", model);
    }

    /**
     * 根据 id 修改商品
     *
     * @param goodsClass 带id的商品对象
     * @return 成功状态
     * @throws Exception 数据修改异常
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    @AdminControllerLog(description = "修改商品")
//    @RequiresPermissions("store:edit")
    public Tip update(GoodsClass goodsClass) throws Exception {
        goodsClass.setCreateTime(new Date());
        goodsclassService.updateById(goodsClass);
        return new Tip();
    }

    /**
     * 根据 id 查找商品
     *
     * @param id 商品id
     * @return 商品对象 jsonj
     * @throws Exception 查找商品失败
     */
    @RequestMapping(value = "/findJson")
    @ResponseBody
    public GoodsClass find(@RequestParam String id) {

        return goodsclassService.selectById(id);
    }

    //----------------------- property ----------------------
    @Resource
    private GoodsClassService goodsclassService;


}
