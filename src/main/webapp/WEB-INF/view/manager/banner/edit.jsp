<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<script type="text/javascript" src="\static\js\ajaxfileupload.js"></script>

<!-- BEGIN FORM-->
<div class="portlet light">
	<div class="portlet-title">
		<div class="caption">
			<span class="caption-subject font-red-sunglo bold uppercase">添加</span>

		</div>
	</div>
	<div class="portlet-body form">
		<!-- BEGIN FORM-->
	<form action="/banner/save" method="post" enctype="multipart/form-data" class="form-horizontal"  id="save-module">
		<div class="form-body">

			<div class="form-group frist">
				<label class="col-md-3 control-label">标题</label>
				<div class="col-md-4">
					<div class="input-icon">
						<input type="hidden" name="id" value="">
						<input style="width: 260px;height: 35px;" type="text" id="" name="title" value="" placeholder="输入banner标题">
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-3 control-label" >所属分类</label>
				<div class="col-md-4">
					<div class="input-icon">
						<select name="catId" style="width: 260px;height: 35px;">
							<%--<c:if test="not empty data.catName">
								<option value="${data.catId}">${data.catName}</option>
							</c:if>--%>
							<c:forEach var="item" items="${bannerCatList}" varStatus="status">
								<option value="${item.id}">${item.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-3 control-label" >图片</label>

				<div class="col-md-4">
					<%--<a href="javaScript:;" id="a0" onclick="addTu()"><div >添加图片</div></a>--%>

						<c:if test="${not empty src0}"><div id="img1" ><input type="file" name="file" id="myImg1" onchange="preview(this)"><div id="AmyImg1"><img src="${src0}"></div><a id="a1" href="javaScript:;" onclick="addTu2()">添加图片</a></div></c:if>
						<c:if test="${not empty src1}"><div id="img2" ><input type="file" name="file" id="myImg2" onchange="preview(this)"><div id="AmyImg2" ><img src="${src1}"></div><a id="a2" href="javaScript:;" onclick="addTu3()">添加图片</a></div></c:if>
						<c:if test="${not empty src2}"><div id="img3" ><input type="file" name="file" id="myImg3" onchange="preview(this)"><div><div id="AmyImg3"><img src="${src2}"></div><a id="a3" href="javaScript:;" onclick="addTu4()">添加图片</a></div></div></c:if>
						<c:if test="${not empty src3}"><div id="img4" ><input type="file" name="file" id="myImg4" onchange="preview(this)"><div><div id="AmyImg4"><img src="${src3}"></div><a id="a4" href="javaScript:;" onclick="addTu5()">添加图片</a></div></div></c:if>
						<c:if test="${not empty src4}"><div id="img5" ><input type="file" name="file" id="myImg5" onchange="preview(this)"></div><div id="AmyImg5"><img src="${src4}"> </div></c:if>


							<div id="img1" style="display: none"><input type="file" name="file" id="myImg1" onchange="preview(this)"><div id="AmyImg1"><c:if test="not empty src0"><img src="${src0}"></c:if> </div><a id="a1" href="javaScript:;" onclick="addTu2()">添加图片</a></div>
							<div id="img2" style="display: none"><input type="file" name="file" id="myImg2" onchange="preview(this)"><div id="AmyImg2" ><c:if test="not empty src1"><img src="${src1}"></c:if> </div><a id="a2" href="javaScript:;" onclick="addTu3()">添加图片</a></div>
							<div id="img3" style="display: none"><input type="file" name="file" id="myImg3" onchange="preview(this)"><div><div id="AmyImg3"><c:if test="not empty src2"><img src="${src2}"></c:if> </div><a id="a3" href="javaScript:;" onclick="addTu4()">添加图片</a></div></div>
							<div id="img4" style="display: none"><input type="file" name="file" id="myImg4" onchange="preview(this)"><div><div id="AmyImg4"><c:if test="not empty src3"><img src="${src3}"></c:if> </div><a id="a4" href="javaScript:;" onclick="addTu5()">添加图片</a></div></div>
							<div id="img5" style="display: none"><input type="file" name="file" id="myImg5" onchange="preview(this)"></div><div id="AmyImg5"><c:if test="not empty src4"><img src="${src4}"></c:if> </div>


				</div>
			</div>
			<script>
				function addTu() {
						document.getElementById("img1").style.display="inline";
						document.getElementById("a0").style.display="none";
				}
				function addTu2() {
						document.getElementById("img2").style.display="inline";
						document.getElementById("a1").style.display="none";
				}
				function addTu3() {
					document.getElementById("img3").style.display="inline";
					document.getElementById("a2").style.display="none";
				}
				function addTu4() {
					document.getElementById("img4").style.display="inline";
					document.getElementById("a3").style.display="none";
				}
				function addTu5() {
					document.getElementById("img5").style.display="inline";
					document.getElementById("a4").style.display="none";
				}
			</script>
			<div class="form-group last">
				<label class="col-md-3 control-label">备注说明</label>
				<div class="col-md-4">
					<textarea name="mark" class="form-control" style="width: 100%;height: 100px;" placeholder="备注"></textarea>
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
<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="/taobaoUpload/imgFileupload.js"></script>

<script>
	function save(){
		var param = tools.formParams("save-module");
		if(param.title==null||param.title==""){
			alert("标题必填");
			return;
		}else if(param.catId==null||param.catId==""){
			alert("分类必选");
			return;
		} else{
			$.ajaxFileUpload({
				url:"/banner/save",
				type:"post",
				secureuri:false,                       //是否启用安全提交,默认为false
				fileElementId:["myImg1","myImg2","myImg3","myImg4","myImg5"],
				data:param,
				dataType:"JSON",
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

	}

    $(function(){
        //初始化页面
        initPage();
    });

	function preview(file)
	{
		var prevDiv = document.getElementById("A"+file.id);
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