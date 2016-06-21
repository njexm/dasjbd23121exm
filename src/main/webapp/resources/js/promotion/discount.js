//添加
function add(url, title, x, y) {
	window.parent.initAdd(url, title, x, y);
}

//打开页面
function open(url, title, x, y) {
	window.parent.initAdd(url, title, x, y);
}

function openDiscountItem(id) {
	var url = Utils.getRootPath()
			+ '/discount/discount/gotoDetailDiscount?id='
			+ id;
	add(url, '促销折扣单详情', 900, 535);
}

// 打开新增促销订单页面
function addPromotion(title, x, y) {
	var url = Utils.getRootPath() + '/discount/discount/gotoAddPromotion';
	add(url, title, x, y);
}

//编辑
function editPromotion(title, x, y) {

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
	if (row.CHECK_STATE == "1") {
		$.messager.alert('提示', '该收货单已提交！', 'warning');
		return;
	} else if (row.CHECK_STATE == "2") {
		$.messager.alert('提示', '该收货单已通过审核！', 'warning');
		return;
	} else if (row.CHECK_STATE == "4") {
		$.messager.alert('提示', '该收货单已完成！', 'warning');
		return;
	} else if (row.CHECK_STATE == "5") {
		$.messager.alert('提示', '该收货单已作废！', 'warning');
		return;
	} else {
		var url = Utils.getRootPath()
				+ '/discount/discount/gotoEditDiscountEdit?id='
				+ row.ID;
		open(url, title, x, y);
	}
}

function openChoseBranch() {
	var zcCodeMode = $("#zcCodeMode").val();
	var zcCodeScope = $("#zcCodeScope").val();
	// 保存
	$.ajax({
		type : "get",
		url : Utils.getRootPath() + '/discount/discount/openChoseBranch?zcCodeMode='
				+ zcCodeMode + '&zcCodeScope=' + zcCodeScope,
		data : {
			
		},
		success : function(data) {
			$('#selectOptions').window({
				title : '选择分店',
				closable : true,
				maximizable : false,
				modal : false,
				draggable : false,
				width : 900,
				height : 500
			});
			$('#selectOptions').html(data);
			$('#dg').datagrid('resize', {
				width : '100%'
			});
		}
	});
}

