//添加
function add(url, title, x, y) {
	window.parent.initAdd(url, title, x, y);
}

//打开页面
function open(url, title, x, y) {
	window.parent.initAdd(url, title, x, y);
}

function openBuyFullSendItem(id) {
	var url = Utils.getRootPath()
			+ '/buyFullSend/buyFullSend/gotoDetailBuyFullSend?id='
			+ id;
	add(url, '促销买满送订单详情', 900, 535);
}

// 打开新增促销订单页面
function addPromotion(title, x, y) {
	var url = Utils.getRootPath() + '/buyFullSend/buyFullSend/gotoAddPromotion';
	add(url, title, x, y);
}

//编辑
function editPromotionFullBuy(title, x, y) {

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
				+ '/buyFullSend/buyFullSend/gotoEditbuyFullSendEdit?id='
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
		url : Utils.getRootPath() + '/buyFullSend/buyFullSend/openChoseBranch?zcCodeMode='
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
			$('#dg').datagrid('hideColumn','FULL_BUY_COUNT');
			$('#dg').datagrid('hideColumn','GOODS_PURCHASE_PRICE');
			$('#dg').datagrid('hideColumn','GOODS_SPECIFICATIONS');
			$('#dg').datagrid('hideColumn','GOODS_UNIT');
			$('#dg').datagrid('hideColumn','REDUCE_MONEY');
			$('#dg').datagrid('hideColumn','FREE_GOODSIDS');
			$('#dg').datagrid('hideColumn','ADD_MORE_MONEY');
			
			
			$('#dg').datagrid('showColumn','FULL_BUY_MONEY');
			$('#dg').datagrid('showColumn','ADD_MONEY');
			
		}
		if (zcCodeScope=="2") {
			$('#dg').datagrid('hideColumn','BRANDCODE');
			$('#dg').datagrid('hideColumn','BRANDNAME');
			$('#dg').datagrid('hideColumn','SERIALNUMBER');
			$('#dg').datagrid('hideColumn','GOODS_NAME');
			$('#dg').datagrid('hideColumn','GOODS_PRICE');
			$('#dg').datagrid('hideColumn','FULL_BUY_COUNT');
			$('#dg').datagrid('hideColumn','GOODS_PURCHASE_PRICE');
			$('#dg').datagrid('hideColumn','GOODS_SPECIFICATIONS');
			$('#dg').datagrid('hideColumn','GOODS_UNIT');
			$('#dg').datagrid('hideColumn','REDUCE_MONEY');
			$('#dg').datagrid('hideColumn','FREE_GOODSIDS');
			$('#dg').datagrid('hideColumn','ADD_MORE_MONEY');
			
			$('#dg').datagrid('showColumn','CLASSCODE');
			$('#dg').datagrid('showColumn','CLASSNAME');
			$('#dg').datagrid('showColumn','FULL_BUY_MONEY');
			$('#dg').datagrid('showColumn','ADD_MONEY');
		}
		if (zcCodeScope=="3") {
			$('#dg').datagrid('hideColumn','CLASSCODE');
			$('#dg').datagrid('hideColumn','CLASSNAME');
			$('#dg').datagrid('hideColumn','SERIALNUMBER');
			$('#dg').datagrid('hideColumn','GOODS_NAME');
			$('#dg').datagrid('hideColumn','GOODS_PRICE');
			$('#dg').datagrid('hideColumn','FULL_BUY_COUNT');
			$('#dg').datagrid('hideColumn','GOODS_PURCHASE_PRICE');
			$('#dg').datagrid('hideColumn','GOODS_SPECIFICATIONS');
			$('#dg').datagrid('hideColumn','GOODS_UNIT');
			$('#dg').datagrid('hideColumn','REDUCE_MONEY');
			$('#dg').datagrid('hideColumn','FREE_GOODSIDS');
			$('#dg').datagrid('hideColumn','ADD_MORE_MONEY');
			
			$('#dg').datagrid('showColumn','BRANDCODE');
			$('#dg').datagrid('showColumn','BRANDNAME');
			$('#dg').datagrid('showColumn','FULL_BUY_MONEY');
			$('#dg').datagrid('showColumn','ADD_MONEY');
		}
		if (zcCodeScope=="4") {
			$('#dg').datagrid('hideColumn','CLASSCODE');
			$('#dg').datagrid('hideColumn','CLASSNAME');
			$('#dg').datagrid('hideColumn','BRANDCODE');
			$('#dg').datagrid('hideColumn','BRANDNAME');
			$('#dg').datagrid('hideColumn','FULL_BUY_COUNT');
			$('#dg').datagrid('hideColumn','REDUCE_MONEY');
			$('#dg').datagrid('hideColumn','FREE_GOODSIDS');
			$('#dg').datagrid('hideColumn','ADD_MORE_MONEY');
			
			
			$('#dg').datagrid('showColumn','SERIALNUMBER');
			$('#dg').datagrid('showColumn','GOODS_NAME');
			$('#dg').datagrid('showColumn','GOODS_PRICE');
			$('#dg').datagrid('showColumn','GOODS_PURCHASE_PRICE');
			$('#dg').datagrid('showColumn','GOODS_SPECIFICATIONS');
			$('#dg').datagrid('showColumn','GOODS_UNIT');
			$('#dg').datagrid('showColumn','FULL_BUY_MONEY');
			$('#dg').datagrid('showColumn','ADD_MONEY');
		}
	}
	if (zcCodeMode =="2") {
		if (zcCodeScope=="1") {
			$('#dg').datagrid('hideColumn','CLASSCODE');
			$('#dg').datagrid('hideColumn','CLASSNAME');
			$('#dg').datagrid('hideColumn','BRANDCODE');
			$('#dg').datagrid('hideColumn','BRANDNAME');
			$('#dg').datagrid('hideColumn','SERIALNUMBER');
			$('#dg').datagrid('hideColumn','GOODS_NAME');
			$('#dg').datagrid('hideColumn','GOODS_PRICE');
			$('#dg').datagrid('hideColumn','FULL_BUY_COUNT');
			$('#dg').datagrid('hideColumn','GOODS_PURCHASE_PRICE');
			$('#dg').datagrid('hideColumn','GOODS_SPECIFICATIONS');
			$('#dg').datagrid('hideColumn','GOODS_UNIT');
			$('#dg').datagrid('hideColumn','ADD_MONEY');
			$('#dg').datagrid('hideColumn','FREE_GOODSIDS');
			$('#dg').datagrid('hideColumn','ADD_MORE_MONEY');
			
			
			$('#dg').datagrid('showColumn','FULL_BUY_MONEY');
			$('#dg').datagrid('showColumn','REDUCE_MONEY');
		}
		if (zcCodeScope=="2") {
			$('#dg').datagrid('hideColumn','BRANDCODE');
			$('#dg').datagrid('hideColumn','BRANDNAME');
			$('#dg').datagrid('hideColumn','SERIALNUMBER');
			$('#dg').datagrid('hideColumn','GOODS_NAME');
			$('#dg').datagrid('hideColumn','GOODS_PRICE');
			$('#dg').datagrid('hideColumn','FULL_BUY_COUNT');
			$('#dg').datagrid('hideColumn','GOODS_PURCHASE_PRICE');
			$('#dg').datagrid('hideColumn','GOODS_SPECIFICATIONS');
			$('#dg').datagrid('hideColumn','GOODS_UNIT');
			$('#dg').datagrid('hideColumn','ADD_MONEY');
			$('#dg').datagrid('hideColumn','FREE_GOODSIDS');
			$('#dg').datagrid('hideColumn','ADD_MORE_MONEY');
			
			$('#dg').datagrid('showColumn','CLASSCODE');
			$('#dg').datagrid('showColumn','CLASSNAME');
			$('#dg').datagrid('showColumn','FULL_BUY_MONEY');
			$('#dg').datagrid('showColumn','REDUCE_MONEY');
		}
		if (zcCodeScope=="3") {
			$('#dg').datagrid('hideColumn','CLASSCODE');
			$('#dg').datagrid('hideColumn','CLASSNAME');
			$('#dg').datagrid('hideColumn','SERIALNUMBER');
			$('#dg').datagrid('hideColumn','GOODS_NAME');
			$('#dg').datagrid('hideColumn','GOODS_PRICE');
			$('#dg').datagrid('hideColumn','FULL_BUY_COUNT');
			$('#dg').datagrid('hideColumn','GOODS_PURCHASE_PRICE');
			$('#dg').datagrid('hideColumn','GOODS_SPECIFICATIONS');
			$('#dg').datagrid('hideColumn','GOODS_UNIT');
			$('#dg').datagrid('hideColumn','ADD_MONEY');
			$('#dg').datagrid('hideColumn','FREE_GOODSIDS');
			$('#dg').datagrid('hideColumn','ADD_MORE_MONEY');
			
			$('#dg').datagrid('showColumn','BRANDCODE');
			$('#dg').datagrid('showColumn','BRANDNAME');
			$('#dg').datagrid('showColumn','FULL_BUY_MONEY');
			$('#dg').datagrid('showColumn','REDUCE_MONEY');
		}
		if (zcCodeScope=="4") {
			$('#dg').datagrid('hideColumn','CLASSCODE');
			$('#dg').datagrid('hideColumn','CLASSNAME');
			$('#dg').datagrid('hideColumn','BRANDCODE');
			$('#dg').datagrid('hideColumn','BRANDNAME');
			$('#dg').datagrid('hideColumn','FULL_BUY_COUNT');
			$('#dg').datagrid('hideColumn','ADD_MONEY');
			$('#dg').datagrid('hideColumn','FREE_GOODSIDS');
			$('#dg').datagrid('hideColumn','ADD_MORE_MONEY');
			
			$('#dg').datagrid('showColumn','SERIALNUMBER');
			$('#dg').datagrid('showColumn','GOODS_NAME');
			$('#dg').datagrid('showColumn','GOODS_PRICE');
			$('#dg').datagrid('showColumn','FULL_BUY_MONEY');
			$('#dg').datagrid('showColumn','GOODS_PURCHASE_PRICE');
			$('#dg').datagrid('showColumn','GOODS_SPECIFICATIONS');
			$('#dg').datagrid('showColumn','GOODS_UNIT');
			$('#dg').datagrid('showColumn','REDUCE_MONEY');
		}
	}
	if (zcCodeMode =="3") {
		if (zcCodeScope=="1") {
			$('#dg').datagrid('hideColumn','CLASSCODE');
			$('#dg').datagrid('hideColumn','CLASSNAME');
			$('#dg').datagrid('hideColumn','BRANDCODE');
			$('#dg').datagrid('hideColumn','BRANDNAME');
			$('#dg').datagrid('hideColumn','SERIALNUMBER');
			$('#dg').datagrid('hideColumn','GOODS_NAME');
			$('#dg').datagrid('hideColumn','GOODS_PRICE');
			$('#dg').datagrid('hideColumn','FULL_BUY_COUNT');
			$('#dg').datagrid('hideColumn','GOODS_PURCHASE_PRICE');
			$('#dg').datagrid('hideColumn','GOODS_SPECIFICATIONS');
			$('#dg').datagrid('hideColumn','GOODS_UNIT');
			$('#dg').datagrid('hideColumn','REDUCE_MONEY');
			$('#dg').datagrid('hideColumn','ADD_MONEY');
		
			$('#dg').datagrid('showColumn','FULL_BUY_MONEY');
			$('#dg').datagrid('showColumn','ADD_MORE_MONEY');
			$('#dg').datagrid('showColumn','FREE_GOODSIDS');
		}
		if (zcCodeScope=="2") {
			$('#dg').datagrid('hideColumn','BRANDCODE');
			$('#dg').datagrid('hideColumn','BRANDNAME');
			$('#dg').datagrid('hideColumn','SERIALNUMBER');
			$('#dg').datagrid('hideColumn','GOODS_NAME');
			$('#dg').datagrid('hideColumn','GOODS_PRICE');
			$('#dg').datagrid('hideColumn','FULL_BUY_COUNT');	
			$('#dg').datagrid('hideColumn','GOODS_PURCHASE_PRICE');
			$('#dg').datagrid('hideColumn','GOODS_SPECIFICATIONS');
			$('#dg').datagrid('hideColumn','GOODS_UNIT');
			$('#dg').datagrid('hideColumn','REDUCE_MONEY');
			$('#dg').datagrid('hideColumn','ADD_MONEY');
			
			$('#dg').datagrid('showColumn','CLASSCODE');	
			$('#dg').datagrid('showColumn','CLASSNAME');	
			$('#dg').datagrid('showColumn','ADD_MORE_MONEY');
			$('#dg').datagrid('showColumn','FULL_BUY_MONEY');
			$('#dg').datagrid('showColumn','FREE_GOODSIDS');
		}
		if (zcCodeScope=="3") {
			$('#dg').datagrid('hideColumn','CLASSCODE');
			$('#dg').datagrid('hideColumn','CLASSNAME');
			$('#dg').datagrid('hideColumn','SERIALNUMBER');
			$('#dg').datagrid('hideColumn','GOODS_NAME');
			$('#dg').datagrid('hideColumn','GOODS_PRICE');
			$('#dg').datagrid('hideColumn','FULL_BUY_COUNT');
			$('#dg').datagrid('hideColumn','GOODS_PURCHASE_PRICE');
			$('#dg').datagrid('hideColumn','GOODS_SPECIFICATIONS');
			$('#dg').datagrid('hideColumn','GOODS_UNIT');
			$('#dg').datagrid('hideColumn','REDUCE_MONEY');
			$('#dg').datagrid('hideColumn','ADD_MONEY');
			
			$('#dg').datagrid('showColumn','BRANDCODE');
			$('#dg').datagrid('showColumn','BRANDNAME');
			$('#dg').datagrid('showColumn','ADD_MORE_MONEY');
			$('#dg').datagrid('showColumn','FULL_BUY_MONEY');
			$('#dg').datagrid('showColumn','FREE_GOODSIDS');
		}
		if (zcCodeScope=="4") {
			$('#dg').datagrid('hideColumn','CLASSCODE');
			$('#dg').datagrid('hideColumn','CLASSNAME');
			$('#dg').datagrid('hideColumn','BRANDCODE');
			$('#dg').datagrid('hideColumn','BRANDNAME');
			$('#dg').datagrid('hideColumn','FULL_BUY_COUNT');
			$('#dg').datagrid('hideColumn','REDUCE_MONEY');
			$('#dg').datagrid('hideColumn','ADD_MONEY');
			
			$('#dg').datagrid('showColumn','SERIALNUMBER');
			$('#dg').datagrid('showColumn','GOODS_NAME');
			$('#dg').datagrid('showColumn','GOODS_PRICE');
			$('#dg').datagrid('showColumn','ADD_MORE_MONEY');
			$('#dg').datagrid('showColumn','FULL_BUY_MONEY');
			$('#dg').datagrid('showColumn','GOODS_PURCHASE_PRICE');
			$('#dg').datagrid('showColumn','GOODS_SPECIFICATIONS');
			$('#dg').datagrid('showColumn','GOODS_UNIT');
			$('#dg').datagrid('showColumn','FREE_GOODSIDS');
		}
	}
	if (zcCodeMode =="4") {
		if (zcCodeScope=="1") {
			$('#dg').datagrid('hideColumn','CLASSCODE');
			$('#dg').datagrid('hideColumn','CLASSNAME');
			$('#dg').datagrid('hideColumn','BRANDCODE');
			$('#dg').datagrid('hideColumn','BRANDNAME');
			$('#dg').datagrid('hideColumn','SERIALNUMBER');
			$('#dg').datagrid('hideColumn','GOODS_NAME');
			$('#dg').datagrid('hideColumn','GOODS_PRICE');
			$('#dg').datagrid('hideColumn','GOODS_PURCHASE_PRICE');
			$('#dg').datagrid('hideColumn','GOODS_SPECIFICATIONS');
			$('#dg').datagrid('hideColumn','GOODS_UNIT');
			$('#dg').datagrid('hideColumn','REDUCE_MONEY');
			$('#dg').datagrid('hideColumn','ADD_MONEY');
			$('#dg').datagrid('hideColumn','FULL_BUY_MONEY');
			$('#dg').datagrid('hideColumn','ADD_MORE_MONEY');
			
			$('#dg').datagrid('showColumn','FULL_BUY_COUNT');
			$('#dg').datagrid('showColumn','FREE_GOODSIDS');
		}
		if (zcCodeScope=="2") {
			$('#dg').datagrid('hideColumn','BRANDCODE');
			$('#dg').datagrid('hideColumn','BRANDNAME');
			$('#dg').datagrid('hideColumn','SERIALNUMBER');
			$('#dg').datagrid('hideColumn','GOODS_NAME');
			$('#dg').datagrid('hideColumn','GOODS_PRICE');
			$('#dg').datagrid('hideColumn','GOODS_PURCHASE_PRICE');
			$('#dg').datagrid('hideColumn','GOODS_SPECIFICATIONS');
			$('#dg').datagrid('hideColumn','GOODS_UNIT');
			$('#dg').datagrid('hideColumn','REDUCE_MONEY');
			$('#dg').datagrid('hideColumn','ADD_MONEY');
			$('#dg').datagrid('hideColumn','FULL_BUY_MONEY');
			$('#dg').datagrid('hideColumn','ADD_MORE_MONEY');
			
			$('#dg').datagrid('showColumn','CLASSCODE');	
			$('#dg').datagrid('showColumn','CLASSNAME');	
			$('#dg').datagrid('showColumn','FULL_BUY_COUNT');
			$('#dg').datagrid('showColumn','FREE_GOODSIDS');
		}
		if (zcCodeScope=="3") {
			$('#dg').datagrid('hideColumn','CLASSCODE');
			$('#dg').datagrid('hideColumn','CLASSNAME');
			$('#dg').datagrid('hideColumn','SERIALNUMBER');
			$('#dg').datagrid('hideColumn','GOODS_NAME');
			$('#dg').datagrid('hideColumn','GOODS_PRICE');
			
			$('#dg').datagrid('hideColumn','GOODS_PURCHASE_PRICE');
			$('#dg').datagrid('hideColumn','GOODS_SPECIFICATIONS');
			$('#dg').datagrid('hideColumn','GOODS_UNIT');
			$('#dg').datagrid('hideColumn','REDUCE_MONEY');
			$('#dg').datagrid('hideColumn','ADD_MONEY');
			$('#dg').datagrid('hideColumn','ADD_MORE_MONEY');
			$('#dg').datagrid('hideColumn','FULL_BUY_MONEY');
			
			$('#dg').datagrid('showColumn','BRANDCODE');
			$('#dg').datagrid('showColumn','BRANDNAME');
			$('#dg').datagrid('showColumn','FREE_GOODSIDS');
			$('#dg').datagrid('showColumn','FULL_BUY_COUNT');
		}
		if (zcCodeScope=="4") {
			$('#dg').datagrid('hideColumn','CLASSCODE');
			$('#dg').datagrid('hideColumn','CLASSNAME');
			$('#dg').datagrid('hideColumn','BRANDCODE');
			$('#dg').datagrid('hideColumn','BRANDNAME');
			$('#dg').datagrid('hideColumn','REDUCE_MONEY');
			$('#dg').datagrid('hideColumn','ADD_MONEY');
			$('#dg').datagrid('hideColumn','ADD_MORE_MONEY');
			$('#dg').datagrid('hideColumn','FULL_BUY_MONEY');
			
			$('#dg').datagrid('showColumn','SERIALNUMBER');
			$('#dg').datagrid('showColumn','GOODS_NAME');
			$('#dg').datagrid('showColumn','GOODS_PRICE');
			$('#dg').datagrid('showColumn','FULL_BUY_COUNT');
			$('#dg').datagrid('showColumn','GOODS_PURCHASE_PRICE');
			$('#dg').datagrid('showColumn','GOODS_SPECIFICATIONS');
			$('#dg').datagrid('showColumn','GOODS_UNIT');
			$('#dg').datagrid('showColumn','FREE_GOODSIDS');
		}
	}
	$('#dg').datagrid('reload');
}


