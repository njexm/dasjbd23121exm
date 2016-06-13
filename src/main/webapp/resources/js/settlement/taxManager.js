//打开新增页面
function openAdd() {
	var url = Utils.getRootPath() + '/settlement/taxManager/openAdd';
	window.parent.initAdd(url, "新增发票信息", 1000, 600);
}

function openTest() {
	var url = Utils.getRootPath() + '/settlement/taxManager/openTest';
	window.parent.initAdd(url, "新增发票信息", 1000, 550);
}
// 新增数据
function saveTaxManager(formId) {
	var datevalue = $('#createDate').datebox('getValue');
	var createMan = $('#createMan').combobox('getValue');
	if(datevalue.length==0){
		$.messager.alert('提示', '请选择或填写日期', 'warning');
		return;
	}
	if(createMan.length==0){
		$.messager.alert('提示', '请选择或填写开票人姓名', 'warning');
		return;
	}
	// 数据有限性判断
	if (validateSubmit(formId)) {
		// 保存
		var url = Utils.getRootPath()
				+ '/settlement/taxManager/saveTaxManager?datevalue='
				+ datevalue;
		$.ajax({
			type : "post",
			url : url,
			data : $('#' + formId).serialize(),
			async : false,
			dataType : 'json',
			success : function(result) {
				if (result.result) {
					$.messager.alert('提示', '新增成功', 'info', function() {
						window.parent.loadDataGrid('taxManager');
						window.parent.closeWinForm();
					});
				} else if (result.status == "error") {
					$.messager.alert('提示', '新增失败，请联系管理员', 'error');
				}
			}
		});
	}
}

function deleteTaxManager() {
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
				url : Utils.getRootPath()
						+ '/settlement/taxManager/deleteTaxManager',
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

function openTaxManager(id) {
	var url = Utils.getRootPath()
			+ '/settlement/taxManager/openTaxManagerDetail?id=' + id;
	window.parent.initAdd(url, "发票明细", 1000, 550);
}

function openEdit() {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	var id = rows[0].ID;
	var url = Utils.getRootPath()
			+ '/settlement/taxManager/openTaxManagerEdit?id=' + id;
	window.parent.initAdd(url, "编辑发票", 1000, 600);
}

function updateTaxManager(formId) {
	var id = $('#id').val();
	var createMan = $('#createMan').combobox('getValue');
	var datevalue = $('#createDate').datebox('getValue');
	if(datevalue.length==0){
		$.messager.alert('提示', '请选择或填写日期', 'warning');
		return;
	}
	if(createMan.length==0){
		$.messager.alert('提示', '请选择或填写开票人姓名', 'warning');
		return;
	}
	// 数据有限性判断
	if (validateSubmit(formId)) {
		// 保存
		var url = Utils.getRootPath()
				+ '/settlement/taxManager/updateTaxManager?id=' + id;
		$.ajax({
			type : "post",
			url : url,
			data : $('#' + formId).serialize(),
			async : false,
			dataType : 'json',
			success : function(result) {
				if (result.result) {
					$.messager.alert('提示', '保存成功', 'info', function() {
						window.parent.loadDataGrid('taxManager');
						window.parent.closeWinForm();
					});
				} else if (result.status == "error") {
					$.messager.alert('提示', '保存失败，请联系管理员', 'error');
				}
			}
		});
	}
}