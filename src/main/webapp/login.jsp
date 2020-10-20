<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

	<link rel="stylesheet" type="text/css" href="css/login.css">

	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.serializejson.min.js"></script>
	<script type="text/javascript" src="js/jquery.validate.min.js"></script>
	<script type="text/javascript" src="js/md5.js"></script>
	<script type="text/javascript" src="js/jquery.cookie.js"></script>
	<script type="text/javascript" src="js/jquery.base64.js"></script>

	<title>登录</title>
	<script type="text/javascript">

		$(function () {
			$("#userLogin").validate({
				rules: {
					username: {
						required: true,
						minlength: 6
					},
					password: {
						required: true,
						minlength: 6
					},
					vcode: {
						required: true,
						minlength: 4
					}
				},
				messages: {
					username: {
						required: "必须填写",
						minlength: "至少6位"
					},
					password: {
						required: "必须填写",
						minlength: "至少6位"
					},
					vcode: {
						required: "必须填写",
						minlength: "验证码为4位"
					}
				},
				submitHandler: function (element, event) {
					// 1. 将用户的密码，转换成md5加密的密文，提交到后台
					var formData = $("#userLogin").serializeJSON();
					formData.password = md5(formData.password);

					$.post({
						url: "user?action=login",
						data: formData,
						dataType: "json",
						success: function (data) {
							// 意味着: readyState==4 && status==200

							if (data.success) {
								location.href = "index.jsp";
							} else {
								$("#checkMsg").text(data.msg);
							}

							$("#captcha").click();
						},
						error: function () {
							console.log();
						}
					});
				}
			});
			//验证码点击事件，点击刷新
			$("#captcha").click(function () {
				$("#captcha").attr("src", "captcha?id=" + Math.random());
			});

			$("#captcha").attr("src", "captcha");
		});
//	两周内自动登录
// 		$("#btn").function(){
// 			if (autoLogin.checked){
// 				alert("请注意！您已勾选自动登录。为了保护您的账号安全，请不要在公共电脑上这样做!!");
// 			}
// 		}
	</script>
</head>
<body >
<!-- login -->
<div class="top center">
	<div class="logo center">
		<a href="${pageContext.request.contextPath }/index.jsp" target="_blank"><img src="./image/mistore_logo.png"
																					 alt=""></a>
	</div>
</div>
<form method="post" action="userLogin" class="form center" id="userLogin" name="userLogin" >
	<div class="login">
		<div class="login_center">
			<div class="login_top">
				<div class="left fl">
					<a href="${pageContext.request.contextPath }/admin/admin.jsp" target="_self" style="color: greenyellow">管理员</a>
				</div>
				<div class="right fr">您还不是我们的会员？
					<a href="${pageContext.request.contextPath }/register.jsp" target="_self">立即注册</a></div>
				<div class="clear"></div>
				<div class="xian center"></div>
			</div>
			<div class="login_main center">
				<div class="username">
					<div class="left fl">用户名:&nbsp;</div>
					<div class="right fl">
						<input class="shurukuang" type="text" name="username" id="username" placeholder="请输入你的用户名"/>
						<label for="username"></label>
					</div>
				</div>
				<div class="username">
					<div class="left fl">密&nbsp;&nbsp;&nbsp;&nbsp;码:&nbsp;</div>
					<div class="right fl">
						<input class="shurukuang" type="password" name="password" id="password" placeholder="请输入你的密码"/>
						<label for="password"></label>
					</div>
				</div>
				<div class="username">
					<div class="left fl">验证码:&nbsp;</div>
					<div class="right fl">
						<input name="vcode" class="yanzhengma" id="vcode" type="text"  placeholder="验证码"/>
						&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<img id="captcha">
						<label for="vcode"> </label></div>
				</div>
				<div class="username">
					<div class="left fl">&nbsp;&nbsp;&nbsp;&nbsp;</div>
					<div class="right fl">
						<label id="checkMsg"></label>
					</div>
				</div>
				<div class="username">
					<input id="autoLogin" name="autoLogin" type="checkbox"/>&nbsp;&nbsp;两周以内自动登录
					<span id="autoLoginMsg"></span>
				</div>
				<div class="login_submit">
					<input class="submit" type="submit" id="btn" name="btn" >
				</div>
			</div>
		</div>
	</div>
</form>
<footer>
	<div class="copyright">简体 | 繁体 | English | 常见问题</div>
	<div class="copyright">小米公司版权所有-京ICP备10046444-<img src="./image/ghs.png" alt="">京公网安备11010802020134号-京ICP证110507号
	</div>

</footer>
</body>
</html>