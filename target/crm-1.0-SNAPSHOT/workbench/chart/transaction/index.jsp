<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/8/27 0027
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
    <style type="text/css">

        #main{
            margin: 0 auto;
            height: 80px;
            width: 500px;
        }

    </style>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="ECharts/echarts.min.js"></script>
    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>

    <script type="text/javascript">

        $(function (){

            //页面加载完毕后，绘制统计图表
            getCharts();
        })

        function getCharts(){

            $.ajax({
                url:"workbench/transaction/getCharts.do",
                type:"get",
                dataType:"json",
                success:function (data){
                    /*
                         data:
                             {"total":100,"dataList":[{value: 20, name: '05提案/报价'},{value: 40, name: '02需求分析'},{...}]}
                    */

                    // 基于准备好的dom，初始化echarts实例
                    var myChart = echarts.init(document.getElementById('main'));

                    // 指定图表的配置项和数据
                    var option = {
                        backgroundColor: '#fff',

                        title: {
                            text: '统计交易阶段数量漏斗图',
                            left: 'center',
                            top: 20,
                            textStyle: {
                                color: '#000000'
                            }
                        },

                        tooltip: {
                            trigger: 'item'
                        },

                        visualMap: {
                            show: false,
                            min: 0,
                            max: data.total,
                            inRange: {
                                colorLightness: [0, 1]
                            }
                        },
                        series: [
                            {
                                name: '访问来源',
                                type: 'pie',
                                radius: '55%',
                                center: ['50%', '50%'],
                                data: data.dataList.sort(function (a, b) { return a.value - b.value; }),
                                roseType: 'radius',
                                label: {
                                    color: 'rgba(255, 255, 255, 0.3)'
                                },
                                labelLine: {
                                    lineStyle: {
                                        color: 'rgba(255, 255, 255, 0.3)'
                                    },
                                    smooth: 0.2,
                                    length: 10,
                                    length2: 20
                                },
                                itemStyle: {
                                    color: '#c23531',
                                    shadowBlur: 200,
                                    borderRadius: 9,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                },

                                animationType: 'scale',
                                animationEasing: 'elasticOut',
                                animationDelay: function (idx) {
                                    return Math.random() * 200;
                                }
                            }
                        ]
                    };

                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);

                }
            })

        }
    </script>

</head>
<body>

    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="width: 900px;height:700px;" ></div>

</body>
</html>
