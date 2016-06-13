//打开页面
function open(url, title, x, y) {
	window.parent.initAdd(url, title, x, y);
}

// 打开新增采购收货单页面
function addPurchase(title, x, y) {
	var url = Utils.getRootPath()
			+ '/purchase/purchaseOrder/gotoAddPurchaseOrder';
	add(url, title, x, y);
}

// 编辑
function edit(title, x, y) {
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
	if (row.AUDIT_STATUS == "1") {
		$.messager.alert('提示', '该收货单已提交！', 'warning');
		return;
	} else if (row.AUDIT_STATUS == "2") {
		$.messager.alert('提示', '该收货单已通过审核！', 'warning');
		return;
	} else if (row.AUDIT_STATUS == "4") {
		$.messager.alert('提示', '该收货单已完成！', 'warning');
		return;
	} else if (row.AUDIT_STATUS == "5") {
		$.messager.alert('提示', '该收货单已作废！', 'warning');
		return;
	} else {
		var url = Utils.getRootPath()
				+ '/purchaseReceive/purchaseReceiveDo/gotoEditPurchaseReceiveEdit?id='
				+ row.ID;
		open(url, title, x, y);
	}
}

function openPurchaseOrderItem(id) {
	var url = Utils.getRootPath()
			+ '/purchaseReceive/purchaseReceiveDo/gotoDetailPurchaseReceive?id='
			+ id;
	add(url, '采购收货单详情', 900, 535);
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
	var url = Utils.getRootPath()
			+ '/purchaseReceive/purchaseReceiveDo/gotoDetailPurchaseReceive?id='
			+ row.ID;
	add(url, title, x, y);
}
// 添加
function add(url, title, x, y) {
	window.parent.initAdd(url, title, x, y);
}