function addBranch() {
	debugger;
	var rows = $('#goodsDg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择至少一条数据！', 'warning');
		return;
	}
	var branchCodes = "";
	for (var i = 0; i < rows.length; i++) {
		branchCodes += rows[i].BRANCH_CODE + "|";
	}
	$('#branchCode').val(branchCodes);
	$('#selectOptions').window('close');
	$('#dg').datagrid('reload');
	
}
function searchDataGridChange(){
	debugger;
	var data = $('#dg').datagrid('getData');
	if(data.rows.length != 0){
		deleteChoseChange();
	}
	searchDataGrid();
}
function searchDataGrid(){
	debugger;
	var zcCodeMode = $("#zcCodeMode").val();
	var zcCodeScope = $("#zcCodeScope").val();
	//$('#dg').datagrid('reload');
	
	if (zcCodeMode =="1") {
		if (zcCodeScope=="1") {
			$('#dg').datagrid('hideColumn','CLASSCODE');
			$('#dg').datagrid('hideColumn','CLASSNAME');
			$('#dg').datagrid('hideColumn','BRANDCODE');
			$('#dg').datagrid('hideColumn','BRANDNAME');
			$('#dg').datagrid('hideColumn','SERIALNUMBER');
			$('#dg').datagrid('hideColumn','GOODS_NAME');
			$('#dg').datagrid('hideColumn','GOODS_PRICE');
			$('#dg').datagrid('hideColumn','FULL_BUY_MONEY');
			$('#dg').datagrid('hideColumn','FULL_BUY_COUNT');
			$('#dg').datagrid('hideColumn','DISCOUNT');
			$('#dg').datagrid('hideColumn','GOODS_PURCHASE_PRICE');
			$('#dg').datagrid('hideColumn','SPECIFICATIONS');
			$('#dg').datagrid('hideColumn','UNIT');
			
			$('#dg').datagrid('showColumn','ALL_DISCOUNT');
			
		}
		if (zcCodeScope=="2") {
			$('#dg').datagrid('hideColumn','ALL_DISCOUNT');
			$('#dg').datagrid('hideColumn','BRANDCODE');
			$('#dg').datagrid('hideColumn','BRANDNAME');
			$('#dg').datagrid('hideColumn','SERIALNUMBER');
			$('#dg').datagrid('hideColumn','GOODS_NAME');
			$('#dg').datagrid('hideColumn','GOODS_PRICE');
			$('#dg').datagrid('hideColumn','FULL_BUY_MONEY');
			$('#dg').datagrid('hideColumn','FULL_BUY_COUNT');
			$('#dg').datagrid('hideColumn','GOODS_PURCHASE_PRICE');
			$('#dg').datagrid('hideColumn','SPECIFICATIONS');
			$('#dg').datagrid('hideColumn','UNIT');
			
			$('#dg').datagrid('showColumn','CLASSCODE');
			$('#dg').datagrid('showColumn','CLASSNAME');
			$('#dg').datagrid('showColumn','DISCOUNT');		
		}
		if (zcCodeScope=="3") {
			$('#dg').datagrid('hideColumn','ALL_DISCOUNT');
			$('#dg').datagrid('hideColumn','CLASSCODE');
			$('#dg').datagrid('hideColumn','CLASSNAME');
			$('#dg').datagrid('hideColumn','SERIALNUMBER');
			$('#dg').datagrid('hideColumn','GOODS_NAME');
			$('#dg').datagrid('hideColumn','GOODS_PRICE');
			$('#dg').datagrid('hideColumn','FULL_BUY_MONEY');
			$('#dg').datagrid('hideColumn','FULL_BUY_COUNT');
			$('#dg').datagrid('hideColumn','GOODS_PURCHASE_PRICE');
			$('#dg').datagrid('hideColumn','SPECIFICATIONS');
			$('#dg').datagrid('hideColumn','UNIT');
			
			$('#dg').datagrid('showColumn','BRANDCODE');
			$('#dg').datagrid('showColumn','BRANDNAME');
			$('#dg').datagrid('showColumn','DISCOUNT');	
		}
		if (zcCodeScope=="4") {
			$('#dg').datagrid('hideColumn','ALL_DISCOUNT');
			$('#dg').datagrid('hideColumn','CLASSCODE');
			$('#dg').datagrid('hideColumn','CLASSNAME');
			$('#dg').datagrid('hideColumn','BRANDCODE');
			$('#dg').datagrid('hideColumn','BRANDNAME');
			$('#dg').datagrid('hideColumn','FULL_BUY_MONEY');
			$('#dg').datagrid('hideColumn','FULL_BUY_COUNT');
			
			$('#dg').datagrid('showColumn','SERIALNUMBER');
			$('#dg').datagrid('showColumn','GOODS_NAME');
			$('#dg').datagrid('showColumn','GOODS_PRICE');
			$('#dg').datagrid('showColumn','DISCOUNT');
			$('#dg').datagrid('showColumn','GOODS_PURCHASE_PRICE');
			$('#dg').datagrid('showColumn','SPECIFICATIONS');
			$('#dg').datagrid('showColumn','UNIT');
		}
	}
	if (zcCodeMode =="2") {
		if (zcCodeScope=="1") {
			$('#dg').datagrid('hideColumn','ALL_DISCOUNT');
			$('#dg').datagrid('hideColumn','CLASSCODE');
			$('#dg').datagrid('hideColumn','CLASSNAME');
			$('#dg').datagrid('hideColumn','BRANDCODE');
			$('#dg').datagrid('hideColumn','BRANDNAME');
			$('#dg').datagrid('hideColumn','SERIALNUMBER');
			$('#dg').datagrid('hideColumn','GOODS_NAME');
			$('#dg').datagrid('hideColumn','GOODS_PRICE');
			$('#dg').datagrid('hideColumn','FULL_BUY_COUNT');
			$('#dg').datagrid('hideColumn','GOODS_PURCHASE_PRICE');
			$('#dg').datagrid('hideColumn','SPECIFICATIONS');
			$('#dg').datagrid('hideColumn','UNIT');
			
			$('#dg').datagrid('showColumn','FULL_BUY_MONEY');	
			$('#dg').datagrid('showColumn','DISCOUNT');	
		}
		if (zcCodeScope=="2") {
			$('#dg').datagrid('hideColumn','ALL_DISCOUNT');
			$('#dg').datagrid('hideColumn','BRANDCODE');
			$('#dg').datagrid('hideColumn','BRANDNAME');
			$('#dg').datagrid('hideColumn','SERIALNUMBER');
			$('#dg').datagrid('hideColumn','GOODS_NAME');
			$('#dg').datagrid('hideColumn','GOODS_PRICE');
			$('#dg').datagrid('hideColumn','FULL_BUY_COUNT');
			$('#dg').datagrid('hideColumn','GOODS_PURCHASE_PRICE');
			$('#dg').datagrid('hideColumn','SPECIFICATIONS');
			$('#dg').datagrid('hideColumn','UNIT');
			
			$('#dg').datagrid('showColumn','CLASSCODE');
			$('#dg').datagrid('showColumn','CLASSNAME');
			$('#dg').datagrid('showColumn','FULL_BUY_MONEY');	
			$('#dg').datagrid('showColumn','DISCOUNT');	
		}
		if (zcCodeScope=="3") {
			$('#dg').datagrid('hideColumn','ALL_DISCOUNT');
			$('#dg').datagrid('hideColumn','CLASSCODE');
			$('#dg').datagrid('hideColumn','CLASSNAME');
			$('#dg').datagrid('hideColumn','SERIALNUMBER');
			$('#dg').datagrid('hideColumn','GOODS_NAME');
			$('#dg').datagrid('hideColumn','GOODS_PRICE');
			$('#dg').datagrid('hideColumn','FULL_BUY_COUNT');
			$('#dg').datagrid('hideColumn','GOODS_PURCHASE_PRICE');
			$('#dg').datagrid('hideColumn','SPECIFICATIONS');
			$('#dg').datagrid('hideColumn','UNIT');
			
			$('#dg').datagrid('showColumn','BRANDCODE');
			$('#dg').datagrid('showColumn','BRANDNAME');
			$('#dg').datagrid('showColumn','FULL_BUY_MONEY');	
			$('#dg').datagrid('showColumn','DISCOUNT');	
		}
		if (zcCodeScope=="4") {
			$('#dg').datagrid('hideColumn','ALL_DISCOUNT');
			$('#dg').datagrid('hideColumn','CLASSCODE');
			$('#dg').datagrid('hideColumn','CLASSNAME');
			$('#dg').datagrid('hideColumn','BRANDCODE');
			$('#dg').datagrid('hideColumn','BRANDNAME');
			$('#dg').datagrid('hideColumn','FULL_BUY_COUNT');
			
			$('#dg').datagrid('showColumn','SERIALNUMBER');
			$('#dg').datagrid('showColumn','GOODS_NAME');
			$('#dg').datagrid('showColumn','GOODS_PRICE');
			$('#dg').datagrid('showColumn','FULL_BUY_MONEY');
			$('#dg').datagrid('showColumn','DISCOUNT');
			$('#dg').datagrid('showColumn','GOODS_PURCHASE_PRICE');
			$('#dg').datagrid('showColumn','SPECIFICATIONS');
			$('#dg').datagrid('showColumn','UNIT');
		}
	}
	if (zcCodeMode =="3") {
		if (zcCodeScope=="1") {
			$('#dg').datagrid('hideColumn','ALL_DISCOUNT');
			$('#dg').datagrid('hideColumn','CLASSCODE');
			$('#dg').datagrid('hideColumn','CLASSNAME');
			$('#dg').datagrid('hideColumn','BRANDCODE');
			$('#dg').datagrid('hideColumn','BRANDNAME');
			$('#dg').datagrid('hideColumn','SERIALNUMBER');
			$('#dg').datagrid('hideColumn','GOODS_NAME');
			$('#dg').datagrid('hideColumn','GOODS_PRICE');
			$('#dg').datagrid('hideColumn','FULL_BUY_MONEY');
			$('#dg').datagrid('hideColumn','GOODS_PURCHASE_PRICE');
			$('#dg').datagrid('hideColumn','SPECIFICATIONS');
			$('#dg').datagrid('hideColumn','UNIT');
			
			$('#dg').datagrid('showColumn','FULL_BUY_COUNT');	
			$('#dg').datagrid('showColumn','DISCOUNT');	
		}
		if (zcCodeScope=="2") {
			$('#dg').datagrid('hideColumn','ALL_DISCOUNT');
			$('#dg').datagrid('hideColumn','BRANDCODE');
			$('#dg').datagrid('hideColumn','BRANDNAME');
			$('#dg').datagrid('hideColumn','SERIALNUMBER');
			$('#dg').datagrid('hideColumn','GOODS_NAME');
			$('#dg').datagrid('hideColumn','GOODS_PRICE');
			$('#dg').datagrid('hideColumn','FULL_BUY_MONEY');
			$('#dg').datagrid('hideColumn','GOODS_PURCHASE_PRICE');
			$('#dg').datagrid('hideColumn','SPECIFICATIONS');
			$('#dg').datagrid('hideColumn','UNIT');
			
			$('#dg').datagrid('showColumn','CLASSCODE');	
			$('#dg').datagrid('showColumn','CLASSNAME');	
			$('#dg').datagrid('showColumn','FULL_BUY_COUNT');	
			$('#dg').datagrid('showColumn','DISCOUNT');	
		}
		if (zcCodeScope=="3") {
			$('#dg').datagrid('hideColumn','ALL_DISCOUNT');
			$('#dg').datagrid('hideColumn','CLASSCODE');
			$('#dg').datagrid('hideColumn','CLASSNAME');
			$('#dg').datagrid('hideColumn','SERIALNUMBER');
			$('#dg').datagrid('hideColumn','GOODS_NAME');
			$('#dg').datagrid('hideColumn','GOODS_PRICE');
			$('#dg').datagrid('hideColumn','FULL_BUY_MONEY');
			$('#dg').datagrid('hideColumn','GOODS_PURCHASE_PRICE');
			$('#dg').datagrid('hideColumn','SPECIFICATIONS');
			$('#dg').datagrid('hideColumn','UNIT');
			
			
			$('#dg').datagrid('showColumn','BRANDCODE');
			$('#dg').datagrid('showColumn','BRANDNAME');
			$('#dg').datagrid('showColumn','FULL_BUY_COUNT');	
			$('#dg').datagrid('showColumn','DISCOUNT');	
		}
		if (zcCodeScope=="4") {
			$('#dg').datagrid('hideColumn','ALL_DISCOUNT');
			$('#dg').datagrid('hideColumn','CLASSCODE');
			$('#dg').datagrid('hideColumn','CLASSNAME');
			$('#dg').datagrid('hideColumn','BRANDCODE');
			$('#dg').datagrid('hideColumn','BRANDNAME');
			$('#dg').datagrid('hideColumn','FULL_BUY_MONEY');
			
			$('#dg').datagrid('showColumn','SERIALNUMBER');
			$('#dg').datagrid('showColumn','GOODS_NAME');
			$('#dg').datagrid('showColumn','GOODS_PRICE');
			$('#dg').datagrid('showColumn','FULL_BUY_COUNT');
			$('#dg').datagrid('showColumn','DISCOUNT');
			$('#dg').datagrid('showColumn','GOODS_PURCHASE_PRICE');
			$('#dg').datagrid('showColumn','SPECIFICATIONS');
			$('#dg').datagrid('showColumn','UNIT');
		}
	}
	
	$('#dg').datagrid('reload');
}


