<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<!-- BEGIN FORM-->
<div class="portlet light" >
    <div class="portlet-title">
        <div class="caption">
            <span class="caption-subject font-red-sunglo bold uppercase">店铺编辑</span>
            <span class="caption-helper">编辑完成记得提交</span>
        </div>
    </div>
    <div class="portlet-body form">
        <!-- BEGIN FORM-->
        <form action="#" class="form-horizontal" id="edit-module">
            <div class="form-body">

                <div class="form-group frist">
                    <label class="col-md-3 control-label">店铺名称</label>
                    <div class="col-md-4">
                        <div class="input-icon">
                            <i class="fa  fa-qq"></i>
                            <input type="hidden" name="id" value="${data.id }">
                            <input type="text" class="form-control" name="name" value="${data.name }" placeholder="店铺名称">
                        </div>
                    </div>
                </div>

                <div class="form-group frist">
                    <label class="col-md-3 control-label">店铺地址</label>
                    <div class="col-md-4">
                        <div class="input-icon">
                            <i class="fa  fa-qq"></i>
                            <input type="text" class="form-control" name="position" value="${data.position }" placeholder="店铺地址">
                        </div>
                    </div>
                </div>
                <div class="form-group frist">
                    <label class="col-md-3 control-label">店铺规模</label>
                    <div class="col-md-4">
                        <div class="input-icon">
                            <i class="fa  fa-qq"></i>
                            <input type="text" class="form-control" name="numPeople" value="${data.numPeople }" placeholder="店铺规模">
                        </div>
                    </div>
                </div>
                <div class="form-group frist">
                    <label class="col-md-3 control-label">货架分类</label>
                    <div class="col-md-4">
                        <div class="input-icon">
                            <i class="fa  fa-qq"></i>
                            <select class="form-control" name="type" value="${data.type }" placeholder="货架分类">
                                <option value ="0">大货架</option>
                                <option value ="1">中货架</option>
                                <option value="2">小货架</option>
                            </select>
                            <%--<input type="text" class="form-control" name="type" value="${data.type }" placeholder="货架分类">--%>
                        </div>
                    </div>
                </div>
                <div class="form-group frist">
                    <label class="col-md-3 control-label">备注</label>
                    <div class="col-md-4">
                        <div class="input-icon">
                            <i class="fa  fa-qq"></i>
                            <input type="text" class="form-control" name="mark" value="${data.mark }" placeholder="备注">
                        </div>
                    </div>
                </div>
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
            tools.post("/store/update",param,function(data){
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