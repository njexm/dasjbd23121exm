//打开新增页面
function openAdd() {
	var url = Utils.getRootPath()
			+ '/advancePayment/advancePaymentDo/gotoAddAdvancePayment';
	window.parent.initAdd(url, "新增预付款", 1000, 535);
}

// 新增数据
function save(formId) {
	// 数据有限性判断
	if (validateSubmit(formId)) {
		// 保存
		var id = $('#advancePaymentId').val();
		var provider = $('#provider').combobox('getValue');
		var paymentTime = $('#paymentTime').val();
		if (provider == "" || provider == null) {
			$.messager.alert('提示', '请选择供应商', 'warning');
			return;
		}
		if (paymentTime == "" || paymentTime == null) {
			$.messager.alert('提示', '请填写付款时间', 'warning');
			return;
		}
		var data = $('#dg').datagrid('getData');
		if (data.rows.length == 0) {
			$.messager.alert('提示', '该付款单没有记录，请添加！', 'warning');
			return;
		}
		// 提交前，所有行的编辑状态取消
		for (var i = 0; i < data.rows.length; i++) {
			$('#dg').datagrid('endEdit', i);
			if (data.rows[i].PREPAYMENT_MONEY == 0
					|| data.rows[i].PREPAYMENT_MONEY == null) {
				$('#dg').datagrid('beginEdit', i);
				$.messager.alert('提示', '请填写预付款金额！', 'warning');
				return;
			}
		}
		var ids = "";
		var remark = "";
		var money = "";
		for (var i = 0; i < data.rows.length; i++) {
			ids += data.rows[i].ID + ",";
			money += data.rows[i].PREPAYMENT_MONEY + ",";
			remark += data.rows[i].REMARK + ",";
		}
		remark = encodeURI(encodeURI(remark));
		var url = Utils.getRootPath()
				+ '/advancePayment/advancePaymentDo/save?ids=' + ids
				+ '&remark=' + remark + '&money=' + money + '&id=' + id;
		$.ajax({
			type : "post",
			url : url,
			data : $('#' + formId).serialize(),
			async : false,
			dataType : 'json',
			success : function(result) {
				if (result.message == "保存成功") {
					$.messager.alert('提示', '新增成功', 'info', function() {
						window.parent.loadDataGrid('advancePayment');
						window.parent.closeWinForm();
					});
				} else if (result.message == "更新成功") {
					$.messager.alert('提示', '保存成功', 'info', function() {
						$.messager.confirm("操作提示", "是否立即提交审核？", function(data) {
							if (data) {
								toCheck();
							} else {
								window.parent.loadDataGrid('purchaseReceive');
								$("#dg").datagrid('load');
							}
						});
						window.parent.loadDataGrid('purchaseReceive');
						$("#dg").datagrid('load');
					});
				} else if (result.status == "error") {
					$.messager.alert('提示', '新增失败，请联系管理员', 'error');
				}
			}
		});
	}
}

// 删除新增页面datagrid
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
	$.ajax({
		type : "post",
		url : Utils.getRootPath()
				+ '/advancePayment/advancePaymentDo/deleteItem',
		data : {
			id : ids
		},
		async : false,
		dataType : 'json',
		success : function(result) {
			$("#dg").datagrid('load');
		}
	});

};