// 删除
function destroyInfo() {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	var row = rows[0];
	if (row.AUDIT_STATUS == "2") {
		$.messager.alert('提示', '该收货单已通过审核，不可刪除！', 'warning');
		return;
	} else if (row.AUDIT_STATUS == "3") {
		$.messager.alert('提示', '该收货单审核未通过，不可刪除！', 'warning');
		return;
	} else if (row.AUDIT_STATUS == "1") {
		$.messager.alert('提示', '该收货单已提交，不可刪除！', 'warning');
		return;
	} else if (row.AUDIT_STATUS == "4") {
		$.messager.alert('提示', '该收货单已完成，不可刪除！', 'warning');
		return;
	} else {
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
							+ '/purchaseReceive/purchaseReceiveDo/delete',
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
	;
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

// 打开新增退货单页面
function goToAddReturn(title, x, y) {
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
	if (row.AUDIT_STATUS == "0") {
		$.messager.alert('提示', '该收货单还未提交！', 'warning');
		return;
	} else if (row.AUDIT_STATUS == "1") {
		$.messager.alert('提示', '该收货单正在审核中！', 'warning');
		return;
	} else if (row.AUDIT_STATUS == "3") {
		$.messager.alert('提示', '该收货单正在处理中！', 'warning');
		return;
	} else if (row.AUDIT_STATUS == "4") {
		$.messager.alert('提示', '该收货单已完成！', 'warning');
		return;
	} else if (row.AUDIT_STATUS == "5") {
		$.messager.alert('提示', '该收货单已作废！', 'warning');
		return;
	} else {
		var url = Utils.getRootPath()
				+ '/purchase/purchaseReturn/gotoAddPurchaseReturn?id=' + row.ID;
		open(url, title, x, y);
	}
}

// 编辑采购收货单，存入实际收货数量
function purchaseReceive(formId) {
	var id = $('#purchaseReceiveId').val();
	var providerId = $('#providerId').combobox('getValue');
	var branchId = $('#branchId').combobox('getValue');
	var data = $('#dg').datagrid('getData');
	if (data.rows.length == 0) {
		$.messager.alert('提示', '该收货单没有商品，请删除！', 'warning');
		return;
	}
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		if (data.rows[i].ACTUAL_NUM <= 0 || data.rows[i].ACTUAL_NUM == null) {
			for (var j = 0; j < data.rows.length; j++) {
				$('#dg').datagrid('beginEdit', j);
			}
			$.messager.alert('提示', '请填写实际收货数量！', 'warning');
			return;
		}
	}
	var ids = "";
	var num = "";
	var goodsPrice = "";
	var productDate = "";
	for (var i = 0; i < data.rows.length; i++) {
		ids += data.rows[i].ID + ",";
		num += data.rows[i].ACTUAL_NUM + ",";
		productDate += data.rows[i].PRODUCEDATE + ",";
		goodsPrice += data.rows[i].GOODSPRICE + ",";
	}
	for (var i = 0; i < num.length; i++) {
		if (num[i].length == 0) {
			$.messager.alert('提示', '请填写实际收货数量！', 'warning');
			return;
		}
	}
	var url = Utils.getRootPath()
			+ '/purchaseReceive/purchaseReceiveDo/saveActualReceive?ids=' + ids
			+ '&num=' + num + '&id=' + id + '&branchId=' + branchId
			+ '&providerId=' + providerId + '&productDate=' + productDate
			+ '&goodsPrice=' + goodsPrice;
	$.ajax({
		type : "post",
		url : url,
		data : $('#' + formId).serialize(),
		async : true,
		dataType : 'json',
		success : function(result) {
			if (result.message == "更新成功") {
				$.messager.alert('提示', '保存成功', 'info', function() {
					$.messager.confirm("操作提示", "是否立即提交审核？", function(data) {
						if (data) {
							submitCheckNumber1();
						} else {
							window.parent.loadDataGrid('purchaseReceive');
							$("#dg").datagrid('load');
						}
					});
					window.parent.loadDataGrid('purchaseReceive');
					$("#dg").datagrid('load');
				});
			} else {
				$.messager.alert('提示', '系统错误，请联系管理员！', 'error');
			}
		}
	});
}

// 获取选中的行
var getRowsId = function() {
	var ids = [];
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	for (var i = 0; i < rows.length; i++) {
		ids.push(rows[i].ORDERID);
	}
	return ids;
};

var getRowsNum = function() {
	var num = [];
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	for (var i = 0; i < rows.length; i++) {
		num.push(rows[i].ACTUAL_NUM);
	}
	return num;
};

// 计算数量
function actualNum1(val, rec) {
	var str = rec.SPECIFICATIONS;
	var str2 = "";
	var a = 0;
	if (str != null) {
		a = str.length;
	}
	if (str != null && str != "") {
		for (var i = 0; i < a; i++) {
			if ((str.charAt(i) >= '0' && str.charAt(i) <= '9')
					|| str.charAt(i) == '.') {
				str2 += str.charAt(i);
			}
			;
		}
		;
	}
	;
	var num = parseFloat(str2);
	var nums = rec.ACTUALNEED;
	num = nums * num;
	return num;
};

// 计算数量
function actualNum3(val, rec) {
	var str = rec.SPECIFICATIONS;
	var str2 = "";
	var a = 0;
	if (str != null) {
		a = str.length;
	}
	if (str != null && str != "") {
		for (var i = 0; i < a; i++) {
			if ((str.charAt(i) >= '0' && str.charAt(i) <= '9')
					|| str.charAt(i) == '.') {
				str2 += str.charAt(i);
			}
			;
		}
		;
	}
	;
	var num = parseFloat(str2);
	var nums = rec.ACTUAL_NUM;
	num = nums * num;
	return num;
};

// 打开审核页面
function openCheck(title, x, y) {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	var row = rows[0];
	if (row.AUDIT_STATUS == "2") {
		$.messager.alert('提示', '该收货单已通过审核！', 'warning');
		return;
	} else if (row.AUDIT_STATUS == "3") {
		$.messager.alert('提示', '该收货单正在处理中！', 'warning');
		return;
	} else if (row.AUDIT_STATUS == "0") {
		$.messager.alert('提示', '该收货单还未提交！', 'warning');
		return;
	} else if (row.AUDIT_STATUS == "4") {
		$.messager.alert('提示', '该收货单已完成！', 'warning');
		return;
	} else if (row.AUDIT_STATUS == "5") {
		$.messager.alert('提示', '该收货单已作废！', 'warning');
		return;
	} else {
		var url = Utils.getRootPath()
				+ '/purchaseReceive/purchaseReceiveDo/gotoEditCheck?id='
				+ row.ID;
		add(url, title, x, y);
	}
}

// 审核通过
function checkPass() {
	var data = $('#dg').datagrid('getData');
	var ids = "";
	var goodsMoney = "";
	for (var i = 0; i < data.rows.length; i++) {
		ids += data.rows[i].ID + ",";
		goodsMoney += data.rows[i].GOODSMONEY + ",";
	}
	if (ids.length == 0) {
		$.messager.alert('提示', '没有商品，确定审核通过？', 'warning');
		return;
	}
	var id = $('#purchaseReceiveId').val();
	var url = Utils.getRootPath()
			+ '/purchaseReceive/purchaseReceiveDo/checkPass?id=' + id + '&ids='
			+ ids + '&goodsMoney=' + goodsMoney;
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
			+ '/purchaseReceive/purchaseReceiveDo/checkNoPass';
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
// 审核不通过
function checkNoPass(type) {
	var id = $('#purchaseReceiveId').val();
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

// 提交审核(带保存功能)
function submitCheck(formId) {
	var purchaseReceiveId = $('#purchaseReceiveId').val();
	var providerId = $('#providerId').combobox('getValue');
	var branchId = $('#branchId').combobox('getValue');
	var data = $('#dg').datagrid('getData');
	if (data.rows.length == 0) {
		$.messager.alert('提示', '该收货单没有商品，请删除！', 'warning');
		return;
	}
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		if (data.rows[i].ACTUAL_NUM <= 0 || data.rows[i].ACTUAL_NUM == null) {
			for (var j = 0; j < data.rows.length; j++) {
				$('#dg').datagrid('beginEdit', j);
			}
			$.messager.alert('提示', '请正确填写实际收货数量，保存后再提交！', 'warning');
			return;
		}
	}
	var ids = "";
	var num = "";
	var goodsPrice = "";
	var productDate = "";
	for (var i = 0; i < data.rows.length; i++) {
		ids += data.rows[i].ID + ",";
		num += data.rows[i].ACTUAL_NUM + ",";
		productDate += data.rows[i].PRODUCEDATE + ",";
		goodsPrice += data.rows[i].GOODSPRICE + ",";
	}
	for (var i = 0; i < num.length; i++) {
		if (num[i].length == 0) {
			$.messager.alert('提示', '请正确填写实际收货数量！', 'warning');
			return;
		}
	}
	var url = Utils.getRootPath()
			+ '/purchaseReceive/purchaseReceiveDo/saveActualReceive?ids=' + ids
			+ '&num=' + num + '&id=' + purchaseReceiveId + '&branchId='
			+ branchId + '&providerId=' + providerId + '&productDate='
			+ productDate + '&goodsPrice=' + goodsPrice;
	$.ajax({
		type : "post",
		url : url,
		data : $('#' + formId).serialize(),
		async : false,
		dataType : 'json',
		success : function(result) {
			if (result.message == "更新成功") {
			} else {
				$.messager.alert('提示', '系统错误，请联系管理员！', 'error');
			}
		}
	});
	var status = '$!{purchaseReceive.auditStatus}';
	if (status == "1") {
		$.messager.alert('提示', '该收货单已经提交！', 'warning');
		return;
	} else if (status == "2") {
		$.messager.alert('提示', '该收货单审核已通过！', 'warning');
		return;
	} else if (status == "3") {
		$.messager.alert('提示', '该收货单正在处理中！', 'warning');
		return;
	} else if (status == "4") {
		$.messager.alert('提示', '该收货单已完成！', 'warning');
		return;
	} else if (status == "5") {
		$.messager.alert('提示', '该收货单已作废！', 'warning');
		return;
	} else {
		var urls = Utils.getRootPath()
				+ '/purchaseReceive/purchaseReceiveDo/submitCheck?id='
				+ purchaseReceiveId;
		$.ajax({
			type : "post",
			url : urls,
			async : true,
			dataType : 'json',
			success : function(result) {
				if (result.message == "保存成功") {
					$.messager.alert('提示', '提交成功', 'info', function() {
						window.parent.loadDataGrid('purchaseOrdersEdit');
						window.parent.closeWinForm();
					});
				} else {
					$.messager.alert('提示', '提交失败，请联系管理员！', 'error');
				}
			}
		});
	}
}

// 提交审核(单独提交功能)
function submitCheckNumber1() {
	var purchaseReceiveId = $('#purchaseReceiveId').val();
	var data = $('#dg').datagrid('getData');
	if (data.rows.length == 0) {
		$.messager.alert('提示', '该收货单没有商品，请删除！', 'warning');
		return;
	}
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		if (data.rows[i].ACTUAL_NUM <= 0 || data.rows[i].ACTUAL_NUM == null) {
			$('#dg').datagrid('beginEdit', i);
			$.messager.alert('提示', '请正确填写实际收货数量保存后再提交！', 'warning');
			return;
		}
	}
	var status = '$!{purchaseReceive.auditStatus}';
	if (status == "1") {
		$.messager.alert('提示', '该收货单已经提交！', 'warning');
		return;
	} else if (status == "2") {
		$.messager.alert('提示', '该收货单审核已通过！', 'warning');
		return;
	} else if (status == "4") {
		$.messager.alert('提示', '该收货单已完成！', 'warning');
		return;
	} else if (status == "5") {
		$.messager.alert('提示', '该收货单已作废！', 'warning');
		return;
	} else {
		var urls = Utils.getRootPath()
				+ '/purchaseReceive/purchaseReceiveDo/submitCheck?id='
				+ purchaseReceiveId;
		$.ajax({
			type : "post",
			url : urls,
			async : true,
			dataType : 'json',
			success : function(result) {
				if (result.message == "保存成功") {
					$.messager.alert('提示', '提交成功', 'info', function() {
						window.parent.loadDataGrid('purchaseOrdersEdit');
						window.parent.closeWinForm();
					});
				} else {
					$.messager.alert('提示', '提交失败，请联系管理员！', 'error');
				}
			}
		});
	}
}

function removeGoods() {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要移除的商品！', 'warning');
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
	var num = "";
	var produceDate = "";
	var goodsPrice = "";
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		$('#dg').datagrid('beginEdit', i);
		idStr += data.rows[i].ID + ",";
		if (data.rows[i].ACTUAL_NUM == null || data.rows[i].ACTUAL_NUM == ''
				|| data.rows[i].ACTUAL_NUM == 'undefiend') {
			num += "0.00,";
		} else {
			num += data.rows[i].ACTUAL_NUM + ",";
		}
		produceDate += data.rows[i].PRODUCEDATE + ",";
		goodsPrice += data.rows[i].GOODSPRICE + ",";
	}
	$.messager.confirm('确认 ', '确定要移除 ？', function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : Utils.getRootPath()
						+ '/purchaseReceive/purchaseReceiveDo/removeGoods?num='
						+ num + '&idStr=' + idStr + '&produceDate='
						+ produceDate + '&goodsPrice=' + goodsPrice,
				data : {
					id : ids
				},
				async : false,
				dataType : 'json',
				success : function(result) {
					if (result.result) {
						$.messager.alert('提示', '移除成功', 'info', function() {
							window.parent.loadDataGrid('purchaseOrders');
							$("#dg").datagrid('load');
						});
					} else if (result.status == "error") {
						$.messager.alert('提示', '移除失败，请联系管理员！', 'error');
						$("#dg").datagrid('load');
					}
				}
			});
		}
		;
	});
}

