$(function(){
	initMenu();
});

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
		}
	};
	var zTree = $.fn.zTree.init($("#treeDemo"),setting);
}

//一级菜单鼠标悬停事件
$(".menu_a").hover(function(){
//	var imgPath = "url("+Utils.getRootPath()+"/resources/image/left2.png)";
//	$(this).css("background-image",imgPath);
	$(this).find("a").css("color","#0180cc");
},function(){
	var path = $(this).find("img").attr("src");
	path = path.substring(path.lastIndexOf("/")+1,path.length);
	if(path == "closed2.png"){
//		var imgPath = "url("+Utils.getRootPath()+"/resources/image/left.png)";
//		$(this).css("background-image",imgPath);
		$(this).find("a").css("color","#333");
	}
});

//二级菜单鼠标悬停事件
//$(".menu_second").hover(function(){
//	$(this).find("a").css("font-weight","bold");
//},function(){
//	$(this).find("a").css("font-weight","normal");
//});

//点击一级菜单事件
function loadNextMenu(Obj){
	var id = Obj.getAttribute("moduleId");
	var imgPath;
//	var path;
	var statu = 0;
	$("li").each(function(){
		var parentID = this.getAttribute("parentID");
		var display = $(this).css("display");
		if(parentID == id && display == "none"){
			$(this).css("display","block");
			statu = 1;
		}else if(parentID == id && display == "block"){
			$(this).css("display","none");
		}
	});
	if(statu == 1){
		imgPath  = Utils.getRootPath()+"/resources/image/menu/open2.png";
//		path = "url("+Utils.getRootPath()+"/resources/image/left2.png)";
	}else{
		imgPath = Utils.getRootPath()+"/resources/image/menu/closed2.png";
//		path = "url("+Utils.getRootPath()+"/resources/image/left.png)";
		$(Obj).find("a").css("color","#333");
	}
//	$(Obj).css("background-image",path);
	$(Obj).find("img").attr("src",imgPath);
}