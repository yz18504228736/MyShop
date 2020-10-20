<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>添加商品种类</title>

	<link rel="stylesheet" href="../css/bootstrap.min.css"/>

	<script src="../js/jquery.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script src="../js/jquery.serializejson.min.js"></script>

	<script>
<%--	显示1级标题,	--%>
		$(function (){
			$.post({
				url: "../category?action=queryByLevel",
				data: {level: 1},
				dataType: "json",
				success: function(data) {
					for (var category of data) {
						$("#firstCategory").append($("<option value='" + category.id +"'>"+category.name+"</option>"));
					}
				}
			});
			//当一级标题选择完发生改变的时候,判断状态
			$("#firstCategory").change(function() {
				var val = $("#firstCategory").val();
				if (val == 0) {
					$("#secondCategory").hide();
					$("#thirdCategory").hide();
				} else {
					$("#thirdCategory").hide();
				//显示第二级目录
					$.post({
						url: "../category?action=queryByParent",
						data: {parent: val},
						dataType: "json",
						success: function(data) {
							$("#secondCategory").show();
							$("#secondCategory").empty();
							$("#secondCategory").append("<option value='0'>--请选择--</option>");
							for (var category of data) {
								$("#secondCategory").append($("<option value='" + category.id +"'>"+category.name+"</option>"));
							}
						}
					});
				}
			});


			//当二级标题发生改变时,判断状态
			$("#secondCategory").change(function () {
				var val= $("#secondCategory").val();
				if (val == 0){
					$("#thirdCategory").hide();
				}else {
					$.post({
						url: "../category?action=queryByParent",
						data: {parent: val},
						dataType: "json",
						success:function (data) {
							$("#thirdCategory").show();
							$("#thirdCategory").empty();
							$("#thirdCategory").append("<option value='0'>--请选择--</option>");
							for (var category of data) {
								$("#thirdCategory").append($("<option value='" + category.id +"'>"+category.name+"</option>"));
							}
						}
					});
				}
			});
			$("#addCategory").submit(function (){
				// 处理父类别
				var parentId;
				var level;
				if ($("#firstCategory").val() == 0) {

					// 没选1级分类
					parentId = null;
					level = 1;
				} else if ($("#secondCategory").val() == 0) {
					// 选了1级分类，没选2级分类
					parentId = $("#firstCategory").val();
					level = 2;
				} else if ($("#thirdCategory").val() == 0) {
					// 选了2级分类，没选3级分类
					parentId = $("#secondCategory").val();
					level = 3;
				} else {
					// 选了3级分类
					parentId = $("#thirdCategory").val();
					level = 4;
				}

				$.post({
					url: "../category?action=add",
					data: {
						parent: parentId,
						name:$("#categoryName").val(),
						level: level
					},
					success: function() {
						// 只要服务器返回，意味着添加成功
						location.reload();
					}
				});
				return false;
			});
		});
	</script>
</head>
<body>
<div style="width:98%;margin-left: 1%;">
	<div class="panel panel-default">
		<div class="panel-heading">
			添加商品种类
		</div>
		<div class="panel-body">
			<form id="addCategory" action="${pageContext.request.contextPath }/addGoodsType" method="post">
				<div class="row">
					<div class="form-group form-inline">
						<span>所属种类</span>
						<select name="firstCategory" id="firstCategory" categoryLevel="1" class="categorySelect">
							<option value="0">--请选择--</option>
						</select>
						<select name="secondCategory" id="secondCategory" style="display: none;">
							<option value="0">--请选择--</option>
						</select>
						<select name="thirdCategory" id="thirdCategory" style="display: none;">
							<option value="0">--请选择--</option>
						</select>
					</div>
				</div>
				<div class="row">
					<div class="form-group form-inline">
						<span>种类名称</span>
						<input id="categoryName" type="text" name="name" class="form-control">
					</div>
				</div>
				<div class="row">
					<div class="btn-group">
						<button type="reset" class="btn btn-default">清空</button>
						<button type="submit" class="btn btn-default">添加</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>