function addReceive() {
	var url = Utils.getRootPath()
			+ '/purchaseReceive/purchaseReceiveDo/gotoAddReceive';
	window.parent.initAdd(url, '新增采购收货单', 1000, 550);
}

function openChoseGoods() {
	var data = $('#dg').datagrid('getData');
	var ids = "";
	var num = "";
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		if (data.rows[i].ACTUAL_NUM == null || data.rows[i].ACTUAL_NUM == "") {
			num += "0,";
		} else {
			num += data.rows[i].ACTUAL_NUM + ",";
		}
		ids += data.rows[i].ID + ",";
	}
	var providerId = $('#providerId').combobox('getValue');
	if (providerId.length == 0) {
		$.messager.alert('提示', '请选择供应商！', 'warning');
		return;
	}
	$
			.ajax({
				type : "get",
				url : Utils.getRootPath()
						+ '/purchaseReceive/purchaseReceiveDo/openChoseGoods?providerId='
						+ providerId + '&ids=' + ids + '&num=' + num,
				data : {},
				success : function(data) {
					$('#selectOptions').window({
						title : '选择商品',
						closable : true,
						maximizable : false,
						modal : false,
						draggable : false,
						width : 900,
						height : 550
					});
					$('#selectOptions').html(data);
				}
			});
}

