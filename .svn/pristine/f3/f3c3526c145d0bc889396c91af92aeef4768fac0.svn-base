//添加
function add(url, title, x, y) {
	window.parent.initAdd(url, title, x, y);
}

// 编辑
function edit(title, x, y) {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要编辑的数据！', 'warning');
		return;
	}
	if (rows.length > 1) {
		$.messager.alert('提示', '只能选择一条数据进行编辑！', 'warning');
		return;
	}
	var row = rows[0];
	if (row.PURCHASE_STATE == "1") {
		$.messager.alert('提示', '该订单已提交！', 'warning');
		return;
	} else if (row.PURCHASE_STATE == "2") {
		$.messager.alert('提示', '该订单已通过审核！', 'warning');
		return;
	} else if (row.PURCHASE_STATE == "4") {
		$.messager.alert('提示', '该订单已完成！', 'warning');
		return;
	} else if (row.PURCHASE_STATE == "5") {
		$.messager.alert('提示', '该订单已作废！', 'warning');
		return;
	} else {
		var url = Utils.getRootPath()
				+ '/purchase/purchaseOrder/gotoEditPurchaseOrder?id=' + row.ID;
		add(url, title, x, y);
	}
}

function getvalue(row) {
	var id = $('#showWin').document.getElementById("goods_supplier_id");
	alert(id);
	$('#goods_supplier_id').combobox('setValue', row.ID);
}

// 打开新增采购订单页面
function addPurchase(title, x, y) {
	var url = Utils.getRootPath()
			+ '/purchase/purchaseOrder/gotoAddPurchaseOrder';
	add(url, title, x, y);
}

// 查看详细
function detail(title, x, y) {
	var row = rows[0];
	var url = Utils.getRootPath()
			+ '/purchase/purchaseOrder/gotoDetailPurchaseOrder?id=' + row.ID;
	add(url, title, x, y);
}

// 保存
function saveAndCommit(formId) {
	var data = $('#dg').datagrid('getData');
	var purchaseId = $('#purchaseId').val();
	if (data.rows.length == 0) {
		$.messager.alert('提示', '该订单没有商品，请删除！', 'warning');
		return;
	}
	var providerId = $('#provider').combobox('getValue');
	if (providerId.length == 0) {
		$.messager.alert('提示', '请选择供应商！', 'warning');
		return;
	}
	var branchId = $('#branch').combobox('getValue');
	if (branchId.length == 0) {
		$.messager.alert('提示', '请选择仓库！', 'warning');
		return;
	}
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		$('#dg').datagrid('beginEdit', i);
		if (data.rows[i].ACTUALNEED <= 0 || data.rows[i].ACTUALNEED == null) {
			$.messager.alert('提示', '请填写实际采购的数量！', 'warning');
			return;
		}
	}
	var ids = "";
	var num = "";
	var produceDate = "";
	var goodsPrice = "";
	for (var i = 0; i < data.rows.length; i++) {
		ids += data.rows[i].ID + ",";
		num += data.rows[i].ACTUALNEED + ",";
		produceDate += data.rows[i].PRODUCEDATE + ",";
		goodsPrice += data.rows[i].GOODSPRICE + ",";
	}
	var url = Utils.getRootPath() + '/purchase/purchaseOrder/saveAndCommit?id='
			+ purchaseId + '&num=' + num + '&ids=' + ids + '&produceDate='
			+ produceDate + '&goodsPrice=' + goodsPrice;
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
							submitCheckNumber1();
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

// 获取选中的行
var selectAll = function() {
	var ids = [];
	var rows = $('#dg').datagrid('getRows');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	for (var i = 0; i < rows.length; i++) {
		ids.push(rows[i].ID);
	}
	return ids;
};

var rowNum = function() {
	var num = [];
	var rows = $('#dg').datagrid('getRows');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	for (var i = 0; i < rows.length; i++) {
		num.push(rows[i].ACTUALNEED);
	}
	return num;
};

