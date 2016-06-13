$(function() {
	$("#openDemoOne,#openDemoTwo,#openDemoThree,#openDemoFour,#beback").hover(
			function() {
				$(this).css("color", "#0180cc");
			}, function() {
				$(this).css("color", "#333");
			});

	$("#openDemoOne").click(function() {
		$("#demo1").css("display", "none");
		$("#demo2").css("display", "none");
		$("#backDiv").css("display", "block");
		$("#demoOne2").css("display", "block");
		$("#demoTwo2").css("display", "none");
		$("#demoThree2").css("display", "none");
		$("#demoFour2").css("display", "none");
	});
	$("#openDemoTwo").click(function() {
		$("#demo1").css("display", "none");
		$("#demo2").css("display", "none");
		$("#backDiv").css("display", "block");
		$("#demoOne2").css("display", "none");
		$("#demoTwo2").css("display", "block");
		$("#demoThree2").css("display", "none");
		$("#demoFour2").css("display", "none");
	});
	$("#openDemoThree").click(function() {
		$("#demo1").css("display", "none");
		$("#demo2").css("display", "none");
		$("#backDiv").css("display", "block");
		$("#demoOne2").css("display", "none");
		$("#demoTwo2").css("display", "none");
		$("#demoThree2").css("display", "block");
		$("#demoFour2").css("display", "none");
	});
	$("#openDemoFour").click(function() {
		$("#demo1").css("display", "none");
		$("#demo2").css("display", "none");
		$("#backDiv").css("display", "block");
		$("#demoOne2").css("display", "none");
		$("#demoTwo2").css("display", "none");
		$("#demoThree2").css("display", "none");
		$("#demoFour2").css("display", "block");
	});
});

