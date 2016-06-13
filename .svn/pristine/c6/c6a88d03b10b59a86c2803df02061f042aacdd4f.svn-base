//添加
function add(url, title, x, y) {
	window.parent.initAdd(url, title, x, y);
}

function add1(url, title, x, y) {
	window.parent.initAdd2(url, title, x, y);
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
	var url = Utils.getRootPath() + '/order/orders/gotoEditOrders?id=' + row.ID;
	add(url, title, x, y);
}

// 编辑
function editRefund(title, x, y) {
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
	var url = Utils.getRootPath() + '/order/orders/gotoEditRefund?id=' + row.ID
			+ '&orderId=' + row.ORDER_ID;
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
function saveBaseInfo(formId) {
	// 数据有限性判断
	if (validateSubmit(formId)) {
		// 保存
		var url = Utils.getRootPath() + '/order/orders/save';
		$.ajax({
			type : "post",
			url : url,
			data : $('#' + formId).serialize(),
			async : false,
			dataType : 'json',
			success : function(result) {
				if (result.status == "OK") {
					$.messager.alert('提示', '保存成功', 'info', function() {
						// loadDataGrid();
						window.parent.closeWinForm();
					});
				} else if (result.status == "error") {
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
		var url = Utils.getRootPath() + '/order/orders/update';
		$.ajax({
			type : "post",
			url : url,
			data : $('#' + formId).serialize(),
			async : false,
			dataType : 'json',
			success : function(result) {
				if (result.status == "OK") {
					$.messager.alert('提示', '修改成功', 'info', function() {
						// loadDataGrid();
						window.parent.closeWinForm();
					});
				} else if (result.status == "error") {
					$.messager.alert('提示', '修改失败，请联系管理员!', 'error');
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
	searchData();
}

function PushInfo(formId) {
	// 数据有限性判断
	if (validateSubmit(formId)) {
		// 保存
		$("#ddialog").dialog('open');
		var url = Utils.getRootPath() + '/order/orders/pushGetInfo';
		$.ajax({
			type : "post",
			url : url,
			data : $('#' + formId).serialize(),
			async : true,
			dataType : 'json',
			success : function(result) {
				$("#ddialog").dialog('close');
				if (result.message == "保存成功") {
					$.messager.alert('提示', '订单拉取完成', 'info', function() {
						window.parent.loadDataGrid('orders');
						window.parent.closeWinForm();
					});
				} else {
					$.messager.alert('提示', '订单拉取失败，请联系管理员!', 'error');
				}
			}
		});
	}
}

function setTime(formId) {
	// 数据有限性判断
	if (validateSubmit(formId)) {
		// 保存
		var url = Utils.getRootPath() + '/order/orders/setOrderTime';
		$.ajax({
			type : "post",
			url : url,
			data : $('#' + formId).serialize(),
			async : false,
			dataType : 'json',
			success : function(result) {
				if (result.message == "保存成功") {
					$.messager.alert('提示', '时间设置成功', 'info', function() {
						window.parent.closeWinForm();
					});
				} else {
					$.messager.alert('提示', '时间设置失败，请联系管理员!', 'error');
				}
			}
		});
	}
}

// // 打开新增供应商商品信息页面
// function openOrderItem(title, x, y) {
// var rows = $('#dg').datagrid('getSelections');
// if (rows.length == 0) {
// $.messager.alert('提示', '请选择需要操作的数据！', 'warning');
// return;
// }
// if (rows.length > 1) {
// $.messager.alert('提示', '请选择一条数据进行操作！', 'warning');
// return;
// }
// var row = rows[0];
// var url = Utils.getRootPath() + '/order/orders/itemList?id=' + row.ID;
// add(url, title, x, y);
// }

function openOrderItem1(id) {
	var url = Utils.getRootPath() + '/order/orders/itemList1?id=' + id;
	add(url, '订单商品详情', 1000, 550);
}

function openRefundItem(id) {
	var url = Utils.getRootPath() + '/order/orders/RefundItem?id=' + id;
	add(url, '退款单详情', 1000, 550);
}

function openOrderItem(id) {
	var url = Utils.getRootPath() + '/order/orders/itemList?id=' + id;
	add(url, '订单商品详情', 1000, 550);
}

function openProcessItem(id) {
	var url = Utils.getRootPath() + '/order/orders/processItemList?id=' + id;
	add(url, '截单订单商品', 1000, 550);
}

// 转到数值精度选择的页面
function moneyDigits(title, x, y) {
	var url = Utils.getRootPath() + '/order/orders/gotoOrdersDigits';
	add(url, '数值精度调整', 300, 200);
}

// 将需要转换的位数传给后台
function saveDigits() {
	var numVal = $('#digitsValue').combobox('getValue');
	var url = Utils.getRootPath() + '/order/orders/saveDigits?numVal=' + numVal;
	$.ajax({
		type : "post",
		url : url,
		async : true,
		dataType : 'json',
		success : function(result) {
			if (result.result) {
				$.messager.alert('提示', '设置成功,请重新登录', 'info', function() {
					var url = Utils.getRootPath() + "/login/logout";
					var b = window.top;
					b.location.href = url;
				});
			} else if (result.status == "error") {
				$.messager.alert('提示', '保存失败，请联系管理员!', 'error');
			}
		}
	});
}

// 根据订单状态生成采购订单
function createPurchaseOrder() {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '至少选择一条数据进行采购！', 'warning');
		return;
	}
	var ids = getRowsId();
	var nums = getRowsNum();
	var atualNums = getRowsAtualNum();
	var url = Utils.getRootPath()
			+ '/purchase/purchaseOrder/pushGetPurchaseOrder';
	$.messager.confirm('确认 ', '确定要新增采购订单 ？', function(r) {
		if (r) {
			$("#ddialog").dialog('open');
			$.ajax({
				type : "post",
				url : url,
				data : 'ids=' + ids + '&nums=' + nums + '&atualNums='
						+ atualNums,
				async : true,
				dataType : 'json',
				success : function(result) {
					$("#ddialog").dialog('close');
					if (result.message == "保存成功") {
						$.messager.alert('提示', '拉取成功', 'info', function() {
							window.parent.closeWinForm();
							window.parent.loadDataGrid('purchaseOrders');
						});
					} else {
						$.messager.alert('提示', '拉取失败，请联系管理员!', 'error');
					}
				}
			});
		}
	});
}

var getRowsId = function() {
	var ids = [];
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	for (var i = 0; i < rows.length; i++) {
		ids.push(rows[i].GOODSFILE_ID);
	}
	return ids;
};

var getRowsNum = function() {
	var num = [];
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	for (var i = 0; i < rows.length; i++) {
		num.push(rows[i].storeneedNums);
	}
	return num;
};
var getRowsAtualNum = function() {
	var num = [];
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	for (var i = 0; i < rows.length; i++) {
		num.push(rows[i].lossStore);
	}
	return num;
};

var getRowsPrice = function() {
	var price = [];
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	for (var i = 0; i < rows.length; i++) {
		price.push(rows[i].GOODS_PURCHASE_PRICE);
	}
	return price;
};

var getRowsMoney = function() {
	var money = [];
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	for (var i = 0; i < rows.length; i++) {
		money.push(rows[i].TOTALPRICE);
	}
	return money;
};
// 计算数量
function actualNum(val, rec) {
	var str = rec.GOODS_SPECIFICATIONS;
	if (str == "" || str == null || str == "undefined") {
		return val = "0.00";
	}
	var str2 = "";
	var a = 0;
	if (str != null) {
		a = str.length;
	}
	if (str != null && str != "") {
		for (var i = 0; i < a; i++) {
			if ((str.charAt(i) >= '0' && str.charAt(i) <= '9')
					|| str.charAt(i) == '.') {
				str2 += str.charAt(i);
			}
			;
		}
		;
	}
	;
	var num = parseFloat(str2);
	var nums = rec.NUMS;
	num = nums * num;
	return num.toFixed(2);
};
// 计算总价
function actualPrice(val, rec) {
	var num = rec.NUMS;
	var price = rec.G_PRICE;
	price = num * price;
	return price;
}

function selectoption() {
	var optionvalue = $('#select_order').val();
	if (optionvalue == "1") {
		$("#td_order")
				.html(
						'<input onkeydown="if(event.keyCode==13){event.keyCode=0;return false}" style="width:100%;" id="orderNum" name="orderNum" type="text" class="easyui-textbox"/>');
	} else if (optionvalue == "2") {
		$("#td_order")
				.html(
						'<input onkeydown="if(event.keyCode==13){event.keyCode=0;return false}" style="width:100%;" id="associator_CardNumber" name="zcOrder.associator.associator_CardNumber" type="text" class="easyui-textbox"/>');
	} else if (optionvalue == "3") {
		$("#td_order")
				.html(
						'<input onkeydown="if(event.keyCode==13){event.keyCode=0;return false}" style="width:100%;" id="consignee" name="consignee" type="text" class="easyui-textbox"/>');
	} else {
		$("#td_order")
				.html(
						'<input onkeydown="if(event.keyCode==13){event.keyCode=0;return false}" style="width:100%;" id="cansignPhone" name="cansignPhone" type="text" class="easyui-textbox"/>');
	}
}

function handle() {
	// var id = $('#goodsFileId').val();
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	var ids = "";
	for (var i = 0; i < rows.length; i++) {
		ids += rows[i].GOODSFILE_ID + ",";
	}
	var url = Utils.getRootPath() + '/order/orders/handle?ids=' + ids;
	$.ajax({
		type : "post",
		url : url,
		async : true,
		dataType : 'json',
		success : function(result) {
			if (result.result) {
				$.messager.alert('提示', '已处理', 'info', function() {
					window.parent.closeWinForm();
					$('#dg').datagrid('reload');
				});
			} else {
				$.messager.alert('提示', '处理失败，请联系管理员!', 'error');
			}
		}
	});
}

// 查看详细
function openDetail(id) {
	var url = Utils.getRootPath() + '/order/orders/openDetail?id=' + id;
	var title = "订单详情";
	add(url, title, 1300, 560);
}

//查看详细
function openOrdersDetail(id) {
	var url = Utils.getRootPath() + '/order/orders/openOrdersDetail?id=' + id;
	var title = "订单详情";
	add(url, title, 1300, 560);
}

// 客服处理详情
function openServiceDetail(id) {
	var url = Utils.getRootPath() + '/order/orders/openServiceDetail?id=' + id;
	var title = "订单详情";
	add(url, title, 1300, 560);
}

// 客服处理订单商品
function showOrders(id) {
	var url = Utils.getRootPath() + '/order/orders/showOrders?id=' + id;
	var title = "订单商品详情";
	add1(url, title, 1200, 540);
}

// 编辑
function editService(title, x, y) {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	var row = rows[0];
	var url = Utils.getRootPath() + '/order/orders/gotoEditService?id='
			+ row.GOODSFILE_ID;
	add(url, title, x, y);
}

// 客服处理
function operation(id, nums, actualNums, delflag) {
	if (delflag == "9") {
		$.messager.alert('提示', '该商品已提交客服！', 'warning');
		return;
	}
	var url = Utils.getRootPath() + '/order/orders/operation?id=' + id
			+ "&nums=" + nums + "&actualNums=" + actualNums;
	$.messager.confirm('确认 ', '确定提交客服 ？', function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : url,
				async : true,
				dataType : 'json',
				success : function(result) {
					if (result.message == "保存成功") {
						$.messager.alert('提示', '已提交客服', 'info', function() {
							window.parent.closeWinForm();
							$('#dg').datagrid('reload');
						});
					} else if (result.message == "更新成功") {
						$('#dg').datagrid('reload');
					} else {
						$.messager.alert('提示', '提交客服失败，请联系管理员!', 'error');
					}
				}
			});
		}
	});
}

// 客服处理移除订单商品
function removed() {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	var ids = "";
	for (var i = 0; i < rows.length; i++) {
		ids = rows[i].ID + ",";
	}
	var url = Utils.getRootPath() + '/order/orders/removed?ids=' + ids;
	$.messager.confirm('确认 ', '确定要删除 ？', function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : url,
				async : true,
				dataType : 'json',
				success : function(result) {
					if (result.result) {
						$.messager.alert('提示', '删除成功', 'info', function() {
							window.parent.closeWinForm();
							$('#dg').datagrid('reload');
						});
					} else {
						$.messager.alert('提示', '撤销失败，请联系管理员!', 'error');
					}
				}
			});
		}
	});
}