// 移除收货商品
function deleteChose() {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要移除的商品！', 'warning');
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
	var num = "";
	var produceDate = "";
	var goodsPrice = "";
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		$('#dg').datagrid('beginEdit', i);
		idStr += data.rows[i].ID + ",";
		if (data.rows[i].ACTUAL_NUM == null || data.rows[i].ACTUAL_NUM == ''
				|| data.rows[i].ACTUAL_NUM == 'undefiend') {
			num += "0.00,";
		} else {
			num += data.rows[i].ACTUAL_NUM + ",";
		}
		produceDate += data.rows[i].PRODUCEDATE + ",";
		goodsPrice += data.rows[i].GOODS_PURCHASE_PRICE + ",";
	}
	$.messager.confirm('确认 ', '确定要删除 ？', function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : Utils.getRootPath()
						+ '/purchaseReceive/purchaseReceiveDo/deleteChose?num='
						+ num + '&idStr=' + idStr + '&produceDate='
						+ produceDate + '&goodsPrice=' + goodsPrice,
				data : {
					id : ids
				},
				async : false,
				dataType : 'json',
				success : function(result) {
					if (result.result) {
						$.messager.alert('提示', '移除成功', 'info', function() {
							window.parent.loadDataGrid('purchaseOrders');
							$('#dg').datagrid('reload');
						});
					} else if (result.status == "error") {
						$.messager.alert('提示', '移除失败，请联系管理员！', 'error');
						$('#dg').datagrid('reload');
					}
				}
			});
		}
		;
	});
};

