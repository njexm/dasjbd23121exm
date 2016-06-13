//打开新增页面
function openAdd() {
	var url = Utils.getRootPath() + '/supplierCost/supplierCostDo/gotoAdd';
	window.parent.initAdd(url, "新增供应商费用单", 1000, 535);
}//编辑
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
	if(row.AUDIT_STATUS == '2'){
		$.messager.alert('提示', '该预付款单已审核，不可编辑！', 'warning');
		return;
	}
	if(row.AUDIT_STATUS == '1'){
		$.messager.alert('提示', '该预付款单已提交审核，不可编辑！', 'warning');
		return;
	}
	var url = Utils.getRootPath() + '/supplierCost/supplierCostDo/gotoEdit?id=' + row.ID;
	add(url,title,x,y);
}


//查看基本信息
function openSupplierCost(id) {
	var url = Utils.getRootPath()+ '/supplierCost/supplierCostDo/gotoDetail?id=' + id;
	add(url, '供应商费用单详情', 1000, 550);
}

//新增一条数据
function addRecord() {
	var id = $('#supplierCostId').val();
	var data = $('#dg').datagrid('getData');
	for ( var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit',i);
	}
	var rows = JSON.stringify(data.rows);
	var url = Utils.getRootPath() + '/supplierCost/supplierCostDo/addRecord?';
	$.ajax({
		type : "post",
		url : url,
		data:{
			'id':id,
			'rows':rows
		},
		async : false,
		dataType : 'json',
		success : function(result) {
			if (result.result) {
				$('#dg').datagrid('reload');
				for ( var i = 0; i < data.rows.length; i++) {
					$('#dg').datagrid('beginEdit',i);
				}
			} else if (result.status == "error") {
				$('#dg').datagrid('reload');
			}
		}
	});
}

//新增数据
function save(formId) {
	// 数据有限性判断
	if (validateSubmit(formId)) {
		// 保存
		var id = $('#supplierCostId').val();
		var provider =$('#provider').combobox('getValue');
		if(provider=="" || provider==null){
			$.messager.alert('提示', '请选择供应商', 'warning');
			return;
		}
		var rows = $('#dg').datagrid('getRows');
		if(rows.length == 0 || rows == null){
			$.messager.alert('提示', '费用类型不能为空！', 'warning');
			return;
		}
		//提交前，所有行的编辑状态取消
		for ( var i = 0; i < rows.length; i++) {
			$('#dg').datagrid('endEdit',i);
			if(rows[i].MONEY=="" || rows[i].MONEY==null){
				$('#dg').datagrid('beginEdit',i);
				$.messager.alert('提示', '请填写金额！', 'warning');
				return;
			}
	    }
		var ids = "";
		var remark = "";
		var money = "";
		var costName = "";
		for (var i = 0; i < rows.length; i++) {
			if(i == rows.length-1){
				ids += rows[i].ID;
				money += rows[i].MONEY;
				remark += rows[i].REMARKS;
				costName += rows[i].COST_NAME;
			}else{
				ids += rows[i].ID + ",";
				money += rows[i].MONEY + ",";
				remark += rows[i].REMARKS + ",";
				costName += rows[i].COST_NAME + ",";
			}
		}
		remark = encodeURI(encodeURI(remark));
		var url = Utils.getRootPath() + '/supplierCost/supplierCostDo/save?ids='
					+ids+'&remark='+remark+'&money='+money+'&id='+id+'&costName='+costName;
		for ( var i = 0; i < rows.length; i++) {
			$('#dg').datagrid('beginEdit',i);
	    }
		$.ajax({
			type : "post",
			url : url,
			data : $('#' + formId).serialize(),
			async : false,
			dataType : 'json',
			success : function(result) {
				if (result.result && id.length==0) {
					$.messager.alert('提示', '新增成功', 'info', function() {
						window.parent.loadDataGrid('advancePayment');
						window.parent.closeWinForm();
					});
				}else if (result.result && id.length != 0) {
					$.messager.alert('提示', '修改成功', 'info', function() {
						window.parent.loadDataGrid('advancePayment');
						window.parent.closeWinForm();
					});
				}else if (result.status == "error") {
					$.messager.alert('提示', '新增失败，请联系管理员！', 'error');
				}
			}
		});
	}
}

//删除新增页面datagrid
function destroyInfo() {
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
	$.ajax({
		type : "post",
		url : Utils.getRootPath() + '/supplierCost/supplierCostDo/deleteItem',
		data : {
			id : ids
		},
		async : false,
		dataType : 'json',
		success : function(result) {
			$("#dg").datagrid('load');
		}
	});
		
};

//删除list页面数据
function removeData() {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	var ids = "";
	if(rows[0].AUDIT_STATUS == '2'){
		$.messager.alert('提示', '该费用单已通过审核，不可删除！', 'warning');
		return;
	}
	if(rows[0].AUDIT_STATUS == '1'){
		$.messager.alert('提示', '该费用单已提交过审核，不可删除！', 'warning');
		return;
	}
	if(rows[0].AUDIT_STATUS == '3'){
		$.messager.alert('提示', '该费用单待处理，不可删除！', 'warning');
		return;
	}
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
				url : Utils.getRootPath() + '/supplierCost/supplierCostDo/delete',
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
						$.messager.alert('提示', '删除失败，请联系管理员！', 'error');
						$("#dg").datagrid('load');
					}
				}
			});
		}
		;
	});
};