require.config({
	paths : {
		echarts : Utils.getRootPath() + '/resources/plugins/echarts/dist'
	}
});
require([ 'echarts', 'echarts/chart/line', 'echarts/chart/bar',
		'echarts/chart/k' ], function(echarts) {
	var chartOne = echarts.init(document.getElementById('demoOne'));
	var chartOne2 = echarts.init(document.getElementById('demoOne2'));
	var url = Utils.getRootPath() + '/logManage/orderNumsExm';
	var url1 = Utils.getRootPath() + '/logManage/sellNums';
	var url2 = Utils.getRootPath() + '/logManage/goodsSellsRand';
	$.ajax({
		type : "POST",
		url : url,
		dataType : "json",
		success : function(data) { // 成功的处理函数
			var optionOne = {
				title : {
					text : ''
				},
				tooltip : {
					trigger : 'axis'
				},
				legend : {
					data : [ '宜鲜美平台', '其他平台' ]
				},
				toolbox : {
					show : true,
					feature : {
						mark : {
							show : false
						},
						dataView : {
							show : false,
							readOnly : false
						},
						magicType : {
							show : false,
							type : [ 'line', 'bar' ]
						},
						restore : {
							show : false
						},
						saveAsImage : {
							show : false
						}
					}
				},
				calculable : true,
				xAxis : [ {
					type : 'category',
					boundaryGap : false,
					data : [ data[0], data[2], data[4], data[6], data[8],
							data[10], data[12], data[14], data[16], data[18],
							data[20], data[22] ],
				} ],
				yAxis : [ {
					type : 'value',
					axisLabel : {
						formatter : '{value} 单'
					}
				} ],
				series : [
						{
							name : '宜鲜美平台',
							type : 'line',
							data : [ data[1], data[3], data[5], data[7],
									data[9], data[11], data[13], data[15],
									data[17], data[19], data[21], data[23] ],
							markPoint : {
								data : [ {
									type : 'max',
									name : '最大值'
								}, {
									type : 'min',
									name : '最小值'
								} ]
							},
							markLine : {
								data : [ {
									type : 'average',
									name : '平均值'
								} ]
							}
						},
						{
							name : '其他平台',
							type : 'line',
							data : [ data[24], data[25], data[26], data[27],
									data[28], data[29], data[30], data[31],
									data[32], data[33], data[34], data[35] ],
							markPoint : {
								data : [ {
									type : 'max',
									name : '最大值'
								}, {
									type : 'min',
									name : '最小值'
								} ]
							},
							markLine : {
								data : [ {
									type : 'average',
									name : '平均值'
								} ]
							}
						} ]
			};
			chartOne.setOption(optionOne);
			chartOne2.setOption(optionOne);
		}
	});
	var chartTwo = echarts.init(document.getElementById('demoTwo'));
	var chartTwo2 = echarts.init(document.getElementById('demoTwo2'));
	$.ajax({
		type : "POST",
		url : url1,
		dataType : "json",
		success : function(data) { // 成功的处理函数
			var optionTwo = {
				tooltip : {
					trigger : 'axis',
					axisPointer : { // 坐标轴指示器，坐标轴触发有效
						type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
					}
				},
				legend : {
					data : [ '惠民疏菜', '精品水果', '海鲜水产', '其他' ]
				},
				toolbox : {
					show : true,
					orient : 'vertical',
					x : 'right',
					y : 'center',
					feature : {
						mark : {
							show : false
						},
						dataView : {
							show : false,
							readOnly : false
						},
						magicType : {
							show : false,
							type : [ 'line', 'bar', 'stack', 'tiled' ]
						},
						restore : {
							show : false
						},
						saveAsImage : {
							show : false
						}
					}
				},
				calculable : true,
				xAxis : [ {
					type : 'category',
					data : [ '周一', '周二', '周三', '周四', '周五', '周六', '周日' ]
				} ],
				yAxis : [ {
					type : 'value'
				} ],
				series : [
						{
							name : '惠民蔬菜',
							type : 'bar',
							data : [ data[0], data[4], data[8], data[12],
									data[16], data[20], data[24] ]
						},
						{
							name : '精品水果',
							type : 'bar',
							data : [ data[1], data[5], data[9], data[13],
									data[17], data[21], data[25] ]
						},
						{
							name : '海鲜水产',
							type : 'bar',
							data : [ data[2], data[6], data[10], data[14],
									data[18], data[22], data[26] ]
						},
						{
							name : '其他',
							type : 'bar',
							data : [ data[3], data[7], data[11], data[15],
									data[19], data[23], data[27] ]
						} ]
			};
			chartTwo2.setOption(optionTwo);
			chartTwo.setOption(optionTwo);
		}
	});

	var chartThree = echarts.init(document.getElementById('demoThree'));
	var chartThree2 = echarts.init(document.getElementById('demoThree2'));
	$.ajax({
		type : "POST",
		url : url1,
		dataType : "json",
		success : function(data) { // 成功的处理函数
			var optionThree = {
				tooltip : {
					trigger : 'axis'
				},
				legend : {
					data : [ data[0], data[2], data[4] ]
				},
				toolbox : {
					show : true,
					feature : {
						mark : {
							show : false
						},
						dataView : {
							show : false,
							readOnly : false
						},
						magicType : {
							show : false,
							type : [ 'line', 'bar', 'stack', 'tiled' ]
						},
						restore : {
							show : false
						},
						saveAsImage : {
							show : false
						}
					}
				},
				calculable : true,
				xAxis : [ {
					type : 'category',
					boundaryGap : false,
					data : [ '周一', '周二', '周三', '周四', '周五', '周六', '周日' ]
				} ],
				yAxis : [ {
					type : 'value'
				} ],
				series : [ {
					name : data[0],
					type : 'line',
					stack : '总量',
					itemStyle : {
						normal : {
							areaStyle : {
								type : 'default'
							}
						}
					},
					data : [ data[1], data[3], data[5], data[7], data[9],
							data[11], data[13] ]
				} ]
			};
			chartThree2.setOption(optionThree);
			chartThree.setOption(optionThree);
		}
	});
	var chartFour = echarts.init(document.getElementById('demoFour'));
	var chartFour2 = echarts.init(document.getElementById('demoFour2'));
	var optionFour = {
		title : {
			text : ''
		},
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			data : [ '景明佳园亭点', '紫苑亭点', '黄鹂新村亭点' ]
		},
		toolbox : {
			show : false
		},
		calculable : true,
		xAxis : [ {
			type : 'category',
			data : [ '1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月',
					'10月', '11月', '12月' ]
		} ],
		yAxis : [ {
			type : 'value'
		} ],
		series : [
				{
					name : '景明佳园亭点',
					type : 'bar',
					data : [ 2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2,
							32.6, 20.0, 6.4, 3.3 ],
					markPoint : {
						data : [ {
							type : 'max',
							name : '最大值'
						}, {
							type : 'min',
							name : '最小值'
						} ]
					},
					markLine : {
						data : [ {
							type : 'average',
							name : '平均值'
						} ]
					}
				},
				{
					name : '紫苑亭点',
					type : 'bar',
					data : [ 2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2,
							48.7, 18.8, 6.0, 2.3 ],
					markPoint : {
						data : [ {
							name : '年最高',
							value : 182.2,
							xAxis : 7,
							yAxis : 183,
							symbolSize : 18
						}, {
							name : '年最低',
							value : 2.3,
							xAxis : 11,
							yAxis : 3
						} ]
					},
					markLine : {
						data : [ {
							type : 'average',
							name : '平均值'
						} ]
					}
				},
				{
					name : '黄鹂新村亭点',
					type : 'bar',
					data : [ 3.6, 2.9, 2.0, 6.4, 38.7, 90.7, 75.6, 112.2, 8.7,
							128.8, 12.0, 22.3 ],
					markPoint : {
						data : [ {
							name : '年最高',
							value : 128.8,
							xAxis : 7,
							yAxis : 183,
							symbolSize : 18
						}, {
							name : '年最低',
							value : 2.0,
							xAxis : 11,
							yAxis : 3
						} ]
					},
					markLine : {
						data : [ {
							type : 'average',
							name : '平均值'
						} ]
					}
				} ]
	};
	chartFour.setOption(optionFour);
	chartFour2.setOption(optionFour);
});

// 打开公告模板
function noticeDetail(id) {
	var url = Utils.getRootPath() + '/notice/openDetail?id=' + id;
	var title = "公告详情";
	add(url, title, 600, 350);
}