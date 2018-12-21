package com.jee.ssm.modules.ssm.services;

import com.github.pagehelper.PageInfo;
import com.jee.ssm.Utils.UUIDUtils;
import com.jee.ssm.Utils.UtilsUpload;
import com.jee.ssm.model.Article;
import com.jee.ssm.model.Student;
import com.jee.ssm.modules.ssm.dao.ArticleDao;
import com.jee.ssm.modules.ssm.dao.StudentDao;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * 账户 Service
 * @author GaoXiang
 * @version 1.0
 */
@Service
public class StudentService extends BaseService<Student>{

    @Resource
    private StudentDao studentDao;

    /**
     * 查询列表 带分页
     * @param map 参数
     * @param page 页码
     * @param size 每页大小
     * @return 列表
     * @throws Exception 数据返回异常
     */
    public PageInfo<Student> list(Map map,int page,int size) throws Exception {
        System.out.println();
        return studentDao.list(map,page,size);
    }

    public void save(MultipartFile file,Student student){
        if (!file.isEmpty()) {
            String imgPath = UtilsUpload.uploadImgByMultipartFile(file, false);
            student.setPhoto(imgPath);
        }
        if (StringUtils.isEmpty(student.getId())) {
            student.setId(UUIDUtils.getUUID());
            try{
                int a= studentDao.insert(student);
            }catch (Exception e){

            }
        }else{
            try{
                int a=studentDao.updateById(student);
            }catch (Exception e){

            }
        }
    }

}
