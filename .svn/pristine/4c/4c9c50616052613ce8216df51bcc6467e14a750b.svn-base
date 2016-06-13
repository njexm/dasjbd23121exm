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
	if (row.STATUS == "1") {
		$.messager.alert('提示', '该订单已提交！', 'warning');
		return;
	} else if (row.STATUS == "2") {
		$.messager.alert('提示', '该订单已通过审核！', 'warning');
		return;
	} else if (row.STATUS == "4") {
		$.messager.alert('提示', '该订单已完成！', 'warning');
		return;
	} else if (row.STATUS == "5") {
		$.messager.alert('提示', '该订单已作废！', 'warning');
		return;
	} else {
		var url = Utils.getRootPath()
				+ '/wholesaleGroupPurchase/order/gotoEditOrder?id=' + row.ID;
		add(url, title, x, y);
	}
}

function openChoseGoods() {
	var customerId = $('#customer').combobox('getValue');
	if (customerId.length == 0) {
		$.messager.alert('提示', '请选择客户！', 'warning');
		return;
	}
	var data = $('#dgAdd').datagrid('getData');
	var idStr = "";
	var num = "";
	var rate = "";
	var goodsPrice = "";
	for (var i = 0; i < data.rows.length; i++) {
		$('#dgAdd').datagrid('endEdit', i);
		if (data.rows[i].ID != null && data.rows[i].ID != "") {
			idStr += data.rows[i].ID + ",";
			if (data.rows[i].RATE == null || data.rows[i].RATE == ''
					|| data.rows[i].RATE == 'undefiend') {
				rate += "0.00,";
			} else {
				rate += data.rows[i].RATE + ",";
			}
			if (data.rows[i].NUMS == null || data.rows[i].NUMS == ''
					|| data.rows[i].NUMS == 'undefiend') {
				num += "0.00,";
			} else {
				num += data.rows[i].NUMS + ",";
			}
			goodsPrice += data.rows[i].GOODSPRICE + ",";
		}
	}
	$.ajax({
		type : "get",
		url : Utils.getRootPath()
				+ '/wholesaleGroupPurchase/order/openChoseGoods?customerId='
				+ customerId + '&num=' + num + '&idStr=' + idStr
				+ '&goodsPrice=' + goodsPrice + '&rate=' + rate,
		success : function(data) {
			$('#selectOptions').window({
				title : '选择商品',
				collapsible : true,
				maximizable : false,
				modal : true,
				draggable : true,
				width : 900,
				height : 440,
			});
			$('#selectOptions').html(data);
		}
	});
}

