/***************************************
    名称：cDesk
    功能：创建一个Windows桌面布局结构
    创建：2012-05-03
    修改：2013-03-30
    作者：cnHero
    版本：v2.5
**************************************/
var cDesk = window.cDesk = {
    /* 常用工具类 */
    Common: {
        /*是否IE浏览器*/
        isIE: function () {
            if (window.ActiveXObject) {
                return true;
            }
            else if (window.XMLHttpRequest) {
                return false;
            }
        },
        /*移除元素*/
        delElement: function (elem) {
            if (elem) {
                if (elem.parentNode) {
                    elem.parentNode.removeChild(elem);
                }
            }
        },
        /* Event对象兼容处理 */
        newEvent: function (event) {
            if (event.target) return event;

            var event2 = {
                target: event.srcElement || document,
                preventDefault: function () { event.returnValue = false },
                stopPropagation: function () { event.cancelBubble = true }
            };
            // IE6/7/8 在原生window.event对象写入数据会导致内存无法回收，应当采用拷贝
            for (var i in event) event2[i] = event[i];
            return event2;
        }
    },

    Document: {
        getClientWidth: function () {
            var w = null;
            if (document && document.documentElement) {
                w = document.documentElement.clientWidth;
            }
            else if (document && document.body) {
                w = document.body.clientWidth;
            }
            return w;
        },
        getClientHeight: function () {
            var h = null;
            if (document && document.documentElement) {
                h = document.documentElement.clientHeight;
            }
            else if (document && document.body) {
                h = document.body.clientHeight;
            }
            return h;
        }
    },

    /* 事件操作类 */
    Event: {
        /*添加事件绑定*/
        add: function (elem, type, fn) {
            elem.addEventListener ? elem.addEventListener(type, fn, false) : elem.attachEvent('on' + type, fn);
            if (type === "contextmenu" && elem['oncontextmenu'] == null) {
                elem.oncontextmenu = fn; //oncontextmenu事件比较特殊
            }
        },
        /*移除事件绑定*/
        del: function (elem, type, fn) {
            if (elem.removeEventListener) {
                elem.removeEventListener(type, fn, false);
            }
            else if (elem.detachEvent) {
                elem.detachEvent("on" + type, fn);
            }
            else {
                elem["on" + type] = null;
            }
        },
        /*清空事件绑定*/
        empty: function (elem, type) {
            elem["on" + type] = null;
        }
    },

    /* 样式操作类 */
    Style: {
        /*添加样式*/
        set: function (elem, name, value) {
            if (elem) {
                if (typeof (name) === 'string') {
                    elem.style[name] = value;
                }
            }
        },
        /*获取样式值*/
        get: function (elem, name) {
            var value = undefined;
            if (elem) {
                if (elem.style[name]) {//获取内嵌样式
                    value = elem.style[name];
                }
                else if (elem.currentStyle) {//获取css样式表样式，IE
                    value = elem.currentStyle[name];
                }
                else if (document.defaultView && document.defaultView.getComputedStyle) {//获取css样式表样式，非IE
                    style = name.replace(/([A-Z])/g, '-$1').toLowerCase(); //
                    value = document.defaultView.getComputedStyle(elem, null)[name];
                }
            }
            return value;
        },
        /*移除样式*/
        del: function (elem, name) {
            if (elem) {
                if (typeof (name) === 'string') {
                    elem.style[name] = "";
                    //移除由css样式表设置的样式
                    var v = "";
                    if (elem.currentStyle) {
                        v = elem.currentStyle[name];
                    }
                    else if (document.defaultView && document.defaultView.getComputedStyle) {
                        v = document.defaultView.getComputedStyle(elem, null)[name];
                    }
                    if (v != "" && v != "auto") {
                        var className = "." + elem.getAttribute("class");
                        for (var i = 0; i < document.styleSheets.length; i++) {
                            var cssClass = document.styleSheets[i];
                            var cssList = cssClass.rules || cssClass.cssRules;
                            for (var j = 0; j < cssList.length; j++) {
                                var css = cssList[j];
                                if (css.selectorText == className) {
                                    css.style[name] = "";
                                }
                            }
                        }
                    }
                }
            }
        },
        /*元素透明操作*/
        Opacity: {
            get: function (elem) {
                return isOpacity
                       ? document.defaultView.getComputedStyle(elem, false).opacity
                       : ropacity.test((elem.currentStyle
				            ? elem.currentStyle.filter
				            : elem.style.filter) || '')
				                ? (parseFloat(RegExp.$1) / 100) + ''
				                : 1;
            },
            set: function (elem, value) {
                if (isOpacity) return elem.style.opacity = value;
                var style = elem.style;
                style.zoom = 1;

                var opacity = 'alpha(opacity=' + value * 100 + ')',
                    filter = style.filter || '';

                style.filter = ralpha.test(filter)
                     ? filter.replace(ralpha, opacity)
                     : style.filter + ' ' + opacity;
            }
        },
        /* 获取样式值(数字)，如：width=200px, 返回值为200 */
        getStyleNumber: function (elem, name) {
            if (elem) {
                var value = cDesk.Style.get(elem, name);
                if (value != "" && value != 'auto') {
                    value = Number(value.toString().split("px")[0]);
                }
                return value;
            }
        }
    },

    /* 拖拽类 */
    Drag: function (dragObj, moveObj, minleft, mintop, maxleft, maxtop) {
        var dragDOM = typeof (dragObj) === 'object' ? dragObj : document.getElementById(dragObj);
        var moveDOM = typeof (moveObj) === 'object' ? moveObj : document.getElementById(moveObj);
        if (dragDOM === null || dragDOM === undefined || moveDOM === null || moveDOM === undefined) {
            return;
        }
        if (minleft === undefined) {
            minleft = 0;
        }
        minleft = Number(minleft);
        minleft = isNaN(minleft) ? 0 : minleft;

        if (maxleft === undefined) {
            var dw = cDesk.Style.getStyleNumber(moveDOM, 'width');
            maxleft = cDesk.Document.getClientWidth() - dw;
        }
        maxleft = Number(maxleft);
        maxleft = isNaN(maxleft) ? cDesk.Document.getClientWidth() : maxleft;

        if (mintop === undefined) {
            mintop = 0;
        }
        mintop = Number(mintop);
        mintop = isNaN(mintop) ? 0 : mintop;

        if (maxtop === undefined) {
            var dh = cDesk.Style.getStyleNumber(moveDOM, 'height');
            maxtop = cDesk.Document.getClientHeight() - dh;
        }
        maxtop = Number(maxtop);
        maxtop = isNaN(maxtop) ? cDesk.Document.getClientHeight() : maxtop;

        cDesk.Event.add(dragDOM, "mousedown", function (ev) {
            var _dragMove = true;
            var _document = document;
            if (!ev) { ev = window.event; }
            ev = cDesk.Common.newEvent(ev);
            var _offsetX = ev.clientX - cDesk.Style.getStyleNumber(moveDOM, 'left'); //鼠标位置相对与移动对象的便宜值
            var _offsetY = ev.clientY - cDesk.Style.getStyleNumber(moveDOM, 'top');

            var _move = function (evm) {
                if (_dragMove) {
                    if (!evm) { evm = window.event; };
                    evm = cDesk.Common.newEvent(evm);
                    var nowleft = evm.clientX + document.body.scrollLeft - _offsetX;
                    var nowtop = evm.clientY + document.body.scrollTop - _offsetY;
                    if (document.documentElement) {
                        nowleft = evm.clientX + document.documentElement.scrollLeft - _offsetX;
                        nowtop = evm.clientY + document.documentElement.scrollTop - _offsetY;
                    }
                    else if (document.body) {
                        nowleft = evm.clientX + document.body.scrollLeft - _offsetX;
                        nowtop = evm.clientY + document.body.scrollTop - _offsetY;
                    }
                    if (nowleft >= minleft && nowleft < maxleft) {
                        cDesk.Style.set(moveDOM, 'left', nowleft + "px");
                    }
                    if (nowtop >= mintop && nowtop < maxtop) {
                        cDesk.Style.set(moveDOM, 'top', nowtop + "px");
                    }
                    evm.stopPropagation();
                    evm.preventDefault();
                }
            };
            var _end = function () {
                _dragMove = false;
                cDesk.Event.empty(_document, 'mousemove');
                cDesk.Event.empty(_document, 'mouseup');
            };

            cDesk.Event.add(_document, 'mousemove', _move);
            cDesk.Event.add(_document, 'mouseup', _end);
            ev.stopPropagation();
            ev.preventDefault();
        });
    }
};

