
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script type="text/javascript" src="\static\js\ajaxfileupload.js"></script>


<div class="portlet light">
    <div class="portlet-title">
        <div class="caption">
            <span class="caption-subject font-red-sunglo bold uppercase">文章</span>
            <span class="caption-helper"></span>
        </div>
    </div>
    <div class="portlet-body form">
        <!-- BEGIN FORM-->
        <form action="#" class="form-horizontal" id="save-module">
            <div class="form-body">
                <div class="form-group first">
                    <label class="col-md-3 control-label">标题</label>
                    <div class="col-md-4">

                        ${data.artName}

                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label" >所属分类</label>
                    <div class="col-md-4">


                            ${data.catId}


                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label">图片</label>
                    <div class="col-md-4">

                        <img src="${data.img}" style="width: 200px;height: 200px">


                    </div>
                </div>

                <div class="form-group last">
                    <label class="col-md-3 control-label">内容</label>
                    <div class="col-md-4">
                        ${data.content}

                    </div>
                </div>


            </div>
            <div class="form-actions">
                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-offset-3 col-md-9">
                                <button type="button" class="btn default" onclick="layer.close(layer_addModule);">返回</button>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6"> </div>
                </div>
            </div>
        </form>
        <!-- END FORM-->
    </div>
</div>

<script>

    $(function(){
        //初始化页面
        initPage();
    });


</script>