// 客服处理撤销商品
function revoke() {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	var ids = "";
	for (var i = 0; i < rows.length; i++) {
		ids = rows[i].GOODSFILE_ID + ",";
	}
	var url = Utils.getRootPath() + '/order/orders/revoke?ids=' + ids;
	$.messager.confirm('确认 ', '确定撤销客服处理 ？', function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : url,
				async : true,
				dataType : 'json',
				success : function(result) {
					if (result.result) {
						$.messager.alert('提示', '撤销成功', 'info', function() {
							window.parent.closeWinForm();
							$('#dg').datagrid('reload');
						});
					} else {
						$.messager.alert('提示', '撤销失败，请联系管理员!', 'error');
					}
				}
			});
		}
	});
}

// 客服处理完成
function adopt() {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	var ids = "";
	for (var i = 0; i < rows.length; i++) {
		ids = rows[i].ID + ",";
	}
	var goodfileId = $('#goodfileId').val();
	var url = Utils.getRootPath() + '/order/orders/adopt?ids=' + ids
			+ "&goodfileId=" + goodfileId;
	$.messager.confirm('确认 ', '确定处理通过 ？', function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : url,
				async : true,
				dataType : 'json',
				success : function(result) {
					if (result.result) {
						$.messager.alert('提示', '选中订单已处理', 'info', function() {
							window.parent.closeWinForm();
							$('#dg').datagrid('reload');
						});
					} else {
						$.messager.alert('提示', '处理失败，请联系管理员!', 'error');
					}
				}
			});
		}
	});
}

