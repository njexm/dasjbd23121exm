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
	var url = Utils.getRootPath() + '/branchTotal/edit?id=' + row.ID;
	add(url, title, x, y);
}

// 查看详细
function detail(title, x, y) {
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
	var url = Utils.getRootPath() + '/order/orders/gotoDetailOrders?id='
			+ row.ID;
	add(url, title, x, y);
}

// 新增数据
function saveBranch(formId) {
	// 数据有限性判断
	if (validateSubmit(formId)) {
		// 保存
		var money1 = $('#money').val();
		var money = new Number(money1);
		if(money > 1 || money < 0){
			$.messager.alert('提示', '折让数字在0~1之间!', 'warning');
			return;
		}
		var province = $('#province').val();
		var city = $('#city').val();
		var country = $('#country').val();
//		var street = $('#street').val();
		var branch_user = $('#branch_user').combobox('getValue');
		if(branch_user == null || branch_user == "" || branch_user.length == 0 || branch_user == "undefined"){
			$.messager.alert('提示', '请填写负责人！', 'warning');
			return;
		}
//		if(street == null || street == "" || street.length == 0){
//			$.messager.alert('提示', '请填写完整的地址！', 'warning');
//			return;
//		}
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
		var url = Utils.getRootPath() + '/branchTotal/save';
		$.ajax({
			type : "post",
			url : url,
			data : $('#' + formId).serialize(),
			async : false,
			dataType : 'json',
			success : function(result) {
				if (result.result) {
					$.messager.alert('提示', '保存成功', 'info', function() {
						// 刷新数据
						window.parent.loadDataGrid('branch');
						window.parent.closeWinForm();
					});
				} else {
					$.messager.alert('提示', '保存失败，请联系管理员!', 'error');
				}
			}
		});
	}
}

// 修改数据
function editBaseInfo(formId) {
	// 数据有限性判断
	if (validateSubmit(formId)) {
		// 保存
		var money = $('#money').val();
		if(money > 1 || money < 0){
			$.messager.alert('提示', '折让数字在0~1之间!', 'warning');
			return;
		}
		var url = Utils.getRootPath() + '/branchTotal/update';
		$.ajax({
			type : "post",
			url : url,
			data : $('#' + formId).serialize(),
			async : false,
			dataType : 'json',
			success : function(result) {
				if (result.result) {
					$.messager.alert('提示', '保存成功', 'info', function() {
						window.parent.loadDataGrid('branch');
						window.parent.closeWinForm();
					});
				} else {
					$.messager.alert('提示', '保存失败，请联系管理员!', 'error');
				}
			}
		});
	}
}

// 删除
function deleteInfo() {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	} else {
		$.messager.confirm('提示', '是否确认删除？', function(r) {
			if (r) {
				var ids = getRowsInfo();
				if (ids.length == 0) {
					$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
				} else {
					var url = Utils.getRootPath()
							+ "/branchTotal/deleteBranch?ids=" + ids;
					$.ajax({
						url : url,
						type : 'GET',
						data : {
							aaaaa : ids
						},
						async : false,
						dataType : 'json',
						success : function(result) {
							if (result.result) {
								$.messager.alert('提示', '删除成功！', 'info',
										function() {
											window.parent
													.loadDataGrid('branch');
										});
							} else {
								$.messager.alert('提示', '该分店正在营业，不可删除!', 'warning');
							}
						}
					});
				}
			}
		});

	}
	;
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

//选择区显示小区
function country(rec){
	var val = $('#country').combobox('getValue');
	var id = rec.ID;
//	var url = Utils.getRootPath()+ '/branchTotal/country?val='+val;
	$('#street').combobox({
		method:'POST',
        url:'${rc.contextPath}/branchTotal/country?id='+id,
        valueField:'AREANAME',
        textField:'AREANAME'
	});
	
//	$.ajax({
//		type : "post",
//		url : url,
//		async : false,
//		dataType : 'json',
//		success : function(result) {
//			if (result.result == true ) {
//				$.messager.alert('提示', '该供应商存在未审核通过结算单，不可选择', 'warning');
//				return;
//			}else if(result.result == false){
//				searchData();
//			}
//		}
//	});
}