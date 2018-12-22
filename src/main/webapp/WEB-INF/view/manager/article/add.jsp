
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script type="text/javascript" src="\static\js\ajaxfileupload.js"></script>
<script type="text/javascript" charset="utf-8">
    window.UEDITOR_HOME_URL = "/ueditor/";
</script>
<!-- 配置文件 -->
<script type="text/javascript" src="/ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="/ueditor/ueditor.all.js"></script>
<!-- 语言包文件(建议手动加载语言包，避免在ie下，因为加载语言失败导致编辑器加载失败) -->
<script type="text/javascript" src="/ueditor/lang/zh-cn/zh-cn.js"></script>

<div class="portlet light">
    <div class="portlet-title">
        <div class="caption">
            <span class="caption-subject font-red-sunglo bold uppercase">文章添加</span>
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
                        <div class="input-icon">
                            <input type="hidden" name="id" value="${data.id}">
                            <input style="width: 260px;height: 35px;" type="text" id="artName" name="artName" value="${data.artName}" placeholder="输入文章标题">
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
                                <option>请选择分类</option>
                                <c:forEach var="item" items="${artCatList}" varStatus="status">
                                <option value="${item.id}">${item.name}</option>
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
                <div class="form-group last">
                    <label class="col-md-3 control-label">内容</label>
                    <div class="col-md-4">
                        <script id="container" style="width:600px" name="content" type="text/plain">${data.content}</script>

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
<script type="text/javascript">
    var editor = UE.getEditor('container',{initialFrameWeight:600})
</script>
<script>

    $(function(){
        //初始化页面
        initPage();
    });

    function save(){
        var param = tools.formParams("save-module");
        if(param.artName==null){
                alert("文章标题必填");
            return;
        }else if(param.content==null){
            alert("文章内容必填");
            return;
        } else{
            $.ajaxFileUpload({
                url:"/article/save",
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