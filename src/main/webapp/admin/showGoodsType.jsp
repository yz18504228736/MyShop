
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css" />
<script src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
	<%
		Object goodsTypeList = pageContext.findAttribute("goodsTypeList");
		System.out.println(goodsTypeList);
		System.out.println(session.getId());
	%>
<title>商品分类</title>
	<script>
		$(function () {
			$("#search").click(function () {

				var userType=$("#username").val();
				var pubdate=$("#pubdate").val();
				$.post({
					url: "${pageContext.request.contextPath }/showGoodsType?action=searchType",
					data: {
						"userType":userType,
						"pubdate":pubdate
					},
					dataType: "json",
					success: function(data) {
						location.reload();
					}
				});
			});
		});
	</script>
</head>
<body>
<div class="row" style="width:98%;margin-left: 1%;">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				商品类型
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
						<div class="form-group form-inline">
							<span>商品等级</span>
							<input type="text" id="username" name="username" class="form-control">
						</div>
					</div>
					<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
						<div class="form-group form-inline">
							<span>商品名称</span>
							<input type="text" id="pubdate" name="pubdate" class="form-control">
						</div>
					</div>
					<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
						<button type="button" class="btn btn-primary" id="search"><span class="glyphicon glyphicon-search"></span></button>
					</div>
				</div>
				<div style="height: 400px;overflow: scroll;">
				<table id="tb_list" class="table table-striped table-hover table-bordered">
					<tr>
						<td class="showTd">序号</td><td>类型</td><td>等级</td><td>所属类型</td><td>操作</td>
					</tr>
					<c:forEach items="${goodsTypeList}" var="gtype" varStatus="i">
					<tr>
						var s=${gtype.parent};
						<td>${i.count}</td>
						<td>${gtype.name}</td>
						<td>${gtype.level}</td>
						<td>${gtype.parent}</td>
						<td>
							<button>修改</button>&nbsp;&nbsp;
							<button>删除</button>
						</td>
					</tr>
					</c:forEach>
					
				</table>
				</div>
			</div>
			
		</div>
	</div>
</div>
</body>
</html>