function openChoseGoods(){
	var zcCodeScope = $("#zcCodeScope").val();
	if (zcCodeScope=="" || zcCodeScope == null) {
		$.messager.alert('提示', '请选择商品范围！', 'warning');
		return;
	}
	if (zcCodeScope=="1" && zcCodeScope != null) {
		$.messager.alert('提示', '全场折扣无需选择', 'warning');
		return;
	}
	var title1 ='';
	if (zcCodeScope=="2") {
		title1='选择类别';
	}
	if (zcCodeScope=="3") {
		title1='选择品牌';
	}
	if (zcCodeScope=="4") {
		title1='选择商品';
	}
	$.ajax({
		type : "get",
		url : Utils.getRootPath() + '/discount/discount/openChoseGoods?zcCodeScope='
				+ zcCodeScope ,
		data : {
			
		},
		success : function(data) {
			$('#selectOptions').window({
				title : title1,
				closable : true,
				maximizable : false,
				modal : false,
				draggable : false,
				width : 900,
				height : 500
			});
			$('#selectOptions').html(data);
			$('#dg').datagrid('resize', {
				width : '100%'
			});
		}
	});
	
}

function addGoods(num) {
	debugger;
	var zcSalesPromotionId = $('#zcSalesPromotionId').val();
	var zcCodeScope = num +"" ;
	if(zcCodeScope=="1"){
		var zcCodeMode2 = $("#zcCodeMode").val();
		var zcCodeScope2 = $("#zcCodeScope").val();
		if (zcCodeScope2 !="1" || zcCodeMode2=="" || zcCodeMode2==null) {
			$.messager.alert('提示', '必须全场范围且有促销方式！', 'warning');
			return;
		}
	}
	var  ids = "";
	if (zcCodeScope!="1") {
		var rows = $('#goodsDg').datagrid('getSelections');
		if (rows.length == 0 ) {
			$.messager.alert('提示', '请选择至少一条数据！', 'warning');
			return;
		}
		for (var i = 0; i < rows.length; i++) {
			ids += rows[i].ID+",";
		}
	}
	var url = '';
	if (zcSalesPromotionId==null || zcSalesPromotionId=="" || zcSalesPromotionId=='undefiend') {
		url = Utils.getRootPath()
		+ '/discount/discount/addDiscountGoodsToItems?ids=' + ids
		+ '&zcCodeScope=' + zcCodeScope;
	}else{
	    url = Utils.getRootPath()
			+ '/discount/discount/addDiscountGoodsToEditItems?ids=' + ids
			+ '&zcCodeScope=' + zcCodeScope
			+ '&zcSalesPromotionId=' + zcSalesPromotionId;
	}
	$.ajax({
		type : "post",
		url : url,
		async : true,
		dataType : 'json',
		success : function(result) {
			$('#selectOptions').window('close');
			if (result.message == "保存成功") {
				$.messager.alert('提示', '添加成功', 'info', function() {
					$('#dg').datagrid('reload');
				});
			} else if (result.message == "保存error") {
				$.messager.alert('提示', '已存在该勾选的商品，请重新确认！', 'info', function() {
					$('#dg').datagrid('reload');
				});
			} else {
				$.messager.alert('提示', '添加失败，请联系管理员！', 'error');
				$('#dg').datagrid('reload');
			}
		}
	});
}