/*功能按钮类*/
cDesk.AppButton = function () {
    function init(config) {
        //默认设置
        var setting = {
            guid: Math.random(),       /*ID*/
            appUrl: "",                /*AppURL*/
            appName: "",               /*App名称*/
            appIcon: "",               /*App图标*/
            appType: "inapp",          /*App类型,[inapp,outapp,pendant,text,image,video],内部功能、外部应用、挂件、文本、图片、视频，不同类型使用不同的打开方式*/
            appIndex: 1,               /*排序*/
            appData: null,             /*附带数据*/
            appOpenedState: "default", /*打开方式 [default,maximun,newbox]*/
            appOpenedWidth: 850,       /*打开时宽度*/
            appOpenedHeight: 500,      /*打开时高度*/
            appLeft: 0,                /*相对与父元素的左边位置*/
            appTop: 0,                 /*相对与父元素的上边位置*/
            appObject: this,           /*当前App对象实例*/
            appDOM: null,              /*当前APP DOM对现实例*/
            appNumberDOM: null,        /*当前APP数字DOM对现实例*/
            appClickHandle: null,      /*点击处理方法*/
            appUpdateHandler: null     /*当前APP更新处理程序*/
        };
        //更新配置
        if (typeof (config) === 'object') {
            for (var p in config) {
                if (setting[p] !== undefined) {
                    setting[p] = config[p];
                }
            }
            setting.appObject = this; //防止配置参数将其覆盖
        }
        //创建AppButton对象
        (function () {
            var thisappid = "cDesk-AppButton";
            var thisappicon = "cDesk-AppButton_Icon";
            var thisappname = "cDesk-AppButton_Name";

            var app = document.createElement("div");
            app.setAttribute("id", thisappid + setting.guid);
            app.setAttribute("class", thisappid);
            app.setAttribute("title", setting.appName);
            cDesk.Style.set(app, "left", setting.appLeft + "px");
            cDesk.Style.set(app, "top", setting.appTop + "px");

            var icon = document.createElement("div");
            icon.setAttribute("id", thisappicon + setting.guid);
            icon.setAttribute("class", thisappicon);
            var number = document.createElement("div");
            number.setAttribute("id", thisappid + "_num" + setting.guid);
            number.setAttribute("class", thisappid + "_num");
            var numberleft = document.createElement("div");
            numberleft.setAttribute("class", thisappid + "_numleft");
            number.appendChild(numberleft);
            var numbertext = document.createElement("div");
            numbertext.setAttribute("class", thisappid + "_numtext");
            number.appendChild(numbertext);
            var numberright = document.createElement("div");
            numberright.setAttribute("class", thisappid + "_numright");
            number.appendChild(numberright);
            icon.appendChild(number);
            var iconimg = document.createElement("img");
            if (setting.appIcon == "" || setting.appIcon == null || setting.appIcon == undefined) {
                cDesk.Style.set(iconimg, 'display', 'none');
            }
            iconimg.setAttribute("src", setting.appIcon);
            cDesk.Style.set(iconimg, 'width', '100%');
            cDesk.Style.set(iconimg, 'height', '100%');
            icon.appendChild(iconimg);
            app.appendChild(icon);

            var name = document.createElement("div");
            name.setAttribute("id", thisappname + setting.guid);
            name.setAttribute("class", thisappname);

            var nameleftb = document.createElement("div");
            nameleftb.setAttribute("class", thisappname + "_left");
            name.appendChild(nameleftb);
            var text = document.createElement("span");
            text.setAttribute("class", thisappname + "_text");
            text.innerHTML = setting.appName;
            name.appendChild(text);
            var namerightb = document.createElement("div");
            namerightb.setAttribute("class", thisappname + "_right");
            name.appendChild(namerightb);

            app.appendChild(name);
            cDesk.Event.add(app, 'click', function () {
                if (setting.appClickHandle && typeof (setting.appClickHandle) === 'function') {
                    setting.appClickHandle(setting.appObject);
                }
            });
            setting.appDOM = app;
            setting.appNumberDOM = number;

            //添加事件绑定，用以更新提示数据
            function appUpdateFn() {
                var nums = 0;
                if (setting.appUpdateHandler) {
                    nums = setting.appUpdateHandler();
                }
                if (isNaN(Number(nums))) {
                    nums = 0;
                }
                if (nums > 0) {
                    setting.appNumberDOM.innerHTML = nums;
                    cDesk.Style.set(setting.appNumberDOM, 'display', 'block');
                }
                else {
                    cDesk.Style.set(setting.appNumberDOM, 'display', 'none');
                }
            }
            cDesk.Event.add(app, 'load', appUpdateFn);
            if (setting.appUpdateHandler) {
                this.AppUpdate = appUpdateFn; //App更新程序，用于外部调用
            }
        }());

        //公共方法
        this.AppObject = setting.appObject,
        this.AppDOM = setting.appDOM,
        this.AppValue = function (attributeName, attributeValue) {
            if (typeof (attributeName) === 'string') {
                if (attributeValue === undefined) {
                    return setting[attributeName];
                }
                else {
                    setting[attributeName] = attributeValue;
                }
            }
        }
    }

    return {
        /*获取一个AppButton实例*/
        getInstance: function (config) {
            var instance = new init(config);
            return instance;
        }
    }
};

