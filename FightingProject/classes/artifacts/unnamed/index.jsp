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
    
	var RoadMapTool = cDesk.AppButton().getInstance({
	    appName: '商品入库' ,
	    appUrl: './Good/AddGoods.jsp',
		appIcon:'./img/map2.png',
	    appClickHandle: function (RoadMapTool) {
	        var win = cDesk.Windows().getInstance(desk, RoadMapTool, {
	        	windowWidth:1205,
        		windowHeight:830,
	            CloseCallback: function () {
	            	
	                cDesk.Taskbar.DelTask(win);
	            }
	        });
	        cDesk.Taskbar.AddTask(win);
	    }
	});
	
	
	var RoadMapTool2 = cDesk.AppButton().getInstance({
	    appName: '查看库存' ,
	    appUrl: './Good/ShowGoods.jsp',
		appIcon:'./img/map3.png',
	    appClickHandle: function (RoadMapTool2) {
	        var win = cDesk.Windows().getInstance(desk, RoadMapTool2, {
	        	windowWidth:1205,
        		windowHeight:830,
	            CloseCallback: function () {
	            	
	                cDesk.Taskbar.DelTask(win);
	            }
	        });
	        cDesk.Taskbar.AddTask(win);
	    }
	});
	desk.AddAppToDesk(0, RoadMapTool);
	desk.AddAppToDesk(0, RoadMapTool2);
	tool.AddAppbutton(RoadMapTool);
   
    var AccidentLocbtn = cDesk.AppButton().getInstance({
        appName: '订单查询' , 
        appUrl: 'http://localhost:8080/AccidentLoc/RoadLoc.html',
		appIcon:'./img/map.png',
        appClickHandle: function (AccidentLocbtn) {
            var win = cDesk.Windows().getInstance(desk, AccidentLocbtn, {
            	windowWidth:1205,
        		windowHeight:830,
                CloseCallback: function () {
                    cDesk.Taskbar.DelTask(win);
                }
            });
            cDesk.Taskbar.AddTask(win);
        }
    });
    desk.AddAppToDesk(1, AccidentLocbtn);
    tool.AddAppbutton(AccidentLocbtn);
    
    var AccidentA = cDesk.AppButton().getInstance({
        appName: '最终版' ,
        appUrl: 'http://localhost:8080/VCC-last/Analyzer.html',
		appIcon:'./img/analyze.png',
		
        appClickHandle: function (calendarYearbtn) {
            var win = cDesk.Windows().getInstance(desk, calendarYearbtn, {
            	windowWidth:1205,
        		windowHeight:830,
                CloseCallback: function () {
                    cDesk.Taskbar.DelTask(win);
                }
            });
            cDesk.Taskbar.AddTask(win);
        }
    });
    var AccidentB = cDesk.AppButton().getInstance({
        appName: '一般事故' ,
        appUrl: 'http://localhost:8080/VCC-ybsg/index.html',
		appIcon:'./img/analyze1.png',
		
        appClickHandle: function (calendarYearbtn) {
            var win = cDesk.Windows().getInstance(desk, calendarYearbtn, {
            	windowWidth:1205,
        		windowHeight:830,
                CloseCallback: function () {
                    cDesk.Taskbar.DelTask(win);
                }
            });
            cDesk.Taskbar.AddTask(win);
        }
    });
    var AccidentC = cDesk.AppButton().getInstance({
        appName: '初始版' ,
        appUrl: 'http://localhost:8080/Demo/index_jysg3.html',
		appIcon:'./img/analyze2.png',
		
        appClickHandle: function (calendarYearbtn) {
            var win = cDesk.Windows().getInstance(desk, calendarYearbtn, {
            	windowWidth:1205,
        		windowHeight:830,
                CloseCallback: function () {
                    cDesk.Taskbar.DelTask(win);
                }
            });
            cDesk.Taskbar.AddTask(win);
        }
    });
    desk.AddAppToDesk(2, AccidentA);
    desk.AddAppToDesk(2, AccidentB);
    desk.AddAppToDesk(2, AccidentC);
    tool.AddAppbutton(AccidentA);
    
    var HotAnalyzebtn = cDesk.AppButton().getInstance({
        appName: '热点分析' ,
        appUrl: 'http://localhost:8080/HotAnalaze/hotAnalyze.html',
		appIcon:'./img/hot3.png',
		
        appClickHandle: function (HotAnalyzebtn) {
            var win = cDesk.Windows().getInstance(desk, HotAnalyzebtn, {
            	windowWidth:1205,
        		windowHeight:830,
                CloseCallback: function () {
                    cDesk.Taskbar.DelTask(win);
                }
            });
            cDesk.Taskbar.AddTask(win);
        }
    });
    var AllDfldbtn = cDesk.AppButton().getInstance({
        appName: '多发路段' ,
        appUrl: 'http://localhost:8080/HotAnalaze/displayAllDfld.html',
		appIcon:'./img/hot2.png',
		
        appClickHandle: function (AllDfldbtn) {
            var win = cDesk.Windows().getInstance(desk, AllDfldbtn, {
            	windowWidth:1205,
        		windowHeight:830,
                CloseCallback: function () {
                    cDesk.Taskbar.DelTask(win);
                }
            });
            cDesk.Taskbar.AddTask(win);
        }
    });
    desk.AddAppToDesk(3, HotAnalyzebtn);
    desk.AddAppToDesk(3, AllDfldbtn);
    tool.AddAppbutton(HotAnalyzebtn);
  
</script>