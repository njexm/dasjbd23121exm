/**
 * 跳转新增页面方法
 * @param title
 * @param x
 * @param y
 */
function addSpecialPromotion(title, x, y){
	var url = Utils.getRootPath() + '/specialPrice/add';
	add(url, title, x, y);
}

//打开页面
function open(url, title, x, y) {
	window.parent.initAdd(url, title, x, y);
}
/**
 * 促销方式改变
 * 
 */
function codeModeChange(mode){
	var type = $('#zcCodeMode').val();
	if(mode == "1"){ //新增时
		//删除其他模式下促销信息
		if(type != ""){
			$.ajax({
				type : "post",
				url : Utils.getRootPath() + '/specialPrice/deleteAddPromotions',
				async : true,
				dataType : 'json',
				data : {
				},
				success : function(data) {
					if(data.result == true){
						if(type == "1"){//直接特价
							$('#dg').datagrid('showColumn','LIMIT_NUMBER');
							$('#dg').datagrid('hideColumn','GROUP_NUMBER');
							$('#dg').datagrid('hideColumn','NUMS');
							$('#dg').datagrid('hideColumn','FULL_BUY_COUNT');
							$('#dg').datagrid('hideColumn','BEGIN_TIME_FRAME');
							$('#dg').datagrid('hideColumn','END_TIME_FRAME');
						}else if(type == "2"){
							$('#dg').datagrid('showColumn','FULL_BUY_COUNT');
							$('#dg').datagrid('hideColumn','GROUP_NUMBER');
							$('#dg').datagrid('hideColumn','NUMS');
							$('#dg').datagrid('hideColumn','LIMIT_NUMBER');
							$('#dg').datagrid('hideColumn','BEGIN_TIME_FRAME');
							$('#dg').datagrid('hideColumn','END_TIME_FRAME');
						}else if(type == "3"){
							$('#dg').datagrid('hideColumn','LIMIT_NUMBER');
							$('#dg').datagrid('hideColumn','GROUP_NUMBER');
							$('#dg').datagrid('hideColumn','NUMS');
							$('#dg').datagrid('hideColumn','FULL_BUY_COUNT');
							$('#dg').datagrid('hideColumn','BEGIN_TIME_FRAME');
							$('#dg').datagrid('hideColumn','END_TIME_FRAME');
						}else if(type == "4"){
							$('#dg').datagrid('showColumn','BEGIN_TIME_FRAME');
							$('#dg').datagrid('showColumn','END_TIME_FRAME');
							$('#dg').datagrid('hideColumn','GROUP_NUMBER');
							$('#dg').datagrid('hideColumn','NUMS');
							$('#dg').datagrid('hideColumn','FULL_BUY_COUNT');
							$('#dg').datagrid('hideColumn','LIMIT_NUMBER');
						}else if(type == "5"){
							$('#dg').datagrid('showColumn','NUMS');
							$('#dg').datagrid('showColumn','GROUP_NUMBER');
							$('#dg').datagrid('hideColumn','LIMIT_NUMBER');
							$('#dg').datagrid('hideColumn','FULL_BUY_COUNT');
							$('#dg').datagrid('hideColumn','BEGIN_TIME_FRAME');
							$('#dg').datagrid('hideColumn','END_TIME_FRAME');
						}
						$('#dg').datagrid('reload');
					}else{
						$.messager.alert('提示', '删除失败，请联系管理员！', 'error');
					}
				}
			});
		}
	}else{     //编辑时
		if(type == "1"){//直接特价
			$('#dg').datagrid('showColumn','LIMIT_NUMBER');
			$('#dg').datagrid('hideColumn','GROUP_NUMBER');
			$('#dg').datagrid('hideColumn','NUMS');
			$('#dg').datagrid('hideColumn','FULL_BUY_COUNT');
			$('#dg').datagrid('hideColumn','BEGIN_TIME_FRAME');
			$('#dg').datagrid('hideColumn','END_TIME_FRAME');
		}else if(type == "2"){
			$('#dg').datagrid('showColumn','FULL_BUY_COUNT');
			$('#dg').datagrid('hideColumn','GROUP_NUMBER');
			$('#dg').datagrid('hideColumn','NUMS');
			$('#dg').datagrid('hideColumn','LIMIT_NUMBER');
			$('#dg').datagrid('hideColumn','BEGIN_TIME_FRAME');
			$('#dg').datagrid('hideColumn','END_TIME_FRAME');
		}else if(type == "3"){
			$('#dg').datagrid('hideColumn','LIMIT_NUMBER');
			$('#dg').datagrid('hideColumn','GROUP_NUMBER');
			$('#dg').datagrid('hideColumn','NUMS');
			$('#dg').datagrid('hideColumn','FULL_BUY_COUNT');
			$('#dg').datagrid('hideColumn','BEGIN_TIME_FRAME');
			$('#dg').datagrid('hideColumn','END_TIME_FRAME');
		}else if(type == "4"){
			$('#dg').datagrid('showColumn','BEGIN_TIME_FRAME');
			$('#dg').datagrid('showColumn','END_TIME_FRAME');
			$('#dg').datagrid('hideColumn','GROUP_NUMBER');
			$('#dg').datagrid('hideColumn','NUMS');
			$('#dg').datagrid('hideColumn','FULL_BUY_COUNT');
			$('#dg').datagrid('hideColumn','LIMIT_NUMBER');
		}else if(type == "5"){
			$('#dg').datagrid('showColumn','NUMS');
			$('#dg').datagrid('showColumn','GROUP_NUMBER');
			$('#dg').datagrid('hideColumn','LIMIT_NUMBER');
			$('#dg').datagrid('hideColumn','FULL_BUY_COUNT');
			$('#dg').datagrid('hideColumn','BEGIN_TIME_FRAME');
			$('#dg').datagrid('hideColumn','END_TIME_FRAME');
		}
		$('#dg').datagrid('reload');
	}
}