function openChoseGoodsFull(){
	debugger;
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
	var idStr = "";
	var fullBuyMoney ="";
	var addMoreMoney = "";
	var fullBuyCount ="";
	var addMoney ="";
	var reduceMoney = "";
	if ($('#dg').datagrid('getData')!=null) {
		var dataRows = $('#dg').datagrid('getData').rows;
		if (dataRows!=null && dataRows.length>0) {
			
		
	for (var i = 0; i < dataRows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		idStr += dataRows[i].ID +",";
		if (dataRows[i].FULL_BUY_MONEY == null || dataRows[i].FULL_BUY_MONEY == ''
			|| dataRows[i].FULL_BUY_MONEY == 'undefiend'){
			fullBuyMoney +="0.0,";
		}else{
			fullBuyMoney +=dataRows[i].FULL_BUY_MONEY +",";
		}
		if (dataRows[i].ADD_MORE_MONEY == null || dataRows[i].ADD_MORE_MONEY == ''
			|| dataRows[i].ADD_MORE_MONEY == 'undefiend'){
			addMoreMoney +="0.0,";
		}else{
			addMoreMoney +=dataRows[i].ADD_MORE_MONEY+",";
		}
		if (dataRows[i].FULL_BUY_COUNT == null || dataRows[i].FULL_BUY_COUNT == ''
			|| dataRows[i].FULL_BUY_COUNT == 'undefiend'){
			fullBuyCount +="0.0,";
		}else{
			fullBuyCount +=dataRows[i].FULL_BUY_COUNT+",";
		}
		if (dataRows[i].ADD_MONEY == null || dataRows[i].ADD_MONEY == ''
			|| dataRows[i].ADD_MONEY == 'undefiend'){
			addMoney +="0.0,";
		}else{
			addMoney +=dataRows[i].ADD_MONEY+",";
		}
		if (dataRows[i].REDUCE_MONEY == null || dataRows[i].REDUCE_MONEY == ''
			|| dataRows[i].REDUCE_MONEY == 'undefiend'){
			reduceMoney +="0.0,";
		}else{
			reduceMoney +=dataRows[i].REDUCE_MONEY+",";
		}
		$('#dg').datagrid('beginEdit', i);
	}
		}
	}
	$.ajax({
		type : "get",
		url : Utils.getRootPath() + '/buyFullSend/buyFullSend/openChoseGoods?zcCodeScope='
				+ zcCodeScope 
				+ '&idStr=' + idStr
				+ '&fullBuyMoney=' + fullBuyMoney
				+ '&addMoreMoney=' + addMoreMoney
				+ '&fullBuyCount=' + fullBuyCount
				+ '&addMoney=' + addMoney
				+ '&reduceMoney=' + reduceMoney,
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
//赠送商品选择
function openChosePresent(){
	debugger;
	var zcCodeScope ="4";
	var zcCodeMode = $('#zcCodeMode').val();
	if(zcCodeMode!="3" && zcCodeMode!="4"){
		$.messager.alert('提示', '该选择只在买满送商品活动有效！', 'warning');
		return;
	}
	var rowIndex = $('#dg').datagrid('getSelections');
	if (rowIndex.length == 0 ) {
		$.messager.alert('提示', '请选择一条数据赠送商品！', 'warning');
		return;
	}
	if(rowIndex.length >1){
		$.messager.alert('提示', '只能选择一条数据赠送商品！', 'warning');
		return;
	}
	var id="";
	for (var i = 0; i < rowIndex.length; i++) {
		 id = rowIndex[0].ID ;
	}
	
	var dataRows = $('#dg').datagrid('getData').rows;
	var ids = "";
	var fullBuyMoney ="";
	var addMoreMoney = "";
	var fullBuyCount ="";
	for (var i = 0; i < dataRows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		ids += dataRows[i].ID +",";
		if (dataRows[i].FULL_BUY_MONEY == null || dataRows[i].FULL_BUY_MONEY == ''
			|| dataRows[i].FULL_BUY_MONEY == 'undefiend'){
			fullBuyMoney +="0.0,";
		}else{
			fullBuyMoney +=dataRows[i].FULL_BUY_MONEY +",";
		}
		if (dataRows[i].ADD_MORE_MONEY == null || dataRows[i].ADD_MORE_MONEY == ''
			|| dataRows[i].ADD_MORE_MONEY == 'undefiend'){
			addMoreMoney +="0.0,";
		}else{
			addMoreMoney +=dataRows[i].ADD_MORE_MONEY+",";
		}
		if (dataRows[i].FULL_BUY_COUNT == null || dataRows[i].FULL_BUY_COUNT == ''
			|| dataRows[i].FULL_BUY_COUNT == 'undefiend'){
			fullBuyCount +="0.0,";
		}else{
			fullBuyCount +=dataRows[i].FULL_BUY_COUNT+",";
		}
		$('#dg').datagrid('beginEdit', i);
	}
	$.ajax({
		type : "get",
		url : Utils.getRootPath() + '/buyFullSend/buyFullSend/openChosePresent?zcCodeScope='
				+ zcCodeScope +'&id=' +id
				+'&fullBuyMoney=' +fullBuyMoney
				+'&addMoreMoney=' +addMoreMoney
				+'&fullBuyCount=' +fullBuyCount
				+'&ids=' +ids,
		data : {
			
		},
		success : function(data) {
			$('#selectOptions').window({
				title : '商品列表',
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
		+ '/buyFullSend/buyFullSend/addBuyFullSendGoodsToItems?ids=' + ids
		+ '&zcCodeScope=' + zcCodeScope;
	}else{
	    url = Utils.getRootPath()
			+ '/buyFullSend/buyFullSend/addBuyFullSendGoodsToEditItems?ids=' + ids
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

function addPresent(num) {
	debugger;
	var zcSalesPromotionItemId = $('#zcSalesPromotionItemId').val();
	var zcCodeScope = num +"" ;
	
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
	var url = Utils.getRootPath()
			+ '/buyFullSend/buyFullSend/addBuyFullSendPresentItems?ids=' + ids
			+ '&zcCodeScope=' + zcCodeScope
			+ '&zcSalesPromotionItemId=' + zcSalesPromotionItemId;
	
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
function saveLineToFullBuy(formId) {
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
	var fullBuyMoney ="";
	var addMoreMoney = "";
	var fullBuyCount ="";
	var addMoney ="";
	var reduceMoney = "";
	
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		$('#dg').datagrid('beginEdit', i);
		ids += data.rows[i].ID + ",";
		if (data.rows[i].ADD_MORE_MONEY == null || data.rows[i].ADD_MORE_MONEY == ''
			|| data.rows[i].ADD_MORE_MONEY == 'undefiend') {
			addMoreMoney += "0.00,";
		} else {
			addMoreMoney += data.rows[i].ADD_MORE_MONEY + ",";
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
		if (data.rows[i].ADD_MONEY == null || data.rows[i].ADD_MONEY == ''
		|| data.rows[i].ADD_MONEY == 'undefiend') {
			addMoney  += "0.00,";
		} else {
			addMoney += data.rows[i].ADD_MONEY + " ,";
		}
		if (data.rows[i].REDUCE_MONEY == null || data.rows[i].REDUCE_MONEY == ''
			|| data.rows[i].REDUCE_MONEY == 'undefiend') {
				reduceMoney  += "0.00,";
			} else {
				reduceMoney += data.rows[i].REDUCE_MONEY + " ,";
			}
	}
	var data = $('#dg').datagrid('getData');
	
	var url = Utils.getRootPath()
			+ '/buyFullSend/buyFullSend/createZcSalesPromotion?ids=' + ids
			+ '&addMoreMoney=' + addMoreMoney + '&data=' + data + '&fullBuyMoney=' + fullBuyMoney
			+ '&fullBuyCount=' + fullBuyCount+ '&addMoney=' + addMoney
			+ '&branchCode=' + branchCode
			+ '&chkValue=' + chkValue
			+ '&reduceMoney=' + reduceMoney;
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
						window.parent.loadDataGrid('buyFullCount');
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
	var idStr = "";
	var fullBuyMoney ="";
	var addMoreMoney = "";
	var fullBuyCount ="";
	var addMoney ="";
	var reduceMoney = "";
	if ($('#dg').datagrid('getData')!=null) {
		var dataRows = $('#dg').datagrid('getData').rows;
		if (dataRows!=null && dataRows.length>0) {
			
		
	for (var i = 0; i < dataRows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		idStr += dataRows[i].ID +",";
		if (dataRows[i].FULL_BUY_MONEY == null || dataRows[i].FULL_BUY_MONEY == ''
			|| dataRows[i].FULL_BUY_MONEY == 'undefiend'){
			fullBuyMoney +="0.0,";
		}else{
			fullBuyMoney +=dataRows[i].FULL_BUY_MONEY +",";
		}
		if (dataRows[i].ADD_MORE_MONEY == null || dataRows[i].ADD_MORE_MONEY == ''
			|| dataRows[i].ADD_MORE_MONEY == 'undefiend'){
			addMoreMoney +="0.0,";
		}else{
			addMoreMoney +=dataRows[i].ADD_MORE_MONEY+",";
		}
		if (dataRows[i].FULL_BUY_COUNT == null || dataRows[i].FULL_BUY_COUNT == ''
			|| dataRows[i].FULL_BUY_COUNT == 'undefiend'){
			fullBuyCount +="0.0,";
		}else{
			fullBuyCount +=dataRows[i].FULL_BUY_COUNT+",";
		}
		if (dataRows[i].ADD_MONEY == null || dataRows[i].ADD_MONEY == ''
			|| dataRows[i].ADD_MONEY == 'undefiend'){
			addMoney +="0.0,";
		}else{
			addMoney +=dataRows[i].ADD_MONEY+",";
		}
		if (dataRows[i].REDUCE_MONEY == null || dataRows[i].REDUCE_MONEY == ''
			|| dataRows[i].REDUCE_MONEY == 'undefiend'){
			reduceMoney +="0.0,";
		}else{
			reduceMoney +=dataRows[i].REDUCE_MONEY+",";
		}
		$('#dg').datagrid('beginEdit', i);
	}
		}
	}
	$.messager.confirm('确认 ', '确定要删除 ？', function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : Utils.getRootPath()
						+ '/buyFullSend/buyFullSend/deleteChose?addMoney=' + addMoney
						+ '&idStr=' + idStr + '&fullBuyMoney=' + fullBuyMoney
						+ '&fullBuyCount=' + fullBuyCount
						+ '&reduceMoney=' + reduceMoney
						+ '&addMoreMoney=' + addMoreMoney,
				data : {
					id : ids
				},
				async : false,
				dataType : 'json',
				success : function(result) {
					if (result.result) {
						$.messager.alert('提示', '删除成功', 'info', function() {
							window.parent.loadDataGrid('buyFullCount');
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
}

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
	
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		$('#dg').datagrid('beginEdit', i);
		idStr += data.rows[i].ID + ",";
	}
	
		
			$.ajax({
				type : "post",
				url : Utils.getRootPath()
						+ '/buyFullSend/buyFullSend/deleteChoseChange?idStr=' + idStr,
				data : {
					id : ids
				},
				async : false,
				dataType : 'json',
				success : function(result) {
					
				}
			});
		
		
	
}

//根据商品表直接选中行后生成促销订单
function EidtLineToBuyFullSend(formId) {
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
	var addMoney = "";
	var fullBuyMoney = "";
	var fullBuyCount = "";
	var addMoreMoney = "";
	var reduceMoney = "";
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		$('#dg').datagrid('beginEdit', i);
		ids += data.rows[i].ID + ",";
		if(data.rows[i].ADD_MONEY ==null || data.rows[i].ADD_MONEY==''
			||data.rows[i].ADD_MONEY =='undefiend'){
			addMoney += "0.0,";
		}else{
			addMoney += data.rows[i].ADD_MONEY + ",";
		}
		if(data.rows[i].FULL_BUY_MONEY ==null || data.rows[i].FULL_BUY_MONEY==''
			||data.rows[i].FULL_BUY_MONEY =='undefiend'){
			fullBuyMoney += "0.0,";
		}else{
			fullBuyMoney += data.rows[i].FULL_BUY_MONEY + ",";
		}
		
		if(data.rows[i].FULL_BUY_COUNT ==null || data.rows[i].FULL_BUY_COUNT==''
			||data.rows[i].FULL_BUY_COUNT =='undefiend'){
			fullBuyCount += "0.0,";
		}else{
			fullBuyCount += data.rows[i].FULL_BUY_COUNT + ",";
		}
		
		if(data.rows[i].ADD_MORE_MONEY ==null || data.rows[i].ADD_MORE_MONEY==''
			||data.rows[i].ADD_MORE_MONEY =='undefiend'){
			addMoreMoney += "0.0,";
		}else{
			addMoreMoney += data.rows[i].ADD_MORE_MONEY + ",";
		}
		
		if(data.rows[i].REDUCE_MONEY ==null || data.rows[i].REDUCE_MONEY==''
			||data.rows[i].REDUCE_MONEY =='undefiend'){
			reduceMoney += "0.0,";
		}else{
			reduceMoney += data.rows[i].REDUCE_MONEY + ",";
		}
	}
	var data = $('#dg').datagrid('getData');
	
	var url = Utils.getRootPath()
			+ '/buyFullSend/buyFullSend/editZcSalesPromotion?ids=' + ids
			+ '&addMoney=' + addMoney + '&data=' + data + '&fullBuyMoney=' + fullBuyMoney
			+ '&fullBuyCount=' + fullBuyCount+ '&addMoreMoney=' + addMoreMoney
			+ '&branchCode=' + branchCode
			+ '&chkValue=' + chkValue
			+ '&reduceMoney=' + reduceMoney;
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
						window.parent.loadDataGrid('buyFullCount');
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

function openEditGoodsFull() {
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
	var addMoney = "";
	var fullBuyMoney = "";
	var fullBuyCount = "";
	var reduceMoney = "";
	var addMoreMoney ="";
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		ids += data.rows[i].ID +",";
		if (data.rows[i].ADD_MONEY == null || data.rows[i].ADD_MONEY == ''
			|| data.rows[i].ADD_MONEY == 'undefiend') {
			addMoney += "0.00,";
	} else {
		addMoney += data.rows[i].ADD_MONEY + ",";
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
	if (data.rows[i].REDUCE_MONEY == null || data.rows[i].REDUCE_MONEY == ''
		|| data.rows[i].REDUCE_MONEY == 'undefiend') {
		reduceMoney  += "0.00,";
	} else {
		reduceMoney += data.rows[i].REDUCE_MONEY + " ,";
	}
	if (data.rows[i].ADD_MORE_MONEY == null || data.rows[i].ADD_MORE_MONEY == ''
		|| data.rows[i].ADD_MORE_MONEY == 'undefiend') {
		addMoreMoney  += "0.00,";
	} else {
		addMoreMoney += data.rows[i].ADD_MORE_MONEY + " ,";
	}
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
				+ '/buyFullSend/buyFullSend/openAddGoods?ids=' + ids
				+ '&addMoney=' + addMoney + '&data=' + data + '&fullBuyMoney=' + fullBuyMoney
				+ '&fullBuyCount=' + fullBuyCount+ '&reduceMoney=' + reduceMoney
				+ '&zcSalesPromotionId='+ zcSalesPromotionId
				+ '&addMoreMoney='+ addMoreMoney,
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
	var idStr = "";
	var fullBuyMoney ="";
	var addMoreMoney = "";
	var fullBuyCount ="";
	var addMoney ="";
	var reduceMoney = "";
	if ($('#dg').datagrid('getData')!=null) {
		var dataRows = $('#dg').datagrid('getData').rows;
		if (dataRows!=null && dataRows.length>0) {
			
		
	for (var i = 0; i < dataRows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		idStr += dataRows[i].ID +",";
		if (dataRows[i].FULL_BUY_MONEY == null || dataRows[i].FULL_BUY_MONEY == ''
			|| dataRows[i].FULL_BUY_MONEY == 'undefiend'){
			fullBuyMoney +="0.0,";
		}else{
			fullBuyMoney +=dataRows[i].FULL_BUY_MONEY +",";
		}
		if (dataRows[i].ADD_MORE_MONEY == null || dataRows[i].ADD_MORE_MONEY == ''
			|| dataRows[i].ADD_MORE_MONEY == 'undefiend'){
			addMoreMoney +="0.0,";
		}else{
			addMoreMoney +=dataRows[i].ADD_MORE_MONEY+",";
		}
		if (dataRows[i].FULL_BUY_COUNT == null || dataRows[i].FULL_BUY_COUNT == ''
			|| dataRows[i].FULL_BUY_COUNT == 'undefiend'){
			fullBuyCount +="0.0,";
		}else{
			fullBuyCount +=dataRows[i].FULL_BUY_COUNT+",";
		}
		if (dataRows[i].ADD_MONEY == null || dataRows[i].ADD_MONEY == ''
			|| dataRows[i].ADD_MONEY == 'undefiend'){
			addMoney +="0.0,";
		}else{
			addMoney +=dataRows[i].ADD_MONEY+",";
		}
		if (dataRows[i].REDUCE_MONEY == null || dataRows[i].REDUCE_MONEY == ''
			|| dataRows[i].REDUCE_MONEY == 'undefiend'){
			reduceMoney +="0.0,";
		}else{
			reduceMoney +=dataRows[i].REDUCE_MONEY+",";
		}
		$('#dg').datagrid('beginEdit', i);
	}
		}
	}
	var  url = Utils.getRootPath()
			+ '/buyFullSend/buyFullSend/addDiscountScopeWhole?ids=' + ids
			+ '&zcCodeScope=' + zcCodeScope
			+ '&zcSalesPromotionId=' + zcSalesPromotionId
			+ '&zcCodeMode=' + zcCodeMode
			+ '&idStr=' + idStr
			+ '&fullBuyMoney=' + fullBuyMoney
			+ '&addMoreMoney=' + addMoreMoney
			+ '&fullBuyCount=' + fullBuyCount
			+ '&addMoney=' + addMoney
			+ '&reduceMoney=' + reduceMoney;
	
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

//删除
function destroyInfo() {
	debugger;
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	var row = rows[0];
	if (row.AUDIT_STATUS == "2") {
		$.messager.alert('提示', '该促销单已通过审核，不可刪除！', 'warning');
		return;
	} else if (row.AUDIT_STATUS == "3") {
		$.messager.alert('提示', '该促销单审核未通过，不可刪除！', 'warning');
		return;
	} else if (row.AUDIT_STATUS == "1") {
		$.messager.alert('提示', '该促销单已提交，不可刪除！', 'warning');
		return;
	} else if (row.AUDIT_STATUS == "4") {
		$.messager.alert('提示', '该促销单已完成，不可刪除！', 'warning');
		return;
	} else {
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
							+ '/buyFullSend/buyFullSend/delete',
					data : {
						id : ids
					},
					async : false,
					dataType : 'json',
					success : function(result) {
						if (result.result) {
							$.messager.alert('提示', '删除成功', 'info', function() {
								window.parent.loadDataGrid('buyFullCount');
								window.parent.closeWinForm();
							});
						} else if (result.status == "error") {
							$.messager.alert('提示', '删除失败，请联系管理员！', 'error');
						}
					}
				});
			}
			;
		});
	}
	;
}




//打开审核页面
function openCheck(title, x, y) {
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	var row = rows[0];
	if (row.AUDIT_STATUS == "2") {
		$.messager.alert('提示', '该收货单已通过审核！', 'warning');
		return;
	} else if (row.AUDIT_STATUS == "3") {
		$.messager.alert('提示', '该收货单正在处理中！', 'warning');
		return;
	} else if (row.AUDIT_STATUS == "0") {
		$.messager.alert('提示', '该收货单还未提交！', 'warning');
		return;
	} else if (row.AUDIT_STATUS == "4") {
		$.messager.alert('提示', '该收货单已完成！', 'warning');
		return;
	} else if (row.AUDIT_STATUS == "5") {
		$.messager.alert('提示', '该收货单已作废！', 'warning');
		return;
	} else {
		var url = Utils.getRootPath()
				+ '/buyFullSend/buyFullSend/gotoEditCheck?id='
				+ row.ID;
		add(url, title, x, y);
	}
}



//审核通过
function checkPass() {
	var data = $('#dg').datagrid('getData');
	var ids = "";
	var allDiscount = "";
	var fullBuyMoney = "";
	var fullBuyCount = "";
	var discount = "";
	for (var i = 0; i < data.rows.length; i++) {
		ids += data.rows[i].ID + ",";
		if(data.rows[i].All_DISCOUNT ==null || data.rows[i].All_DISCOUNT==''
			||data.rows[i].All_DISCOUNT =='undefiend'){
			allDiscount += "0.0,";
		}else{
			allDiscount += data.rows[i].All_DISCOUNT + ",";
		}
		if(data.rows[i].FULL_BUY_MONEY ==null || data.rows[i].FULL_BUY_MONEY==''
			||data.rows[i].FULL_BUY_MONEY =='undefiend'){
			fullBuyMoney += "0.0,";
		}else{
			fullBuyMoney += data.rows[i].FULL_BUY_MONEY + ",";
		}
		
		if(data.rows[i].FULL_BUY_COUNT ==null || data.rows[i].FULL_BUY_COUNT==''
			||data.rows[i].FULL_BUY_COUNT =='undefiend'){
			fullBuyCount += "0.0,";
		}else{
			fullBuyCount += data.rows[i].FULL_BUY_COUNT + ",";
		}
		
		if(data.rows[i].DISCOUNT ==null || data.rows[i].DISCOUNT==''
			||data.rows[i].DISCOUNT =='undefiend'){
			discount += "0.0,";
		}else{
			discount += data.rows[i].DISCOUNT + ",";
		}
	}
	if (ids.length == 0) {
		$.messager.alert('提示', '没有商品，确定审核通过？', 'warning');
		return;
	}
	var id = $('#zcSalesPromotionId').val();
	var url = Utils.getRootPath()
			+ '/buyFullSend/buyFullSend/checkPass?id=' + id + '&ids='
			+ ids + '&allDiscount=' + allDiscount
			+ '&fullBuyMoney=' + fullBuyMoney
			+ '&fullBuyCount=' + fullBuyCount
			+ '&discount=' + discount;
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
							window.parent.loadDataGrid('buyFullCount');
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
function checkNoPass(type) {
	var id = $('#zcSalesPromotionId').val();
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
	debugger;
	var url = Utils.getRootPath()
			+ '/buyFullSend/buyFullSend/checkNoPass';
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
			if (result.result) {
				$.messager.alert('提示', '审核完成', 'info', function() {
					window.parent.loadDataGrid('buyFullCount');
					window.parent.closeWinForm();
				});
			} else if(result.status == "error"){
				$.messager.alert('提示', '审核失败，请联系管理员！', 'error');
			}
		}
	});
}