// 删除list页面数据
function removeData() {
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
	if (row.AUDIT_STATUS == "2") {
		$.messager.alert('提示', '该预付款单已通过审核！', 'warning');
		return;
	} else if (row.AUDIT_STATUS == "3") {
		$.messager.alert('提示', '该预付款单审核不通过！', 'warning');
		return;
	} else if (row.AUDIT_STATUS == "1") {
		$.messager.alert('提示', '该预付款单已提交！', 'warning');
		return;
	} else if (row.AUDIT_STATUS == "4") {
		$.messager.alert('提示', '该预付款单已完成！', 'warning');
		return;
	} else {
		$.messager.confirm('确认 ', '确定要删除 ？', function(r) {
			if (r) {
				$.ajax({
					type : "post",
					url : Utils.getRootPath()
							+ '/advancePayment/advancePaymentDo/delete',
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
};

function addRecord() {
	var data = $('#dg').datagrid('getData');
	var advancePaymentId = $('#advancePaymentId').val();
	// 提交前，所有行的编辑状态取消
	var ids = "";
	var remark = "";
	var money = "";
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		ids += data.rows[i].ID + ",";
		if (data.rows[i].PREPAYMENT_MONEY != null
				&& data.rows[i].PREPAYMENT_MONEY != "") {
			money += data.rows[i].PREPAYMENT_MONEY + ",";
		} else {
			money += "0,";
		}
		if (data.rows[i].REMARK != null && data.rows[i].REMARK != "") {
			remark += data.rows[i].REMARK + ",";
		} else {
			remark += " ,";
		}
		$('#dg').datagrid('beginEdit', i);
	}
	var url = Utils.getRootPath()
			+ '/advancePayment/advancePaymentDo/addRecord';
	$.ajax({
		type : "post",
		url : url,
		data : {
			ids : ids,
			remark : remark,
			money : money,
			advancePaymentId : advancePaymentId
		},
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

// 查看预付款单的基本信息
function openAdvancePayment(id) {
	var url = Utils.getRootPath()
			+ '/advancePayment/advancePaymentDo/gotoDetail?id=' + id;
	add(url, '预付款单详情', 1000, 550);
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
	if (row.AUDIT_STATUS == '1') {
		$.messager.alert('提示', '该预付款单已提交！', 'warning');
		return;
	} else if (row.AUDIT_STATUS == '2') {
		$.messager.alert('提示', '该预付款单审核已通过！', 'warning');
		return;
	} else if (row.AUDIT_STATUS == '4') {
		$.messager.alert('提示', '该预付款单已完成 ！', 'warning');
		return;
	}
	var url = Utils.getRootPath()
			+ '/advancePayment/advancePaymentDo/gotoEdit?id=' + row.ID;
	add(url, title, x, y);
}
// 打开审核页面
function openCheck(title, x, y) {
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
	if (row.AUDIT_STATUS == '0') {
		$.messager.alert('提示', '该预付款单还未提交！', 'warning');
		return;
	} else if (row.AUDIT_STATUS == '2') {
		$.messager.alert('提示', '该预付款单审核已通过！', 'warning');
		return;
	} else if (row.AUDIT_STATUS == '3') {
		$.messager.alert('提示', '该预付款单正在处理中 ！', 'warning');
		return;
	} else if (row.AUDIT_STATUS == '4') {
		$.messager.alert('提示', '该预付款单已完成 ！', 'warning');
		return;
	}
	var url = Utils.getRootPath()
			+ '/advancePayment/advancePaymentDo/gotoCheck?id=' + row.ID;
	add(url, title, x, y);
}

// 审核通过
function checkPass() {
	var id = $('#advancePaymentId').val();
	var url = Utils.getRootPath()
			+ '/advancePayment/advancePaymentDo/checkPass?id=' + id;
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
						$.messager.alert('提示', '系统错误，请联系管理员', 'error');
					}
				}
			});
		}
	});
}

// 提交审核(纯提交审核 )
function toCheck() {
	var id = $('#advancePaymentId').val();
	var status = $('#auditStatus').val();
	if (status == 1) {
		$.messager.alert('提示', '该费用单已提交审核，不可再次提交！', 'warning');
		return;
	} else if (status == 3) {
		$.messager.alert('提示', '该费用单审核未通过，请处理后提交！', 'warning');
		return;
	} else if (status == 2) {
		$.messager.alert('提示', '该费用单审核已通过，不可再次提交！', 'warning');
		return;
	}
	$.messager.confirm('确认 ', '确定提交审核？', function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : Utils.getRootPath()
						+ '/advancePayment/advancePaymentDo/toCheck',
				data : {
					'id' : id
				},
				async : false,
				dataType : 'json',
				success : function(result) {
					if (result.result) {
						$.messager.alert('提示', '提交成功', 'info', function() {
							window.parent.loadDataGrid('purchaseReturn');
							window.parent.closeWinForm();
						});
					} else if (result.status == "error") {
						$.messager.alert('提示', '提交失败，请联系管理员', 'error');
						$("#dg").datagrid('load');
					}
				}
			});
		}
		;
	});
}

