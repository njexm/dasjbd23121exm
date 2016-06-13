//添加
function add(url, title, x, y) {
	window.parent.initAdd(url, title, x, y);
}

// 打开新增采购订单页面
function addChange(title, x, y) {
	var url = Utils.getRootPath() + '/storeChange/gotoAddChange';
	add(url, title, x, y);
}
// 编辑
function editChange(title, x, y) {
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
	if (row.STATUS == '1') {
		$.messager.alert('提示', '选择的记录已提交待审核,不能进行编辑！', 'warning');
		return;
	}
	var url = Utils.getRootPath() + '/storeChange/gotoEdit?id=' + row.ID;
	add(url, title, x, y);
}
function checkChange(title, x, y) {
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
	if (row.STATUS != '1') {
		$.messager.alert('提示', '选择的记录不能进行审核！', 'warning');
		return;
	}
	var url = Utils.getRootPath() + '/storeChange/check?id=' + row.ID;
	add(url, title, x, y);
}

function openChoseGoods() {
	var changeType = $('#changeType').val();
	var branchId = $('#branchId').combobox('getValue');
	if (changeType.length == 0) {
		$.messager.alert('提示', '请选择库存调整方式！', 'warning');
		return;
	}
	if (branchId.length == 0) {
		$.messager.alert('提示', '请选择调整仓库！', 'warning');
		return;
	}
	var data = $('#dg').datagrid('getData');
	var pyNum = $('#pyNum').val();
	var ids = "";
	var num = "";
	var remark = "";
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		if (data.rows[i].CHANGENUMBER == null
				|| data.rows[i].CHANGENUMBER == "") {
			num += "0,";
		} else {
			num += data.rows[i].CHANGENUMBER + ",";
		}
		if (data.rows[i].REMARK == null || data.rows[i].REMARK == "") {
			remark += " ,";
		} else {
			remark += data.rows[i].REMARK + ",";
		}
		ids += data.rows[i].ID + ",";
	}
	remark = encodeURI(encodeURI(remark));
	$.ajax({
		type : "get",
		url : Utils.getRootPath() + '/storeChange/openChoseGoods?pyNum='
				+ pyNum + '&remark=' + remark + '&changeType=' + changeType
				+ '&branchId=' + branchId,
		data : {
			ids : ids,
			num : num,
		},
		success : function(data) {
			$('#selectOptions').window({
				title : '选择商品',
				closable : true,
				maximizable : false,
				modal : false,
				draggable : false,
				width : 900,
				height : 500
			});
			$('#selectOptions').html(data);
			$('#showWinBatch').window('close');
			$('#showexportBatch').window('close');
			$('#dg').datagrid('resize', {
				width : '100%'
			});
		}
	});
	
}

function closeWindow() {
	$('#selectOptions').window('close');
	$('#dg').datagrid('reload');
}