// 订单作废
function invalid() {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	var ids = "";
	for (var i = 0; i < rows.length; i++) {
		ids = rows[i].ID + ",";
	}
	var url = Utils.getRootPath() + '/order/orders/invalid?ids=' + ids;
	$.messager.confirm('确认 ', '确定作废 ？', function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : url,
				async : true,
				dataType : 'json',
				success : function(result) {
					if (result.result) {
						$.messager.alert('提示', '选中订单已作废', 'info', function() {
							window.parent.closeWinForm();
							$('#dg').datagrid('reload');
						});
					} else {
						$.messager.alert('提示', '处理失败，请联系管理员!', 'error');
					}
				}
			});
		}
	});
}

// 树形菜单
function tree() {
	var queryParams = null;
	$('#MyTree').tree({
		checkbox : false,
		url : Utils.getRootPath() + "/order/orders/getTreeData",
		onClick : function(node) {
			if ($('#MyTree').tree('isLeaf', node.target)) {
				queryParams = {
					'goodsFile.goodsType.id' : node.id
				};
			} else {
				queryParams = null;
			}
			$('#dg').datagrid('options').queryParams = queryParams;
			$("#dg").datagrid('load');
		}
	});
}

// 初始化父级菜单下拉框
function initParentTree() {
	$('#goods_classify').combotree({
		url : Utils.getRootPath() + "/goodsFile/goodsFile/getTreeData",
		onLoadSuccess : function(node, data) {
			var optionValue = $('#goods_classify').attr("optionValue");
			if (optionValue != undefined && optionValue != "") {
				$('#goods_classify').combotree('setValue', optionValue);
			}
		}
	});
}

// 退款通过
function pass() {
	var id = $('#id').val();
	$.messager.confirm('确认 ', '确定退款通过 ？', function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : Utils.getRootPath() + '/order/orders/pass',
				data : {
					id : id
				},
				async : false,
				dataType : 'json',
				success : function(result) {
					if (result.result) {
						$.messager.alert('提示', '退款通过', 'info', function() {
							window.parent.closeWinForm();
						});
					} else {
						$.messager.alert('提示', '处理失败，请联系系统管理员！', 'warning');
					}
				}
			});
		}
		;
	});
};

// 退款通过
function nopass() {
	var id = $('#id').val();
	$.messager.confirm('确认 ', '确定退款不通过 ？', function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : Utils.getRootPath() + '/order/orders/nopass',
				data : {
					id : id
				},
				async : false,
				dataType : 'json',
				success : function(result) {
					if (result.result) {
						$.messager.alert('提示', '退款不通过', 'info', function() {
							window.parent.closeWinForm();
						});
					} else {
						$.messager.alert('提示', '处理失败，请联系系统管理员！', 'warning');
					}
				}
			});
		}
		;
	});
};

