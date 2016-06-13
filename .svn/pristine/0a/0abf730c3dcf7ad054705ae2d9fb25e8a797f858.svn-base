//添加
function add(url, title, x, y) {
	window.parent.initAdd(url, title, x, y);
}

// 拼接ID
var getRowsInfo = function() {
	var ids = [];
	var rows = $('#dg').datagrid('getSelections');
	for (var i = 0; i < rows.length; i++) {
		ids.push(rows[i].ID);
	}
	return ids;
};

// 拼接ID
var getRowsId = function() {
	var ids = [];
	var data = $('#dg').datagrid('getData');
	for (var i = 0; i < data.rows.length; i++) {
		var row = data.rows[i];
		ids.push(row.ID);
	}
	return ids;
};

// 按条件查询
function searchAdjustment() {
	var queryParams = {
		'adjustments.auditor' : $('#auditor').val(),
		'adjustments.adjustment_id' : $('#adjustment_id').val(),
		'adjustments.createtime_adj' : $('#createtime').val(),
		'adjustments.audflag' : $('#audflag').val(),
	};
	$('#dg').datagrid('options').queryParams = queryParams;
	$("#dg").datagrid('load');

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

// 打开新增调价单页面
function addAdjustment() {
	var url = Utils.getRootPath() + '/adjustment/adjustment/addAdjustment';
	add(url, '新增调价单', 1000, 535);
}

// //从商品档案打开新增调价单页面
// function editAdjustment() {
// var rows = $('#dg').datagrid('getSelections');
// if (rows.length == 0) {
// $.messager.alert('提示', '请选择需要操作的数据！', 'warning');
// return;
// }
// if (rows.length > 1) {
// $.messager.alert('提示', '只能选择一条数据进行操作！', 'warning');
// return;
// }
// var row = rows[0];
// var url= Utils.getRootPath() + '/adjustment/adjustment/addAdjustment?id=' +
// row.ID;
// add(url,'新增调价单',1000,535);
// }

// 打开编辑调价单页面
function editAdjustment(title, x, y) {
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
	if (row.AUDFLAG == "1") {
		$.messager.alert('提示', '该调价单已提交，不可再次编辑！', 'warning');
		return;
	} else if (row.AUDFLAG == "2") {
		$.messager.alert('提示', '该调价单已通过审核，不可再次编辑！', 'warning');
		return;
	} else if (row.AUDFLAG == "4") {
		$.messager.alert('提示', '该调价单已完成！', 'warning');
		return;
	} else if (row.AUDFLAG == "5") {
		$.messager.alert('提示', '该调价单审核未通过，请新建调价单！', 'warning');
		return;
	} else {
		var url = Utils.getRootPath()
				+ '/adjustment/adjustment/gotoEditAdjustment?id=' + row.ID;
		add(url, "编辑调价单", 1000, 535);
	}
}

// 打开查看调价单页面
function openAdjustmentsDetail(id) {
	var url = Utils.getRootPath()
			+ '/adjustment/adjustment/gotoAdjustmentsDetail?id=' + id;
	add(url, '调价单详情', 1000, 535);
}

// 打开审核调价单页面
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
	if (row.AUDFLAG == "0") {
		$.messager.alert('提示', '该调价单未保存，请等待其提交后再审核！', 'warning');
		return;
	} else if (row.AUDFLAG == "2") {
		$.messager.alert('提示', '该调价单已通过审核，不可再次审核！', 'warning');
		return;
	} else if (row.AUDFLAG == "3") {
		$.messager.alert('提示', '该调价单尚未处理，请等待其提交后再审核！', 'warning');
		return;
	} else if (row.AUDFLAG == "4") {
		$.messager.alert('提示', '该调价单已完成！', 'warning');
		return;
	} else if (row.AUDFLAG == "5") {
		$.messager.alert('提示', '该调价单审核未通过，请新建调价单！', 'warning');
		return;
	} else {
		var url = Utils.getRootPath()
				+ '/adjustment/adjustment/gotoEditCheck?id=' + row.ID;
		add(url, "审核调价单", 1000, 535);
	}
}

