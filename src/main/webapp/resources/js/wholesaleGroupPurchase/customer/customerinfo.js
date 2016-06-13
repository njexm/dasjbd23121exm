//添加
function add(url, title, x, y) {
	window.parent.initAdd(url, title, x, y);
}

// 打开编辑界面
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
	var url = Utils.getRootPath() + '/customer/customerinfo/gotoEdit?id='
			+ row.ID;
	add(url, title, x, y);
}

// 打开查看界面
function detail(id) {
	// var row = rows[0];
	var url = Utils.getRootPath() + '/customer/customerinfo/gotoDetail?id='
			+ id;
	add(url, '查看客户基本信息', 700, 550);
}

// 新增数据
function saveProviderInfo(formId) {
	// 数据有限性判断
	if (validateSubmit(formId)) {
		var telephone = $('#telephone').val();
		var mobilephone = $('#mobilephone').val();
		if (mobilephone == "" && telephone == "") {
			$.messager.alert('提示', '手机座机必填一项！', 'warning');
			return;
		}
		var province = $('#province').val();
		var city = $('#city').val();
		var county = $('#county').val();
		if (province == null || province == "" || province.length == 0) {
			$.messager.alert('提示', '请填写完整的地址！', 'warning');
			return;
		}
		if (county == null || county == "" || county.length == 0) {
			$.messager.alert('提示', '请填写完整的地址！', 'warning');
			return;
		}
		if (city == null || city == "" || city.length == 0) {
			$.messager.alert('提示', '请填写完整的地址！', 'warning');
			return;
		}
		var url = Utils.getRootPath() + '/customer/customerinfo/save';
		$.ajax({
			type : "post",
			url : url,
			data : $('#' + formId).serialize(),
			async : false,
			dataType : 'json',
			success : function(result) {
				if (result.result) {
					$.messager.alert('提示', '保存成功', 'info', function() {
						window.parent.loadDataGrid('provider');
						window.parent.closeWinForm();
					});
				} else if (result.status == "error") {
					$.messager.alert('提示', '保存失败，请联系管理员', 'error');
				}
			}
		});
	}
}

// 修改数据
function editInfo(formId) {
	// 数据有限性判断
	if (validateSubmit(formId)) {
		var telephone = $('#telephone').val();
		var mobilephone = $('#mobilephone').val();
		if (mobilephone == "" && telephone == "") {
			$.messager.alert('提示', '手机座机必填一项！', 'warning');
			return;
		}
		// 保存
		var province = $('#province').val();
		var city = $('#city').val();
		var county = $('#county').val();
		var street = $('#street').val();
		if (street == null || street == "" || street.length == 0) {
			$.messager.alert('提示', '请填写正确地址！', 'warning');
			return;
		}
		if (province == null || province == "" || province.length == 0) {
			$.messager.alert('提示', '请填写完整的地址！', 'warning');
			return;
		}
		if (county == null || county == "" || county.length == 0) {
			$.messager.alert('提示', '请填写完整的地址！', 'warning');
			return;
		}
		if (city == null || city == "" || city.length == 0) {
			$.messager.alert('提示', '请填写完整的地址！', 'warning');
			return;
		}
		var url = Utils.getRootPath() + '/customer/customerinfo/update';
		$.ajax({
			type : "post",
			url : url,
			data : $('#' + formId).serialize(),
			async : false,
			dataType : 'json',
			success : function(result) {
				if (result.result) {
					$.messager.alert('提示', '保存成功', 'info', function() {
						window.parent.loadDataGrid('provider');
						window.parent.closeWinForm();
					});
				} else if (result.status == "error") {
					$.messager.alert('提示', '保存失败，请联系管理员', 'error');
				}
			}
		});
	}
}

// 删除数据
function deleteInfo(formId) {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	$.messager.confirm('提示', '是否确认删除？', function(r) {
		if (r) {
			var id = rows[0].ID;
			// 数据有限性判断
			if (validateSubmit(formId)) {
				// 保存
				var url = Utils.getRootPath()
						+ '/customer/customerinfo/deleteCustomerInfo?id=' + id;
				$.ajax({
					type : "post",
					url : url,
					async : false,
					dataType : 'json',
					success : function(result) {
						if (result.result) {
							$.messager.alert('提示', '删除成功', 'info', function() {
								window.parent.loadDataGrid('provider');
								window.parent.closeWinForm();
							});
						} else {
							$.messager
									.alert('提示', '该供应商存在交易关系，不可删除', 'warning');
						}
					}
				});
			}
		}
	});
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
//
//
// var getRowsInfo = function() {
// var ids = [];
// var rows = $('#dg').datagrid('getSelections');
// for (var i = 0; i < rows.length; i++) {
// ids.push(rows[i].ID);
// }
// return ids;
// };
//
// // 查看供应商基本信息
// function opengoodsproItem(id) {
// var url = Utils.getRootPath()
// + '/customer/customerinfo/gotoDetailProvider?id=' + id;
// add(url, '查看供应商基本信息', 1000, 550);
// }


//批量导入
function batchImport() {
	var url = Utils.getRootPath() + '/customer/customerinfo/saveBatchImport';
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
					window.parent.loadDataGrid('provider');
					$('#showWinBatch').window('close');
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
