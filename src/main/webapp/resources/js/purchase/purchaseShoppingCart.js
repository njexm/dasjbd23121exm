//添加商品信息到购物车
function addToShoppingCart(id, orderNum, serialnumber, specifications) {
	var str = "";
	var a = 0;
	if (str != null) {
		a = specifications.length;
	}
	if (specifications != null && specifications != "") {
		for (var i = 0; i < a; i++) {
			if ((specifications.charAt(i) >= '0' && specifications.charAt(i) <= '9')
					|| specifications.charAt(i) == '.') {
				str += specifications.charAt(i);
			}
		}
	}
	var num = parseFloat(str);
	var nums = orderNum * num;
	var url = Utils.getRootPath()
			+ '/purchase/purchaseShopping/addToShoppingCart?id=' + id
			+ '&orderNum=' + orderNum + '&serialnumber=' + serialnumber
			+ '&actualNums=' + nums;
	$.ajax({
		type : "post",
		url : url,
		async : true,
		dataType : 'json',
		success : function(result) {
			if (result.result) {
				window.parent.loadDataGrid('purchaseOrders');
				$("#dd").datagrid('load');
			} else {
				$.messager.alert('提示', '添加失败，请联系管理员！', 'error');
				window.parent.loadDataGrid('purchaseOrders');
				$("#dd").datagrid('load');
			}
		}
	});
}

// 根据勾选的订单商品添加到购物车
function addTo() {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要添加的商品！', 'warning');
		return;
	}
	var ids = getRowsId();
	var orderNum = getRowsOrderNum();
	var serialnumber = getRowsSerialnumber();
	var url = Utils.getRootPath()
			+ '/purchase/purchaseShopping/pullPageGoodsToShoppingCart?ids='
			+ ids + '&orderNum=' + orderNum + '&serialnumber=' + serialnumber;
	$.ajax({
		type : "post",
		url : url,
		async : true,
		dataType : 'json',
		success : function(result) {
			if (result.result) {
				window.parent.loadDataGrid('purchaseOrders');
				$("#dd").datagrid('load');
			} else {
				$.messager.alert('提示', '添加失败，请联系管理员！', 'error');
				window.parent.loadDataGrid('purchaseOrders');
				$("#dd").datagrid('load');
			}
		}
	});
}

var getRowsId = function() {
	var ids = [];
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		return;
	}
	for (var i = 0; i < rows.length; i++) {
		ids.push(rows[i].GOODSFILE_ID);
	}
	return ids;
};
var getRowsOrderNum = function() {
	var orderNum = [];
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		return;
	}
	for (var i = 0; i < rows.length; i++) {
		orderNum.push(rows[i].NUMS);
	}
	return orderNum;
};
var getRowsSerialnumber = function() {
	var serialnumber = [];
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		return;
	}
	for (var i = 0; i < rows.length; i++) {
		serialnumber.push(rows[i].SERIALNUMBER);
	}
	return serialnumber;
};
var getRowsSpecifications = function() {
	var specifications = [];
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		return;
	}
	for (var i = 0; i < rows.length; i++) {
		specifications.push(rows[i].GOODS_SPECIFICATIONS);
	}
	return specifications;
};
// 打开购物车页面
function openShoppingCart() {
	var url = Utils.getRootPath()
			+ '/purchase/purchaseShopping/openShoppingCart';
	window.parent.initAdd(url, "我的采购订单购物车", 1000, 535);
}
// 删除购物车商品
function deleteGoods() {
	var rows = $('#dg').datagrid('getSelections');
	var url = Utils.getRootPath()
			+ '/purchase/purchaseShopping/openShoppingCart';
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
			$
					.ajax({
						type : "post",
						url : Utils.getRootPath()
								+ '/purchase/purchaseShopping/delete',
						data : {
							id : ids
						},
						async : false,
						dataType : 'json',
						success : function(result) {
							if (result.result) {
								$.messager.alert('提示', '删除成功', 'info');
								window.parent.loadDataGrid('purchaseOrders');
								window.parent.initAdd(url, "我的采购订单购物车", 1000,
										535);
								$("#dd").datagrid('load');

							} else if (result.status == "error") {
								$.messager.alert('提示', '删除失败，请联系管理员！', 'error');
								$("#dd").datagrid('load');
							}
						}
					});
		}
		;
	});
};

function deleteShoppingCart(id) {
	var url = Utils.getRootPath()
			+ '/purchase/purchaseShopping/deleteShoppingCart?id=' + id;
	$.ajax({
		type : "post",
		url : url,
		async : true,
		dataType : 'json',
		success : function(result) {
			if (result.result) {
				window.parent.loadDataGrid('purchaseOrders');
				$("#dd").datagrid('load');
			} else {
				$.messager.alert('提示', '删除失败，请联系管理员！', 'error');
				window.parent.loadDataGrid('purchaseOrders');
				$("#dd").datagrid('load');
			}
		}
	});
}
// 一键保存购物车商品
function createPurchase() {
	var url = Utils.getRootPath() + '/purchase/purchaseShopping/createPurchase';
	$("#ddialog").dialog('open');
	$.ajax({
		type : "post",
		url : url,
		async : true,
		dataType : 'json',
		success : function(result) {
			$("#ddialog").dialog('close');
			if (result.result) {
				$.messager.alert('提示', '订单生成成功！', 'info', function() {
					window.parent.closeWinForm();
					window.parent.loadDataGrid('purchaseOrders');
				});
			} else {
				$.messager.alert('提示', '保存失败，请联系管理员！', 'error');
			}
		}
	});
}

function previewPrint() {
	$("#ddialog").window('open');
	$.ajax({
		type : "post",
		url : Utils.getRootPath()
				+ '/purchase/purchaseShopping/gotoPrintWaitPurchaseGoods',
		data : {},
		success : function(data) {
			$('#selectOptions').window({
				title : '打印预览',
				closable : true,
				maximizable : false,
				modal : false,
				draggable : false,
				width : 800,
				height : 450
			});
			$('#selectOptions').html(data);
			$("#ddialog").window('close');
		}
	});
}
function PreviewMytable() {
	var LODOP = getLodop();
	LODOP.PRINT_INIT("采购订单表格");
	var strStyle = "<style> table,td,th {border-width: 1px;border-style: solid;border-collapse: collapse}</style>";
	LODOP.ADD_PRINT_TABLE(60, "5%", "90%", 314, strStyle
			+ document.getElementById("div2").innerHTML);
	LODOP.SET_PRINT_STYLEA(0, "Vorient", 3);
	LODOP.ADD_PRINT_HTM(26, "5%", "90%", 140,
			document.getElementById("div1").innerHTML);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.SET_PRINT_STYLEA(0, "LinkedItem", 1);
	LODOP.PREVIEW();
};