<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加用户</title>
<link rel="stylesheet" href="../layui/css/layui.css"  media="all">
</head>
<body>
<form class="layui-form layui-form-pane" action="../AddUserServlet" method="post">

	<div class="layui-form-item">
    	<label class="layui-form-label">用户名</label>
    	<div class="layui-input-inline">
      		<input type="text" id = "Uname" name="Uname" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
    	</div>
	</div>
  
	<div class="layui-form-item">
    	<label class="layui-form-label">密码</label>
    	<div class="layui-input-inline">
      		<input type="text" id = "Upwd" name="Upwd" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
    	</div>
    </div>
  
	<div class="layui-form-item">
    	<label class="layui-form-label">用户邮箱</label>
    	<div class="layui-input-inline">
      		<input type="text" id = "Uemail" name="Uemail" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
    	</div>
	</div>
    
	<div class="layui-form-item">
    	<label class="layui-form-label">真实姓名</label>
    	<div class="layui-input-inline">
      		<input type="text" id = "Urealname" name="Urealname" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
    	</div>
	</div>
	
	<div class="layui-form-item">
    	<label class="layui-form-label">手机号</label>
    	<div class="layui-input-inline">
      		<input type="text" id = "Uphone" name="Uphone" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
    	</div>
	</div>
	
	<div class="layui-form-item">
    	<label class="layui-form-label">用户类型</label>
    	<div class="layui-input-inline">
      		<input type="text" id = "Utype" name="Utype" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
    	</div>
	</div>
	
	<div class="layui-form-item">
    	<label class="layui-form-label">用户余额</label>
    	<div class="layui-input-inline">
      		<input type="text" id = "Umoney" name="Umoney" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
    	</div>
	</div>
	
	<div class="layui-form-item">
		<button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
	</div>
</form>

<script src="../layui/layui.js" charset="utf-8"></script>
</body>
</html>