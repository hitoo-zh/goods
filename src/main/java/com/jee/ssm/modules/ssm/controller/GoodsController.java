package com.jee.ssm.modules.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.jee.ssm.common.config.Const;
import com.jee.ssm.model.*;

import com.jee.ssm.model.json.Tip;
import com.jee.ssm.model.param.ParamMap;
import com.jee.ssm.modules.ssm.dao.*;
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
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController extends AdminBaseController<Goods> {
    @Resource
    private GoodsCatDao goodsCatDao;

    @Resource
    private GoodsDao goodsDao;

    @Resource
    private OutDao outDao;

    @Resource
    private GoodsService goodsService;


    @RequestMapping(value="/rukuByIds")
    @ResponseBody
    public ModelAndView rukuByIds(@RequestParam("ids") List<String> ids,String a,Model model) throws Exception{

            List<Goods> goodsList= goodsService.rukuByIds(ids);
            model.addAttribute("goodsList",goodsList);
            model.addAttribute("a",a);
            return new ModelAndView("manager/goods/churu");
    }

    @RequestMapping(value="/list")
    public String list(HttpServletRequest request, ModelMap modelMap, Integer page, Integer size) throws Exception {

        ParamMap paramMap = new ParamMap(request);
        PageInfo<Goods> pageInfo =  goodsService.list(paramMap,page,size);
        modelMap.put("pageInfo",pageInfo);
        modelMap.putAll(paramMap);
        return "manager/goods/list";
    }
    @RequestMapping(value="/outList")
    public String outList(HttpServletRequest request, ModelMap modelMap, Integer page, Integer size) throws Exception {

        ParamMap paramMap = new ParamMap(request);
        PageInfo<Out> pageInfo =  goodsService.outList(paramMap,page,size);
        modelMap.put("pageInfo",pageInfo);
        modelMap.putAll(paramMap);
        return "manager/out/list";
    }
    @RequestMapping(value="/tu")
    public String tu(Model model) throws Exception {
        Out out=new Out();
        out.setType("1");
        Long a=outDao.count(out);//出库
        out.setType("2");
        Long b=outDao.count(out);//入库
        model.addAttribute("chuku",a);
        model.addAttribute("ruku",b);
        return "manager/out/tu";
    }
    @RequestMapping(value="/add")
    public ModelAndView add(ModelMap model){
        List<GoodsCat>goodsCatList=goodsCatDao.list(new HashMap());
        model.addAttribute("goodsCatList",goodsCatList);
        return new ModelAndView("manager/goods/add",model);
    }

    @RequestMapping(value="/saveeezzy")
    @ResponseBody
    public Model save(@RequestParam("file") MultipartFile file,Goods goods,Model model){
        goodsService.save(file,goods);
        model.addAttribute("成功！");
        return model;
    }



    @RequestMapping(value="/edit")
    public ModelAndView edit(ModelMap model, @RequestParam String id) {
        try{
            Goods goods=goodsDao.selectById(id);
            model.put("data",goods);

            List<GoodsCat> goodsCatList=goodsCatDao.list(new HashMap());
            model.addAttribute("goodsCatList",goodsCatList);

            return new ModelAndView("manager/goods/add",model);
        }catch(Exception e){
            return null;
        }
    }

    @RequestMapping(value="/delete")
    @ResponseBody
    public Tip delete(@RequestParam String id) {
        try {
            goodsDao.deleteById(id);
            return new Tip();
        } catch (Exception e) {
            //e.printStackTrace();
            return new Tip(1);
        }
    }



}
