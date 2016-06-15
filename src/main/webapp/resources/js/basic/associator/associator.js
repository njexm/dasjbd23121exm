//添加
function add(url, title, x, y) {
	window.parent.initAdd(url, title, x, y);
}

//打开新增页面
function addAssociator() {
	var url= Utils.getRootPath() + '/associator/associator/addAssociator';
	add(url,'新增会员信息',1000,465);
}

//新增数据
function saveBaseInfo(formId) {
	// 数据有限性判断
	if (validateSubmit(formId)) {
		// 保存
		var url = Utils.getRootPath() + '/associator/associator/save';
		$.ajax({
			type : "post",
			url : url,
			data : $('#' + formId).serialize(),
			async : false,
			dataType : 'json',
			success : function(result) {
				if (result.result) {
					$.messager.alert('提示', '保存成功', 'info', function() {
						window.parent.loadDataGrid('associator');
						window.parent.closeWinForm();
					});
				} else if (result.status == "error") {
					$.messager.alert('提示', '保存失败，请联系管理员', 'error');
				}
			}
		});
	}
}

//按条件查询
function searchAassociator(){
	
	var queryParams = {
		'associator.associator_Name':$('#associator_Name').val(),
		'associator.associator_Mobilephone':$('#associator_Mobilephone').val(),
		'associator.associator_CardNumber':$('#associator_CardNumber').val()
	};
	$('#dg').datagrid('options').queryParams = queryParams;
	$("#dg").datagrid('load');
	
}

//打开编辑页面
function editAssociator() {
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
	var url = Utils.getRootPath() + '/associator/associator/editAssociator?id=' + row.ID;
	add(url,'编辑会员信息',1000,440);
}

//编辑数据
function editBaseInfo(formId) {
	// 数据有限性判断
	if (validateSubmit(formId)) {
		// 保存
		var url = Utils.getRootPath() + '/associator/associator/update';
		$.ajax({
			type : "post",
			url : url,
			data : $('#' + formId).serialize(),
			async : false,
			dataType : 'json',
			success : function(result) {
				if (result.result) {
					$.messager.alert('提示', '保存成功', 'info', function() {
						window.parent.loadDataGrid('associator');
						window.parent.closeWinForm();
					});
				} else {
					$.messager.alert('提示', '保存失败，请联系管理员', 'error');
				}
			}
		});
	}
}

//打开查看页面
function detail(id) {
//	var rows = $('#dg').datagrid('getSelections');
//	if (rows.length == 0) {
//		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
//		return;
//	}
//	if (rows.length > 1) {
//		$.messager.alert('提示', '请选择一条数据进行操作！', 'warning');
//		return;
//	}
//	var row = rows[0];
//	var url = Utils.getRootPath() + '/associator/associator/detailAssociator?id='+ row.ID;
	var url = Utils.getRootPath() + '/associator/associator/detailAssociator?id='+ id;
	add(url, '查看会员信息',1000,400);
}

//删除
function deleteAssociator() {
	var rows = $('#dg').datagrid('getSelections');
		$.messager.confirm('提示', '是否确认删除？', function(r) {
			if (r) {
				var ids = getRowsInfo();
					var url = Utils.getRootPath()
							+ "/associator/associator/importMember";
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
													.loadDataGrid('associator');
										});
							} else {
								$.messager.alert('提示', '删除失败！', 'error');
							}
						}
					});
			}
		});

}

//拼接ID
var getRowsInfo = function(){
	var ids = [];
	var rows = $('#dg').datagrid('getSelections');
	for(var i=0; i<rows.length; i++){
		ids.push(rows[i].ID);
	}
	return ids;
};

function searchAassociatorAnalyze(){
	var queryParams={
			'associator.associator_Category':$('#associator_Category').combobox('getValue'),
			'associator.associator_Amount':$('#associator_Amount').combobox('getValue'),
			'associator.associator_ConsumeAmount':$('#associator_ConsumeAmount').combobox('getValue'),
			'associator.associator_RegisterStore':$('#associator_RegisterStore').combobox('getValue')
		};
	$('#dg').datagrid('options').queryParams = queryParams;
	$("#dg").datagrid('load');
}