function openChooseGoods() {
	var orderId = $('#orderId').val();
	var customerId = $('#customer').combobox('getValue');
	if (customerId.length == 0) {
		$.messager.alert('提示', '请选择客户！', 'warning');
		return;
	}
	var data = $('#dgEdit').datagrid('getData');
	var idStr = "";
	var num = "";
	var rate = "";
	var goodsPrice = "";
	for (var i = 0; i < data.rows.length; i++) {
		$('#dgEdit').datagrid('endEdit', i);
		if (data.rows[i].ID != null && data.rows[i].ID != "") {
			idStr += data.rows[i].ID + ",";
			if (data.rows[i].RATE == null || data.rows[i].RATE == ''
					|| data.rows[i].RATE == 'undefiend') {
				rate += "0.00,";
			} else {
				rate += data.rows[i].RATE + ",";
			}
			if (data.rows[i].NUMS == null || data.rows[i].NUMS == ''
					|| data.rows[i].NUMS == 'undefiend') {
				num += "0.00,";
			} else {
				num += data.rows[i].NUMS + ",";
			}
			goodsPrice += data.rows[i].GOODSPRICE + ",";
		}
	}
	$.ajax({
		type : "get",
		url : Utils.getRootPath()
				+ '/wholesaleGroupPurchase/order/openChooseGoods?customerId='
				+ customerId + '&num=' + num + '&idStr=' + idStr
				+ '&goodsPrice=' + goodsPrice + '&rate=' + rate + '&orderId='
				+ orderId,
		success : function(data) {
			$('#selectOptions').window({
				title : '选择商品',
				collapsible : true,
				maximizable : false,
				modal : true,
				draggable : true,
				width : 880,
				height : 440,
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

	var ids = "";
	var prices = "";
	for (var i = 0; i < rows.length; i++) {
		ids += rows[i].ID + ",";
		prices += rows[i].PRICE + ",";
	}
	var url = Utils.getRootPath()
			+ '/wholesaleGroupPurchase/order/addGoodsToItems';
	$.ajax({
		type : "post",
		url : url,
		async : true,
		dataType : 'json',
		data : {
			'ids' : ids,
			'prices' : prices
		},
		success : function(result) {
			$('#selectOptions').window('close');
			if (result.message == "保存成功") {
				$.messager.alert('提示', '添加成功', 'info', function() {
					$('#dgAdd').datagrid('reload');
				});
			} else if (result.message == "保存error") {
				$.messager.alert('提示', '已存在该勾选的商品，请重新确认！', 'info', function() {
					$('#dgAdd').datagrid('reload');
				});
			} else {
				$.messager.alert('提示', '添加失败，请联系管理员！', 'error');
				$('#dgAdd').datagrid('reload');
			}
		}
	});
}

function addGoodsI() {
	var wholeSalePurchaseOrdersId = $('#wholeSalePurchaseOrdersId').val();
	var rows = $('#goodsDg2').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择至少一条数据！', 'warning');
		return;
	}
	var ids = "";
	var prices = "";
	for (var i = 0; i < rows.length; i++) {
		ids += rows[i].ID + ",";
		prices += rows[i].PRICE + ",";
	}
	var url = Utils.getRootPath()
			+ '/wholesaleGroupPurchase/order/addGoodsToItems?ordersId='
			+ wholeSalePurchaseOrdersId;
	$.ajax({
		type : "post",
		url : url,
		async : true,
		dataType : 'json',
		data : {
			'ids' : ids,
			'prices' : prices
		},
		success : function(result) {
			$('#selectOptions').window('close');
			if (result.message == "保存成功") {
				$.messager.alert('提示', '添加成功', 'info', function() {
					$('#dgEdit').datagrid('reload');
				});
			} else if (result.message == "保存error") {
				$.messager.alert('提示', '已存在该勾选的商品，请重新确认！', 'info', function() {
					$('#dgEdit').datagrid('reload');
				});
			} else {
				$.messager.alert('提示', '添加失败，请联系管理员！', 'error');
				$('#dgEdit').datagrid('reload');
			}
		}
	});
}

// 根据商品表直接选中行后生成批发团购订单
function addWholeGroupOrder(formId) {
	var data = $('#dgAdd').datagrid('getData');
	if (data.rows.length == 0) {
		$.messager.alert('提示', '新建订单没有商品，无需新建！', 'warning');
		return;
	}
	for (var i = 0; i < data.rows.length; i++) {
		$('#dgAdd').datagrid('endEdit', i);
		$('#dgAdd').datagrid('beginEdit', i);
		if (data.rows[i].NUMS == 0 || data.rows[i].NUMS == null) {
			$('#dgAdd').datagrid('beginEdit', i);
			$.messager.alert('提示', '请填写商品的数量！', 'warning');
			return;
		}
	}
	var branchId = $("#branch").combobox('getValue');
	if (branchId.length == 0) {
		$.messager.alert('提示', '请选择仓库！', 'warning');
		return;
	}
	var customerId = $("#customer").combobox('getValue');
	if (customerId.length == 0) {
		$.messager.alert('提示', '请选择客户！', 'warning');
		return;
	}
	var payType = $('#payType').val();
	if (payType.length == 0) {
		$.messager.alert('提示', '请选择付款方式！', 'warning');
		return;
	}
	var ids = "";
	var num = "";
	var price = "";
	var rate = "";
	for (var i = 0; i < data.rows.length; i++) {
		ids += data.rows[i].ID + ",";
		if (data.rows[i].NUMS == null || data.rows[i].NUMS == ""
				|| data.rows[i].NUMS == 'undefiend') {
			num += "0,";
		} else {
			num += data.rows[i].NUMS + ",";
		}
		price += data.rows[i].GOODSPRICE + ",";
		if (data.rows[i].RATE == null || data.rows[i].RATE == ""
				|| data.rows[i].RATE == 'undefiend') {
			rate += "0,";
		} else {
			rate += data.rows[i].RATE + ",";
		}
	}
	var url = Utils.getRootPath()
			+ '/wholesaleGroupPurchase/order/createWholeByGoods?ids=' + ids
			+ '&nums=' + num + '&price=' + price + '&rate=' + rate
			+ '&branchId=' + branchId + '&customerId=' + customerId
			+ '&payType=' + payType;
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
						window.parent
								.loadDataGrid('wholesaleGroupPurchaseOrder');
						window.parent.closeWinForm();
					});
				} else {
					$.messager.alert('提示', '新增失败，请联系管理员！', 'error');
				}
			}
		});
	}
}

