//打开页面
function open(url, title, x, y) {
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
		$.messager.alert('提示', '只能选择一条数据进行操作！', 'warning');
		return;
	}
	var row = rows[0];
	if (row.STATUE == "1") {
		$.messager.alert('提示', '该退货单已提交！', 'warning');
		return;
	} else if (row.STATUE == "2") {
		$.messager.alert('提示', '该退货单已通过审核！', 'warning');
		return;
	} else if (row.STATUE == "4") {
		$.messager.alert('提示', '该退货单已完成！', 'warning');
		return;
	} else if (row.STATUE == "5") {
		$.messager.alert('提示', '该退货单已作废！', 'warning');
		return;
	} else {
		var url = Utils.getRootPath()
				+ '/purchase/purchaseReturn/gotoEditPurchaseReturn?id='
				+ row.ID;
		open(url, title, x, y);
	}
}

// 根据收货单商品选中行后填写退货数量生成采购退货单
function saveLineToReturn() {
	var data = $('#dg').datagrid('getData');
	var ids = "";
	var receiveNum = "";
	var returnNum = "";
	var receiveId = $('#purchaseReceiveId').val();
	var nullFlag = "";
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		if (data.rows[i].CREATEFLAG != "1") {
			nullFlag += data.rows[i].RETURNNUM;
		}
		if (data.rows[i].CREATEFLAG == "1" && data.rows[i].RETURNNUM != "") {
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
		if (data.rows[i].RETURNNUM != null && data.rows[i].RETURNNUM != ""
				&& data.rows[i].RETURNNUM != "undefiend"
				&& data.rows[i].RETURNNUM != "0") {
			if (data.rows[i].CREATEFLAG == "1") {
				$('#dg').datagrid('beginEdit', i);
				$("#dg").datagrid('load');
				$.messager.alert('提示', '存在已生成采购退货单的商品，请重新确认！', 'warning');
				return;
			}
			if (parseFloat(data.rows[i].ACTUAL_NUM) < parseFloat(data.rows[i].RETURNNUM)) {
				$('#dg').datagrid('beginEdit', i);
				$.messager.alert('提示', '退货数量不能大于收货数量！', 'warning');
				return;
			}
			ids += data.rows[i].ID + ",";
			receiveNum += data.rows[i].ACTUAL_NUM + ",";
			returnNum += data.rows[i].RETURNNUM + ",";
		}
	}
	var url = Utils.getRootPath()
			+ '/purchase/purchaseReturn/createPurchaseByReceiveGoods?ids='
			+ ids + '&receiveNum=' + receiveNum + '&returnNum=' + returnNum
			+ '&receiveId=' + receiveId;
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
// 获取选中的行
var getRowsIddd = function() {
	var ids = [];
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	for (var i = 0; i < rows.length; i++) {
		ids.push(rows[i].ID);
	}
	return ids;
};

var getRowsReceiveNum = function() {
	var receiveNum = [];
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	for (var i = 0; i < rows.length; i++) {
		receiveNum.push(rows[i].ACTUAL_NUM);
	}
	return receiveNum;
};

var getRowsReturnNum = function() {
	var returnNum = [];
	var row = $('#dg').datagrid('getRows');
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	for (var i = 0; i < rows.length; i++) {
		if (rows[i].RETURNNUM == 0 || rows[i].RETURNNUM == null) {
			for (var j = 0; j < row.length; j++) {
				$('#dg').datagrid('beginEdit', j);
			}
			$.messager.alert('提示', '请填写需要退货的数量！', 'warning');
			return;
		}
		returnNum.push(rows[i].RETURNNUM);
	}
	return returnNum;
};

// 查看退货单明细
function openPurchaseReturnItem(id) {
	var url = Utils.getRootPath()
			+ '/purchase/purchaseReturn/gotoDetailPurchaseReturn?id=' + id;
	open(url, '退货单详情', 1000, 550);
}

// 删除
function destroyInfo() {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	var row = rows[0];
	if (row.STATUE == "2") {
		$.messager.alert('提示', '该退货单已通过审核！不可刪除！', 'warning');
		return;
	} else if (row.STATUE == "3") {
		$.messager.alert('提示', '该退货单待处理！不可刪除！', 'warning');
		return;
	} else if (row.STATUE == "1") {
		$.messager.alert('提示', '该退货单已提交！不可刪除！', 'warning');
		return;
	} else if (row.STATUE == "4") {
		$.messager.alert('提示', '该退货单已完成！不可刪除！', 'warning');
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
							+ '/purchase/purchaseReturn/delete',
					data : {
						id : ids
					},
					async : false,
					dataType : 'json',
					success : function(result) {
						if (result.result) {
							$.messager.alert('提示', '删除成功', 'info', function() {
								window.parent.loadDataGrid('purchaseReturn');
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

// 计算收货数量
function actualNum2(val, rec) {
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
// 计算退货数量
function actualNum(val, rec) {
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
	var nums = rec.RETURNNUM;
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
	if (row.STATUE == "2") {
		$.messager.alert('提示', '该退货单已通过审核！', 'warning');
		return;
	} else if (row.STATUE == "3") {
		$.messager.alert('提示', '该退货单正在处理中！', 'warning');
		return;
	} else if (row.STATUE == "0") {
		$.messager.alert('提示', '该退货单还未提交！', 'warning');
		return;
	} else if (row.STATUE == "4") {
		$.messager.alert('提示', '该退货单已完成！', 'warning');
		return;
	} else if (row.STATUE == "5") {
		$.messager.alert('提示', '该退货单已作废！', 'warning');
		return;
	} else {
		var url = Utils.getRootPath()
				+ '/purchase/purchaseReturn/gotoEditCheck?id=' + row.ID;
		open(url, title, x, y);
	}
}

// 审核通过
function checkPass() {
	var id = $('#returnId').val();
	var data = $('#dg').datagrid('getData');
	var ids = "";
	var moneys = "";
	for (var i = 0; i < data.rows.length; i++) {
		ids += data.rows[i].ID + ",";
		moneys += data.rows[i].MONEY + ",";
	}
	var url = Utils.getRootPath() + '/purchase/purchaseReturn/checkPass?id='
			+ id + '&ids=' + ids + '&moneys=' + moneys;
	$.messager.confirm('确认 ', '确定该退货单通过 ？', function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : url,
				async : false,
				dataType : 'json',
				success : function(result) {
					if (result.message == "保存成功") {
						$.messager.alert('提示', '审核通过！', 'info', function() {
							window.parent.loadDataGrid('purchaseReturn');
							window.parent.closeWinForm();
						});
					} else if (result.message == "查询失败") {
						$.messager.alert('提示', '库存中没有该商品，请重新确认！', 'info');
						$('#dg').datagrid('reload');
					} else if (result.message == "更新失败") {
						$.messager.alert('提示', '该商品库存不足，请重新确认！', 'info');
						$('#dg').datagrid('reload');
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
	var url = Utils.getRootPath() + '/purchase/purchaseReturn/checkNoPass';
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
	var id = $('#returnId').val();
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

// 保存
function saveAndCommit(formId) {
	var data = $('#dg').datagrid('getData');
	var returnId = $('#returnId').val();
	if (data.rows.length == 0) {
		$.messager.alert('提示', '该退货单没有商品，请删除！', 'warning');
		return;
	}
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		if (data.rows[i].RETURNNUM <= 0 || data.rows[i].RETURNNUM == null) {
			$('#dg').datagrid('beginEdit', i);
			$.messager.alert('提示', '请填写实际退货的数量！', 'warning');
			return;
		}
	}
	var ids = "";
	var num = "";
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		if (parseFloat(data.rows[i].RETURNNUM) > parseFloat(data.rows[i].RECEIVENUM)) {
			$('#dg').datagrid('beginEdit', i);
			$.messager.alert('提示', '退货数量不能超过实际收货数量！', 'warning');
			return;
		}
		ids += data.rows[i].ID + ",";
		num += data.rows[i].RETURNNUM + ",";
	}
	var url = Utils.getRootPath()
			+ '/purchase/purchaseReturn/saveAndCommit?id=' + returnId + "&ids="
			+ ids + "&num=" + num;
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
				$.messager.alert('提示', '系统错误，请联系管理员！', 'error');
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
		if (rows[i].RETURNNUM == 0 || rows[i].RETURNNUM == null) {
			for (var j = 0; j < rows.length; j++) {
				$('#dg').datagrid('beginEdit', j);
			}
			$.messager.alert('提示', '请填写需要退货的数量！', 'warning');
			return;
		}
		num.push(rows[i].RETURNNUM);
	}
	return num;
};

// 提交审核(带保存功能)
function submitCheck(formId) {
	var returnId = $('#returnId').val();
	var status = '$!{purchaseReturn.statue}';
	var data = $('#dg').datagrid('getData');
	if (data.rows.length == 0) {
		$.messager.alert('提示', '该退货单没有商品，请删除！', 'warning');
		return;
	}
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		if (data.rows[i].RETURNNUM <= 0 || data.rows[i].RETURNNUM == null) {
			$('#dg').datagrid('beginEdit', i);
			$.messager.alert('提示', '请填写实际退货数量，保存后再提交！', 'warning');
			return;
		}
	}
	var ids = "";
	var num = "";
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		if (parseFloat(data.rows[i].RETURNNUM) > parseFloat(data.rows[i].RECEIVENUM)) {
			$('#dg').datagrid('beginEdit', i);
			$.messager.alert('提示', '退货数量不能超过实际收货数量！', 'warning');
			return;
		}
		ids += data.rows[i].ID + ",";
		num += data.rows[i].RETURNNUM + ",";
	}
	var urls = Utils.getRootPath()
			+ '/purchase/purchaseReturn/saveAndCommit?id=' + returnId + "&ids="
			+ ids + "&num=" + num;
	$.ajax({
		type : "post",
		url : urls,
		data : $('#' + formId).serialize(),
		async : false,
		dataType : 'json',
		success : function(result) {
			if (result.result) {
			} else {
				$.messager.alert('提示', '系统错误，请联系管理员！', 'error');
			}
		}
	});
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
				+ '/purchase/purchaseReturn/submitCheck?id=' + returnId;
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
					$.messager.alert('提示', '提交失败，请联系管理员！', 'error');
				}
			}
		});
	}
}

// 提交审核(单独提交审核)
function submitCheckNumber1() {
	var returnId = $('#returnId').val();
	var status = '$!{purchaseReturn.statue}';
	var data = $('#dg').datagrid('getData');
	if (data.rows.length == 0) {
		$.messager.alert('提示', '该退货单没有商品，请删除！', 'warning');
		return;
	}
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		if (data.rows[i].RETURNNUM <= 0 || data.rows[i].RETURNNUM == null) {
			$('#dg').datagrid('beginEdit', i);
			$.messager.alert('提示', '请填写实际退货数量，保存后再提交！', 'warning');
			return;
		}
	}
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
				+ '/purchase/purchaseReturn/submitCheck?id=' + returnId;
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
					$.messager.alert('提示', '提交失败，请联系管理员！', 'error');
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
	var goodsPrice = "";
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		$('#dg').datagrid('beginEdit', i);
		idStr += data.rows[i].ID + ",";
		if (data.rows[i].RETURNNUM == null || data.rows[i].RETURNNUM == ''
				|| data.rows[i].RETURNNUM == 'undefiend') {
			num += "0.00,";
		} else {
			num += data.rows[i].RETURNNUM + ",";
		}
		goodsPrice += data.rows[i].GOODSPRICE + ",";
	}
	$.messager.confirm('确认 ', '确定要删除 ？', function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : Utils.getRootPath()
						+ '/purchase/purchaseReturn/removeGoods?num=' + num
						+ '&idStr=' + idStr + '&goodsPrice=' + goodsPrice,
				data : {
					id : ids
				},
				async : false,
				dataType : 'json',
				success : function(result) {
					if (result.result) {
						$.messager.alert('提示', '删除成功', 'info', function() {
							window.parent.loadDataGrid('purchaseOrders');
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
}

function previewPrint() {
	var returnId = $('#returnId').val();
	$.ajax({
		type : "get",
		url : Utils.getRootPath()
				+ '/purchase/purchaseReturn/gotoPrintPurchaseReturn?id='
				+ returnId,
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
	LODOP.PRINT_INIT("采购退货单表格");
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