//根据商品表直接选中行后生成促销订单
function saveLineToPurchase(formId) {
	debugger;
	var data = $('#dg').datagrid('getData');
	var zcCodeMode = $("#zcCodeMode").val();
	var zcCodeScope = $("#zcCodeScope").val();
	if (zcCodeScope=="" || zcCodeScope == null) {
		$.messager.alert('提示', '请选择商品范围！', 'warning');
		return;
	}
	if (zcCodeMode=="" || zcCodeMode == null) {
		$.messager.alert('提示', '请选择促销方式！', 'warning');
		return;
	}
	//document.getElementsByName('week')
	 var chkValue ="";//定义一个数组    
     $('input[name="week"]:checked').each(function(){//遍历每一个名字为interest的复选框，其中选中的执行函数    
    	 chkValue += $(this).val() +",";//将选中的值添加到数组chk_value中    
     });
    
	var branchCode = $('#branchCode').val();
	
	var ids = "";
	var allDiscount = "";
	var fullBuyMoney = "";
	var fullBuyCount = "";
	var discount = "";
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		ids += data.rows[i].ID + ",";
		allDiscount += data.rows[i].All_DISCOUNT + ",";
		fullBuyMoney += data.rows[i].FULL_BUY_MONEY + ",";
		fullBuyCount += data.rows[i].FULL_BUY_COUNT + ",";
		discount += data.rows[i].DISCOUNT + ",";
		$('#dg').datagrid('beginEdit', i);
	}
	var data = $('#dg').datagrid('getData');
	
	var url = Utils.getRootPath()
			+ '/discount/discount/createZcSalesPromotion?ids=' + ids
			+ '&allDiscount=' + allDiscount + '&data=' + data + '&fullBuyMoney=' + fullBuyMoney
			+ '&fullBuyCount=' + fullBuyCount+ '&discount=' + discount
			+ '&branchCode=' + branchCode
			+ '&chkValue=' + chkValue;
			//+ '&zcCodeMode=' + zcCodeMode
			//+ '&zcCodeScope=' + zcCodeScope;
	// 数据有限性判断
	if (validateSubmit(formId)) {
		$.ajax({
			type : "post",
			url : url,
			async : false,
			data : $('#' + formId).serialize(),
			dataType : 'json',
			success : function(result) {
				if (result.result) {
					$.messager.alert('提示', '新增成功', 'info', function() {
						window.parent.loadDataGrid('purchaseOrders');
						window.parent.closeWinForm();
					});
				} else if (result.message == "保存error") {
					$.messager.alert('提示', '请重新确认', 'info',
							function() {
								$('#dg').datagrid('reload');
							});
				} else {
					$.messager.alert('提示', '新增失败，请联系管理员！', 'error');
				}
			}
		});
	}
}



