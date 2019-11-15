package com.jee.ssm.modules.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.jee.ssm.model.Article;
import com.jee.ssm.model.Banner;
import com.jee.ssm.model.param.ParamMap;
import com.jee.ssm.modules.ssm.dao.BannerCatDao;
import com.jee.ssm.modules.ssm.dao.BannerDao;
import com.jee.ssm.modules.ssm.services.BannerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/layui")
public class LayuiController extends AdminBaseController<Article> {

    @RequestMapping(value="/form")
    /*@RequiresPermissions("article:list")*/
    public String list(){
        return "manager/layui/form";
    }
}
