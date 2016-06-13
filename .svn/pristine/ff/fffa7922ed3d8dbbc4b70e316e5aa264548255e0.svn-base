function newAddProcessGoods() {
	$.messager.confirm('确认 ', '确定新增一条加工单 ？', function(r) {
		if (r) {
			var url = Utils.getRootPath()
					+ '/wareHouse/processGoods/newAddProcessGoods';
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
		;
	});
}

// 查看明细
function openDetail(id) {
	var url = Utils.getRootPath() + '/wareHouse/processGoods/openDetail?id='
			+ id;
	window.parent.initAdd(url, '加工单详情', 1000, 550);
}

function openEditProcessGoods() {
	
	var rows = $('#dg').datagrid('getSelections');
	var id=rows[0].ID;
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要编辑的加工单！', 'warning');
		return;
	}
	if (rows.length > 1) {
		$.messager.alert('提示', '只能选择一条加工单进行编辑！', 'warning');
		return;
	}
	var row = rows[0];
	if (row.STATUE == "1") {
		$.messager.alert('提示', '该加工单已提交！', 'warning');
		return;
	} else if (row.STATUE == "2") {
		$.messager.alert('提示', '该加工单已通过审核！', 'warning');
		return;
	} else if (row.STATUE == "4") {
		$.messager.alert('提示', '该加工单已完成！', 'warning');
		return;
	} else if (row.STATUE == "5") {
		$.messager.alert('提示', '该加工单已作废！', 'warning');
		return;
	} else {
		var url = Utils.getRootPath()
				+ '/wareHouse/processGoods/openEditProcessGoods?id=' + id;
		window.parent.initAdd(url, '编辑加工单', 1000, 550);
	}
}

function choose() {
	var processGoodsId = $('#processGoodsId').val();
	var data = $('#dg').datagrid('getData');
	var ids = "";
	var num = "";
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		if (data.rows[i].USENUM == null || data.rows[i].USENUM == "") {
			num += "0,";
		} else {
			num += data.rows[i].USENUM + ",";
		}
		ids += data.rows[i].ID + ",";
	}
	$.ajax({
		type : "get",
		url : Utils.getRootPath()
				+ '/wareHouse/processGoods/openChoseGoods?processGoodsId='
				+ processGoodsId + '&ids=' + ids + '&num=' + num,
		data : {},
		success : function(data) {
			$('#selectOptions').window({
				title : '选择商品',
				closable : true,
				maximizable : false,
				modal : false,
				draggable : false,
				width : 900,
				height : 420
			});
			$('#selectOptions').html(data);
		}
	});
}

function saveCheck() {
	var processGoodsId = $('#processGoodsId').val();
	var rows = $('#goodsDg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择至少一条数据！', 'warning');
		return;
	}
	var ids = "";
	var useNums = "";
	for (var i = 0; i < rows.length; i++) {
		ids += rows[i].GOODSFILE_ID + ",";
		if (parseFloat(rows[i].GOODSUSENUMS) >= parseFloat(rows[i].USENEEDSNUMS)) {
			useNums += rows[i].USENEEDSNUMS + ",";
		} else {
			useNums += rows[i].GOODSUSENUMS + ",";
		}
	}
	var url = Utils.getRootPath() + '/wareHouse/processGoods/saveCheck?ids='
			+ ids + '&processGoodsId=' + processGoodsId + '&useNums=' + useNums;
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
					//window.parent.closeWinForm();
				});
			} else if (result.message == "保存error") {
				$.messager.alert('提示', '已存在勾选的商品中的一件或多件，请重新确认', 'info',
						function() {
							$('#dg').datagrid('reload');
						});
			} else if (result.message == "查询失败") {
				$.messager.alert('提示', '勾选的商品中有一种或多种没有原材料与之对应，请联系管理员', 'info',
						function() {
							$('#dg').datagrid('reload');
						});
			} else {
				$.messager.alert('提示', '添加失败，请联系管理员', 'error');
				$('#dg').datagrid('reload');
			}
		}
	});	
//	updateProcessUseGoods();
}

