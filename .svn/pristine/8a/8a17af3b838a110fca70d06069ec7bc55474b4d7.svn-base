//打开填写预收款页面
function openWrite() {
	var url = Utils.getRootPath() + '/settlement/branchAdvance/openWrite';
	add(url, '填写预收款', 1000, 535);
}

function addRecord() {
	var url = Utils.getRootPath() + '/settlement/advanceDetail/addRecord';
	$.ajax({
		type : "post",
		url : url,
		async : false,
		dataType : 'json',
		success : function(result) {
			if (result.result) {
				$('#dg').datagrid('reload');
			} else if (result.status == "error") {
				$('#dg').datagrid('reload');
			}
		}
	});
}

function updateRecord() {
	var rows = $('#dg').datagrid('getRows');
	for (var i = 0; i < rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		if (rows[i].ADVANCE == 0 || rows[i].ADVANCE == null) {
			$('#dg').datagrid('beginEdit', i);
			$.messager.alert('提示', '请填写预收款金额！', 'warning');
			return;
		}
	}
	var ids = "";
	var advance = "";
	var remark = "";
	for (var i = 0; i < rows.length; i++) {
		ids += rows[i].ID + ",";
		advance += rows[i].ADVANCE + ",";
		remark += rows[i].REMARK + ",";
	}
	var url = Utils.getRootPath()
			+ '/settlement/advanceDetail/updateRecord?advance=' + advance
			+ '&ids=' + ids;
	$.ajax({
		type : "post",
		url : url,
		data : {
			'remark' : remark
		},
		async : true,
		dataType : 'json',
		success : function(result) {
			if (result.result) {
				$("#dg").datagrid('load');
			} else {
				$("#dg").datagrid('load');
			}
		}
	});
}

function deleteRecord() {
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
				url : Utils.getRootPath()
						+ '/settlement/advanceDetail/deleteRecord',
				data : {
					id : ids
				},
				async : false,
				dataType : 'json',
				success : function(result) {
					if (result.result) {
						$.messager.alert('提示', '删除成功', 'info', function() {
							$("#dg").datagrid('load');
						});
					} else if (result.status == "error") {
						$.messager.alert('提示', '删除失败，请联系管理员', 'error');
						$("#dg").datagrid('load');
					}
				}
			});
		}
		;
	});
}

function saveBills(formId) {
	var ids = getRowsId();
	// 数据有限性判断
	if (validateSubmit(formId)) {
		var branch = $('#branch_id').combobox('getValue');
		var date = $('#gatheringDate').datebox('getValue');
		if (branch == '') {
			$.messager.alert('提示', '分店未选择或填写无效', 'warning');
			return;
		} else if (date == '') {
			$.messager.alert('提示', '收款日期未选择或填写无效', 'warning');
			return;
		} else if (ids.length == 0) {
			$.messager.alert('提示', '无预收款记录，请重新确认！', 'warning');
			return;
		} else {
			// 保存
			var url = Utils.getRootPath()
					+ '/settlement/advanceDetail/saveBills?ids=' + ids;
			$.ajax({
				type : "post",
				url : url,
				data : $('#' + formId).serialize(),
				async : false,
				dataType : 'json',
				success : function(result) {
					if (result.result) {
						$.messager.alert('提示', '新增成功', 'info', function() {
							window.parent.loadDataGrid('taxManager');
							window.parent.closeWinForm();
						});
					} else if (result.status == "error") {
						$.messager.alert('提示', '新增失败，请联系管理员', 'error');
					}
				}
			});
		}
	}
}

// 获取所有的数据的ID
var getRowsId = function() {
	var ids = [];
	var data = $('#dg').datagrid('getData');
	for (var i = 0; i < data.rows.length; i++) {
		var row = data.rows[i];
		ids.push(row.ID);
	}
	return ids;
};

function openAdvance(id) {
	var url = Utils.getRootPath()
			+ '/settlement/advanceDetail/openAdvanceDetail?id=' + id;
	window.parent.initAdd(url, "预收款明细", 1000, 535);
}

function deleteWrite() {
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
	var row = rows[0];
	if (row.STATUE == "finish") {
		$.messager.alert('提示', '该收款单审核已通过，不可删除！', 'warning');
		return;
	} else {
		$.messager.confirm('确认 ', '确定要删除 ？', function(r) {
			if (r) {
				$.ajax({
					type : "post",
					url : Utils.getRootPath()
							+ '/settlement/branchAdvance/deleteWrite',
					data : {
						id : ids
					},
					async : false,
					dataType : 'json',
					success : function(result) {
						if (result.result) {
							$.messager.alert('提示', '删除成功', 'info', function() {
								$("#dg").datagrid('load');
							});
						} else if (result.status == "error") {
							$.messager.alert('提示', '删除失败，请联系管理员', 'error');
							$("#dg").datagrid('load');
						}
					}
				});
			}
			;
		});
	}
}

