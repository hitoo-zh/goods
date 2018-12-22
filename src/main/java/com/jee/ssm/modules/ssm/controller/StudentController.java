package com.jee.ssm.modules.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.jee.ssm.model.Article;
import com.jee.ssm.model.Class;
import com.jee.ssm.model.Grade;
import com.jee.ssm.model.Student;
import com.jee.ssm.model.json.Tip;
import com.jee.ssm.model.param.ParamMap;
import com.jee.ssm.modules.ssm.dao.*;
import com.jee.ssm.modules.ssm.services.ArticleService;
import com.jee.ssm.modules.ssm.services.StudentService;
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
@RequestMapping("/student")
public class StudentController extends AdminBaseController<Student> {

    @RequestMapping(value="/list")
    public String list(HttpServletRequest request, ModelMap modelMap, Integer page, Integer size) throws Exception {
        ParamMap paramMap = new ParamMap(request);
        PageInfo<Student> pageInfo =  studentService.list(paramMap,page,size);
        List<Grade> gradeList=gradeDao.list(new HashMap()).getList();
        List<Class> classList=classDao.list(new HashMap()).getList();

        modelMap.put("pageInfo",pageInfo);
        modelMap.put("gradeList",gradeList);
        modelMap.put("classList",classList);
        modelMap.putAll(paramMap);
        return "manager/student/list";
    }
    @RequestMapping("/selectClass")
    public ModelAndView selectClass(String id,Model model){
        List classes=classDao.selectBy(id);
        model.addAttribute("classes",classes);
        return new ModelAndView("manager/student/option");
    }


    @RequestMapping(value="/add")
    public ModelAndView add(ModelMap model){
        List<Grade> gradeList=gradeDao.list(new HashMap()).getList();
        model.addAttribute("gradeList",gradeList);
        return new ModelAndView("manager/student/add",model);
    }
    @RequestMapping(value="/save")
    @ResponseBody
    public Model save(@RequestParam("file") MultipartFile file, Student student,Model model){
        studentService.save(file,student);
        model.addAttribute("成功！");
        return model;
    }

    @RequestMapping(value="/edit")
    public ModelAndView edit(ModelMap model, @RequestParam String id) {
        try{
            Student student=studentDao.selectById(id);
            model.put("data",student);
            if(null!=student.getClassId()&&!"".equals(student.getClassId())){
                Class cl=classDao.selectById(student.getClassId());
                List classes=classDao.selectBy(cl.getGradeId());
                model.addAttribute("classes",classes);
            }
            List<Grade> gradeList=gradeDao.list(new HashMap()).getList();
            model.addAttribute("gradeList",gradeList);

            return new ModelAndView("manager/student/add",model);
        }catch(Exception e){
            return null;
        }
    }
    @RequestMapping(value="/delete")
    @ResponseBody
    public Tip delete(@RequestParam String id) {
        try {
            studentDao.deleteById(id);
            return new Tip();
        } catch (Exception e) {
            //e.printStackTrace();
            return new Tip(1);
        }
    }

    //----------------------- property ----------------------
    @Resource
    private GradeDao gradeDao;

    @Resource
    private ClassDao classDao;

    @Resource
    private StudentDao studentDao;

    @Resource
    private StudentService studentService;
}