//移除促销折扣详细
function deleteChose() {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要删除的数据！', 'warning');
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
	var data = $('#dg').datagrid('getData');
	var idStr = "";
	var allDiscount = "";
	var fullBuyMoney = "";
	var fullBuyCount = "";
	var discount = "";
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		$('#dg').datagrid('beginEdit', i);
		idStr += data.rows[i].ID + ",";
//		fullBuyMoney += data.rows[i].FULL_BUY_MONEY + ",";
//		fullBuyCount += data.rows[i].FULL_BUY_COUNT + ",";
//		discount += data.rows[i].DISCOUNT + " ,";
		if (data.rows[i].All_DISCOUNT == null || data.rows[i].All_DISCOUNT == ''
				|| data.rows[i].All_DISCOUNT == 'undefiend') {
			allDiscount += "0.00,";
		} else {
			allDiscount += data.rows[i].All_DISCOUNT + ",";
		}
		if (data.rows[i].FULL_BUY_MONEY == null || data.rows[i].FULL_BUY_MONEY == ''
			|| data.rows[i].FULL_BUY_MONEY == 'undefiend') {
			fullBuyMoney += "0.00,";
		} else {
			fullBuyMoney += data.rows[i].FULL_BUY_MONEY + ",";
		}
		if (data.rows[i].FULL_BUY_COUNT == null || data.rows[i].FULL_BUY_COUNT == ''
			|| data.rows[i].FULL_BUY_COUNT == 'undefiend') {
			fullBuyCount += "0.00,";
		} else {
			fullBuyCount += data.rows[i].FULL_BUY_COUNT + ",";
		}
		if (data.rows[i].DISCOUNT == null || data.rows[i].DISCOUNT == ''
			|| data.rows[i].DISCOUNT == 'undefiend') {
			discount  += "0.00,";
		} else {
			discount += data.rows[i].DISCOUNT + " ,";
		}
		}
	$.messager.confirm('确认 ', '确定要删除 ？', function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : Utils.getRootPath()
						+ '/discount/discount/deleteChose?allDiscount=' + allDiscount
						+ '&idStr=' + idStr + '&fullBuyMoney=' + fullBuyMoney
						+ '&fullBuyCount=' + fullBuyCount
						+ '&discount=' + discount,
				data : {
					id : ids
				},
				async : false,
				dataType : 'json',
				success : function(result) {
					if (result.result) {
						$.messager.alert('提示', '删除成功', 'info', function() {
							window.parent.loadDataGrid('purchaseOrders');
							$('#dg').datagrid('reload');
						});
					} else if (result.status == "error") {
						$.messager.alert('提示', '删除失败，请联系管理员！', 'error');
						$('#dg').datagrid('reload');
					}
				}
			});
		}
		;
	});
};

