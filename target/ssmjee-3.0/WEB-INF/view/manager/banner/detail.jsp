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
<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
<script src="/static/jqueryScrollBanner/js/jquery.scrollBanner.js" type="text/javascript"></script>
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
    <meta content="后台服务中心" name="description"/>
    <meta content="GaoXiang" name="author"/>
    <jsp:include page="../body/link-page.jsp"/>
</head>
<!-- END HEAD -->
<body class="page-content-white  fade-in-up">

<!-- BEGIN CONTAINER -->
<div class="page-container">


    <!-- BEGIN PAGE TOOLS-->
    <div class="portlet light margin-bottom-1">
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

    </div>
    <!-- END PAGE TOOLS-->

    <!-- BEGIN PAGE TABLE-->
    <div class="portlet light">
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

                    <%--<div class="btn-group">
                        <a href="javascript:history.go(-1);" class="btn blue ">
                            后退 <i class="fa fa-history"></i>
                        </a>
                        <a href="javascript:void(0);" class="btn green btn-module-add">
                            添加 <i class="fa fa-plus"></i>
                        </a>
                    </div>--%>
                </div>
                <%--<div class="col-md-9">
                    <div class="btn-group pull-right">
                        <button class="btn btn-danger btn-module-delete-all"> 批量删除 <i class="fa fa-times"></i></button>
                    </div>
                </div>--%>
            </div>
        </div>

        <div class="portlet-body table-responsive">

            <div id="banner"></div>
            <script type="text/javascript">
                $("#banner").scrollBanner({
                    images : [
                        <c:forEach var="item" items="${list}" varStatus="status">
                        {src:"${item}",title:"${banner.title}",href:"#"},
                        </c:forEach>
                        /*{src:"/static/jqueryScrollBanner/img/banner2.png",title:"banner2",href:"#"},
                        {src:"/static/jqueryScrollBanner/img/banner3.png",title:"banner3",href:"#"},
                        {src:"/static/jqueryScrollBanner/img/banner4.png",title:"banner4",href:"#"}*/
                    ],
                    scrollTime:3000,
                    bannerHeight:"500px",
                    iconColor: "#FFFFFF",
                    iconHoverColor : "#82C900",
                    iconPosition : "center"
                });
            </script>
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
            url: "/banner/list",                                          //表格分页url
            ajax: true                                                      //为true时伪静态刷新指定ID的table
        });

    });



    /**
     * 添加模块
     */
    function addModule(){
        tools.loadPage("/banner/add", null, function(html){
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
        $.post("/banner/edit", {"id":id}, function(html){
            window.layer_addModule = layer.open({
                id:"addModule",
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


</script>
<!-- END PAGE JAVASCRIPT-->
<!-- END PAGE JAVASCRIPT-->
</body>
</html>