function addProcess(formId) {
	var data = $('#dg').datagrid('getData');
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		if (data.rows[i].PROCESSNUM == 0 || data.rows[i].PROCESSNUM == null) {
			$('#dg').datagrid('beginEdit', i);
			$.messager.alert('提示', '请填写需要领取的数量！', 'warning');
			return;
		}
	}
	var ids = "";
	var num = "";
	for (var i = 0; i < data.rows.length; i++) {
		ids += data.rows[i].ID + ",";
		num += data.rows[i].PROCESSNUM + ",";
	}
	if (ids.length == 0) {
		$.messager.alert('提示', '您还未选择商品原料！', 'warning');
		return;
	}
	var drawMan = $('#drawMan').combobox('getValue');
	if (drawMan.length == 0) {
		$.messager.alert('提示', '请选择领料人！', 'warning');
		return;
	}
	for (var i = 0; i < num.length; i++) {
		if (num[i].length == 0) {
			$.messager.alert('提示', '请填写需要采购的数量！', 'warning');
			return;
		}
	}
	var url = Utils.getRootPath() + '/wareHouse/processHouse/addProcess?ids='
			+ ids + '&nums=' + num + '&drawMan=' + drawMan;
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
				} else {
					$.messager.alert('提示', '新增失败，请联系管理员', 'error');
				}
			}
		});
	}
}

function deleteProcessGoods() {
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
		$.messager.alert('提示', '该加工单已通过审核！', 'warning');
		return;
	} else if (row.STATUE == "1") {
		$.messager.alert('提示', '该加工单已提交！', 'warning');
		return;
	} else if (row.STATUE == "3") {
		$.messager.alert('提示', '该加工单审核未通过！', 'warning');
		return;
	} else if (row.STATUE == "4") {
		$.messager.alert('提示', '该加工单已完成！', 'warning');
		return;
	} else {
		$.messager.confirm('确认 ', '确定要删除 ？', function(r) {
			if (r) {
				$.ajax({
					type : "post",
					url : Utils.getRootPath()
							+ '/wareHouse/processGoods/deleteProcessGoods',
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
							$.messager.alert('提示', '删除失败，请联系管理员', 'error');
						}
					}
				});
			}
			;
		});
	}
}

function removeUseGoods() {
	var processGoodsId = $('#processGoodsId').val();
	var rowss = $('#datagrid').datagrid('getSelections');
	if (rowss.length != 0) {
		$.messager.alert('提示', '不可移除成品！', 'warning');
		return;
	}
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要移除的数据！', 'warning');
		return;
	}
	var ids = "";
	for (var i = 0; i < rows.length; i++) {
		ids += rows[i].ID + ",";
	}
	$.messager
			.confirm(
					'确认 ',
					'确定要移除该商品 ？',
					function(r) {
						if (r) {
							$
									.ajax({
										type : "post",
										url : Utils.getRootPath()
												+ '/wareHouse/processGoods/removeUseGoods?processGoodsId='
												+ processGoodsId,
										data : {
											id : ids
										},
										async : false,
										dataType : 'json',
										success : function(result) {
											if (result.message == "删除成功") {
												$.messager.alert('提示', '移除成功',
														'info');
												$('#dg').datagrid('reload');
											} else if (result.message == "删除失败") {
												$.messager.alert('提示',
														'移除失败，请联系管理员', 'error');
												$('#dg').datagrid('reload');
											}
										}
									});
						}
						;
					});
}

