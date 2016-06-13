//添加
function add(url,title,x,y)
{
	window.parent.initAdd(url,title,x,y);
}

//初始化行政区划 ---根据id赋值
function initArea(areaId)
{
	$.ajax({
		type : "get",
		url : Utils.getRootPath() + '/area/initArea', 
		data : {
		},
		async : false,
		dataType : 'json',
		success : function(result) {
			if(result.status == "ERROR"){
				$.messager.alert('提示', '行政区划初始化失败', 'error');
			}else if(result.status == "OK")
			{
				if(result.message.length>0)
				{
					for(var i = 0;i<result.message.length;i++)
					{
						$("#"+areaId).append("<option value=" + result.message[i].id + ">" +  result.message[i].areaName + "</option>");
					}
				}
				
			}
			
		}

	});
}
function initNjArea(areaId)
{
	$.ajax({
		type : "get",
		url : Utils.getRootPath() + '/area/listCountryJson', 
		data : {
		},
		async : false,
		dataType : 'json',
		success : function(result) {
			if(result.status == "ERROR"){
				$.messager.alert('提示', '行政区划初始化失败', 'error');
			}else if(result.status == "OK")
			{
				if(result.message.length>0)
				{
					for(var i = 0;i<result.message.length;i++)
					{
						$("#"+areaId).append("<option value=" + result.message[i].id + ">" +  result.message[i].areaName + "</option>");
					}
				}
				
			}
			
		}

	});
}
//获取行政区划名称 --（code转name）
function getAreaInfo(val,type)
{
	var str = "";
	$.ajax({
		type : "get",
		url : Utils.getRootPath() + '/area/getAreaInfo',
		data : {
			"value":val,
			"type":type
		},
		async : false,
		dataType : 'json',
		success : function(result) {
			if(result.status == "OK"){
				str = result.message;
			}
			
		}
	});
	return str;
}
//获取行政区划名称 --（code转name）
function getAreaDetailInfo(province,city,county,type)
{
	var str = "";
	$.ajax({
		type : "get",
		url : Utils.getRootPath() + '/area/getAreaDetailInfo',
		data : {
			"province":province,
			"city":city,
			"county":county,
			"type":type
		},
		async : false,
		dataType : 'json',
		success : function(result) {
			if(result.status == "OK"){
				str = result.message;
			}
			
		}
	});
	return str;
}

//初始化行政区划 --（返回字符串）
function initAreaStr()
{
	var options ="";
	$.ajax({
		type : "get",
		url : Utils.getRootPath() + '/area/initArea',
		data : {
		},
		async : false,
		dataType : 'json',
		success : function(result) {
			if(result.status == "ERROR"){
				$.messager.alert('提示', '行政区划初始化失败', 'error');
			}else if(result.status == "OK")
			{
				if(result.message.length>0)
				{
					for(var i = 0;i<result.message.length;i++)
					{
						options+="<option value=" + result.message[i].id + ">" +  result.message[i].areaName + "</option>";
					}
				}
				
			}
			
		}

	});
	return options;
}

//选择行政区划（联动）
function selectArea(parentId,areaId,tempId)
{
	if(parentId!='')
	{
		$.ajax({
			type : "get",
			url : Utils.getRootPath() + '/area/initArea',
			data : {
				"parentId" : parentId
			},
			async : false,
			dataType : 'json',
			success : function(result) {
				if(result.status == "OK")
				{
					if(result.message.length>0)
					{
						if(areaId==tempId)
						{
							$('#'+tempId+' option').remove();
							$("#"+tempId).append("<option value=''>--请选择--</option>");
						}else if(areaId!=tempId)
						{
							$('#'+areaId+' option').remove();
							$('#'+tempId+' option').remove();
							$("#"+areaId).append("<option value=''>--请选择--</option>");
							$("#"+tempId).append("<option value=''>--请选择--</option>");
						}
						for(var i = 0;i<result.message.length;i++)
						{
							$("#"+areaId).append("<option value=" + result.message[i].id + ">" +  result.message[i].areaName + "</option>");
						}
					}
					
				}
				
			}

		});
	}else 
	{
		if(areaId==tempId)
		{
			$('#'+tempId+' option').remove();
			$("#"+tempId).append("<option value=''>--请选择--</option>");
		}else if(areaId!=tempId)
		{
			$('#'+areaId+' option').remove();
			$('#'+tempId+' option').remove();
			$("#"+areaId).append("<option value=''>--请选择--</option>");
			$("#"+tempId).append("<option value=''>--请选择--</option>");
		}
	}
	
}


