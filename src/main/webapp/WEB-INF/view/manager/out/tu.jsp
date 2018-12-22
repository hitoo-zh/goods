<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>jQuery饼状图比例分布数据显示代码</title>

	<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="/static/js/js/jsapi.js"></script>
	<script type="text/javascript" src="/static/js/js/corechart.js"></script>
	<script type="text/javascript" src="/static/js/js/jquery.gvChart-1.0.1.min.js"></script>
	<script type="text/javascript" src="/static/js/js/jquery.ba-resize.min.js"></script>

<%--<script type="text/javascript">
gvChartInit();
$(document).ready(function(){
	$('#myTable5').gvChart({
		chartType: 'PieChart',
		gvSettings: {
			vAxis: {title: 'No of players'},
			hAxis: {title: 'Month'},
			width: 600,
			height: 350
		}
	});
});
</script>--%>

<script type="text/javascript">
gvChartInit();
$(document).ready(function(){
		$('#myTable1').gvChart({
			chartType: 'PieChart',
			gvSettings: {
			vAxis: {title: 'No of players'},
			hAxis: {title: 'Month'},
			width: 600,
			height: 350
		}
	});
});

</script>

</head>


<body>

	<div style="width:600px;margin:0 auto;">

	  <%-- <table id='myTable5'>
			<caption>会员地区分布</caption>
			<thead>
				<tr>
					<th></th>
					<th>河北</th>
					<th>河南</th>
					<th>湖北</th>
					<th>湖南</th>
					<th>山东</th>
					<th>山西</th>

				</tr>
			</thead>
			<tbody>
				<tr>
					<th>1200</th>
					<td>540</td>
					<td>300</td>
					<td>150</td>
					<td>180</td>
					<td>120</td>
					<td>180</td>
				</tr>
			</tbody>
		</table>--%>

		<table id='myTable1'>
			<caption>出库入库分布</caption>
			<thead>
			<tr>
				<th></th>
				<th>出库</th>
				<th>入库</th>
			</tr>
			</thead>
			<tbody>
			<tr>

				<c:choose>
					<c:when test="${(chuku+ruku)>0}">
						<th>${chuku+ruku}</th>
						<td>${chuku}</td>
						<td>${ruku}</td>
					</c:when>
					<c:otherwise>
						因为今天数据为零，所以只是象征展示
						<th>1000</th>
						<td>350</td>
						<td>350</td>
					</c:otherwise>
				</c:choose>

			</tr>
			</tbody>
		</table>

	</div>	


</body>
</html>
