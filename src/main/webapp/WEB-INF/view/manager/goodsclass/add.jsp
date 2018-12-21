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
                            <input type="text" name="id" value="${goodsClass.id}">
                        </div>
                    </div>
                </div>

                    <div class="form-group frist">
                    <label class="col-md-3 control-label">分类名称</label>
                    <div class="col-md-4">
                        <div class="input-icon">
                            <i class="fa  fa-qq"></i>
                            <input type="text" class="form-control" name="name" placeholder="商品名称">
                        </div>
                    </div>
                </div>
                <%--<div class="form-group frist">
                    <label class="col-md-3 control-label">所属分类</label>
                    <div class="col-md-4">
                        <div class="input-icon">
                            <i class="fa  fa-qq"></i>
                            <select class="form-control" name="pid" placeholder="所属分类">
                                <option value ="${goodsClass.id}">${goodsClass.name}</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="form-group frist">
                    <label class="col-md-3 control-label">创建人</label>
                    <div class="col-md-4">
                        <input type="text" class="form-control" name="createBy" placeholder="创建者">
                    </div>
                </div>
                    &lt;%&ndash;<div class="form-group frist">&ndash;%&gt;
                        &lt;%&ndash;<label class="col-md-3 control-label">创建时间</label>&ndash;%&gt;
                        &lt;%&ndash;<div class="col-md-4">&ndash;%&gt;
                            &lt;%&ndash;<input type="text" class="form-control" name="createTime" placeholder="创建时间">&ndash;%&gt;
                        &lt;%&ndash;</div>&ndash;%&gt;
                    &lt;%&ndash;</div>&ndash;%&gt;
            </div>--%>
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
        if(tools.valid("save-module")){
            tools.post("/goodsclass/save",param,function(data){
                if(data.success){
                    layer.msg('保存成功', {icon: 1,time:1000},function(){
                        //刷新列表页面
                        toPage(null);
                        //关闭弹窗
                        layer.close(layer_addModule);
                        window.location.href="/goodsclass/list";
                            // ?page=0&size=10
                    });
                }else{
                    tools.errorTip(data.code,data.data);
                }
            });
        }
    }
</script>