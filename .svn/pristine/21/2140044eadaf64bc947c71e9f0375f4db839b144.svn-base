//添加
function add(url, title, x, y) {
	window.parent.initAdd(url, title, x, y);
}

// 打开新增页面
function addInfo(title, x, y) {
	var none = $('#treeValue').val();
	if (none == null || none == "") {
		var url = Utils.getRootPath()
				+ '/goodsFile/goodsFile/gotoAddGoodsFile?id=' + '0';
		add(url, title, x, y);
		return;
	} else {
		var url = Utils.getRootPath()
				+ '/goodsFile/goodsFile/gotoAddGoodsFile?id=' + none;
		add(url, title, x, y);
	}
}

function add2(url, title, x, y) {
	window.parent.initAdd2(url, title, x, y);
}

function add3(url, title, x, y) {
	window.parent.initAdd3(url, title, x, y);
}

// 打开选择供应商页面
function openAddProvider(title, x, y) {
	var url = Utils.getRootPath() + '/goodsFile/goodsFile/providerInit';
	add2(url, title, x, y);
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
	var url = Utils.getRootPath()
			+ '/goodsFile/goodsFile/gotoEditGoodsFile?id=' + row.ID;
	add(url, title, x, y);
}

// 为商品选择供应商
function selectProvider() {
	var rows = $('#sp').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择需要操作的数据！', 'warning');
		return;
	}
	if (rows.length > 1) {
		$.messager.alert('提示', '请选择一条数据进行操作!', 'warning');
		return;
	}
	var row = rows[0];
	// $("#goods_supplier_id").val('setValue',row.ID);
	// document.getElementById("goods_supplier_id").value=row.ID;
	// $('#showWin goods_supplier_id').combobox('setValue',row.ID);
	// $('#goods_supplier_id').combobox('setText',row.PROVIDER_NICKNAME);
	// document.getElementById("goods_supplier_nickname").value=row.PROVIDER_NICKNAME;
	// $("#selectProvider").window('close');
	getvalue(row);
	window.parent.closeWinForm2();
}

function getvalue(row) {
	var id = $('#showWin').document.getElementById("goods_supplier_id");
	alert(id);
	$('#goods_supplier_id').combobox('setValue', row.ID);
}

// 查看详细
function detail(title, x, y) {
	// var rows = $('#dg').datagrid('getSelections');
	// // if (rows.length == 0) {
	// // $.messager.alert('提示', '请选择需要操作的数据！', 'warning');
	// // return;
	// // }
	// // if (rows.length > 1) {
	// // $.messager.alert('提示', '请选择一条数据进行操作！', 'warning');
	// // return;
	// // }
	var row = rows[0];
	var url = Utils.getRootPath()
			+ '/goodsFile/goodsFile/gotoDetailGoodsFile?id=' + row.ID;
	add(url, title, x, y);
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
				url : Utils.getRootPath() + '/goodsFile/goodsFile/delete',
				data : {
					id : ids
				},
				async : false,
				dataType : 'json',
				success : function(result) {
					if (result.result) {
						$.messager.alert('提示', '删除成功', 'info', function() {
							window.parent.loadDataGrid('goodfile');
							window.parent.closeWinForm();
						});
					} else {
						$.messager.alert('提示', '该商品仍在销售，不可删除', 'warning');
					}
				}
			});
		}
		;
	});
};

// 新增数据
function saveBaseInfo(formId) {
	var goodsClass = $('#goods_class_id').combobox('getValue');
	if (goodsClass.length == 0) {
		$.messager.alert('提示', '请选择商品类别！', 'warning');
		return;
	}
	var goodsBrand = $('#goods_brand_id').combobox('getValue');
	if (goodsBrand.length == 0) {
		$.messager.alert('提示', '请选择商品品牌！', 'warning');
		return;
	}
	// var goodsSupplier = $('#goods_supplier_id').combobox('getValue');
	// if (goodsSupplier.length == 0) {
	// $.messager.alert('提示', '请选择供应商！', 'warning');
	// return;
	// }
	var zcUserInfo = $('#zcUserInfo').combobox('getValue');
	if (zcUserInfo.length == 0) {
		$.messager.alert('提示', '请选择工位！', 'warning');
		return;
	}
	var goodsType = $('#goodsType').combobox('getValue');
	if (goodsType.length == 0) {
		$.messager.alert('提示', '请选择一条截单类型！', 'warning');
		return;
	}
	var wasteRate = $('#wasteRate').val();
	if (wasteRate < 0 || wasteRate > 100 || wasteRate.length == 0) {
		$.messager.alert('提示', '损耗率应在0到100之间！', 'warning');
		return;
	}
	var goodsFileId = $('#goodsFileId').val();
	// 数据有限性判断
	if (validateSubmit(formId)) {
		// 保存
		var url = Utils.getRootPath() + '/goodsFile/goodsFile/save?id='
				+ goodsFileId;
		$.ajax({
			type : "post",
			url : url,
			data : $('#' + formId).serialize(),
			async : false,
			dataType : 'json',
			success : function(result) {
				if (result.message == "保存成功") {
					$.messager.alert('提示', '保存成功', 'info', function() {
						window.parent.loadDataGrid('goodfile');
						window.parent.closeWinForm();
					});
				} else if (result.message == "查询成功") {
					$.messager.alert('提示', '货号已存在，请重新输入', 'warning');
				} else if (result.status == "error") {
					$.messager.alert('提示', '保存失败，请联系管理员', 'error');
				}
			}
		});
	}
}

