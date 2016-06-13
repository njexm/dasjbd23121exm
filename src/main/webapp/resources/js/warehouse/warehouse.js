//添加
function add(url, title, x, y) {
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
		$.messager.alert('提示', '请选择一条数据进行操作！', 'warning');
		return;
	}
	var row = rows[0];
	if (row.STATUS == '2') {
		$.messager.alert('提示', '选择的记录已审核,不能进行编辑！', 'warning');
		return;
	}
	var url = Utils.getRootPath() + '/warehouse/wareHouse/gotoEdit?id='
			+ row.ID;
	add(url, title, x, y);
}

function editTable(title, x, y) {
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
	var checkNumberStatus = row.STATUS;
	if (checkNumberStatus == '2') {
		$.messager.alert('提示', '选择的记录已审核,不能进行编辑！', 'warning');
		return;
	}
	if (checkNumberStatus == '1') {
		$.messager.alert('提示', '选择的记录已提交待审核,不能进行编辑！', 'warning');
		return;
	}
	var url = Utils.getRootPath() + '/warehouse/wareHouse/editCheckItem?id='
			+ row.ID;
	add(url, title, x, y);
}
function checkTable(title, x, y) {
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
	var checkNumberStatus = row.STATUS;
	if (checkNumberStatus != '1') {
		$.messager.alert('提示', '选择的记录不能进行审核，请先提交为待审核', 'warning');
		return;
	}
	var url = Utils.getRootPath() + '/warehouse/wareHouse/checkCheckItem?id='
			+ row.ID;
	add(url, title, x, y);
}

function addInventory(title, x, y) {
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
	if (row.STATUS == '2') {
		$.messager.alert('提示', '选择的记录已审核,不能生存盘点单！', 'warning');
		return;
	}
	var url = Utils.getRootPath() + '/warehouse/wareHouse/addInventory?id='
			+ row.ID;
	add(url, title, x, y);
}

// 打开新增页面
function addNewNumber() {
	var url = Utils.getRootPath() + '/warehouse/wareHouse/addNewNumber';
	add(url, '新增盘点号', 800, 600);
}

function insertDifference() {
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
	if (row.STATUS == '0') {
		$.messager.alert('提示', '该盘点单还未提交！', 'warning');
		return;
	} else if (row.STATUS == '1') {
		$.messager.alert('提示', '该盘点单还未审核！', 'warning');
		return;
	} else if (row.STATUS == '3') {
		$.messager.alert('提示', '该盘点单正在处理中！', 'warning');
		return;
	}
	// if (row.DEFLAG == '' || row.DEFLAG == null || row.DEFLAG == undefined) {
	// $.messager.alert('提示', '该盘点号没有盘点,不能生存盘点差异单！', 'warning');
	// return;
	// }
	var url = Utils.getRootPath() + '/warehouse/wareHouse/insertDifference?id='
			+ row.ID;
	$.ajax({
		type : "post",
		url : url,
		async : false,
		dataType : 'json',
		success : function(result) {
			if (result.message == "保存成功") {
				$.messager.alert('提示', '保存成功', 'info', function() {
					window.parent.loadDataGrid('zcCheckNumber');
					window.parent.closeWinForm();
				});
			} else if (result.message == "查询成功") {
				$.messager.alert('提示', '该盘点单存在差异单，无需重复生成！', 'warning');
			} else if (result.message == "保存失败") {
				$.messager.alert('提示', '保存失败，请联系管理员', 'error');
			}
		}
	});
}

// 新增数据
function saveBaseInfo(formId) {
	// 数据有限性判断
	if (validateSubmit(formId)) {
		var cangku = $('#branchId').combobox('getValue');
		if (cangku == '') {
			$.messager.alert('提示', '盘点仓库未选择或填写无效', 'warning');
			return;
		} else {
			// 保存
			var url = Utils.getRootPath() + '/warehouse/wareHouse/saveNumber';
			$.ajax({
				type : "post",
				url : url,
				data : $('#' + formId).serialize(),
				async : false,
				dataType : 'json',
				success : function(result) {
					if (result.result) {
						$.messager.alert('提示', '保存成功', 'info', function() {
							window.parent.loadDataGrid('zcCheckNumber');
							window.parent.closeWinForm();
						});
					} else if (result.status == "error") {
						$.messager.alert('提示', '保存失败，请联系管理员', 'error');
					}
				}
			});
		}
	}
}

