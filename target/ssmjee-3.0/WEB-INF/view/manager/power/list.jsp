<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/03/20
  Time: 下午 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="ssm" uri="http://ssm.elangzhi.com/jsp/tag/functions" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="zh" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="zh" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="zh">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <meta charset="utf-8"/>
    <title>后台服务中心</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="" name="后台服务中心"/>
    <meta content="" name="GaoXiang"/>
    <jsp:include page="../body/link-page.jsp"/>
</head>
<!-- END HEAD -->
<body class="page-content-white fade-in-up">

<!-- BEGIN CONTAINER -->
<div class="page-container">

    <!-- BEGIN PAGE TOOLS-->
    <div class="portlet light">
        <div class="portlet-title">
            <div class="caption">
                <i class="icon-paper-plane font-green-haze"></i>
                <span class="caption-subject bold font-green-haze uppercase">搜索</span>
                <span class="caption-helper">点击右侧搜索按钮开始检索</span>
            </div>
            <div class="tools">
                <a href="javascript:void(0);" class="collapse" data-original-title="收起" title="收起"></a>
                <a href="javascript:void(0);" class="fullscreen" data-original-title="全屏" title="全屏"></a>
            </div>
        </div>
        <div class="portlet-body">
            <div class="form-inline" role="form" id="table-param">
                <div class="form-group form-md-line-input has-success">
                    <input type="text" class="form-control" name="parentId" value="${parentId}"
                           placeholder="权限parentId">
                    <div class="form-control-focus"></div>
                </div>
                <div class="form-group form-md-line-input has-success">
                    <input type="text" class="form-control" name="name" value="${name}" autofocus placeholder="权限名称">
                    <div class="form-control-focus"></div>
                </div>
                <button class="btn btn-success btn-tools-search"><i class="icon-magnifier"></i> 搜索</button>
                <button class="btn btn-danger btn-tools-reset" data-url-param="&page=1&size=10&parentId=0"><i
                        class="icon-reload"></i> 重置
                </button>
            </div>
        </div>
    </div>

    <!-- END PAGE TOOLS-->

    <!-- BEGIN PAGE TABLE-->
    <div class="portlet light margin-bottom-5">
        <div class="portlet-title">
            <div class="caption">
                <i class="icon-speech  font-blue-hoki"></i>
                <span class="caption-subject font-blue-hoki">数据表</span>
                <span class="caption-helper">
                    当前查询条件下有：<span class="show-page-total">${pageInfo.total}</span> 条数据，
                    总计：<span class="show-page-count">${pageInfo.pages}</span> 页，
                    当前显示第：<span class="show-page-current">${pageInfo.pageNum}</span> 页，
                    首行为第：<span class="show-page-begin">${(pageInfo.pageNum - 1) * pageInfo.pageSize + 1}</span> 条数据。
                </span>
            </div>

            <div class="tools">
                <a href="" class="collapse" data-original-title="" title=""> </a>
                <a href="" class="reload btn-tools-refresh" data-original-title="" title=""> </a>
                <a href="" class="fullscreen" data-original-title="" title=""> </a>
            </div>

        </div>

        <div class="table-toolbar">
            <div class="row">
                <div class="col-md-3">

                    <div class="btn-group">
                        <a href="javascript:history.go(-1);" class="btn blue ">
                            后退 <i class="fa fa-history"></i>
                        </a>
                        <a href="javascript:void(0);" class="btn green btn-module-add">
                            添加 <i class="fa fa-plus"></i>
                        </a>
                    </div>
                </div>
                <div class="col-md-9">
                    <div class="btn-group pull-right">
                        <button class="btn btn-danger btn-module-delete-all"> 批量删除 <i class="fa fa-times"></i></button>
                    </div>
                </div>
            </div>
        </div>
        <div class="portlet-body table-responsive ">

            <div class="table-scrollable">
                <table data-current-page="${pageInfo.pageNum}" data-page-size="${pageInfo.pageSize}"
                       data-total-counts="${pageInfo.total}" data-visible-pages="10" data-page-counts="${pageInfo.pages}"
                       class="table table-bordered table-hover" id="table">
                    <thead>
                    <tr>
                        <th class="table-checkbox">
                            <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
                                <input type="checkbox" class="checkbox-all" title="全选"><span></span>
                            </label>
                        </th>
                        <th> 编号 </th>
                        <th> 路径 </th>
                        <th> 参数 </th>
                        <th> 标识 </th>
                        <th> 名称 </th>
                        <th> 图标 </th>
                        <th> 父级 </th>
                        <th> 下级数量</th>
                        <th style="min-width: 100px;"> 类型</th>
                        <th> 排序 </th>
                        <th> 操作 </th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${pageInfo.list}" varStatus="status">
                        <tr>
                            <td class="center">
                                <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
                                    <input type="checkbox" class="checkbox-child" title="选择此条数据" value="${item.id}"><span></span>
                                </label>
                            </td>
                            <td>${item.id}</td>
                            <td>${item.url}</td>
                            <td>${item.param}</td>
                            <td>${item.shiro}</td>
                            <td class="center">
                                <a style="color: blue;"
                                   href="/power/list?page=1&size=10&parentId=${item.id}">${item.name}</a>
                            </td>
                            <td class="center"><i class="${item.icon}"></i></td>
                            <td class="center"> ${item.parentId}</td>
                            <td class="center"> ${item.powerCount}</td>
                            <td class="center">${item.type==0?"菜单权限":"操作权限" }</td>
                            <td class="center">${item.sort}</td>
                            <td>
                                <div class="btn-group btn-group-xs btn-group-solid">
                                    <button data-id="${item.id}" class="btn btn-success btn-module-edit">查看\编辑</button>
                                    <button data-id="${item.id}" class="btn btn-danger btn-module-delete">删除</button>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <ul class="pagination">
                <li>
                    <select class="form-control" id="pageSize">
                        <optgroup label="每页显示行数"></optgroup>
                        <option ${pageInfo.pageSize==3?"selected":""} value="3">3</option>
                        <option ${pageInfo.pageSize==5?"selected":""} value="5">5</option>
                        <option ${pageInfo.pageSize==10?"selected":""} value="10">10</option>
                        <option ${pageInfo.pageSize==20?"selected":""} value="20">20</option>
                        <option ${pageInfo.pageSize==50?"selected":""} value="50">50</option>
                        <option ${pageInfo.pageSize==0?"selected":""} value="0">全部</option>
                    </select>
                </li>
            </ul>
            <ul class="pagination" id="pagination"></ul>

        </div>

    </div>
    <!-- END PAGE TABLE-->
