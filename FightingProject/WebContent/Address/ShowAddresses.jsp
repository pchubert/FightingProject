<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show Addresses</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="../layui/css/layui.css"  media="all">
</head>
<body>

<script src="../layui/layui.js" charset="utf-8"></script>
              
<table class="layui-hide" id="test"></table>     

<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
 
<script>
layui.use('table', function(){
  var table = layui.table;

  //测试用
  //alert(window.location.href);
  //window.location.href="/FightingProject/ListAddressesServlet";

  table.render({
	    elem: '#test'
	    ,url: '/FightingProject/ListAddressesServlet'
	    ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
	    ,page: true //开启分页
	    ,cols: [[
	      {field:'id', width:80, title: 'ID', sort: true}
	      ,{field:'uid', width:80, title: '用户名'}
	      ,{field:'name', width:80, title: '地址名'}
	      ,{field:'telephone', width:80, title: '电话'}
	      ,{field:'buildingName', width:80, title: '建筑名'}
	      ,{field:'introduction', width:80, title: '介绍'}
	    ]]
	  });
  
});

</script>

</body>
</html>