// 修改数据
function editBaseInfo(formId) {
	// 数据有限性判断
	if (validateSubmit(formId)) {
		// 保存
		var url = Utils.getRootPath() + '/warehouse/wareHouse/update';
		$.ajax({
			type : "post",
			url : url,
			data : $('#' + formId).serialize(),
			async : false,
			dataType : 'json',
			success : function(result) {
				if (result.status == "OK") {
					$.messager.alert('提示', '保存成功', 'info', function() {
						// loadDataGrid();
						window.parent.loadDataGrid('zcCheckNumber');
						window.parent.closeWinForm();
					});
				} else if (result.status == "error") {
					$.messager.alert('提示', '保存失败，请联系管理员', 'error');
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
}
// 添加商品到盘点单
function addCheckItem() {
	var rows = $('#selectDG').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	var ids = [];
	var nums = "";
	for (var i = 0; i < rows.length; i++) {
		ids.push(rows[i].ID);
		nums += rows[i].HOUSESTORE + ",";
	}
	var warehouseId = $('#zcWarehouseId').val();
	var url = Utils.getRootPath() + '/warehouse/wareHouse/addCheckItem?ids='
			+ ids + '&pandianhao=' + warehouseId + '&nums=' + nums;
	$.ajax({
		type : "post",
		url : url,
		async : true,
		dataType : 'json',
		success : function(result) {
			$('#selectOptions').window('close');

			if (result.message == "保存成功") {
				$.messager.alert('提示', '添加成功', 'info', function() {
					$("#dg").datagrid('reload');
				});
			} else if (result.message == "保存error") {
				$.messager.alert('提示', '添加失败，存在已盘点商品', 'info', function() {
				});
			} else {
				$.messager.alert('提示', '保存失败，请联系管理员', 'error');
			}
		}
	});
}

// 根据订单状态生成采购订单
function editCheckItem() {
	var data = $('#dg').datagrid('getData');
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
	}
	;
	var ids = selectAlldg();
	if (ids.length == 0) {
		$.messager.alert('提示', '该盘点单没有商品不能提交！', 'warning');
		return;
	}
	var warehouseId = $('#zcWarehouseId').val();
	var num = AllNums();
	var url = Utils.getRootPath() + '/warehouse/wareHouse/editCheckItem?ids='
			+ ids + '&num=' + num + '&pandianhao=' + warehouseId;
	$.ajax({
		type : "post",
		url : url,
		async : true,
		dataType : 'json',
		success : function(result) {
			if (result.message == "保存成功") {
				$.messager.alert('提示', '保存成功', 'info', function() {
					window.parent.loadDataGrid('zcCheckNumber');
					window.parent.closeWinForm();
				});
			} else {
				$.messager.alert('提示', '保存失败，请联系管理员', 'error');
			}
		}
	});
}

function CheckItemAjax(id, reason, type) {
	var url = Utils.getRootPath() + '/warehouse/wareHouse/CheckWareHouse';
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
					window.parent.loadDataGrid('zcCheckNumber');
					window.parent.closeWinForm();
				});
			} else {
				$.messager.alert('提示', '审核失败，请联系管理员', 'error');
			}
		}
	});
}
// 审核盘点单
function checkPandiandan(type) {
	var id = $('#zcWarehouseId').val();
	if (type == '2') {
		$.messager.confirm('确认 ', '确定审核通过？', function(r) {
			if (r) {
				CheckItemAjax(id, null, type);
			}
		});
	} else if (type == '3') {
		$.messager.prompt('提示信息', '请输入审核未通过的原因：', function(r) {
			if (r) {
				CheckItemAjax(id, r, type);
			}
			if (r.length == 0 || r == null || r == 'undefine') {
				r = " ";
				CheckItemAjax(id, r, type);
			}
		});
	}
}
// 根据订单状态生成采购订单
function createCheckItem() {
	var type = $('#type').val();
	var pdd = $('#pandianhao').val();
	var pdhId = $('#checkNumberId').val();
	var data = $('#dg').datagrid('getData');
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
	}
	var ids = "";
	var num = "";
	for (var i = 0; i < data.rows.length; i++) {
		if (data.rows[i].actualCheckNumber != ""
				&& data.rows[i].actualCheckNumber != null
				&& data.rows[i].actualCheckNumber != 'undefiend') {
			ids += data.rows[i].ID + ",";
			num += data.rows[i].actualCheckNumber + ",";
		}
	}
	var idStr = "";
	for (var i = 0; i < data.rows.length; i++) {
		if (data.rows[i].actualCheckNumber == ""
				|| data.rows[i].actualCheckNumber == null
				|| data.rows[i].actualCheckNumber == 'undefiend') {
			idStr += data.rows[i].ID + ",";
		}
	}
	var url = Utils.getRootPath() + '/warehouse/wareHouse/saveCheckItem?ids='
			+ ids + '&num=' + num + '&pandianhao=' + pdd + '&checkNumberId='
			+ pdhId + '&type=' + type + '&idStr=' + idStr;
	$.ajax({
		type : "post",
		url : url,
		async : false,
		dataType : 'json',
		success : function(result) {
			if (result.message == "保存成功") {
				$.messager.alert('提示', '保存成功', 'info', function() {
					window.parent.loadDataGrid('zcCheckNumber');
					window.parent.closeWinForm();
				});
			} else {
				$.messager.alert('提示', '保存失败，请联系管理员', 'error');
			}
		}
	});
}