function addGoods() {
	var rows = $('#goodsDg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择至少一条数据！', 'warning');
		return;
	}
	var ids = getRowsIdId();
	var url = Utils.getRootPath()
			+ '/purchaseReceive/purchaseReceiveDo/addGoodsToItems?ids=' + ids;
	$.ajax({
		type : "post",
		url : url,
		async : true,
		dataType : 'json',
		success : function(result) {
			$('#selectOptions').window('close');
			if (result.message == "保存成功") {
				$.messager.alert('提示', '添加成功', 'info', function() {
					$('#dg').datagrid('reload');
				});
			} else if (result.message == "保存error") {
				$.messager.alert('提示', '已存在该勾选的商品，请重新确认！', 'info', function() {
					$('#dg').datagrid('reload');
				});
			} else {
				$.messager.alert('提示', '添加失败，请联系管理员！', 'error');
				$('#dg').datagrid('reload');
			}
		}
	});
}

// 获取勾选的ID
var getRowsIdId = function() {
	var ids = [];
	var rows = $('#goodsDg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	for (var i = 0; i < rows.length; i++) {
		ids.push(rows[i].ID);
	}
	return ids;
};

function saveNewReceive(formId) {
	var data = $('#dg').datagrid('getData');
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		if (data.rows[i].ACTUAL_NUM == 0 || data.rows[i].ACTUAL_NUM == null) {
			for (var j = 0; j < data.rows.length; j++) {
				$('#dg').datagrid('beginEdit', j);
			}
			$.messager.alert('提示', '请正确填写实际收货 数量！', 'warning');
			return;
		}
	}
	var providerId = $('#providerId').combobox('getValue');
	if (providerId.length == 0) {
		$.messager.alert('提示', '请选择供应商！', 'warning');
		return;
	}
	var branchId = $('#branchId').combobox('getValue');
	if (branchId.length == 0) {
		$.messager.alert('提示', '请选择收货仓库！', 'warning');
		return;
	}
	var ids = "";
	var num = "";
	var produceDate = "";
	for (var i = 0; i < data.rows.length; i++) {
		ids += data.rows[i].ID + ",";
		num += data.rows[i].ACTUAL_NUM + ",";
		produceDate += data.rows[i].PRODUCEDATE + ",";
	}
	if (ids.length == 0) {
		$.messager.alert('提示', '该收货单中没有商品！', 'warning');
		return;
	}
	for (var i = 0; i < num.length; i++) {
		if (num[i].length == 0) {
			$.messager.alert('提示', '请正确填写实际收货数量！', 'warning');
			return;
		}
	}
	var url = Utils.getRootPath()
			+ '/purchaseReceive/purchaseReceiveDo/saveNewReceive?ids=' + ids
			+ '&nums=' + num + '&branchId=' + branchId + '&providerId='
			+ providerId + '&produceDate=' + produceDate;
	// 数据有限性判断
	if (validateSubmit(formId)) {
		$.ajax({
			type : "post",
			url : url,
			async : false,
			data : $('#' + formId).serialize(),
			dataType : 'json',
			success : function(result) {
				if (result.result) {
					$.messager.alert('提示', '新增成功', 'info', function() {
						window.parent.loadDataGrid('purchaseOrders');
						window.parent.closeWinForm();
					});
				} else if (result.message == "保存error") {
					$.messager.alert('提示', '存在该供应商不提供的商品，请重新确认！', 'info',
							function() {
								$('#dg').datagrid('reload');
							});
				} else {
					$.messager.alert('提示', '新增失败，请联系管理员！', 'error');
				}
			}
		});
	}
}

