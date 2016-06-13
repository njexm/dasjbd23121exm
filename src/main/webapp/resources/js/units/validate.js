//*******************************长度验证**********************************
function getBytes(str) {
	var cArr = str.match(/[^\x00-\xff]/ig);
	return str.length + (cArr == null ? 0 : cArr.length);
};
// *******************************非空验证**********************************
function isEmpty(str) {
	if (str.length == 0) {
		return true;
	}
	return false;
};
// *******************************字符串全局替换*****************************
String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {
	if (!RegExp.prototype.isPrototypeOf(reallyDo)) {
		return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi" : "g")),
				replaceWith);
	} else {
		return this.replace(reallyDo, replaceWith);
	}
};
// 规格转换
function formatterGuige(val) {
	if (null != val && '' != val) {
		var result = val.split('：');
		if (result[0] != '商品规格') {
			return result[0];
		} else {
			return result[1];
		}
	}
};
// 状态转换
function formatterStatus(value) {
	if (value == '0') {
		return '<span style="color:green">待提交</span>';
	} else if (value == '1') {
		return '<span style="color:blue">待审核</span>';
	} else if (value == '2') {
		return '<span style="color:black">已审核</span>';
	} else if (value == '3') {
		return '<span style="color:red">待处理</span>';
	} else if (value == '4') {
		return '<span style="color:yellow">完成</span>';
	} else if (value == '5') {
		return '<span style="color:pink">作废</span>';
	} else {
		return '未知';
	}
};

// 盘点范围转换
function formatterCheckType(value) {
	if (value == '0') {
		return '<span style="color:blue">全场盘点</span>';
	} else if (value == '1') {
		return '<span style="color:blue">单品盘点</span>';
	} else if (value == '2') {
		return '<span style="color:blue">类别盘点</span>';
	} else if (value == '3') {
		return '<span style="color:blue">品牌盘点</span>';
	} else {
		return '其他类型';
	}
};

// 状态转换
function formatterStatusNoColour(value) {
	if (value == '0') {
		return '待提交';
	} else if (value == '1') {
		return '待审核';
	} else if (value == '2') {
		return '已审核';
	} else if (value == '3') {
		return '待处理';
	} else if (value == '4') {
		return '完成';
	} else if (value == '5') {
		return '作废';
	} else {
		return '未知';
	}
};

// 状态转换
function formatterOrderStatus(value) {
	if (value == '1') {
		return '待处理';
	} else if (value == '2') {
		return '待采购';
	} else if (value == '3') {
		return '待加工';
	} else if (value == '4') {
		return '待分拣';
	} else if (value == '5') {
		return '待配送';
	} else if (value == '6') {
		return '待提货';
	} else if (value == '7') {
		return '完成';
	} else if (value == '8') {
		return '待客服';
	} else if (value == '9') {
		return '作废';
	} else {
		return '未知';
	}
};

function accuracy() {
	var cookies = document.cookie;
	var cookie = [];
	cookie = cookies.split(';');
	var numAccuracy = "";
	// var moneyAccuracy = "";
	if (cookies.length >= 0) {
		for (var i = 0; i < cookie.length; i++) {
			if (cookie[i].indexOf("countOdsi") == 1) {
				numAccuracy = cookie[i].replace(" countOdsi=", "");
			}
			// if(cookie[i].indexOf("accuracy") == 1){
			// moneyAccuracy = cookie[i].replace(" accuracy=","");
			// }
		}
	}
	// if(type == 1){
	return numAccuracy;
	// }else{
	// return moneyAccuracy;
	// }
}

// 统一数字精度，金额type可以不写，数量type参数=1
function formatterNumber(val) {
	var mark = 0;
	if (val == 0 || val == '0') {
		return val = "0.00";
	}
	if (val == 'undefined' || val == "" || val == null || val == 'NaN') {
		return val = "0.00";
	}
	if (typeof (val) == typeof ("123")) {
		if (val.indexOf("font-weight:700") >= 0) {
			val = val.replace("<span style='color:red;font-weight:700 '>", "");
			val = val.replace("</span>", "");
			mark = 1;
		} else if (val.indexOf("font-weight:700") >= 0) {
			val = val.replace("<span style='color:red;font-weight:700 '>", "");
			val = val.replace("</span>", "");
			mark = 1;
		}
	}
	var roundDigit = accuracy();
	var num = new Number(roundDigit);
	if (null != val && '' != val) {
		var number = new Number(val);
		var result = number.toFixed(num);
		if (mark == 1) {
			return "<span style='color:red;font-weight:700 '>" + result
					+ "</span>";
		} else {
			return result;
		}
	}
};