// 修改数据
function editBaseInfo(formId) {
	var wasteRate = $('#wasteRate').val();
	if (wasteRate < 0 || wasteRate > 100 || wasteRate.length == 0) {
		$.messager.alert('提示', '损耗率应在0到100之间！', 'warning');
		return;
	}
	// 数据有限性判断
	if (validateSubmit(formId)) {
		// 保存
		var url = Utils.getRootPath() + '/goodsFile/goodsFile/update';
		$.ajax({
			type : "post",
			url : url,
			data : $('#' + formId).serialize(),
			async : false,
			dataType : 'json',
			success : function(result) {
				if (result.result) {
					$.messager.alert('提示', '保存成功', 'info', function() {
						window.parent.loadDataGrid('goodfile');
						window.parent.closeWinForm();
					});
				} else {
					$.messager.alert('提示', '保存失败，请联系管理员', 'error');
				}
			}
		});
	}
}

// 批量导入
function batchImport() {
	var url = Utils.getRootPath() + '/goodsFile/goodsFile/saveBatchImport';
	$('#importBtn').linkbutton({
		iconCls : 'icon-save'
	});
	$('#importBtn').text('导入中');
	$('#myBatch').form('submit', {
		url : url,
		dataType : 'html',
		onSubmit : function() {
		},
		success : function(data) {
			$('#importBtn').linkbutton({
				iconCls : 'icon-save'
			});
			$('#importBtn').text('导入');
			var result = eval("(" + data + ")");
			if (result.msg == "fail") {
				$.messager.alert('提示', '导入失败', 'error');

			} else if (result.msg == "success") {
				$.messager.alert('提示', '导入成功！', 'info', function() {
					window.parent.loadDataGrid('goodfile');
					$('#showWinBatch').window('close');
				});
			} else {
				var info = result.resultAnwser;
				var wrongRow = new Array();
				wrongRow = info.split("@");
				var wrongMsg = "批量导入失败:" + "<br/>";
				// if(null!=wrongRow && wrongRow.length >0)
				// {
				// for(var i=0;i<wrongRow.length;i++){
				// wrongMsg +=wrongRow[i]+"<br/>";
				// }
				$.messager.alert('提示', wrongMsg);

				// }

			}
		}
	});
}

// //打开供应商列表
// function openSelectProvider(){
// $('#selectProvider').window({
// title:'选择供应商',
// closable: true,
// modal: false,
// draggable:false
// });
// $("#selectProvider").window('open');
//
// }

// //打开供应商新增
// function addProvider(){
// $('#addProvider').window({
// title:'新增供应商',
// closable: true,
// modal: false,
// draggable:false
// });
// $("#addProvider").window('open');
//
// }

// 新增数据
function saveProviderInfo(formId) {
	// 数据有限性判断
	if (validateSubmit(formId)) {
		// 保存
		var url = Utils.getRootPath() + '/provider/poviderinfo/save';
		$.ajax({
			type : "post",
			url : url,
			data : $('#' + formId).serialize(),
			async : false,
			dataType : 'json',
			success : function(result) {
				if (result.result) {
					$.messager.alert('提示', '保存成功', 'info', function() {
						window.parent.loadDataGrid('provider');
						window.parent.closeWinForm3();
					});
				} else if (result.status == "error") {
					$.messager.alert('提示', '保存失败，请联系管理员', 'error');
				}
			}
		});
	}
}

// 查看商品档案的基本信息
function opengoodsItem(id) {
	var url = Utils.getRootPath()
			+ '/goodsFile/goodsFile/gotoDetailGoodsFile?id=' + id;
	add(url, '查看详情', 900, 535);
}

