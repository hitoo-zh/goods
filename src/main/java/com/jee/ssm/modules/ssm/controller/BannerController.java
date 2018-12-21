package com.jee.ssm.modules.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.jee.ssm.Utils.UtilsUpload;
import com.jee.ssm.model.Article;
import com.jee.ssm.model.Banner;
import com.jee.ssm.model.param.ParamMap;
import com.jee.ssm.modules.ssm.dao.ArticleCatDao;
import com.jee.ssm.modules.ssm.dao.ArticleDao;
import com.jee.ssm.modules.ssm.dao.BannerCatDao;
import com.jee.ssm.modules.ssm.dao.BannerDao;
import com.jee.ssm.modules.ssm.services.ArticleService;
import com.jee.ssm.modules.ssm.services.BannerService;
import org.apache.commons.fileupload.FileUploadException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/banner")
public class BannerController extends AdminBaseController<Article> {

    @RequestMapping(value="/list")
    /*@RequiresPermissions("article:list")*/
    public String list(HttpServletRequest request, ModelMap modelMap, Integer page, Integer size) throws Exception {
        ParamMap paramMap = new ParamMap(request);
        PageInfo<Banner> pageInfo =  bannerService.list(paramMap,page,size);
        modelMap.put("pageInfo",pageInfo);
        modelMap.putAll(paramMap);
        return "manager/banner/list";
    }
    @RequestMapping(value="/add")
    public ModelAndView add(ModelMap model){
        List bannerCatList=bannerCatDao.list(new HashMap()).getList();
        model.addAttribute("bannerCatList",bannerCatList);
        return new ModelAndView("manager/banner/add",model);
    }
    @RequestMapping(value="/save")
    @ResponseBody
    public Model save(MultipartHttpServletRequest request,@RequestParam(value = "file", required = false) MultipartFile[] fileList, Banner banner,Model model){
       List<MultipartFile> files = request.getFiles("file");
        bannerService.save(fileList,banner);
        model.addAttribute("成功！");
        return model;
    }
    @RequestMapping(value="/edit")
    public ModelAndView edit(ModelMap model, @RequestParam String id) {
        try{
            Banner banner=bannerDao.selectById(id);
            model.put("data",banner);
            String src=banner.getImgs();
            String[] srcs=src.split(",");
            if(srcs.length>0){
                for (int i=0;i<srcs.length;i++){
                    model.put("src"+i,srcs[i]);
                }
            }

            List bannerCatList=bannerCatDao.list(new HashMap()).getList();
            model.put("bannerCatList",bannerCatList);
            return new ModelAndView("manager/banner/edit",model);
        }catch(Exception e){
            return null;
        }
    }

    @RequestMapping(value="/selectById")
    public ModelAndView selectById(ModelMap model, @RequestParam String id) {
        try {
            Banner banner = bannerDao.selectById(id);
            if(null!=banner) {
                String src = banner.getImgs();
                String[] srcs = src.split(",");
                List list = new ArrayList();
                if (srcs.length > 0) {
                    for (int i = 0; i < srcs.length; i++) {
                        list.add(srcs[i]);
                    }
                }
                model.put("list", list);
                model.put("banner", banner);
            }
                return new ModelAndView("manager/banner/detail", model);
        }catch (Exception e){
return null;
        }

    }
    //----------------------- property ----------------------
    @Resource
    private BannerCatDao bannerCatDao;
    @Resource
    private BannerDao bannerDao;

    @Resource
    private BannerService bannerService;
}
