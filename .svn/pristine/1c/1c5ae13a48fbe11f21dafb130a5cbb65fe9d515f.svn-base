//添加
function add(url, title, x, y) {
	window.parent.initAdd(url, title, x, y);
}

// 打开新增采购订单页面
function addChange(title, x, y) {
	var url = Utils.getRootPath() + '/switchChange/gotoAddChange';
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
	var url = Utils.getRootPath() + '/switchChange/gotoEdit?id=' + row.ID;
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
		$.messager.alert('提示', '选择的记录状态不是待审核,不能进行审核！', 'warning');
		return;
	}
	var url = Utils.getRootPath() + '/switchChange/check?id=' + row.ID;
	add(url, title, x, y);
}

function openChoseGoods() {
	var fromBranchId = $('#fromBranchId').combobox('getValue');
	if (fromBranchId.length == 0) {
		$.messager.alert('提示', '请选择转出仓库！', 'warning');
		return;
	}
	var data = $('#dg').datagrid('getData');
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
	}
	var remarks = "";
	for (var i = 0; i < data.rows.length; i++) {
		if (data.rows[i].REMARK == null || data.rows[i].REMARK == "") {
			remarks += " ,";
		} else {
			remarks += data.rows[i].REMARK + ",";
		}
	}
	remarks = encodeURI(encodeURI(remarks));
	var rows = JSON.stringify(data.rows);
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('beginEdit', i);
	}
	// 保存
	$.ajax({
		type : "get",
		url : Utils.getRootPath() + '/switchChange/openChoseGoods?remarks='
				+ remarks + '&fromBranchId=' + fromBranchId,
		data : {
			rows : rows
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
	var ids = getRowsIdId();
	var changgeNums = "";
	for (var i = 0; i < rows.length; i++) {
		changgeNums += rows[i].STORE + ",";
	}
	var changeId = $('#switchChangeId').val();
	var url = Utils.getRootPath() + '/switchChange/addGoodsToItems?ids=' + ids;
	$.ajax({
		type : "post",
		url : url,
		data : {
			'changeId' : changeId,
			'changgeNums' : changgeNums,
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
		ids.push(rows[i].GOODSFILE_ID);
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
					window.parent.loadDataGrid('zcSwitchChange');
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
		var fromcangku = $('#fromBranchId').combobox('getValue');
		var tocangku = $('#toBranchId').combobox('getValue');
		var jingshouren = $('#operatorUserId').combobox('getValue');
		if (fromcangku == '') {
			$.messager.alert('提示', '转出仓库未选择或填写无效', 'warning');
			return;
		} else if (tocangku == '') {
			$.messager.alert('提示', '转入仓库未选择或填写无效', 'warning');
			return;
		} else if (tocangku == fromcangku) {
			$.messager.alert('提示', '转入仓库与转出仓库不能一致', 'warning');
			return;
		} else if (jingshouren == '') {
			$.messager.alert('提示', '业务员未选择或填写无效', 'warning');
			return;
		} else {
			var data = $('#dg').datagrid('getData');
			for (var i = 0; i < data.rows.length; i++) {
				$('#dg').datagrid('endEdit', i);
			}
			if (data.rows.length == 0 || data.rows.length == null) {
				$.messager.alert('提示', '商品不能为空！', 'warning');
				return;
			}
			// for (var i = 0; i < data.rows.length; i++) {
			// $('#dg').datagrid('endEdit', i);
			// if (data.rows[i].CHANGENUMBER == null ||
			// data.rows[i].CHANGENUMBER == "") {
			// $.messager.alert('提示', '数量不能为空！', 'warning');
			// return;
			// }
			// }
			var remarks = "";
			var nums = "";
			var ids = "";
			for (var i = 0; i < data.rows.length; i++) {
				if (data.rows[i].CHANGENUMBER == null
						|| data.rows[i].CHANGENUMBER == 'undefiend'
						|| data.rows[i].CHANGENUMBER <= 0) {
					$.messager.alert('提示', '调整数量必须大于0！', 'warning');
					return;
				}
				if (data.rows[i].REMARK == null || data.rows[i].REMARK == "") {
					remarks += " ,";
				} else {
					remarks += data.rows[i].REMARK + ",";
				}
				ids += data.rows[i].ID + ",";
				nums += data.rows[i].CHANGENUMBER + ",";
			}
			remarks = encodeURI(encodeURI(remarks));
			// 保存
			var url = Utils.getRootPath() + '/switchChange/save?ids=' + ids
					+ '&nums=' + nums + '&remarks=' + remarks;
			$.ajax({
				type : "post",
				url : url,
				data : $('#' + formId).serialize(),
				async : false,
				dataType : 'json',
				success : function(result) {
					if (result.result) {
						$.messager.alert('提示', '保存成功', 'info', function() {
							window.parent.loadDataGrid('zcSwitchChange');
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
		var fromcangku = $('#fromBranchId').combobox('getValue');
		var tocangku = $('#toBranchId').combobox('getValue');
		var jingshouren = $('#operatorUserId').combobox('getValue');
		if (fromcangku == '') {
			$.messager.alert('提示', '转出仓库未选择或填写无效', 'warning');
			return;
		} else if (tocangku == '') {
			$.messager.alert('提示', '转入仓库未选择或填写无效', 'warning');
			return;
		} else if (tocangku == fromcangku) {
			$.messager.alert('提示', '转入仓库与转出仓库不能一致', 'warning');
			return;
		} else if (jingshouren == '') {
			$.messager.alert('提示', '业务员未选择或填写无效', 'warning');
			return;
		} else {
			var data = $('#dg').datagrid('getData');
			if (data.rows.length == 0 || data.rows.length == null) {
				$.messager.alert('提示', '商品不能为空！', 'warning');
				return;
			}
			for (var i = 0; i < data.rows.length; i++) {
				$('#dg').datagrid('endEdit', i);
				if (data.rows[i].CHANGENUMBER == null
						|| data.rows[i].CHANGENUMBER == "") {
					$.messager.alert('提示', '数量不能为空！', 'warning');
					return;
				}
			}
			var changeId = $('#switchChangeId').val();
			var remarks = "";
			var nums = "";
			var ids = "";
			for (var i = 0; i < data.rows.length; i++) {
				if (data.rows[i].CHANGENUMBER == null
						|| data.rows[i].CHANGENUMBER == 'undefiend'
						|| data.rows[i].CHANGENUMBER <= 0) {
					$.messager.alert('提示', '调整数量必须大于0！', 'warning');
					return;
				}
				if (data.rows[i].REMARK == null || data.rows[i].REMARK == "") {
					remarks += " ,";
				} else {
					remarks += data.rows[i].REMARK + ",";
				}
				ids += data.rows[i].ID + ",";
				nums += data.rows[i].CHANGENUMBER + ",";
			}
			remarks = encodeURI(encodeURI(remarks));
			// 保存
			for (var i = 0; i < data.rows.length; i++) {
				$('#dg').datagrid('beginEdit', i);
			}
			var url = Utils.getRootPath() + '/switchChange/update?ids=' + ids
					+ '&nums=' + nums + '&changeId=' + changeId + '&remarks='
					+ remarks;
			$.ajax({
				type : "post",
				url : url,
				data : $('#' + formId).serialize(),
				async : false,
				dataType : 'json',
				success : function(result) {
					if (result.result) {
						$.messager.alert('提示', '保存成功', 'info', function() {
							window.parent.loadDataGrid('zcSwitchChange');
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
	var url = Utils.getRootPath() + '/switchChange/checkChange';
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
					window.parent.loadDataGrid('zcSwitchChange');
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
	var changeId = $('#switchChangeId').val();
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
	var url = Utils.getRootPath() + '/switchChange/detail?id=' + id;
	add(url, '详情', 1000, 800);
}

function previewPrint() {
	var switchChangeId = $('#switchChangeId').val();
	$.ajax({
		type : "get",
		url : Utils.getRootPath() + '/switchChange/gotoPrintSwitchChange?id='
				+ switchChangeId,
		data : {},
		success : function(data) {
			$('#prints').window({
				title : '打印预览',
				closable : true,
				maximizable : false,
				modal : false,
				draggable : false,
				width : 800,
				height : 450
			});
			$('#prints').html(data);
		}
	});
}
function PreviewMytable() {
	var LODOP = getLodop();
	LODOP.PRINT_INIT("转仓单表格");
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
				url : Utils.getRootPath() + '/switchChange/removeGoods',
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

function deleteOrder() {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要删除的调整单！', 'warning');
		return;
	}
	var row = rows[0];
	var id = row.ID;
	if (row.STATUS == "2") {
		$.messager.alert('提示', '该转仓单已通过审核，不可删除！', 'warning');
		return;
	} else if (row.STATUS == "3") {
		$.messager.alert('提示', '该转仓单待处理，不可删除！', 'warning');
		return;
	} else if (row.STATUS == "1") {
		$.messager.alert('提示', '该转仓单已提交审核，不可删除！', 'warning');
		return;
	} else if (row.STATUS == "4") {
		$.messager.alert('提示', '该转仓单已完成！', 'warning');
		return;
	} else {
		$.messager.confirm('确认 ', '确定要删除 ？', function(r) {
			if (r) {
				$.ajax({
					type : "post",
					url : Utils.getRootPath() + '/switchChange/deleteOrder',
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
}