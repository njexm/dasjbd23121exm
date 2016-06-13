function openEdit() {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要编辑的销售单！', 'warning');
		return;
	}
	if (rows.length > 1) {
		$.messager.alert('提示', '只能选择一条销售单进行编辑！', 'warning');
		return;
	}
	var row = rows[0];
	if (row.STATUE == "1") {
		$.messager.alert('提示', '该销售单已提交！', 'warning');
		return;
	} else if (row.STATUE == "2") {
		$.messager.alert('提示', '该销售单已通过审核！', 'warning');
		return;
	} else if (row.STATUE == "4") {
		$.messager.alert('提示', '该销售单已完成！', 'warning');
		return;
	} else if (row.STATUE == "5") {
		$.messager.alert('提示', '该销售单已作废！', 'warning');
		return;
	} else {
		var url = Utils.getRootPath()
				+ '/wholesaleGroupPurchase/wholeSell/openEdit?id=' + row.ID;
		window.parent.initAdd(url, '编辑团购销售单', 1000, 550);
	}
}
// 打开明细页面
function openWholeSellDetail(id) {
	var url = Utils.getRootPath()
			+ '/wholesaleGroupPurchase/wholeSell/openWholeSellDetail?id=' + id;
	window.parent.initAdd(url, '团购销售单明细', 1000, 550);
}