function showGoods(val) {
	if (val != null && val != '') {
		return val.replace("商品规格：", "");
	}
}
// Date时间转换
function formatterDate(val) {
	if (null != val && '' != val) {
		// ie转化格式
		if (navigator.userAgent.indexOf("MSIE") > 0 && 'NaN' == (new Date(val))) {
			val = val.replace(/-/g, "/");
		}
		var date = new Date(val);
		var month = date.getMonth() + 1;
		if (month < 10)
			month = "0" + month;
		var day = date.getDate();
		if (day < 10)
			day = "0" + day;
		return date.getFullYear() + '-' + month + '-' + day;
	}
};

// Date时间转换
function formatterDateByM(val) {
	if (null != val && '' != val) {
		// ie转化格式
		if (navigator.userAgent.indexOf("MSIE") > 0 && 'NaN' == (new Date(val))) {
			val = val.replace(/-/g, "/");
		}
		var date = new Date(val);
		var month = date.getMonth() + 1;
		if (month < 10)
			month = "0" + month;
		var day = date.getDate();
		if (day < 10)
			day = "0" + day;
		var hour = date.getHours();
		if (hour < 10)
			hour = "0" + hour;
		var minute = date.getMinutes();
		if (minute < 10)
			minute = "0" + minute;
		return date.getFullYear() + '-' + month + '-' + day + ' ' + hour + ':'
				+ minute;
	}
};

// Date时间转换年月日 时分秒毫秒
function formatterCompleteDateMillsecond() {

	var date = new Date();
	var month = date.getMonth() + 1;
	if (month < 10)
		month = "0" + month;
	var day = date.getDate();
	if (day < 10)
		day = "0" + day;
	var hour = date.getHours();
	if (hour < 10)
		hour = "0" + hour;
	var minute = date.getMinutes();
	if (minute < 10)
		minute = "0" + minute;
	var second = date.getSeconds();
	if (second < 10)
		second = "0" + second;
	var millsecond = date.getMilliseconds();
	if (millsecond < 10)
		millsecond = "0" + millsecond;
	return date.getFullYear() + "" + month + "" + day + hour + minute + second
			+ millsecond;
}

// Date时间转换年-月-日 时:分:秒
function formatterCompleteDate(val) {

	if (null != val && '' != val) {
		// ie转化格式
		if (navigator.userAgent.indexOf("MSIE") > 0 && 'NaN' == (new Date(val))) {
			val = val.replace(/-/g, "/");
		}
		var date = new Date(val);
		var month = date.getMonth() + 1;
		if (month < 10)
			month = "0" + month;
		var day = date.getDate();
		if (day < 10)
			day = "0" + day;
		var hour = date.getHours();
		if (hour < 10)
			hour = "0" + hour;
		var minute = date.getMinutes();
		if (minute < 10)
			minute = "0" + minute;
		var second = date.getSeconds();
		if (second < 10)
			second = "0" + second;
		return date.getFullYear() + '-' + month + '-' + day + ' ' + hour + ':'
				+ minute + ':' + second;
	}
};

function getCurentDate() {
	var now = new Date();

	var year = now.getFullYear(); // 年
	var month = now.getMonth() + 1; // 月
	var day = now.getDate(); // 日

	var clock = year + "";

	if (month < 10)
		clock += "0";

	clock += month + "";

	if (day < 10)
		clock += "0";

	clock += day;

	return (clock);
}

// *********************************表单验证******************************
$(function() {

	$("input[precision]").each(function() {
		var precision = $(this).attr("precision");

		var regex = new RegExp("^\\d*(?:\\.\\d{0," + precision + "})?$");
		// ie浏览器
		if (navigator.userAgent.indexOf("MSIE") > 0) {
			var browser = navigator.userAgent.toLowerCase();
			var vesion = parseFloat(browser.match(/msie ([\d]+)/)[1]);
			// ie9以上版本弃用onpropertychange方法
			if (vesion < 9) {
				$(this).bind("propertychange", function() {
					if (this.value == this.value2) {
						return;
					}
					if (this.value.search(regex) == -1) {
						$(this).val((this.value2) ? this.value2 : '');
					} else {
						this.value2 = this.value;
					}
				});
			} else {
				$(this).on('input', function() {
					if (this.value == this.value2) {
						return;
					}
					if (this.value.search(regex) == -1) {
						$(this).val((this.value2) ? this.value2 : '');
					} else {
						this.value2 = this.value;
					}
				});
			}
		} else {
			$(this).on('input', function() {
				if (this.value == this.value2) {
					return;
				}
				if (this.value.search(regex) == -1) {
					$(this).val((this.value2) ? this.value2 : '');
				} else {
					this.value2 = this.value;
				}
			});
		}
	});
});

