<%@page import="javax.websocket.Session"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    if(session.getAttribute("username") == null)
    {
    	  response.sendRedirect("login.jsp");
          return;     
    }
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <link type="text/css" rel="Stylesheet" href="cDesk/theme/default/default.css" />
</head>
<body>

</body>
</html>
<script type="text/javascript" src="cDesk/cDesk-2.5.min.js"></script>
<script type="text/javascript" src="cDesk/cdesk_config.js" ></script>
<script type="text/javascript">
    
    //商品入库功能块(修缮中)
	var goodsWarehousing = cDesk.AppButton().getInstance({
	    appName: '商品入库' ,
	    appUrl: './Good/AddGoods.jsp',
		appIcon:'./images/goodsWarehousing.png',
	    appClickHandle: function (goodsWarehousing) {
	        var win = cDesk.Windows().getInstance(desk, goodsWarehousing, {
	        	windowWidth:1205,
        		windowHeight:830,
	            CloseCallback: function () {
	            	
	                cDesk.Taskbar.DelTask(win);
	            }
	        });
	        cDesk.Taskbar.AddTask(win);
	    }
	});
	
	//商品出库功能块(修缮中)
	var outOfTheTreasury = cDesk.AppButton().getInstance({
	    appName: '商品出库' ,
	    appUrl: './Good/ShowGoods.jsp',
		appIcon:'./images/outOfTheTreasury.png',
	    appClickHandle: function (outOfTheTreasury) {
	        var win = cDesk.Windows().getInstance(desk, outOfTheTreasury, {
	        	windowWidth:1205,
        		windowHeight:830,
	            CloseCallback: function () {
	            	
	                cDesk.Taskbar.DelTask(win);
	            }
	        });
	        cDesk.Taskbar.AddTask(win);
	    }
	});
	
	//更新库存功能块(修缮中)
	var updateStock = cDesk.AppButton().getInstance({
	    appName: '更新库存' ,
	    appUrl: './Good/ShowGoods.jsp',
		appIcon:'./images/updateStock.png',
	    appClickHandle: function (updateStock) {
	        var win = cDesk.Windows().getInstance(desk, updateStock, {
	        	windowWidth:1205,
        		windowHeight:830,
	            CloseCallback: function () {
	            	
	                cDesk.Taskbar.DelTask(win);
	            }
	        });
	        cDesk.Taskbar.AddTask(win);
	    }
	});
	
	//查看库存功能块(修缮中)
	var viewInventory = cDesk.AppButton().getInstance({
	    appName: '查看库存' ,
	    appUrl: './Good/ShowGoods.jsp',
		appIcon:'./images/viewInventory.png',
	    appClickHandle: function (viewInventory) {
	        var win = cDesk.Windows().getInstance(desk, viewInventory, {
	        	windowWidth:1205,
        		windowHeight:830,
	            CloseCallback: function () {
	            	
	                cDesk.Taskbar.DelTask(win);
	            }
	        });
	        cDesk.Taskbar.AddTask(win);
	    }
	});
	
	//功能块
	desk.AddAppToDesk(0, goodsWarehousing);
	desk.AddAppToDesk(0, outOfTheTreasury);
	desk.AddAppToDesk(0, updateStock);
	desk.AddAppToDesk(0, viewInventory);
	
	//侧边栏
	tool.AddAppbutton(goodsWarehousing);
   
    var orderQuery = cDesk.AppButton().getInstance({
        appName: '订单查询' , 
        appUrl: 'http://localhost:8080/AccidentLoc/RoadLoc.html',
		appIcon:'./images/orderQuery.png',
        appClickHandle: function (orderQuery) {
            var win = cDesk.Windows().getInstance(desk, orderQuery, {
            	windowWidth:1205,
        		windowHeight:830,
                CloseCallback: function () {
                    cDesk.Taskbar.DelTask(win);
                }
            });
            cDesk.Taskbar.AddTask(win);
        }
    });
    
    desk.AddAppToDesk(1, orderQuery);
    
    tool.AddAppbutton(orderQuery);
    
    var viewTheUser = cDesk.AppButton().getInstance({
        appName: '查看用户' ,
        appUrl: 'http://localhost:8080/VCC-last/Analyzer.html',
		appIcon:'./images/viewTheUser.png',
		
        appClickHandle: function (viewTheUser) {
            var win = cDesk.Windows().getInstance(desk, viewTheUser, {
            	windowWidth:1205,
        		windowHeight:830,
                CloseCallback: function () {
                    cDesk.Taskbar.DelTask(win);
                }
            });
            cDesk.Taskbar.AddTask(win);
        }
    });
    var userFreezing = cDesk.AppButton().getInstance({
        appName: '用户冻结' ,
        appUrl: 'http://localhost:8080/VCC-ybsg/index.html',
		appIcon:'./images/userFreezing.png',
		
        appClickHandle: function (userFreezing) {
            var win = cDesk.Windows().getInstance(desk, userFreezing, {
            	windowWidth:1205,
        		windowHeight:830,
                CloseCallback: function () {
                    cDesk.Taskbar.DelTask(win);
                }
            });
            cDesk.Taskbar.AddTask(win);
        }
    });
    var UserAddress = cDesk.AppButton().getInstance({
        appName: '用户地址' ,
        appUrl: 'http://localhost:8080/Demo/index_jysg3.html',
		appIcon:'./images/UserAddress.png',
		
        appClickHandle: function (UserAddress) {
            var win = cDesk.Windows().getInstance(desk, UserAddress, {
            	windowWidth:1205,
        		windowHeight:830,
                CloseCallback: function () {
                    cDesk.Taskbar.DelTask(win);
                }
            });
            cDesk.Taskbar.AddTask(win);
        }
    });
    desk.AddAppToDesk(2, viewTheUser);
    desk.AddAppToDesk(2, userFreezing);
    desk.AddAppToDesk(2, UserAddress);
    tool.AddAppbutton(viewTheUser);
    
    var queryManagement = cDesk.AppButton().getInstance({
        appName: '查询管理' ,
        appUrl: 'http://localhost:8080/HotAnalaze/hotAnalyze.html',
		appIcon:'./images/queryManagement.png',
		
        appClickHandle: function (queryManagement) {
            var win = cDesk.Windows().getInstance(desk, queryManagement, {
            	windowWidth:1205,
        		windowHeight:830,
                CloseCallback: function () {
                    cDesk.Taskbar.DelTask(win);
                }
            });
            cDesk.Taskbar.AddTask(win);
        }
    });
    var authorityManagement = cDesk.AppButton().getInstance({
        appName: '权限管理' ,
        appUrl: 'http://localhost:8080/HotAnalaze/displayAllDfld.html',
		appIcon:'./images/authorityManagement.png',
		
        appClickHandle: function (authorityManagement) {
            var win = cDesk.Windows().getInstance(desk, authorityManagement, {
            	windowWidth:1205,
        		windowHeight:830,
                CloseCallback: function () {
                    cDesk.Taskbar.DelTask(win);
                }
            });
            cDesk.Taskbar.AddTask(win);
        }
    });
    desk.AddAppToDesk(3, queryManagement);
    desk.AddAppToDesk(3, authorityManagement);
    tool.AddAppbutton(queryManagement);
  
</script>