// 新增页面导出
function exportExcelAdd() {
	
	var data = $('#dg').datagrid('getData');
	var idStr = "";
	
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		$('#dg').datagrid('beginEdit', i);
		idStr += data.rows[i].ID + ",";
		
	}
	var url = Utils.getRootPath() + '/exportExcel/exportExcelSupplierQuery?idStr=' + idStr ;
	$('#myBatch1').form('submit', {
		url : url,
		dataType : 'html',
		onSubmit : function() {
		},
		success : function(data) {
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
	