/**
 * 打开选择商品界面
 */
function openChooseGoods() {
	debugger;
	var type = $('#zcCodeMode').val();
	var zcSalesPromotionId = $('#zcSalesPromotionId').val();
	if(type == ""){
		$.messager.alert('提示', '请选择促销模式！', 'info');
		return;
	}
	var data = $('#dg').datagrid('getData');
	var ids = "";
	var groups = "";
	var numbers = "";
	var prices = "";
	var nums = "";
	var fulls = "";
	var begins = "";
	var ends = ""; 
	if (data.rows!=null && data.rows.length>0) {
		for (var i = 0; i < data.rows.length; i++) {
			$('#dg').datagrid('endEdit', i);
			$('#dg').datagrid('beginEdit', i);
			ids += data.rows[i].ID + ",";
			groups += data.rows[i].GROUP_NUMBER + ",";
			numbers += data.rows[i].LIMIT_NUMBER + ",";
			prices += data.rows[i].BARGAIN_PRICE + ",";
			nums += data.rows[i].NUMS + ",";
			fulls += data.rows[i].FULL_BUY_COUNT + ",";
			begins += data.rows[i].BEGIN_TIME_FRAME + ",";
			ends += data.rows[i].END_TIME_FRAME + ",";
		}
	}
	$.ajax({
		type : "get",
		url : Utils.getRootPath() + '/specialPrice/openChooseGoods?zcSalesPromotionId='+zcSalesPromotionId
									+'&ids='+ids
									+'&groups='+groups
									+'&numbers='+numbers
									+'&prices='+prices
									+'&nums='+nums
									+'&fulls='+fulls
									+'&begins='+begins
									+'&ends='+ends
									+'&type='+type,
		success : function(data) {
			$('#selectOptions').window({
				title : '选择商品',
				collapsible : true,
				maximizable : false,
				modal : true,
				draggable : true,
				width : 880,
				height : 440,
			});
			$('#selectOptions').html(data);
		}
	});
}

/**
 * 添加商品
 */
