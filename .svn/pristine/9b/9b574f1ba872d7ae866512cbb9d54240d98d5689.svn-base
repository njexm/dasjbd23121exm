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
	var url = Utils.getRootPath()
			+ '/provider/poviderinfo/gotoEditProvider?id=' + row.ID;
	add(url, title, x, y);
}

// 查看详细
function detail(title, x, y) {
	// var rows = $('#dg').datagrid('getSelections');
	// if (rows.length == 0) {
	// $.messager.alert('提示', '请选择需要操作的数据！', 'warning');
	// return;
	// }
	// if (rows.length > 1) {
	// $.messager.alert('提示', '请选择一条数据进行操作！', 'warning');
	// return;
	// }
	var row = rows[0];
	var url = Utils.getRootPath()
			+ '/provider/poviderinfo/gotoDetailProvider?id=' + row.ID;
	add(url, title, x, y);
}

// 新增数据
function saveProviderInfo(formId) {
	// 数据有限性判断
	if (validateSubmit(formId)) {
		// 保存
		var province = $('#province').val();
		var city = $('#city').val();
		var country = $('#country').val();
		if(province == null || province == "" || province.length == 0){
			$.messager.alert('提示', '请填写完整的地址！', 'warning');
			return;
		}
		if(country == null || country == "" || city.length == 0){
			$.messager.alert('提示', '请填写完整的地址！', 'warning');
			return;
		}
		if(city == null || city == "" || city.length == 0){
			$.messager.alert('提示', '请填写完整的地址！', 'warning');
			return;
		}
		var url = Utils.getRootPath() + '/provider/poviderinfo/save';
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
function editProviderInfo(formId) {
	// 数据有限性判断
	if (validateSubmit(formId)) {
		// 保存
		var province = $('#province').val();
		var city = $('#city').val();
		var county = $('#county').val();
		var street = $('#street').val();
		if(street == null || street == "" || street.length == 0){
			$.messager.alert('提示', '请填写正确地址！', 'warning');
			return;
		}
		if(province == null || province == "" || province.length == 0){
			$.messager.alert('提示', '请填写完整的地址！', 'warning');
			return;
		}
		if(county == null || county == "" || county.length == 0){
			$.messager.alert('提示', '请填写完整的地址！', 'warning');
			return;
		}
		if(city == null || city == "" || city.length == 0){
			$.messager.alert('提示', '请填写完整的地址！', 'warning');
			return;
		}
		var url = Utils.getRootPath() + '/provider/poviderinfo/update';
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
function deleteProvider(formId) {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	var id = rows[0].ID;
	// 数据有限性判断
	if (validateSubmit(formId)) {
		// 保存
		var url = Utils.getRootPath()
				+ '/provider/poviderinfo/deleteProvider?id=' + id;
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
					$.messager.alert('提示', '该供应商存在交易关系，不可删除', 'warning');
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
// 打开新增供应商商品信息页面
function openAddGoods(title, x, y) {
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
	var url = Utils.getRootPath() + '/provider/poviderinfo/openAddGoods?id='
			+ row.ID;
	add(url, title, x, y);
}

// 新增供应商商品信息
function addTo() {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要添加的商品！', 'warning');
		return;
	}
	// 保存
	var providerId = $('#providerInfoId').val();
	var ids = getRowsInfo();
	var url = Utils.getRootPath() + '/provider/poviderinfo/addTo?ids=' + ids;
	$.ajax({
		type : "post",
		url : url,
		data : {
			'providerId' : providerId
		},
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

var getRowsInfo = function() {
	var ids = [];
	var rows = $('#dg').datagrid('getSelections');
	for (var i = 0; i < rows.length; i++) {
		ids.push(rows[i].ID);
	}
	return ids;
};

// 查看供应商商品
function goodsDetail(title, x, y) {
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
	var url = Utils.getRootPath() + '/provider/poviderinfo/gotoGoodsDetail?id='
			+ row.ID;
	add(url, title, x, y);
}

// 查看供应商基本信息
function opengoodsproItem(id) {
	var url = Utils.getRootPath()
			+ '/provider/poviderinfo/gotoDetailProvider?id=' + id;
	add(url, '查看供应商基本信息', 1000, 550);
}