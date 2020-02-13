
//标签云
$.ajax({
    type: 'GET',
    url: '/getTagsCloud',
    dataType: 'json',
    data: {
    },
    success: function (data) {
        if(data['taglen'] == 0){
            var tagCloud = $('.tag-cloud');
            tagCloud.empty();
            tagCloud.append($('<h3 class="widget-title">标签云</h3>'));
            var widgetTagCloud = $('<div class="widget-tag-cloud"><span>暂无标签</span></div>');
            tagCloud.append(widgetTagCloud);
            $('#right').append(tagCloud);
        } else {
            putInTagsCloud(data);
        }

    },
    error: function () {
    }
});

function putInTagsCloud(data){
    var tagCloud = $('.tag-cloud');
    tagCloud.empty();
    tagCloud.append($('<h3 class="widget-title">标签云</h3>'));
    var widgetTagCloud = $('<div class="widget-tag-cloud"></div>');
    $.each(data['tagsList'], function (index, obj) {
        widgetTagCloud.append($('<a href="tag?tag=' + obj['tagName'] + '" style="font-size:' + obj['tagSize'] + 'px">' + obj['tagName'] + '</a>'));
    });
    tagCloud.append(widgetTagCloud);

}

//网站最后更新时间（版本更新需更改）
var siteLastUpdateTime = '2020年02月13日15点';
//网站开始时间
var siteBeginRunningTime = '2020-01-31 11:27:00';

//网站信息
$.ajax({
    type: 'GET',//要求为String类型的参数，请求方式（post或get）默认为get
    url: '/getSiteInfo',//发送请求的地址
    dataType: 'json',//返回JSON数据
    data: {},//请求参数
    success: function (data) {//这里的data是服务器返回的
        var siteInfo = $('.site-info');
        siteInfo.empty();
        siteInfo.append('<h5 class="site-title">' +
            '<i class="am-icon-info site-icon"></i>' +
            '<strong style="margin-left: 15px">网站信息</strong>' +
            '</h5>');
        var siteDefault = $('<ul class="site-default"></ul>');
        siteDefault.append('<li>' +
            '<i class="am-icon-file site-default-icon"></i><span class="site-default-word">文章总数</span>：' + data['articleNum'] + ' 篇' +
            '</li>');
        siteDefault.append('<li>' +
            '<i class="am-icon-tags site-default-icon"></i><span class="site-default-word">标签总数</span>：' + data['tagsNum'] + ' 个' +
            '</li>');
        siteDefault.append('<li>' +
            '<i class="am-icon-comments-o site-default-icon"></i><span class="site-default-word">访问量</span>：' + data['vistorNum'] + ' 条' +
            '</li>');
        siteDefault.append('<li>' +
            '<i class="am-icon-pencil-square site-default-icon"></i><span class="site-default-word">网站最后更新</span>：<span class="siteUpdateTime">' + siteLastUpdateTime + '</span>' +
            '</li>');
        siteDefault.append('<li>' +
            '<i class="am-icon-calendar site-default-icon"></i><span class="site-default-word">网站运行天数</span>：<span class="siteRunningTime"> </span>' +
            '</li>');
        siteInfo.append(siteDefault);

    },
    error: function () {
        var siteInfo = $('.site-info');
        siteInfo.empty();
        siteInfo.append('<h5 class="site-title">' +
            '<i class="am-icon-info site-icon"></i>' +
            '<strong style="margin-left: 15px">网站信息</strong>' +
            '</h5>');
        var siteDefault = $('<ul class="site-default"></ul>');
        siteDefault.append('<li>' +
            '<i class="am-icon-pencil-square site-default-icon"></i><span class="site-default-word">网站最后更新</span>：<span class="siteUpdateTime">' + siteLastUpdateTime + '</span>' +
            '</li>');
        siteDefault.append('<li>' +
            '<i class="am-icon-calendar site-default-icon"></i><span class="site-default-word">网站运行天数</span>：<span class="siteRunningTime"> </span>' +
            '</li>');
        siteInfo.append(siteDefault);

    }
});

//网站运行时间
function siteRunningTime(time) {
    var theTime;
    var strTime = "";
    if (time >= 86400) {
        theTime = parseInt(time / 86400);
        strTime += theTime + "天";
        time -= theTime * 86400;
    }
    if (time >= 3600) {
        theTime = parseInt(time / 3600);
        strTime += theTime + "时";
        time -= theTime * 3600;
    }
    if (time >= 60) {
        theTime = parseInt(time / 60);
        strTime += theTime + "分";
        time -= theTime * 60;
    }
    strTime += time + "秒";

    $('.siteRunningTime').html(strTime);
}

var nowDate = new Date().getTime();
//网站开始运行日期
var oldDate = new Date(siteBeginRunningTime.replace(/-/g, '/'));
var time = oldDate.getTime();
var theTime = parseInt((nowDate - time) / 1000);
//周期性计算
setInterval(function () {
    siteRunningTime(theTime);
    theTime++;
}, 1000);



