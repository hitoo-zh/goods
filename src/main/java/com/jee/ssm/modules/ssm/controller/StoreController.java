package com.jee.ssm.modules.ssm.controller;


import com.github.pagehelper.PageInfo;
import com.jee.ssm.common.log.AdminControllerLog;
import com.jee.ssm.common.utils.UUIDFactory;
import com.jee.ssm.model.Store;
import com.jee.ssm.model.json.Tip;
import com.jee.ssm.model.param.ParamMap;
import com.jee.ssm.modules.ssm.dao.StoreDao;
import com.jee.ssm.modules.ssm.services.StoreService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 账户 Controller
 * @author 王冲
 * @version 1.0
 */
@Controller
@RequestMapping("/store")
public class StoreController extends AdminBaseController<Store> {

    /**
     * 获取店铺列表 获取全部 不分页
     * @param request 请求参数
     * @return 店铺列表页面
     * @throws Exception 获取店铺列表失败
     */
    @RequestMapping(value="/all")
//    @RequiresPermissions("store:list")
    public String all(HttpServletRequest request,ModelMap modelMap) throws Exception {

        ParamMap paramMap = new ParamMap(request);
        PageInfo<Store> pageInfo =  storeService.list(paramMap);
        paramMap.put("pageInfo",pageInfo);
        modelMap.putAll(paramMap);
        return "manager/store/list";
    }

    /**
     * 获取店铺列表 分页
     * @return 店铺列表页面
     * @throws Exception 获取店铺列表失败
     */
    @RequestMapping(value="/list")
//    @RequiresPermissions("store:list")
    public String list(HttpServletRequest request,ModelMap modelMap,Integer page,Integer size) throws Exception {
//        if (page == null) {
//            page = 0;
//        }
//        if (size == null) {
//            size = 10;
//        }
        ParamMap paramMap = new ParamMap(request);
        PageInfo<Store> pageInfo =  storeService.list(paramMap,page,size);
        modelMap.put("pageInfo",pageInfo);
        modelMap.putAll(paramMap);
        return "manager/store/list";
    }

    /**
     * 进入店铺添加页面 携带一个生成的id --> longId
     * @param model 返回的实体容器
     * @return 添加页面
     */
    @RequestMapping(value="/add")
//    @RequiresPermissions("store:add")
    public ModelAndView add(ModelMap model){
        model.put("longId", UUIDFactory.getStringId());
        return new ModelAndView("manager/store/add",model);
    }

    /**
     * 店铺添加
     * @param store 带id的店铺对象
     * @return 成功状态
     * @throws Exception 数据添加异常
     */
    @RequestMapping(value="/save")
    @ResponseBody
    @AdminControllerLog(description = "添加店铺")
//    @RequiresPermissions("store:add")
    public Tip save(Store store) throws Exception {
//        store.setSetTime(new Date());
        storeService.insert(store);
        return new Tip();
    }


    /**
     * 根据 id 删除店铺
     * @param id 店铺id
     * @return 成功状态
     */
    @RequestMapping(value="/delete")
    @ResponseBody
    @AdminControllerLog(description = "删除店铺" )
//    @RequiresPermissions("store:delete")
    public Tip delete(@RequestParam String id) {

        try {
            storeService.deleteById(id);
            return new Tip();
        } catch (Exception e) {
            //e.printStackTrace();
            return new Tip(1);
        }
    }

    /**
     * 根据 id 列表批量删除
     * @param ids 店铺id List
     * @return 成功状态
     */
    @RequestMapping(value="/deleteByIds")
    @ResponseBody
    @AdminControllerLog(description = "批量删除店铺")
//    @RequiresPermissions("role:delete")
    public Tip deleteByIds(@RequestParam("ids") List<String> ids) {

        try {
            storeService.deleteByIds(ids);
            return new Tip();
        } catch (Exception e) {
            //e.printStackTrace();
            return new Tip(1);
        }
    }

    /**
     * 进入店铺编辑页面
     * @param model 返回店铺的容器
     * @param id 店铺id
     * @return 编辑页面
     * @throws Exception 进入编辑页面失败
     */
    @RequestMapping(value="/edit")
//    @RequiresPermissions("store:edit")
    public ModelAndView edit(ModelMap model, @RequestParam String id) {
        model.put("data",storeService.selectById(id));
        return new ModelAndView("manager/store/edit",model);
    }

    /**
     * 根据 id 修改店铺
     * @param store 带id的店铺对象
     * @return 成功状态
     * @throws Exception 数据修改异常
     */
    @RequestMapping(value="/update")
    @ResponseBody
    @AdminControllerLog(description = "修改店铺" )
//    @RequiresPermissions("store:edit")
    public Tip update(Store store) throws Exception {

        storeService.updateById(store);
        return new Tip();
    }

    /**
     * 根据 id 查找店铺
     * @param id 店铺id
     * @return 店铺对象 jsonj
     * @throws Exception 查找店铺失败
     */
    @RequestMapping(value="/findJson")
    @ResponseBody
    public Store find(@RequestParam String id) {

        return storeService.selectById(id);
    }

    //----------------------- property ----------------------
    @Resource
    private StoreService storeService;
    @Resource
    private StoreDao storeDao;

}