function addGoods() {
	var rows = $('#goodsDg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择至少一条数据！', 'warning');
		return;
	}			
	var stores = "";
	for (var i = 0; i < rows.length; i++) {		
		if (rows[i].STORE == null
				|| rows[i].STORE == "") {
			stores += "0,";
		} else {
			stores += rows[i].STORE + ",";
		}		
	}
	var ids = getRowsIdId();
	var changeId = $('#storeChangeId').val();
	var url = Utils.getRootPath() + '/storeChange/addGoodsToItems?ids=' + ids+'&store='+stores;
	$.ajax({
		type : "post",
		url : url,
		data : {
			'changeId' : changeId
		},
		async : true,
		dataType : 'json',
		success : function(result) {
			$('#selectOptions').window('close');
			if (result.message == "保存成功") {
				$.messager.alert('提示', '添加成功', 'info', function() {
					$('#dg').datagrid('reload');
				});
			} else if (result.message == "保存error") {
				$.messager.alert('提示', '已存在该勾选的商品，请重新确认', 'info', function() {
					$('#dg').datagrid('reload');
				});
			} else {
				$.messager.alert('提示', '添加失败，请联系管理员', 'error');
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
	add(url, '填写/修改', 350, 280);
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
	var checkNumberStatus = row.CHECKSTATUS;
	if (checkNumberStatus == '2') {
		$.messager.alert('提示', '选择的记录已审核,不能进行编辑！', 'warning');
		return;
	}
	var url = Utils.getRootPath() + '/warehouse/wareHouse/editCheckItem?id='
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
	if (row.STATUS == '2') {
		$.messager.alert('提示', '选择的记录已审核,不能生存盘点差异单！', 'warning');
		return;
	}
	var url = Utils.getRootPath() + '/warehouse/wareHouse/insertDifference?id='
			+ row.ID;
	$.ajax({
		type : "post",
		url : url,
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

// 新增数据
function saveBaseInfo(formId) {
	// 数据有限性判断
	if (validateSubmit(formId)) {
		var cangku = $('#branchId').combobox('getValue');
		var jingshouren = $('#operatorUserId').combobox('getValue');
		if (cangku == '') {
			$.messager.alert('提示', '仓库未选择或填写无效', 'warning');
			return;
		} else if (jingshouren == '') {
			$.messager.alert('提示', '经手人未选择或填写无效', 'warning');
			return;
		} else {
			var data = $('#dg').datagrid('getData');
			for (var i = 0; i < data.rows.length; i++) {
				$('#dg').datagrid('endEdit', i);
			}
			var remarks = "";
			var changeNums = "";
			var ids = "";
			for (var i = 0; i < data.rows.length; i++) {
				if (data.rows[i].REMARK == null || data.rows[i].REMARK == "") {
					remarks += " ,";
				} else {
					remarks += data.rows[i].REMARK + ",";
				}
				ids += data.rows[i].ID + ",";
				changeNums += data.rows[i].CHANGENUMBER + ",";
			}
			remarks = encodeURI(encodeURI(remarks));
			var row = $('#dg').datagrid('getRows');
			if (row.length == 0 || row == null || row.length == null) {
				$.messager.alert('提示', '调整单没有商品，请选择商品新增', 'warning');
				return;
			}
			// 保存
			var url = Utils.getRootPath() + '/storeChange/save?ids=' + ids
					+ '&remarks=' + remarks + '&changeNums=' + changeNums;
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
		var cangku = $('#branchId').combobox('getValue');
		var jingshouren = $('#operatorUserId').combobox('getValue');
		if (cangku == '') {
			$.messager.alert('提示', '仓库未选择或填写无效', 'warning');
			return;
		} else if (jingshouren == '') {
			$.messager.alert('提示', '经手人未选择或填写无效', 'warning');
			return;
		} else {
			var data = $('#dg').datagrid('getData');
			if (data.rows.length == 0) {
				$.messager.alert('提示', '请添加至少一条商品', 'warning');
				return;
			}
			for (var i = 0; i < data.rows.length; i++) {
				$('#dg').datagrid('endEdit', i);
			}
			var changeId = $('#storeChangeId').val();
			var remarks = "";
			var ids = "";
			var changeNums = "";
			for (var i = 0; i < data.rows.length; i++) {
				if (data.rows[i].REMARK == null || data.rows[i].REMARK == "") {
					remarks += " ,";
				} else {
					remarks += data.rows[i].REMARK + ",";
				}
				ids += data.rows[i].ID + ",";
				changeNums += data.rows[i].CHANGENUMBER + ",";
			}
			remarks = encodeURI(encodeURI(remarks));
			// 保存
			var url = Utils.getRootPath() + '/storeChange/update?ids=' + ids
					+ '&changeId=' + changeId + '&remarks=' + remarks
					+ '&changeNums=' + changeNums;
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

function CheckChangeByAjax(rows, changeId, reason, type) {
	// 保存
	var url = Utils.getRootPath() + '/storeChange/checkChange';
	$.ajax({
		type : "post",
		url : url,
		data : {
			'rows' : rows,
			'changeId' : changeId,
			'reason' : reason,
			'type' : type
		},
		async : false,
		dataType : 'json',
		success : function(result) {
			if (result.message == "保存成功") {
				$.messager.alert('提示', '审核完成', 'info', function() {
					window.parent.loadDataGrid('zcStoreChange');
					window.parent.closeWinForm();
				});
			} else if (result.message == "保存error") {
				$.messager.alert('提示', '审核失败，出库数量大于库存数量', 'info', function() {
				});
			} else {
				$.messager.alert('提示', '审核失败，请联系管理员', 'error');
			}
		}
	});
}
function changeCheck(type) {
	var data = $('#dg').datagrid('getData');
	var changeId = $('#storeChangeId').val();
	var rows = JSON.stringify(data.rows);
	if (type == '2') {
		$.messager.confirm('确认 ', '确定审核通过？', function(r) {
			if (r) {
				CheckChangeByAjax(rows, changeId, null, type);
			}
		});
	} else if (type == '3') {

		$.messager.prompt('提示信息', '请输入审核未通过的原因：', function(r) {
			if (r) {
				CheckChangeByAjax(rows, changeId, r, type);
			}
			if (r.length == 0 || r == null || r == 'undefine') {
				r = " ";
				CheckChangeByAjax(rows, changeId, r, type);
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
	for (var i = 0; i < rows.length; i++) {
		ids.push(rows[i].ID);
	}
	var warehouseId = $('#zcWarehouseId').val();
	var url = Utils.getRootPath() + '/warehouse/wareHouse/addCheckItem?ids='
			+ ids + '&pandianhao=' + warehouseId;
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

function opendetail(id) {
	var url = Utils.getRootPath() + '/storeChange/detail?id=' + id;
	add(url, '详情', 1000, 800);
}

function previewPrint() {
	var storeChangeId = $('#storeChangeId').val();
	$.ajax({
		type : "get",
		url : Utils.getRootPath() + '/storeChange/gotoPrint?id='
				+ storeChangeId,
		data : {},
		success : function(data) {
			$('#selectOptions1').window({
				title : '打印预览',
				closable : true,
				maximizable : false,
				modal : false,
				draggable : false,
				width : 800,
				height : 450
			});
			$('#selectOptions1').html(data);
		}
	});
}
function PreviewMytable() {
	var LODOP = getLodop();
	LODOP.PRINT_INIT("库存调整单");
	var strStyle = "<style> table,td,th {border-width: 1px;border-style: solid;border-collapse: collapse}</style>";
	LODOP.ADD_PRINT_TABLE(155, "5%", "90%", 314, strStyle
			+ document.getElementById("div2").innerHTML);
	LODOP.SET_PRINT_STYLEA(0, "Vorient", 3);
	LODOP.ADD_PRINT_HTM(26, "5%", "90%", 140,
			document.getElementById("div1").innerHTML);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.SET_PRINT_STYLEA(0, "LinkedItem", 1);
	LODOP.PREVIEW();
};

function removeChose() {
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
	$.messager.confirm('确认 ', '确定要移除 ？', function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : Utils.getRootPath() + '/storeChange/removeChose',
				data : {
					id : ids
				},
				async : false,
				dataType : 'json',
				success : function(result) {
					if (result.result) {
						$("#dg").datagrid('load');
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

function deleteOrder() {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要删除的调整单！', 'warning');
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
		$.messager.alert('提示', '该调整单已通过审核！', 'warning');
		return;
	} else if (row.STATUS == "3") {
		$.messager.alert('提示', '该调整单审核不通过！', 'warning');
		return;
	} else if (row.STATUS == "1") {
		$.messager.alert('提示', '该调整单已提交！', 'warning');
		return;
	} else if (row.STATUS == "4") {
		$.messager.alert('提示', '该调整单已完成！', 'warning');
		return;
	} else {
		$.messager.confirm('确认 ', '确定要删除 ？', function(r) {
			if (r) {
				$.ajax({
					type : "post",
					url : Utils.getRootPath() + '/storeChange/deleteOrder',
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


//导入
function importIntoAdd() {
	var storeChangeNumber = $('#storeChange_Number').val();
	var branchId = $('#branchId').val();
	var ids = "";
	var data = $('#dg').datagrid('getData');
	for (var i = 0; i < data.rows.length; i++) {
		ids += data.rows[i].ID + ",";
	}
	
	var url = Utils.getRootPath() + '/importExcel/importIntoStoreChange?ids=' + ids+'&storeChangeNumber='+storeChangeNumber+'&branchId='+branchId;
	$('#importBtn').text('导入中');
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

//导出
function exportExcelAdd() {
	debugger;
	var storeChangeNumber = $('#storeChange_Number').val();
	var branchId = $('#branchId').combobox('getValue');
	var data = $('#dg').datagrid('getData');
	var idStr = "";
	var changenumbers = "";
	var remarkTotal = "";
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		$('#dg').datagrid('beginEdit', i);
		idStr += data.rows[i].ID + ",";
		changenumbers += data.rows[i].CHANGENUMBER +",";
		if (data.rows[i].REMARK==null||data.rows[i].REMARK==''||data.rows[i].REMARK=='undefiend') {
			remarkTotal +=" "+"|";
		}else{
		remarkTotal +=data.rows[i].REMARK+"|";
		}
	}
	var url = Utils.getRootPath() + '/exportExcel/outStoreChangeExcel?idStr='
			+ idStr + '&storeChangeNumber=' + storeChangeNumber+ '&branchId=' + branchId
			+ '&changenumbers=' + changenumbers
			+ '&remarkTotal=' + remarkTotal;
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

//审核导出
function exportExcelCheck(changeType) {
	debugger;
	var storeChangeNumber = $("#storeChangeNumber").val();
	var branchId = $("#branchCheckId").val();
	var data = $('#dg').datagrid('getData');
	var idStr = "";
	var changenumbers = "";
	var remarkTotal = "";
	var changemoneyTotal = "";
	var systemStore = "";
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		$('#dg').datagrid('beginEdit', i);
		idStr += data.rows[i].ID + ",";
		changenumbers += data.rows[i].CHANGENUMBER +",";
		remarkTotal +=data.rows[i].REMARK+"/";
		changemoneyTotal+=data.rows[i].CHANGEMONEY +",";
		systemStore +=data.rows[i].STORE +",";
	}
	var url = Utils.getRootPath() + '/exportExcel/outStoreChangeCheckExcel?idStr='
			+ idStr + '&storeChangeNumber=' + storeChangeNumber+ '&branchId=' + branchId
			+ '&changenumbers=' + changenumbers
			+ '&remarkTotal=' + remarkTotal
			+ '&changemoneyTotal=' + changemoneyTotal
			+ '&systemStore=' + systemStore
			+ '&changeType=' + changeType;
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


//编辑导出
function exportExcelEdit(changeType) {
	debugger;
	var storeChangeNumber = $('#storeChange_Number').val();
	var branchId = $('#branchId').combobox('getValue');
	var data = $('#dg').datagrid('getData');
	var idStr = "";
	var changenumbers = "";
	var remarkTotal = "";
	var storeSystem = "";
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		$('#dg').datagrid('beginEdit', i);
		idStr += data.rows[i].ID + ",";
		changenumbers += data.rows[i].CHANGENUMBER +",";
		remarkTotal +=data.rows[i].REMARK+"|";
		storeSystem += data.rows[i].STORE +",";
	}
	var url = Utils.getRootPath() + '/exportExcel/outStoreChangeEditExcel?idStr='
			+ idStr + '&storeChangeNumber=' + storeChangeNumber+ '&branchId=' + branchId
			+ '&changenumbers=' + changenumbers
			+ '&remarkTotal=' + remarkTotal
			+ '&storeSystem=' + storeSystem
			+ '&changeType=' + changeType;
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

//编辑导入
function importIntoEdit() {
	var storeChangeNumber = $('#storeChange_Number').val();
	var branchId = $('#branchId').val();
	var ids = "";
	var data = $('#dg').datagrid('getData');
	for (var i = 0; i < data.rows.length; i++) {
		ids += data.rows[i].ID + ",";
	}
	
	var url = Utils.getRootPath() + '/importExcel/importIntoStoreChangeEdit?ids=' + ids+'&storeChangeNumber='+storeChangeNumber+'&branchId='+branchId
					;
	$('#importBtn').text('导入中');
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