function updateProcessUseGoods() {
	var processGoodsId = $('#processGoodsId').val();
	var data = $('#dg').datagrid('getData');
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		if (data.rows[i].USENUM == 0 || data.rows[i].USENUM == null) {
			$('#dg').datagrid('beginEdit', i);
			$.messager.alert('提示', '请填写原材料使用数量！', 'warning');
			return;
		}
	}
	var ids = "";
	var useNum = "";
	for (var i = 0; i < data.rows.length; i++) {
		ids += data.rows[i].ID + ",";
		useNum += data.rows[i].USENUM + ",";
	}
	if (useNum.length == 0) {
		$.messager.alert('提示', '该加工单没有原材料，请重新确认！', 'warning');
		return;
	}
	var url = Utils.getRootPath()
			+ '/wareHouse/processGoods/updateProcessUseGoods?useNum=' + useNum
			+ '&ids=' + ids + '&processGoodsId=' + processGoodsId;
	$.ajax({
		type : "post",
		url : url,
		async : true,
		dataType : 'json',
		success : function(result) {
			if (result.message == "保存成功") {
				$.messager.alert('提示', '保存成功', 'info', function() {
					window.parent.loadDataGrid('purchaseReceive');
					window.parent.closeWinForm();
				});
			} else if (result.message == "查询失败") {
				$.messager.alert('提示', '加工配送库原材料不足', 'warning', function() {
					$("#dg").datagrid('reload');
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

/*function updateProcessUseGoodsno() {
	
	var processGoodsId = $('#processGoodsId').val();
	var data = $('#dg').datagrid('getData');
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
	}
	var ids = "";
	var useNum = "";
	for (var i = 0; i < data.rows.length; i++) {
		ids += data.rows[i].ID + ",";
		useNum += data.rows[i].USENUM + ",";
	}
	alert();
	var url = Utils.getRootPath()
			+ '/wareHouse/processGoods/updateProcessUseGoodsno?useNum=' + useNum
			+ '&ids=' + ids + '&processGoodsId=' + processGoodsId;
	$.ajax({
		type : "post",
		url : url,
		async : true,
		dataType : 'json',
		success : function(result) {}
	});
}*/

function finishProcessGoods() {
	var processGoodsId = $('#processGoodsId').val();
	var data = $('#dg').datagrid('getData');
	if (data.rows.length == 0) {
		$.messager.alert('提示', '没有原材料请重新确认！', 'warning');
		return;
	}
	var datas = $('#datagrid').datagrid('getData');
	if (datas.rows.length == 0) {
		$.messager.alert('提示', '没有成品请重新确认！', 'warning');
		return;
	}
	var url = Utils.getRootPath()
			+ '/wareHouse/processGoods/finishProcessGoods?id=' + processGoodsId;
	$.ajax({
		type : "post",
		url : url,
		async : true,
		dataType : 'json',
		success : function(result) {
			if (result.message == "保存成功") {
				$.messager.alert('提示', '操作成功，加工结束！', 'info', function() {
					window.parent.loadDataGrid('purchaseOrders');
					window.parent.closeWinForm();
				});
			} else {
				$.messager.alert('提示', '操作失败，请联系管理员', 'error', function() {
					window.parent.loadDataGrid('purchaseOrders');
				});
			}
		}
	});
}
// 带保存功能提交
function commitCheck() {
	var data = $('#dg').datagrid('getData');
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		if (data.rows[i].USENUM == 0 || data.rows[i].USENUM == null) {
			$('#dg').datagrid('beginEdit', i);
			$.messager.alert('提示', '请填写原材料使用数量！', 'warning');
			return;
		}
	}
	var ids = "";
	var useNum = "";
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		if (parseInt(data.rows[i].USENUM) > parseInt(data.rows[i].STORE)) {
			$.messager.alert('提示', '加工配送库原材料不足！', 'warning');
			$("#dg").datagrid('reload');
			return;
		}
		ids += data.rows[i].ID + ",";
		useNum += data.rows[i].USENUM + ",";
	}
	if (useNum.length == 0) {
		$.messager.alert('提示', '该加工单没有商品，请重新确认！', 'warning');
		return;
	}
	var processGoodsId = $('#processGoodsId').val();
	var url = Utils.getRootPath()
			+ '/wareHouse/processGoods/updateProcessUseGoods?useNum=' + useNum
			+ '&ids=' + ids + '&processGoodsId=' + processGoodsId;
	$.ajax({
		type : "post",
		url : url,
		async : false,
		dataType : 'json',
		success : function(result) {
			if (result.message == "保存成功") {
				window.parent.loadDataGrid('purchaseReceive');
				$("#dg").datagrid('reload');
			} else {
				$.messager.alert('提示', '保存失败，请联系管理员', 'error', function() {
					window.parent.loadDataGrid('purchaseOrders');
					window.parent.closeWinForm();
				});
			}
		}
	});
	if (data.rows.length == 0) {
		$.messager.alert('提示', '该加工单没有原材料！', 'warning');
		return;
	}
	var status = '$!{processGoods.status}';
	if (status == "1") {
		$.messager.alert('提示', '该加工单已经提交！', 'warning');
		return;
	} else if (status == "2") {
		$.messager.alert('提示', '该加工单审核已通过！', 'warning');
		return;
	} else if (status == "3") {
		$.messager.alert('提示', '该加工单正在处理中！', 'warning');
		return;
	} else if (status == "4") {
		$.messager.alert('提示', '该加工单已完成！', 'warning');
		return;
	} else if (status == "5") {
		$.messager.alert('提示', '该加工单已作废！', 'warning');
		return;
	} else {
		var url = Utils.getRootPath()
				+ '/wareHouse/processGoods/commitCheck?processGoodsId='
				+ processGoodsId;
		$.ajax({
			type : "post",
			url : url,
			async : false,
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
// 单提交审核功能
function commitCheckNumber() {
	var processGoodsId = $('#processGoodsId').val();
	var data = $('#dg').datagrid('getData');
	if (data.rows.length == 0) {
		$.messager.alert('提示', '该加工单没有原材料！', 'warning');
		return;
	}
	var status = '$!{processGoods.status}';
	if (status == "1") {
		$.messager.alert('提示', '该加工单已经提交！', 'warning');
		return;
	} else if (status == "2") {
		$.messager.alert('提示', '该加工单审核已通过！', 'warning');
		return;
	} else if (status == "3") {
		$.messager.alert('提示', '该加工单正在处理中！', 'warning');
		return;
	} else if (status == "4") {
		$.messager.alert('提示', '该加工单已完成！', 'warning');
		return;
	} else if (status == "5") {
		$.messager.alert('提示', '该加工单已作废！', 'warning');
		return;
	} else {
		var url = Utils.getRootPath()
				+ '/wareHouse/processGoods/commitCheck?processGoodsId='
				+ processGoodsId;
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

function checkProcessGoods() {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要审核的加工单！', 'warning');
		return;
	}
	if (rows.length > 1) {
		$.messager.alert('提示', '只能选择一条加工单进行审核！', 'warning');
		return;
	}
	var row = rows[0];
	if (row.STATUE == "4") {
		$.messager.alert('提示', '该加工单已完成！', 'warning');
		return;
	}
	var url = Utils.getRootPath()
			+ '/wareHouse/processGoods/checkProcessGoods?id=' + row.ID;
	window.parent.initAdd(url, '审核加工单', 1000, 550);
}

// 审核通过
function checkPass() {
	var processGoodsId = $('#processGoodsId').val();
	var data = $('#dg').datagrid('getData');
	var ids = "";
	for (var i = 0; i < data.rows.length; i++) {
		ids += data.rows[i].ID + ",";
	}
	if (ids.length == 0) {
		$.messager.alert('提示', '没有原材料，确定审核通过？', 'warning');
		return;
	}
	var url = Utils.getRootPath() + '/wareHouse/processGoods/checkPass?id='
			+ processGoodsId;
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
// 审核不通过
function CheckItemAjax(id, reason, type) {
	var url = Utils.getRootPath() + '/wareHouse/processGoods/checkNoPass';
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
	var id = $('#processGoodsId').val();
	if (type == '1') {
		$.messager.prompt('提示信息', '请输入审核未通过的原因：', function(r) {
			if (r) {
				CheckItemAjax(id, r, type);
			} else {
				var reason = " ";
				CheckItemAjax(id, reason, type);
			}
		});
	}
}

function previewPrint() {
	var processGoodsId = $('#processGoodsId').val();
	$.ajax({
		type : "get",
		url : Utils.getRootPath()
				+ '/wareHouse/processGoods/gotoPrintProcessGoods?id='
				+ processGoodsId,
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
	LODOP.PRINT_INIT("加工单表格");
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

function openPrint() {
	$("#ddialog").dialog('open');
	$.ajax({
		type : "get",
		url : Utils.getRootPath()
				+ '/wareHouse/processGoods/gotoPrintWaitProcessGoods',
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
			$("#ddialog").dialog('close');
		}
	});
}
function printTable() {
	var LODOP = getLodop();
	LODOP.PRINT_INIT("待加工商品表格");
	var strStyle = "<style> table,td,th {border-width: 1px;border-style: solid;border-collapse: collapse}</style>";
	LODOP.ADD_PRINT_TABLE(60, "5%", "90%", 314, strStyle
			+ document.getElementById("div2").innerHTML);
	LODOP.SET_PRINT_STYLEA(0, "Vorient", 3);
	LODOP.ADD_PRINT_HTM(26, "5%", "90%", 140,
			document.getElementById("div1").innerHTML);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.SET_PRINT_STYLEA(0, "LinkedItem", 1);
	LODOP.PREVIEW();
};

function addProduct() {
	var id = $('#processGoodsId').val();
	var url = Utils.getRootPath() + '/wareHouse/processGoods/addProduct?id='
			+ id;
	$.ajax({
		type : "post",
		url : url,
		async : false,
		dataType : 'json',
		success : function(result) {
			if (result.result) {
				$('#datagrid').datagrid('reload');
				$('#datagrid').datagrid('beginEdit', 1);
			} else if (result.status == "error") {
				$('#datagrid').datagrid('reload');
			}
		}
	});
}

function productGoodsAdd() {
	var id = $('#processGoodsId').val();
	var data = $('#datagrid').datagrid('getData');
	var ids = "";
	var serialNumber = "";
	for (var i = 0; i < data.rows.length; i++) {
		$('#datagrid').datagrid('endEdit', i);
		ids += data.rows[i].ID + ",";
		serialNumber += data.rows[i].SERIALNUMBER + ",";
	}
	var url = Utils.getRootPath()
			+ '/wareHouse/processGoods/productGoodsAdd?ids=' + ids
			+ '&serialNumber=' + serialNumber + '&id=' + id;
	$.ajax({
		type : "post",
		url : url,
		async : false,
		dataType : 'json',
		success : function(result) {
			if (result.message == "保存成功") {
				$('#datagrid').datagrid('reload');
				addProduct();
			} else if (result.message == "查询失败") {
				$.messager.alert('提示', '条码不正确请重试', 'warning', function() {
					$('#datagrid').datagrid('reload');
					$('#datagrid').datagrid('beginEdit', 1);
				});
			} else if (result.message == "查询成功") {
				$('#datagrid').datagrid('reload');
				$('#datagrid').datagrid('beginEdit', 1);
			} else if (result.message == "查询question") {
				$('#datagrid').datagrid('reload');
				$('#datagrid').datagrid('beginEdit', 1);
			} else if (result.status == "error") {
				$.messager.alert('提示', '添加失败', 'error', function() {
					$('#datagrid').datagrid('reload');
				});
			}
		}
	});
}

function endAdd() {
	var id = $('#processGoodsId').val();
	var url = Utils.getRootPath() + '/wareHouse/processGoods/endAdd?id=' + id;
	$.ajax({
		type : "post",
		url : url,
		async : false,
		dataType : 'json',
		success : function(result) {
			if (result.result) {
				$('#datagrid').datagrid('reload');
			} else if (result.status == "error") {
				$('#datagrid').datagrid('reload');
			}
		}
	});
}

function openAddproduct() {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择一条加工单！', 'warning');
		return;
	}
	if (rows.length > 1) {
		$.messager.alert('提示', '只能选择一条加工单！', 'warning');
		return;
	}
	var row = rows[0];
	if (row.STATUE == "4") {
		$.messager.alert('提示', '该加工单已完成！', 'warning');
		return;
	}
	var url = Utils.getRootPath()
			+ '/wareHouse/processGoods/openAddproduct?id=' + row.ID;
	window.parent.initAdd(url, '添加成品', 1000, 550);
}

function updateProduct() {
	endAdd();
	var processGoodsId = $('#processGoodsId').val();
	var data = $('#datagrid').datagrid('getData');
	var ids = "";
	var productNums = "";
	for (var i = 0; i < data.rows.length; i++) {
		ids += data.rows[i].ID + ",";
		if (data.rows[i].GOODSNUM != null && data.rows[i].GOODSNUM != ""
				&& data.rows[i].GOODSNUM != "undefiend") {
			productNums += data.rows[i].GOODSNUM + ",";
		} else {
			productNums += "0,";
		}
	}
	if (data.rows.length == 0) {
		$.messager.alert('提示', '请添加成品！', 'warning');
		return;
	}
	var url = Utils.getRootPath()
			+ '/wareHouse/processGoods/updateProduct?ids=' + ids
			+ '&processGoodsId=' + processGoodsId + '&productNums='
			+ productNums;
	$.ajax({
		type : "post",
		url : url,
		async : true,
		dataType : 'json',
		success : function(result) {
			if (result.message == "保存成功") {
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

function removeProduct() {
	var processGoodsId = $('#processGoodsId').val();
	var rowsdata = $('#dg').datagrid('getSelections');
	if (rowsdata.length > 0) {
		$.messager.alert('提示', '原材料不可移除！', 'warning');
		return;
	}
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要移除的成品！', 'warning');
		return;
	}
	var ids = "";
	for (var i = 0; i < rows.length; i++) {
		ids += rows[i].ID + ",";
	}
	$.messager
			.confirm(
					'确认 ',
					'确定要移除该成品 ？',
					function(r) {
						if (r) {
							$
									.ajax({
										type : "post",
										url : Utils.getRootPath()
												+ '/wareHouse/processGoods/removeProduct?processGoodsId='
												+ processGoodsId,
										data : {
											ids : ids
										},
										async : false,
										dataType : 'json',
										success : function(result) {
											if (result.message == "删除成功") {
												$.messager.alert('提示', '移除成功',
														'info');
												$('#datagrid').datagrid(
														'reload');
											} else if (result.message == "删除失败") {
												$.messager.alert('提示',
														'移除失败，请联系管理员', 'error');
												$('#datagrid').datagrid(
														'reload');
											}
										}
									});
						}
						;
					});
}

function dataRecord() {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择一条加工单！', 'warning');
		return;
	}
	if (rows.length > 1) {
		$.messager.alert('提示', '只能选择一条加工单！', 'warning');
		return;
	}
	var url = Utils.getRootPath()
			+ '/wareHouse/processGoods/openChooseProcessGoods?processGoodsId='
			+ rows[0].ID;
	window.parent.initAdd(url, '分拣单列表', 1000, 550);
}

function chooseThisProcess() {
	var processGoodsId = $('#processGoodsId').val();
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择一条分拣单！', 'warning');
		return;
	}
	if (rows.length > 1) {
		$.messager.alert('提示', '只能选择一条分拣单！', 'warning');
		return;
	}
	var url = Utils.getRootPath()
			+ '/wareHouse/processGoods/chooseThisProcess?sorteId=' + rows[0].ID
			+ '&processGoodsId=' + processGoodsId;
	$.messager.confirm('确认 ', '确定要同步数据 ？', function(r) {
		if (r) {
			$("#ddialog").dialog('open');
			$.ajax({
				type : "post",
				url : url,
				async : true,
				dataType : 'json',
				success : function(result) {
					$("#ddialog").dialog('close');
					if (result.message == "保存成功") {
						$.messager.alert('提示', '同步成功', 'info', function() {
							window.parent.loadDataGrid('purchaseOrders');
							window.parent.closeWinForm();
						});
					} else {
						$.messager.alert('提示', '同步失败，请联系管理员', 'error',
								function() {
									window.parent
											.loadDataGrid('purchaseOrders');
									window.parent.closeWinForm();
								});
					}
				}
			});
		}
	});
}