/*桌面盒子*/
cDesk.DeskBox = function () {
    //对象实例引用
    var instance;

    //初始化
    function init(namelist) {
        //私有变量
        var deskboxName = "cDesk-DeskBox";          //分屏盒子名称
        var deskboxToolName = "cDesk-DeskBox_Tool"; //分屏工具栏名称
        var deskboxTabName = "cDesk-DeskBox_Tab";   //分屏工具按钮名称
        var appButtonWidth = 92;                    //应用盒子宽度
        var appButtonHeight = 85;                   //应用盒子高低，用于计算每个APP的left和top
        var deskList = new Array();                 //桌面集合
        var dexkboxToolDOM = null;                  //分屏栏DOM对象
        if (namelist == undefined || namelist == null || namelist == "") {
            namelist = new Array();
        };

        //创建桌面
        (function () {
            var thisdeskleft = 80; //工具栏宽度
            var thisdesktop = 35; //35是屏幕栏高度
            var taskbarheight = 50; //50是Taskbar高度
            var deskh = cDesk.Document.getClientHeight() - thisdesktop - taskbarheight;
            var deskw = cDesk.Document.getClientWidth() - thisdeskleft;
            for (var i = 0; i < namelist.length; i++) {
                var deskbox = document.createElement("div");
                deskbox.setAttribute("id", deskboxName + i.toString());
                deskbox.setAttribute("class", deskboxName);
                deskbox.setAttribute("deskindex", i);
                cDesk.Style.set(deskbox, 'left', thisdeskleft + 'px');
                cDesk.Style.set(deskbox, 'top', thisdesktop + 'px');
                cDesk.Style.set(deskbox, 'top', thisdesktop + 'px');
                cDesk.Style.set(deskbox, 'width', deskw + 'px');
                cDesk.Style.set(deskbox, 'height', deskh + 'px');
                cDesk.Event.add(deskbox, 'contextmenu', function () {
                    return false; /*禁用右键菜单*/
                });
                if (i == 0) {
                    cDesk.Style.set(deskbox, 'display', 'block');
                }
                else {
                    cDesk.Style.set(deskbox, 'display', 'none');
                }
                deskList.push(deskbox);
                document.body.appendChild(deskbox);
            }
        } ());

        //创建屏幕切换工具栏
        (function () {
            var tool = document.createElement("ul");
            tool.setAttribute("id", deskboxToolName);
            tool.setAttribute("class", deskboxToolName);
            cDesk.Style.set(tool, 'left', '400px');
            cDesk.Style.set(tool, 'height', '30px');
            cDesk.Style.set(tool, 'zIndex', 20000);
            cDesk.Drag(tool, tool, 0, 0,undefined, undefined);

            var toolleft = document.createElement("div");
            toolleft.setAttribute("class", deskboxToolName + "_left");
            tool.appendChild(toolleft);
            var toolright = document.createElement("div");
            toolright.setAttribute("class", deskboxToolName + "_right");
            tool.appendChild(toolright);

            for (var i = 0; i < namelist.length; i++) {
                (function (tabindex) {
                    var a = document.createElement("li");
                    a.innerHTML = namelist[i].toString();
                    a.setAttribute("id", deskboxTabName + i.toString());
                    a.setAttribute("class", deskboxTabName);
                    a.setAttribute("href", "javascript:/*www.cDesk.org*/");
                    cDesk.Event.add(a, 'mousedown', function (e) {
                        CutoverDesk(tabindex);
                        //阻止事件冒泡
                        e = cDesk.Common.newEvent(e);
                        e.stopPropagation();
                        e.preventDefault();
                    });
                    if (i == 0) {
                        cDesk.Style.set(a, 'color', '#000000');
                        cDesk.Style.set(a, 'background', '#00FF40');
                    }
                    tool.appendChild(a);
                })(i);
            }
            document.body.appendChild(tool);
        } ());

        //桌面切换
        function CutoverDesk(index) {
            var showboxid = deskboxName + index.toString();
            var showtabid = deskboxTabName + index.toString();
            var hidboxid = "";
            var hidtabid = "";
            for (var i = 0; i < namelist.length; i++) {
                var nowtab = document.getElementById(deskboxTabName + i.toString());
                var nowbox = document.getElementById(deskboxName + i.toString());
                var nowboxStyle = cDesk.Style.get(nowbox, 'display');
                if (nowboxStyle != 'none') {
                    hidboxid = deskboxName + i.toString();
                    hidtabid = deskboxTabName + i.toString();
                    break;
                }
            }
            if (showboxid != hidboxid) {
                if (hidboxid != "" && hidtabid != "") {
                    var hidtab = document.getElementById(hidtabid);
                    var hidbox = document.getElementById(hidboxid);
                    cDesk.Style.del(hidtab, 'background');
                    cDesk.Style.del(hidtab, 'color');
                    cDesk.Style.set(hidbox, 'display', 'none');

                    var showbox = document.getElementById(showboxid);
                    var showtab = document.getElementById(showtabid);
                    cDesk.Style.set(showtab, 'background', '#00FF40');
                    cDesk.Style.set(showtab, 'color', '#000000');
                    cDesk.Style.set(showbox, 'display', 'block');
                }
            }
        };
        //设置应用的位置
        function SetAppPosition(deskbox, appobj) {
            var apptop = 20;
            var appleft = 35;
            var xappNumsTemp = cDesk.Style.getStyleNumber(deskbox, 'width') / (appButtonWidth + appleft); /*横向可以放置APP按钮数量*/
            var xappNums = Number(xappNumsTemp.toString().split('.')[0]); /*取整*/
            var yappNumsTemp = cDesk.Style.getStyleNumber(deskbox, 'height') / (appButtonHeight + apptop); /*纵向可以放置APP按钮数量*/
            var yappNums = Number(yappNumsTemp.toString().split('.')[0]);
            var appNums = deskbox.childNodes;
            if (appNums.length >= (xappNums * yappNums)) {
                //return; /*超过桌面可放置的最大上限就不添加到桌面*/
            }

            var appColumn = Number((appNums.length / yappNums).toString().split('.')[0]); /*APP按钮放置的列号*/
            var appRow = appNums.length % yappNums; /*APP按钮放置的行号*/

            var thisleft = (appColumn * appButtonWidth) + ((appColumn + 1) * appleft);
            cDesk.Style.set(appobj.AppDOM, 'left', thisleft + 'px');
            var thistop = (appRow * appButtonHeight) + ((appRow + 1) * apptop);
            cDesk.Style.set(appobj.AppDOM, 'top', thistop + 'px');
            deskbox.appendChild(appobj.AppDOM);
        };
        //获取当前显示桌面
        function getShowDesk() {
            var desks = document.body.childNodes;
            for (var i = 0; i < desks.length; i++) {
                var node = desks[i];
                if (node.nodeType == 1) {
                    var classname = node.getAttribute("class");
                    if (node.tagName.toLowerCase() == "div" && classname == deskboxName) {
                        if (cDesk.Style.get(node, 'display') != 'none') {
                            return node;
                        }
                    }
                }
            }
        };
        //公共方法
        return {
            /*添加应用按钮到指定屏幕*/
            AddAppToDesk: function (deskIndex, appmodel) {
                var deskbox = document.getElementById(deskboxName + deskIndex.toString());
                if (typeof (deskbox) === 'object') {
                    SetAppPosition(deskbox, appmodel);
                }
            },
            /*从屏幕中移除APP*/
            RemoveAppForDesk: function (deskIndex, appmodel) {
                var deskbox = document.getElementById(deskboxName + deskIndex.toString());
                var apps = deskbox.childNodes;
                for (var i = 0; i < apps.length; i++) {
                    var app = apps[i];
                    if (appmodel && appmodel.AppDOM) {
                        if (app.setAttribute('id') == appmodel.AppDOM.setAttribute('id')) {
                            cDesk.Common.delElement(app);
                            break;
                        }
                    }
                }
            },
            /*获取当前桌面*/
            GetShowDesk: function () {
                return getShowDesk();
            },
            /*转到指定桌面*/
            GoDesk: function (index) {
                CutoverDesk(index);
            },
            /*获取桌面集合*/
            GetDeskList: deskList
        };
    }

    return {
        /*获取一个实例*/
        getInstance: function (deskNameList) {
            if (!instance) {
                instance = init(deskNameList);
            }
            return instance;
        }
    }
};

