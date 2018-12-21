<%--
  Created by IntelliJ IDEA.
  User: 王冲
  Date: 2018/10/26
  Time: 下午 09:51
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="\static\js\ajaxfileupload.js"></script>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


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

                <div class="form-group">
                    <label class="col-md-3 control-label">请选择分类</label>
                    <div class="col-md-4">
                        <div class="input-icon">
                            <input name="storeId" value="${storeId}" style="display: none"/>
                            <select name="classId" style="width:260px;height:40px" onchange="ajaxClass(this)">
                                <option value="">--选择分类--</option>
                                <c:forEach var="item" items="${goodsClasses}">
                                    <option value="${item.id}">${item.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="form-group" id="clas" style="display: none">
                    <label class="col-md-3 control-label">选择商品</label>
                    <div class="col-md-4">
                        <div class="input-icon" id="cla">

                        </div>
                    </div>
                </div>

            </div>
            <div class="form-actions">
                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-offset-3 col-md-9">
                                <button type="button" class="btn green" onclick="save(${storeId});">保存</button>
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

    function ajaxClass(goodsClass) {
        // $("#move").remove();
        var id = goodsClass.value;
        $.post("/fkucun/selectGoods", {"id": id}, function (html) {
            $("#clas").show();
            $("#classsss").hide();
            $("#cla").html(html);
        });
    }


    function save() {
        var param = tools.formParams("save-module");
        if (null == param.classId || "" == param.classId) {
            alert("请选择分类");
            return;
        } else if (null == param.goodsId || "" == param.goodsId) {
            alert("请选择商品");
            return;
        } else {
            var param = tools.formParams("save-module");
            if (tools.valid("save-module")) {
                tools.post("/fkucun/save", param, function (data) {
                    if (data.success) {
                        layer.msg('保存成功', {icon: 1, time: 1000}, function () {
                            //刷新列表页面
                            toPage(null);
                            //关闭弹窗
                            layer.close(layer_addModule);
                            window.location.href = "/store/list";
                        });
                    } else {
                        tools.errorTip(data.code, data.data);
                    }
                });
            }
        }
    }




</script>