
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script type="text/javascript" src="\static\js\ajaxfileupload.js"></script>

<div class="portlet light">
    <div class="portlet-title">
        <div class="caption">
            <span class="caption-subject font-red-sunglo bold uppercase">学生添加</span>
            <span class="caption-helper"></span>
        </div>
    </div>
    <div class="portlet-body form">
        <!-- BEGIN FORM-->
        <form action="#" class="form-horizontal" id="save-module">
            <div class="form-body">
                <div class="form-group first">
                    <label class="col-md-3 control-label">名字</label>
                    <div class="col-md-4">
                        <div class="input-icon">
                            <input type="hidden" name="id" value="${data.id}">
                            <input style="width: 260px;height: 35px;" type="text" id="name" name="name" value="${data.name}" placeholder="输入你的名字">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label" >请选择年级</label>
                    <div class="col-md-4">
                        <div class="input-icon">
                            <select name="gradeId" onchange="ajaxClass(this)" style="width: 260px;height: 35px;">
                              <c:if test="${not empty data.classId}">
                                    <option id="move">${data.gradeName}</option>
                                </c:if>
                                <option>选择年级</option>
                                <c:forEach var="item" items="${gradeList}" varStatus="status">
                                <option value="${item.id}">${item.gradeName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <script>
                    function ajaxClass(grade) {
                        $("#move").remove();
                        var id=grade.value;
                        $.post("/student/selectClass", {"id":id}, function(html){
                            $("#clas").show();
                            $("#classsss").hide();
                            $("#cla").html(html);
                        });
                    }
                </script>
                <div class="form-group" id="clas" style="display: none">
                    <label class="col-md-3 control-label" >选择班级</label>
                    <div class="col-md-4">
                        <div class="input-icon" id="cla">

                        </div>
                    </div>
                </div>
                <c:if test="${not empty data.classId}">
                    <div class="form-group" id="classsss">
                        <label class="col-md-3 control-label" >选择班级</label>
                        <div class="col-md-4">
                            <div class="input-icon" >
                                <select name="classId" style="width:260px;height:40px">
                                    <c:forEach var="item" items="${classes}">
                                        <option value="${item.id}">${item.className}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>

                </c:if>
                <div class="form-group">
                    <label class="col-md-3 control-label">头像</label>
                    <div class="col-md-4">
                        <div class="input-icon">
                            <input type="file" name="file" id="myImg" onchange="preview(this)">
                            <div id="preview"></div>
                            <input name="img" type="hidden" id="img" class="form-control" value="<${data.photo}>">
                        </div>
                    </div>
                </div>
                    <c:if test="${not empty data.photo}">
                    <div class="form-group">
                        <label class="control-label col-md-3">原图：
                            <span class="required" aria-required="true"> * </span>
                        </label>
                        <div class="col-md-7">
                            <img src="${data.photo}" style="width: 200px;height: 200px">
                        </div>
                    </div>
                  </c:if>
                <div class="form-group last">
                    <label class="col-md-3 control-label">性别</label>
                    <div class="col-md-4">
                        <c:if test="${data.sex==1}">
                       <input type="radio" name="sex" value="1" checked>男
                       <input type="radio" name="sex" value="2">女
                        </c:if>
                        <c:if test="${data.sex==2}">
                            <input type="radio" name="sex" value="1">男
                            <input type="radio" name="sex" value="2" checked>女
                        </c:if>
                        <c:if test="${data.sex!=2 && data.sex!=1}">
                            <input type="radio" name="sex" value="1" checked>男
                            <input type="radio" name="sex" value="2">女
                        </c:if>
                    </div>
                </div>
                <div class="form-group first">
                    <label class="col-md-3 control-label">telephone</label>
                    <div class="col-md-4">
                        <div class="input-icon">
                            <input style="width: 260px;height: 35px;" type="text"  name="telephone" value="${data.telephone}" placeholder="输入你的电话">
                        </div>
                    </div>
                </div>
                <div class="form-group first">
                    <label class="col-md-3 control-label">地址</label>
                    <div class="col-md-4">
                        <div class="input-icon">
                            <input style="width: 260px;height: 35px;" type="text"  name="address" value="${data.address}" placeholder="输入你的地址">
                        </div>
                    </div>
                </div>
                <div class="form-group last">
                    <label class="col-md-3 control-label">备注说明</label>
                    <div class="col-md-4">
                        <textarea name="mark" class="form-control" style="width: 100%;height: 100px;" placeholder="备注">${data.mark}</textarea>
                    </div>
                </div>
            </div>
            <div class="form-actions">
                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-offset-3 col-md-9">
                                <button type="button" class="btn green" onclick="save();">保存</button>
                                <button type="button" class="btn default" onclick="layer.close(layer_addModule);">取消</button>
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

    function save(){
        var param = tools.formParams("save-module");
        if(null==param.name||""==param.name){
                alert("名字必填");
            return;
        }else if(null==param.gradeId||""==param.gradeId){
            alert("年级必选");
            return;
        }else if(null==param.classId||""==param.classId){
            alert("班级必选");
            return;
        } else{
            $.ajaxFileUpload({
                url:"/student/save",
                type:'post',
                secureuri:false,                       //是否启用安全提交,默认为false
                fileElementId:'myImg',
                data:param,
                dataType:'JSON',
                success:function(data){        //服务器响应成功时的处理函数
                    layer.msg('添加成功', {icon: 1,time:1000},function(){
                        //刷新列表页面
                        toPage(null);
                        //关闭弹窗
                        layer.close(layer_addModule);
                    });
                },
                error:function(data, status, e){ //服务器响应失败时的处理函数

                }

            })
        }
    };

    function preview(file)
    {
        var prevDiv = document.getElementById('preview');
        if (file.files && file.files[0])
        {
            var reader = new FileReader();
            reader.onload = function(evt){
                prevDiv.innerHTML = '<img style="width:200px;height:200px;" src="' + evt.target.result + '" />';
            }
            reader.readAsDataURL(file.files[0]);
        }
        else
        {
            prevDiv.innerHTML = '<div class="img" style="width:200px;height:200px;filter:progid:DXImageTransform.Microsoft.' +
                    'AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
        }
    }
</script>