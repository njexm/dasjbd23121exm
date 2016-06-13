//打开编辑页面
function openEdit() {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要编辑的团购退货单！', 'warning');
		return;
	}
	if (rows.length > 1) {
		$.messager.alert('提示', '只能选择一条团购退货单进行编辑！', 'warning');
		return;
	}
	var row = rows[0];
	if (row.STATUE == "1") {
		$.messager.alert('提示', '该团购退货单已提交！', 'warning');
		return;
	} else if (row.STATUE == "2") {
		$.messager.alert('提示', '该团购退货单已通过审核！', 'warning');
		return;
	} else if (row.STATUE == "4") {
		$.messager.alert('提示', '该团购退货单已完成！', 'warning');
		return;
	} else if (row.STATUE == "5") {
		$.messager.alert('提示', '该团购退货单已作废！', 'warning');
		return;
	} else {
		var url = Utils.getRootPath()
				+ '/wholesaleGroupPurchase/return/gotoEdit?id=' + row.ID;
		window.parent.initAdd(url, '编辑团购退货单', 1000, 550);
	}
}

// 打开明细页面
function openReturnDetail(id) {
	var url = Utils.getRootPath()
			+ '/wholesaleGroupPurchase/return/gotoDetail?id=' + id;
	window.parent.initAdd(url, '团购退货单明细', 1000, 550);
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
	if (row.STATUE == "2") {
		$.messager.alert('提示', '该团购退货单已通过审核！', 'warning');
		return;
	} else if (row.STATUE == "3") {
		$.messager.alert('提示', '该团购退货单正在处理中！', 'warning');
		return;
	} else if (row.STATUE == "0") {
		$.messager.alert('提示', '该团购退货单还未提交！', 'warning');
		return;
	} else if (row.STATUE == "4") {
		$.messager.alert('提示', '该团购退货单已完成！', 'warning');
		return;
	} else if (row.STATUE == "5") {
		$.messager.alert('提示', '该团购退货单已作废！', 'warning');
		return;
	} else {
		var url = Utils.getRootPath()
				+ '/wholesaleGroupPurchase/return/gotoCheck?id=' + row.ID;
		add(url, title, x, y);
	}
}

// 移除商品
function removeGoods() {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要删除的商品！', 'warning');
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
	var data = $('#dg').datagrid('getData');
	var idStr = "";
	var returnNums = "";
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		$('#dg').datagrid('beginEdit', i);
		idStr += data.rows[i].ID + ",";
		if (data.rows[i].RETURNNUMS == null || data.rows[i].RETURNNUMS == ''
				|| data.rows[i].RETURNNUMS == 'undefiend') {
			returnNums += "0.00,";
		} else {
			returnNums += data.rows[i].RETURNNUMS + ",";
		}
	}
	$.messager.confirm('确认 ', '确定要删除 ？', function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : Utils.getRootPath()
						+ '/wholesaleGroupPurchase/return/removeGoods?returnNums='
						+ returnNums + '&idStr=' + idStr,
				data : {
					id : ids
				},
				async : false,
				dataType : 'json',
				success : function(result) {
					if (result.result) {
						$.messager.alert('提示', '删除成功', 'info', function() {
							window.parent.loadDataGrid('goodfile');
							$("#dg").datagrid('load');
						});
					} else if (result.status == "error") {
						$.messager.alert('提示', '删除失败，请联系管理员!', 'error');
						$("#dg").datagrid('load');
					}
				}
			});
		}
		;
	});
}

// 保存编辑信息
function updateReturn(formId) {
	var data = $('#dg').datagrid('getData');
	var wholeReturnId = $('#wholeReturnId').val();
	if (data.rows.length == 0) {
		$.messager.alert('提示', '该团购退货单没有商品，请删除！', 'warning');
		return;
	}
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		$('#dg').datagrid('beginEdit', i);
		if (data.rows[i].RETURNNUMS <= 0 || data.rows[i].RETURNNUMS == null) {
			$.messager.alert('提示', '请填写实际退货的数量！', 'warning');
			return;
		}
		if (parseFloat(data.rows[i].RETURNNUMS) > parseFloat(data.rows[i].NUMS)) {
			$.messager.alert('提示', '实际退货的数量不能大于销售数量！', 'warning');
			return;
		}
	}
	var ids = "";
	var returnNums = "";
	for (var i = 0; i < data.rows.length; i++) {
		ids += data.rows[i].ID + ",";
		returnNums += data.rows[i].RETURNNUMS + ",";
	}
	var url = Utils.getRootPath()
			+ '/wholesaleGroupPurchase/return/updateReturn?id=' + wholeReturnId
			+ '&returnNums=' + returnNums + '&ids=' + ids;
	$.ajax({
		type : "post",
		url : url,
		async : true,
		data : $('#' + formId).serialize(),
		dataType : 'json',
		success : function(result) {
			if (result.result) {
				$.messager.alert('提示', '保存成功', 'info', function() {
					$.messager.confirm("操作提示", "是否立即提交审核？", function(data) {
						if (data) {
							submitCheckOnly();
						} else {
							window.parent.loadDataGrid('purchaseOrders');
							$("#dg").datagrid('load');
						}
					});
					window.parent.loadDataGrid('purchaseOrders');
					$("#dg").datagrid('load');
				});
			} else {
				$.messager.alert('提示', '保存失败，请联系管理员！', 'error');
			}
		}
	});
}