// 根据订单状态生成采购订单
function createNullCheckTable() {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要生成的盘点号！', 'warning');
		return;
	}
	if (rows.length > 1) {
		$.messager.alert('提示', '只能选择一条盘点号！', 'warning');
		return;
	}
	var row = rows[0];
	var pdhId = row.ID;
	if (row.STATUS == "1") {
		$.messager.alert('提示', '该盘点号已提交！', 'warning');
		return;
	} else if (row.STATUS == "2") {
		$.messager.alert('提示', '该盘点号已通过审核！', 'warning');
		return;
	} else if (row.STATUS == "3") {
		$.messager.alert('提示', '该盘点号正在处理中！', 'warning');
		return;
	}
	var url = Utils.getRootPath()
			+ '/warehouse/wareHouse/createNullCheckTable?checkNumberId='
			+ pdhId;
	$.ajax({
		type : "post",
		url : url,
		async : false,
		dataType : 'json',
		success : function(result) {
			if (result.message == "保存成功") {
				$.messager.alert('提示', '保存成功', 'info', function() {
					window.parent.loadDataGrid('zcCheckNumber');
					window.parent.closeWinForm();
				});
			} else {
				$.messager.alert('提示', '保存失败，请联系管理员', 'error');
			}
		}
	});
}

var getRowsId = function() {
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

// 获取选中的行
var selectAlldg = function() {
	var ids = [];
	var rows = $('#dg').datagrid('getRows');
	for (var i = 0; i < rows.length; i++) {
		ids.push(rows[i].ID);
	}
	return ids;
};

var AllNums = function() {
	var num = [];
	var rows = $('#dg').datagrid('getRows');
	for (var i = 0; i < rows.length; i++) {
		if (rows[i].ACTUALCHECKNUMBER == ""
				|| rows[i].ACTUALCHECKNUMBER == null
				|| rows[i].ACTUALCHECKNUMBER.length == 0) {
			rows[i].ACTUALCHECKNUMBER = "0.00";
		}
		num.push(rows[i].ACTUALCHECKNUMBER);
	}
	return num;
};

var rowNums = function() {
	var num = [];
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	for (var i = 0; i < rows.length; i++) {
		if (rows[i].actualCheckNumber == ""
				|| rows[i].actualCheckNumber == null
				|| rows[i].actualCheckNumber.length == 0) {
			rows[i].actualCheckNumber = "0.00";
		}
		num.push(rows[i].actualCheckNumber);
	}
	return num;
};

// 打开 申请盘点号中的商品品牌列表
function openClassify() {
	var type = $("#checkType").val();
	$.ajax({
		type : "get",
		url : Utils.getRootPath() + '/warehouse/wareHouse/initClassify?type='
				+ type,
		data : {},
		success : function(data) {
			$('#selectOptions').window({
				title : '选择商品品牌分类',
				closable : true,
				maximizable : false,
				modal : false,
				draggable : false,
				width : 700,
				height : 500
			});
			$('#selectOptions').html(data);
		}
	});
}

// 打开 申请盘点号中的商品列表
function openGoodsItem() {
	var id = $("#zcWarehouseId").val();
	var data = $('#dg').datagrid('getData');
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
	}
	;
	var ids = selectAlldg();
	var num = AllNums();
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('beginEdit', i);
	}
	$.ajax({
		type : "post",
		url : Utils.getRootPath() + '/warehouse/wareHouse/initGoodsItem?id='
				+ id + '&ids=' + ids + '&num=' + num,
		data : {},
		success : function(data) {
			$('#selectOptions').window({
				title : '选择商品',
				collapsible : true,
				maximizable : false,
				modal : true,
				draggable : true,
				width : 880,
				height : 480,
			});
			$('#selectOptions').html(data);
			$('#showWinBatch').window('close');
			$('#showexportBatch').window('close');
		}
	});
}

