var zTreeObj;
$(function(){
	$("#roleDataGrid").datagrid({
		title:"角色信息列表",
		url:Utils.getRootPath()+"/roleManage/getRoleDataGrid",
		width:1200,
		height:420,
		pagination:true,
		rownumbers:true,
		fit:true,
		fitColumns:true,
		columns:[[
	        {field:'id',checkbox : true},
			{field:'name',title:'角色名称',width:100},	       
	        {field:'creater',title:'创建者',width:80},
	        {field:'description',title:'描述',width:100},
	        {field:'createTime',title:'创建时间',width:100},
	        {field:'updateTime',title:'更新时间',width:100}
		]],
		toolbar : "#queryCondiction"
	});
	
	var p = $('#roleDataGrid').datagrid('getPager');
	$(p).pagination({
        pageSize: 10,//每页显示的记录条数，默认为10  
        pageList: [5,10,15],//可以设置每页记录条数的列表  
        beforePageText: '第',//页数文本框前显示的汉字  
        afterPageText: '页    共 {pages} 页',  
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
    });
	
	$("#queryBtn").click(function(){
		var roleName = $("#roleName").val().trim();
		var createrName = $("#createrName").val().trim();
		$("#roleDataGrid").datagrid("load",{name:roleName,creater:createrName}); 
	})
});

function addRole(){
	$('#dlg').dialog({    
	    title: '添加角色',    
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
	    
	    }
	});    
	$('#dlg').dialog('refresh', Utils.getRootPath()+"/roleManage/openAddRoleDlg");  
}

function deleteRoles(){
	var objArr = $("#roleDataGrid").datagrid("getChecked");
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
				url:Utils.getRootPath()+"/roleManage/deleteRoles",
				data:{ids : ids},
				dataType:"text",
				success:function(msg){
					if("success" == msg){
						$("#roleDataGrid").datagrid("load");
					}else{
						$.messager.alert('提示','系统发生异常，请待会再试！',"info");
					}
				}
			})
		}
	})
}

function updateRole(){
	var objarr = $("#roleDataGrid").datagrid("getChecked");
	if(objarr == null || objarr.length != 1){
		$.messager.alert('提示','请选择一条数据！',"info");
		return;
	}
	var id = objarr[0].id;
	$('#dlg').dialog({
	    title: '修改角色',    
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
	    	var description = $("#description").val();
	    	alert(description);
	    	if(description == null || description == ""){
	    		$("#roleDescription").val("");
	    	}else{
	    		$("#roleDescription").val(description);
	    	}
	    }
	});    
	$('#dlg').dialog('refresh', Utils.getRootPath()+"/roleManage/openUpdateRoleDlg?id="+id);  
}

function saveOrUpdate(){
	var roleId = $("#roleId").val();
	var roleName = $("#name").val();
	var currentUserId = $("#currentUserId").val();
	var roleDescription = $("#roleDescription").val();
	if(roleName == null || roleName == ""){
		$.messager.alert('提示','角色名称不能为空！',"info");
		return;
	}else if(roleName.length > 20){
		$.messager.alert('提示','角色名称不能超过20个字符！',"info");
		return;
	}
	if(roleDescription != null && roleDescription.length > 100){
		$.messager.alert('提示','描述不能超过100个字符！',"info");
		return;
	}
	$.ajax({
		type:"post",
		url:Utils.getRootPath()+"/roleManage/saveOrUpdate",
		data:{id:roleId,name:roleName,description:roleDescription,creater:currentUserId},
		dataType:"text",
		success:function(msg){
			if("success" == msg){
				$('#dlg').dialog('close');
				$("#roleDataGrid").datagrid("load");
			}else{
				$.messager.alert('提示','系统发生异常，请待会再试！',"info");
			}
		}
	});
}

function distributeMenu(){
	var objarr = $("#roleDataGrid").datagrid("getChecked");
	if(objarr == null || objarr.length != 1){
		$.messager.alert('提示','请选择一条数据！',"info");
		return;
	}
	var roleId = objarr[0].id;
	$('#menuDlg').dialog({
	    title: '分配菜单',    
	    width: 550,    
	    height: 400,    
	    closed: false,    
	    cache: false,    
	    modal: true,
	    toolbar : [{
	    	text: '确定',
            iconCls: 'icon-ok',
            handler: function () {
            	saveTheCheckedMenu(roleId);
            }
	    },{
	    	text: '取消',
            iconCls: 'icon-cancel',
            handler: function () {
            	$('#menuDlg').dialog('close');
            }
	    }],
	    onLoad : function(){
	    	initMenuTree();
	    }
	});    
	$('#menuDlg').dialog('refresh', Utils.getRootPath()+"/roleManage/openMenuDlg?roleId="+roleId);  
}

function saveTheCheckedMenu(roleId){
	var nodes = zTreeObj.getCheckedNodes(true);
	var menuJsonStr = JSON.stringify(nodes);
	$.ajax({
		type:"post",
		url:Utils.getRootPath()+"/roleManage/saveTheCheckedMenu",
		data:{
			roleId : roleId,
			menuJsonStr : menuJsonStr
		},
		dataType:"text",
		success:function(msg){
			if("success" == msg){
				$('#menuDlg').dialog('close');
				$("#roleDataGrid").datagrid("load");
			}else{
				$.messager.alert('提示','系统发生异常，请待会再试！',"info");
			}
		}
	});
}

function initMenuTree(){
	var setting = {
			async : {
				type : "post",
				enable: true,
				url : Utils.getRootPath()+"/menuManage/getTreeNodes"
			},
			check : {
				enable: true,
				chkStyle : "checkbox",
			},
			data : {
				key : {
					name : "moduleName"
				},
				simpleData : {
					enable : true,
					idKey : "id",
					pIdKey : "parentID",
					rootPId : "ROOT"
				}
			},
			view : {
				showIcon : true,
				showLine: true
			},
			callback:{
				onAsyncSuccess : function(){
					var nodes = zTreeObj.transformToArray(zTreeObj.getNodes());
					var menuIdjsonArr = eval($("#menuIdjsonStr").val());
					for(var i=0;i<nodes.length;i++){
						for(var j=0;j<menuIdjsonArr.length;j++){
							if(nodes[i].id == menuIdjsonArr[j]){
								zTreeObj.checkNode(nodes[i], true, false);
							}
						}
					}
				}
			}
		};
		var obj = $.fn.zTree.init($("#menuTree"),setting);
		zTreeObj = $.fn.zTree.getZTreeObj("menuTree");
}
