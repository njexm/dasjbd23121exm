//查看基本信息
function openSorteGoods(id) {
	var url = Utils.getRootPath()
			+ '/supplierSettlement/supplierSettlementDo/gotoDetail?id=' + id;
	add(url, '供应商费用单详情', 1200, 560);
}

// 打开新增页面
function openAdd() {
	var url = Utils.getRootPath() + '/sorte/sorteDo/gotoAdd';
	window.parent.initAdd(url, "新增供应商结算单", 1100, 600);
}

// 查看基本信息
function openDetail(id) {
	var url = Utils.getRootPath() + '/sorte/sorteDo/gotoDetail?id=' + id;
	add(url, '分拣单详情', 1100, 600);
}
// 分拣
function sorting(title, x, y) {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	var row = rows[0];
	if (row.AUDITS_TATUS != '2') {
		$.messager.alert('提示', '该分拣单尚未通过审核，不可分拣！', 'warning');
		return;
	}
	var url = Utils.getRootPath() + '/sorte/sorteDo/gotoSorting?id=' + row.ID;
	add(url, title, x, y);
}
// 编辑
function edit(title, x, y) {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	var row = rows[0];
	if (row.AUDITS_TATUS == '2') {
		$.messager.alert('提示', '该分拣单审核已通过，不可编辑！', 'warning');
		return;
	} else if (row.AUDITS_TATUS == '1') {
		$.messager.alert('提示', '该分拣单已提交审核，不可编辑！', 'warning');
		return;
	}
	var url = Utils.getRootPath() + '/sorte/sorteDo/gotoEdit?id=' + row.ID;
	add(url, title, x, y);
}

// 打开分点选择
function openChoseBranch() {
	$.ajax({
		type : "get",
		url : Utils.getRootPath() + '/sorte/sorteDo/openChoseBranch',
		data : {},
		success : function(data) {
			$('#selectOptions').window({
				title : '分店列表',
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

function openGoodsDetail(areaId, status) {
	$.ajax({
		type : "get",
		url : Utils.getRootPath() + '/sorte/sorteDo/openGoodsDetail',
		data : {
			'status' : status,
			'areaId' : areaId
		},
		success : function(data) {
			$('#selectOptions').window({
				title : '订单商品列表',
				closable : true,
				maximizable : false,
				modal : false,
				draggable : false,
				width : 900,
				height : 450
			});
			$('#selectOptions').html(data);
		}
	});
}

function saveCheck() {
	var sorteId = $('#sorteId').val();
	var rows = $('#goodsDg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择至少一条数据！', 'warning');
		return;
	}
	var ids = "";
	for (var i = 0; i < rows.length; i++) {
		ids += rows[i].ID + ",";
	}
	var url = Utils.getRootPath() + '/sorte/sorteDo/saveCheck?ids=' + ids
			+ '&sorteId=' + sorteId;
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
			} else if (result.result == true) {
				$.messager.alert('提示', '您选择的分店中有商品库存不足，不能分拣!，请重新确认', 'info',
						function() {
							$('#dg').datagrid('reload');
						});
			} else if (result.result == false) {
				$.messager.alert('提示', '已存在某个勾选的分店，请重新确认', 'info', function() {
					$('#dg').datagrid('reload');
				});
			} else {
				$.messager.alert('提示', '添加失败，请联系管理员', 'error');
				$('#dg').datagrid('reload');
			}
		}
	});
}

// 新增数据
function save(formId) {
	// 数据有限性判断
	if (validateSubmit(formId)) {
		// 保存
		var id = $('#sorteId').val();
		var data = $('#dg').datagrid('getData');
		for (var i = 0; i < data.rows.length; i++) {
			$('#dg').datagrid('endEdit', i);
		}
		if (data.rows.length == 0 || data.rows == null) {
			$.messager.alert('提示', '亭点为空，请添加亭点！', 'warning');
			return;
		}
		// 提交前，所有行的编辑状态取消
		var ids = "";
		var remark = "";
		for (var i = 0; i < data.rows.length; i++) {
			$('#dg').datagrid('beginEdit', i);
			if (data.rows[i].REMARK == null || data.rows[i].REMARK == "") {
				remark += " ,";
			} else {
				remark += data.rows[i].REMARK + ",";
			}
			ids += data.rows[i].ID + ",";
		}
		remark = encodeURI(encodeURI(remark));
		var url = Utils.getRootPath() + '/sorte/sorteDo/save?ids=' + ids
				+ '&id=' + id + '&remark=' + remark;
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
		url : Utils.getRootPath() + '/sorte/sorteDo/deleteItem',
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

function openChoseAll() {
	var id = $('#sorteId').val();
	var rows = $('#dg').datagrid('getRows');
	// 提交前，所有行的编辑状态取消
	var ids = "";
	for (var i = 0; i < rows.length; i++) {
		ids += rows[i].ID + ",";
	}
	$.messager.confirm('确认 ', '确定全部分拣 ？', function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : Utils.getRootPath() + '/sorte/sorteDo/chooseAll',
				data : {
					id : id,
					ids : ids
				},
				async : false,
				dataType : 'json',
				success : function(result) {
					if (result.result) {
						$.messager.alert('提示', '添加成功！', 'info', function() {
							$('#dg').datagrid('reload');
						});
					} else if (result.result == true) {
						$.messager.alert('提示', '您选择的分店中有商品库存不足，不能分拣!，请重新确认',
								'info', function() {
									$('#dg').datagrid('reload');
								});
					} else if (result.status == "error") {
						$.messager.alert('提示', '系统错误，请联系管理员', 'error');
					} else if (result.message == "查询成功") {
						$.messager.alert('提示', '选中的亭点中有分店没有订单', 'error');
					}
				}
			});
		}
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
	if (rows[0].AUDITS_TATUS == 2) {
		$.messager.alert('提示', '该结算单已通过审核，不可删除！', 'warning');
		return;
	}
	if (rows[0].AUDITS_TATUS == 3) {
		$.messager.alert('提示', '该结算单待处理，不可删除！', 'warning');
		return;
	}
	if (rows[0].AUDITS_TATUS == 1) {
		$.messager.alert('提示', '该结算单已提交审核，不可删除！', 'warning');
		return;
	}
	$.messager.confirm('确认 ', '确定要删除 ？', function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : Utils.getRootPath() + '/sorte/sorteDo/delete',
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
						$.messager.alert('提示', '删除失败，请联系管理员', 'error');
						$("#dg").datagrid('load');
					}
				}
			});
		}
		;
	});
};