// 查看订单的基本信息
function openOrderItem(id) {
	var url = Utils.getRootPath()
			+ '/wholesaleGroupPurchase/order/gotoDetailOrder?id=' + id;
	add(url, '批发团购订单商品详情', 1000, 550);
}

// 保存
function saveAndCommit(formId) {
	var data = $('#dgEdit').datagrid('getData');
	if (data.rows.length == 0) {
		$.messager.alert('提示', '新建订单没有商品，无需新建！', 'warning');
		return;
	}
	for (var i = 0; i < data.rows.length; i++) {
		$('#dgEdit').datagrid('endEdit', i);
		$('#dgEdit').datagrid('beginEdit', i);
		if (data.rows[i].NUMS == 0 || data.rows[i].NUMS == null) {
			$('#dgEdit').datagrid('beginEdit', i);
			$.messager.alert('提示', '请填写商品的数量！', 'warning');
			return;
		}
	}
	var branchId = $("#branch").combobox('getValue');
	if (branchId.length == 0) {
		$.messager.alert('提示', '请选择仓库！', 'warning');
		return;
	}
	var customerId = $("#customer").combobox('getValue');
	if (customerId.length == 0) {
		$.messager.alert('提示', '请选择客户！', 'warning');
		return;
	}
	var payType = $('#payType').val();
	if (payType.length == 0) {
		$.messager.alert('提示', '请选择付款方式！', 'warning');
		return;
	}
	var ids = "";
	var num = "";
	var price = "";
	var rate = "";
	for (var i = 0; i < data.rows.length; i++) {
		ids += data.rows[i].ID + ",";
		num += data.rows[i].NUMS + ",";
		price += data.rows[i].GOODSPRICE + ",";
		rate += data.rows[i].RATE + ",";
	}
	var url = Utils.getRootPath()
			+ '/wholesaleGroupPurchase/order/saveAndCommit?ids=' + ids
			+ '&nums=' + num + '&price=' + price + '&rate=' + rate
			+ '&branchId=' + branchId + '&customerId=' + customerId;
	// 数据有限性判断
	if (validateSubmit(formId)) {
		$
				.ajax({
					type : "post",
					url : url,
					async : false,
					data : $('#' + formId).serialize(),
					dataType : 'json',
					success : function(result) {
						if (result.result) {
							$.messager
									.alert(
											'提示',
											'保存成功',
											'info',
											function() {
												$.messager
														.confirm(
																"操作提示",
																"是否立即提交审核？",
																function(data) {
																	if (data) {
																		$(
																				'#orderStatus')
																				.val(
																						"0");
																		submitCheckNumber1();
																	} else {
																		window.parent
																				.loadDataGrid('wholesaleGroupPurchaseOrder');
																		window.parent
																				.closeWinForm();
																	}
																});
											});
						} else {
							$.messager.alert('提示', '新增失败，请联系管理员！', 'error');
						}
					}
				});
	}
}

// 单独提交，提交审核
function submitCheckNumber1() {
	var data = $('#dgEdit').datagrid('getData');
	if (data.rows.length == 0) {
		$.messager.alert('提示', '新建订单没有商品，无需新建！', 'warning');
		return;
	}
	for (var i = 0; i < data.rows.length; i++) {
		$('#dgEdit').datagrid('endEdit', i);
		$('#dgEdit').datagrid('beginEdit', i);
		if (data.rows[i].NUMS == 0 || data.rows[i].NUMS == null) {
			$('#dgEdit').datagrid('beginEdit', i);
			$.messager.alert('提示', '请填写实际采购数量保存后再提交！', 'warning');
			return;
		}
	}
	var status = $('#orderStatus').val();
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
				+ '/wholesaleGroupPurchase/order/submitCheck?id='
				+ $('#orderId').val();
		$.ajax({
			type : "post",
			url : url,
			async : true,
			dataType : 'json',
			success : function(result) {
				if (result.message == "保存成功") {
					$.messager.alert('提示', '提交成功', 'info', function() {
						window.parent
								.loadDataGrid('wholesaleGroupPurchaseOrder');
						window.parent.closeWinForm();
					});
				} else {
					$.messager.alert('提示', '提交失败，请联系管理员!', 'error');
				}
			}
		});
	}
}

