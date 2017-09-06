<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no"/>
    <style type="text/css">
        body, html {
            width: 100%;
            height: 100%;
            margin: 0;
            font-family: "微软雅黑", serif;
        }

        #allmap {
            height: 500px;
            width: 100%;
        }
    </style>
    <script src="static/js/jquery.js"></script>
    <title>基于区块链的数字资产交易和过程追踪服务</title>
</head>

<body>
<h2>基于区块链的数字资产交易和过程追踪服务</h2>
<div>
    <input value="昵称" title="昵称" id="nickname">
    <input value="密码" title="密码" id="password">

    <button id="login">登录</button>
    <button id="register">注册</button>
</div>
</body>
</html>

<script type="text/javascript">
    $("#login").click(function () {
        var nickname = $("#nickname").val();
        var password = $("#password").val();
        $.ajax({
            type: "post",
            dataType: "json",
            url: '/account/get',
            data: {nickname: nickname, password: password},
            asyn: false,
            success: function (data) {
                console.log("response data:" + data);
                alert(data);
                var account = data.account;
                if (account != undefined && account != "") {
                    localStorage.setItem("account", account);

                }
            },
            error: function (data, b, c) {
                alert("ERROR: " + b);
            }
        });
    });

    /**district值的绑定*/
    $("#district").click(function () {
        district = $("#district").val();
    });

    /**触发查询*/
    $("#search").click(function () {
        if ($("#tag").val() === "SUSPEND" || $("#district").val() === "SUSPEND") {
            alert("请先输入查询条件");
            return;
        }
        console.debug("query input info: " + district + "--" + tagId + "--" + queryType);
        getInfo(district, tagId, queryType);
    });


    /**
     * 通过指定行政区、标签id、查询类型，获取对应的信息
     * @param districtName 行政区名，如"上海市浦东区"
     * @param tagId 居住地010_601, 工作地010_602，家庭接入点010_611，非家庭接入点010_612
     * @param queryType "justCount"-只查询标签数量，"moreInfo"-查询具体标签值
     */
    function getInfo(districtName, tagId, queryType) {
        MAP.clearOverlays();    // 清空历史数据
        // 1. 通过百度api获得districtName对应行政区的经纬度边界
        BD_BDARY.get(districtName, function (bdResponse) {
            var count = bdResponse.boundaries.length; //行政区域的点有多少个
            if (count === 0) {
                alert('未能获取当前输入行政区域');
                return;
            }
            var bdStr = bdResponse.boundaries[0];
            // 用红色虚线勾勒行政区边界
            var ply = new BMap.Polygon(bdResponse.boundaries[0], {strokeWeight: 5, strokeColor: "#ff0000"}); //建立多边形覆盖物
            MAP.addOverlay(ply);
            for (var i = 1; i < count; i++) {
                bdStr += ";" + bdResponse.boundaries[i];
                ply = new BMap.Polygon(bdResponse.boundaries[i], {strokeWeight: 5, strokeColor: "#ff0000"}); //建立多边形覆盖物
                MAP.addOverlay(ply);
            }

            // 2. 将边界封装成json
            var districtPolygon = bdary2Json(bdStr);

            // 3. 发送post请求，获取数据
            $.ajax({
                type: "post",
                dataType: "json",
                url: '/mesh/queryInfo',
                data: {polygon: districtPolygon, tagId: tagId, queryType: queryType},
                asyn: false,
                // 4. 处理请求回来的数据{meshId:count, meshId:count, ...}
                success: function (data) {
                    if (data != "") {
                        // 5.1 先遍历所有的count，取最大值和最小值。
                        var minCount = 10000000;
                        var maxCount = 0;
                        $.each(data, function (meshId, count) {
                            if (count > maxCount) maxCount = count;
                            if (count < minCount) minCount = count;
                        });
                        var seg = (maxCount - minCount) / heatLevelStyle.length;

                        // 5.1 根据min和max，为每个mesh上色
                        $.each(data, function (meshId, count) {
                            var position = meshId2Position(parseInt(meshId));
                            var mesh = new BMap.Polygon([
                                new BMap.Point(position.lng, position.lat),
                                new BMap.Point(position.lng + 0.01, position.lat),
                                new BMap.Point(position.lng + 0.01, position.lat + 0.01),
                                new BMap.Point(position.lng, position.lat + 0.01)
                            ], count2HeatStyle(count, minCount, seg));
                            MAP.addOverlay(mesh);
                        })
                    }
                },
                error: function (data, b, c) {
                    alert("ERROR: " + b);
                }
            });
        });

    }

    /**
     * 将百度获取到的边界字符串转换成json
     * @param bdaryStr 格式如 "121.33, 31.6666;122.9878, 32.4;..."
     */
    function bdary2Json(bdaryStr) {
        var res = {polygon: []};
        var splits = bdaryStr.split(";");
        for (var i = 0; i < splits.length; i++) {
            var pStr = splits[i].split(",");
            var point = {};
            point.lng = parseFloat(pStr[0]);
            point.lat = parseFloat(pStr[1]);
            res.polygon.push(point);
        }
        return JSON.stringify(res);
    }


    /**
     * 将count值转换为热力的某一个色阶
     * 目前仅用简单的count取模，以后可以先对所有值进行统计。
     * @param count 当前热力值
     * @param min   热力色阶中level为0时对应的值(通过遍历count数组得到)
     * @param seg   热力色阶中一个segment对应的跨度值（通过遍历count数组得到）
     * @apiNote Version 1.0
     * @see heatLevelStyle
     */
    function count2HeatStyle(count, min, seg) {
        var res = Math.round((count - min) / seg);
        if (res > heatLevelStyle.length) return heatLevelStyle[heatLevelStyle.length - 1];
        if (res < 0) return heatLevelStyle[0];
        return heatLevelStyle[res];
    }
</script>