//选择行政区划（联动）
function selectNjArea(parentId,areaId,tempId)
{
	if(parentId!='')
	{
		$.ajax({
			type : "get",
			url : Utils.getRootPath() + '/area/listCountryJson',
			data : {
				"parentId" : parentId
			},
			async : false,
			dataType : 'json',
			success : function(result) {
				if(result.status == "OK")
				{
					if(result.message.length>0)
					{
						if(areaId==tempId)
						{
							$('#'+tempId+' option').remove();
							$("#"+tempId).append("<option value=''>--请选择--</option>");
						}else if(areaId!=tempId)
						{
							$('#'+areaId+' option').remove();
							$('#'+tempId+' option').remove();
							$("#"+areaId).append("<option value=''>--请选择--</option>");
							$("#"+tempId).append("<option value=''>--请选择--</option>");
						}
						for(var i = 0;i<result.message.length;i++)
						{
							$("#"+areaId).append("<option value=" + result.message[i].id + ">" +  result.message[i].areaName + "</option>");
						}
					}
					
				}
				
			}

		});
	}else 
	{
		if(areaId==tempId)
		{
			$('#'+tempId+' option').remove();
			$("#"+tempId).append("<option value=''>--请选择--</option>");
		}else if(areaId!=tempId)
		{
			$('#'+areaId+' option').remove();
			$('#'+tempId+' option').remove();
			$("#"+areaId).append("<option value=''>--请选择--</option>");
			$("#"+tempId).append("<option value=''>--请选择--</option>");
		}
	}
	
}
//初始化下拉编码表并赋值
function initSelectCode(type,id)
{
	var options ="<option value=''>--请选择--</option>";
	$.ajax({
		type : "get",
		url : Utils.getRootPath() + '/code/initCode',
		data : {
			"codeType" : type
		},
		async : false,
		dataType : 'json',
		success : function(result) {
			if (result.status == "OK") {
				for(var i = 0;i<result.message.length;i++)
				{
					options += "<option value='" + result.message[i].CODEVALUE + "'>" +  result.message[i].CODENAME + "</option>";
					
				}
			} else if (result.status == "ERROR") {
				$.messager.alert('提示', '初始化'+type+'下拉列表失败', 'error');
			}
		}

	});
	$('#'+id).append(options);
}

//初始化下拉编码表并赋值
function initSelectCodeByDefaultValue(type,id)
{
	var options ="";
	$.ajax({
		type : "get",
		url : Utils.getRootPath() + '/code/initCode',
		data : {
			"codeType" : type
		},
		async : false,
		dataType : 'json',
		success : function(result) {
			if (result.status == "OK") {
				for(var i = 0;i<result.message.length;i++)
				{
					options += "<option value='" + result.message[i].CODEVALUE + "'>" +  result.message[i].CODENAME + "</option>";
					
				}
			} else if (result.status == "ERROR") {
				$.messager.alert('提示', '初始化'+type+'下拉列表失败', 'error');
			}
		}

	});
	$('#'+id).append(options);
}

//初始化下拉编码表 (加载数据过多只初始化值)
function initSelectCodeValue(type)
{
	var options ="<option value=''>--请选择--</option>";
	$.ajax({
		type : "get",
		url : Utils.getRootPath() + '/code/initCode',
		data : {
			"codeType" : type
		},
		async : false,
		dataType : 'json',
		success : function(result) {
			if (result.status == "OK") {
				for(var i = 0;i<result.message.length;i++)
				{
					options += "<option value='" + result.message[i].CODEVALUE + "'>" +  result.message[i].CODENAME + "</option>";
					
				}
			} else if (result.status == "ERROR") {
				$.messager.alert('提示', '初始化'+type+'下拉列表失败', 'error');
			}
		}

	});
	return options;
}

