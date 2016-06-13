function login(turnurl){
	var name = $("input[name=name]").val();
	var password = $("input[name=password]").val();
	if(name==""){
		$.messager.alert("提示","请输入用户名","info");
		return ;
	}
	if(password==""){
		$.messager.alert("提示","请输入密码","info");
		return ;
	}
	var url = Utils.getRootPath() + '/exm/login/login';
	$.ajax({
		type : "post",
		url : url,
		data : $('#loginForm').serialize(),
		async : false,
		dataType : 'json',
		success : function(result) {
			if(result.result){
				var url = turnurl ;
				window.location.href = url;
			}else{
				$.messager.alert("提示","登录失败，请检查用户名、密码是否正确","info");
			}
		}
	});
}

var exm = {
		login : function(rootUrl) {
			var checked = document.getElementById("checked").checked;
			var name = $("input[name=name]").val();
			var password = $("input[name=password]").val();
			if (checked == true) {//复选框是否被选中
				document.cookie="name="+name;
				document.cookie="password="+password;
				}
			$('#loginForm').form('submit', {
				url : rootUrl + '/login/login?checked='+checked,
				onSubmit : function() {
					if(name==""){
						$.messager.alert("提示","请输入用户名","info");
						return false;
					}
					if(password==""){
						$.messager.alert("提示","请输入密码","info");
						return false;
					}
					return true;
				},
				success : function(result) {
					result = $.parseJSON(result);
					if(result.result){
						//TODO 操作成功后的操作
						var url = rootUrl + "/index";
						window.location.href = url;
					}else{
						//TODO 操作失败后的操作
						$.messager.alert("提示","登录失败，请检查用户名、密码是否正确","info");
					}
				}
			});
		},
		reset : function() {
			var url = Utils.getRootPath() + "/views/login/login";
			window.location.href = url;
		}
	};

$(function(){
	var cookies = document.cookie;
	var cookie = [];
	cookie = cookies.split(';');
	var userName = "";
	var userPwd = "";
	if(cookies.length >= 0){
		for(var i=0;i<cookie.length;i++){
			if(cookie[i].indexOf("userName") == 1){
				userName = cookie[i].replace(" userName=","");
				$("#inputUsername").val(userName);
			}
			if(cookie[i].indexOf("userPwd") == 1){
				userPwd = cookie[i].replace(" userPwd=","");
				$("#inputPassword").val(userPwd);
			}
		}
	}
});