function editWrite() {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	var id = rows[0].ID;
	if (rows[0].STATUE == "finish") {
		$.messager.alert('提示', '该收款单审核已通过，不可编辑！', 'warning');
		return;
	} else {
		var url = Utils.getRootPath()
				+ '/settlement/advanceDetail/openAdvanceEdit?id=' + id;
		window.parent.initAdd(url, "编辑预收款", 1000, 535);
	}
}

function updateBills(formId) {
	var ids = getRowsId();
	// 数据有限性判断
	if (validateSubmit(formId)) {
		var branch = $('#branch_id').combobox('getValue');
		var date = $('#gatheringDate').datebox('getValue');
		if (branch == '') {
			$.messager.alert('提示', '分店未选择或填写无效', 'warning');
			return;
		} else if (date == '') {
			$.messager.alert('提示', '收款日期未选择或填写无效', 'warning');
			return;
		} else {
			// 保存
			var url = Utils.getRootPath()
					+ '/settlement/advanceDetail/updateBills?ids=' + ids;
			$.ajax({
				type : "post",
				url : url,
				data : $('#' + formId).serialize(),
				async : false,
				dataType : 'json',
				success : function(result) {
					if (result.result) {
						$.messager.alert('提示', '提交成功', 'info', function() {
							window.parent.loadDataGrid('taxManager');
							window.parent.closeWinForm();
						});
					} else if (result.status == "error") {
						$.messager.alert('提示', '提交失败，请联系管理员', 'error');
					}
				}
			});
		}
	}
}

function addRecordEdit() {
	var id = $('#branchAdvance').val();
	var url = Utils.getRootPath()
			+ '/settlement/advanceDetail/addRecordEdit?id=' + id;
	$.ajax({
		type : "post",
		url : url,
		async : false,
		dataType : 'json',
		success : function(result) {
			if (result.result) {
				$('#dg').datagrid('reload');
			} else if (result.status == "error") {
				$('#dg').datagrid('reload');
			}
		}
	});
}

// 打开审核页面
function openCheck(title, x, y) {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	if (rows.length > 1) {
		$.messager.alert('提示', '只能选择一条数据进行操作！', 'warning');
		return;
	}
	var row = rows[0];
	if (row.STATUE == "finish") {
		$.messager.alert('提示', '该收款单已通过审核，不可再次审核！', 'warning');
		return;
	} else if (row.STATUE == "dead") {
		$.messager.alert('提示', '该收款单审核未通过，请新建收款单！', 'warning');
		return;
	} else if (row.STATUE == "wait") {
		$.messager.alert('提示', '该收款单未保存或提交，请等待其提交后再审核！', 'warning');
		return;
	} else {
		var url = Utils.getRootPath()
				+ '/settlement/advanceDetail/gotoEditCheck?id=' + row.ID;
		add(url, "审核预收款", 1000, 535);
	}
}

// 审核通过
function checkPass() {
	var id = $('#branchAdvance').val();
	var url = Utils.getRootPath() + '/settlement/advanceDetail/checkPass?id='
			+ id;
	$.messager.confirm('确认 ', '确定审核通过？', function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : url,
				async : false,
				dataType : 'json',
				success : function(result) {
					if (result.result) {
						$.messager.alert('提示', '审核通过！', 'info', function() {
							window.parent.loadDataGrid('purchaseOrders');
							window.parent.closeWinForm();
						});
					} else if (result.status == "error") {
						$.messager.alert('提示', '系统错误，请联系管理员', 'error');
					}
				}
			});
		}
	});
}

// 审核不通过
function checkNoPass() {
	var id = $('#branchAdvance').val();
	var url = Utils.getRootPath() + '/settlement/advanceDetail/checkNoPass?id='
			+ id;
	$.messager.confirm('确认 ', '确定审核不能通过？', function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : url,
				async : false,
				dataType : 'json',
				success : function(result) {
					if (result.result) {
						$.messager.alert('提示', '审核不通过！', 'info', function() {
							window.parent.loadDataGrid('purchaseOrders');
							window.parent.closeWinForm();
						});
					} else if (result.status == "error") {
						$.messager.alert('提示', '系统错误，请联系管理员', 'error');
					}
				}
			});
		}
	});
}