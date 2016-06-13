$(function(){
	initMenu();
	saveBtnClick();
	addBtnClick();
	delBtnClick();
});

function selectMenuDialog(){
	$('#selectMenuDialog').dialog({
	    title: '菜单列表',    
	    width: 300,    
	    height: 400,
	    left : 200,
	    top : 30,
	    closed: false,    
	    cache: false,    
	    modal: true,
	    toolbar : [{
	    	text:'确定',
			iconCls:'icon-ok',
			handler:function(){
				var treeObj = $.fn.zTree.getZTreeObj("tree");
				var nodes = treeObj.getSelectedNodes();
				if(nodes.length > 0){
					var node = nodes[0];
					$("#parentMenuId").val(node.id);
					$("#parentMenuName").val(node.moduleName);
				}
				$('#selectMenuDialog').dialog("close");
			}
	    },{
	    	text:'取消',
			iconCls:'icon-no',
			handler:function(){
				$('#selectMenuDialog').dialog("close");
			}
	    }],
	    onLoad : function(){
	    	var setting = {
	    			async : {
	    				type : "post",
	    				enable: true,
	    				url : Utils.getRootPath()+"/menuManage/getTreeNodes"
	    			},
	    			data : {
	    				key : {
	    					name : "moduleName"
	    				},
	    				simpleData : {
	    					enable : true,
	    					idKey : "id",
	    					pIdKey : "parentID",
	    					rootPId : "ROOT"
	    				}
	    			},
	    			view : {
	    				showIcon : true,
	    				showLine: true
	    			}
	    		};
	    		var obj = $.fn.zTree.init($("#tree"),setting);
	    }
	});    
	$('#selectMenuDialog').dialog('refresh', Utils.getRootPath()+'/menuManage/selectMenuDialog');  
}

//function parentMenuOnClick(){
//	var treeObj = $.fn.zTree.getZTreeObj("tree");
//	var nodes = treeObj.getSelectedNodes();
//	if(nodes.length > 0){
//		var node = nodes[0];
//		$("#parentMenuId").val(node.id);
//		$("#parentMenuName").val(node.moduleName);
//	}
//}

function delBtnClick(){
	$("a[name='delBtn']").click(function(){
		var menuId = $("#menuId").val();
		if(menuId == null || menuId == ""){
			$.messager.alert('提示','请选择要删除的菜单！',"info");
			return;
		}
		$.messager.confirm('确认','您确认想要删除这个节点吗？',function(r){
			if (r){
				$.ajax({
					type:"post",
					url:Utils.getRootPath()+"/menuManage/deleteMenuById",
					data:{id:menuId},
					dataType:"text",
					success:function(msg){
						if("success" == msg){
							window.location.href = Utils.getRootPath()+"/menuManage/init";
						}
					}
				});
			}

		})
	});
}

function addBtnClick(){
	$("a[name='addBtn']").click(function(){
		$("#menuName").val("");
		$("#menuId").val("");
		$("#parentMenuName").val("");
		$("#parentMenuId").val("");
		$("#indexNum").val("");
		$("#menuUrl").val("");
	})
}

function saveBtnClick(){
	$("a[name='saveBtn']").click(function(){
		var menuName = $("#menuName").val();
		var menuId = $("#menuId").val();
		var parentMenuName = $("#parentMenuName").val();
		var parentMenuId = $("#parentMenuId").val();
		var indexNum = $("#indexNum").val();
		var menuUrl = $("#menuUrl").val();
		if(menuName == null || menuName == ""){
			$.messager.alert('提示','菜单名称不能为空',"info");
			return;
		}
		if(indexNum == null || indexNum == ""){
			$.messager.alert('提示','菜单序列号不能为空',"info");
			return;
		}
		var type = "^[0-9]*[1-9][0-9]*$";
		var re = new RegExp(type);
		if(indexNum.match(re) == null){
			$.messager.alert('提示','请输入正整数！',"info");
			return;
		}
		if(menuUrl == null || menuUrl == ""){
			$.messager.alert('提示','菜单链接不能为空',"info");
			return;
		}
		
		$.ajax({
			type:"post",
			url:Utils.getRootPath()+"/menuManage/saveOrUpdateMenu",
			data:{moduleName:menuName,id:menuId,parentID:parentMenuId,indexNum:indexNum,target:menuUrl},
			dataType:"text",
			success:function(msg){
				if("success" == msg){
					window.location.href = Utils.getRootPath()+"/detail";
				}
			}
		})
	});
}

function initMenu(){
	var setting = {
		async : {
			type : "post",
			enable: true,
			url : Utils.getRootPath()+"/menuManage/getTreeNodes"
		},
		data : {
			key : {
				name : "moduleName"
			},
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "parentID",
				rootPId : "ROOT"
			}
		},
		view : {
			showIcon : true,
			showLine: true
		},
		callback : {
			onClick : zTreeOnClick
		}
	};
	var obj = $.fn.zTree.init($("#tree"),setting);
	var zTreeObj = $.fn.zTree.init($("#menuTree"),setting);
}

function zTreeOnClick(){
	var treeObj = $.fn.zTree.getZTreeObj("menuTree");
	var nodes = treeObj.getSelectedNodes();
	if(nodes.length > 0){
		var node = nodes[0];
		var parentNode = node.getParentNode();
		$("#menuName").val(node.moduleName);
		$("#menuId").val(node.id);
		$("#indexNum").val(node.indexNum);
		$("#menuUrl").val(node.target);
		if(parentNode != null){
			$("#parentMenuName").val(parentNode.moduleName);
		}else{
			$("#parentMenuName").val("");
		}
		$("#parentMenuId").val(node.parentID);
	}
	
}