// 删除
function destroyInfo() {
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
	if (row.PURCHASE_STATE == "2") {
		$.messager.alert('提示', '该订单已通过审核，不可刪除!', 'warning');
		return;
	} else if (row.PURCHASE_STATE == "3") {
		$.messager.alert('提示', '该订单待处理，不可刪除!', 'warning');
		return;
	} else if (row.PURCHASE_STATE == "1") {
		$.messager.alert('提示', '该订单已提交，不可刪除!', 'warning');
		return;
	} else if (row.PURCHASE_STATE == "4") {
		$.messager.alert('提示', '该订单已完成，不可刪除!', 'warning');
		return;
	} else {
		$.messager.confirm('确认 ', '确定要删除 ？', function(r) {
			if (r) {
				$.ajax({
					type : "post",
					url : Utils.getRootPath()
							+ '/purchase/purchaseOrder/delete',
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
};

// 新增数据
function saveBaseInfo(formId) {
	// 数据有限性判断
	if (validateSubmit(formId)) {
		// 保存
		var url = Utils.getRootPath() + '/goodsFile/goodsFile/save';
		$.ajax({
			type : "post",
			url : url,
			data : $('#' + formId).serialize(),
			async : false,
			dataType : 'json',
			success : function(result) {
				if (result.result) {
					$.messager.alert('提示', '新增成功', 'info', function() {
						window.parent.loadDataGrid('goodfile');
						window.parent.closeWinForm();
					});
				} else if (result.status == "error") {
					$.messager.alert('提示', '新增失败，请联系管理员！', 'error');
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
		var url = Utils.getRootPath() + '/goodsFile/goodsFile/update';
		$.ajax({
			type : "post",
			url : url,
			data : $('#' + formId).serialize(),
			async : false,
			dataType : 'json',
			success : function(result) {
				if (result.result) {
					$.messager.alert('提示', '修改成功', 'info', function() {
						window.parent.loadDataGrid('goodfile');
						$("#dg").datagrid('load');
					});
				} else {
					$.messager.alert('提示', '修改失败，请联系管理员！', 'error');
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
	searchData();
}

// 新增数据
function saveProviderInfo(formId) {
	// 数据有限性判断
	if (validateSubmit(formId)) {
		// 保存
		var url = Utils.getRootPath() + '/provider/poviderinfo/save';
		$.ajax({
			type : "post",
			url : url,
			data : $('#' + formId).serialize(),
			async : false,
			dataType : 'json',
			success : function(result) {
				if (result.result) {
					$.messager.alert('提示', '新增成功', 'info', function() {
						window.parent.loadDataGrid('provider');
						window.parent.closeWinForm();
					});
				} else if (result.status == "error") {
					$.messager.alert('提示', '新增失败，请联系管理员！', 'error');
				}
			}
		});
	}
}

// 查看采购订单的基本信息
function openPurchaseOrderItem(id) {
	var url = Utils.getRootPath()
			+ '/purchase/purchaseOrder/gotoDetailPurchaseOrder?id=' + id;
	add(url, '采购订单商品详情', 1000, 550);
}

// 根据商品表直接选中行后生成采购订单
function saveLineToPurchase(formId) {
	var data = $('#dg').datagrid('getData');
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		if (data.rows[i].ACTUALNEED == 0 || data.rows[i].ACTUALNEED == null) {
			$('#dg').datagrid('beginEdit', i);
			$.messager.alert('提示', '请填写需要采购的数量！', 'warning');
			return;
		}
	}
	var providerId = $('#providerId').combobox('getValue');
	if (providerId.length == 0) {
		$.messager.alert('提示', '请选择供应商！', 'warning');
		return;
	}
	var branchId = $('#storehouseId').combobox('getValue');
	if (branchId.length == 0) {
		$.messager.alert('提示', '请选择仓库！', 'warning');
		return;
	}
	var ids = "";
	var num = "";
	var price = "";
	var produceDate = "";
	for (var i = 0; i < data.rows.length; i++) {
		ids += data.rows[i].ID + ",";
		num += data.rows[i].ACTUALNEED + ",";
		price += data.rows[i].GOODSPRICE + ",";
		produceDate += data.rows[i].PRODUCEDATE + ",";
	}
	var data = $('#dg').datagrid('getData');
	for (var i = 0; i < num.length; i++) {
		if (num[i].length == 0) {
			$.messager.alert('提示', '请填写需要采购的数量！', 'warning');
			return;
		}
	}
	var url = Utils.getRootPath()
			+ '/purchase/purchaseOrder/createPurchaseByGoods?ids=' + ids
			+ '&nums=' + num + '&data=' + data + '&price=' + price
			+ '&branchId=' + branchId + '&produceDate=' + produceDate;
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
					$.messager.alert('提示', '存在该供应商不提供的商品，请重新确认', 'info',
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
// 获取所有的数据的填写的商品单价
var getRowsPrice = function() {
	var price = [];
	var data = $('#dg').datagrid('getData');
	for (var i = 0; i < data.rows.length; i++) {
		var row = data.rows[i];
		price.push(row.GOODSPRICE);
	}
	return price;
};
// 获取所有的数据的填写的数量
var getRowsNum = function() {
	var num = [];
	var data = $('#dg').datagrid('getData');
	for (var i = 0; i < data.rows.length; i++) {
		var row = data.rows[i];
		num.push(row.ORDERNUM);
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
	var nums = rec.ORDERNUM;
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
	if (rows.length > 1) {
		$.messager.alert('提示', '只能选择一条数据进行操作！', 'warning');
		return;
	}
	var row = rows[0];
	if (row.PURCHASE_STATE == "2") {
		$.messager.alert('提示', '该订单已通过审核！', 'warning');
		return;
	} else if (row.PURCHASE_STATE == "3") {
		$.messager.alert('提示', '该订单正在处理中！', 'warning');
		return;
	} else if (row.PURCHASE_STATE == "0") {
		$.messager.alert('提示', '该订单还未提交！', 'warning');
		return;
	} else if (row.PURCHASE_STATE == "4") {
		$.messager.alert('提示', '该订单已完成！', 'warning');
		return;
	} else if (row.PURCHASE_STATE == "5") {
		$.messager.alert('提示', '该订单已作废！', 'warning');
		return;
	} else {
		var url = Utils.getRootPath()
				+ '/purchase/purchaseOrder/gotoEditCheck?id=' + row.ID;
		add(url, title, x, y);
	}
}

// 审核通过
function checkPass() {
	var id = $('#purchaseId').val();
	var url = Utils.getRootPath() + '/purchase/purchaseOrder/checkPass?id='
			+ id;
	$.messager
			.confirm(
					'确认 ',
					'确定审核通过？',
					function(r) {
						if (r) {
							$
									.ajax({
										type : "post",
										url : url,
										async : false,
										dataType : 'json',
										success : function(result) {
											if (result.result) {
												$.messager
														.alert(
																'提示',
																'审核完成',
																'info',
																function() {
																	$.messager
																			.confirm(
																					"操作提示",
																					"是否立即生成采购收货单？",
																					function(
																							data) {
																						if (data) {
																							createReceiveAfterCheck();
																						} else {
																							window.parent
																									.loadDataGrid('purchaseOrders');
																							$(
																									"#dg")
																									.datagrid(
																											'load');
																						}
																					});
																	window.parent
																			.loadDataGrid('purchaseOrders');
																	$("#dg")
																			.datagrid(
																					'load');
																});
											} else if (result.status == "error") {
												$.messager.alert('提示',
														'系统错误，请联系管理员', 'error');
											}
										}
									});
						}
					});
}
// 审核不通过
function CheckItemAjax(id, reason, type) {
	var url = Utils.getRootPath() + '/purchase/purchaseOrder/checkNoPass';
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
				$.messager.alert('提示', '审核失败，请联系管理员', 'error');
			}
		}
	});
}
// 审核不通过
function checkNoPass(type) {
	var id = $('#purchaseId').val();
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
// 审核通过后直接生成采购收货单
function createReceiveAfterCheck() {
	var purchaseId = $('#purchaseId').val();
	var status = '$!{purchaseOrder.state}';
	if (status == "0") {
		$.messager.alert('提示', '该订单还未提交！', 'warning');
		return;
	} else if (status == "1") {
		$.messager.alert('提示', '该订单未进行审核！', 'warning');
		return;
	} else if (status == "3") {
		$.messager.alert('提示', '该订单正在处理中！', 'warning');
		return;
	} else if (status == "4") {
		$.messager.alert('提示', '该订单已完成！', 'warning');
		return;
	} else if (status == "5") {
		$.messager.alert('提示', '该订单已作废！', 'warning');
		return;
	} else {
		var url = Utils.getRootPath()
				+ '/purchaseReceive/purchaseReceiveDo/createPurchaseReceive?id='
				+ purchaseId;
		$.ajax({
			type : "post",
			url : url,
			async : true,
			dataType : 'json',
			success : function(result) {
				if (result.message == "保存成功") {
					$.messager.alert('提示', '保存成功！', 'info', function() {
						window.parent.loadDataGrid('purchaseOrders');
						window.parent.closeWinForm();
					});
				} else if (result.message == "保存失败") {
					$.messager.alert('提示', '该采购单已生成了采购收货单，请重新选择！', 'warning',
							function() {
								window.parent.loadDataGrid('purchaseOrders');
								window.parent.closeWinForm();
							});
				} else {
					$.messager.alert('提示', '未能同时生成采购收货单，请联系管理员！', 'error',
							function() {
								window.parent.loadDataGrid('purchaseOrders');
								window.parent.closeWinForm();
							});
				}
			}
		});
	}
}
// 生成采购收货单
function purchaseReceive() {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	if (rows.length > 1) {
		$.messager.alert('提示', '只能选择一条数据进行操作！', 'warning');
		return;
	}
	var id = rows[0].ID;
	var row = rows[0];
	if (row.PURCHASE_STATE == "0") {
		$.messager.alert('提示', '该订单还未提交！', 'warning');
		return;
	} else if (row.PURCHASE_STATE == "1") {
		$.messager.alert('提示', '该订单未进行审核！', 'warning');
		return;
	} else if (row.PURCHASE_STATE == "3") {
		$.messager.alert('提示', '该订单正在处理中！', 'warning');
		return;
	} else if (row.PURCHASE_STATE == "4") {
		$.messager.alert('提示', '该订单已完成！', 'warning');
		return;
	} else if (row.PURCHASE_STATE == "5") {
		$.messager.alert('提示', '该订单已作废！', 'warning');
		return;
	} else {
		$.messager
				.confirm(
						'确认 ',
						'确定生成采购收货单？',
						function(r) {
							if (r) {
								var url = Utils.getRootPath()
										+ '/purchaseReceive/purchaseReceiveDo/createPurchaseReceive?id='
										+ id;
								$("#ddialog").dialog('open');
								$
										.ajax({
											type : "post",
											url : url,
											async : true,
											dataType : 'json',
											success : function(result) {
												$("#ddialog").dialog('close');
												if (result.message == "保存成功") {
													$.messager
															.alert(
																	'提示',
																	'新增成功',
																	'info',
																	function() {
																		window.parent
																				.loadDataGrid('purchaseOrdersEdit');
																		window.parent
																				.closeWinForm();
																	});
												} else if (result.message == "保存失败") {
													$.messager
															.alert(
																	'提示',
																	'该采购单已生成了采购收货单，请重新选择',
																	'warning');
												} else {
													$.messager.alert('提示',
															'新增失败，请联系管理员',
															'error');
												}
											}
										});
							}
						});
	}
}

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
	var num = "";
	var produceDate = "";
	var goodsPrice = "";
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		$('#dg').datagrid('beginEdit', i);
		idStr += data.rows[i].ID + ",";
		if (data.rows[i].ACTUALNEED == null || data.rows[i].ACTUALNEED == ''
				|| data.rows[i].ACTUALNEED == 'undefiend') {
			num += "0.00,";
		} else {
			num += data.rows[i].ACTUALNEED + ",";
		}
		produceDate += data.rows[i].PRODUCEDATE + ",";
		goodsPrice += data.rows[i].GOODSPRICE + ",";
	}
	$.messager.confirm('确认 ', '确定要删除 ？', function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : Utils.getRootPath()
						+ '/purchase/purchaseOrder/removeGoods?num=' + num
						+ '&idStr=' + idStr + '&produceDate=' + produceDate
						+ '&goodsPrice=' + goodsPrice,
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

function openChoseGoods() {
	var data = $('#dg').datagrid('getData');
	var ids = "";
	var price = "";
	var num = "";
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		if (data.rows[i].ID != null || data.rows[i].ID != "") {
			ids += data.rows[i].ID + ",";
			if (data.rows[i].GOODSPRICE == 0 || data.rows[i].GOODSPRICE == ""
					|| data.rows[i].GOODSPRICE == null) {
				price += "0,";
			} else {
				price += data.rows[i].GOODSPRICE + ",";
			}
			if (data.rows[i].ACTUALNEED == 0 || data.rows[i].ACTUALNEED == ""
					|| data.rows[i].ACTUALNEED == null) {
				num += "0,";
			} else {
				num += data.rows[i].ACTUALNEED + ",";
			}
		}
	}
	var providerId = $('#providerId').combobox('getValue');
	if (providerId.length == 0) {
		$.messager.alert('提示', '请选择供应商！', 'warning');
		return;
	}
	var storehouseId = $('#storehouseId').combobox('getValue');
	if (storehouseId.length == 0) {
		$.messager.alert('提示', '请选择仓库！', 'warning');
		return;
	}
	$.ajax({
		type : "get",
		url : Utils.getRootPath()
				+ '/purchase/purchaseOrder/openChoseGoods?providerId='
				+ providerId + '&storehouseId=' + storehouseId + '&num=' + num
				+ '&ids=' + ids + '&price=' + price,
		success : function(data) {
			$('#selectOptions').window({
				title : '选择商品',
				collapsible : true,
				maximizable : false,
				modal : true,
				draggable : true,
				width : 880,
				height : 435,
			});
			$('#selectOptions').html(data);
			$('#showWinBatch').window('close');
			$('#showexportBatch').window('close');
		}
	});
}

function addGoods() {
	var rows = $('#goodsDg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择至少一条数据！', 'warning');
		return;
	}
	var storehouseId = $('#branchesId').val();
	var ids = getRowsIdId();
	var url = Utils.getRootPath()
			+ '/purchase/purchaseOrder/addGoodsToItems?ids=' + ids
			+ '&storehouseId=' + storehouseId;
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

function addGoods2() {
	var rows = $('#goodsDg1').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择至少一条数据！', 'warning');
		return;
	}
	var branchCount = $('#branchCount').val();
	var ids = getRowsIdId2();
	var url = Utils.getRootPath()
			+ '/purchase/purchaseOrder/addGoodsToItems?ids=' + ids
			+ '&storehouseId=' + branchCount;
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
var getRowsIdId2 = function() {
	var ids = [];
	var rows = $('#goodsDg1').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	for (var i = 0; i < rows.length; i++) {
		ids.push(rows[i].ID);
	}
	return ids;
};
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

// 移除采购商品
function deleteChose() {
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
	var data = $('#dg').datagrid('getData');
	var idStr = "";
	var num = "";
	var produceDate = "";
	var goodsPrice = "";
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		$('#dg').datagrid('beginEdit', i);
		idStr += data.rows[i].ID + ",";
		if (data.rows[i].ACTUALNEED == null || data.rows[i].ACTUALNEED == ''
				|| data.rows[i].ACTUALNEED == 'undefiend') {
			num += "0.00,";
		} else {
			num += data.rows[i].ACTUALNEED + ",";
		}
		produceDate += data.rows[i].PRODUCEDATE + ",";
		goodsPrice += data.rows[i].GOODSPRICE + ",";
	}
	$.messager.confirm('确认 ', '确定要删除 ？', function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : Utils.getRootPath()
						+ '/purchase/purchaseOrder/deleteChose?num=' + num
						+ '&idStr=' + idStr + '&produceDate=' + produceDate
						+ '&goodsPrice=' + goodsPrice,
				data : {
					id : ids
				},
				async : false,
				dataType : 'json',
				success : function(result) {
					if (result.result) {
						$.messager.alert('提示', '删除成功', 'info', function() {
							window.parent.loadDataGrid('purchaseOrders');
							$('#dg').datagrid('reload');
						});
					} else if (result.status == "error") {
						$.messager.alert('提示', '删除失败，请联系管理员！', 'error');
						$('#dg').datagrid('reload');
					}
				}
			});
		}
		;
	});
};

function closeWindow() {
	var url = Utils.getRootPath()
			+ '/purchase/purchaseOrder/gotoAddPurchaseOrder';
	$('#selectOptions').window('close');
	window.parent.initAdd(url, '新增采购订单', 1000, 535);
}

function addPyGoods() {
	var data = $('#dg').datagrid('getData');
	var ids = "";
	var price = "";
	var num = "";
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		if (data.rows[i].ID != null || data.rows[i].ID != "") {
			ids += data.rows[i].ID + ",";
			if (data.rows[i].GOODSPRICE == 0 || data.rows[i].GOODSPRICE == ""
					|| data.rows[i].GOODSPRICE == null) {
				price += "0,";
			} else {
				price += data.rows[i].GOODSPRICE + ",";
			}
			if (data.rows[i].ACTUALNEED == 0 || data.rows[i].ACTUALNEED == ""
					|| data.rows[i].ACTUALNEED == null) {
				num += "0,";
			} else {
				num += data.rows[i].ACTUALNEED + ",";
			}
		}
	}
	var providerId = $('#providerId').combobox('getValue');
	if (providerId == "") {
		$.messager.show({
			title : '提示',
			msg : '请选择供应商',
			timeout : 1000,
			showType : 'slide',
			style : {
				right : '45%',
				top : '38%',
				bottom : '40%'
			}
		});
		return;
	}
	var storehouseId = $('#storehouseId').combobox('getValue');
	if (storehouseId == "") {
		$.messager.show({
			title : '提示',
			msg : '请选择仓库',
			timeout : 1000,
			showType : 'slide',
			style : {
				right : '45%',
				top : '38%',
				bottom : '40%'
			}
		});
		return;
	}
	var pyNum = $('#pyNum').val();
	pyNum = pyNum.toUpperCase();
	var url = Utils.getRootPath() + '/purchase/purchaseOrder/addPyGoods?pyNum='
			+ pyNum + '&providerId=' + providerId + '&storehouseId='
			+ storehouseId + '&num=' + num + '&ids=' + ids + '&price=' + price;
	$.ajax({
		type : "post",
		url : url,
		async : true,
		dataType : 'json',
		success : function(result) {
			if (result.message == "保存成功") {
				$.messager.alert('提示', '添加成功', 'info', function() {
					$('#dg').datagrid('reload');
				});
			} else if (result.message == "保存warning") {
				$.messager.alert('提示', '该供应商没有该拼音码的商品，请重新选择！', 'warning');
			} else if (result.message == "保存error") {
				$.messager.show({
					title : '提示',
					msg : '已存在该商品，请重新确认',
					timeout : 1000,
					showType : 'slide',
					style : {
						right : '45%',
						top : '38%',
						bottom : '40%'
					}
				});
			} else if (result.message == "查询失败") {
				$.messager.show({
					title : '提示',
					msg : '没有该拼音码的商品，请确认输入是否正确！',
					timeout : 1000,
					showType : 'slide',
					style : {
						right : '45%',
						top : '38%',
						bottom : '40%'
					}
				});
			} else if (result.message == "查询question") {
				$.messager.show({
					title : '提示',
					msg : '该拼音码有多种商品，不止一个供应商提供，请精确填写！',
					timeout : 1000,
					showType : 'slide',
					style : {
						right : '45%',
						top : '38%',
						bottom : '40%'
					}
				});
			} else if (result.message == "查询成功") {
				$.ajax({
					type : "get",
					url : Utils.getRootPath()
							+ '/purchase/purchaseOrder/openPym?pyNum=' + pyNum
							+ '&providerId=' + providerId + '&storehouseId='
							+ storehouseId + '&num=' + num + '&ids=' + ids
							+ '&price=' + price,
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
			} else {
				$.messager.alert('提示', '添加失败，请联系管理员！', 'error');
				$('#dg').datagrid('reload');
			}
		}
	});
}
// 提交审核（带保存功能）
function submitCheck(formId) {
	var purchaseId = $('#purchaseId').val();
	var data = $('#dg').datagrid('getData');
	if (data.rows.length == 0) {
		$.messager.alert('提示', '该订单没有商品，请删除！', 'warning');
		return;
	}
	var providerId = $('#provider').combobox('getValue');
	if (providerId.length == 0) {
		$.messager.alert('提示', '请选择供应商！', 'warning');
		return;
	}
	var branchId = $('#branch').combobox('getValue');
	if (branchId.length == 0) {
		$.messager.alert('提示', '请选择仓库！', 'warning');
		return;
	}
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		$('#dg').datagrid('beginEdit', i);
		if (data.rows[i].ACTUALNEED <= 0 || data.rows[i].ACTUALNEED == null) {
			$.messager.alert('提示', '请填写实际采购数量保存后再提交！', 'warning');
			return;
		}
	}
	var ids = "";
	var num = "";
	var produceDate = "";
	var goodsPrice = "";
	for (var i = 0; i < data.rows.length; i++) {
		ids += data.rows[i].ID + ",";
		num += data.rows[i].ACTUALNEED + ",";
		produceDate += data.rows[i].PRODUCEDATE + ",";
		goodsPrice += data.rows[i].GOODSPRICE + ",";
	}
	var urls = Utils.getRootPath()
			+ '/purchase/purchaseOrder/saveAndCommit?id=' + purchaseId
			+ '&num=' + num + '&ids=' + ids + '&produceDate=' + produceDate
			+ '&goodsPrice=' + goodsPrice;
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
	var status = '$!{purchaseOrder.state}';
	if (status == "1") {
		$.messager.alert('提示', '该订单已经提交！', 'warning');
		return;
	} else if (status == "2") {
		$.messager.alert('提示', '该订单审核已通过！', 'warning');
		return;
	} else if (status == "3") {
		$.messager.alert('提示', '该订单正在处理中！', 'warning');
		return;
	} else if (status == "4") {
		$.messager.alert('提示', '该订单已完成！', 'warning');
		return;
	} else if (status == "5") {
		$.messager.alert('提示', '该订单已作废！', 'warning');
		return;
	} else {
		var url = Utils.getRootPath()
				+ '/purchase/purchaseOrder/submitCheck?id=' + purchaseId;
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
					$.messager.alert('提示', '提交失败，请联系管理员!', 'error');
				}
			}
		});
	}
}
function previewPrint() {
	var providerId = $('#purchaseId').val();
	$.ajax({
		type : "get",
		url : Utils.getRootPath()
				+ '/purchase/purchaseOrder/gotoPrintPurchaseOrder?id='
				+ providerId,
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
	LODOP.PRINT_INIT("采购订单表格");
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

// 单独提交，提交审核
function submitCheckNumber1() {
	var purchaseId = $('#purchaseId').val();
	var data = $('#dg').datagrid('getData');
	if (data.rows.length == 0) {
		$.messager.alert('提示', '该订单没有商品，请删除！', 'warning');
		return;
	}
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		if (data.rows[i].ACTUALNEED <= 0 || data.rows[i].ACTUALNEED == null) {
			$('#dg').datagrid('beginEdit', i);
			$.messager.alert('提示', '请填写实际采购数量保存后再提交！', 'warning');
			return;
		}
	}
	var status = '$!{purchaseOrder.state}';
	if (status == "1") {
		$.messager.alert('提示', '该订单已经提交！', 'warning');
		return;
	} else if (status == "2") {
		$.messager.alert('提示', '该订单审核已通过！', 'warning');
		return;
	} else if (status == "3") {
		$.messager.alert('提示', '该订单正在处理中！', 'warning');
		return;
	} else if (status == "4") {
		$.messager.alert('提示', '该订单已完成！', 'warning');
		return;
	} else if (status == "5") {
		$.messager.alert('提示', '该订单已作废！', 'warning');
		return;
	} else {
		var url = Utils.getRootPath()
				+ '/purchase/purchaseOrder/submitCheck?id=' + purchaseId;
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

function sendTo() {
	var url = Utils.getRootPath() + '/purchase/purchaseOrder/sendTo';
	window.parent.initAdd(url, '消息推送', 1000, 550);
}

function openMessage() {
	var data = $('#dg').datagrid('getData');
	var providerId = $('#providerId').combobox('getValue');
	if (providerId.length == 0) {
		$.messager.alert('提示', '请选择供应商！', 'warning');
		return;
	}
	var ids = "";
	var num = "";
	var unit = "";
	var names = "";
	for (var i = 0; i < data.rows.length; i++) {
		ids += data.rows[i].ID + ",";
		num += data.rows[i].ACTUALNEED + ",";
		unit += data.rows[i].UNIT + ",";
		names += data.rows[i].GOODSNAME + ",";
	}
	unit = encodeURI(encodeURI(unit));
	names = encodeURI(encodeURI(names));
	var url = Utils.getRootPath()
			+ '/purchase/purchaseOrder/openMessage?providerId=' + providerId
			+ '&unit=' + unit + '&ids=' + ids + '&num=' + num + '&names='
			+ names;
	window.parent.initAdd2(url, '编辑短信', 600, 300);
}

function sendCommom() {
	var ids = $('#ids').val();
	var messages = $('#messages').val();
	var mobilephone = $('#mobilephone').val();
	var url = Utils.getRootPath() + '/purchase/purchaseOrder/sendCommom';
	$.ajax({
		type : "post",
		url : url,
		data : {
			ids : ids,
			mobilephone : mobilephone,
			messages : messages,
		},
		async : false,
		dataType : 'json',
		success : function(result) {
			if (result.message == "保存成功") {
				$.messager.alert('提示', '发送成功！', 'info', function() {
					window.parent.closeWinForm2();
				});
			} else if (result.message == "保存失败") {
				$.messager.alert('提示', '发送失败！', 'error', function() {
					window.parent.closeWinForm2();
				});
			}
		}
	});
}

function openEmail() {
	var data = $('#dg').datagrid('getData');
	var providerId = $('#providerId').combobox('getValue');
	if (providerId.length == 0) {
		$.messager.alert('提示', '请选择供应商！', 'warning');
		return;
	}
	var ids = "";
	var num = "";
	var unit = "";
	var names = "";
	for (var i = 0; i < data.rows.length; i++) {
		ids += data.rows[i].ID + ",";
		num += data.rows[i].ACTUALNEED + ",";
		unit += data.rows[i].UNIT + ",";
		names += data.rows[i].GOODSNAME + ",";
	}
	unit = encodeURI(encodeURI(unit));
	names = encodeURI(encodeURI(names));
	var url = Utils.getRootPath()
			+ '/purchase/purchaseOrder/openEmail?providerId=' + providerId
			+ '&unit=' + unit + '&ids=' + ids + '&num=' + num + '&names='
			+ names;
	window.parent.initAdd2(url, '编辑邮件', 600, 300);
}

function pushinfoEmail() {
	var messages = $('#messages').val();
	var mail = $('#mail').val();
	var sendEmail = $('#sendEmail').val();
	var sendPwd = $('#sendPwd').val();
	var url = Utils.getRootPath() + '/purchase/purchaseOrder/pushinfoEmail';
	$.ajax({
		type : "post",
		url : url,
		data : {
			mail : mail,
			messages : messages,
			sendEmail : sendEmail,
			sendPwd : sendPwd,
		},
		async : false,
		dataType : 'json',
		success : function(result) {
			if (result.message == "保存成功") {
				$.messager.alert('提示', '发送成功！', 'info', function() {
					window.parent.closeWinForm2();
				});
			} else if (result.message == "查询失败") {
				$.messager.alert('提示', '发件人登录名或密码错误！', 'warning', function() {
					window.parent.closeWinForm2();
				});
			} else if (result.message == "保存失败") {
				$.messager.alert('提示', '发送失败！', 'error', function() {
					window.parent.closeWinForm2();
				});
			}
		}
	});
}

function changeEmail(formId) {
	// 数据有限性判断
	if (validateSubmit(formId)) {
		// 保存
		var url = Utils.getRootPath() + '/purchase/purchaseOrder/changeEmail';
		$.ajax({
			type : "post",
			url : url,
			data : $('#' + formId).serialize(),
			async : false,
			dataType : 'json',
			success : function(result) {
				if (result.message == "保存成功") {
					$.messager.alert('提示', '设置成功', 'info', function() {
					});
				} else if (result.message == "查询失败") {
					$.messager.alert('提示', '邮箱或密码错误，请核对！', 'warning');
				} else if (result.message == "保存失败") {
					$.messager.alert('提示', '新增失败，请联系管理员！', 'error');
				}
			}
		});
	}
}

function doQuery(target, q) {
	var opts = $.data(target, "combobox").options;
	// 设置values?谁会输入valueField呢？q为text，把text作为value带进去是什么意思呢？
	// 个人觉得这个地方不合理
	if (opts.multiple && !q) {
		setValues(target, [], true);
	} else {
		setValues(target, [ q ], true);
	}
	if (opts.mode == "remote") {// 如果为remote模式，则请求远程数据
		request(target, null, {
			q : q
		}, true);
	} else {// 本地模式
		var panel = $(target).combo("panel");
		// 隐藏所有下拉选项
		panel.find("div.combobox-item").hide();
		var data = $.data(target, "combobox").data;
		for (var i = 0; i < data.length; i++) {
			// 如果根据text过滤到(过滤规则为：包含用户输入值即匹配)匹配选项，则显示、设置选项。
			if (opts.filter.call(target, q, data[i])) {
				var v = data[i][opts.valueField];
				var s = data[i][opts.textField];
				var item = panel.find("div.combobox-item[value=\"" + v + "\"]");
				// 显示item
				item.show();
				if (s == q) {// 完全匹配(即完全等于)
					// 设置values
					setValues(target, [ v ], true);
					// 添加选中样式
					item.addClass("combobox-item-selected");
				}
			}
		}
	}
};

// 导入
function batchImport(purchaseId) {
	var ids = "";
	var data = $('#dg').datagrid('getData');
	for (var i = 0; i < data.rows.length; i++) {
		ids += data.rows[i].ID + ",";
	}
	var url = Utils.getRootPath() + '/importExcel/savePOItemImport?purchaseId='
			+ purchaseId + '&ids=' + ids;
	$('#myBatch').form('submit', {
		url : url,
		dataType : 'html',
		onSubmit : function() {
		},
		success : function(data) {
			var result = eval("(" + data + ")");
			if (result.msg == "fail") {
				$.messager.alert('提示', '导入失败', 'error');
			} else if (result.msg == "success") {
				$.messager.alert('提示', '导入成功！', 'info', function() {
					window.parent.loadDataGrid('goodfile');
					$('#showWinBatch').window('close');
					$("#dg").datagrid('load');
				});
			} else {
				var info = result.resultAnwser;
				var wrongRow = new Array();
				wrongRow = info.split("@");
				var wrongMsg = "批量导入失败:" + "<br/>";
				$.messager.alert('提示', wrongMsg);
			}
		}
	});
}

// 导出
function exportExcel(purchaseId, id) {
	var url = Utils.getRootPath() + '/exportExcel/outPurchaseOrderExcel?id='
			+ id + '&purchaseId=' + purchaseId;
	$('#importBtn').linkbutton({
		iconCls : 'icon-save'
	});
	$('#importBtn').text('导出中');
	$('#myBatch1').form('submit', {
		url : url,
		dataType : 'html',
		onSubmit : function() {
		},
		success : function(data) {
			$('#importBtn').linkbutton({
				iconCls : 'icon-save'
			});
			$('#importBtn').text('导出');
			var result = eval("(" + data + ")");
			if (result.msg == "fail") {
				$.messager.alert('提示', '导出失败', 'error');

			} else if (result.msg == "success") {
				$.messager.alert('提示', '导出 成功！', 'info', function() {
					window.parent.loadDataGrid('goodfile');
					$('#showexportBatch').window('close');
					$("#dg").datagrid('load');
				});
			} else {
				var info = result.resultAnwser;
				var wrongRow = new Array();
				wrongRow = info.split("@");
				var wrongMsg = "批量导出失败:" + "<br/>";
				$.messager.alert('提示', wrongMsg);
			}
		}
	});
}

function openChooseGoods() {
	var purchaseId = $('#purchaseId').val();
	var data = $('#dg').datagrid('getData');
	var ids = "";
	var price = "";
	var num = "";
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		if (data.rows[i].ID != null || data.rows[i].ID != "") {
			ids += data.rows[i].ID + ",";
			if (data.rows[i].GOODSPRICE == 0 || data.rows[i].GOODSPRICE == ""
					|| data.rows[i].GOODSPRICE == null) {
				price += "0,";
			} else {
				price += data.rows[i].GOODSPRICE + ",";
			}
			if (data.rows[i].ACTUALNEED == 0 || data.rows[i].ACTUALNEED == ""
					|| data.rows[i].ACTUALNEED == null) {
				num += "0,";
			} else {
				num += data.rows[i].ACTUALNEED + ",";
			}
		}
	}
	var providerId = $('#provider').combobox('getValue');
	if (providerId.length == 0) {
		$.messager.alert('提示', '请选择供应商！', 'warning');
		return;
	}
	var storehouseId = $('#branch').combobox('getValue');
	if (storehouseId.length == 0) {
		$.messager.alert('提示', '请选择仓库！', 'warning');
		return;
	}
	$.ajax({
		type : "get",
		url : Utils.getRootPath()
				+ '/purchase/purchaseOrder/openChooseGoods?providerId='
				+ providerId + '&storehouseId=' + storehouseId + '&num=' + num
				+ '&ids=' + ids + '&price=' + price + '&purchaseId='
				+ purchaseId,
		success : function(data) {
			$('#selectOptions').window({
				title : '选择商品',
				collapsible : true,
				maximizable : false,
				modal : true,
				draggable : true,
				width : 880,
				height : 435,
			});
			$('#selectOptions').html(data);
			$('#showWinBatch').window('close');
			$('#showexportBatch').window('close');
		}
	});
}

function addGoods1() {
	var purchaseId = $('#purchaseId').val();
	var rows = $('#goodsDg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择至少一条数据！', 'warning');
		return;
	}
	var storehouseId = $('#branchesId').val();
	var ids = getRowsIdId();
	var url = Utils.getRootPath()
			+ '/purchase/purchaseOrder/addGoodsToItemsEdit?ids=' + ids
			+ '&storehouseId=' + storehouseId + '&purchaseId=' + purchaseId;
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

// 导入
function importIntoAdd() {
	var ids = "";
	var data = $('#dg').datagrid('getData');
	for (var i = 0; i < data.rows.length; i++) {
		ids += data.rows[i].ID + ",";
	}
	var url = Utils.getRootPath() + '/importExcel/importIntoAdd?ids=' + ids;
	$('#myBatch').form('submit', {
		url : url,
		dataType : 'html',
		onSubmit : function() {
		},
		success : function(data) {
			var result = eval("(" + data + ")");
			if (result.msg == "fail") {
				$.messager.alert('提示', '导入失败', 'error');
			} else if (result.msg == "success") {
				$.messager.alert('提示', '导入成功！', 'info', function() {
					window.parent.loadDataGrid('goodfile');
					$('#showWinBatch').window('close');
					$("#dg").datagrid('load');
				});
			} else {
				var info = result.resultAnwser;
				var wrongRow = new Array();
				wrongRow = info.split("@");
				var wrongMsg = "批量导入失败:" + "<br/>";
				$.messager.alert('提示', wrongMsg);
			}
		}
	});
}

// 新增页面导出
function exportExcelAdd() {
	var odd = $('#odd').val();
	var data = $('#dg').datagrid('getData');
	var idStr = "";
	var num = "";
	var goodsPrice = "";
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		$('#dg').datagrid('beginEdit', i);
		idStr += data.rows[i].ID + ",";
		if (data.rows[i].ACTUALNEED == null || data.rows[i].ACTUALNEED == ''
				|| data.rows[i].ACTUALNEED == 'undefiend') {
			num += "0.00,";
		} else {
			num += data.rows[i].ACTUALNEED + ",";
		}
		goodsPrice += data.rows[i].GOODSPRICE + ",";
	}
	var url = Utils.getRootPath() + '/exportExcel/exportExcelAdd?odd=' + odd
			+ '&idStr=' + idStr + '&num=' + num + '&goodsPrice=' + goodsPrice;
	$('#myBatch1').form('submit', {
		url : url,
		dataType : 'html',
		onSubmit : function() {
		},
		success : function(data) {
			var result = eval("(" + data + ")");
			if (result.msg == "fail") {
				$.messager.alert('提示', '导出失败', 'error');
			} else if (result.msg == "success") {
				$.messager.alert('提示', '导出 成功！', 'info', function() {
					window.parent.loadDataGrid('goodfile');
					$('#showexportBatch').window('close');
					$("#dg").datagrid('load');
				});
			} else {
				var info = result.resultAnwser;
				var wrongRow = new Array();
				wrongRow = info.split("@");
				var wrongMsg = "批量导出失败:" + "<br/>";
				$.messager.alert('提示', wrongMsg);
			}
		}
	});
}