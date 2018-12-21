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
            <span class="caption-subject font-red-sunglo bold uppercase">店铺添加</span>
            <span class="caption-helper">同名店铺可以重复，但是建议只分配一个</span>
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
                    <label class="col-md-3 control-label">店铺名称</label>
                    <div class="col-md-4">
                        <div class="input-icon">
                            <i class="fa  fa-qq"></i>
                            <input type="text" class="form-control" name="name" placeholder="店铺名称">
                        </div>
                    </div>
                </div>
                <div class="form-group frist">
                    <label class="col-md-3 control-label">店铺地址</label>
                    <div class="col-md-4">
                        <div class="input-icon">
                            <i class="fa  fa-qq"></i>
                            <input type="text" class="form-control" name="position" placeholder="店铺地址">
                        </div>
                    </div>
                </div>
                <div class="form-group frist">
                    <label class="col-md-3 control-label">店铺规模</label>
                    <div class="col-md-4">
                        <input type="text" class="form-control" name="numPeople" placeholder="店铺规模">
                    </div>
                </div>
                    <div class="form-group frist">
                        <label class="col-md-3 control-label">货架分类</label>
                        <div class="col-md-4">
                            <select class="form-control" name="type" placeholder="货架分类">
                                <option value ="0">大货架</option>
                                <option value ="1">中货架</option>
                                <option value="2">小货架</option>
                            </select>
                            <%--<input type="text" class="form-control" name="type" placeholder="货架分类">--%>
                        </div>
                    </div>
                    <div class="form-group frist">
                        <label class="col-md-3 control-label">备注</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control" name="mark" placeholder="备注">
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
        if(tools.valid("save-module")){
            tools.post("/store/save",param,function(data){
                if(data.success){
                    layer.msg('保存成功', {icon: 1,time:1000},function(){
                        //刷新列表页面
                        toPage(null);
                        //关闭弹窗
                        layer.close(layer_addModule);
                        window.location.href="/store/list";
                    });
                }else{
                    tools.errorTip(data.code,data.data);
                }
            });
        }
    }
</script>