// 更新
function getLetter() {
	$.messager.confirm('确认 ', '确定要更新 ？', function(r) {
		$("#ddialog").dialog('open');
		if (r) {
			$.ajax({
				type : "post",
				url : Utils.getRootPath() + '/goodsFile/goodsFile/getLetter',
				async : true,
				dataType : 'json',
				success : function(result) {
					if (result.result) {
						$("#ddialog").dialog('close');
						$.messager.alert('提示', '更新成功', 'info', function() {
							$("#ddialog").dialog('close');
							window.parent.loadDataGrid('goodfile');
							window.parent.closeWinForm();
						});
					} else if (result.status == "error") {
						$.messager.alert('提示', '更新失败，请联系管理员', 'error');
					}
				}
			});
		}
		;
	});
};

// 打开新增商品调价单页面
function addAdjustment() {
	var url = Utils.getRootPath() + '/goodsFile/goodsFile/gotoAddAdjustment';
	add(url, '商品调价单', 800, 535);
}

// 新增数据
function saveBaseInfo(formId) {
	var goodsId = $('#goodsFileId').val();
	// 数据有限性判断
	if (validateSubmit(formId)) {
		// 新增商品调价单保存
		var url = Utils.getRootPath() + '/goodsFile/goodsFile/save?id='
				+ goodsId;
		$.ajax({
			type : "post",
			url : url,
			data : $('#' + formId).serialize(),
			async : false,
			dataType : 'json',
			success : function(result) {
				if (result.message == "保存成功") {
					$.messager.alert('提示', '保存成功', 'info', function() {
						window.parent.loadDataGrid('goodfile');
						window.parent.closeWinForm();
					});
				} else if (result.message == "查询成功") {
					$.messager.alert('提示', '货号已存在，请重新输入', 'warning');
				} else if (result.status == "error") {
					$.messager.alert('提示', '保存失败，请联系管理员', 'error');
				}
			}
		});
	}
};

function chooseProduct() {
	var goodsFileId = $('#goodsFileId').val();
	var goodsAttribute = $('#goodsAttribute').val();
	if (goodsAttribute == '1') {
		$.ajax({
			type : "get",
			url : Utils.getRootPath()
					+ '/goodsFile/goodsFile/chooseProduct?goodsFileId='
					+ goodsFileId,
			data : {},
			success : function(data) {
				$('#selectOptions').window({
					title : '选择成品',
					closable : true,
					maximizable : false,
					modal : false,
					draggable : false,
					width : 550,
					height : 400
				});
				$('#selectOptions').html(data);
			}
		});
	}
}

function checkProductGoods() {
	var rows = $('#goodsDg').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择一条成品记录！', 'warning');
		return;
	}
	var id = rows[0].ID;
	var goodsFileId = $('#fileId').val();
	$.ajax({
		type : "post",
		url : Utils.getRootPath() + '/goodsFile/goodsFile/saveCheck?id=' + id
				+ '&goodsFileId=' + goodsFileId,
		async : true,
		dataType : 'json',
		success : function(result) {
			$('#selectOptions').window('close');
			if (result.status == "OK") {
				$.messager.alert('提示', '保存成功', 'info', function() {
					$('#productGoodsId').val(result.message);
					$('#dg').datagrid('reload');
				});
			} else if (result.status == "ERROR") {
				$.messager.alert('提示', '保存失败，请联系管理员', 'error');
				$('#dg').datagrid('reload');
			}
		}
	});
}

// 树形菜单
function tree() {
	$('#MyTree').tree(
			{
				checkbox : false,
				url : Utils.getRootPath() + "/goodsFile/goodsFile/getTreeData",
				onBeforeExpand : function(node, param) {
					$('#MyTree').tree('options').url = Utils.getRootPath()
							+ "/goodsFile/goodsFile/getTreeData?id=" + node.id;
				},
				onClick : function(node) {
					if ($('#MyTree').tree('isLeaf', node.target)) {
						var queryParams = {
							'goodsFile.id' : node.id,
						};
						$('#dg').datagrid('options').queryParams = queryParams;
						$("#dg").datagrid('load');
						document.getElementById("treeValue").value = node.id;
					} else {
						// alert(node.id);
						// var
						// childrenNodes=$('#MyTree').tree('getChildren',node.target);
						var queryParams = {
							'goodsFile.id' : node.id,
						};
						$('#dg').datagrid('options').queryParams = queryParams;
						$("#dg").datagrid('load');
						document.getElementById("treeValue").value = node.id;
					}
				},
				onDblClick : function(node) {
					$(this).tree(
							node.state === 'closed' ? 'expand' : 'collapse',
							node.target);
					node.state = node.state === 'closed' ? 'open' : 'closed';
				}
			});
}

// 初始化父级菜单下拉框
function initParentTree() {
	$('#goods_classify').combotree({
		url : Utils.getRootPath() + "/commodityClassify/getTreeData",
		onLoadSuccess : function(node, data) {
			var optionValue = $('#goods_classify').attr("optionValue");
			if (optionValue != undefined && optionValue != "") {
				$('#goods_classify').combotree('setValue', optionValue);
			}
		}
	});
}