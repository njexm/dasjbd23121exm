//添加
function add(url, title, x, y) {
	window.parent.initAdd(url, title, x, y);
}

// 编辑
function editDifference(title, x, y) {
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
	var url = Utils.getRootPath() + '/checkDifference/gotoEdit?id=' + row.ID;
	add(url, title, x, y);
}

function checkDifferenceDetail(title, x, y) {
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
		$.messager.alert('提示', '该盘点差异单未提交，不可审核！', 'warning');
		return;
	}
	if (row.STATUS == '3') {
		$.messager.alert('提示', '该盘点差异单待处理，不可审核！', 'warning');
		return;
	}
	if (row.STATUS == '2') {
		$.messager.alert('提示', '该盘点差异单审核已通过，不可再次审核！', 'warning');
		return;
	}
	var url = Utils.getRootPath() + '/checkDifference/check?id=' + row.ID;
	add(url, title, x, y);
}

// 打开新增页面
function addNewNumber() {
	var url = Utils.getRootPath() + '/warehouse/wareHouse/addNewNumber';
	add(url, '新增盘点号', 800, 600);
}

// 提交审核
function insertDifference() {
	var data = $('#dg').datagrid('getData');
	var differenceId = $('#checkDifferenceId').val();
	if (data.rows.length == 0) {
		$.messager.alert('提示', '该差异单没有数据，请删除!', 'warning');
		return;
	}
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
	}
	var rows = JSON.stringify(data.rows);
	var url = Utils.getRootPath() + '/checkDifference/saveDifference';
	$.ajax({
		type : "post",
		url : url,
		data : {
			'differenceId' : differenceId,
			'rows' : rows
		},
		async : false,
		dataType : 'json',
		success : function(result) {
			if (result.result) {
				$.messager.alert('提示', '保存成功', 'info', function() {
					window.parent.loadDataGrid('checkDifference');
					window.parent.closeWinForm();
				});
			} else if (result.status == "error") {
				$.messager.alert('提示', '保存失败，请联系管理员', 'error');
			}
		}
	});
}

// 审核
function checkDifference(type) {
	var differenceId = $('#checkDifferenceId').val();
	var data = $('#dg').datagrid('getData');
	var rows = JSON.stringify(data.rows);
	if (type == '2') {
		$.messager.confirm('确认 ', '确定审核通过？', function(r) {
			if (r) {
				CheckByAjax(rows, differenceId, null, type);
			}
		});
	} else if (type == '3') {
		$.messager.prompt('提示信息', '请输入审核未通过的原因：', function(r) {
			if (r) {
				CheckByAjax(rows, differenceId, r, type);
			}
			if (r.length == 0 || r == null || r == 'undefine') {
				r = " ";
				CheckByAjax(rows, differenceId, r, type);
			}
		});
	}
}

function CheckByAjax(rows, differenceId, reason, type) {
	var checkAreas = $('#checkAreas').val();
	$("#ddialog").dialog('open');
	var url = Utils.getRootPath()
			+ '/checkDifference/checkDifference?checkAreas=' + checkAreas;
	$.ajax({
		type : "post",
		url : url,
		data : {
			'rows' : rows,
			'differenceId' : differenceId,
			'reason' : reason,
			'type' : type
		},
		async : false,
		dataType : 'json',
		success : function(result) {
			$("#ddialog").dialog('close');
			if (result.result) {
				$.messager.alert('提示', '审核完成', 'info', function() {
					window.parent.loadDataGrid('checkDifference');
					window.parent.closeWinForm();
				});
			} else if (result.status == "error") {
				$.messager.alert('提示', '保存失败，请联系管理员', 'error');
			}
		}
	});
}
function opendetail(id) {
	var url = Utils.getRootPath() + '/checkDifference/detail?id=' + id;
	add(url, '详情', 1000, 800);
}

function previewPrint() {
	var checkDifferenceId = $('#checkDifferenceId').val();
	$.ajax({
		type : "get",
		url : Utils.getRootPath()
				+ '/checkDifference/gotoPrintCheckDifference?id='
				+ checkDifferenceId,
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
	LODOP.PRINT_INIT("盘点差异单表格");
	var strStyle = "<style> table,td,th {border-width: 1px;border-style: solid;border-collapse: collapse}</style>";
	LODOP.ADD_PRINT_TABLE(130, "5%", "90%", 314, strStyle
			+ document.getElementById("div2").innerHTML);
	LODOP.SET_PRINT_STYLEA(0, "Vorient", 3);
	LODOP.ADD_PRINT_HTM(26, "5%", "90%", 140,
			document.getElementById("div1").innerHTML);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.SET_PRINT_STYLEA(0, "LinkedItem", 1);
	LODOP.PREVIEW();
};

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
		$.messager.alert('提示', '该盘点差异单已通过审核，不可删除！', 'warning');
		return;
	} else if (row.STATUS == "1") {
		$.messager.alert('提示', '该盘点差异单已提交审核，不可产出！', 'warning');
		return;
	} else {
		$.messager.confirm('确认 ', '确定要删除 ？', function(r) {
			if (r) {
				$.ajax({
					type : "post",
					url : Utils.getRootPath() + '/checkDifference/delete',
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

//导出
function exportExcel(checkNumber, id) {
	debugger;
	var data = $('#dg').datagrid('getData');
	var differentReason = "";
	for (var i = 0; i < data.rows.length; i++) {
		var ed = $('#dg').datagrid('getEditor', {index:i,field:'DIFFERENCEREASON'});
		var productname  = $(ed.target).combobox('getText') ;
		if (productname==null ||productname==''||productname=='undefiend') {
			differentReason+=" "+",";
		}else{
			differentReason +=$(ed.target).combobox('getText')+",";
		}
		
	}
	var url = Utils.getRootPath() + '/exportExcel/outCheckDifferenceExcel?id='
			+ id + '&checkNumber=' + checkNumber+ '&differentReason=' + differentReason;
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
function exportCheckExcel(checkNumber, id) {
	var url = Utils.getRootPath() + '/exportExcel/outCheckOverDifferenceExcel?id='
			+ id + '&checkNumber=' + checkNumber;
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


//批量导入
function batchImport(checkNumber,id) {
	debugger;
	var url = Utils.getRootPath()
			+ '/importExcel/saveCheckDifferenceImport?checkNumber=' + checkNumber+'&id=' +id;
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
