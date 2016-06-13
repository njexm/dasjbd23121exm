//查询
function searchUserInfo() {

	var queryParams = {
			'branchIdInfo.branch_name.branch_name':$('#branch_name').val(),
	};
	$('#dg').datagrid('options').queryParams = queryParams;
	$("#dg").datagrid('reload');
}

//清空查询条件
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