/*任务栏类*/
cDesk.Taskbar = (function () {
    //对象实例引用
    var instance;

    //实例化
    function init() {
        var taskbarName = "cDesk-Taskbar";   //任务栏名称
        var taskbarObj = this;               //当前Taskbar对象实例
        var taskbarDOM = null;               //当前任务栏DOM对象
        var taskbarLeftDOM = 0;
        var taskbarRightDOM = 0;
        var taskContentDOM = null;           //任务内容框DOM对对象

        //创建任务栏
        (function () {
            var taskbar = document.createElement("div");
            taskbar.setAttribute("id", taskbarName);
            taskbar.setAttribute("class", taskbarName);
            cDesk.Event.add(taskbar, 'contextmenu', function () { return false; }); //禁用右键菜单

            //左移动按钮
            var leftBtn = document.createElement("div");
            leftBtn.setAttribute("id", taskbarName + "_Left");
            leftBtn.setAttribute("class", taskbarName + "_Left");
            cDesk.Event.add(leftBtn, 'click', function () {
                var nowLeft = cDesk.Style.getStyleNumber(taskContentDOM, 'left');
                if (nowLeft < 0) {
                    nowLeft += 120;
                    if (nowLeft > 0) {
                        nowLeft = 0;
                    }
                }
                cDesk.Style.set(taskContentDOM, 'left', nowLeft + 'px');
            });
            taskbar.appendChild(leftBtn);

            //右移动按钮
            var rightBtn = document.createElement("div");
            rightBtn.setAttribute("id", taskbarName + "_Right");
            rightBtn.setAttribute("class", taskbarName + "_Right");
            cDesk.Event.add(rightBtn, 'click', function () {
                var nowLeft = cDesk.Style.getStyleNumber(taskContentDOM, 'left');
                var nowWidth = cDesk.Style.getStyleNumber(taskContentDOM, 'width');
                if (((nowLeft * -1) + cDesk.Document.getClientWidth()) < nowWidth) {
                    nowLeft -= 120;
                    if (((nowLeft * -1) + cDesk.Document.getClientWidth()) > nowWidth) {
                        nowLeft = nowWidth - cDesk.Document.getClientWidth();
                    }
                }
                if (nowLeft >= 0) {
                    cDesk.Style.set(taskContentDOM, 'left', (nowLeft * -1) + 'px');
                }
                else {
                    cDesk.Style.set(taskContentDOM, 'left', nowLeft + 'px');
                }
            });
            taskbar.appendChild(rightBtn);

            /*任务栏内容框*/
            var content = document.createElement("ul");
            content.setAttribute("id", taskbarName + "_Content");
            content.setAttribute("class", taskbarName + "_Content");
            taskbar.appendChild(content);

            document.body.appendChild(taskbar);
            taskbarDOM = taskbar;
            taskbarLeftDOM = leftBtn;
            taskbarRightDOM = rightBtn;
            taskContentDOM = content;
        } ());

        //添加任务到任务栏
        function AddTask(winObj) {
            if (typeof (winObj) === 'object' && typeof (winObj.WindowDOM) === 'object') {
                if (typeof (winObj.AppButton) === 'object') {
                    var appicon = winObj.AppButton.AppValue('appIcon');
                    var appname = winObj.AppButton.AppValue('appName');

                    var task = document.createElement("li");
                    task.setAttribute("id", "cDesk-Taskbar_Task_" + winObj.WindowDOM.getAttribute("id"));
                    task.setAttribute("class", "cDesk-Taskbar_Task");
                    task.setAttribute("title", appname);

                    var taskiconbox = document.createElement("div");
                    taskiconbox.setAttribute("class", "cDesk-Taskbar_Task_Icon");
                    var taskicon = document.createElement("img");
                    taskicon.setAttribute("src", appicon);
                    taskicon.setAttribute("width", "34px");
                    taskicon.setAttribute("height", "34px");
                    taskiconbox.appendChild(taskicon);
                    task.appendChild(taskiconbox);

                    var taskname = document.createElement("div");
                    taskname.setAttribute("class", "cDesk-Taskbar_Task_Name");
                    taskname.innerHTML = appname;
                    task.appendChild(taskname);

                    //任务图标点击处理
                    cDesk.Event.add(task, 'click', function () {
                        desk.GoDesk(winObj.WindowDOM.parentNode.getAttribute("deskindex"));
                        winObj.Show();
                    });
                    //任务图标右键处理
                    cDesk.Event.add(task, 'contextmenu', function (e) {
                        if (e === undefined) {
                            e = window.event;
                        }
                        e = cDesk.Common.newEvent(e);
                        RightClickMenu(e, winObj);
                        return false;
                    });

                    //动态设置任务框宽度
                    taskContentDOM.appendChild(task);
                    SettingStyle("add");
                }
            }
        }

        //验证任务是否已经添加到任务栏
        function IsAddTask(taskid) {
            var result = false;
            var tasks = taskContentDOM.childNodes;
            for (var i = 0; i < tasks.length; i++) {
                var taskdom = tasks[i];
                if (taskdom.setAttribute("id") == taskid && taskdom.tagName.toLowerCase() == 'li') {
                    result = true;
                    break;
                }
            }
            return result;
        }

        //删除任务图标
        function DeleteTask(winObj, isCallback) {
            var tasks = taskContentDOM.childNodes;
            var winId = "cDesk-Taskbar_Task_" + winObj.WindowDOM.getAttribute("id");
            for (var i = 0; i < tasks.length; i++) {
                var taskDom = tasks[i];
                if (taskDom.tagName.toLowerCase() == 'li' && taskDom.getAttribute('id') == winId) {
                    cDesk.Common.delElement(taskDom);
                    SettingStyle("del");
                    break;
                }
            }
        }

        //动态设置任务栏样式
        function SettingStyle(type) {
            var nowWidth = cDesk.Style.getStyleNumber(taskContentDOM, 'width');
            var nowLeft = cDesk.Style.getStyleNumber(taskContentDOM, 'left');
            if (type == 'add') {//添加任务
                if (nowWidth == 'auto') {
                    nowWidth = 120;
                }
                else {
                    nowWidth += 120;
                }
            }
            else if (type == 'del') {//删除任务
                nowWidth -= 120;
                nowLeft += 120;
            }
            //任务盒子位置
            if (nowLeft > 10 || nowLeft == "" || nowLeft == "auto") {
                nowLeft = 10;
            }
            if ((nowLeft * -1) > nowWidth) {
                nowLeft = nowWidth - 120;
            }
            //左右按钮控制
            if (nowWidth > cDesk.Document.getClientWidth()) {
                cDesk.Style.set(taskbarLeftDOM, 'display', 'block');
                cDesk.Style.set(taskbarRightDOM, 'display', 'block');
            }
            else {
                cDesk.Style.set(taskbarLeftDOM, 'display', 'none');
                cDesk.Style.set(taskbarRightDOM, 'display', 'none');
            }
            //设置新的位置和宽度
            cDesk.Style.set(taskContentDOM, 'width', nowWidth + 'px');
            cDesk.Style.set(taskContentDOM, 'left', nowLeft + 'px');
        }

        //右键菜单方法
        function RightClickMenu(p, winobj) {
            var rcMenuName = "cDesk-Taskbar_RightClickMenu";
            //删除已经打开的右键菜单
            var oldMenu = document.getElementById(rcMenuName);
            cDesk.Common.delElement(oldMenu);

            //创建新的右键菜单
            var menu = document.createElement("div");
            menu.setAttribute("id", rcMenuName);
            menu.setAttribute("class", rcMenuName);
            cDesk.Event.add(document, 'click', function () {
                cDesk.Common.delElement(menu);
                cDesk.Event.empty(document, 'click');
            });

            //设置位置
            var menuWidth = 120;
            var menuHeight = 90;
            var offset = 8;
            var theLeft = p.clientX + offset;
            var theTop = p.clientY - menuHeight - offset;
            if ((theLeft + menuWidth) > cDesk.Document.getClientWidth()) {
                theLeft -= (menuWidth + offset);
            }
            cDesk.Style.set(menu, 'left', theLeft + 'px');
            cDesk.Style.set(menu, 'top', theTop + 'px');

            /*添加菜单项*/
            var minItem = document.createElement("div");
            minItem.setAttribute("class", rcMenuName + "_Item");
            minItem.innerHTML = "最小化";
            cDesk.Event.add(minItem, 'click', function () {
                winobj.Minimize();
                cDesk.Common.delElement(menu);
            });
            menu.appendChild(minItem);
            var maxItem = document.createElement("div");
            maxItem.setAttribute("class", rcMenuName + "_Item");
            maxItem.innerHTML = "最大化";
            cDesk.Event.add(maxItem, 'click', function () {
                desk.GoDesk(winobj.WindowDOM.parentNode.getAttribute("deskindex"));
                winobj.Maximize();
                winobj.Show();
                cDesk.Common.delElement(menu);
            });
            menu.appendChild(maxItem);
            var closeItem = document.createElement("div");
            closeItem.setAttribute("class", rcMenuName + "_Item");
            closeItem.innerHTML = "关闭";
            cDesk.Event.add(closeItem, 'click', function () {
                winobj.Close();
                cDesk.Common.delElement(menu);
            });
            menu.appendChild(closeItem);
            document.body.appendChild(menu);
        }

        //公共方法
        return {
            //添加图标到任务栏
            AddTask: function (winObj) {
                AddTask(winObj);
            },
            //从任务栏移除任务图标，并关闭窗口
            DelTask: function (winObj) {
                DeleteTask(winObj, true);
            }
        }
    }

    //创建对象实例
    if (!instance) {
        instance = init();
    }
    return instance;
} ());