// 审核
function submitCheck(formId) {
	var data = $('#dgEdit').datagrid('getData');
	if (data.rows.length == 0) {
		$.messager.alert('提示', '新建订单没有商品，无需新建！', 'warning');
		return;
	}
	for (var i = 0; i < data.rows.length; i++) {
		$('#dgEdit').datagrid('endEdit', i);
		$('#dgEdit').datagrid('beginEdit', i);
		if (data.rows[i].NUMS == 0 || data.rows[i].NUMS == null) {
			$('#dgEdit').datagrid('beginEdit', i);
			$.messager.alert('提示', '请填写商品的数量！', 'warning');
			return;
		}
	}
	var branchId = $("#branch").combobox('getValue');
	if (branchId.length == 0) {
		$.messager.alert('提示', '请选择仓库！', 'warning');
		return;
	}
	var customerId = $("#customer").combobox('getValue');
	if (customerId.length == 0) {
		$.messager.alert('提示', '请选择客户！', 'warning');
		return;
	}
	var payType = $('#payType').val();
	if (payType.length == 0) {
		$.messager.alert('提示', '请选择付款方式！', 'warning');
		return;
	}
	var ids = "";
	var num = "";
	var price = "";
	var rate = "";
	for (var i = 0; i < data.rows.length; i++) {
		ids += data.rows[i].ID + ",";
		num += data.rows[i].NUMS + ",";
		price += data.rows[i].GOODSPRICE + ",";
		rate += data.rows[i].RATE + ",";
	}
	var urls = Utils.getRootPath()
			+ '/wholesaleGroupPurchase/order/saveAndCommit?ids=' + ids
			+ '&nums=' + num + '&price=' + price + '&rate=' + rate
			+ '&branchId=' + branchId + '&customerId=' + customerId;
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
	var status = '$!{order.status}';
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
				+ '/wholesaleGroupPurchase/order/submitCheck?id='
				+ $('#orderId').val();
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

// 删除
function destroyInfo() {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要删除的数据！', 'warning');
		return;
	}
	var row = rows[0];
	if (row.STATUS == "2") {
		$.messager.alert('提示', '该订单已通过审核，不可刪除!', 'warning');
		return;
	} else if (row.STATUS == "3") {
		$.messager.alert('提示', '该订单待处理，不可刪除!', 'warning');
		return;
	} else if (row.STATUS == "1") {
		$.messager.alert('提示', '该订单已提交，不可刪除!', 'warning');
		return;
	} else if (row.STATUS == "4") {
		$.messager.alert('提示', '该订单已完成，不可刪除!', 'warning');
		return;
	} else {
		$.messager
				.confirm(
						'确认 ',
						'确定要删除 ？',
						function(r) {
							if (r) {
								$
										.ajax({
											type : "post",
											url : Utils.getRootPath()
													+ '/wholesaleGroupPurchase/order/delete',
											data : {
												id : row.ID
											},
											async : false,
											dataType : 'json',
											success : function(result) {
												if (result.result) {
													$.messager
															.alert(
																	'提示',
																	'删除成功',
																	'info',
																	function() {
																		window.parent
																				.loadDataGrid('wholesaleGroupPurchaseOrder');
																		window.parent
																				.closeWinForm();
																	});
												} else if (result.status == "error") {
													$.messager.alert('提示',
															'删除失败，请联系管理员！',
															'error');
												}
											}
										});
							}
							;
						});
	}
};