function deleteChoseChange() {
	var rows = $('#dg').datagrid('getData');
	var ids = "";
	for (var i = 0; i < rows.length; i++) {
		if (rows.length == i + 1) {
			ids += rows[i].ID;
		} else {
			ids += rows[i].ID + ",";
		}
	}
	var data = $('#dg').datagrid('getData');
	var idStr = "";
	var allDiscount = "";
	var fullBuyMoney = "";
	var fullBuyCount = "";
	var discount = "";
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		$('#dg').datagrid('beginEdit', i);
		idStr += data.rows[i].ID + ",";
//		fullBuyMoney += data.rows[i].FULL_BUY_MONEY + ",";
//		fullBuyCount += data.rows[i].FULL_BUY_COUNT + ",";
//		discount += data.rows[i].DISCOUNT + " ,";
		if (data.rows[i].All_DISCOUNT == null || data.rows[i].All_DISCOUNT == ''
				|| data.rows[i].All_DISCOUNT == 'undefiend') {
			allDiscount += "0.00,";
		} else {
			allDiscount += data.rows[i].All_DISCOUNT + ",";
		}
		if (data.rows[i].FULL_BUY_MONEY == null || data.rows[i].FULL_BUY_MONEY == ''
			|| data.rows[i].FULL_BUY_MONEY == 'undefiend') {
			fullBuyMoney += "0.00,";
		} else {
			fullBuyMoney += data.rows[i].FULL_BUY_MONEY + ",";
		}
		if (data.rows[i].FULL_BUY_COUNT == null || data.rows[i].FULL_BUY_COUNT == ''
			|| data.rows[i].FULL_BUY_COUNT == 'undefiend') {
			fullBuyCount += "0.00,";
		} else {
			fullBuyCount += data.rows[i].FULL_BUY_COUNT + ",";
		}
		if (data.rows[i].DISCOUNT == null || data.rows[i].DISCOUNT == ''
			|| data.rows[i].DISCOUNT == 'undefiend') {
			discount  += "0.00,";
		} else {
			discount += data.rows[i].DISCOUNT + " ,";
		}
		}
	
		
			$.ajax({
				type : "post",
				url : Utils.getRootPath()
						+ '/discount/discount/deleteChoseChange?allDiscount=' + allDiscount
						+ '&idStr=' + idStr + '&fullBuyMoney=' + fullBuyMoney
						+ '&fullBuyCount=' + fullBuyCount
						+ '&discount=' + discount,
				data : {
					id : ids
				},
				async : false,
				dataType : 'json',
				success : function(result) {
					
				}
			});
		
		
	
};