function addGoods(){
	var rows = $('#goodsDg').datagrid('getSelections');
	var zcSalesPromotionId = $('#zcSalesPromotionId').val();
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择至少一条数据！', 'warning');
		return;
	}
	var ids = "";
	for (var i = 0; i < rows.length; i++) {
		ids += rows[i].ID + ",";
	}
	var url = Utils.getRootPath()
			+ '/specialPrice/addGoodsToItems?zcSalesPromotionId='+zcSalesPromotionId;
	$.ajax({
		type : "post",
		url : url,
		async : true,
		dataType : 'json',
		data : {
			'ids' : ids,
		},
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

/**
 * 删除商品
 */
function deleteChoose(){
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要删除的数据！', 'warning');
		return;
	}
	var ids = "";
	for (var i = 0; i < rows.length; i++) {
		ids += rows[i].ID + ",";
	}
	var data = $('#dg').datagrid('getData');
	var type = $('#zcCodeMode').val();
	var idStr = "";
	var groups = "";
	var numbers = "";
	var prices = "";
	var nums = "";
	var fulls = "";
	var begins = "";
	var ends = ""; 
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		$('#dg').datagrid('beginEdit', i);
		idStr += data.rows[i].ID + ",";
		groups += data.rows[i].GROUP_NUMBER + ",";
		numbers += data.rows[i].LIMIT_NUMBER + ",";
		prices += data.rows[i].BARGAIN_PRICE + ",";
		nums += data.rows[i].NUMS + ",";
		fulls += data.rows[i].FULL_BUY_COUNT + ",";
		begins += data.rows[i].BEGIN_TIME_FRAME + ",";
		ends += data.rows[i].END_TIME_FRAME + ",";
	}
	$.messager.confirm('确认 ', '确定要删除 ？', function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : Utils.getRootPath() + '/specialPrice/deleteChoose',
				data : {
					id : ids,
					type : type,
					idStr : idStr,
					groups : groups,
					numbers : numbers,
					prices : prices,
					nums : nums,
					fulls : fulls,
					begins : begins,
					ends : ends
				},
				async : false,
				dataType : 'json',
				success : function(result) {
					if (result.result) {
						$.messager.alert('提示', '移除成功', 'info', function() {
							window.parent.loadDataGrid('purchaseOrders');
							$('#dg').datagrid('reload');
						});
					} else if (result.status == "error") {
						$.messager.alert('提示', '移除失败，请联系管理员！', 'error');
						$('#dg').datagrid('reload');
					}
				}
			});
		}
		;
	});
}

/**
 * 新增
 */
