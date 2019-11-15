<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<!-- BEGIN FORM-->
<div class="portlet light" >
    <div class="portlet-title">
        <div class="caption">
            <span class="caption-subject font-red-sunglo bold uppercase">编辑</span>
            <span class="caption-helper">编辑完成记得提交</span>
        </div>
    </div>
    <div class="portlet-body form">
        <!-- BEGIN FORM-->
        <form action="#" class="form-horizontal" id="edit-module">
            <div class="form-body">

                <div class="form-group frist">
                    <label class="col-md-3 control-label">名称</label>
                    <div class="col-md-4">
                        <div class="input-icon">
                            <input type="hidden" name="id" value="${data.id }">
                            <input type="text" class="form-control" name="name" value="${data.name }" placeholder="名称">
                        </div>
                    </div>
                </div>

                <div class="form-group frist">
                    <label class="col-md-3 control-label">图片</label>
                    <div class="col-md-4">
                        <div class="input-icon">
                            <input type="text" class="form-control" name="img" value="${data.img }" placeholder="图片">
                        </div>
                    </div>
                </div>
                <div class="form-group frist">
                    <label class="col-md-3 control-label">原价</label>
                    <div class="col-md-4">
                        <div class="input-icon">
                            <input type="text" class="form-control" name="jinPrice" value="${data.jinPrice }" placeholder="原价">
                        </div>
                    </div>
                </div>
                <div class="form-group frist">
                    <label class="col-md-3 control-label">优惠价</label>
                    <div class="col-md-4">
                        <div class="input-icon">
                            <input type="text" class="form-control" name="salePrice" value="${data.salePrice }" placeholder="优惠价">
                        </div>
                    </div>
                </div>
                <div class="form-group frist">
                    <label class="col-md-3 control-label">货源</label>
                    <div class="col-md-4">
                        <div class="input-icon">
                            <input type="text" class="form-control" name="huoYuan" value="${data.huoYuan }" placeholder="货源">
                        </div>
                    </div>
                </div>
                <div class="form-group frist">
                    <label class="col-md-3 control-label">所属分类</label>
                    <div class="col-md-4">
                        <div class="input-icon">
                            <select class="form-control" name="classId" value="${data.classId }" placeholder="所属分类">

                                    <%--     <c:if test="${not empty data.id}">
                                             <option value="${data.id}">${data.name}</option>
                                         </c:if>--%>
                                    <option selected value="${data.classId}">${goodsClassName}</option>
                                    <c:forEach var="items" items="${goodsClass}">
                                        <c:if test="${items.id!=data.classId}">
                                        <option value="${items.id}">${items.name}</option>
                                        </c:if>
                                    </c:forEach>

                            </select>
                        </div>
                    </div>
                </div>
                <div class="form-group frist">
                    <label class="col-md-3 control-label">创建人</label>
                    <div class="col-md-4">
                        <div class="input-icon">
                            <input type="text" class="form-control" name="createBy" value="${data.createBy }" placeholder="创建人">
                        </div>
                    </div>
                </div>
                <%--<div class="form-group frist">--%>
                    <%--<label class="col-md-3 control-label">创建时间</label>--%>
                    <%--<div class="col-md-4">--%>
                        <%--<div class="input-icon">--%>
                            <%--<input type="text" class="form-control" name="mark" value="${data.mark }" placeholder="创建时间">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            </div>
            <div class="form-actions">
                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-offset-3 col-md-9">
                                <button type="button" class="btn green" onclick="edit();">修改</button>
                                <button type="button" class="btn default" onclick="layer.close(layer_editModule);">取消</button>
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
<!-- script 开始 -->
<script>

    $(function(){
        //初始化页面
        initPage();
    });

    function edit(){
        var param = tools.formParams("edit-module");
        if(tools.valid("edit-module")){
            tools.post("/fgoods/update",param,function(data){
                if(data.success){
                    layer.msg('修改成功', {icon: 1,time:1000},function(){
                        //刷新列表页面
                        toPage(null);
                        //关闭弹窗
                        layer.close(layer_editModule);
                    });
                }else{
                    tools.errorTip(data.code,data.data);
                }
            });
        }
    }
</script>
<!-- script 结束 -->