// 单独提交，提交审核
function submitCheckOnly() {
	var wholeReturnId = $('#wholeReturnId').val();
	var data = $('#dg').datagrid('getData');
	if (data.rows.length == 0) {
		$.messager.alert('提示', '该退货单没有商品，请删除！', 'warning');
		return;
	}
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		$('#dg').datagrid('beginEdit', i);
		if (data.rows[i].RETURNNUMS <= 0 || data.rows[i].RETURNNUMS == null) {
			$.messager.alert('提示', '请填写实际退货的数量保存后再提交！', 'warning');
			return;
		}
		if (parseFloat(data.rows[i].RETURNNUMS) > parseFloat(data.rows[i].NUMS)) {
			$.messager.alert('提示', '实际退货的数量不能大于销售数量，请修改并保存后再提交！', 'warning');
			return;
		}
	}
	var status = '$!{WGPurchaseReturn.statue}';
	if (status == "1") {
		$.messager.alert('提示', '该退货单已经提交！', 'warning');
		return;
	} else if (status == "2") {
		$.messager.alert('提示', '该退货单审核已通过！', 'warning');
		return;
	} else if (status == "3") {
		$.messager.alert('提示', '该退货单正在处理中！', 'warning');
		return;
	} else if (status == "4") {
		$.messager.alert('提示', '该退货单已完成！', 'warning');
		return;
	} else if (status == "5") {
		$.messager.alert('提示', '该退货单已作废！', 'warning');
		return;
	} else {
		var url = Utils.getRootPath()
				+ '/wholesaleGroupPurchase/return/submitCheck?id='
				+ wholeReturnId;
		$.ajax({
			type : "post",
			url : url,
			async : true,
			dataType : 'json',
			success : function(result) {
				if (result.message == "保存成功") {
					$.messager.alert('提示', '提交成功', 'info', function() {
						window.parent.loadDataGrid('purchaseOrdersEdit');
						window.parent.closeWinForm();
					});
				} else {
					$.messager.alert('提示', '提交失败，请联系管理员', 'error');
				}
			}
		});
	}
}

//提交审核并保存
function submitCheckAndSave(formId) {
	var wholeReturnId = $('#wholeReturnId').val();
	var data = $('#dg').datagrid('getData');
	if (data.rows.length == 0) {
		$.messager.alert('提示', '该团购退货单没有商品，请删除！', 'warning');
		return;
	}
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		$('#dg').datagrid('beginEdit', i);
		if (data.rows[i].RETURNNUMS <= 0 || data.rows[i].RETURNNUMS == null) {
			$.messager.alert('提示', '请填写实际退货的数量！', 'warning');
			return;
		}
		if (parseFloat(data.rows[i].RETURNNUMS) > parseFloat(data.rows[i].NUMS)) {
			$.messager.alert('提示', '实际退货的数量不能大于销售数量！', 'warning');
			return;
		}
	}
	var ids = "";
	var returnNums = "";
	for (var i = 0; i < data.rows.length; i++) {
		ids += data.rows[i].ID + ",";
		returnNums += data.rows[i].RETURNNUMS + ",";
	}
	var urls = Utils.getRootPath()
			+ '/wholesaleGroupPurchase/return/updateReturn?id=' + wholeReturnId
			+ '&returnNums=' + returnNums + '&ids=' + ids;
	$.ajax({
		type : "post",
		url : urls,
		async : false,
		data : $('#' + formId).serialize(),
		dataType : 'json',
		success : function(result) {
			if (result.result) {
				window.parent.loadDataGrid('purchaseOrders');
				$("#dg").datagrid('load');
			} else {
				$.messager.alert('提示', '保存失败，请联系管理员!', 'error');
			}
		}
	});
	var status = '$!{WGPurchaseReturn.statue}';
	if (status == "1") {
		$.messager.alert('提示', '该退货单已经提交！', 'warning');
		return;
	} else if (status == "2") {
		$.messager.alert('提示', '该退货单审核已通过！', 'warning');
		return;
	} else if (status == "3") {
		$.messager.alert('提示', '该退货单正在处理中！', 'warning');
		return;
	} else if (status == "4") {
		$.messager.alert('提示', '该退货单已完成！', 'warning');
		return;
	} else if (status == "5") {
		$.messager.alert('提示', '该退货单已作废！', 'warning');
		return;
	} else {
		var url = Utils.getRootPath()
				+ '/wholesaleGroupPurchase/return/submitCheck?id='
				+ wholeReturnId;
		$.ajax({
			type : "post",
			url : url,
			async : true,
			dataType : 'json',
			success : function(result) {
				if (result.message == "保存成功") {
					$.messager.alert('提示', '提交成功', 'info', function() {
						window.parent.loadDataGrid('purchaseOrdersEdit');
						window.parent.closeWinForm();
					});
				} else {
					$.messager.alert('提示', '提交失败，请联系管理员', 'error');
				}
			}
		});
	}
}



