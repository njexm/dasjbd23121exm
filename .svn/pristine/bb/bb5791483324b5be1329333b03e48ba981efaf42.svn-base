$(function(){
	$("#gender").combobox({
		panelHeight:"60"
	});
	
	$("#userDataGrid").datagrid({
		title:"用户信息列表",
		url:Utils.getRootPath()+"/userManage/getUserDataGrid",
		width:800,
		height:420,
		pagination:true,
		rownumbers:true,
		fit:true,
		fitColumns:true,
		columns:[[
	        {field:'id',checkbox : true},
			{field:'name',title:'用户名',width:60},    
	        {field:'gender',title:'性别',width:20,
				formatter:function(value,row,index){
					if(value == "M"){
						return "男";
					}else{
						return "女";
					}
				}
	        },
	        {field:'position',title:'职务',width:100},
	        {field:'email',title:'邮箱',width:100},
	        {field:'phoneNo',title:'手机',width:100},
	        {field:'roleName',title:'权限角色',width:120}
		]],
		toolbar : "#tb"
	});
	
	var p = $('#userDataGrid').datagrid('getPager');
	$(p).pagination({  
        pageSize: 10,//每页显示的记录条数，默认为10  
        pageList: [5,10,15],//可以设置每页记录条数的列表  
        beforePageText: '第',//页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',  
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
    });
	
	$("#queryBtn").click(function(){
		var userName = $("#userName").val().trim();
		var gender = $('#gender').combobox('getValue');
		if(gender == "all"){
			gender = "";
		}
		$("#userDataGrid").datagrid("load",{name:userName,gender:gender}); 
	})
	
});

function updateUser(){
	var objarr = $("#userDataGrid").datagrid("getChecked");
	if(objarr == null || objarr.length != 1){
		$.messager.alert('提示','请选择一条数据！',"info");
		return;
	}
	var id = objarr[0].id;
	$('#dlg').dialog({
	    title: '修改用户',    
	    width: 400,    
	    height: 300,    
	    closed: false,    
	    cache: false,    
	    modal: true,
	    toolbar : [{
	    	text: '确定',
            iconCls: 'icon-ok',
            handler: function () {
            	saveOrUpdate();
            }
	    },{
	    	text: '取消',
            iconCls: 'icon-cancel',
            handler: function () {
            	$('#dlg').dialog('close');
            }
	    }],
	    onLoad : function(){
	    	$("#dlggender").combobox({
	    		panelHeight:"60"
	    	});
	    	var t = $("#hiddenGender").val();
	    	$('#dlggender').combobox('setValues', t);
	    }
	});    
	$('#dlg').dialog('refresh', Utils.getRootPath()+"/userManage/openUpdateUserDlg?id="+id);  
	
}

function addUser(){
	$('#dlg').dialog({
	    title: '添加用户',    
	    width: 	1000,    
	    height: 300,    
	    closed: false,    
	    cache: false,    
	    modal: true,
	    toolbar : [{
	    	text: '确定',
            iconCls: 'icon-ok',
            handler: function () {
            	saveOrUpdate();
            }
	    },{
	    	text: '取消',
            iconCls: 'icon-cancel',
            handler: function () {
            	$('#dlg').dialog('close');
            }
	    }],
	    onLoad : function(){
	    	$("#dlggender").combobox({
	    		panelHeight:"60"
	    	});
	    }
	});    
	$('#dlg').dialog('refresh', Utils.getRootPath()+"/userManage/openAddUserDlg");  
}

function deleteUsers(){
	var objArr = $("#userDataGrid").datagrid("getChecked");
	if(objArr == null || objArr.length == 0){
		$.messager.alert('提示','请至少选择一条数据！',"info");
		return;
	}
	var ids = "";
	for(var i=0;i<objArr.length;i++){
		var id = objArr[i].id;
		ids+=","+id;
	}
	ids = ids.substring(1);
	
	$.messager.confirm('确认','您确认想要删除吗？',function(r){
		if (r){
			$.ajax({
				type:"post",
				url:Utils.getRootPath()+"/userManage/deleteUsers",
				data:{ids : ids},
				dataType:"text",
				success:function(msg){
					if("success" == msg){
						$("#userDataGrid").datagrid("load");
					}else{
						$.messager.alert('提示','系统发生异常，请待会再试！',"info");
					}
				}
			})
		}
	})
	
}

function saveOrUpdate(){
	var id = $("#id").val();
	var userName = $("#name").val();
	if(userName == null || userName == ""){
		$.messager.alert('提示','用户名不能为空！',"info");
		return;
	}else if(userName.length > 20){
		$.messager.alert('提示','用户名不能超过20个字符！',"info");
		return;
	}
	var pwd = $("#pwd").val();
	if(pwd == null || pwd == ""){
		$.messager.alert('提示','密码不能为空！',"info");
		return;
	}else if(pwd.length > 10){
		$.messager.alert('提示','密码不能超过10个字符！',"info");
		return;
	}
	var position = $("#position").val();
	if(position == null || position == ""){
		$.messager.alert('提示','职位不能为空！',"info");
		return;
	}else if(position.length > 20){
		$.messager.alert('提示','职位不能超过20个字符！',"info");
		return;
	}
	var gender=$('#dlggender').combobox('getValue');
	if(gender == null || gender == "all" || gender == "default"){
		$.messager.alert('提示','性别不能为空！',"info");
		return;
	}
	var email = $("#email").val();
	if(email == null || email == ""){
		$.messager.alert('提示','邮箱不能为空！',"info");
		return;
	}else if(!checkEmail(email)){
		$.messager.alert('提示','请输入正确的邮箱！',"info");
		return;
	}
	var phone  =$("#phone").val();
	if(phone == null || phone == ""){
		$.messager.alert('提示','手机号码不能为空！',"info");
		return;
	}else if(!checkMobilePhone(phone)){
		$.messager.alert('提示','请输入正确的手机号码！',"info");
		return;
	}
	$.ajax({
		type:"post",
		url:Utils.getRootPath()+"/userManage/saveOrUpdate",
		data:{id:id,name:userName,password:pwd,email:email,position:position,phoneNo:phone,gender:gender},
		dataType:"text",
		success:function(msg){
			if("success" == msg){
				$('#dlg').dialog('close');
				$("#userDataGrid").datagrid("load");
			}else{
				$.messager.alert('提示','系统发生异常，请待会再试！',"info");
			}
		}
	});
}