//根据商品表直接选中行后生成促销订单
function EidtLineToPurchase(formId) {
	debugger;
	var data = $('#dg').datagrid('getData');
	var zcCodeMode = $("#zcCodeMode").val();
	var zcCodeScope = $("#zcCodeScope").val();
	if (zcCodeScope=="" || zcCodeScope == null) {
		$.messager.alert('提示', '请选择商品范围！', 'warning');
		return;
	}
	if (zcCodeMode=="" || zcCodeMode == null) {
		$.messager.alert('提示', '请选择促销方式！', 'warning');
		return;
	}
	//document.getElementsByName('week')
	 var chkValue ="";//定义一个数组    
     $('input[name="week"]:checked').each(function(){//遍历每一个名字为interest的复选框，其中选中的执行函数    
    	 chkValue += $(this).val() +",";//将选中的值添加到数组chk_value中    
     });
    
	var branchCode = $('#branchCode').val();
	
	var ids = "";
	var allDiscount = "";
	var fullBuyMoney = "";
	var fullBuyCount = "";
	var discount = "";
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		ids += data.rows[i].ID + ",";
		allDiscount += data.rows[i].All_DISCOUNT + ",";
		fullBuyMoney += data.rows[i].FULL_BUY_MONEY + ",";
		fullBuyCount += data.rows[i].FULL_BUY_COUNT + ",";
		discount += data.rows[i].DISCOUNT + ",";
		$('#dg').datagrid('beginEdit', i);
	}
	var data = $('#dg').datagrid('getData');
	
	var url = Utils.getRootPath()
			+ '/discount/discount/editZcSalesPromotion?ids=' + ids
			+ '&allDiscount=' + allDiscount + '&data=' + data + '&fullBuyMoney=' + fullBuyMoney
			+ '&fullBuyCount=' + fullBuyCount+ '&discount=' + discount
			+ '&branchCode=' + branchCode
			+ '&chkValue=' + chkValue;
			//+ '&zcCodeMode=' + zcCodeMode
			//+ '&zcCodeScope=' + zcCodeScope;
	// 数据有限性判断
	if (validateSubmit(formId)) {
		$.ajax({
			type : "post",
			url : url,
			async : false,
			data : $('#' + formId).serialize(),
			dataType : 'json',
			success : function(result) {
				if (result.result) {
					$.messager.alert('提示', '修改成功', 'info', function() {
						window.parent.loadDataGrid('purchaseOrders');
						window.parent.closeWinForm();
					});
				} else if (result.message == "保存error") {
					$.messager.alert('提示', '请重新确认', 'info',
							function() {
								$('#dg').datagrid('reload');
							});
				} else {
					$.messager.alert('提示', '修改失败，请联系管理员！', 'error');
				}
			}
		});
	}
}

