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
var getRowsId = 
	function() {
	
	var data = $('#dg').datagrid('getData');
	
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		if (data.rows[i].GOODS_PURCHASE_PRICE_2==0 
				|| data.rows[i].LOWEST_PRICE_2==0 
				|| data.rows[i].WHOLESALE_PRICE_2==0
				|| data.rows[i].GOODS_PRICE_2==0 
				|| data.rows[i].DISTRIBUTION_PRICE_2==0 
				|| data.rows[i].MEMBER_PRICE_2==0
				|| data.rows[i].WHOLESALE_PRICE2_2==0
				|| data.rows[i].WHOLESALE_PRICE3_2==0
				|| data.rows[i].WHOLESALE_PRICE4_2==0
				|| data.rows[i].WHOLESALE_PRICE5_2==0
				|| data.rows[i].WHOLESALE_PRICE6_2==0
				|| data.rows[i].WHOLESALE_PRICE7_2==0
				|| data.rows[i].WHOLESALE_PRICE8_2==0
				|| data.rows[i].MEMBER_PRICE2_2==0
				|| data.rows[i].MEMBER_PRICE3_2==0
				|| data.rows[i].MEMBER_PRICE4_2==0
				|| data.rows[i].MEMBER_PRICE5_2==0
			) {
			for (var j = 0; j < data.rows.length; j++) {
				$('#dg').datagrid('beginEdit', j);
			}
			$.messager.alert('提示', '请正确填写数量！', 'warning');
			return;
		}
		var ids ="";
		var goodsPurchasePrice2 = "";
		var lowestPrice2 = "";
		var wholeSalePrice2 = "";
		var goodPrice2 = "";
		var distributionPrice2 ="";
		var memberPrice2 = "";
		var wholeSalePrice22 ="";
		var wholeSalePrice32 ="";
		var wholeSalePrice42 ="";
		var wholeSalePrice52 ="";
		var wholeSalePrice62 ="";
		var wholeSalePrice72 ="";
		var wholeSalePrice82 ="";
		var memberPrice22 ="";
		var memberPrice32 ="";
		var memberPrice42 ="";
		var memberPrice52 ="";
		for (var i = 0; i < data.rows.length; i++) {
			ids += data.rows[i].ID + ",";
			goodsPurchasePrice2 += data.rows[i].GOODS_PURCHASE_PRICE_2+",";
			lowestPrice2 += data.rows[i].LOWEST_PRICE_2 +",";
			wholeSalePrice2 +=data.rows[i].WHOLESALE_PRICE_2 +",";
			goodPrice2 += data.rows[i].GOODS_PRICE_2 + ",";
			distributionPrice2 +=data.rows[i].DISTRIBUTION_PRICE_2 + ",";
			memberPrice2 +=data.rows[i].MEMBER_PRICE_2 + ",";
			wholeSalePrice22 +=data.rows[i].WHOLESALE_PRICE2_2 + ",";
			wholeSalePrice32 +=data.rows[i].WHOLESALE_PRICE3_2 + ",";
			wholeSalePrice42 +=data.rows[i].WHOLESALE_PRICE4_2 + ",";
			wholeSalePrice52 +=data.rows[i].WHOLESALE_PRICE5_2 + ",";
			wholeSalePrice62 +=data.rows[i].WHOLESALE_PRICE6_2 + ",";
			wholeSalePrice72 +=data.rows[i].WHOLESALE_PRICE7_2 + ",";
			wholeSalePrice82 +=data.rows[i].WHOLESALE_PRICE8_2 + ",";
			memberPrice22 +=data.rows[i].MEMBER_PRICE2_2 + ",";
			memberPrice32 +=data.rows[i].MEMBER_PRICE3_2 + ",";
			memberPrice42 +=data.rows[i].MEMBER_PRICE4_2 + ",";
			memberPrice52 +=data.rows[i].MEMBER_PRICE5_2 + ",";
			
		}
		
		
	}
	
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
	debugger;
var data = $('#dg').datagrid('getData');
	