// 在调价单中添加一条商品
function addAdj() {
	var rows = $('#goodsDg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择至少一条商品进行添加！', 'warning');
		return;
	}
	var ids = "";
	for (var i = 0; i < rows.length; i++) {
		ids += rows[i].ID + ",";
	}
	var url = Utils.getRootPath() + '/adjustment/adjustment/addAdj?ids=' + ids;
	$.ajax({
		type : "post",
		url : url,
		async : false,
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

// 更新调价单中的数据
function updateAdj() {
	var rows = $('#dg').datagrid('getRows');
	for (var i = 0; i < rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
	}

	var ids = "";
	var serialnumber = "";
	var lowest_price_2 = "";
	var goods_purchase_price_2 = "";
	var wholesale_price_2 = "";
	var goods_price_2 = "";
	var distribution_price_2 = "";
	var member_price_2 = "";
	var wholesale_price2_2 = "";
	var wholesale_price3_2 = "";
	var wholesale_price4_2 = "";
	var wholesale_price5_2 = "";
	var wholesale_price6_2 = "";
	var wholesale_price7_2 = "";
	var wholesale_price8_2 = "";
	var member_price2_2 = "";
	var member_price3_2 = "";
	var member_price4_2 = "";
	var member_price5_2 = "";
	var remark = "";
	for (var i = 0; i < rows.length; i++) {
		ids += rows[i].ID + ",";
		serialnumber += rows[i].SERIALNUMBER + ",";
		lowest_price_2 += rows[i].LOWEST_PRICE_2 + ",";
		goods_purchase_price_2 += rows[i].GOODS_PURCHASE_PRICE_2 + ",";
		wholesale_price_2 += rows[i].WHOLESALE_PRICE_2 + ",";
		goods_price_2 += rows[i].GOODS_PRICE_2 + ",";
		distribution_price_2 += rows[i].DISTRIBUTION_PRICE_2 + ",";
		member_price_2 += rows[i].MEMBER_PRICE_2 + ",";
		wholesale_price2_2 += rows[i].WHOLESALE_PRICE2_2 + ",";
		wholesale_price3_2 += rows[i].WHOLESALE_PRICE3_2 + ",";
		wholesale_price4_2 += rows[i].WHOLESALE_PRICE4_2 + ",";
		wholesale_price5_2 += rows[i].WHOLESALE_PRICE5_2 + ",";
		wholesale_price6_2 += rows[i].WHOLESALE_PRICE6_2 + ",";
		wholesale_price7_2 += rows[i].WHOLESALE_PRICE7_2 + ",";
		wholesale_price8_2 += rows[i].WHOLESALE_PRICE8_2 + ",";
		member_price2_2 += rows[i].MEMBER_PRICE2_2 + ",";
		member_price3_2 += rows[i].MEMBER_PRICE3_2 + ",";
		member_price4_2 += rows[i].MEMBER_PRICE4_2 + ",";
		member_price5_2 += rows[i].MEMBER_PRICE5_2 + ",";
		remark += rows[i].REMARK + ",";
	}

	if (ids.length == 0) {
		$.messager.alert('提示', '没有商品可以调价！', 'warning');
		return;
	}

	var url = Utils.getRootPath()
			+ '/adjustment/adjustment/updateAdj?serialnumber=' + serialnumber
			+ '&lowest_price_2=' + lowest_price_2 + '&goods_purchase_price_2='
			+ goods_purchase_price_2 + '&wholesale_price_2='
			+ wholesale_price_2 + '&goods_price_2=' + goods_price_2
			+ '&distribution_price_2=' + distribution_price_2
			+ '&member_price_2=' + member_price_2 + '&wholesale_price2_2='
			+ wholesale_price2_2 + '&wholesale_price3_2=' + wholesale_price3_2
			+ '&wholesale_price4_2=' + wholesale_price4_2
			+ '&wholesale_price5_2=' + wholesale_price5_2
			+ '&wholesale_price6_2=' + wholesale_price6_2
			+ '&wholesale_price7_2=' + wholesale_price7_2
			+ '&wholesale_price8_2=' + wholesale_price8_2 + '&member_price2_2='
			+ member_price2_2 + '&member_price3_2=' + member_price3_2
			+ '&member_price4_2=' + member_price4_2 + '&member_price5_2='
			+ member_price5_2 + '&ids=' + ids;
	$.ajax({
		type : "post",
		url : url,
		data : {
			'remark' : remark
		},
		async : true,
		dataType : 'json',
		success : function(result) {
			if (result.result) {
				$("#dg").datagrid('load');
			} else {
				$("#dg").datagrid('load');
			}
		}
	});
}

// 在调价单中删除选中的数据
function deleteAdj() {
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
	$.messager.confirm('确认 ', '确定要删除 ？', function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : Utils.getRootPath() + '/adjustment/adjustment/deleteAdj',
				data : {
					id : ids
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
}

// 删除选中的调价单
function deleteAdjustment() {
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
	var row = rows[0];
	if (row.AUDFLAG == "2") {
		$.messager.alert('提示', '该调价单已通过审核！', 'warning');
		return;
	} else if (row.AUDFLAG == "4") {
		$.messager.alert('提示', '该调价单已完成！', 'warning');
		return;
	} else {
		$.messager.confirm('确认 ', '确定要删除 ？', function(r) {
			if (r) {
				$.ajax({
					type : "post",
					url : Utils.getRootPath()
							+ '/adjustment/adjustment/deleteAdjustment',
					data : {
						id : ids
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
	}
}

// 保存调价单
function saveAdjustments(formId) {
	var ids = getRowsId();
	// 数据有限性判断
	// if (validateSubmit(formId)) {
	if (ids.length == 0) {
		$.messager.alert('提示', '没有商品可以调价！', 'warning');
		return;
	}

	var url = Utils.getRootPath()
			+ '/adjustment/adjustment/saveAdjustments?ids=' + ids;
	$.messager.confirm('确认 ', '确定要保存调价单？', function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : url,
				data : $('#' + formId).serialize(),
				async : false,
				dataType : 'json',
				success : function(result) {
					if (result.result) {
						$.messager.alert('提示', '新增成功', 'info', function() {
							window.parent.loadDataGrid('adjustment');
							window.parent.closeWinForm();
						});
					} else if (result.status == "error") {
						$.messager.alert('提示', '新增失败，请联系管理员', 'error');
					}
				}
			});
		}
	});
	// }
}

// 修改并提交调价单
function updateAdjustments() {
	var rows = $('#dg').datagrid('getRows');
	for (var i = 0; i < rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
	}
	var ids = "";
	var serialnumber = "";
	var lowest_price_2 = "";
	var goods_purchase_price_2 = "";
	var wholesale_price_2 = "";
	var goods_price_2 = "";
	var distribution_price_2 = "";
	var member_price_2 = "";
	var wholesale_price2_2 = "";
	var wholesale_price3_2 = "";
	var wholesale_price4_2 = "";
	var wholesale_price5_2 = "";
	var wholesale_price6_2 = "";
	var wholesale_price7_2 = "";
	var wholesale_price8_2 = "";
	var member_price2_2 = "";
	var member_price3_2 = "";
	var member_price4_2 = "";
	var member_price5_2 = "";
	var remark = "";
	for (var i = 0; i < rows.length; i++) {
		ids += rows[i].ID + ",";
		serialnumber += rows[i].SERIALNUMBER + ",";
		lowest_price_2 += rows[i].LOWEST_PRICE_2 + ",";
		goods_purchase_price_2 += rows[i].GOODS_PURCHASE_PRICE_2 + ",";
		wholesale_price_2 += rows[i].WHOLESALE_PRICE_2 + ",";
		goods_price_2 += rows[i].GOODS_PRICE_2 + ",";
		distribution_price_2 += rows[i].DISTRIBUTION_PRICE_2 + ",";
		member_price_2 += rows[i].MEMBER_PRICE_2 + ",";
		wholesale_price2_2 += rows[i].WHOLESALE_PRICE2_2 + ",";
		wholesale_price3_2 += rows[i].WHOLESALE_PRICE3_2 + ",";
		wholesale_price4_2 += rows[i].WHOLESALE_PRICE4_2 + ",";
		wholesale_price5_2 += rows[i].WHOLESALE_PRICE5_2 + ",";
		wholesale_price6_2 += rows[i].WHOLESALE_PRICE6_2 + ",";
		wholesale_price7_2 += rows[i].WHOLESALE_PRICE7_2 + ",";
		wholesale_price8_2 += rows[i].WHOLESALE_PRICE8_2 + ",";
		member_price2_2 += rows[i].MEMBER_PRICE2_2 + ",";
		member_price3_2 += rows[i].MEMBER_PRICE3_2 + ",";
		member_price4_2 += rows[i].MEMBER_PRICE4_2 + ",";
		member_price5_2 += rows[i].MEMBER_PRICE5_2 + ",";
		remark += rows[i].REMARK + ",";
	}
	if (ids.length == 0) {
		$.messager.alert('提示', '没有商品可以调价！', 'warning');
		return;
	}
	var url = Utils.getRootPath()
			+ '/adjustment/adjustment/updateAdjustments?serialnumber='
			+ serialnumber + '&lowest_price_2=' + lowest_price_2
			+ '&goods_purchase_price_2=' + goods_purchase_price_2
			+ '&wholesale_price_2=' + wholesale_price_2 + '&goods_price_2='
			+ goods_price_2 + '&distribution_price_2=' + distribution_price_2
			+ '&member_price_2=' + member_price_2 + '&wholesale_price2_2='
			+ wholesale_price2_2 + '&wholesale_price3_2=' + wholesale_price3_2
			+ '&wholesale_price4_2=' + wholesale_price4_2
			+ '&wholesale_price5_2=' + wholesale_price5_2
			+ '&wholesale_price6_2=' + wholesale_price6_2
			+ '&wholesale_price7_2=' + wholesale_price7_2
			+ '&wholesale_price8_2=' + wholesale_price8_2 + '&member_price2_2='
			+ member_price2_2 + '&member_price3_2=' + member_price3_2
			+ '&member_price4_2=' + member_price4_2 + '&member_price5_2='
			+ member_price5_2 + '&ids=' + ids;
	$.messager.confirm('确认 ', '确定要提交调价单？', function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : url,
				data : {
					'remark' : remark
				},
				async : true,
				dataType : 'json',
				success : function(result) {
					if (result.result) {
						$.messager.alert('提示', '提交成功', 'info', function() {
							window.parent.loadDataGrid('adjustment');
							window.parent.closeWinForm();
						});
					} else if (result.status == "error") {
						$.messager.alert('提示', '提交失败，请联系管理员', 'error');
					}
				}
			});
		}
	});
}

// 调价单审核通过
function checkPass() {
	var id = $('#adjustments_id').val();
	var auditor = $('#auditor').val();
	var url = Utils.getRootPath() + '/adjustment/adjustment/checkPass?id=' + id
			+ '&auditor=' + auditor;
	$.messager.confirm('确认 ', '确定审核通过？', function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : url,
				async : false,
				dataType : 'json',
				success : function(result) {
					if (result.result) {
						$.messager.alert('提示', '审核通过！', 'info', function() {
							window.parent.loadDataGrid('adjustment');
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

// 调价单审核不通过
function checkNoPass() {
	var id = $('#adjustments_id').val();
	var auditor = $('#auditor').val();
	var url = Utils.getRootPath() + '/adjustment/adjustment/checkNoPass?id='
			+ id + '&auditor=' + auditor;
	$.messager.confirm('确认 ', '确定审核不能通过？', function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : url,
				async : false,
				dataType : 'json',
				success : function(result) {
					if (result.result) {
						$.messager.alert('提示', '审核不通过！', 'info', function() {
							window.parent.loadDataGrid('adjustment');
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

function openChoseGoods() {
	$.ajax({
		type : "get",
		url : Utils.getRootPath() + '/adjustment/adjustment/openChoseGoods',
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
}