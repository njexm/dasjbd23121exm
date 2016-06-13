//打开新增页面
function openAdd() {
	var url = Utils.getRootPath()
			+ '/supplierSettletOrder/supplierSettletOrderDo/gotoAdd';
	window.parent.initAdd(url, "新增供应商付款单", 1200, 560);
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
				+ '/supplierSettletOrder/supplierSettletOrderDo/deleteItem',
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

// 新增数据
function save(formId) {
	var paymentMode = $('#paymentMode').combobox('getValue');
	if (paymentMode.length == 0) {
		$.messager.alert('提示', '请选择付款方式！', 'warning');
		return;
	}
	// 数据有限性判断
	if (validateSubmit(formId)) {
		// 保存
		var id = $('#supplierSettletOrderId').val();
		var customer = $('#customer').combobox('getValue');
		if (customer == "" || customer == null) {
			$.messager.alert('提示', '请选择供应商！', 'warning');
			return;
		}
		var data = $('#dg').datagrid('getData');
		for (var i = 0; i < data.rows.length; i++) {
			$('#dg').datagrid('endEdit', i);
			var actual = data.rows[i].ACTUAL_MONEY;
			var favorable = data.rows[i].FAVORABLE_MONEY;
			if (actual == null || actual == "") {
				data.rows[i].ACTUAL_MONEY = "0";
			}
			if (favorable == null || favorable == "") {
				data.rows[i].FAVORABLE_MONEY = "0";
			}
			var unpaid = data.rows[i].UNPAID_MONEY;
			var actualMoney = actual * 1;
			var favorableMoney = favorable * 1;
			var unpaidMoney = unpaid * 1;
			if (unpaidMoney < actualMoney + favorableMoney) {
				$.messager.alert('提示', '付款金额大于未付金额！', 'warning');
				$('#dg').datagrid('beginEdit', i);
				return;
			}
			var time = data.rows[i].NEW_TIME;
			if (time == null) {
				$.messager.alert('提示', '请输入约定付款时间！', 'warning');
				$('#dg').datagrid('beginEdit', i);
				return;
			}
			// var r = new
			// RegExp("^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01])$");
			// alert(r.test(time));
		}
		var remark = "";
		for (var i = 0; i < data.rows.length; i++) {
			if(data.rows[i].REMARKS!=null&&data.rows[i].REMARKS!=""&&data.rows[i].REMARKS!='undefiend'&&data.rows[i].REMARKS.length>=0){
				remark += data.rows[i].REMARKS + ",";
			}else{
				remark += " ,";
			}
		}
		remark = encodeURI(encodeURI(remark));
		var rows = JSON.stringify(data.rows);
		var url = Utils.getRootPath()
				+ '/supplierSettletOrder/supplierSettletOrderDo/save?id=' + id
				+ '&rows=' + rows + '&remark=' + remark;
		$.ajax({
			type : "post",
			url : url,
			data : $('#' + formId).serialize(),
			async : false,
			dataType : 'json',
			success : function(result) {
				if (result.result && id.length == 0) {
					$.messager.alert('提示', '新增成功', 'info', function() {
						window.parent.loadDataGrid('advancePayment');
						window.parent.closeWinForm();
					});
				} else if (result.result && id.length != 0) {
					$.messager.alert('提示', '修改成功', 'info', function() {
						window.parent.loadDataGrid('advancePayment');
						window.parent.closeWinForm();
					});
				} else if (result.status == "error") {
					$.messager.alert('提示', '新增失败，请联系管理员！', 'error');
				}
			}
		});
	}
}

// 查看基本信息
function opensupplierSettletOrder(id) {
	var url = Utils.getRootPath()
			+ '/supplierSettletOrder/supplierSettletOrderDo/gotoDetail?id=' + id;
	add(url, '供应商费用单详情', 1200, 560);
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
	if (row.AUDIT_STATUS == '2') {
		$.messager.alert('提示', '该结算单审核已通过，不可编辑！', 'warning');
		return;
	} else if (row.AUDIT_STATUS == '1') {
		$.messager.alert('提示', '该结算单已提交审核，不可编辑！', 'warning');
		return;
	}
	var url = Utils.getRootPath()
			+ '/supplierSettletOrder/supplierSettletOrderDo/gotoEdit?id=' + row.ID;
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
	if (row.AUDIT_STATUS == 2) {
		$.messager.alert('提示', '该结算单已通过审核，不可再次审核！', 'warning');
		return;
	} else if (row.AUDIT_STATUS == 3) {
		$.messager.alert('提示', '该结算单审核未通过，不可再次审核！', 'warning');
		return;
	} else if (row.AUDIT_STATUS == 0) {
		$.messager.alert('提示', '该结算单未提交审核！', 'warning');
		return;
	}
	var url = Utils.getRootPath()
			+ '/supplierSettletOrder/supplierSettletOrderDo/gotoCheck?id=' + row.ID;
	add(url, title, x, y);
}

// 审核通过
function checkPass() {
	var id = $('#supplierSettletOrderId').val();
	var data = $('#dg').datagrid('getData');
	var rows = JSON.stringify(data.rows);
	var url = Utils.getRootPath()
			+ '/supplierSettletOrder/supplierSettletOrderDo/checkPass?id=' + id
			+ '&rows=' + rows;
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
function CheckItemAjax(id, reason, type) {
	var url = Utils.getRootPath()
			+ '/supplierSettletOrder/supplierSettletOrderDo/check';
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
			if (result.message == "更新成功") {
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
// 审核不通过
function checkNoPass(type) {
	var id = $('#supplierSettletOrderId').val();
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

// 提交审核
function toCheck() {
	var id = $('#supplierSettletOrderId').val();
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
						+ '/supplierSettletOrder/supplierSettletOrderDo/toCheck',
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
						$.messager.alert('提示', '提交失败，请联系管理员！', 'error');
						$("#dg").datagrid('load');
					}
				}
			});
		}
		;
	});
}