//打开审核页面
function openCheck(title, x, y) {
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
	if(row.AUDIT_STATUS == 2){
		$.messager.alert('提示', '该费用单已通过审核，不可再次审核！', 'warning');
		return;
	}else if(row.AUDIT_STATUS == 3){
		$.messager.alert('提示', '该费用单审核未通过，请处理后再提交审核！', 'warning');
		return;
	}else if(row.AUDIT_STATUS == 0){
		$.messager.alert('提示', '该费用单未提交审核！', 'warning');
		return;
	}
	var url = Utils.getRootPath() + '/supplierCost/supplierCostDo/gotoCheck?id=' + row.ID;
	add(url,title,x,y);
}

//审核通过
function checkPass() {
	var id = $('#supplierCostId').val();
	var url = Utils.getRootPath()+ '/supplierCost/supplierCostDo/checkPass?id='+id;
	$.messager.confirm('确认 ', '确定审核通过 ？', function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : url,
				async : false,
				dataType : 'json',
				success : function(result) {
					if (result.result) {
						$.messager.alert('提示', '审核通过！', 'info', function() {
							window.parent.loadDataGrid('purchaseReturn');
							window.parent.closeWinForm();
						});
					} else if (result.status == "error") {
						$.messager.alert('提示', '系统错误，请联系管理员！', 'error');
					}
				}
			});
		}
	});
}

//审核不通过
function check(type) {
	var id = $('#supplierCostId').val();
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

//审核不通过
function CheckItemAjax(id, reason, type) {
	var url = Utils.getRootPath()
			+ '/supplierCost/supplierCostDo/check';
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
			if (result.message == "更新成功") {
				$.messager.alert('提示', '审核完成', 'info', function() {
					window.parent.loadDataGrid('purchaseOrders');
					window.parent.closeWinForm();
				});
			} else {
				$.messager.alert('提示', '审核失败，请联系管理员！', 'error');
			}
		}
	});
}

function toCheck(formId){
	// 数据有限性判断
	if (validateSubmit(formId)) {
		// 保存
		var id = $('#supplierCostId').val();
		var provider =$('#provider').combobox('getValue');
		if(provider=="" || provider==null){
			$.messager.alert('提示', '请选择供应商', 'warning');
			return;
		}
		var rows = $('#dg').datagrid('getRows');
		if(rows.length == 0 || rows == null){
			$.messager.alert('提示', '费用类型不能为空！', 'warning');
			return;
		}
		//提交前，所有行的编辑状态取消
		for ( var i = 0; i < rows.length; i++) {
			$('#dg').datagrid('endEdit',i);
			if(rows[i].MONEY=="" || rows[i].MONEY==null){
				$('#dg').datagrid('beginEdit',i);
				$.messager.alert('提示', '请填写金额！', 'warning');
				return;
			}
			if(rows[i].COST_NAME=="" || rows[i].COST_NAME==null){
				$('#dg').datagrid('beginEdit',i);
				$.messager.alert('提示', '请选择费用类型！', 'warning');
				return;
			}
	    }
		var ids = "";
		var remark = "";
		var money = "";
		var costName = "";
		for (var i = 0; i < rows.length; i++) {
			if(i == rows.length-1){
				ids += rows[i].ID;
				money += rows[i].MONEY;
				remark += rows[i].REMARKS;
				costName += rows[i].COST_NAME;
			}else{
				ids += rows[i].ID + ",";
				money += rows[i].MONEY + ",";
				remark += rows[i].REMARKS + ",";
				costName += rows[i].COST_NAME + ",";
			}
		}
		remark = encodeURI(encodeURI(remark));
		var url = Utils.getRootPath() + '/supplierCost/supplierCostDo/toCheck?ids='
					+ids+'&remark='+remark+'&money='+money+'&id='+id+'&costName='+costName;
		for ( var i = 0; i < rows.length; i++) {
			$('#dg').datagrid('beginEdit',i);
	    }
		var status=$('#status').val();
		if(status==1)
		{
			$.messager.alert('提示', '该费用单已提交审核，不可再次提交！', 'warning');
			return;
		}else if(status==2)
		{
			$.messager.alert('提示', '该费用单审核已通过，不可再次提交！', 'warning');
			return;
		}
		$.messager.confirm('确认 ', '确定提交审核？', function(r) {
			if (r) {
				$.ajax({
					type : "post",
					url : url,
					data : $('#' + formId).serialize(),
					async : false,
					dataType : 'json',
					success : function(result) {
						if (result.result) {
							$.messager.alert('提示', '提交成功', 'info', function() {
								window.parent.loadDataGrid('purchaseReturn');
								window.parent.closeWinForm();
							});
						} else if (result.status == "error") {
							$.messager.alert('提示', '提交失败，请联系管理员！', 'error');
							$("#dg").datagrid('load');
						}
					}
				});
			};
		});
	}
}