// 提交审核
function toCheck() {
	var id = $('#sorteId').val();
	var status = '$!{sorte.auditStatus}';
	if (status == 1) {
		$.messager.alert('提示', '该分拣单已提交审核，不可再次提交！', 'warning');
		return;
	} else if (status == 3) {
		$.messager.alert('提示', '该分拣单审核未通过，请处理后提交！', 'warning');
		return;
	} else if (status == 2) {
		$.messager.alert('提示', '该分拣单审核已通过，不可再次提交！', 'warning');
		return;
	}
	$.messager.confirm('确认 ', '确定提交审核？', function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : Utils.getRootPath() + '/sorte/sorteDo/toCheck',
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
	if (row.AUDITS_TATUS == 2) {
		$.messager.alert('提示', '该分拣单已通过审核，不可再次审核！', 'warning');
		return;
	} else if (row.AUDITS_TATUS == 3) {
		$.messager.alert('提示', '该分拣单审核未通过，不可再次审核！', 'warning');
		return;
	} else if (row.AUDITS_TATUS == 0) {
		$.messager.alert('提示', '该分拣单未提交审核！', 'warning');
		return;
	}
	var url = Utils.getRootPath() + '/sorte/sorteDo/gotoCheck?id=' + row.ID;
	add(url, title, x, y);
}

// 审核
function checkPass(type) {
	var sorteId = $('#sorteId').val();
	if (type == '2') {
		$.messager.confirm('确认 ', '确定审核通过？', function(r) {
			if (r) {
				CheckByAjax(sorteId, null, type);
			}
		});
	} else if (type == '3') {
		$.messager.prompt('提示信息', '请输入审核未通过的原因：', function(r) {
			if (r) {
				CheckByAjax(sorteId, r, type);
			}
			if (r.length == 0 || r == null || r == 'undefine') {
				r = " ";
				CheckByAjax(sorteId, r, type);
			}
		});
	}
}

function CheckByAjax(sorteId, reason, type) {
	var url = Utils.getRootPath() + '/sorte/sorteDo/checkPass';
	$.ajax({
		type : "post",
		url : url,
		async : false,
		data : {
			'type' : type,
			'reason' : reason,
			'sorteId' : sorteId
		},
		dataType : 'json',
		success : function(result) {
			if (result.message == "保存成功") {
				$.messager.alert('提示', '审核完成', 'info', function() {
					window.parent.loadDataGrid('advancePayment');
					window.parent.closeWinForm();
				});
			} else {
				$.messager.alert('提示', '审核失败，请联系管理员', 'error');
			}
		}
	});
}

// 开始分拣
function startSort(id) {
	var url = Utils.getRootPath() + '/sorte/sorteDo/startSort';
	$.ajax({
		type : "post",
		url : url,
		async : false,
		data : {
			'id' : id
		},
		dataType : 'json',
		success : function(result) {
			if (result.message == "保存成功") {
				$("#dg").datagrid('load');
			} else {
				$.messager.alert('提示', '审核失败，请联系管理员', 'error');
			}
		}
	});
}
// 完成分拣
function endSort(id) {
	var url = Utils.getRootPath() + '/sorte/sorteDo/endSort';
	$.ajax({
		type : "post",
		url : url,
		async : false,
		data : {
			'id' : id
		},
		dataType : 'json',
		success : function(result) {
			if (result.message == "保存成功") {
				$("#dg").datagrid('load');
			} else {
				$.messager.alert('提示', '审核失败，请联系管理员', 'error');
			}
		}
	});
}

function previewPrint() {
	$('#ddialog').window('open');
	$.ajax({
		type : "get",
		url : Utils.getRootPath() + '/sorte/sorteDo/gotoPrintSorteGoods',
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
			$('#ddialog').window('close');
		}
	});
}
function printTable() {
	var LODOP = getLodop();
	LODOP.PRINT_INIT("分拣单商品汇总表格");
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

function openPrint() {
	var sorteId = $('#sorteId').val();
	$.ajax({
		type : "get",
		url : Utils.getRootPath() + '/sorte/sorteDo/gotoSortePrint?id='
				+ sorteId,
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
function printMyTable() {
	var LODOP = getLodop();
	LODOP.PRINT_INIT("分拣单总表格");
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

// 初始化父级菜单下拉框
function initParentTree() {
	$('#goods_classify').combotree({
		url : Utils.getRootPath() + "/commodityClassify/getTreeData",
		onLoadSuccess : function(node, data) {
			var optionValue = $('#goods_classify').attr("optionValue");
			if (optionValue != undefined && optionValue != "") {
				$('#goods_classify').combotree('setValue', optionValue);
			}
		}
	});
}