function distributeRole(){
	var objArr = $("#userDataGrid").datagrid("getChecked");
	if(objArr == null || objArr.length != 1){
		$.messager.alert('提示','请选择一条数据！',"info");
		return;
	}
	var userId = objArr[0].id;
	
	$('#distributeRoleDlg').dialog({
	    title: '分配角色',    
	    width: 1000,    
	    height: 400,    
	    closed: false,    
	    cache: false,    
	    modal: true,
	    toolbar : [{
	    	text: '确定',
            iconCls: 'icon-ok',
            handler: function () {
            	saveDistributeRole(userId);
            }
	    },{
	    	text: '取消',
            iconCls: 'icon-cancel',
            handler: function () {
            	$('#distributeRoleDlg').dialog('close');
            }
	    }],
	    onLoad : function(){
	    	loadTheGrid(userId);
	    }
	});    
	$('#distributeRoleDlg').dialog('refresh', Utils.getRootPath()+"/userManage/distributeRoleDlg");  
}

function loadTheGrid(userId){
	$("#leftGrid").datagrid({
		title:"可选择角色列表",
		url:Utils.getRootPath()+"/roleManage/getRoleDataGrid",
		pagination:true,
		fit:true,
		fitColumns:true,
		columns:[[
	        {field:'id',checkbox : true},
			{field:'name',title:'角色名称',width:60},
	        {field:'description',title:'描述',width:100}
		]],
		onDblClickRow : function(rowIndex, rowData){
			leftToRight(rowIndex, rowData);
		}
	});
	
	$("#rightGrid").datagrid({
		title:"已选择角色列表",
		url:Utils.getRootPath()+"/userManage/getExsitRoleDataGrid",
		queryParams:{userId:userId},
		pagination:true,
		fit:true,
		fitColumns:true,
		columns:[[
	        {field:'id',checkbox : true},
			{field:'name',title:'角色名称',width:60},
	        {field:'description',title:'描述',width:100}
		]],
		onDblClickRow : function(rowIndex, rowData){
			rightToLeft(rowIndex, rowData);
		},
		onAfterRender : function(target){
			alert(22);
		}
	});
}

/**
 * 鼠标双击，左边角色添加到右边grid
 * @param rowIndex
 * @param rowData
 */
function leftToRight(rowIndex, rowData){
	var roleId = rowData.id;
	var roleName = rowData.name;
	var roleDescription = rowData.description;
	
	var allRows = $("#rightGrid").datagrid("getRows");
	var index = null;
	for(var i=0;i<allRows.length;i++){
		var row = allRows[i];
		if(roleId == row.id){
			index = $('#rightGrid').datagrid('getRowIndex', row);
			$("#rightGrid").datagrid("deleteRow",index);
			break;
		}
	}
	$("#leftGrid").datagrid("deleteRow",rowIndex);
	
	$("#rightGrid").datagrid("insertRow",{
		index : 0,
		row : {
			id : roleId,
			name : roleName,
			description : roleDescription
		}
	});
}

/**
 * 鼠标双击，右边角色添加到左边grid
 * @param rowIndex
 * @param rowData
 */
function rightToLeft(rowIndex, rowData){
	var roleId = rowData.id;
	var roleName = rowData.name;
	var roleDescription = rowData.description;
	
	var allRows = $("#leftGrid").datagrid("getRows");
	var index = null;
	for(var i=0;i<allRows.length;i++){
		var row = allRows[i];
		if(roleId == row.id){
			index = $('#leftGrid').datagrid('getRowIndex', row);
			$("#leftGrid").datagrid("deleteRow",index);
			break;
		}
	}
	$("#rightGrid").datagrid("deleteRow",rowIndex);
	
	$("#leftGrid").datagrid("insertRow",{
		index : 0,
		row : {
			id : roleId,
			name : roleName,
			description : roleDescription
		}
	});
}

function saveDistributeRole(userId){
	var roleRows = $("#rightGrid").datagrid("getRows");
	var roleJsonStr = JSON.stringify(roleRows);
	$.ajax({
		type:"post",
		url:Utils.getRootPath()+"/userManage/saveDistributeRole",
		data:{roleJsonStr:roleJsonStr,userId:userId},
		dataType:"text",
		success:function(msg){
			if("success" == msg){
				$('#distributeRoleDlg').dialog('close');
				$("#userDataGrid").datagrid("load");
			}else{
				$.messager.alert('提示','系统发生异常，请待会再试！',"info");
			}
		}
	});
}