function save(formId){
	var data = $('#dg').datagrid('getData');
	if (data.rows.length == 0) {
		$.messager.alert('提示', '新建特价促销单没有商品，无需新建！', 'warning');
		return;
	}
	var type = $('#zcCodeMode').val();
	if(type == ""){
		$.messager.alert('提示', '请选择促销模式！', 'warning');
		return;
	}
	var beginDate = $('#promotionBeginDate').val();
	var endDate = $('#promotionEndDate').val();
	if(beginDate == "undefined" || beginDate == "" || beginDate == null || endDate == "undefined" || endDate == "" || endDate == null){
		$.messager.alert('提示', '请选择促销期限！', 'warning');
		return;
	}
	var promotionTitle = $('#promotionTitle').val();
	if(isEmpty(promotionTitle)){
		$.messager.alert('提示', '请选择促销方案名称！', 'warning');
		return;
	}
	var branch = $('#branchCode').val();
	if(isEmpty(branch)){
		$.messager.alert('提示', '请选择促销分店！', 'warning');
		return;
	}
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		$('#dg').datagrid('beginEdit', i);
		if(type == "1"){
			if(data.rows[i].LIMIT_NUMBER == 0 || data.rows[i].LIMIT_NUMBER == null){
				$('#dg').datagrid('beginEdit', i);
				$.messager.alert('提示', '请填写每单限量！', 'warning');
				return;
			}
		}else if(type == "2"){
			if(data.rows[i].FULL_BUY_COUNT == 0 || data.rows[i].FULL_BUY_COUNT == null){
				$('#dg').datagrid('beginEdit', i);
				$.messager.alert('提示', '请填写买满数量！', 'warning');
				return;
			}
		}else if(type == "3"){
		}else if(type == "4"){
			if(data.rows[i].BEGIN_TIME_FRAME == "undefined" || data.rows[i].BEGIN_TIME_FRAME == null){
				$('#dg').datagrid('beginEdit', i);
				$.messager.alert('提示', '请填写开始时间！', 'warning');
				return;
			}
			if(data.rows[i].END_TIME_FRAME == "undefined" || data.rows[i].END_TIME_FRAME == null){
				$('#dg').datagrid('beginEdit', i);
				$.messager.alert('提示', '请填写结束时间！', 'warning');
				return;
			}  
		}else if(type == "5"){
			if(data.rows[i].NUMS == 0 || data.rows[i].NUMS == null){
				$('#dg').datagrid('beginEdit', i);
				$.messager.alert('提示', '请填写数量！', 'warning');
				return;
			}
			if(data.rows[i].GROUP_NUMBER == 0 || data.rows[i].GROUP_NUMBER == null){
				$('#dg').datagrid('beginEdit', i);
				$.messager.alert('提示', '请填写组号！', 'warning');
				return;
			}
		}
	}
	var ids = "";
	var groups = "";
	var numbers = "";
	var prices = "";
	var nums = "";
	var fulls = "";
	var begins = "";
	var ends = ""; 
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		$('#dg').datagrid('beginEdit', i);
		ids += data.rows[i].ID + ",";
		groups += data.rows[i].GROUP_NUMBER + ",";
		numbers += data.rows[i].LIMIT_NUMBER + ",";
		prices += data.rows[i].BARGAIN_PRICE + ",";
		nums += data.rows[i].NUMS + ",";
		fulls += data.rows[i].FULL_BUY_COUNT + ",";
		begins += data.rows[i].BEGIN_TIME_FRAME + ",";
		ends += data.rows[i].END_TIME_FRAME + ",";
	}
	var chkValue ="";//定义一个数组    
    $('input[name="week"]:checked').each(function(){//遍历每一个名字为interest的复选框，其中选中的执行函数    
   	 chkValue += $(this).val() +",";//将选中的值添加到数组chk_value中    
    });
	var url = Utils.getRootPath() + '/specialPrice/createItems?ids='+ids+'&groups=' + groups
		+ '&numbers='+numbers+'&prices='+prices+'&nums='+nums+'&fulls='+fulls+'&begins='+begins+'&ends='+ends+'&branchs='+$('#branchCode').val()+'&chkValue='+chkValue;
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
						window.parent.loadDataGrid('specialPrice');
						window.parent.closeWinForm();
					});
				} else {
					$.messager.alert('提示', '新增失败，请联系管理员！', 'error');
				}
			}
		});
	}
}

/**
 * 打开分店选择页面
 */
