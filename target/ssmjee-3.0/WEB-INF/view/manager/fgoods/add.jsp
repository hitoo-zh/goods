<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="\static\js\ajaxfileupload.js"></script>
<%--
  Created by IntelliJ IDEA.
  User: 王冲
  Date: 2018/10/13
  Time: 下午 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="portlet light">
    <div class="portlet-title">
        <div class="caption">
            <span class="caption-subject font-red-sunglo bold uppercase">商品添加</span>
            <span class="caption-helper">同名商品可以重复，但是建议只分配一个</span>
        </div>
    </div>
    <div class="portlet-body form">
        <!-- BEGIN FORM-->
        <form action="#" class="form-horizontal" id="save-module">
            <div class="form-body">

                <div class="form-group hide">
                    <label class="col-md-3 control-label">ID</label>
                    <div class="col-md-4">
                        <div class="input-icon">
                            <i class="fa  fa-qq"></i>
                            <input type="text" name="id" value="${longId}">
                            <%--<input type="text" name="id" value="${store.id}">--%>
                        </div>
                    </div>
                </div>

                <div class="form-group frist">
                    <label class="col-md-3 control-label">名称</label>
                    <div class="col-md-4">
                        <div class="input-icon">
                            <input type="text" class="form-control" name="name" placeholder="名称">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label">图片</label>
                    <div class="col-md-4">
                        <div class="input-icon">
                            <input type="file" name="file" id="myImg" onchange="preview(this)">
                            <div id="preview"></div>
                            <input name="img" type="hidden" id="img" class="form-control" value="${data.img}">
                        </div>
                    </div>
                </div>
                <c:if test="${not empty data.img}">
                    <div class="form-group">
                        <label class="control-label col-md-3">原图：
                            <span class="required" aria-required="true"> * </span>
                        </label>
                        <div class="col-md-7">
                            <img src="${data.img}" style="width: 200px;height: 200px">
                        </div>
                    </div>
                </c:if>

                <div class="form-group frist">
                    <label class="col-md-3 control-label">原价</label>
                    <div class="col-md-4">
                        <div class="input-icon">
                            <input type="text" class="form-control" name="jinPrice" placeholder="原价">
                        </div>
                    </div>
                </div>
                <div class="form-group frist">
                    <label class="col-md-3 control-label">优惠价</label>
                    <div class="col-md-4">
                        <input type="text" class="form-control" name="salePrice" placeholder="优惠价">
                    </div>
                </div>
                <div class="form-group frist">
                    <label class="col-md-3 control-label">货源</label>
                    <div class="col-md-4">
                        <input type="text" class="form-control" name="huoYuan" placeholder="货源">
                    </div>
                </div>
                <div class="form-group frist">
                    <label class="col-md-3 control-label">所属分类 </label>
                    <div class="col-md-4">
                        <select name="classId" style="width: 260px;height: 35px;">
                       <%--     <c:if test="${not empty data.id}">
                                <option value="${data.id}">${data.name}</option>
                            </c:if>--%>
                            <c:forEach var="items" items="${goodsClass}">
                                <option  value="${items.id}">${items.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group frist">
                    <label class="col-md-3 control-label">创建人</label>
                    <div class="col-md-4">
                        <input type="text" class="form-control" name="createBy" placeholder="创建人">
                    </div>
                </div>
                <%--<div class="form-group frist">--%>
                <%--<label class="col-md-3 control-label">创建时间</label>--%>
                <%--<div class="col-md-4">--%>
                <%--<input type="text" class="form-control" name="mark" placeholder="备注">--%>
                <%--</div>--%>
                <%--</div>--%>
            </div>
            <div class="form-actions">
                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-offset-3 col-md-9">
                                <button type="button" class="btn green" onclick="save();">保存</button>
                                <button type="button" class="btn default" onclick="layer.close(layer_addModule);">取消
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6"></div>
                </div>
            </div>
        </form>
        <!-- END FORM-->
    </div>
</div>
<script>

    $(function () {
        //初始化页面
        initPage();
    });

    function save(){
        var param = tools.formParams("save-module");
        if(param.name==null){
            alert("商品名字必填");
            return;
        }else{

            $.ajaxFileUpload({
                url:'/fgoods/saveeezzy',
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