// 审核通过
function checkPass() {
	var data = $('#dg').datagrid('getData');
	var ids = "";
	var returnNums = "";
	var returnAmount = "";
	for (var i = 0; i < data.rows.length; i++) {
		ids += data.rows[i].ID + ",";
		returnNums += data.rows[i].RETURNNUMS + ",";
		returnAmount += data.rows[i].RETURNAMOUNT + ",";
	}
	if (ids.length == 0) {
		$.messager.alert('提示', '没有商品，确定审核通过？', 'warning');
		return;
	}
	var id = $('#wholeReturnId').val();
	var url = Utils.getRootPath()
			+ '/wholesaleGroupPurchase/return/checkPass?id=' + id
			+ '&returnNums=' + returnNums + '&ids=' + ids + '&returnAmount='
			+ returnAmount;
	$.messager.confirm('确认 ', '确定审核通过 ？', function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : url,
				async : false,
				dataType : 'json',
				success : function(result) {
					if (result.result) {
						$.messager.alert('提示', '审核通过！', 'info', function() {
							window.parent.loadDataGrid('purchaseReturn');
							window.parent.closeWinForm();
						});
					} else if (result.status == "error") {
						$.messager.alert('提示', '系统错误，请联系管理员！', 'error');
					}
				}
			});
		}
	});
}

// 审核不通过
function checkNoPass(type) {
	var id = $('#wholeReturnId').val();
	if (type == '1') {
		$.messager.prompt('提示信息', '请输入审核未通过的原因：', function(r) {
			if (r) {
				CheckItemAjax(id, r, type);
			} else if (r.length == 0 || r == null || r == 'undefined') {
				r = " ";
				CheckItemAjax(id, r, type);
			}
		});
	}
}

// 审核不通过(原因)
function CheckItemAjax(id, reason, type) {
	var url = Utils.getRootPath()
			+ '/wholesaleGroupPurchase/return/checkNoPass';
	$.ajax({
		type : "post",
		url : url,
		data : {
			'id' : id,
			'reason' : reason,
			'type' : type
		},
		async : false,
		dataType : 'json',
		success : function(result) {
			if (result.message == "保存成功") {
				$.messager.alert('提示', '审核完成', 'info', function() {
					window.parent.loadDataGrid('purchaseOrders');
					window.parent.closeWinForm();
				});
			} else {
				$.messager.alert('提示', '审核失败，请联系管理员！', 'error');
			}
		}
	});
}

function deleteReturn() {
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
	var row = rows[0];
	if (row.STATUE == "2") {
		$.messager.alert('提示', '该退货单已通过审核，不可刪除!', 'warning');
		return;
	} else if (row.STATUE == "3") {
		$.messager.alert('提示', '该退货单待处理，不可刪除!', 'warning');
		return;
	} else if (row.STATUE == "1") {
		$.messager.alert('提示', '该退货单已提交，不可刪除!', 'warning');
		return;
	} else if (row.STATUE == "4") {
		$.messager.alert('提示', '该退货单已完成，不可刪除!', 'warning');
		return;
	} else {
		$.messager.confirm('确认 ', '确定要删除 ？', function(r) {
			if (r) {
				$.ajax({
					type : "post",
					url : Utils.getRootPath()
							+ '/wholesaleGroupPurchase/return/deleteReturn',
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
							$.messager.alert('提示', '删除失败，请联系管理员！', 'error');
						}
					}
				});
			}
			;
		});
	}
}