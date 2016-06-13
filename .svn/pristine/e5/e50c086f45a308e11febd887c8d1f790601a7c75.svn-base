//添加
function add(url,title,x,y)
{
	window.parent.initAdd(url,title,x,y);
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
	var url = Utils.getRootPath() + '/module/edit?id=' + row.ID;
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
function saveModule(formId) {
	// 数据有限性判断
	if (validateSubmit(formId)) {
		// 保存
		var url = Utils.getRootPath() + '/module/save';
		$.ajax({
			type : "post",
			url : url,
			data : $('#' + formId).serialize(),
			async : false,
			dataType : 'json',
			success : function(result) {
				if (result.message == "保存成功") {
					$.messager.alert('提示', '保存成功', 'info', function() {
						// loadDataGrid();
						window.parent.loadDataGrid('module');
						window.parent.closeWinForm();
					});
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
					$.messager.alert('提示', '保存成功', 'info', function() {
						// loadDataGrid();
						window.parent.closeWinForm();
						("#dg").datagrid('load');
					});
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

//初始化父级菜单下拉框
function initParentModule(){
	$('#parentID').combotree({    
	    url: Utils.getRootPath() + "/module/getModuleTreeData",    
	    onLoadSuccess: function(node, data){
	    	var optionValue = $('#parentID').attr("optionValue");
	    	if(optionValue!=undefined && optionValue != ""){
	    		$('#parentID').combotree('setValue', optionValue);
	    	}
	    }
	});  
}


//选择图标
function changeIcon(src){
	$('#icon').attr('src',src);
	var Srcvalue=src.split('exm');
	$('#iconId').val(Srcvalue[1]);
}

//删除
function destroyInfo() {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	var ids = "";
	for (var i = 0; i < rows.length; i++) {
		if (rows.length == i + 1) {
			ids += rows[i].ID;
		} else {
			ids += rows[i].ID + ",";
		}
	}
	$.messager.confirm('确认 ', '确定要删除 ？', function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : Utils.getRootPath() + '/module/delete',
				data : {
					id : ids
				},
				async : false,
				dataType : 'json',
				success : function(result) {
					if (result.result) {
						$.messager.alert('提示', '删除成功', 'info', function() {
							window.parent.loadDataGrid('goodfile');
							window.parent.closeWinForm();
						});
					} else{
						$.messager.alert('提示', '该用户存在任务，不可删除', 'warning');
					}
				}
			});
		};
	});
};