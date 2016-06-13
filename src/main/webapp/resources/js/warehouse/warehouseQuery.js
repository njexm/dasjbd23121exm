// 初始化父级菜单下拉框
function initParentTree() {
	$('#classify').combotree({
		url : Utils.getRootPath() + "/commodityClassify/getTreeData",
		onLoadSuccess : function(node, data) {
			var optionValue = $('#classify').attr("optionValue");
			if (optionValue != undefined && optionValue != "") {
				$('#classify').combotree('setValue', optionValue);
			}
		}
	});
}