// 编辑时移除商品
function removeGoods() {
	var orderId = $('#orderId').val();
	var rows = $('#dgEdit').datagrid('getSelections');
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
	var data = $('#dgEdit').datagrid('getData');
	var idStr = "";
	var num = "";
	var rate = "";
	var goodsPrice = "";
	for (var i = 0; i < data.rows.length; i++) {
		$('#dgEdit').datagrid('endEdit', i);
		$('#dgEdit').datagrid('beginEdit', i);
		idStr += data.rows[i].ID + ",";
		if (data.rows[i].RATE == null || data.rows[i].RATE == ''
				|| data.rows[i].RATE == 'undefiend') {
			rate += "0.00,";
		} else {
			rate += data.rows[i].RATE + ",";
		}
		if (data.rows[i].NUMS == null || data.rows[i].NUMS == ''
				|| data.rows[i].NUMS == 'undefiend') {
			num += "0.00,";
		} else {
			num += data.rows[i].NUMS + ",";
		}
		goodsPrice += data.rows[i].GOODSPRICE + ",";
	}
	$.messager.confirm('确认 ', '确定要删除 ？', function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : Utils.getRootPath()
						+ '/wholesaleGroupPurchase/order/removeGoods?num='
						+ num + '&idStr=' + idStr + '&goodsPrice=' + goodsPrice
						+ '&rate=' + rate + '&orderId=' + orderId,
				data : {
					id : ids
				},
				async : false,
				dataType : 'json',
				success : function(result) {
					if (result.result) {
						$.messager.alert('提示', '移除成功', 'info', function() {
							window.parent.loadDataGrid('purchaseOrders');
							$('#dgEdit').datagrid('reload');
						});
					} else if (result.status == "error") {
						$.messager.alert('提示', '移除失败，请联系管理员！', 'error');
						$('#dgEdit').datagrid('reload');
					}
				}
			});
		}
		;
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
	if (row.STATUS == "2") {
		$.messager.alert('提示', '该订单已通过审核！', 'warning');
		return;
	} else if (row.STATUS == "3") {
		$.messager.alert('提示', '该订单正在处理中！', 'warning');
		return;
	} else if (row.STATUS == "0") {
		$.messager.alert('提示', '该订单还未提交！', 'warning');
		return;
	} else if (row.STATUS == "4") {
		$.messager.alert('提示', '该订单已完成！', 'warning');
		return;
	} else if (row.STATUS == "5") {
		$.messager.alert('提示', '该订单已作废！', 'warning');
		return;
	} else {
		var url = Utils.getRootPath()
				+ '/wholesaleGroupPurchase/order/gotoEditCheck?id=' + row.ID;
		add(url, title, x, y);
	}
}

// 审核通过
function checkPass() {
	var id = $('#orderId').val();
	var url = Utils.getRootPath()
			+ '/wholesaleGroupPurchase/order/checkPass?id=' + id;
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
																					"是否立即生成销售单？",
																					function(
																							data) {
																						if (data) {
																							$(
																									'#orderStatus')
																									.val(
																											"2");
																							createReceiveAfterCheck();
																						} else {
																							window.parent
																									.loadDataGrid('wholesaleGroupPurchaseOrder');
																							window.parent
																									.closeWinForm();
																						}
																					});
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
	var url = Utils.getRootPath() + '/wholesaleGroupPurchase/order/checkNoPass';
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
					window.parent.loadDataGrid('wholesaleGroupPurchaseOrder');
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
	var id = $('#orderId').val();
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
	var orderId = $('#orderId').val();
	var status = $('#orderStatus').val();
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
				+ '/wholesaleGroupPurchase/order/createWholeSellReceive?id='
				+ orderId;
		$
				.ajax({
					type : "post",
					url : url,
					async : true,
					dataType : 'json',
					success : function(result) {
						if (result.message == "保存成功") {
							$.messager
									.alert(
											'提示',
											'保存成功！',
											'info',
											function() {
												window.parent
														.loadDataGrid('wholesaleGroupPurchaseOrder');
												window.parent.closeWinForm();
											});
						} else if (result.message == "保存失败") {
							$.messager
									.alert(
											'提示',
											'该采购单已生成了采购收货单，请重新选择！',
											'warning',
											function() {
												window.parent
														.loadDataGrid('wholesaleGroupPurchaseOrder');
												window.parent.closeWinForm();
											});
						} else {
							$.messager
									.alert(
											'提示',
											'未能同时生成采购收货单，请联系管理员！',
											'error',
											function() {
												window.parent
														.loadDataGrid('wholesaleGroupPurchaseOrder');
												window.parent.closeWinForm();
											});
						}
					}
				});
	}
}

function addWholeSell() {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	var status = rows[0].STATUS;
	var orderId = rows[0].ID;
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
				+ '/wholesaleGroupPurchase/order/createWholeSellReceive?id='
				+ orderId;
		$
				.ajax({
					type : "post",
					url : url,
					async : true,
					dataType : 'json',
					success : function(result) {
						if (result.message == "保存成功") {
							$.messager
									.alert(
											'提示',
											'保存成功！',
											'info',
											function() {
												window.parent
														.loadDataGrid('wholesaleGroupPurchaseOrder');
												window.parent.closeWinForm();
											});
						} else if (result.message == "保存失败") {
							$.messager
									.alert(
											'提示',
											'该采购单已生成了采购收货单，请重新选择！',
											'warning',
											function() {
												window.parent
														.loadDataGrid('wholesaleGroupPurchaseOrder');
												window.parent.closeWinForm();
											});
						} else {
							$.messager
									.alert(
											'提示',
											'未能同时生成采购收货单，请联系管理员！',
											'error',
											function() {
												window.parent
														.loadDataGrid('wholesaleGroupPurchaseOrder');
												window.parent.closeWinForm();
											});
						}
					}
				});
	}
}