function validateSubmit(formId) {
	var isSubmit = true;
	$("#" + formId + " :input[reg]").each(function() {
		var tip = $(this).attr("tip");
		if (!validate($(this), tip)) {
			isSubmit = false;
			return false;
		}
	});

	return isSubmit;
}

function validate(obj, tip) {
	if (null == tip || '' == tip) {
		tip = "请填写正确格式";
	}
	var reg = comparison(obj.attr("reg")); // 属性
	var objValue = obj.attr("value");
	objValue = objValue.replace(/(^\s*)|(\s*$)/g, "");
	if (reg != null && !reg.test(objValue)) {
		$.messager.alert('提示', tip, 'warning');
		return false;
	} else {
		return true;
	}
}

function validateSubmitByAlert(formId) {
	var isSubmit = true;
	$("#" + formId + " :input[reg]").each(function() {
		var tip = $(this).attr("tip");
		if (!validateByAlert($(this), tip)) {
			isSubmit = false;
			return false;
		}
	});
	return isSubmit;
}

function validateByAlert(obj, tip) {
	if (null == tip || '' == tip) {
		tip = "请填写正确格式";
	}
	var reg = comparison(obj.attr("reg")); // 属性
	var objValue = obj.attr("value");
	objValue = objValue.replace(/(^\s*)|(\s*$)/g, "");
	if (reg != null && !reg.test(objValue)) {
		alert(tip);
		return false;
	} else {
		return true;
	}
}

function comparison(obj) {
	switch (obj) {
	case "Email":
		return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	case "NullOrEmail":
		return /null|^$|^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	case "Chinese":
		return /^[\u0391-\uFFE5]+$/;
	case "Require":
		return /.+/;
	case "Phone":
		return /^0\d{2,3}-?\d{7,8}$/;
	case "NullOrPhone":
		return /null|^$|^0\d{2,3}-?\d{7,8}$/;
	case "Mobile":
		return /^0?1[3|4|5|7|8][0-9]\d{8}$/;
	case "NullOrMobile":
		return /null|^$|^0?1[3|4|5|7|8][0-9]\d{8}$/;
	case "MobileOrPhone":
		return /^1[3|4|5|7|8][0-9]\d{8}$/;
	case "NullOrMobileOrPhone":
		return /null|^$|^0?1[3|4|5|7|8][0-9]\d{8}|^0\d{2,3}-?\d{7,8}$/;
	case "IdCard":
		return /^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;
	case "NullOrIdCard":
		return /null|^$|^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;
	case "Zip":
		return /^[1-9]\d{5}$/;
	case "NullOrZip":
		return /null|^$|^[1-9]\d{5}$/;
	case "Fax":
		return /^0\d{2,3}-?\d{7,8}$/;
	case "NullOrFax":
		return /null|^$|^0\d{2,3}-?\d{7,8}$/;
	case "Num":
		return /^\d+(\.{0,1}\d+){0,1}$/;
	case "NullOrNum":
		return /null|^$|^\d+(\.{0,1}\d+){0,1}$/;
	case "Money":
		return /^(([1-9]\d*)|0)(\.\d{1,2})?$/;
	case "NullOrMoney":
		return /null|^$|^(([1-9]\d*)|0)(\.\d{1,2})?$/;
	case "yyyy-MM-dd":
		return /^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)$/;
	case "HH:mm":
		return /^(?:[01]\d|2[0-3])(?::[0-5]\d)$/;
	case "HH:mm:ss":
		return /^(?:[01]\d|2[0-3])(?::[0-5]\d){2}$/;
	case "CarNum":
		return /^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z0-9]{5}$/;
	case "organizationCode":
		return /^[a-zA-Z0-9]{8}-[a-zA-Z0-9]$/; // 组织机构代码证
	case "street":
		return /.+/;
	case "TaxCode":
		return /^\d{10}$/;
	case "TaxNumber":
		return /^\d{8}$/;
	}
}