function openChoseBranch() {
	$.ajax({
		type : "get",
		url : Utils.getRootPath() + '/discount/discount/openChoseBranch',
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

/**
 * 打开详请页面
 * @param number
 */
function openDetail(id){
	var url = Utils.getRootPath()
	+ '/specialPrice/openDetail?id=' + id;
add(url, '特价促销单详情', 1000, 550);
}


//编辑
function editSpecialPromotion(title, x, y) {
	debugger;
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
				+ '/specialPrice/gotoEditspecialPriceEdit?id='
				+ row.ID;
		open(url, title, x, y);
	}
}

/**
 * 编辑
 */
function eidt(formId){
	var data = $('#dg').datagrid('getData');
	if (data.rows.length == 0) {
		$.messager.alert('提示', '编辑特价促销单没有商品，无需新建！', 'warning');
		return;
	}
	var type = $('#zcCodeMode').val();
	if(type == ""){
		$.messager.alert('提示', '请选择促销模式！', 'warning');
		return;
	}
	var beginDate = $('#promotionBeginDate').val();
	var endDate = $('#promotionEndDate').val();
	if(beginDate == "undefined" || beginDate == "" || beginDate == null || endDate == "undefined" || endDate == "" || endDate == null){
		$.messager.alert('提示', '请选择促销期限！', 'warning');
		return;
	}
	var promotionTitle = $('#promotionTitle').val();
	if(isEmpty(promotionTitle)){
		$.messager.alert('提示', '请选择促销方案名称！', 'warning');
		return;
	}
	var branch = $('#branchCode').val();
	if(isEmpty(branch)){
		$.messager.alert('提示', '请选择促销分店！', 'warning');
		return;
	}
	var remark = $('#promotionRemark').val();
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		$('#dg').datagrid('beginEdit', i);
		if(type == "1"){
			if(data.rows[i].LIMIT_NUMBER == 0 || data.rows[i].LIMIT_NUMBER == null){
				$('#dg').datagrid('beginEdit', i);
				$.messager.alert('提示', '请填写每单限量！', 'warning');
				return;
			}
		}else if(type == "2"){
			if(data.rows[i].FULL_BUY_COUNT == 0 || data.rows[i].FULL_BUY_COUNT == null){
				$('#dg').datagrid('beginEdit', i);
				$.messager.alert('提示', '请填写买满数量！', 'warning');
				return;
			}
		}else if(type == "3"){
		}else if(type == "4"){
			if(data.rows[i].BEGIN_TIME_FRAME == "undefined" || data.rows[i].BEGIN_TIME_FRAME == null){
				$('#dg').datagrid('beginEdit', i);
				$.messager.alert('提示', '请填写开始时间！', 'warning');
				return;
			}
			if(data.rows[i].END_TIME_FRAME == "undefined" || data.rows[i].END_TIME_FRAME == null){
				$('#dg').datagrid('beginEdit', i);
				$.messager.alert('提示', '请填写结束时间！', 'warning');
				return;
			}  
		}else if(type == "5"){
			if(data.rows[i].NUMS == 0 || data.rows[i].NUMS == null){
				$('#dg').datagrid('beginEdit', i);
				$.messager.alert('提示', '请填写数量！', 'warning');
				return;
			}
			if(data.rows[i].GROUP_NUMBER == 0 || data.rows[i].GROUP_NUMBER == null){
				$('#dg').datagrid('beginEdit', i);
				$.messager.alert('提示', '请填写组号！', 'warning');
				return;
			}
		}
	}
	var ids = "";
	var groups = "";
	var numbers = "";
	var prices = "";
	var nums = "";
	var fulls = "";
	var begins = "";
	var ends = ""; 
	for (var i = 0; i < data.rows.length; i++) {
		$('#dg').datagrid('endEdit', i);
		$('#dg').datagrid('beginEdit', i);
		ids += data.rows[i].ID + ",";
		groups += data.rows[i].GROUP_NUMBER + ",";
		numbers += data.rows[i].LIMIT_NUMBER + ",";
		prices += data.rows[i].BARGAIN_PRICE + ",";
		nums += data.rows[i].NUMS + ",";
		fulls += data.rows[i].FULL_BUY_COUNT + ",";
		begins += data.rows[i].BEGIN_TIME_FRAME + ",";
		ends += data.rows[i].END_TIME_FRAME + ",";
	}
	var chkValue ="";//定义一个数组    
    $('input[name="week"]:checked').each(function(){//遍历每一个名字为interest的复选框，其中选中的执行函数    
   	 chkValue += $(this).val() +",";//将选中的值添加到数组chk_value中    
    });
	var url = Utils.getRootPath() + '/specialPrice/eidtItems?ids='+ids+'&groups=' + groups
		+ '&numbers='+numbers+'&prices='+prices+'&nums='+nums+'&fulls='+fulls+'&begins='+begins+'&ends='+ends+'&branchs='+$('#branchCode').val()+'&chkValue='+chkValue;
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
					$.messager.alert('提示', '编辑成功', 'info', function() {
						window.parent.loadDataGrid('specialPrice');
						window.parent.closeWinForm();
					});
				} else {
					$.messager.alert('提示', '新增失败，请联系管理员！', 'error');
				}
			}
		});
	}
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
	if (row.CHECK_STATE == "2") {
		$.messager.alert('提示', '该促销单已通过审核，不可刪除！', 'warning');
		return;
	} else if (row.CHECK_STATE == "3") {
		$.messager.alert('提示', '该促销单审核未通过，不可刪除！', 'warning');
		return;
	} else if (row.CHECK_STATE == "1") {
		$.messager.alert('提示', '该促销单已提交，不可刪除！', 'warning');
		return;
	} else if (row.CHECK_STATE == "4") {
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
							+ '/specialPrice/delete',
					data : {
						id : ids
					},
					async : false,
					dataType : 'json',
					success : function(result) {
						if (result.result) {
							$.messager.alert('提示', '删除成功', 'info', function() {
								window.parent.loadDataGrid('specialPrice');
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
	if (row.CHECK_STATE == "2") {
		$.messager.alert('提示', '该收货单已通过审核！', 'warning');
		return;
	} else if (row.CHECK_STATE == "3") {
		$.messager.alert('提示', '该收货单正在处理中！', 'warning');
		return;
	} else if (row.CHECK_STATE == "0") {
		$.messager.alert('提示', '该收货单还未提交！', 'warning');
		return;
	} else if (row.CHECK_STATE == "4") {
		$.messager.alert('提示', '该收货单已完成！', 'warning');
		return;
	} else if (row.CHECK_STATE == "5") {
		$.messager.alert('提示', '该收货单已作废！', 'warning');
		return;
	} else {
		var url = Utils.getRootPath()
				+ '/specialPrice/gotoEditCheck?id='
				+ row.ID;
		add(url, title, x, y);
	}
}

//审核通过
function checkPass() {
	var data = $('#dg').datagrid('getData');
	var ids = "";
	
	for (var i = 0; i < data.rows.length; i++) {
		ids += data.rows[i].ID + ",";
		
	}
	if (data.rows.length == 0) {
		$.messager.alert('提示', '没有商品，确定审核通过？', 'warning');
		return;
	}
	var id = $('#zcSalesPromotionId').val();
	var url = Utils.getRootPath()
			+ '/specialPrice/checkPass?id=' + id + '&ids='
			+ ids ;
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
							window.parent.loadDataGrid('specialPrice');
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
			+ '/specialPrice/checkNoPass';
	$.ajax({
		type : "post",
		url : url,
		data : {
			'id' : id,
			'reason' : reason,
			'type' : type
		},
		async : true,
		dataType : 'json',
		success : function(result) {
			if (result.result) {
				$.messager.alert('提示', '审核完成', 'info', function() {
					window.parent.loadDataGrid('specialPrice');
					window.parent.closeWinForm();
				});
			} else if(result.status=="error"){
				$.messager.alert('提示', '审核失败，请联系管理员！', 'error');
			}
		}
	});
}

//终止
function stop() {
	debugger;
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	var row = rows[0];
	if (row.CHECK_STATE != "2" && row.CHECK_STATE != "4") {
		$.messager.alert('提示', '该促销单未通过审核,无法中止！', 'warning');
		return;
	}  else if (row.CHECK_STATE == "4") {
		$.messager.alert('提示', '该促销单已完成，无需中止！', 'warning');
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
		$.messager.confirm('确认 ', '确定要终止？', function(r) {
			if (r) {
				$.ajax({
					type : "post",
					url : Utils.getRootPath()
							+ '/specialPrice/stop',
					data : {
						id : ids
					},
					async : false,
					dataType : 'json',
					success : function(result) {
						if (result.result) {
							$.messager.alert('提示', '终止成功', 'info', function() {
								window.parent.loadDataGrid('specialPrice');
								window.parent.closeWinForm();
							});
						} else if (result.status == "error") {
							$.messager.alert('提示', '终止失败，请联系管理员！', 'error');
						}
					}
				});
			}
			;
		});
	}
	;
}
