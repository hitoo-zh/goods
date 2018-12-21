package com.jee.ssm.modules.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.jee.ssm.common.log.AdminControllerLog;
import com.jee.ssm.common.utils.UUIDFactory;
import com.jee.ssm.model.*;
import com.jee.ssm.model.json.Tip;
import com.jee.ssm.model.param.ParamMap;
import com.jee.ssm.modules.ssm.dao.FgoodsDao;
import com.jee.ssm.modules.ssm.dao.GoodsCatDao;
import com.jee.ssm.modules.ssm.dao.GoodsDao;
import com.jee.ssm.modules.ssm.dao.OutDao;
import com.jee.ssm.modules.ssm.services.FgoodsService;
import com.jee.ssm.modules.ssm.services.GoodsClassService;
import com.jee.ssm.modules.ssm.services.GoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/fgoods")
public class FgoodsController extends AdminBaseController<Fgoods> {

    @Resource
    private FgoodsDao fgoodsDao;
    @Resource
    private FgoodsService fgoodsService;
    @Resource
    private GoodsClassService goodsClassService;

    @RequestMapping(value="/list")
    public String list(HttpServletRequest request,ModelMap modelMap,Integer page,Integer size) throws Exception {
        if(page==null){
            page = 0;
        }
        if(size==null){
            size=10;
        }
        ParamMap paramMap = new ParamMap(request);
        PageInfo<Fgoods> pageInfo =  fgoodsService.list(paramMap,page,size);
        List<GoodsClass> gcList =  goodsClassService.selectAll();
      /*  PageInfo<Fgoods> pageInfos = new PageInfo<>();*/
        List<Fgoods> fdlist = new ArrayList<>();
        for(Fgoods pi:pageInfo.getList()){
            for(GoodsClass gc:gcList){
                if(pi.getClassId().equals(gc.getId())){
                        pi.setClassName(gc.getName());
                        fdlist.add(pi);
                }
            }

        }

        pageInfo.setList(fdlist);
        modelMap.put("pageInfo",pageInfo);
        modelMap.putAll(paramMap);
        return "manager/fgoods/list";
    }

    @RequestMapping(value = "/add")
    public ModelAndView add(ModelMap model) {
        List<GoodsClass> goodsClass = goodsClassService.selectAll();
        model.addAttribute("goodsClass", goodsClass);
        model.put("longId", UUIDFactory.getStringId());
        return new ModelAndView("manager/fgoods/add", model);
    }

    @RequestMapping(value = "/saveeezzy")
    @ResponseBody
    public Tip saveeezzy(@RequestParam("file") MultipartFile file,Fgoods fgoods,Model model) throws Exception {
        fgoodsService.insert(file,fgoods);
        model.addAttribute("成功！");
        return new Tip();
    }

    @RequestMapping(value="/delete")
    @ResponseBody
//    @AdminControllerLog(description = "删除店铺" )
    public Tip delete(@RequestParam String id) {
        try {
            fgoodsService.deleteById(id);
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
//    @AdminControllerLog(description = "批量删除")
    public Tip deleteByIds(@RequestParam("ids") List<String> ids) {
        try {
            fgoodsService.deleteByIds(ids);
            return new Tip();
        } catch (Exception e) {
            //e.printStackTrace();
            return new Tip(1);
        }
    }

    /**
     * 进入编辑页面
     * @param model 容器
     * @param id id
     * @return 编辑页面
     * @throws Exception 进入编辑页面失败
     */
    @RequestMapping(value="/edit")
    public ModelAndView edit(ModelMap model, @RequestParam String id) {
        List<GoodsClass> goodsClass = goodsClassService.selectAll();
        model.addAttribute("goodsClass", goodsClass);
        model.put("data",fgoodsService.selectById(id));
        GoodsClass goods = goodsClassService.selectById(fgoodsService.selectById(id).getClassId());
        model.put("goodsClassName",goods.getName());
        return new ModelAndView("manager/fgoods/edit",model);
    }

    /**
     * 根据 id 修改
     * @param fgoods 带id的对象
     * @return 成功状态
     * @throws Exception 数据修改异常
     */
    @RequestMapping(value="/update")
    @ResponseBody
    @AdminControllerLog(description = "修改" )
    public Tip update(Fgoods fgoods) throws Exception {

        fgoodsService.updateById(fgoods);
        return new Tip();
    }



}