/*工具栏类*/
cDesk.Toolbar = function () {
    //对象实例引用
    var instance;

    //实例化
    function init(desk, config) {
        //默认配置参数
        var setting = {
            toolbarName: "cDesk-Toolbar",        //任务栏名称
            toolbarWidth: 60,                    //任务栏宽度
            toolbarHeight: 350,
            toolbarLeft: 0,
            toolbarTop: (cDesk.Document.getClientHeight() - 350) / 2,
            setBtn1Title: "设置1",
            setBtn2Title: "设置2",
            setBtn3Title: "设置3",
            setBtn4Title: "设置4",
            setBtn1Handler: null,
            setBtn2Handler: null,
            setBtn3Handler: null,
            setBtn4Handler: null,
            outBtnHandler: null,                 //退出按钮处理方法
            toolbarDOM: null,                    //当前工具栏DOM对象
            toolbarContentDOM: null              //工具内容框DOM对对象
        };
        //更新配置
        if (typeof (config) === 'object') {
            for (var p in config) {
                if (setting[p] !== undefined) {
                    setting[p] = config[p];
                }
            }
            setting.toolbarObj = this; //防止配置参数将其覆盖
        }

        //创建工具栏
        (function () {
            //工具栏框
            var toolbar = document.createElement("div");
            toolbar.setAttribute("id", setting.toolbarName);
            toolbar.setAttribute("class", setting.toolbarName);
            cDesk.Style.set(toolbar, 'top', (setting.toolbarTop - 50) + "px");
            cDesk.Event.add(toolbar, 'contextmenu', function (p) { return false; }); //禁用右键菜单
            cDesk.Event.add(toolbar, 'mousedown', function (ev) {/*绑定拖拽*/
                cDesk.Style.set(toolbar, 'cursor', 'move');
                cDesk.Style.set(toolbar, 'zIndex', 39999);
                var _dragMove = true;
                var doc = document;
                if (!ev) {
                    ev = window.event;
                }
                ev = cDesk.Common.newEvent(ev);
                var X = ev.clientX - document.body.scrollLeft - toolbar.offsetLeft;
                cDesk.Event.add(doc, 'mousemove', function (e) {
                    if (_dragMove) {
                        if (!e) {
                            e = window.event;
                        }
                        e = cDesk.Common.newEvent(e);
                        cDesk.Style.set(toolbar, 'left', (e.clientX + document.body.scrollLeft - X) + "px");
                    }
                });
                cDesk.Event.add(doc, 'mouseup', function () {
                    if (_dragMove) {
                        var nowwidth = cDesk.Style.getStyleNumber(toolbar, 'width');
                        var nowleft = cDesk.Style.getStyleNumber(toolbar, 'left');
                        var nowaxis = cDesk.Document.getClientWidth() / 2;
                        if (nowleft <= nowaxis) {
                            cDesk.Style.set(toolbar, 'left', '0px');
                            for (var i = 0; i < desk.GetDeskList.length; i++) {
                                var deskbox = desk.GetDeskList[i];
                                cDesk.Style.set(deskbox, 'left', (nowwidth + 20) + "px");
                            }
                        }
                        else {
                            cDesk.Style.del(toolbar, 'left');
                            cDesk.Style.set(toolbar, 'right', '0px');
                            for (var i = 0; i < desk.GetDeskList.length; i++) {
                                var deskbox = desk.GetDeskList[i];
                                cDesk.Style.set(deskbox, 'left', "0px");
                            }
                        }
                        cDesk.Style.del(toolbar, 'cursor');
                        cDesk.Style.set(toolbar, 'zIndex', 9999);
                        cDesk.Event.empty(doc, 'mousemove');
                        cDesk.Event.empty(doc, 'mouseup');
                        _dragMove = false;
                    }
                });
            });

            var toolbartop = document.createElement("div");
            toolbartop.setAttribute("class", setting.toolbarName + "_Top");
            toolbar.appendChild(toolbartop);
            var toolbarbottom = document.createElement("div");
            toolbarbottom.setAttribute("class", setting.toolbarName + "_Bottom");
            toolbar.appendChild(toolbarbottom);

            /*快捷功能图标*/
            var content = document.createElement("div");
            content.setAttribute("id", setting.toolbarName + "_Content");
            content.setAttribute("class", setting.toolbarName + "_Content");
            toolbar.appendChild(content);
            setting.toolbarContentDOM = content;

            /*桌面设置图标块*/
            var set = document.createElement("div");
            set.setAttribute("id", setting.toolbarName + "_Setting");
            set.setAttribute("class", setting.toolbarName + "_Setting");
            var setbtn1 = document.createElement("div");
            setbtn1.setAttribute("id", setting.toolbarName + "_Setting_Btn1");
            setbtn1.setAttribute("class", setting.toolbarName + "_Setting_Btn");
            setbtn1.setAttribute("title", setting.setBtn1Title);
            cDesk.Event.add(setbtn1, 'mousedown', function (e) {
                if (setting.setBtn1Handler) {
                    setting.setBtn1Handler(setbtn1);
                }
                //阻止事件冒泡
                e = cDesk.Common.newEvent(e);
                e.stopPropagation();
                e.preventDefault();
            });
            set.appendChild(setbtn1);

            var setbtn2 = document.createElement("div");
            setbtn2.setAttribute("id", setting.toolbarName + "_Setting_Btn2");
            setbtn2.setAttribute("class", setting.toolbarName + "_Setting_Btn");
            setbtn2.setAttribute("title", setting.setBtn2Title);
            cDesk.Event.add(setbtn2, 'mousedown', function (e) {
                if (setting.setBtn2Handler) {
                    setting.setBtn2Handler(setbtn2);
                }
                //阻止事件冒泡
                e = cDesk.Common.newEvent(e);
                e.stopPropagation();
                e.preventDefault();
            });
            set.appendChild(setbtn2);

            var setbtn3 = document.createElement("div");
            setbtn3.setAttribute("id", setting.toolbarName + "_Setting_Btn3");
            setbtn3.setAttribute("class", setting.toolbarName + "_Setting_Btn");
            setbtn3.setAttribute("title", setting.setBtn3Title);
            cDesk.Event.add(setbtn3, 'mousedown', function (e) {
                if (setting.setBtn3Handler) {
                    setting.setBtn3Handler(setbtn3);
                }
                //阻止事件冒泡
                e = cDesk.Common.newEvent(e);
                e.stopPropagation();
                e.preventDefault();
            });
            set.appendChild(setbtn3);

            var setbtn4 = document.createElement("div");
            setbtn4.setAttribute("id", setting.toolbarName + "_Setting_Btn4");
            setbtn4.setAttribute("class", setting.toolbarName + "_Setting_Btn");
            setbtn4.setAttribute("title", setting.setBtn4Title);
            setbtn4.setAttribute("state", "none");
            cDesk.Event.add(setbtn4, 'mousedown', function (e) {
                if (setting.setBtn4Handler) {
                    setting.setBtn4Handler(setbtn4);
                }
                //阻止事件冒泡
                e = cDesk.Common.newEvent(e);
                e.stopPropagation();
                e.preventDefault();
            });
            set.appendChild(setbtn4);
            toolbar.appendChild(set);

            /*退出按钮*/
            var outbutton = document.createElement("div");
            outbutton.setAttribute("id", setting.toolbarName + "_Outbutton");
            outbutton.setAttribute("class", setting.toolbarName + "_Outbutton");
            outbutton.setAttribute("title", "退出系统");
            cDesk.Event.add(outbutton, 'mousedown', function (e) {
                if (setting.outBtnHandler) {
                    setting.outBtnHandler();
                }
                //阻止事件冒泡
                e = cDesk.Common.newEvent(e);
                e.stopPropagation();
                e.preventDefault();
            });
            toolbar.appendChild(outbutton);
            document.body.appendChild(toolbar);
        } ());

        //添加APP到工具栏
        function addApp(appbutton) {
            var appname = setting.toolbarName + "_Content_App";
            var applist = setting.toolbarContentDOM.childNodes;
            if (applist.length < 5) {
                var app = document.createElement("div");
                app.setAttribute("id", appname + "_" + applist.length);
                app.setAttribute("class", appname);
                app.setAttribute("title", appbutton.AppValue("appName"));
                var top = 0;
                if (applist.length > 0) {
                    top = applist.length * 45;
                }
                cDesk.Style.set(app, 'top', top + "px");
                cDesk.Event.add(app, 'click', function () {
                    if (appbutton.AppValue('appClickHandle')) {
                        appbutton.AppValue('appClickHandle')(appbutton);
                    }
                });

                var appicon = document.createElement("img");
                appicon.setAttribute("class", appname + "_Icon");
                appicon.setAttribute("src", appbutton.AppValue("appIcon"));
                app.appendChild(appicon);
                setting.toolbarContentDOM.appendChild(app);
            }
        }

        /*添加一个AppButton到任务栏*/
        this.AddAppbutton = function (appbotton) {
            addApp(appbotton);
        }
    }

    return {
        /*获取一个对象实例*/
        getInstance: function (desk, setting) {
            if (!instance) {
                instance = new init(desk, setting);
            }
            return instance;
        }
    }
};

