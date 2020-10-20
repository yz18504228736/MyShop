<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<% String path = request.getContextPath(); %>
<% String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";%>
<%  request.setCharacterEncoding("UTF-8");%>
<html>
<head>
	<base href="<%=basePath%>"/>
	<title>商品添加页面</title>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<link rel="stylesheet" href="css/bootstrap.min.css"/>

	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/DatePicker.js"></script>

	<script>
		$(function () {
			$.post({
				url: "product?action=queryGoods",
				data: " ",
				dataType: "json",
				success: function (data) {
					for (var choise of data) {
						$("#typeid").append($("<option value='" + choise.level + "'>" +choise.level+"--"+ choise.name + "</option>"));
					}
				}
			});
		});

	</script>
</head>
<body>
<div class="row" style="margin-left: 20px;">
	<form action="product?action=add" id="addProduct" method="post" enctype="multipart/form-data">
		<div>
			<h3>新增商品</h3>
		</div>
		<hr/>
		<div class="row">
			<div class="col-sm-6">
				<div class="form-group form-inline">
					<label>名称:</label>
					<input type="text" name="name" class="form-control"/>
				</div>

				<div class="form-group form-inline">
					<label>分类:</label>
					<select name="typeid" id="typeid" class="form-control"  categoryLevel="1" >
							<option value="0">--请选择--</option>
					</select>
				</div>
				<div class="form-group form-inline">
					<label >时间:</label>
					<input type="text" name="pubdate" readonly="readonly" class="form-control" onclick="setday(this)"/>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="form-group form-inline">
					<label name="price">价格:</label>
					<input type="text" name="price" class="form-control"/>
				</div>
				<div class="form-group form-inline">
					<label >评分:</label>
					<input type="text" name="star" class="form-control"/>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-10">
				<div class="form-group form-inline">
					<label>商品图片</label>
					<input type="file" name="picture"/>
				</div>
				<div class="form-group ">
					<label>商品简介</label>
					<textarea name="intro" class="form-control" rows="5"></textarea>
				</div>
				<div class="form-group form-inline">
					<input type="submit" value="添加" id="submit" class="btn btn-primary"/>
					<input type="reset" value="重置" class="btn btn-default"/>
				</div>
			</div>
		</div>
	</form>
</div>
</body>
</html>