</div>
<!-- END CONTAINER -->

<!-- BEGIN PAGE JAVASCRIPT-->
<jsp:include page="../body/javascript-page.jsp"/>
<script>


    $(document).ready(function () {

        //初始化页面
        initList({
            table: "table",                                                 //表格ID
            url: "/power/list",                                          //表格分页url
            ajax: true                                                      //为true时伪静态刷新指定ID的table
        });

    });


    /**
     * 添加模块
     */
    function addModule(){
        $.post("/power/add", null, function(html){
            window.layer_addModule = layer.open({
                id:"addModule",
                type: 1,
                title:"添加",
                /* area:['900px','600px'],*/
                area:'900px',
                content: html,
                anim:1,
                shadeClose:false,
                cancel: function(){}
            });
        });
    }


    /**
     * 编辑模块
     * @param id 模块名
     */
    function editModule(id){
        $.post("/power/edit", {"id":id}, function(html){
            window.layer_editModule = layer.open({
                id:"editModule",
                type: 1,
                title:"编辑",
                /* area:['900px','600px'],*/
                area:'900px',
                content: html,
                anim:1,
                shadeClose:false,
                cancel: function(){}
            });
        });
    }

    /**
     * 删除单个
     * @param id 要删除的id
     */
    function deleteById(id){
        layer.confirm("确定删除么？",function() {
            tools.post("/power/delete", {"id":id}, function (data) {
                if(data.success){
                    layer.msg('删除成功！', {icon: 1,time:1000},function(){
                        //跳转到第一页
                        toPage(1);
                    });
                }else{
                    tools.errorTip(data.code,data.data);
                }
            });
        });
    }


    /**
     * 批量删除
     */
    function deleteByIds(){
        var ids = getIds($('#table').find(".checkbox-child:checked"));
        if(ids.length === 0){
            layer.msg("请先选择要删除的条目！", {icon: 2,time:1000});
            return;
        }
        layer.confirm("确定删除选中信息么？",function(){
            tools.post("/power/deleteByIds",{"ids":ids.join(",")},function(data){
                if(data.success){
                    layer.msg('批量删除成功！', {icon: 1,time:1000},function(){
                        //跳转到第一页
                        toPage(1);
                    });
                }else{
                    tools.errorTip(data.code,data.data);
                }
            });
        });
    }




</script>
<!-- END PAGE JAVASCRIPT-->
</body>
</html>