// 删除list页面数据
function removeData() {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	var id = rows[0].ID;
	if (rows[0].AUDIT_STATUS == 2) {
		$.messager.alert('提示', '该结算单已通过审核，不可删除！', 'warning');
		return;
	} else if (rows[0].AUDIT_STATUS == 1) {
		$.messager.alert('提示', '该结算单已提交 ，不可删除！', 'warning');
		return;
	} else if (rows[0].AUDIT_STATUS == 3) {
		$.messager.alert('提示', '该结算单审核不通过，不可删除！', 'warning');
		return;
	} else if (rows[0].AUDIT_STATUS == 4) {
		$.messager.alert('提示', '该结算单已完成，不可删除！', 'warning');
		return;
	}
	$.messager.confirm('确认 ', '确定要删除 ？', function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : Utils.getRootPath()
						+ '/supplierSettletOrder/supplierSettletOrderDo/delete',
				data : {
					'id' : id
				},
				async : false,
				dataType : 'json',
				success : function(result) {
					if (result.result) {
						$.messager.alert('提示', '删除成功', 'info', function() {
							$("#dg").datagrid('load');
						});
					} else if (result.status == "error") {
						$.messager.alert('提示', '删除失败，请联系管理员！', 'error');
						$("#dg").datagrid('load');
					}
				}
			});
		}
		;
	});
};

// 新增时判断已有的供应商结算单状态
function customer() {
	var id = $('#customer').combobox('getValue');
	var url = Utils.getRootPath()
			+ '/supplierSettletOrder/supplierSettletOrderDo/customer?id=' + id;
	$.ajax({
		type : "post",
		url : url,
		async : false,
		dataType : 'json',
		success : function(result) {
			if (result.result == true) {
				$.messager.alert('提示', '该团购批发客户存在未审核通过结算单，不可选择！', 'warning');
				return;
			} else if (result.result == false) {
				searchData();
			}
		}
	});
}

function searchData() {
	var queryParams = {
		'supplierSettletOrder.customer.id' : $('#customer').combobox('getValue')
	};
	$('#dg').datagrid('options').queryParams = queryParams;
	$("#dg").datagrid('load');
}
