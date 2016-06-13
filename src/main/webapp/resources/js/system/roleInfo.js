//添加
function add(url,title,x,y)
{
	window.parent.initAdd(url,title,x,y);
}
function searchRoleInfo(){
	
	var queryParams = {
		'ctpRole.name':$('#roleName').val(),
	};
	$('#dg').datagrid('options').queryParams = queryParams;
	$("#dg").datagrid('load');
}
// 编辑
function edit(title, x, y) {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	if (rows.length > 1) {
		$.messager.alert('提示', '请选择一条数据进行操作！', 'warning');
		return;
	}
	var row = rows[0];
	var url = Utils.getRootPath() + '/roleInfo/edit?id=' + row.ID;
	add(url, title, x, y);
}

// 查看详细
function detail(title, x, y) {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	if (rows.length > 1) {
		$.messager.alert('提示', '请选择一条数据进行操作！', 'warning');
		return;
	}
	var row = rows[0];
	var url = Utils.getRootPath() + '/order/orders/gotoDetailOrders?id='
			+ row.ID;
	add(url, title, x, y);
}

// 新增数据
function saveRoleInfo(formId) {
	// 数据有限性判断
	if (validateSubmit(formId)) {
		// 保存
		var id = $('#id').val();
		var url = Utils.getRootPath() + '/roleInfo/save?id='+id;
		$.ajax({
			type : "post",
			url : url,
			data : $('#' + formId).serialize(),
			async : false,
			dataType : 'json',
			success : function(result) {
				if (result.message == "保存成功") {
/*					$.messager.alert('提示', '保存成功', 'info', function() {
						// loadDataGrid();
						window.parent.loadDataGrid('roleInfo');
						window.parent.closeWinForm();
					});*/
					var msg='保存成功';
					alert_totalQuery(msg);
				} else if (result.status == "error") {
					$.messager.alert('提示', '保存失败，请联系管理员', 'error');
				}
			}
		});
	}
}

// 修改数据
function editRoleInfo(formId) {
	// 数据有限性判断
	if (validateSubmit(formId)) {
		// 保存
		var url = Utils.getRootPath() + '/roleInfo/update';
		$.ajax({
			type : "post",
			url : url,
			data : $('#' + formId).serialize(),
			async : false,
			dataType : 'json',
			success : function(result) {
				if (result.message == "更新成功") {
/*					$.messager.alert('提示', '保存成功', 'info', function() {
						// loadDataGrid();
						window.parent.closeWinForm();
						("#dg").datagrid('load');
					});*/
					var msg='修改成功';
					alert_totalQuery2(msg);
				} else if (result.status == "error") {
					$.messager.alert('提示', '保存失败，请联系管理员', 'error');
				}
			}
		});
	}
}

// 清空查询条件
function clearForm(ff) {
	var elements = ff.elements;
	for (var i = 0; i < elements.length; i++) {
		var element = elements[i];
		if (element.type == "text") {
			element.value = "";
		} else if (element.type == "radio" || element.type == "checkbox") {
			element.checked = false;
		} else if (element.options != null) {
			element.options[0].selected = true;
		}
	}
}

//打开权限选择面板
function openModuleTree(){
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	if (rows.length > 1) {
		$.messager.alert('提示', '请选择一条数据进行操作！', 'warning');
		return;
	}
	initModuleTree();
	//打开div
	$('#moduleTreeDialog').dialog('open').dialog('setTitle', '菜单');
}

function initModuleTree(){
	$('#moduleTree').tree({
	    url: Utils.getRootPath() + "/module/getModuleTreeDataList",
	    checkbox: true,
	    lines: true,
	    onLoadSuccess: function(node, data){
	    	initSelectedNode();
	    }
	});  
}

