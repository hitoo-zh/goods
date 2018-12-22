<%--sadsad--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script type="text/javascript" src="\static\js\ajaxfileupload.js"></script>
<div class="portlet light">
    <div class="portlet-title">
        <div class="caption">
            <span class="caption-subject font-red-sunglo bold uppercase">商品出库/入库</span>
            <span class="caption-helper"></span>
        </div>
    </div>
    <div class="portlet-body form">
        <!-- BEGIN FORM-->
            <div class="portlet-body table-responsive">
                <table class="table table-bordered table-hover" id="table">
            <thead>
            <tr>
                <th class="table-checkbox">
                    <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
                        <input type="checkbox" class="checkbox-all" title="全选"><span></span>
                    </label>
                </th>
                <th>编号</th>
                <th>名字</th>
                <th>图片</th>
                <th>单位</th>
                <th>产地</th>
                <%--<th>类别</th>
                <th>创建时间</th>--%>
               <%-- <th>创建人</th>--%>
                <th>出库量/入库量</th>
                <th>备注</th>
               <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${goodsList}" varStatus="status">
                <tr>
                    <td class="center">
                        <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
                            <%--<input type="checkbox" class="checkbox-child" title="选择此条数据" value="${item.id}">--%><span></span>
                        </label>
                    </td>
                   <%-- <form action="#" class="form-horizontal" id="zzyy">--%>
                        <input type="hidden" id="id"+${item.id}  name="id" value="${item.id}">
                        <input type="hidden" id="a" name="a" value="${a}">
                    <td>${item.id}</td>
                    <td>${item.gName}</td>
                    <td><img src="${item.photo}" style="width: 60px;height: 60px"></td>
                    <td>${item.unit}</td>
                    <td>${item.product}</td>
                   <%-- <td>${item.catName}</td>--%>
                   <%-- <td>${item.createTime}</td>
                    <td>${item.createBy}</td>--%>
                    <td><input type="number" name="count" id="count${item.id}"></td>
                    <td><textarea name="mark" id="mark${item.id}"></textarea></td>

                   <td>
                        <div class="btn-group btn-group-xs btn-group-solid ">
                            <a href="javaScript:;" class="btn btn-success" onclick="zzyy(${item.id})">
                                <c:if test="${a==1}">
                                    出库
                                </c:if>
                                <c:if test="${a==2}">
                                    入库
                                </c:if>
                            </a>
                        </div>
                    </td>

                </tr>
            </c:forEach>
            </tbody>
            </table>
            </div>

            <div class="form-actions">
                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-offset-3 col-md-9">
                             <%--   <button type="button" class="btn green" onclick="save();">保存</button>--%>
                                <button type="button" class="btn default" onclick="layer.close(layer_addModule);">完成</button>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6"> </div>
                </div>
            </div>
        <!-- END FORM-->
</div>
</div>
<script>

    $(function(){
        //初始化页面
        initPage();
    });
        function zzyy(id) {
            var a=$('#a').val();
            var count=$('#count'+id).val();
            var mark=$('#mark'+id).val();

            if(null==count||""==count){
                alert("入库数量必填");
                return;
            }else{
                $.post("/out/save",{'id':id,'a':a,'count':count,'mark':mark},function(data){
                    if(data.success){
                        layer.msg('入库成功', {icon: 1,time:1000},null);
                    }else{
                        tools.errorTip(data.code,data.data);
                    }
                });
            }



        }
   /* function save(){
        var param = tools.formParams("save-module");
        if(param.gName==null){
                alert("商品名字必填");
            return;
        }else if(param.catId==null){
            alert("商品分类");
            return;
        } else{
            $.ajaxFileUpload({
                url:'/goods/saveee',
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
    };*/


</script>