/*桌面窗口类*/
cDesk.Windows = function () {
    function init(desk, appbutton, config) {
        //默认配置参数
        var setting = {
            windowName: "cDesk-Windows",
            windowTitle: "新窗口",
            windowWidth: 850,
            windowHeight: 500,
            ShwoHome: true,
            ShowRefresh: true,               //是否显示刷新按钮
            ShowMinimize: true,              //是否显示最小化按钮
            ShowMaximize: true,              //是否显示最大化按钮
            ShowClose: true,                 //是否显示关闭按钮
            MinimizeCallback: null,          //最小化后的回调方法
            MaximizeCallback: null,          //最大化后的回调方法
            ReductionCallback: null,         //还原后的回调方法
            CloseCallback: null,             //关闭后的回调方法
            Move: true,                      //窗口是否可以移动
            Lock: false,                     //是否开启锁屏
            Resize: true,                    //是否允许调整窗口大小
            windowObj: this,                 //当前对象实例
            windowDOM: null,                 //当前窗口DOM对象
            maximizeBtn: null,               //最大化按钮
            reductionBtn: null               //还原按钮
        };
        //更新配置参数
        if (typeof (config) === 'object') {
            for (var p in config) {
                if (setting[p] !== undefined) {
                    setting[p] = config[p];
                }
            }
            setting.windowObj = this; //防止配置参数将其覆盖
        }

        //初始化方法
        var deskbox = null;
        var appid = null;
        var appurl = null;
        var appname = null;
        var apptype = null;
        var MyCopyright = "javascript:/*www.cdesk.org*/";
        var IndexName = "winZIndex";
        var isopened = false; //App是否打开标识
        (function () {
            if (typeof (desk) !== 'object') {
                return;
            }
            if (typeof (appbutton) !== 'object') {
                return;
            }
            deskbox = desk.GetShowDesk();
            appid = appbutton.AppDOM.getAttribute("id");
            appurl = appbutton.AppValue("appUrl");
            appname = appbutton.AppValue("appName");
            apptype = appbutton.AppValue("appType");

            //APP没有打开才创建窗口
            if (!IsOpenedApp()) {
                if (appbutton) {
                    if (apptype == "outapp") {
                        window.open(appurl);
                        return; //外部应用直接在新浏览器窗口中打开
                    }
                    setting.windowTitle = appname;
                }

                //设置打开标识
                appbutton.AppDOM.setAttribute("deskboxIndex", deskbox.getAttribute("deskindex"));
                CreateWindow();
            }
            else {
                isopened = true;
            }
        } ());

        //验证APP是否已经打开
        function IsOpenedApp() {
            var result = false;
            var deskIndex = appbutton.AppDOM.getAttribute("deskboxIndex");
            var oldDeskbox = document.getElementById("cDesk-DeskBox" + deskIndex);
            if (oldDeskbox) {
                result = true;
                deskbox = oldDeskbox;
                var openedApps = deskbox.childNodes;
                for (var i = 0; i < openedApps.length; i++) {
                    var openapp = openedApps[i];
                    if (openapp.getAttribute("class").toString() == setting.windowName) {
                        if (openapp.getAttribute("appid") == appid) {
                            //如果已经打开APP就显示在最上面
                            var nowZindex = Number(deskbox.getAttribute("windZIndex"));
                            var meZindex = cDesk.Style.get(openapp, 'zIndex');
                            if (meZindex < nowZindex) {
                                meZindex = nowZindex + 1;
                                cDesk.Style.set(openapp, 'zIndex', meZindex);
                                deskbox.setAttribute("windZIndex", meZindex);
                            }
                            cDesk.Style.set(openapp, 'display', 'block');
                            desk.GoDesk(deskIndex); /*转到对应的桌面*/
                            break;
                        }
                    }
                }
            }
            return result;
        }

        //创建并打开窗口
        function CreateWindow() {
            var baseZIndex = 10000;
            //设置窗口Zindex
            if (!deskbox.getAttribute(IndexName)) {
                deskbox.setAttribute(IndexName, baseZIndex);
            }
            var maxleft = cDesk.Document.getClientWidth() - 200;
            var maxtop = cDesk.Document.getClientHeight() - 200;
            var winZIndex = Number(deskbox.getAttribute(IndexName)) + 1;
            var win = document.createElement("div");
            var winId = setting.windowName + Math.random();
            win.setAttribute("id", winId);
            win.setAttribute("class", setting.windowName);
            win.setAttribute("name", setting.windowName); /*方便查找*/
            win.setAttribute("appid", appid);
            cDesk.Style.set(win, 'zIndex', winZIndex);
            cDesk.Style.set(win, 'width', setting.windowWidth + "px");
            cDesk.Style.set(win, 'height', setting.windowHeight + "px");
            cDesk.Style.set(win, 'left', ((GetWindowsNum() * 30) % maxleft) + "px"); //设置窗口默认位置
            cDesk.Style.set(win, 'top', ((GetWindowsNum() * 40) % maxtop) + "px");

            //东方位框
            var winEastBox = document.createElement("div");
            winEastBox.setAttribute("id", winId + "_East");
            winEastBox.setAttribute("class", setting.windowName + "_East");
            win.appendChild(winEastBox);
            //南方位框
            var winSouthBox = document.createElement("div");
            winSouthBox.setAttribute("id", winId + "_South");
            winSouthBox.setAttribute("class", setting.windowName + "_South");
            win.appendChild(winSouthBox);
            //西方位框
            var winWesternBox = document.createElement("div");
            winWesternBox.setAttribute("id", winId + "_Western");
            winWesternBox.setAttribute("class", setting.windowName + "_Western");
            win.appendChild(winWesternBox);
            //北方位框
            var winNorthBox = document.createElement("div");
            winNorthBox.setAttribute("id", winId + "_North");
            winNorthBox.setAttribute("class", setting.windowName + "_North");
            win.appendChild(winNorthBox);
            var winWesternTopBox = document.createElement("div");
            winWesternTopBox.setAttribute("id", winId + "_Westerntop");
            winWesternTopBox.setAttribute("class", setting.windowName + "_Westerntop");
            win.appendChild(winWesternTopBox);
            var winEastTopBox = document.createElement("div");
            winEastTopBox.setAttribute("id", winId + "_Easttop");
            winEastTopBox.setAttribute("class", setting.windowName + "_Easttop");
            win.appendChild(winEastTopBox);
            //东北方位框
            var winNortheastBox = document.createElement("div");
            winNortheastBox.setAttribute("id", winId + "_Northeast");
            winNortheastBox.setAttribute("class", setting.windowName + "_Northeast");
            win.appendChild(winNortheastBox);
            //东南方位框
            var winSoutheastBox = document.createElement("div");
            winSoutheastBox.setAttribute("id", winId + "_Southeast");
            winSoutheastBox.setAttribute("class", setting.windowName + "_Southeast");
            win.appendChild(winSoutheastBox);
            //西南方位框
            var winNorthwestBox = document.createElement("div");
            winNorthwestBox.setAttribute("id", winId + "_Southwest");
            winNorthwestBox.setAttribute("class", setting.windowName + "_Southwest");
            win.appendChild(winNorthwestBox);
            //西北方位框
            var winSouthwestBox = document.createElement("div");
            winSouthwestBox.setAttribute("id", winId + "_Northwest");
            winSouthwestBox.setAttribute("class", setting.windowName + "_Northwest");
            win.appendChild(winSouthwestBox);

            //标题栏
            var winTitle = document.createElement("div");
            winTitle.setAttribute("id", winId + "_Title");
            winTitle.setAttribute("class", setting.windowName + "_Title");
            //标题栏中的标题框
            var TitleBox = document.createElement("div");
            TitleBox.setAttribute("class", setting.windowName + "_TitleBox");
            TitleBox.innerHTML = setting.windowTitle;
            winTitle.appendChild(TitleBox);
            //标题栏中的常规按钮框
            var FunButtonBox = document.createElement("div");
            FunButtonBox.setAttribute("class", setting.windowName + "_ButtonBox");
            //主页按钮
            var btnHome = document.createElement("a");
            btnHome.setAttribute("id", setting.windowName + "_Homebtn");
            btnHome.setAttribute("class", setting.windowName + "_Homebtn");
            btnHome.setAttribute("href", MyCopyright);
            btnHome.setAttribute("title", "主页");
            //btnHome.innerHTML = "主";
            cDesk.Event.add(btnHome, 'click', function () { HomeHandler(btnHome); });
            if (!setting.ShwoHome) {
                cDesk.Style.set(btnHome, 'display', 'none');
            }
            FunButtonBox.appendChild(btnHome);
            //刷新按钮
            var btnRefresh = document.createElement("a");
            btnRefresh.setAttribute("id", setting.windowName + "_Refreshbtn");
            btnRefresh.setAttribute("class", setting.windowName + "_Refreshbtn");
            btnRefresh.setAttribute("href", MyCopyright);
            btnRefresh.setAttribute("title", "刷新");
            //btnRefresh.innerHTML = "刷";
            cDesk.Event.add(btnRefresh, 'click', function () { RefreshHandler(btnRefresh); });
            if (!setting.ShowRefresh) {
                cDesk.Style.set(btnRefresh, 'display', 'none');
            }
            FunButtonBox.appendChild(btnRefresh);
            //最小化按钮
            var btnMinimize = document.createElement("a");
            btnMinimize.setAttribute("id", setting.windowName + "_Minimizebtn");
            btnMinimize.setAttribute("class", setting.windowName + "_Minimizebtn");
            btnMinimize.setAttribute("href", MyCopyright);
            btnMinimize.setAttribute("title", "最小化");
            //btnMinimize.innerHTML = "小";
            cDesk.Event.add(btnMinimize, 'click', function () { MinimizeHandler(btnMinimize); });
            if (!setting.ShowMinimize) {
                cDesk.Style.set(btnMinimize, 'display', 'none');
            }
            FunButtonBox.appendChild(btnMinimize);
            //最大化按钮
            var btnMaximize = document.createElement("a");
            btnMaximize.setAttribute("id", setting.windowName + "_Maximizebtn");
            btnMaximize.setAttribute("class", setting.windowName + "_Maximizebtn");
            btnMaximize.setAttribute("href", MyCopyright);
            btnMaximize.setAttribute("title", "最大化");
            btnMaximize.setAttribute("winZIndex", 999999); /*最大化时窗口的Z序*/
            //btnMaximize.innerHTML = "大";
            cDesk.Event.add(btnMaximize, 'click', function () { MaximizeHandler(); });
            if (!setting.ShowMaximize) {
                cDesk.Style.set(btnMaximize, 'display', 'none');
            }
            setting.maximizeBtn = btnMaximize;
            FunButtonBox.appendChild(btnMaximize);
            //还原按钮
            var btnReduction = document.createElement("a");
            btnReduction.setAttribute("id", setting.windowName + "_Reductionbtn");
            btnReduction.setAttribute("class", setting.windowName + "_Reductionbtn");
            btnReduction.setAttribute("href", MyCopyright);
            btnReduction.setAttribute("title", "还原");
            btnReduction.setAttribute("winZIndex", winZIndex); /*还原时保持窗口原来的宽、高和Z序*/
            btnReduction.setAttribute("winWidth", cDesk.Style.get(win, 'width'));
            btnReduction.setAttribute("winHeight", cDesk.Style.get(win, 'height'));
            //btnReduction.innerHTML = "原";
            setting.reductionBtn = btnReduction;
            cDesk.Event.add(btnReduction, 'click', function () { ReductionHandler(); });
            FunButtonBox.appendChild(btnReduction);
            //关闭按钮
            var btnClose = document.createElement("a");
            btnClose.setAttribute("id", setting.windowName + "_Closebtn");
            btnClose.setAttribute("class", setting.windowName + "_Closebtn");
            btnClose.setAttribute("href", MyCopyright);
            btnClose.setAttribute("title", "关闭");
            //btnClose.innerHTML = "关";
            cDesk.Event.add(btnClose, 'click', function () { CloseHandler(btnClose); });
            if (!setting.ShowClose) {
                cDesk.Style.set(btnClose, 'display', 'none');
            }
            FunButtonBox.appendChild(btnClose);
            winTitle.appendChild(FunButtonBox);
            win.appendChild(winTitle);

            //创建内容窗口
            var winContent = document.createElement("div");
            winContent.setAttribute("class", setting.windowName + "_Content");
            var winContentIframe = document.createElement("iframe");
            winContentIframe.setAttribute("frameBorder", "no");
            winContentIframe.setAttribute("scrolling", "auto");
            winContentIframe.setAttribute("src", appurl);
            winContentIframe.setAttribute("id", setting.windowName + "_Content_Iframe_" + winZIndex);
            winContentIframe.setAttribute("class", setting.windowName + "_Content_Iframe");
            winContent.appendChild(winContentIframe);
            win.appendChild(winContent);
            deskbox.appendChild(win);
            deskbox.setAttribute(IndexName, winZIndex);

            //主页事件处理方法
            function HomeHandler(btn) {
                winContentIframe.setAttribute("src", winContentIframe.getAttribute("src"));
            }
            //刷新事件处理方法
            function RefreshHandler(btn) {
                try {
                    if (winContentIframe.contentDocument) {
                        var newurl = winContentIframe.contentDocument.URL;
                        winContentIframe.setAttribute("src", newurl);
                    }
                }
                catch (e) {
                    //跨域访问网页时，winContentIframe.contentDocument被禁用
                }
            }
            setting.windowDOM = win;

            //支持拖拽
            if (setting.Move === true) {
                cDesk.Drag(win, win, -300, 0, undefined, 400);
            }
            //选择后，窗体显示在最上面
            cDesk.Event.add(win, 'click', function () {
                var maxZindex = Number(deskbox.getAttribute(IndexName));
                var meZindex = cDesk.Style.get(win, 'zIndex');
                if (meZindex < maxZindex) {
                    meZindex = maxZindex + 1;
                    cDesk.Style.set(win, 'zIndex', meZindex);
                    deskbox.setAttribute(IndexName, meZindex);
                }
            });
        }

        //获取窗口数量
        function GetWindowsNum() {
            var num = 0;
            var somList = deskbox.childNodes;
            for (var i = 0; i < somList.length; i++) {
                var som = somList[i];
                if (som.nodeType == 1) {
                    if (som.tagName.toLocaleLowerCase() === "div" && som.getAttribute("class") === setting.windowName) {
                        num += 1;
                    }
                }
            }
            num += 1;
            return num;
        }

        //显示窗口
        function ShowWindows() {
            var maxZindex = Number(deskbox.getAttribute(IndexName));
            var meZindex = Number(cDesk.Style.get(setting.windowDOM, 'zIndex'));
            if (meZindex < maxZindex) {
                meZindex = maxZindex + 1;
                cDesk.Style.set(setting.windowDOM, 'zIndex', meZindex);
                deskbox.setAttribute(IndexName, meZindex);
            }
            cDesk.Style.set(setting.windowDOM, 'display', 'block');
        }
        //最小化
        function MinimizeHandler(btn) {
            cDesk.Style.set(setting.windowDOM, 'display', 'none');
            if (setting.MinimizeCallback) {
                setting.MinimizeCallback(setting.windowObj);
            }
        }
        //最大化
        function MaximizeHandler() {
            var oldleft = cDesk.Style.get(setting.windowDOM, 'left');
            var oldtop = cDesk.Style.get(setting.windowDOM, 'top');
            setting.reductionBtn.setAttribute("winLeft", oldleft);
            setting.reductionBtn.setAttribute("winTop", oldtop);
            var maxZindex = setting.maximizeBtn.getAttribute("winZIndex");
            cDesk.Style.set(setting.windowDOM, 'zIndex', maxZindex);
            cDesk.Style.set(setting.windowDOM, 'width', cDesk.Document.getClientWidth() - 10 + "px");
            cDesk.Style.set(setting.windowDOM, 'height', cDesk.Document.getClientHeight() - 10 + "px");
            cDesk.Style.set(setting.windowDOM, 'top', '5px');
            cDesk.Style.set(setting.windowDOM, 'left', '5px');
            cDesk.Style.set(setting.maximizeBtn, 'display', 'none');
            cDesk.Style.set(setting.reductionBtn, 'display', 'block');
            if (setting.MaximizeCallback) {
                setting.MaximizeCallback(setting.windowObj);
            }
        }
        //还原
        function ReductionHandler() {
            var newIndex = setting.reductionBtn.getAttribute("winZIndex");
            var newW = setting.reductionBtn.getAttribute("winWidth");
            var newH = setting.reductionBtn.getAttribute("winHeight");
            var newL = setting.reductionBtn.getAttribute("winLeft");
            var newT = setting.reductionBtn.getAttribute("winTop");
            cDesk.Style.set(setting.windowDOM, 'zIndex', newIndex);
            cDesk.Style.set(setting.windowDOM, 'width', newW);
            cDesk.Style.set(setting.windowDOM, 'height', newH);
            cDesk.Style.set(setting.windowDOM, 'left', newL);
            cDesk.Style.set(setting.windowDOM, 'top', newT);
            cDesk.Style.set(setting.reductionBtn, 'display', 'none');
            cDesk.Style.set(setting.maximizeBtn, 'display', 'block');
            if (setting.ReductionCallback) {
                setting.ReductionCallback(setting.windowObj);
            }
        }
        //关闭
        function CloseHandler(btn) {
            cDesk.Style.set(setting.windowDOM, 'display', 'none');
            appbutton.AppDOM.removeAttribute("deskboxIndex");
            if (setting.CloseCallback) {
                setting.CloseCallback(setting.windowObj);
            }
            cDesk.Common.delElement(setting.windowDOM);
            setting.windowDOM = undefined;
            setting.windowObj = undefined;
        }

        //公共方法，如果APP已经打开，就返回一个未设置值
        if (!isopened) {
            return {
                AppButton: appbutton,
                WindowObj: setting.windowObj,
                WindowDOM: setting.windowDOM,
                Reduction: ReductionHandler,
                Minimize: MinimizeHandler,
                Maximize: MaximizeHandler,
                Close: CloseHandler,
                Show: ShowWindows
            }
        }
        else {
            return undefined;
        }
    }

    return {
        /*获取一个Windows实例*/
        getInstance: function (desk, appbutton, config) {
            var instance = init(desk, appbutton, config);
            return instance;
        }
    }
};

