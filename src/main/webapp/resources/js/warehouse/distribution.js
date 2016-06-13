//添加
function add(url, title, x, y) {
	window.parent.initAdd(url, title, x, y);
}

function openOrderItem(id) {
	var url = Utils.getRootPath() + '/distribution/distributionDo/itemList?id=' + id;
	add(url, '订单配送商品详情', 1000, 550);
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
	searchData();
}