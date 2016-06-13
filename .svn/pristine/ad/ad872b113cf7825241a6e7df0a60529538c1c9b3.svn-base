//添加
function add(url, title, x, y) {
	window.parent.initAdd(url, title, x, y);
}
function searchRoleInfo() {

	var queryParams = {
		'ctpRole.name' : $('#roleName').val(),
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
	var url = Utils.getRootPath() + '/notice/edit?id=' + row.ID;
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
	var url = Utils.getRootPath() + '/notice/gotoDetailOrders?id=' + row.ID;
	add(url, title, x, y);
}

// 新增数据
function saveRoleInfo(formId) {
	var title = document.getElementById('title').value;
	var description = document.getElementById('description').value;
	if (title.length == 0) {
		$.messager.alert('提示', '标题不能为空！', 'warning');
		return;
	}
	if (description.length == 0) {
		$.messager.alert('提示', '内容不能为空！', 'warning');
		return;
	}
	// 数据有限性判断
	if (validateSubmit(formId)) {
		// 保存
		var url = Utils.getRootPath() + '/notice/save';
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
						window.parent.loadDataGrid('notice');
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
	var title = document.getElementById('title').value;
	var description = document.getElementById('description').value;
	if (title.length == 0) {
		$.messager.alert('提示', '标题不能为空！', 'warning');
		return;
	}
	if (description.length == 0) {
		$.messager.alert('提示', '内容不能为空！', 'warning');
		return;
	}
	// 数据有限性判断
	if (validateSubmit(formId)) {
		// 保存
		var url = Utils.getRootPath() + '/notice/update';
		$.ajax({
			type : "post",
			url : url,
			data : $('#' + formId).serialize(),
			async : false,
			dataType : 'json',
			success : function(result) {
				if (result.message == "更新成功") {
					$.messager.alert('提示', '保存成功', 'info', function() {
						window.parent.loadDataGrid('notice');
						window.parent.closeWinForm();
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

// 打开权限选择面板
function openModuleTree() {
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
	// 打开div
	$('#moduleTreeDialog').dialog('open').dialog('setTitle', '菜单');
}

function initModuleTree() {
	$('#moduleTree').tree({
		url : Utils.getRootPath() + "/module/getModuleTreeDataList",
		checkbox : true,
		lines : true,
		onLoadSuccess : function(node, data) {
			initSelectedNode();
		}
	});
}

function initSelectedNode() {
	var row = $('#dg').datagrid('getSelections');
	var roleId = row[0].ID;
	$.ajax({
		async : false,
		url : Utils.getRootPath() + "/roleInfo/getGrantModuleId?time="
				+ new Date(),
		data : {
			roleId : roleId
		},
		dataType : 'json',
		success : function(data) {
			for (var i = 0; i < data.length; i++) {
				var moduleId = data[i].moduleid;
				var parent = data[i].parentid;
				var node = $('#moduleTree').tree('find', moduleId);
				if (node != undefined && node != null && parent != undefined
						&& parent != 'ROOT') {
					$('#moduleTree').tree('check', node.target);
				}
			}
		}
	});
}

function saveGrantModule() {
	var row = $('#dg').datagrid('getSelections');
	var roleId = row[0].ID;
	var nodes = $('#moduleTree').tree('getChecked',
			[ 'checked', 'indeterminate' ]);
	// alert("nodes.length=="+nodes.length);
	if (nodes.length == 0) {
		$.messager.alert("提示", "请选择菜单进行分配", "info");
		return;
	}
	var nodeIds = "";
	for (var i = 0; i < nodes.length; i++) {
		nodeIds += "," + nodes[i].id;
	}
	nodeIds = nodeIds.substring(1);
	$.ajax({
		async : false,
		url : Utils.getRootPath() + "/roleInfo/saveGrantModule?time="
				+ new Date(),
		data : {
			roleId : roleId,
			moduleIds : nodeIds
		},
		dataType : 'json',
		success : function(result) {
			$.messager.alert(result.title, result.message, result.icon,
					function() {
						if (result.result) {
							// TODO 操作成功后的操作
							$('#moduleTreeDialog').dialog('close');
						} else {
							// TODO 操作失败后的操作
						}
					});
		}
	});
}
// 查看详细
function openDetail(id) {
	var url = Utils.getRootPath() + '/notice/openDetail?id=' + id;
	var title = "公告详情";
	add(url, title, 600, 350);
}

function deleteNotice() {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要删除的数据！', 'warning');
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
				url : Utils.getRootPath() + '/notice/deleteNotice',
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
					} else if (result.status == "error") {
						$.messager.alert('提示', '删除失败，请联系管理员', 'error');
					}
				}
			});
		}
		;
	});
}

function setThrow(id) {
	var url = Utils.getRootPath() + '/notice/setThrow?id=' + id;
	$.ajax({
		type : "post",
		url : url,
		async : false,
		dataType : 'json',
		success : function(result) {
			if (result.message == "删除成功") {
				$('#dg').datagrid('load');
			} else if (result.status == "删除失败") {
				$.messager.alert('提示', '修改失败，请联系管理员', 'error');
				$('#dg').datagrid('load');
			}
		}
	});
}