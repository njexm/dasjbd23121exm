/**
 * 工具脚本
 */
var Utils = function() {
	return {
		/**
		 * 获取根路径
		 */
		getBasePath : function() {
			var curWwwPath = window.document.location.href;
			var pathName = window.document.location.pathname;
			var pos = curWwwPath.indexOf(pathName);
			var localhostPaht = curWwwPath.substring(0, pos);
			return (localhostPaht + "/");
		},
		/**
		 * 获取项目根路径
		 */
		getRootPath : function() {
			var curWwwPath = window.document.location.href;
			var pathName = window.document.location.pathname;
			var pos = curWwwPath.indexOf(pathName);
			var localhostPaht = curWwwPath.substring(0, pos);
			var projectName = pathName
					.substring(0, pathName.substr(1).indexOf('/') + 1);
			return (localhostPaht + projectName);
		},
		/**
		 * JS 取消冒泡事件 兼容火狐IE
		 */
		stopPropagation : function(e) {
			if (e && e.stopPropagation) {
				// W3C取消冒泡事件
				// e.preventDefault();
				e.stopPropagation();
			} else {
				// IE取消冒泡事件
				window.event.cancelBubble = true;
			}
		},
		/**
		 * 判断字符长度
		 */
		getByteLen : function(val) {
			var len = 0;
		    for (var i = 0; i < val.length; i++) {
				var length = val.charCodeAt(i);
				if (length >= 0 && length <= 128) {
				    len += 1;
				} else {
				    len += 2;
				}
		    }
			return len;
		},
		/**
		 * 设置宽度和高度自适应
		 */
		resizedGrid : function(div_id) {
			var height = $(window).height() - 20;
			$("#"+div_id).datagrid('resize', {
				width : function() {
					return document.body.clientWidth * 0.9;
				},
				height : height
			});
		}
		
		
	};
}();

String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {
	if (!RegExp.prototype.isPrototypeOf(reallyDo)) {
		return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi" : "g")),
				replaceWith);
	} else {
		return this.replace(reallyDo, replaceWith);
	}
};


jQuery(function($){
    // 备份jquery的ajax方法  
    var _ajax=$.ajax;
    // 重写ajax方法，先判断登录在执行success函数 
    $.ajax=function(opt){
    	var _success = opt && opt.success || function(a, b){};
    	var _error = opt && opt.error || function(a, b){};  
        var _opt = $.extend(opt, {
        	success:function(data, textStatus){
        		// 如果后台将请求重定向到了登录页，则data里面存放的就是登录页的源码，这里需要找到data是登录页的证据(标记)
                if(data){
                    if (!data.success && data.isLoginRequired) {
                       //window.location.href= Globals.ctx + "/login.action";
                       alert("请重新登录!");
        				return;
                    } else {
                       _success(data, textStatus);
                    }
                } 
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                // 错误方法增强处理
                if(XMLHttpRequest.responseText!=null&&XMLHttpRequest.responseText.indexOf("isLoginRequired")!=-1)
                {
	                 alert("操作超时，请重新登录!"); 
	                 window.location.href= Utils.getRootPath() + "/login.jsp";
	                 return;
                }
                else
                { 
               		 _error(XMLHttpRequest, textStatus, errorThrown);
                }
               
            } 
        });
        _ajax(_opt);
    };
});
 