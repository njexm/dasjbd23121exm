//添加
function add(url, title, x, y) {
	window.parent.initAdd(url, title, x, y);
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
	var url = Utils.getRootPath() + '/branch/branchDo/edit?id=' + row.ID;
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
function saveBranch(formId) {
	var province = $('#province').val();
	var city = $('#city').val();
	var country = $('#country').val();
	var branch = $('#branch').combobox('getValue');
	if (province.length == 0) {
		$.messager.alert('提示', '请选择省份！', 'warning');
		return;
	}
	if (city.length == 0) {
		$.messager.alert('提示', '请选择城市！', 'warning');
		return;
	}
	if (country.length == 0) {
		$.messager.alert('提示', '请选择区县！', 'warning');
		return;
	}
	if (branch.length == 0) {
		$.messager.alert('提示', '请选择所属分店！', 'warning');
		return;
	}
	// 数据有限性判断
	if (validateSubmit(formId)) {
		// 保存
		var url = Utils.getRootPath() + '/branch/branchDo/save';
		$.ajax({
			type : "post",
			url : url,
			data : $('#' + formId).serialize(),
			async : false,
			dataType : 'json',
			success : function(result) {
				if (result.result) {
					$.messager.alert('提示', '保存成功', 'info', function() {
						// 刷新数据
						window.parent.loadDataGrid('branch');
						window.parent.closeWinForm();
					});
				} else {
					$.messager.alert('提示', '保存失败，请联系管理员', 'error');
				}
			}
		});
	}
}

// 修改数据
function editBaseInfo(formId) {
	var province = $('#province').val();
	var city = $('#city').val();
	var county = $('#county').val();
	var branch = $('#branch').combobox('getValue');
	if (province.length == 0) {
		$.messager.alert('提示', '请选择省份！', 'warning');
		return;
	}
	if (city.length == 0) {
		$.messager.alert('提示', '请选择城市！', 'warning');
		return;
	}
	if (county.length == 0) {
		$.messager.alert('提示', '请选择区县！', 'warning');
		return;
	}
	if (branch.length == 0) {
		$.messager.alert('提示', '请选择所属分店！', 'warning');
		return;
	}
	// 数据有限性判断
	if (validateSubmit(formId)) {
		// 保存
		var url = Utils.getRootPath() + '/branch/branchDo/update';
		$.ajax({
			type : "post",
			url : url,
			data : $('#' + formId).serialize(),
			async : false,
			dataType : 'json',
			success : function(result) {
				if (result.result) {
					$.messager.alert('提示', '保存成功', 'info', function() {
						window.parent.loadDataGrid('branch');
						window.parent.closeWinForm();
					});
				} else {
					$.messager.alert('提示', '保存失败，请联系管理员', 'error');
				}
			}
		});
	}
}

// 删除
function deleteInfo() {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	} else {
		$.messager.confirm('提示', '是否确认删除？', function(r) {
			if (r) {
				var ids = getRowsInfo();
				if (ids.length == 0) {
					$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
				} else {
					var url = Utils.getRootPath()
							+ "/branch/branchDo/deleteBranch?ids=" + ids;
					$.ajax({
						url : url,
						type : 'GET',
						data : {
							aaaaa : ids
						},
						async : false,
						dataType : 'json',
						success : function(result) {
							if (result.result) {
								$.messager.alert('提示', '删除成功！', 'info',
										function() {
											window.parent
													.loadDataGrid('branch');
										});
							} else {
								$.messager.alert('提示', '该仓库正在使用中，不能删除！',
										'warning');
							}
						}
					});
				}
			}
		});

	}
	;
}

// 拼接ID
var getRowsInfo = function() {
	var ids = [];
	var rows = $('#dg').datagrid('getSelections');
	for (var i = 0; i < rows.length; i++) {
		ids.push(rows[i].ID);
	}
	return ids;
};
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

// 树形菜单
function tree() {
	var queryParams = null;
	$('#MyTree').tree({
		checkbox : false,
		url : Utils.getRootPath() + "/branch/branchDo/getTreeData",
		onClick : function(node) {
			if ($('#MyTree').tree('isLeaf', node.target)) {
				queryParams = {
					'branchsView.branchTotal' : node.id
				};
			} else {
				queryParams = null
			}
			$('#dg').datagrid('options').queryParams = queryParams;
			$("#dg").datagrid('load');
		}
	});
}

function set() {
	var row = $('#dg').datagrid('getSelections');
	if (row.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	var id = row[0].ID;
	var url = Utils.getRootPath() + '/branch/branchDo/setApproveHouse?id=' + id;
	$.messager.confirm('确认 ', '确定设置选中仓库为默认仓库？', function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : url,
				async : false,
				dataType : 'json',
				success : function(result) {
					if (result.result) {
						$.messager.alert('提示', '设置成功!', 'info', function() {
							window.parent.loadDataGrid('goodfile');
						});
					} else if (result.status == "error") {
						$.messager.alert('提示', '设置失败，请联系管理员!', 'error');
					}
				}
			});
		}
		;
	});
}
