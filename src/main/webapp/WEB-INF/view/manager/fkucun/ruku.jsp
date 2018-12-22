<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- BEGIN FORM-->
<div class="portlet light">
    <div class="portlet-title">
        <div class="caption">
            <span class="caption-subject font-red-sunglo bold uppercase">入库</span>
            <span class="caption-helper">入库完成记得提交</span>
        </div>
    </div>
    <div class="portlet-body form">
        <!-- BEGIN FORM-->
        <form action="#" class="form-horizontal" id="edit-module">
            <div class="form-body">
                <div class="form-group frist">
                    <label class="col-md-3 control-label">剩余数量</label>
                    <div class="col-md-4">
                        <div class="input-icon">
                            <i class="fa  fa-qq"></i>
                            <input type="hidden" name="id" value="${data.id }">
                            <input type="text" class="form-control" name="name" readonly="readonly" value="${data.goodsNum}"
                                   placeholder="商品剩余数量">
                        </div>
                    </div>

                </div>
                <div class="form-group frist">
                    <label class="col-md-3 control-label">入库数量</label>
                    <div class="col-md-4">
                        <div class="input-icon">
                            <i class="fa  fa-qq"></i>
                            <input type="text" class="form-control" name="goodsNum" value="" placeholder="入库数量">
                        </div>
                    </div>
                </div>
                <div class="form-group frist">
                    <label class="col-md-3 control-label">备注</label>
                    <div class="col-md-4">
                        <div class="input-icon">
                            <i class="fa  fa-qq"></i>
                            <input type="text" class="form-control" name="mark" value="" placeholder="备注">
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-actions">
                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-offset-3 col-md-9">
                                <button type="button" class="btn green" onclick="edit('${data.storeId }');">入库</button>
                                <button type="button" class="btn default" onclick="layer.close(layer_editModule);">取消
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
<!-- script 开始 -->
<script>

    $(function () {
        //初始化页面
        initPage();
    });

    function edit(id) {
        var param = tools.formParams("edit-module");
        if (tools.valid("edit-module")) {
            tools.post("/fkucun/updateNum", param, function (data) {
                if (data.success) {
                    layer.msg('入库成功', {icon: 1, time: 1000}, function () {
                        //刷新列表页面
                        /*toPage(null);*/
                        location.href = "/fkucun/selectGoodsByStoreId?storeId=" + id;

                        //关闭弹窗
                        layer.close(layer_editModule);
                    });
                } else {
                    tools.errorTip(data.code, data.data);
                }
            });
        }
    }
</script>
<!-- script 结束 -->