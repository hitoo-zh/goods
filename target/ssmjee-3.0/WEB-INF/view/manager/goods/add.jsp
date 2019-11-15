
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script type="text/javascript" src="\static\js\ajaxfileupload.js"></script>
<div class="portlet light">
    <div class="portlet-title">
        <div class="caption">
            <span class="caption-subject font-red-sunglo bold uppercase">商品添加</span>
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
                            <input style="width: 260px;height: 35px;" type="text" id="gName" name="gName" value="${data.gName}" placeholder="输入商品名字">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label" >所属分类</label>
                    <div class="col-md-4">
                        <div class="input-icon">
                            <select name="catId" style="width: 260px;height: 35px;">
                               <c:if test="${not empty data.catId}">
                                    <option value="${data.catId}">${data.catName}</option>
                                </c:if>
                                <c:forEach var="item" items="${goodsCatList}" varStatus="status">
                                <option value="${item.id}">${item.catName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label">图片</label>
                    <div class="col-md-4">
                        <div class="input-icon">
                            <input type="file" name="file" id="myImg" onchange="preview(this)">
                            <div id="preview"></div>
                            <input name="photo" type="hidden" id="img" class="form-control" value="${data.photo}">
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
                    <label class="col-md-3 control-label">单位</label>
                    <div class="col-md-4">
                       <input type="text" name="unit" value="${data.unit}">
                    </div>
                </div>
                <div class="form-group last">
                    <label class="col-md-3 control-label">数量</label>
                    <div class="col-md-4">
                        <input type="text" name="count" value="${data.count}">
                    </div>
                </div>
                <div class="form-group last">
                    <label class="col-md-3 control-label">产地</label>
                    <div class="col-md-4">
                        <input type="text" name="product" value="${data.product}">
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
        if(param.gName==null){
                alert("商品名字必填");
            return;
        }else if(param.catId==null){
            alert("商品分类");
            return;
        } else{
            $.ajaxFileUpload({
                url:'/goods/saveeezzy',
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