function updateWholeSell(formId) {
	var data = $('#dg').datagrid('getData');
	var wholeSellId = $('#wholeSellId').val();
	if (data.rows.length == 0) {
		$.messager.alert('提示', '该销售单没有商品，请删除！', 'warning');
		return;
	}
	var customerInfoId = $('#customerInfoId').combobox('getValue');
	if (customerInfoId.length == 0) {
		$.messager.alert('提示', '请选择客户！', 'warning');
		return;
	}
	var branchId = $('#branchId').combobox('getValue');
	if (branchId.length == 0) {
		$.messager.alert('提示', '请选择仓库！', 'warning');
		return;
	}
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		$('#dg').datagrid('beginEdit', i);
		if (data.rows[i].NUMS <= 0 || data.rows[i].NUMS == null) {
			$.messager.alert('提示', '请填写销售数量(大于0)！', 'warning');
			return;
		}
	}
	var ids = "";
	var num = "";
	var rate = "";
	for (var i = 0; i < data.rows.length; i++) {
		ids += data.rows[i].ID + ",";
		num += data.rows[i].NUMS + ",";
		rate += data.rows[i].RATE + ",";
	}
	var url = Utils.getRootPath()
			+ '/wholesaleGroupPurchase/wholeSell/updateWholeSell?id='
			+ wholeSellId + '&nums=' + num + '&ids=' + ids + '&rates=' + rate;
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
	var wholeSellId = $('#wholeSellId').val();
	var data = $('#dg').datagrid('getData');
	if (data.rows.length == 0) {
		$.messager.alert('提示', '该销售单没有商品，请删除！', 'warning');
		return;
	}
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		$('#dg').datagrid('beginEdit', i);
		if (data.rows[i].NUMS <= 0 || data.rows[i].NUMS == null) {
			$.messager.alert('提示', '请填写实际采购数量保存后再提交！', 'warning');
			return;
		}
	}
	var status = '$!{wholeSell.statue}';
	if (status == "1") {
		$.messager.alert('提示', '该销售单已经提交！', 'warning');
		return;
	} else if (status == "2") {
		$.messager.alert('提示', '该销售单审核已通过！', 'warning');
		return;
	} else if (status == "3") {
		$.messager.alert('提示', '该销售单正在处理中！', 'warning');
		return;
	} else if (status == "4") {
		$.messager.alert('提示', '该销售单已完成！', 'warning');
		return;
	} else if (status == "5") {
		$.messager.alert('提示', '该销售单已作废！', 'warning');
		return;
	} else {
		var url = Utils.getRootPath()
				+ '/wholesaleGroupPurchase/wholeSell/submitCheck?id='
				+ wholeSellId;
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

// 提交审核（带保存功能）
function submitCheckAndSave(formId) {
	var data = $('#dg').datagrid('getData');
	var wholeSellId = $('#wholeSellId').val();
	if (data.rows.length == 0) {
		$.messager.alert('提示', '该销售单没有商品，请删除！', 'warning');
		return;
	}
	var customerInfoId = $('#customerInfoId').combobox('getValue');
	if (customerInfoId.length == 0) {
		$.messager.alert('提示', '请选择客户！', 'warning');
		return;
	}
	var branchId = $('#branchId').combobox('getValue');
	if (branchId.length == 0) {
		$.messager.alert('提示', '请选择仓库！', 'warning');
		return;
	}
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		$('#dg').datagrid('beginEdit', i);
		if (data.rows[i].NUMS <= 0 || data.rows[i].NUMS == null) {
			$.messager.alert('提示', '请填写销售数量(大于0)！', 'warning');
			return;
		}
	}
	var ids = "";
	var num = "";
	var rate = "";
	for (var i = 0; i < data.rows.length; i++) {
		ids += data.rows[i].ID + ",";
		num += data.rows[i].NUMS + ",";
		rate += data.rows[i].RATE + ",";
	}
	var urls = Utils.getRootPath()
			+ '/wholesaleGroupPurchase/wholeSell/updateWholeSell?id='
			+ wholeSellId + '&nums=' + num + '&ids=' + ids + '&rates=' + rate;
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
	var status = '$!{wholeSell.statue}';
	if (status == "1") {
		$.messager.alert('提示', '该销售单已经提交！', 'warning');
		return;
	} else if (status == "2") {
		$.messager.alert('提示', '该销售单审核已通过！', 'warning');
		return;
	} else if (status == "3") {
		$.messager.alert('提示', '该销售单正在处理中！', 'warning');
		return;
	} else if (status == "4") {
		$.messager.alert('提示', '该销售单已完成！', 'warning');
		return;
	} else if (status == "5") {
		$.messager.alert('提示', '该销售单已作废！', 'warning');
		return;
	} else {
		var url = Utils.getRootPath()
				+ '/wholesaleGroupPurchase/wholeSell/submitCheck?id='
				+ wholeSellId;
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
	var rates = "";
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		$('#dg').datagrid('beginEdit', i);
		idStr += data.rows[i].ID + ",";
		if (data.rows[i].NUMS == null || data.rows[i].NUMS == ''
				|| data.rows[i].NUMS == 'undefiend') {
			num += "0.00,";
		} else {
			num += data.rows[i].NUMS + ",";
		}
		if (data.rows[i].RATE == null || data.rows[i].RATE == ''
				|| data.rows[i].RATE == 'undefiend') {
			rates += "0.00,";
		} else {
			rates += data.rows[i].RATE + ",";
		}
	}
	$.messager.confirm('确认 ', '确定要删除 ？', function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : Utils.getRootPath()
						+ '/wholesaleGroupPurchase/wholeSell/removeGoods?num='
						+ num + '&idStr=' + idStr + '&rates=' + rates,
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

function deleteWholeSell() {
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
		$.messager.alert('提示', '该销售单已通过审核，不可刪除!', 'warning');
		return;
	} else if (row.STATUE == "3") {
		$.messager.alert('提示', '该销售单待处理，不可刪除!', 'warning');
		return;
	} else if (row.STATUE == "1") {
		$.messager.alert('提示', '该销售单已提交，不可刪除!', 'warning');
		return;
	} else if (row.STATUE == "4") {
		$.messager.alert('提示', '该销售单已完成，不可刪除!', 'warning');
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
													+ '/wholesaleGroupPurchase/wholeSell/deleteWholeSell',
											data : {
												id : ids
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
																				.loadDataGrid('goodfile');
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
		$.messager.alert('提示', '该销售单已通过审核！', 'warning');
		return;
	} else if (row.STATUE == "3") {
		$.messager.alert('提示', '该销售单正在处理中！', 'warning');
		return;
	} else if (row.STATUE == "0") {
		$.messager.alert('提示', '该销售单还未提交！', 'warning');
		return;
	} else if (row.STATUE == "4") {
		$.messager.alert('提示', '该销售单已完成！', 'warning');
		return;
	} else if (row.STATUE == "5") {
		$.messager.alert('提示', '该销售单已作废！', 'warning');
		return;
	} else {
		var url = Utils.getRootPath()
				+ '/wholesaleGroupPurchase/wholeSell/gotoEditCheck?id='
				+ row.ID;
		window.parent.initAdd(url, '审核团购销售单', 1000, 550);
	}
}

// 审核通过
function checkPass() {
	var data = $('#dg').datagrid('getData');
	var ids = "";
	var goodsAmount = "";
	for (var i = 0; i < data.rows.length; i++) {
		if (parseFloat(data.rows[i].STORE) < parseFloat(data.rows[i].NUMS)) {
			$.messager.alert('提示', '库存量不足，不能审核通过！', 'warning');
			return;
		}
	}
	for (var i = 0; i < data.rows.length; i++) {
		ids += data.rows[i].ID + ",";
		goodsAmount += data.rows[i].AMOUNT + ",";
	}
	if (ids.length == 0) {
		$.messager.alert('提示', '没有商品，确定审核通过？', 'warning');
		return;
	}
	var id = $('#wholeSellId').val();
	var url = Utils.getRootPath()
			+ '/wholesaleGroupPurchase/wholeSell/checkPass?id=' + id + '&ids='
			+ ids + '&goodsAmount=' + goodsAmount;
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
			+ '/wholesaleGroupPurchase/wholeSell/checkNoPass';
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
	var id = $('#wholeSellId').val();
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

function openAddReturn() {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要退货的销售单！', 'warning');
		return;
	}
	if (rows.length > 1) {
		$.messager.alert('提示', '只能选择一条销售单进行退货！', 'warning');
		return;
	}
	var row = rows[0];
	if (row.STATUE == "0") {
		$.messager.alert('提示', '该销售单还未提交！', 'warning');
		return;
	} else if (row.STATUE == "1") {
		$.messager.alert('提示', '该销售单还未审核！', 'warning');
		return;
	} else if (row.STATUE == "4") {
		$.messager.alert('提示', '该销售单已完成！', 'warning');
		return;
	} else if (row.STATUE == "3") {
		$.messager.alert('提示', '该销售单正在处理中！', 'warning');
		return;
	} else {
		var url = Utils.getRootPath()
				+ '/wholesaleGroupPurchase/wholeSell/openAddReturn?id='
				+ row.ID;
		window.parent.initAdd(url, '新增团购退货单', 1000, 550);
	}
}

function saveLineToReturn() {
	var data = $('#dg').datagrid('getData');
	var ids = "";
	var goodsPrices = "";
	var rates = "";
	var nums = "";
	var returnNums = "";
	var wholeSellId = $('#wholeSellId').val();
	var nullFlag = "";
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		if (data.rows[i].CREATEFLAG != "1") {
			nullFlag += data.rows[i].RETURNNUMS;
		}
		if (data.rows[i].CREATEFLAG == "1" && data.rows[i].RETURNNUMS != "") {
			$("#dg").datagrid('load');
			$.messager.alert('提示', '存在已生成采购退货单的商品，请重新确认！', 'warning');
			return;
		}
	}
	if (nullFlag == "") {
		$("#dg").datagrid('load');
		$.messager.alert('提示', '请填写退货数量！', 'warning');
		return;
	}
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		if (data.rows[i].RETURNNUMS != null && data.rows[i].RETURNNUMS != ""
				&& data.rows[i].RETURNNUMS != "undefiend"
				&& data.rows[i].RETURNNUMS != "0") {
			if (data.rows[i].CREATEFLAG == "1") {
				$('#dg').datagrid('beginEdit', i);
				$("#dg").datagrid('load');
				$.messager.alert('提示', '存在已生成采购退货单的商品，请重新确认！', 'warning');
				return;
			}
			if (parseFloat(data.rows[i].NUMS) < parseFloat(data.rows[i].RETURNNUMS)) {
				$('#dg').datagrid('beginEdit', i);
				$.messager.alert('提示', '退货数量不能大于收货数量！', 'warning');
				return;
			}
			ids += data.rows[i].ID + ",";
			goodsPrices += data.rows[i].ORDER_PRICE + ",";
			rates += data.rows[i].RATE + ",";
			nums += data.rows[i].NUMS + ",";
			returnNums += data.rows[i].RETURNNUMS + ",";
		}
	}
	var url = Utils.getRootPath()
			+ '/wholesaleGroupPurchase/wholeSell/saveLineToReturn?ids=' + ids
			+ '&nums=' + nums + '&returnNum=' + returnNums + '&goodsPrices='
			+ goodsPrices + '&rates=' + rates + '&wholeSellId=' + wholeSellId;
	$("#ddialog").dialog('open');
	$.ajax({
		type : "post",
		url : url,
		async : true,
		dataType : 'json',
		success : function(result) {
			$("#ddialog").dialog('close');
			if (result.message == "保存成功") {
				$.messager.alert('提示', '退货单生成成功', 'info', function() {
					window.parent.loadDataGrid('purchaseOrders');
					window.parent.closeWinForm();
				});
			} else if (result.message == "保存error") {
				$.messager.alert('提示', '存在已生成采购退货单的商品，请重新确认！', 'info');
			} else {
				$.messager.alert('提示', '保存失败，请联系管理员！', 'error');
			}
		}
	});
}