//显示编码表值 
function viewSelectCode(type,value)
{
	var returnValue = "";
	$.ajax({
		type : "get",
		url : Utils.getRootPath() + '/code/initCode',
		data : {
			"codeType" : type
		},
		async : false,
		dataType : 'json',
		success : function(result) {
			if (result.status == "OK") {
				for(var i = 0;i<result.message.length;i++)
				{
					if(result.message[i].CODEVALUE==value)
					{
						returnValue = result.message[i].CODENAME;
					}
				}
			} else if (result.status == "ERROR") {
				$.messager.alert('提示', '初始化'+type+'下拉列表失败', 'error');
			}
		}

	});
	return returnValue;
}

//初始化下拉编码表 (加载数据过多只初始化值,无请选择)
function initSelectCodeByDefaultValueCache(type)
{
	var options ="";
	$.ajax({
		type : "get",
		url : Utils.getRootPath() + '/code/initCode',
		data : {
			"codeType" : type
		},
		async : false,
		dataType : 'json',
		success : function(result) {
			if (result.status == "OK") {
				for(var i = 0;i<result.message.length;i++)
				{
					options += "<option value='" + result.message[i].CODEVALUE + "'>" +  result.message[i].CODENAME + "</option>";
					
				}
			} else if (result.status == "ERROR") {
				$.messager.alert('提示', '初始化'+type+'下拉列表失败', 'error');
			}
		}

	});
	return options;
}


function logout(){
	$.messager.confirm('确认 ', '是否退出系统 ？', function(r) {
		if(r){
			var url = Utils.getRootPath() + "/login/logout";
			window.location.href = url;
		}
	});
	
}


function  validatePassword(){
	var pa=/^(?=.*?[a-zA-Z])(?=.*?[0-6])[!"#$%&'()*+,\-./:;<=>?@\[\\\]^_`{|}~A-Za-z0-9]{6,16}$/;
	var oldpassword=$('#oldPassword').val();
	var newPassword1=$('#newPassword1').val();
	var newPassword2=$('#newPassword2').val();
	if( oldpassword ==''){
		$.messager.alert('提示', '请输入原始密码!', 'warning');
	}else if(newPassword1 ==''){
		$.messager.alert('提示', '请输入新密码!', 'warning');
	}else if(newPassword2 == ''){
		$.messager.alert('提示', '请确认新密码!', 'warning');
	}else if( newPassword2 == newPassword1){
		//验证原始密码的正确性
		var rsult=validateInitPass(oldpassword);
		if(rsult == "OK"){
			if(! pa.test(newPassword1)){
				$.messager.alert('提示', '新密码格式不正确 !</br>(格式:长度为6-16位,且包括数字和字母)', 'warning');
			}else{
			var yesOrfault = resetPassword(newPassword1);
			if(yesOrfault =="OK"){
				$.messager.alert('提示', '修改成功!请重新登录',"info", function() {
					var url = Utils.getRootPath() + "/login/logout";
					window.location.href = url;
				});
			}
			}
		}
	}else {
		$.messager.alert('提示', '请确认新密码输入一致!', 'warning');
	}
}

/**
 * 验证初始密码的正确性
 * @param oldpassword
 */
function validateInitPass(oldpassword){
	var dataPass="FAIL";
	$.ajax({
		type : "get",
		url : Utils.getRootPath() + '/userInfo/validateInitPass',
		data : {
			"oldpassword" : oldpassword
		},
		async : false,
		dataType : 'json',
		success : function(result) {
			if (result.status == "OK") {
				dataPass=result.status;
			} else if (result.status == "ERROR") {
				$.messager.alert('提示', '输入的原始密码验证错误!', 'error');
			}else{
				$.messager.alert('提示', '验证出错，请与管理员联系!', 'error');
			}
		}

	});
	return dataPass;
}
//重置密码
function resetPassword(password){
	var dataPass="FAIL";
	$.ajax({
		type : "get",
		url : Utils.getRootPath() + '/userInfo/resetPassword',
		data : {
			"password" : password
		},
		async : false,
		dataType : 'json',
		success : function(result) {
			if (result.status == "OK") {
				dataPass=result.status;
			} else if (result.status == "ERROR") {
				$.messager.alert('提示', '修改密码失败,请重试!', 'error');
			}else{
				$.messager.alert('提示', '修改出错，请与管理员联系!', 'error');
			}
		}

	});
	return dataPass;
}


//编辑
function editUserDetail(title, x, y) {
	var url = Utils.getRootPath() + '/userInfo/editDetail';
	add(url, title, x, y);
}