/*主题类*/
cDesk.Themes = function () {
    //对象实例引用
    var instance;

    //实例化
    function init() {
        //默认配置
        var setting = {
            Name: "cDesk-ThemeBox",
            Width: 550,
            Height: 485,
            That: this,
            ThatDOM: null,
            ContentDOM: null
        };

        //创建主题盒子
        (function () {
            var themebox = document.createElement("div");
            themebox.setAttribute("id", setting.Name);
            themebox.setAttribute("class", setting.Name);
            var left = (cDesk.Document.getClientWidth() - setting.Width) / 2 + 'px';
            var top = (cDesk.Document.getClientHeight() - setting.Height) / 2 + 'px';
            cDesk.Style.set(themebox, 'left', left);
            cDesk.Style.set(themebox, 'top', top);

            var title = document.createElement("div");
            title.setAttribute("id", setting.Name + "_Title");
            title.setAttribute("class", setting.Name + "_Title");
            title.innerHTML = "主题设置";

            var close = document.createElement("div");
            close.setAttribute("id", setting.Name + "_Close");
            close.setAttribute("class", setting.Name + "_Close");
            close.setAttribute("title", "关闭");
            cDesk.Event.add(close, 'click', function (e) {
                themebox.parentNode.removeChild(themebox);
                cDesk.Common.delElement(themebox);
                setting.That = null;
                setting.ThatDOM = null;
                setting.ContentDOM = null;
            });
            title.appendChild(close);
            themebox.appendChild(title);

            var content = document.createElement("div");
            content.setAttribute("id", setting.Name + "_Content");
            content.setAttribute("class", setting.Name + "_Content");
            var contentBox = document.createElement("div");
            contentBox.setAttribute("id", setting.Name + "_ContentBox");
            contentBox.setAttribute("class", setting.Name + "_ContentBox");
            content.appendChild(contentBox);
            themebox.appendChild(content);

            setting.ContentDOM = contentBox;
            setting.ThatDOM = themebox;
            document.body.appendChild(themebox);
            cDesk.Drag(title, themebox, 0, 0, 1200, 500);
        } ());

        //添加主题到盒子
        function addTheme(config) {
            var _setting = {
                themeName: '默认主题',
                themeIcon: '',
                themeImage: '',
                themeCss: '',
                setCallback: null
            };
            //更新配置参数
            if (typeof (config) === 'object') {
                for (var p in config) {
                    if (_setting[p] !== undefined) {
                        _setting[p] = config[p];
                    }
                }
            }
            var IdName = "cDesk-Theme";
            var theme = document.createElement("div");
            theme.setAttribute("id", IdName + setting.ContentDOM.childNodes.length);
            theme.setAttribute("class", IdName);
            theme.setAttribute("title", "点击设置为主题");
            var themeTitle = document.createElement("div");
            themeTitle.setAttribute("class", IdName + "_Title");
            themeTitle.innerHTML = _setting.themeName;
            theme.appendChild(themeTitle);
            var themeContent = document.createElement("div");
            themeContent.setAttribute("id", IdName + "_Content");
            themeContent.setAttribute("class", IdName + "_Content");
            var themeImg = document.createElement("img");
            themeImg.setAttribute("class", IdName + "_Img");
            themeImg.setAttribute("src", _setting.themeIcon);
            themeContent.appendChild(themeImg);
            theme.appendChild(themeContent);
            cDesk.Event.add(theme, 'click', function (e) {
                var backimg = new Image();
                backimg.src = _setting.themeImage;
                cDesk.Event.add(backimg, 'load', function () {
                    cDesk.Style.set(document.body, "background", "url('" + _setting.themeImage + "')");
                });
                if (_setting.setCallback) {
                    _setting.setCallback(_setting);
                }
            });
            setting.ContentDOM.appendChild(theme);
        };

        //公共方法
        return {
            AddTheme: function (config) { addTheme(config); },
            ThemeboxObj: setting.That,
            ThemeboxDOM: setting.ThatDOM
        }
    };

    return {
        /*获取一个对象实例*/
        getInstance: function () {
            if (!instance) {
                instance = init();
            }
            return instance;
        }
    }
};

