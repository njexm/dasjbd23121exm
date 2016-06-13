//添加
function add(url, title, x, y) {
	window.parent.initAdd(url, title, x, y);
}

function searchUserInfo() {

	var queryParams = {
		'zcUserInfo.userName' : $('#userName').val(),
	};
	$('#dg').datagrid('options').queryParams = queryParams;
	$("#dg").datagrid('reload');
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
	var url = Utils.getRootPath() + '/userInfo/edit?id=' + row.ID;
	add(url, title, x, y);
}
// 权限管理
function openAuthority(title, x, y) {
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
	var url = Utils.getRootPath() + '/userInfo/openAuthority?id=' + row.ID;
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
function saveUserInfo(formId) {
	// 数据有限性判断
	if (validateSubmit(formId)) {
		// 保存
		var province = $('#province').val();
		var city = $('#city').val();
		var country = $('#country').val();
		var street = $('#street').val();
		var branch_name = $('#branch_name').val();
		if (street == null || street == "" || street.length == 0) {
			$.messager.alert('提示', '请填写正确地址！', 'warning');
			return;
		}
		if (province == null || province == "" || province.length == 0) {
			$.messager.alert('提示', '请填写完整的地址！', 'warning');
			return;
		}
		if (country == null || country == "" || country.length == 0) {
			$.messager.alert('提示', '请填写完整的地址！', 'warning');
			return;
		}
		if (city == null || city == "" || city.length == 0) {
			$.messager.alert('提示', '请填写完整的地址！', 'warning');
			return;
		}
		var url = Utils.getRootPath() + '/userInfo/save';
		$.ajax({
			type : "post",
			url : url,
			data : $('#' + formId).serialize(),
			async : false,
			dataType : 'json',
			success : function(result) {
				if (result.message == "保存成功") {
					$.messager.alert('提示', '保存成功', 'info', function() {
						$.messager.confirm("操作提示", "您是否需要再次添加用户",
								function(data) {
									if (data) {
										add(Utils.getRootPath()
												+ '/userInfo/add', '新增用户', 600,
												300);
									} else {
										window.parent.loadDataGrid('userInfo');
										window.parent.closeWinForm();
									}
								});
					});
				} else {
					$.messager.alert('提示', '保存失败，请联系管理员', 'error');
				}
			}
		});
	}
}

// 修改数据
function editUserInfo(formId) {
	// 数据有限性判断
	if (validateSubmit(formId)) {
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
		var url = Utils.getRootPath() + '/userInfo/update';
		$.ajax({
			type : "post",
			url : url,
			data : $('#' + formId).serialize(),
			async : false,
			dataType : 'json',
			success : function(result) {
				if (result.message == "更新成功") {
/*					$.messager.alert('提示', '保存失败，请联系管理员', 'info',function(){
				          window.parent.loadDataGrid('userInfo');
							window.parent.closeWinForm();
							("#dg").datagrid('load');
					});*/
					var msg='修改成功';
					alert_totalQuery(msg);					
				} else if (result.status == "error") {
					$.messager.alert('提示', '保存失败，请联系管理员', 'error');
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

// Iframe弹出框
function roleIframe() {
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
	if (row.BRANCH_NAME_ID == null || row.BRANCH_NAME_ID == 'undefiend'
			|| row.BRANCH_NAME_ID == "") {
		
	}else{
		$.messager.alert('提示', '该用户为分店用户，无法进行角色分配！', 'warning');
		return;
	}

	$("#userId").val(rows[0].USER_ID);
	$("#name_role").textbox('setValue', '');
	// 初始化安装位置数据
	initJSInfo();
	// 打开div
	$('#js_info').dialog('open').dialog('setTitle', '分配角色');

}

function initJSInfo() {
	$('#dg_js').datagrid({
		url : Utils.getRootPath() + "/roleInfo/allList",
		toolbar : "#toolbar1",
		loadMsg : "正在加载数据，请稍等...",
		rownumbers : true,
		fitColumns : true,
		pagination : false,
		pageSize : 10,
		singleSelect : false,
		striped : true,
		nowrap : true,
		queryParams : getQueryParam(),
		columns : [ [ {
			field : 'id',
			checkbox : true,
			width : 20

		}, {
			title : '角色名称',
			field : 'name',
			width : 140,
			align : 'center'
		} ] ],
		onLoadSuccess : function(row) {
			$.post(Utils.getRootPath() + "/userInfo/getUserRolesByUserId", {
				userId : $("#userId").val()
			}, function(rows) {
				rows = $.parseJSON(rows);
				var rowData = row.rows;
				// $("input[value=" + rows[i].rightId +
				// "]").attr("checked","checked");
				for (var i = 0; i < rows.length; i++) {
					$.each(rowData, function(idx, val) {// 遍历JSON
						if (val.id == rows[i].rightId) {
							$("#dg_js").datagrid("selectRow", idx);// 如果数据行为已选中则选中改行
						}
					});
				}
			});
		}
	});

}

function getQueryParam() {
	return {
		user_name : $("#name_role").val()
	};
}

function findJSInfo() {
	$('#dg_js').datagrid('load', getQueryParam());
}

function selectJSInfo() {
	var rows = $('#dg_js').datagrid('getSelections');
	if (rows.length <= 0) {
		$.messager.alert('警告', '请选择数据！');
		return;
	}
	var userId = $("#userId").val();
	var roleId = '';

	for (var i = 0; i < rows.length; i++) {
		if (rows.length == i + 1) {
			roleId += rows[i].id;
		} else {
			roleId += rows[i].id + ",";
		}
	}
	$.ajax({
		async : false,
		url : Utils.getRootPath() + "/userInfo/updateUserRole?userId=" + userId
				+ "&roleId=" + roleId,
		type : "post",
		data : {

		},
		dataType : "json",
		success : function(data) {
			list = data;
		}
	});
	//$.messager.alert('提示', '保存成功！');	
	var msg='保存成功';
	alert_totalQuery2(msg);
    //$('#js_info').dialog('close');
	// var url = Utils.getRootPath() + "/login/logout";
	// parent.window.location.href = url;
}

// 删除
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
	$.messager.confirm('确认 ', '确定要删除 ？', function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : Utils.getRootPath() + '/userInfo/delete',
				data : {
					id : ids
				},
				async : false,
				dataType : 'json',
				success : function(result) {
					if (result.result) {
						/*$.messager.alert('提示', '删除成功', 'info', function() {
							window.parent.closeWinForm();
							$("#dg").datagrid('load');
						});*/
						var msg='删除成功';
						alert_totalQuery(msg);	
					} else {
						$.messager.alert('提示', '该用户存在任务，不可删除', 'warning',
								function() {
									$("#dg").datagrid('load');
								});
					}
				}
			});
		}
		;
	});
};

//alert延迟5秒关闭
function alert_totalQuery(msg){  
    var interval;  
    var time=1000;  
    var x=5;  
    $.messager.alert(' ','<font size=\"2\" color=\"#666666\"><strong>'+msg+'</strong></font>','info',function(){// loadDataGrid();    	
    });  
    $(".messager-window .window-header .panel-title").append("系统提示（"+x+"秒后自动关闭）");  
    interval=setInterval(fun,time);  
    function fun(){  
        --x;  
        if(x==0){  
          clearInterval(interval);  
          $(".messager-body").window('close'); 
          window.parent.loadDataGrid('userInfo');
			window.parent.closeWinForm();
			("#dg").datagrid('load');
        }  
        $(".messager-window .window-header .panel-title").text("");  
        $(".messager-window .window-header .panel-title").append("系统提示（"+x+"秒后自动关闭）");  
    }  
}  
//alert延迟5秒关闭
function alert_totalQuery2(msg){  
    var interval;  
    var time=1000;  
    var x=5;  
    $.messager.alert(' ','<font size=\"2\" color=\"#666666\"><strong>'+msg+'</strong></font>','info',function(){// loadDataGrid();    	
    });  
    $(".messager-window .window-header .panel-title").append("系统提示（"+x+"秒后自动关闭）");  
    interval=setInterval(fun,time);  
    function fun(){  
        --x;  
        if(x==0){  
          clearInterval(interval);  
          $(".messager-body").window('close');
          $('#js_info').dialog('close');
        }  
        $(".messager-window .window-header .panel-title").text("");  
        $(".messager-window .window-header .panel-title").append("系统提示（"+x+"秒后自动关闭）");  
    }  
} 

