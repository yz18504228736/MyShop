<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" >
		<link rel="stylesheet" type="text/css" href="css/login.css"   >
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="js/jquery.validate.min.js"></script>
		<script type="text/javascript" src="js/jquery.serializejson.min.js"></script>
		<script type="text/javascript" src="js/md5.js"></script>
	<script type="text/javascript">
	$(function(){
		// validate插件，remote会向远程进行校验
		// 所以远程的资源，有响应
		// 响应字符串为true，代表校验通过
		// 响应字符串为false，代表校验失败
		$("#registerFrom").validate({
			rules:{
				username:{
					required:true,
					minlength:6,
					remote:"user?action=checkUsername"
				},
				password:{
					required:true,
					minlength: 6
				},
				confirm:{
					required:true,
					equalTo:"#password"
				},
				email:{
					required:true,
					email:true,
					remote:"user?action=checkEmail"
				},
				gender:{
					required:true
				}
			},
			messages: {
				username: {
					required:"请输入用户名",
					minlength:"请输入6位以上字符",
					remote:"账户名已存在"
				},
				password: {
					required:"请输入密码",
					minlength:"请输入6位以上字符"
				},
				confirm: {
					required: "必须填写",
					equalTo: "两次密码必须输入一致"
				},
				email: {
					required: "邮箱必须填写",
					email: "邮箱格式必须正确",
					remote:"邮箱已存在"
				},
				gender: {
					required: "必须选择性别"
				}
			},
			submitHandler: function(element){
				// element: 元素
				// 1. 将password明文改密文
				// 2. 将confirm删掉
				// 解决方案1: 读字符串，手动切分参数名与参数值
				// 解决方案2: 将password.value设置成密文的；将confirm.value设置为空
				// $("#password").val("....................................");
				// $("#confirm").val("");
				// 解决方案3: 将用户填写的内容，封装成json对象，修改这个json对象，即可
				var forData= $(element).serializeJSON();
			//	将password字段明文转密文
				forData.password=md5(forData.password);
			//	将confirm字段删除
				delete forData.confirm;
			//	将字段改完之后，发送请求
				$.post({
					url:"user?action=register",
					data:forData,
					success:function (data) {
						//后台处理结束之后，让用户看到"注册成功"的页面
						location.href = "registerSuccess.jsp";
					}
				});
				return false;
			}
		});
	});
</script>
<title>注册</title>
</head>
<body>
	<div class="regist">
		<div class="regist_center">
			<div class="regist_top">
				<div class="left fl"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;会员注册</div>
				<div class="right fr">
					<a href="index.jsp" target="_black">小米商城</a>
				</div>
				<div class="clear"></div>
				<div class="xian center"></div>
			</div>
			<div class="center-block" style="margin-top: 80px;">
				<form id="registerFrom" class="form-horizontal" action="userRegister" method="post">

					<div class="form-group">
						<label class="col-sm-2 control-label">用户名</label>
						<div class="col-sm-8" style="width: 40%">
							<input type="text" id="username" name="username" class="form-control col-sm-10"/>
						</div>
						<div class="col-sm-2">
							<label for="username" class="error text-danger help-block" style="display:none;"> username</label>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">密码</label>
						<div class="col-sm-8" style="width: 40%">
							<input type="password" id="password" name="password" class="form-control col-sm-10" />
						</div>
						<div class="col-sm-2">
							<label for="password" class="error text-danger help-block" style="display:none;"> password</label>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">确认密码</label>
						<div class="col-sm-8" style="width: 40%">
							<input type="password" id="confirm" name="confirm" class="form-control col-sm-10"  />
						</div>
						<div class="col-sm-2">
							<label for="confirm" class="error text-danger help-block" style="display:none;"> confirm</label>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">邮箱</label>
						<div class="col-sm-8" style="width: 40%">
							<input type="text" name="email" id="email" class="form-control col-sm-10"/>
						</div>
						<div class="col-sm-2">
							<label for="email" class="error text-danger help-block" style="display:none;"> email</label>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">性别</label>
						<div class="col-sm-8" id="gender" style="width: 40%">
							<label class="radio-inline"> <input type="radio" name="gender" value="m">男</label>
							<label class="radio-inline"> <input type="radio" name="gender" value="f"> 女</label>
						</div>
						<div class="col-sm-2">
							<label for="gender" class="error text-danger help-block" style="display:none;"> gender</label>
						</div>
					</div>
					<hr>
					<div class="form-group">
						<div class="col-sm-7 col-sm-push-2">
							<input id="registerBtn" type="submit" value="注册" class="btn btn-primary  btn-lg" style="width: 200px;" /> &nbsp; &nbsp;
							<input type="reset" value="重置" class="btn btn-default  btn-lg" style="width: 200px;"  />
						</div>
					</div>
					<div>${session.registerMsg}</div>
				</form>

			</div>
		</div>
	</div>
	
</body>
</html>