/*挂件类*/
cDesk.Pendant = function () {
    function init(desk, config) {
        //默认配置参数
        var setting = {
            pendantUrl: "",                //URL
            pendantName: "cDesk-Pendant",  //名称
            pendantLeft: 9999,             //大于屏幕宽度就向右对齐，小于0就左对齐
            pendantTop: -1,                //大于屏幕高度就向下对齐，小于0就顶对齐
            type: "part",                  //局部:part,全局:global
            data: null,                    //关联数据
            isClose: true,                 //是否可关闭
            isMove: true,                  //是否可移动
            moveCallback: null,            //移动后的回调方法
            closeCallback: null,           //关闭后的回调方法
            pendantDOM: null
        };
        //更新配置参数
        if (typeof (config) === 'object') {
            for (var p in config) {
                if (setting[p] !== undefined) {
                    setting[p] = config[p];
                }
            }
            setting.pendantList = new Array();
        }

        //创建挂件
        (function () {
            var deskbox = desk.GetShowDesk();
            var maxleft = cDesk.Document.getClientWidth() - 10;
            var maxtop = cDesk.Document.getClientHeight() - 20;
            var pendantWidth = 0;
            var pendantHeight = 0;
            var className = "cDesk-Pendant";
            var pendant = document.createElement("div");
            var pendantId = className + Math.random();
            pendant.setAttribute("id", pendantId);
            pendant.setAttribute("class", className);
            var pendantIframe = document.createElement("iframe");
            pendantIframe.setAttribute("class", className + "_Iframe");
            pendantIframe.setAttribute("frameBorder", "no");
            pendantIframe.setAttribute("scrolling", "auto");
            pendantIframe.setAttribute("src", setting.pendantUrl);

            //设置挂件位置
            SetLeftTop();
            function SetLeftTop() {
                if (setting.pendantLeft < 0) {
                    setting.pendantLeft = 0;
                }
                else if (setting.pendantLeft > (maxleft - pendantWidth)) {
                    setting.pendantLeft = maxleft - pendantWidth;
                }
                cDesk.Style.set(pendant, 'left', setting.pendantLeft + 'px');

                if (setting.pendantTop < 5) {
                    setting.pendantTop = 5;
                }
                else if (setting.pendantTop > (maxtop - pendantHeight)) {
                    setting.pendantTop = maxtop - pendantHeight;
                }
                cDesk.Style.set(pendant, 'top', setting.pendantTop + 'px');
            };
            pendant.appendChild(pendantIframe);

            //创建挂件外框
            var pendantTopbox = document.createElement("div");
            pendantTopbox.setAttribute("id", pendantId + "_Top");
            pendantTopbox.setAttribute("class", className + "_Top");
            cDesk.Event.add(pendant, 'mouseover', function () {
                cDesk.Style.set(pendantTopbox, 'display', 'block');
            });
            cDesk.Event.add(pendant, 'mouseout', function () {
                cDesk.Style.set(pendantTopbox, 'display', 'none');
            });
            cDesk.Event.add(pendantIframe, 'load', function () {
                //自动适应内容宽高
                if (pendantIframe.contentDocument && pendantIframe.contentDocument.body) {
                    pendantWidth = Math.max(pendantIframe.contentDocument.body.scrollWidth,
                                            pendantIframe.contentDocument.documentElement.scrollWidth);
                    pendantHeight = Math.max(pendantIframe.contentDocument.body.scrollHeight,
                                            pendantIframe.contentDocument.documentElement.scrollHeight);
                    cDesk.Style.set(pendant, 'width', pendantWidth + "px");
                    cDesk.Style.set(pendant, 'height', pendantHeight + "px");
                    SetLeftTop();
                    //支持移动挂件
                    if (setting.isMove) {
                        var maxleft = cDesk.Document.getClientWidth() - pendantWidth;
                        var maxtop = cDesk.Document.getClientHeight() - pendantHeight;
                        cDesk.Drag(pendantTopbox, pendant, 0, 0, maxleft, maxtop);
                    }
                }
            });
            /*关闭按钮*/
            var pendantClosebtn = document.createElement("div");
            pendantClosebtn.setAttribute("id", pendantId + "_Close");
            pendantClosebtn.setAttribute("class", className + "_Close");
            pendantClosebtn.setAttribute("title", "移除挂件");
            if (setting.isClose) {
                cDesk.Style.set(pendantClosebtn, 'display', 'block');
                cDesk.Event.add(pendantClosebtn, 'click', function () {
                    cDesk.Common.delElement(pendant);
                    pendant = null;
                    setting.pendantDOM = null;
                    setting.pendantObj = null;
                    if (setting.closeCallback) {
                        setting.closeCallback(setting.data);
                    }
                });
            }
            else {
                cDesk.Style.set(pendantClosebtn, 'display', 'none');
            }
            pendantTopbox.appendChild(pendantClosebtn);
            pendant.appendChild(pendantTopbox);
            setting.pendantDOM = pendant;

            //全局挂件在每个桌面都可见，局部挂件只能在指定桌面可见
            if (setting.type == "global") {
                document.body.appendChild(pendant);
            }
            else {
                deskbox.appendChild(pendant);
            }
        } ());

        //公共方法
        return {
            PendantDOM: setting.pendantDOM,
            PendantLeft: setting.pendantLeft,
            PendantTop: setting.pendantTop,
            Data: setting.data
        }
    }

    return {
        /*获取一个对象实例*/
        getInstance: function (desk, config) {
            var instance = init(desk, config);
            return instance;
        }
    }
};