//	for (var i = 0; i < data.rows.length; i++) {
//		$('#dg').datagrid('endEdit', i);
//		if (data.rows[i].GOODS_PURCHASE_PRICE_2==0 
//				|| data.rows[i].LOWEST_PRICE_2==0 
//				|| data.rows[i].WHOLESALE_PRICE_2==0
//				|| data.rows[i].GOODS_PRICE_2==0 
//				|| data.rows[i].DISTRIBUTION_PRICE_2==0 
//				|| data.rows[i].MEMBER_PRICE_2==0
//				|| data.rows[i].WHOLESALE_PRICE2_2==0
//				|| data.rows[i].WHOLESALE_PRICE3_2==0
//				|| data.rows[i].WHOLESALE_PRICE4_2==0
//				|| data.rows[i].WHOLESALE_PRICE5_2==0
//				|| data.rows[i].WHOLESALE_PRICE6_2==0
//				|| data.rows[i].WHOLESALE_PRICE7_2==0
//				|| data.rows[i].WHOLESALE_PRICE8_2==0
//				|| data.rows[i].MEMBER_PRICE2_2==0
//				|| data.rows[i].MEMBER_PRICE3_2==0
//				|| data.rows[i].MEMBER_PRICE4_2==0
//				|| data.rows[i].MEMBER_PRICE5_2==0
//			) {
//			for (var j = 0; j < data.rows.length; j++) {
//				$('#dg').datagrid('beginEdit', j);
//			}
//			$.messager.alert('提示', '请正确填写价格！', 'warning');
//			return;
//		}
//	}
		var ids ="";
		var goodsPurchasePrice2s = "";
		var lowestPrice2s = "";
		var wholeSalePrice2s = "";
		var goodPrice2s = "";
		var distributionPrice2s ="";
		var memberPrice2s = "";
		var wholeSalePrice22s ="";
		var wholeSalePrice32s ="";
		var wholeSalePrice42s ="";
		var wholeSalePrice52s ="";
		var wholeSalePrice62s ="";
		var wholeSalePrice72s ="";
		var wholeSalePrice82s ="";
		var memberPrice22s ="";
		var memberPrice32s ="";
		var memberPrice42s ="";
		var memberPrice52s ="";
		for (var i = 0; i < data.rows.length; i++) {
			ids += data.rows[i].ID + ",";
			goodsPurchasePrice2s += data.rows[i].GOODS_PURCHASE_PRICE_2+",";
			
			lowestPrice2s += data.rows[i].LOWEST_PRICE_2 +",";
			wholeSalePrice2s +=data.rows[i].WHOLESALE_PRICE_2 +",";
			
			goodPrice2s += data.rows[i].GOODS_PRICE_2 + ",";
			distributionPrice2s +=data.rows[i].DISTRIBUTION_PRICE_2 + ",";
			
			memberPrice2s +=data.rows[i].MEMBER_PRICE_2 + ",";
			wholeSalePrice22s +=data.rows[i].WHOLESALE_PRICE2_2 + ",";
			wholeSalePrice32s +=data.rows[i].WHOLESALE_PRICE3_2 + ",";
			wholeSalePrice42s +=data.rows[i].WHOLESALE_PRICE4_2 + ",";
			wholeSalePrice52s +=data.rows[i].WHOLESALE_PRICE5_2 + ",";
			wholeSalePrice62s +=data.rows[i].WHOLESALE_PRICE6_2 + ",";
			wholeSalePrice72s +=data.rows[i].WHOLESALE_PRICE7_2 + ",";
			wholeSalePrice82s +=data.rows[i].WHOLESALE_PRICE8_2 + ",";
			memberPrice22s +=data.rows[i].MEMBER_PRICE2_2 + ",";
			memberPrice32s +=data.rows[i].MEMBER_PRICE3_2 + ",";
			memberPrice42s +=data.rows[i].MEMBER_PRICE4_2 + ",";
			memberPrice52s +=data.rows[i].MEMBER_PRICE5_2 + ",";
			
		}
		
		
	
	//var ids = getRowsId();
	// 数据有限性判断
	// if (validateSubmit(formId)) {
	if (ids.length == 0) {
		$.messager.alert('提示', '没有商品可以调价！', 'warning');
		return;
	}

	var url = Utils.getRootPath()
			+ '/adjustment/adjustment/saveAdjustments?ids=' + ids
			+'&goodsPurchasePrice2s='+goodsPurchasePrice2s
			+'&lowestPrice2s='+lowestPrice2s
			+'&wholeSalePrice2s='+wholeSalePrice2s
			+'&goodPrice2s='+goodPrice2s
			+'&distributionPrice2s='+distributionPrice2s
			+'&memberPrice2s='+memberPrice2s
			+'&wholeSalePrice22s='+wholeSalePrice22s
			+'&wholeSalePrice32s='+wholeSalePrice32s
			+'&wholeSalePrice42s='+wholeSalePrice42s
			+'&wholeSalePrice52s='+wholeSalePrice52s
			+'&wholeSalePrice62s='+wholeSalePrice62s
			+'&wholeSalePrice72s='+wholeSalePrice72s
			+'&wholeSalePrice82s='+wholeSalePrice82s
			+'&memberPrice22s='+memberPrice22s
			+'&memberPrice32s='+memberPrice32s
			+'&memberPrice42s='+memberPrice42s
			+'&memberPrice52s='+memberPrice52s;
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