function openEditGoods() {
	var data = $('#dg').datagrid('getData');
	var zcCodeMode = $("#zcCodeMode").val();
	var zcCodeScope = $("#zcCodeScope").val();
	if (zcCodeScope=="" || zcCodeScope == null) {
		$.messager.alert('提示', '请选择商品范围！', 'warning');
		return;
	}
	if (zcCodeMode=="" || zcCodeMode == null) {
		$.messager.alert('提示', '请选择促销方式！', 'warning');
		return;
	}
	
	var zcSalesPromotionId = $('#zcSalesPromotionId').val();
	
	var ids = "";
	var allDiscount = "";
	var fullBuyMoney = "";
	var fullBuyCount = "";
	var discount = "";
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		ids += data.rows[i].ID + ",";
		allDiscount += data.rows[i].All_DISCOUNT + ",";
		fullBuyMoney += data.rows[i].FULL_BUY_MONEY + ",";
		fullBuyCount += data.rows[i].FULL_BUY_COUNT + ",";
		discount += data.rows[i].DISCOUNT + ",";
		$('#dg').datagrid('beginEdit', i);
	}
	var data = $('#dg').datagrid('getData');
	var title1 ='';
	if (zcCodeScope=="2") {
		title1='选择类别';
	}
	if (zcCodeScope=="3") {
		title1='选择品牌';
	}
	if (zcCodeScope=="4") {
		title1='选择商品';
	}
	$.ajax({
		type : "get",
		url : Utils.getRootPath()
				+ '/discount/discount/openAddGoods?ids=' + ids
				+ '&allDiscount=' + allDiscount + '&data=' + data + '&fullBuyMoney=' + fullBuyMoney
				+ '&fullBuyCount=' + fullBuyCount+ '&discount=' + discount
				+ '&zcSalesPromotionId='+ zcSalesPromotionId,
		data : {},
		success : function(data) {
			$('#selectOptions').window({
				title : title1,
				closable : true,
				maximizable : false,
				modal : false,
				draggable : false,
				width : 900,
				height : 550
			});
			$('#selectOptions').html(data);
			$('#showWinBatch').window('close');
			$('#showexportBatch').window('close');
		}
	});
}

function addGoodsWhileEdit(num) {
	debugger;
	var zcSalesPromotionId = $('#zcSalesPromotionId').val();
	var zcCodeScope = num +"" ;
	var zcCodeMode = $('#zcCodeMode').val()+"";
	if(zcCodeScope=="1"){
		var zcCodeMode2 = $("#zcCodeMode").val();
		var zcCodeScope2 = $("#zcCodeScope").val();
		if (zcCodeScope2 !="1" || zcCodeMode2=="" || zcCodeMode2==null) {
			$.messager.alert('提示', '必须全场范围且有促销方式！', 'warning');
			return;
		}
	}
	var  ids = "";
	if (zcCodeScope!="1") {
		var rows = $('#goodsDg').datagrid('getSelections');
		if (rows.length == 0 ) {
			$.messager.alert('提示', '请选择至少一条数据！', 'warning');
			return;
		}
		for (var i = 0; i < rows.length; i++) {
			ids += rows[i].ID+",";
		}
	}
	var  url = Utils.getRootPath()
			+ '/discount/discount/addDiscountScopeWhole?ids=' + ids
			+ '&zcCodeScope=' + zcCodeScope
			+ '&zcSalesPromotionId=' + zcSalesPromotionId
			+ '&zcCodeMode=' + zcCodeMode;
	
	$.ajax({
		type : "post",
		url : url,
		async : true,
		dataType : 'json',
		success : function(result) {
			$('#selectOptions').window('close');
			if (result.message == "保存成功") {
				$.messager.alert('提示', '添加成功', 'info', function() {
					$('#dg').datagrid('reload');
				});
			} else if (result.message == "保存error") {
				$.messager.alert('提示', '已存在该勾选的商品，请重新确认！', 'info', function() {
					$('#dg').datagrid('reload');
				});
			} else {
				$.messager.alert('提示', '添加失败，请联系管理员！', 'error');
				$('#dg').datagrid('reload');
			}
		}
	});
}