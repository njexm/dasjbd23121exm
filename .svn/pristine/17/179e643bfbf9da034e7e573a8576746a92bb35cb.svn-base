//添加
function add(url, title, x, y) {
	window.parent.initAdd(url, title, x, y);
}
// 打开新增页面
function addInfo(title, x, y) {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		var url = Utils.getRootPath() + '/commodityClassify/add?id=' + 0;
		add(url, title, x, y);
		return;
	} else if (rows.length > 1) {
		$.messager.alert('提示', '请选择一条数据进行操作！', 'warning');
		return;
	} else {
		var row = rows[0];
		var url = Utils.getRootPath() + '/commodityClassify/add?id=' + row.ID;
		add(url, title, x, y);
	}
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
	var url = Utils.getRootPath() + '/commodityClassify/edit?id=' + row.ID;
	add(url, title, x, y);
}

function editBrand(title, x, y) {
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
	var url = Utils.getRootPath() + '/commodityClassify/editBrand?id=' + row.ID;
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
function saveCommodityClassify(formId) {
	// 数据有限性判断
	if (validateSubmit(formId)) {
		// 保存
		var url = Utils.getRootPath() + '/commodityClassify/save';
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
						window.parent.loadDataGrid('commodityClassify');
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
	// 数据有限性判断
	if (validateSubmit(formId)) {
		// 保存
		var url = Utils.getRootPath() + '/commodityClassify/update';
		$.ajax({
			type : "post",
			url : url,
			data : $('#' + formId).serialize(),
			async : false,
			dataType : 'json',
			success : function(result) {
				if (result.result) {
					$.messager.alert('提示', '保存成功', 'info', function() {
						window.parent.loadDataGrid('commodityClassify');
						window.parent.closeWinForm();
					});
				} else {
					$.messager.alert('提示', '保存失败，请联系管理员', 'error');
				}
			}
		});
	}
}

// 删除类别
function deleteInfo() {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	} else {
		$.messager
				.confirm(
						'提示',
						'是否确认删除？',
						function(r) {
							if (r) {
								var ids = getRowsInfo();
								if (ids.length == 0) {
									$.messager.alert('提示', '请选择需要操作的数据！',
											'warning');
								} else {
									var url = Utils.getRootPath()
											+ "/commodityClassify/delete?ids="
											+ ids;
									$
											.ajax({
												url : url,
												type : 'GET',
												data : {
													aaaaa : ids
												},
												async : false,
												dataType : 'json',
												success : function(result) {
													if (result.result) {
														$.messager
																.alert(
																		'提示',
																		'删除成功！',
																		'info',
																		function() {
																			window.parent
																					.loadDataGrid('commodityClassify');
																			window.parent
																					.closeWinForm();
																		});
													} else {
														$.messager
																.alert(
																		'提示',
																		'该类别存在销售商品，不可删除！',
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

// 删除品牌
function deleteInfo1() {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	} else {
		$.messager
				.confirm(
						'提示',
						'是否确认删除？',
						function(r) {
							if (r) {
								var ids = getRowsInfo();
								if (ids.length == 0) {
									$.messager.alert('提示', '请选择需要操作的数据！',
											'warning');
								} else {
									var url = Utils.getRootPath()
											+ "/commodityClassify/delete1?ids="
											+ ids;
									$
											.ajax({
												url : url,
												type : 'GET',
												data : {
													aaaaa : ids
												},
												async : false,
												dataType : 'json',
												success : function(result) {
													if (result.result) {
														$.messager
																.alert(
																		'提示',
																		'删除成功！',
																		'info',
																		function() {
																			window.parent
																					.loadDataGrid('commodityClassify');
																			window.parent
																					.closeWinForm();
																		});
													} else {
														$.messager
																.alert(
																		'提示',
																		'该品牌存在销售商品，不可删除！',
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

// 初始化父级菜单下拉框
function initParentTree() {
	$('#parentID').combotree({
		url : Utils.getRootPath() + "/commodityClassify/getTreeData",
		onLoadSuccess : function(node, data) {
			var optionValue = $('#parentID').attr("optionValue");
			if (optionValue != undefined && optionValue != "") {
				$('#parentID').combotree('setValue', optionValue);
			}
		}
	});
}

function openAddGoodsType() {
	var url = Utils.getRootPath() + '/goodsFile/goodsType/openAddGoodsType';
	window.parent.initAdd(url, '新增截单类型', 650, 250);
}

function addGoodsType(formId) {
	// 数据有限性判断
	if (validateSubmit(formId)) {
		// 保存
		var url = Utils.getRootPath() + '/goodsFile/goodsType/addGoodsType';
		$.ajax({
			type : "post",
			url : url,
			data : $('#' + formId).serialize(),
			async : false,
			dataType : 'json',
			success : function(result) {
				if (result.message == "保存成功") {
					$.messager.alert('提示', '新增成功', 'info', function() {
						window.parent.loadDataGrid('purchaseOrders');
						window.parent.closeWinForm();
					});
				} else {
					$.messager.alert('提示', '新增失败，请联系管理员', 'error', function() {
						window.parent.loadDataGrid('purchaseOrders');
						window.parent.closeWinForm();
					});
				}
			}
		});
	}
}

// 起始时间转换
function formatterStartTime(value) {
	var val = (value * 1 - 10);
	if (value == '0') {
		return '0点';
	} else {
		return val + '点';
	}
};
// 跨度转换
function formatterTimeLength(value) {
	var val = (value * 1 - 10);
	return val + '天';
};

function openEdit() {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要编辑的数据！', 'warning');
		return;
	}
	if (rows.length > 1) {
		$.messager.alert('提示', '只能选择一条数据进行编辑！', 'warning');
		return;
	}
	var id = rows[0].ID;
	var url = Utils.getRootPath() + '/goodsFile/goodsType/openEdit?id=' + id;
	window.parent.initAdd(url, '编辑截单类型', 650, 250);
}

// 修改数据
function updateGoodsType(formId) {
	// 数据有限性判断
	if (validateSubmit(formId)) {
		// 保存
		var url = Utils.getRootPath() + '/goodsFile/goodsType/updateGoodsType';
		$.ajax({
			type : "post",
			url : url,
			data : $('#' + formId).serialize(),
			async : false,
			dataType : 'json',
			success : function(result) {
				if (result.message == "更新成功") {
					$.messager.alert('提示', '保存成功', 'info', function() {
						window.parent.loadDataGrid('purchaseOrders');
						window.parent.closeWinForm();
					});
				} else {
					$.messager.alert('提示', '保存失败，请联系管理员', 'error', function() {
						window.parent.loadDataGrid('purchaseOrders');
						window.parent.closeWinForm();
					});
				}
			}
		});
	}
}

function deleteGoodsType() {
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
				url : Utils.getRootPath()
						+ '/goodsFile/goodsType/deleteGoodsType',
				data : {
					id : ids
				},
				async : false,
				dataType : 'json',
				success : function(result) {
					if (result.result) {
						$.messager.alert('提示', '删除成功', 'info', function() {
							window.parent.loadDataGrid('goodsType');
							window.parent.closeWinForm();
						});
					} else {
						$.messager.alert('提示', '删除失败', 'warning');
					}
				}
			});
		}
		;
	});
}

function openAddWorkStation() {
	var url = Utils.getRootPath() + '/goodsFile/workStation/openAddWorkStation';
	window.parent.initAdd(url, '新增工位信息', 650, 250);
}

function saveNewWorkStation(formId) {
	// 数据有限性判断
	if (validateSubmit(formId)) {
		// 保存
		var url = Utils.getRootPath()
				+ '/goodsFile/workStation/saveNewWorkStation';
		$.ajax({
			type : "post",
			url : url,
			data : $('#' + formId).serialize(),
			async : false,
			dataType : 'json',
			success : function(result) {
				if (result.message == "保存成功") {
					$.messager.alert('提示', '新增成功', 'info', function() {
						window.parent.loadDataGrid('purchaseOrders');
						window.parent.closeWinForm();
					});
				} else {
					$.messager.alert('提示', '新增失败，请联系管理员', 'error', function() {
						window.parent.loadDataGrid('purchaseOrders');
						window.parent.closeWinForm();
					});
				}
			}
		});
	}
}

function openEditWorkStation() {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要编辑的数据！', 'warning');
		return;
	}
	if (rows.length > 1) {
		$.messager.alert('提示', '只能选择一条数据进行编辑！', 'warning');
		return;
	}
	var id = rows[0].ID;
	var url = Utils.getRootPath()
			+ '/goodsFile/workStation/openEditWorkStation?id=' + id;
	window.parent.initAdd(url, '编辑类别信息', 650, 250);
}

// 修改数据
function updateWorkStation(formId) {
	// 数据有限性判断
	if (validateSubmit(formId)) {
		// 保存
		var url = Utils.getRootPath()
				+ '/goodsFile/workStation/updateWorkStation';
		$.ajax({
			type : "post",
			url : url,
			data : $('#' + formId).serialize(),
			async : false,
			dataType : 'json',
			success : function(result) {
				if (result.message == "更新成功") {
					$.messager.alert('提示', '保存成功', 'info', function() {
						window.parent.loadDataGrid('purchaseOrders');
						window.parent.closeWinForm();
					});
				} else {
					$.messager.alert('提示', '保存失败，请联系管理员', 'error', function() {
						window.parent.loadDataGrid('purchaseOrders');
						window.parent.closeWinForm();
					});
				}
			}
		});
	}
}

function deleteWorkStation() {
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
				url : Utils.getRootPath()
						+ '/goodsFile/workStation/deleteWorkStation',
				data : {
					id : ids
				},
				async : false,
				dataType : 'json',
				success : function(result) {
					if (result.result) {
						$.messager.alert('提示', '删除成功', 'info', function() {
							window.parent.loadDataGrid('goodsType');
							window.parent.closeWinForm();
						});
					} else {
						$.messager.alert('提示', '删除失败', 'warning');
					}
				}
			});
		}
		;
	});
}