// 提交审核(带保存功能)
function submmitCheck(formId) {
	// 数据有限性判断
	if (validateSubmit(formId)) {
		// 保存
		var id = $('#advancePaymentId').val();
		var provider = $('#provider').combobox('getValue');
		var paymentTime = $('#paymentTime').val();
		if (provider == "" || provider == null) {
			$.messager.alert('提示', '请选择供应商', 'warning');
			return;
		}
		if (paymentTime == "" || paymentTime == null) {
			$.messager.alert('提示', '请填写付款时间', 'warning');
			return;
		}
		var data = $('#dg').datagrid('getData');
		if (data.rows.length == 0) {
			$.messager.alert('提示', '该付款单没有记录，请添加！', 'warning');
			return;
		}
		// 提交前，所有行的编辑状态取消
		for (var i = 0; i < data.rows.length; i++) {
			$('#dg').datagrid('endEdit', i);
			if (data.rows[i].PREPAYMENT_MONEY == 0
					|| data.rows[i].PREPAYMENT_MONEY == null) {
				$('#dg').datagrid('beginEdit', i);
				$.messager.alert('提示', '请填写预付款金额！', 'warning');
				return;
			}
		}
		var ids = "";
		var remark = "";
		var money = "";
		for (var i = 0; i < data.rows.length; i++) {
			ids += data.rows[i].ID + ",";
			money += data.rows[i].PREPAYMENT_MONEY + ",";
			remark += data.rows[i].REMARK + ",";
		}
		remark = encodeURI(encodeURI(remark));
		var urls = Utils.getRootPath()
				+ '/advancePayment/advancePaymentDo/save?ids=' + ids
				+ '&remark=' + remark + '&money=' + money + '&id=' + id;
		$.ajax({
			type : "post",
			url : urls,
			data : $('#' + formId).serialize(),
			async : false,
			dataType : 'json',
			success : function(result) {
				if (result.message == "更新成功") {
					window.parent.loadDataGrid('purchaseReceive');
					$("#dg").datagrid('load');
				} else if (result.status == "error") {
					$.messager.alert('提示', '保存失败，请联系管理员', 'error');
				}
			}
		});
		var status = $('#auditStatus').val();
		if (status == 1) {
			$.messager.alert('提示', '该费用单已提交审核，不可再次提交！', 'warning');
			return;
		} else if (status == 3) {
			$.messager.alert('提示', '该费用单审核未通过，请处理后提交！', 'warning');
			return;
		} else if (status == 2) {
			$.messager.alert('提示', '该费用单审核已通过，不可再次提交！', 'warning');
			return;
		}
		$.messager.confirm('确认 ', '确定提交审核？', function(r) {
			if (r) {
				$.ajax({
					type : "post",
					url : Utils.getRootPath()
							+ '/advancePayment/advancePaymentDo/toCheck',
					data : {
						'id' : id
					},
					async : false,
					dataType : 'json',
					success : function(result) {
						if (result.result) {
							$.messager.alert('提示', '提交成功', 'info', function() {
								window.parent.loadDataGrid('purchaseReturn');
								window.parent.closeWinForm();
							});
						} else if (result.status == "error") {
							$.messager.alert('提示', '提交失败，请联系管理员', 'error');
							$("#dg").datagrid('load');
						}
					}
				});
			}
			;
		});
	}
}

// 审核不通过
function CheckItemAjax(id, reason, type) {
	var url = Utils.getRootPath() + '/advancePayment/advancePaymentDo/check';
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
			if (result.result) {
				$.messager.alert('提示', '审核完成！', 'info', function() {
					window.parent.loadDataGrid('purchaseReturn');
					window.parent.closeWinForm();
				});
			} else if (result.status == "error") {
				$.messager.alert('提示', '系统错误，请联系管理员', 'error');
			}
		}
	});
}
// 审核不通过
function checkNoPass(type) {
	var id = $('#advancePaymentId').val();
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