function opendetail(id) {
	var url = Utils.getRootPath() + '/warehouse/wareHouse/detail?id=' + id;
	add(url, '详情', 1000, 800);
}

function previewPrint() {
	var zcWarehouseId = $('#zcWarehouseId').val();
	$
			.ajax({
				type : "get",
				url : Utils.getRootPath()
						+ '/warehouse/wareHouse/gotoPrintCheckItem?id='
						+ zcWarehouseId,
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
	LODOP.PRINT_INIT("盘点单表格");
	var strStyle = "<style> table,td,th {border-width: 1px;border-style: solid;border-collapse: collapse}</style>";
	LODOP.ADD_PRINT_TABLE(150, "5%", "90%", 314, strStyle
			+ document.getElementById("div2").innerHTML);
	LODOP.SET_PRINT_STYLEA(0, "Vorient", 3);
	LODOP.ADD_PRINT_HTM(26, "5%", "90%", 140,
			document.getElementById("div1").innerHTML);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.SET_PRINT_STYLEA(0, "LinkedItem", 1);
	LODOP.PREVIEW();
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
	if (row.STATUS == "2") {
		$.messager.alert('提示', '该盘点号已通过审核，不可删除！', 'warning');
		return;
	} else if (row.STATUS == "1") {
		$.messager.alert('提示', '该盘点号已提交审核，不可删除！', 'warning');
		return;
	} else if (row.STATUS == "3") {
		$.messager.alert('提示', '该盘点号待处理，不可删除！', 'warning');
		return;
	} else {
		$.messager.confirm('确认 ', '确定要删除 ？', function(r) {
			if (r) {
				$.ajax({
					type : "post",
					url : Utils.getRootPath() + '/warehouse/wareHouse/delete',
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
						} else {
							$.messager.alert('提示', '该盘点号存在关联运用，不可删除！',
									'warning');
						}
					}
				});
			}
			;
		});
	}
};

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
	$.messager.confirm('确认 ', '确定要删除 ？', function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : Utils.getRootPath() + '/warehouse/wareHouse/removeGoods',
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
						$.messager.alert('提示', '删除失败，请联系管理员', 'error');
						$("#dg").datagrid('load');
					}
				}
			});
		}
		;
	});
}

// 删除
function reduse() {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要删除的数据！', 'warning');
		return;
	}
	var row = rows[0];
	var id = row.ID;
	if (row.STATUS == "2") {
		$.messager.alert('提示', '该盘点单已通过审核，不可删除！', 'warning');
		return;
	} else if (row.STATUS == "1") {
		$.messager.alert('提示', '该盘点单已提交审核，不可删除！', 'warning');
		return;
	} else if (row.STATUS == "3") {
		$.messager.alert('提示', '该盘点号待处理，不可删除！', 'warning');
		return;
	} else {
		$.messager.confirm('确认 ', '确定要删除 ？', function(r) {
			if (r) {
				$.ajax({
					type : "post",
					url : Utils.getRootPath()
							+ '/warehouse/wareHouse/deleteTable',
					data : {
						id : id
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
};

// 批量导入
function batchImport(checkNumber) {
	var url = Utils.getRootPath()
			+ '/importExcel/saveCheckItemImport?wareHouseId=' + checkNumber;
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
					window.parent.loadDataGrid('goodfile');
					$('#showWinBatch').window('close');
					$("#dg").datagrid('load');
				});
			} else {
				var info = result.resultAnwser;
				var wrongRow = new Array();
				wrongRow = info.split("@");
				var wrongMsg = "批量导入失败:" + "<br/>";
				// if(null!=wrongRow && wrongRow.length >0)
				// {
				// for(var i=0;i<wrongRow.length;i++){
				// wrongMsg +=wrongRow[i]+"<br/>";
				// }
				$.messager.alert('提示', wrongMsg);

				// }

			}
		}
	});
}
// 批量导出
function exportExcel(pandianhao, storeHouse, id) {
	var url = Utils.getRootPath() + '/exportExcel/CheckItemExport?id=' + id
			+ '&pandianhao=' + pandianhao + '&storeHouse=' + storeHouse;
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
				// if(null!=wrongRow && wrongRow.length >0)
				// {
				// for(var i=0;i<wrongRow.length;i++){
				// wrongMsg +=wrongRow[i]+"<br/>";
				// }
				$.messager.alert('提示', wrongMsg);

				// }

			}
		}
	});
}
