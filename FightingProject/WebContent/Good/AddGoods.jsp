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
<form class="layui-form layui-form-pane" action="../AddGoodsServlet" method="post">
	<div class="layui-form-item">
		<label class="layui-form-label">商品名称</label>
		<div class="layui-input-inline">
			<input type="text" id = "Gname" name="Gname" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
		</div>
	</div>
  
	<div class="layui-form-item">
		<label class="layui-form-label">商品类别</label>
		<div class="layui-input-inline">
			<input type="text" id = "Gtype" name="Gtype" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
		</div>
    </div>
  
	<div class="layui-form-item">
    	<label class="layui-form-label">商品价格</label>
    	<div class="layui-input-inline">
			<input type="text" id = "Gprice" name="Gprice" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
		</div>
	</div>
  
	<div class="layui-form-item">
    	<label class="layui-form-label">商品数量</label>
    	<div class="layui-input-inline">
      		<input type="text" id = "Gamount" name="Gamount" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
    	</div>
    </div>
  
	<div class="layui-form-item">
    	<label class="layui-form-label">商品单位</label>
    	<div class="layui-input-inline">
      		<input type="text" id = "Gunit" name="Gunit" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
    	</div>
  	</div>
  
	<div class="layui-form-item">
		<label class="layui-form-label">商品折扣 </label>
		<div class="layui-input-inline">
			<input type="text" id = "Gdiscount" name="Gdiscount" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
    	</div>
    </div>
  
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
		<legend>商品图片</legend>
	</fieldset>
 
	<div class="layui-upload">
 		<button type="button" class="layui-btn" id="test1">上传图片</button>
		<div class="layui-upload-list">
			<img class="layui-upload-img" id="demo1">
			<p id="demoText"></p>
		</div>
	</div>      

	<div class="layui-form-item layui-form-text">
		<label class="layui-form-label">商品简介</label>
    	<div class="layui-input-block">
      		<textarea id = "Gintro" name="Gintro" placeholder="请输入内容" class="layui-textarea"></textarea>
    	</div>
	</div>
	
    <input type="hidden" id = "Gimgurl" name="Gimgurl" value="123"/>
   
    <div class="layui-form-item layui-form-text">
    	<label class="layui-form-label">备注</label>
    	<div class="layui-input-block">
      		<textarea id = "Gbrief" name="Gbrief" placeholder="请输入内容" class="layui-textarea"></textarea>
    	</div>
	</div>
	
	<div class="layui-form-item">
		<button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
	</div>
</form>
          


<script src="../layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
layui.use('upload', function(){
  var $ = layui.jquery
  ,upload = layui.upload;
  
  //普通图片上传
  var uploadInst = upload.render({
    elem: '#test1'
    ,url: '../UploadFileServlet'
    ,before: function(obj){
      //预读本地文件示例，不支持ie8
      obj.preview(function(index,  file, result){
        $('#demo1').attr('src', result); //图片链接（base64）
      });
    }
    ,done: function(res){
      //如果上传失败
      if(res.code > 0){
        return layer.msg('上传失败');
      }
      //上传成功
      $('#Tpic').val(res.name)
      $('#Gimgurl').val(res.name)
      
    }
    ,error: function(){
      //演示失败状态，并实现重传
      var demoText = $('#demoText');
      demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
      demoText.find('.demo-reload').on('click', function(){
        uploadInst.upload();
      });
    }
  });
});

//Demo
layui.use('form', function(){
var form = layui.form;

//监听提交
form.on('submit(formDemo)', function(data){
  layer.msg(JSON.stringify(data.field));
  
});
});


</script>
</body>
</html>