// 订单作废
function abolishInfo() {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要作废的订单！', 'warning');
		return;
	}
	var row = rows[0];
	if (row.STATUS == "5") {
		$.messager.alert('提示', '该订单已作废！', 'warning');
		return;
	} else {
		$.messager
				.confirm(
						'确认 ',
						'确定要作废该订单 ？',
						function(r) {
							if (r) {
								$
										.ajax({
											type : "post",
											url : Utils.getRootPath()
													+ '/wholesaleGroupPurchase/order/abolish',
											data : {
												id : row.ID
											},
											async : false,
											dataType : 'json',
											success : function(result) {
												if (result.result) {
													$.messager
															.alert(
																	'提示',
																	'订单作废成功',
																	'info',
																	function() {
																		window.parent
																				.loadDataGrid('wholesaleGroupPurchaseOrder');
																		window.parent
																				.closeWinForm();
																	});
												} else if (result.status == "error") {
													$.messager.alert('提示',
															'订单作废失败，请联系管理员！',
															'error');
												}
											}
										});
							}
							;
						});
	}
};

// 导入
function batchImport(id) {
	var url = Utils.getRootPath() + '/importExcel/saveWGPOrderImport?id=' + id;
	$('#importBtn').linkbutton({
		iconCls : 'icon-save'
	});
	$('#importBtn').text('导入中');
	$('#myBatch').form('submit', {
		url : url,
		dataType : 'html',
		onSubmit : function() {
		},
		success : function(data) {
			$('#importBtn').linkbutton({
				iconCls : 'icon-save'
			});
			$('#importBtn').text('导入');
			var result = eval("(" + data + ")");
			if (result.msg == "fail") {
				$.messager.alert('提示', '导入失败', 'error');

			} else if (result.msg == "success") {
				$.messager.alert('提示', '导入成功！', 'info', function() {
					window.parent.loadDataGrid('wholesaleGroupPurchaseOrder');
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
function exportExcel(orderNumber, id) {
	var url = Utils.getRootPath() + '/exportExcel/outWGPOrderExcel?id=' + id
			+ '&orderNumber=' + orderNumber;
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

function deleteChose() {
	var rows = $('#dgAdd').datagrid('getSelections');
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
	var data = $('#dgAdd').datagrid('getData');
	var idStr = "";
	var num = "";
	var rate = "";
	var goodsPrice = "";
	for (var i = 0; i < data.rows.length; i++) {
		$('#dgAdd').datagrid('endEdit', i);
		$('#dgAdd').datagrid('beginEdit', i);
		idStr += data.rows[i].ID + ",";
		if (data.rows[i].RATE == null || data.rows[i].RATE == ''
				|| data.rows[i].RATE == 'undefiend') {
			rate += "0.00,";
		} else {
			rate += data.rows[i].RATE + ",";
		}
		if (data.rows[i].NUMS == null || data.rows[i].NUMS == ''
				|| data.rows[i].NUMS == 'undefiend') {
			num += "0.00,";
		} else {
			num += data.rows[i].NUMS + ",";
		}
		goodsPrice += data.rows[i].GOODSPRICE + ",";
	}
	$.messager.confirm('确认 ', '确定要删除 ？', function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : Utils.getRootPath()
						+ '/wholesaleGroupPurchase/order/deleteChose?num='
						+ num + '&idStr=' + idStr + '&goodsPrice=' + goodsPrice
						+ '&rate=' + rate,
				data : {
					id : ids
				},
				async : false,
				dataType : 'json',
				success : function(result) {
					if (result.result) {
						$.messager.alert('提示', '移除成功', 'info', function() {
							window.parent.loadDataGrid('purchaseOrders');
							$('#dgAdd').datagrid('reload');
						});
					} else if (result.status == "error") {
						$.messager.alert('提示', '移除失败，请联系管理员！', 'error');
						$('#dgAdd').datagrid('reload');
					}
				}
			});
		}
		;
	});
}
