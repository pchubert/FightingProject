	var names = ['商品管理','订单管理','用户管理','权限管理'];
    var desk = cDesk.DeskBox().getInstance(names);
    var tool = cDesk.Toolbar().getInstance(desk, {
        setBtn3Title: '主题设置',
        outBtnHandler: function () {
        	alert("您确定要退出系统吗？");
        	
        },
        setBtn3Handler: function () {
            var themes = cDesk.Themes().getInstance();
            themes.AddTheme({
                themeName: '抽象1',
                themeIcon: './theme/min/abstract1.png',
                themeImage: './theme/abstract1.png',
                themeCss: '',
                setCallback: function (d) { }
            });
        
            themes.AddTheme({
                themeName: '抽象2',
                themeIcon: '/theme/min/abstract2.png',
                themeImage: '/theme/abstract2.png',
                themeCss: '',
                setCallback: function (d) { }
            });
            
            themes.AddTheme({
                themeName: '抽象3',
                themeIcon: '/theme/min/abstract3.png',
                themeImage: '/theme/abstract3.png',
                themeCss: '',
                setCallback: function (d) { }
            });
            
            themes.AddTheme({
                themeName: '风景1',
                themeIcon: '/theme/min/scenery1.png',
                themeImage: '/theme/scenery1.png',
                themeCss: '',
                setCallback: function (d) { }
            });
            
            themes.AddTheme({
                themeName: '风景2',
                themeIcon: '/theme/min/scenery2.png',
                themeImage: '/theme/scenery2.png',
                themeCss: '',
                setCallback: function (d) { }
            });
           
            themes.AddTheme({
                themeName: '风景3',
                themeIcon: '/theme/min/scenery3.png',
                themeImage: '/theme/scenery3.png',
                themeCss: '',
                setCallback: function (d) { }
            });
           
            themes.AddTheme({
                themeName: '动物1',
                themeIcon: '/theme/min/animal1.png',
                themeImage: '/theme/animal1.png',
                themeCss: '',
                setCallback: function (d) { }
            });
            
            themes.AddTheme({
                themeName: '动物2',
                themeIcon: '/theme/min/animal2.png',
                themeImage: '/theme/animal2.png',
                themeCss: '',
                setCallback: function (d) { }
            });
           
            themes.AddTheme({
                themeName: '动物3',
                themeIcon: '/theme/min/animal3.png',
                themeImage: '/theme/animal3.png',
                themeCss: '',
                setCallback: function (d) { }
            });
            
            themes.AddTheme({
                themeName: '卡通1',
                themeIcon: '/theme/min/cartoon1.png',
                themeImage: '/theme/cartoon1.png',
                themeCss: '',
                setCallback: function (d) { }
            });
            
            themes.AddTheme({
                themeName: '卡通2',
                themeIcon: '/theme/min/cartoon2.png',
                themeImage: '/theme/cartoon2.png',
                themeCss: '',
                setCallback: function (d) { }
            });
          
            themes.AddTheme({
                themeName: '卡通3',
                themeIcon: '/theme/min/cartoon3.png',
                themeImage: '/theme/cartoon3.png',
                themeCss: '',
                setCallback: function (d) { }
            });
            
            themes.AddTheme({
                themeName: '人物1',
                themeIcon: '/theme/min/person1.png',
                themeImage: '/theme/person1.png',
                themeCss: '',
                setCallback: function (d) { }
            });
            
            themes.AddTheme({
                themeName: '人物2',
                themeIcon: '/theme/min/person2.png',
                themeImage: '/theme/person2.png',
                themeCss: '',
                setCallback: function (d) { }
            });
        }
    });