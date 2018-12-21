package com.jee.ssm.modules.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.jee.ssm.common.utils.UUIDFactory;
import com.jee.ssm.model.Account;
import com.jee.ssm.model.Article;
import com.jee.ssm.model.Role;
import com.jee.ssm.model.json.Tip;
import com.jee.ssm.model.param.ParamMap;
import com.jee.ssm.modules.ssm.dao.ArticleCatDao;
import com.jee.ssm.modules.ssm.dao.ArticleDao;
import com.jee.ssm.modules.ssm.services.AccountService;
import com.jee.ssm.modules.ssm.services.ArticleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController extends AdminBaseController<Article> {

    @RequestMapping(value="/list")
    /*@RequiresPermissions("article:list")*/
    public String list(HttpServletRequest request, ModelMap modelMap, Integer page, Integer size) throws Exception {
        ParamMap paramMap = new ParamMap(request);
        PageInfo<Article> pageInfo =  articleService.list(paramMap,page,size);
        modelMap.put("pageInfo",pageInfo);
        modelMap.putAll(paramMap);
        return "manager/article/list";
    }

    @RequestMapping(value="/add")
    /*@RequiresPermissions("article:add")*/
    public ModelAndView add(ModelMap model){
        List artCatList=articleCatDao.list(new HashMap()).getList();
        model.addAttribute("artCatList",artCatList);
        return new ModelAndView("manager/article/add",model);
    }
    @RequestMapping(value="/save")
    @ResponseBody
    public Model save(@RequestParam("file") MultipartFile file, Article article,Model model){
        articleService.save(file,article);
        model.addAttribute("成功！");
        return model;
    }

    @RequestMapping(value="/edit")
    public ModelAndView edit(ModelMap model, @RequestParam String id) {
        try{
            Article article=articleDao.selectById(id);
            model.put("data",article);
            List artCatList=articleCatDao.list(new HashMap()).getList();
            model.put("artCatList",artCatList);
            return new ModelAndView("manager/article/add",model);
        }catch(Exception e){
            return null;
        }
    }
    @RequestMapping(value="/detail")
    public ModelAndView detail(ModelMap model, @RequestParam String id) {
        try{
            Article article=articleDao.selectById(id);
            model.put("data",article);
            List artCatList=articleCatDao.list(new HashMap()).getList();
            model.put("artCatList",artCatList);
            return new ModelAndView("manager/article/detail",model);
        }catch(Exception e){
            return null;
        }
    }

    //----------------------- property ----------------------
    @Resource
    private ArticleCatDao articleCatDao;

    @Resource
    private ArticleDao articleDao;

    @Resource
    private ArticleService articleService;
}