function openAddGoods() {
	var data = $('#dg').datagrid('getData');
	var ids = "";
	var num = "";
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		if (data.rows[i].ACTUAL_NUM == "" || data.rows[i].ACTUAL_NUM == null) {
			num += '0,';
		} else {
			num += data.rows[i].ACTUAL_NUM + ",";
		}
		ids += data.rows[i].ID + ",";
	}
	var providerId = $('#providerId').combobox('getValue');
	var purchaseReceiveId = $('#purchaseReceiveId').val();
	if (providerId.length == 0) {
		$.messager.alert('提示', '请选择供应商！', 'warning');
		return;
	}
	$.ajax({
		type : "get",
		url : Utils.getRootPath()
				+ '/purchaseReceive/purchaseReceiveDo/openAddGoods?providerId='
				+ providerId + '&purchaseReceiveId=' + purchaseReceiveId
				+ '&ids=' + ids + '&num=' + num,
		data : {},
		success : function(data) {
			$('#selectOptions').window({
				title : '选择商品',
				closable : true,
				maximizable : false,
				modal : false,
				draggable : false,
				width : 900,
				height : 550
			});
			$('#selectOptions').html(data);
		}
	});
}

function chooseGoodsToReceive() {
	var rows = $('#goodsDg').datagrid('getSelections');
	var purchaseReceiveId = $('#purchaseReceiveId').val();
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择至少一条数据！', 'warning');
		return;
	}
	var ids = getRowsIdId();
	var url = Utils.getRootPath()
			+ '/purchaseReceive/purchaseReceiveDo/chooseGoodsToReceive?ids='
			+ ids + '&purchaseReceiveId=' + purchaseReceiveId;
	$.ajax({
		type : "post",
		url : url,
		async : true,
		dataType : 'json',
		success : function(result) {
			$('#selectOptions').window('close');
			if (result.message == "保存成功") {
				$.messager.alert('提示', '添加成功', 'info', function() {
					$('#dg').datagrid('reload');
				});
			} else if (result.message == "保存error") {
				$.messager.alert('提示', '已存在该勾选的商品，请重新确认！', 'info', function() {
					$('#dg').datagrid('reload');
				});
			} else {
				$.messager.alert('提示', '添加失败，请联系管理员！', 'error');
				$('#dg').datagrid('reload');
			}
		}
	});
}

function previewPrint() {
	var purchaseReceiveId = $('#purchaseReceiveId').val();
	$
			.ajax({
				type : "get",
				url : Utils.getRootPath()
						+ '/purchaseReceive/purchaseReceiveDo/gotoPrintPurchaseReceive?id='
						+ purchaseReceiveId,
				data : {},
				success : function(data) {
					$('#selectOptions').window({
						title : '打印预览',
						closable : true,
						maximizable : false,
						modal : false,
						draggable : false,
						width : 800,
						height : 450
					});
					$('#selectOptions').html(data);
				}
			});
}

function PreviewMytable() {
	var LODOP = getLodop();
	LODOP.PRINT_INIT("采购收货单表格");
	var strStyle = "<style> table,td,th {border-width: 1px;border-style: solid;border-collapse: collapse}</style>";
	LODOP.ADD_PRINT_TABLE(200, "5%", "90%", 314, strStyle
			+ document.getElementById("div2").innerHTML);
	LODOP.SET_PRINT_STYLEA(0, "Vorient", 3);
	LODOP.ADD_PRINT_HTM(26, "5%", "90%", 140,
			document.getElementById("div1").innerHTML);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.SET_PRINT_STYLEA(0, "LinkedItem", 1);
	LODOP.PREVIEW();
};