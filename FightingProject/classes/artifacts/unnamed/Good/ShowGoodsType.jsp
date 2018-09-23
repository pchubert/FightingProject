<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../layui/css/layui.css"  media="all">
</head>
<body>
  <div class="layui-form layui-border-box layui-table-view" lay-filter="LAY-table-1" style=" height:332px;">
   <div class="layui-table-box">
    <div class="layui-table-header">
     <table cellspacing="0" cellpadding="0" border="0" class="layui-table">
      <thead>
       <tr>
        <th data-field="id">
         <div class="layui-table-cell laytable-cell-1-id">
          <span>ID</span>
          <span class="layui-table-sort layui-inline"><i class="layui-edge layui-table-sort-asc"></i><i class="layui-edge layui-table-sort-desc"></i></span>
         </div></th>
        <th data-field="username">
         <div class="layui-table-cell laytable-cell-1-username">
          <span>用户名</span>
         </div></th>
        <th data-field="sex">
         <div class="layui-table-cell laytable-cell-1-sex">
          <span>性别</span>
          <span class="layui-table-sort layui-inline"><i class="layui-edge layui-table-sort-asc"></i><i class="layui-edge layui-table-sort-desc"></i></span>
         </div></th>
        <th data-field="city">
         <div class="layui-table-cell laytable-cell-1-city">
          <span>城市</span>
         </div></th>
        <th data-field="sign">
         <div class="layui-table-cell laytable-cell-1-sign">
          <span>签名</span>
         </div></th>
        <th data-field="experience">
         <div class="layui-table-cell laytable-cell-1-experience">
          <span>积分</span>
          <span class="layui-table-sort layui-inline"><i class="layui-edge layui-table-sort-asc"></i><i class="layui-edge layui-table-sort-desc"></i></span>
         </div></th>
        <th data-field="score">
         <div class="layui-table-cell laytable-cell-1-score">
          <span>评分</span>
          <span class="layui-table-sort layui-inline"><i class="layui-edge layui-table-sort-asc"></i><i class="layui-edge layui-table-sort-desc"></i></span>
         </div></th>
        <th data-field="classify">
         <div class="layui-table-cell laytable-cell-1-classify">
          <span>职业</span>
         </div></th>
        <th data-field="wealth">
         <div class="layui-table-cell laytable-cell-1-wealth">
          <span>财富</span>
          <span class="layui-table-sort layui-inline"><i class="layui-edge layui-table-sort-asc"></i><i class="layui-edge layui-table-sort-desc"></i></span>
         </div></th>
        <th data-field="9">
         <div class="layui-table-cell laytable-cell-1-9" align="center">
          <span></span>
         </div></th>
        <th class="layui-table-patch">
         <div class="layui-table-cell" style="width: 17px;"></div></th>
       </tr>
      </thead>
     </table>
    </div>
    <div class="layui-table-body layui-table-main" style="height: 251px;">
     <table cellspacing="0" cellpadding="0" border="0" class="layui-table">
      <tbody>
       <tr data-index="0" class="">
        <td data-field="id">
         <div class="layui-table-cell laytable-cell-1-id">
          10000
         </div></td>
        <td data-field="username">
         <div class="layui-table-cell laytable-cell-1-username">
          user-0
         </div></td>
        <td data-field="sex">
         <div class="layui-table-cell laytable-cell-1-sex">
          女
         </div></td>
        <td data-field="city">
         <div class="layui-table-cell laytable-cell-1-city">
          城市-0
         </div></td>
        <td data-field="sign">
         <div class="layui-table-cell laytable-cell-1-sign">
          签名-0
         </div></td>
        <td data-field="experience">
         <div class="layui-table-cell laytable-cell-1-experience">
          255
         </div></td>
        <td data-field="score">
         <div class="layui-table-cell laytable-cell-1-score">
          57
         </div></td>
        <td data-field="classify">
         <div class="layui-table-cell laytable-cell-1-classify">
          作家
         </div></td>
        <td data-field="wealth">
         <div class="layui-table-cell laytable-cell-1-wealth">
          82830700
         </div></td>
        <td data-field="9" align="center" data-off="true">
         <div class="layui-table-cell laytable-cell-1-9"> 
          <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a> 
          <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a> 
          <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a> 
         </div></td>
       </tr>
     
       </tbody>
      </table>
     </div>
    </div>
   </div>
   <div class="layui-table-page">
    <div id="layui-table-page1">
     <div class="layui-box layui-laypage layui-laypage-default" id="layui-laypage-2">
      <a href="javascript:;" class="layui-laypage-prev layui-disabled" data-page="0"><i class="layui-icon"></i></a>
      <span class="layui-laypage-curr"><em class="layui-laypage-em"></em><em>1</em></span>
      <a href="javascript:;" data-page="2">2</a>
      <a href="javascript:;" data-page="3">3</a>
      <span class="layui-laypage-spr">…</span>
      <a href="javascript:;" class="layui-laypage-last" title="尾页" data-page="100">100</a>
      <a href="javascript:;" class="layui-laypage-next" data-page="2"><i class="layui-icon"></i></a>
      <span class="layui-laypage-skip">到第<input type="text" min="1" value="1" class="layui-input" />页<button type="button" class="layui-laypage-btn">确定</button></span>
      <span class="layui-laypage-count">共 1000 条</span>
      <span class="layui-laypage-limits"><select lay-ignore=""><option value="10" selected="">10 条/页</option><option value="20">20 条/页</option><option value="30">30 条/页</option><option value="40">40 条/页</option><option value="50">50 条/页</option><option value="60">60 条/页</option><option value="70">70 条/页</option><option value="80">80 条/页</option><option value="90">90 条/页</option></select></span>
     </div>
    </div>
   </div>
   <style>.laytable-cell-1-id{ width: 80px; }.laytable-cell-1-username{ width: 80px; }.laytable-cell-1-sex{ width: 80px; }.laytable-cell-1-city{ width: 80px; }.laytable-cell-1-sign{ width: 170px; }.laytable-cell-1-experience{ width: 80px; }.laytable-cell-1-score{ width: 80px; }.laytable-cell-1-classify{ width: 80px; }.laytable-cell-1-wealth{ width: 135px; }.laytable-cell-1-9{ width: 165px; }</style>
  </div>
</body>
</html>