
// 打开要货单明细
function openDispatchingItem(id){
	var url = Utils.getRootPath() + '/dispatching/dispatchings/openDispatchingItem?id=' + id;
	add(url, '要货单详情', 1000, 550);
	
}

//打开审核页面
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
	if (row.STATUS == "2") {
		$.messager.alert('提示', '该订单已通过审核！', 'warning');
		return;
	} else if (row.STATUS == "3") {
		$.messager.alert('提示', '该订单正在处理中！', 'warning');
		return;
	} else if (row.STATUS == "0") {
		$.messager.alert('提示', '该订单还未提交！', 'warning');
		return;
	} else if (row.STATUS == "4") {
		$.messager.alert('提示', '该订单已完成！', 'warning');
		return;
	} else if (row.STATUS == "5") {
		$.messager.alert('提示', '该订单已作废！', 'warning');
		return;
	} else {
		var url = Utils.getRootPath()
				+ '/dispatching/dispatchings/gotoCheck?id=' + row.ID;
		add(url, title, x, y);
	}
}

//审核通过
function checkPass() {
	var id = $('#id').val();
	var url = Utils.getRootPath()
			+ '/dispatching/dispatchings/checkPass?id=' + id;
	$.messager.confirm('确认 ', '确定审核通过？', function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : url,
				async : false,
				dataType : 'json',
				success : function(result) {
					if (result.result) {
						$.messager.alert('提示', '审核完成', 'info', function() {
							window.parent.loadDataGrid('branchRequire');
							window.parent.closeWinForm();
						});
					} else if (result.status == "error") {
						$.messager.alert('提示', '系统错误，请联系管理员', 'error');
					} else {
						$.messager.alert('提示', '审核失败，库存不足', 'info', function(){
							window.parent.loadDataGrid('branchRequire');
							window.parent.closeWinForm();
						});
						
					}
				}
			});
		}
	});
}
// 审核不通过
function CheckItemAjax(id, reason, type) {
	var url = Utils.getRootPath() + '/dispatching/dispatchings/checkNoPass';
	$.ajax({
		type : "post",
		url : url,
		data : {
			'id' : id,
			'reason' : reason,
			'type' : type
		},
		async : false,
		dataType : 'json',
		success : function(result) {
			if (result.message == "保存成功") {
				$.messager.alert('提示', '审核完成', 'info', function() {
					window.parent.loadDataGrid('branchRequire');
					window.parent.closeWinForm();
				});
			} else {
				$.messager.alert('提示', '审核失败，请联系管理员', 'error');
			}
		}
	});
}
// 审核不通过
function checkNoPass(type) {
	var id = $('#id').val();
	if (type == '1') {
		$.messager.prompt('提示信息', '请输入审核未通过的原因：', function(r) {
			if (r) {
				CheckItemAjax(id, r, type);
			} else if (r.length == 0 || r == null || r == 'undefined') {
				r = " ";
				CheckItemAjax(id, r, type);
			}
		});
	}
}