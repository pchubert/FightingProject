<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加商品</title>
<link rel="stylesheet" href="../layui/css/layui.css"  media="all">
</head>
<body>
<form class="layui-form layui-form-pane" action="../AddAddressServlet" method="post">
	<div class="layui-form-item">
		<label class="layui-form-label">用户 ID</label>
		<div class="layui-input-inline">
			<input type="text" id = "Uid" name="Uid" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
		</div>
	</div>
  
	<div class="layui-form-item">
		<label class="layui-form-label">地址名</label>
		<div class="layui-input-inline">
			<input type="text" id = "name" name="name" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
		</div>
    </div>
  
	<div class="layui-form-item">
    	<label class="layui-form-label">联系方式</label>
    	<div class="layui-input-inline">
			<input type="text" id = "telephone" name="telephone" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
		</div>
	</div>
  
	<div class="layui-form-item">
    	<label class="layui-form-label">建筑名</label>
    	<div class="layui-input-inline">
      		<input type="text" id = "buildingName" name="buildingName" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
    	</div>
    </div>
  
	<div class="layui-form-item layui-form-text">
		<label class="layui-form-label">介绍</label>
    	<div class="layui-input-block">
      		<textarea id = "introduction" name="introduction" placeholder="请输入内容" class="layui-textarea"></textarea>
    	</div>
	</div>
  
	<div class="layui-form-item">
		<button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
	</div>
</form>
          
<script src="../layui/layui.js" charset="utf-8"></script>

</body>
</html>