function initSelectedNode(){
	var row = $('#dg').datagrid('getSelections');
	var roleId = row[0].ID; 
	$.ajax({
		async: false,
		url: Utils.getRootPath() + "/roleInfo/getGrantModuleId?time="+new Date(),
		data: {
			roleId: roleId
		},
		dataType: 'json',
		success: function(data) {
			for(var i=0;i<data.length;i++){
				var moduleId = data[i].moduleid;
				var parent = data[i].parentid;
				var node = $('#moduleTree').tree('find', moduleId);
				if(node!=undefined && node!=null && parent!=undefined && parent != 'ROOT'){
					$('#moduleTree').tree('check', node.target);
				}
			}
		}
	});
}

function saveGrantModule(){
	var row = $('#dg').datagrid('getSelections');
	var roleId = row[0].ID; 
	var nodes = $('#moduleTree').tree('getChecked', ['checked','indeterminate']);
	//alert("nodes.length=="+nodes.length);
	if(nodes.length==0){
		$.messager.alert("提示","请选择菜单进行分配","info");
		return;
	}
	var nodeIds = "";
	for(var i=0;i<nodes.length;i++){
		nodeIds += ","+nodes[i].id;
	}
	nodeIds = nodeIds.substring(1);
	$.ajax({
		async: false,
		url: Utils.getRootPath() + "/roleInfo/saveGrantModule?time="+new Date(),
		data: {
			roleId: roleId,
			moduleIds: nodeIds
		},
		dataType: 'json',
		success: function(result) {
			$.messager.alert(result.title, result.message, result.icon, function(){
				if(result.result){
					//TODO 操作成功后的操作
					$('#moduleTreeDialog').dialog('close');
				}else{
					//TODO 操作失败后的操作
				}
			});
		}
	});
}

//删除
function destroyInfo() {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	var id = rows[0].ID;
	$.messager.confirm('确认 ', '确定要删除 ？', function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : Utils.getRootPath() + '/roleInfo/delete',
				data : {
					id : id
				},
				async : false,
				dataType : 'json',
				success : function(result) {
					if (result.result) {
/*						$.messager.alert('提示', '删除成功', 'info', function() {
							window.parent.loadDataGrid('goodfile');
							window.parent.closeWinForm();
						});*/
						var msg='删除成功';
						alert_totalQuery2(msg);
					} else{
						$.messager.alert('提示', '该角色存在任务，不可删除', 'warning');
					}
				}
			});
		};
	});
};

//alert延迟5秒关闭
function alert_totalQuery(msg){  
    var interval;  
    var time=1000;  
    var x=5;  
    $.messager.alert(' ','<font size=\"2\" color=\"#666666\"><strong>'+msg+'</strong></font>','info',function(){// loadDataGrid();    	
    });  
    $(".messager-window .window-header .panel-title").append("系统提示（"+x+"秒后自动关闭）");  
    interval=setInterval(fun,time);  
    function fun(){  
        --x;  
        if(x==0){  
          clearInterval(interval);  
          $(".messager-body").window('close'); 
          window.parent.loadDataGrid('roleInfo');
			window.parent.closeWinForm();
			("#dg").datagrid('load');
        }  
        $(".messager-window .window-header .panel-title").text("");  
        $(".messager-window .window-header .panel-title").append("系统提示（"+x+"秒后自动关闭）");  
    }  
}  
//alert延迟5秒关闭
function alert_totalQuery2(msg){  
    var interval;  
    var time=1000;  
    var x=5;  
    $.messager.alert(' ','<font size=\"2\" color=\"#666666\"><strong>'+msg+'</strong></font>','info',function(){// loadDataGrid();    	
    });  
    $(".messager-window .window-header .panel-title").append("系统提示（"+x+"秒后自动关闭）");  
    interval=setInterval(fun,time);  
    function fun(){  
        --x;  
        if(x==0){  
          clearInterval(interval);  
          $(".messager-body").window('close');
          $('#js_info').dialog('close');
        }  
        $(".messager-window .window-header .panel-title").text("");  
        $(".messager-window .window-header .panel-title").append("系统提示（"+x+"秒后自动关闭）");  
    }  
} 