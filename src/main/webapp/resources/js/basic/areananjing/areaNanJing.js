//添加
function add(url, title, x, y) {
	window.parent.initAdd(url, title, x, y);
}

//按条件查询
function searchData(){
	
	var queryParams = {
			'areaNanJing.areaName':$('#areaName').val(),
			'areaNanJing.realname':$('#realname').val(),
			'areaNanJing.address':$('#address').val()
		}; 
	$('#dg').treegrid('options').queryParams = queryParams;
	$("#dg").treegrid('load');
	
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

//打开新增页面
function addAreaNanJing() {
	//获取网页中id名是'dg'的所有行的值
	var rows = $('#dg').datagrid('getSelections');
	//判断选择的行数
	if (rows.length == 0) {
		var url= Utils.getRootPath() + '/areaNanJing/addAreaNanJing?id='+ 0;
		add(url,'新增区域管理',350,380);
	}else if (rows.length > 1) {
		$.messager.alert('提示', '请选择一条数据进行操作！', 'warning');
		return;
	}else {
	//取第一行的值
	var row = rows[0];
	var url= Utils.getRootPath() + '/areaNanJing/addAreaNanJing?id=' +row.ID;
	add(url,'新增区域管理',350,380);
	}
}

//新增数据
function saveAreaNanJing(formId) {
	// 数据有限性判断
	if (validateSubmit(formId)) {
		// 保存
		var url = Utils.getRootPath() + '/areaNanJing/save';
		$.ajax({
			type : "post",
			url : url,
			data : $('#' + formId).serialize(),
			async : false,
			dataType : 'json',
			success : function(result) {
				if (result.result) {
					$.messager.alert('提示', '保存成功', 'info', function() {
						window.parent.loadDataGrid('areaNanJing');
						window.parent.closeWinForm();
					});
				} else if (result.status == "error") {
					$.messager.alert('提示', '保存失败，请联系管理员', 'error');
				}
			}
		});
	}
}

//打开编辑页面
function editAreaNanJing() {
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
	var url = Utils.getRootPath() + '/areaNanJing/editAreaNanJing?id=' + row.ID;
	add(url,'编辑区域信息',350,400);
}

//编辑数据
function editBaseInfo(formId) {
	// 数据有限性判断
	if (validateSubmit(formId)) {
		// 保存
		var url = Utils.getRootPath() + '/areaNanJing/update';
		$.ajax({
			type : "post",
			url : url,
			data : $('#' + formId).serialize(),
			async : false,
			dataType : 'json',
			success : function(result) {
				if (result.result) {
					$.messager.alert('提示', '保存成功', 'info', function() {
						window.parent.loadDataGrid('areaNanJing');
						window.parent.closeWinForm();
					});
				} else {
					$.messager.alert('提示', '保存失败，请联系管理员', 'error');
				}
			}
		});
	}
}


//删除
function deleteAreaNanJing() {
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
							+ "/areaNanJing/delete?ids=" + ids;
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
													.loadDataGrid('areaNanJing');
										});
							} else {
								$.messager.alert('提示', '删除失败！', 'error');
							}
						}
					});
				}
			}
		});

	};
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


//初始化父级菜单下拉框
function initParentTree(){
	$('#parentID').combotree({    
	    url: Utils.getRootPath() + "/areaNanJing/getTreeData",    
	    onLoadSuccess: function(node, data){
	    	var optionValue = $('#parentID').attr("optionValue");
	    	if(optionValue!=undefined && optionValue != ""){
	    		$('#parentID').combotree('setValue', optionValue);
	    	}
	    }
	});  
}







