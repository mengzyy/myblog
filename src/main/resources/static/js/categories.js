var categoryData='';
var categoryName='';
var eachPagecount=5;
//初始化填充分类
$.ajax({
    type: 'GET',
    url: '/categoriesAll',
    dataType: 'json',
    data: {},
    success: function (data) {
        var categories = $('.categories');
        categories.empty();
        categories.append($('<div class="categories-title">' +
            '<h3 style="font-size: 20px"></h3>' +
            '</div>'));
        var categoriesComment = $('<div class="categories-comment am-animation-slide-top"></div>');
        $.each(data['categoryList'], function (index, obj) {
            var catename = "'" + obj['cateName'] + "'";
            categoriesComment.append($('<div class="category">' +
                '<span><a class="categoryName" onclick="getArticlesByCateName(' + catename + ')"' + '>' + obj['cateName'] + '</a></span>' +
                '</div>'));
        });
        categories.append(categoriesComment);
    },
    error: function () {
        alert("获取分类信息失败");
    }
});

//获取特定分类文章和初始化第一页
function getArticlesByCateName(cateName) {
    categoryName= cateName;
    //这里每次点击就刷新右边ajax
    //返回全部某类别数据,同时初始化插件
    $.ajax({
        type: 'GET',
        url: '/getArticlesByCateName',
        dataType: 'json',
        data: {"categoryName": categoryName},
        success: function (data) {
            categoryData = data['articleList'];
            //默认先画第一页
            nextpage(1);
        }
    })

}
//分页
//datanum:计算哪一页
function nextpage(datanum) {
    var datanumber = Number(datanum);

    //当前页
    var nowpage = datanumber;
    //需要填充的数据总量
    var allcount = categoryData.length;
    //每个页面最大显示的数据
    var pageSize = eachPagecount;

    var pagedatalist = [];
    var leftindex = (nowpage - 1) * pageSize;
    var rightindex = nowpage * pageSize - 1;
    for (var i = leftindex; i >= 0 && i <= rightindex && i <= allcount - 1; i++) {
        pagedatalist.push(categoryData[i]);
    }
    //画最上的样子
    var categoryTimeline = $('.categoryTimeline');
    categoryTimeline.empty();
    var timeLine = $('<div class="timeline timeline-wrap"></div>');
    timeLine.append($('<div class="timeline-row">' +
        '<span class="node" style="-webkit-box-sizing: content-box;-moz-box-sizing: content-box;box-sizing: content-box;">' +
        '<i class="am-icon-folder"></i>' +
        '</span>' +
        '<h1 class="title  am-animation-slide-top"># ' + categoryName + '</h1>' +
        '</div>'));
    $.each(pagedatalist, function (index, obj) {

        var timelineRowMajor = $('<div class="timeline-row-major"></div>');
        timelineRowMajor.append($('<span class="node am-animation-slide-top am-animation-delay-1"></span>'));
        var content = $('<div class="content am-comment-main am-animation-slide-top am-animation-delay-1"></div>');
        content.append($('<header class="am-comment-hd" style="background: #fff">' +
            '<div class="contentTitle am-comment-meta">' +
            '<a href="/getArticleById?articleId=' + obj['articleId'] + '">' + obj['articleTitle'] + '</a>' +
            '</div>' +
            '</header>'));
        var amCommentBd = $('<div class="am-comment-bd"></div>');
        amCommentBd.append($('<i class="am-icon-calendar"> <a href="/archives?archive=' + obj['publishDate'] + '">' + obj['publishDate'] + '</a></i>' +
            '<i class="am-icon-folder"> <a href="/getArticlesByCateName?categoryName=' + obj['articleCategories'] + '">' + obj['articleCategories'] + '</a></i>'));
        var amCommentBdTags = $('<i class="am-comment-bd-tags am-icon-tag"></i>');
        for (var i = 0; i < obj['tagsList'].length; i++) {
            var tag = $('<a href="/tags?tag=' + obj['tagsList'][i]['tagName'] + '">' + obj['tagsList'][i]['tagName'] + '</a>');
            amCommentBdTags.append(tag);
            if (i != (obj['tagsList'].length - 1)) {
                amCommentBdTags.append(",");
            }
        }
        amCommentBd.append(amCommentBdTags);
        content.append(amCommentBd);
        timelineRowMajor.append(content);
        timeLine.append(timelineRowMajor);
    })
    categoryTimeline.append(timeLine);


    //分页导航实现
    //实现确定要分的页数 向上取整
    $('.am-pagination.am-pagination-centered').empty();
    var pages = $('.am-pagination.am-pagination-centered');
    var pagescountall = Math.ceil(allcount / pageSize);
    //显示
    var row = 5;
    //这边是判断是否可以上一步
    if (nowpage <= pagescountall && nowpage!=1) {
        pages.append('<li><a href="javascript:void(0)" onclick="nextpage(' + (nowpage - 1) + ')">&laquo; 上一页</a></li>')
    } else {
        pages.append('<li class="am-disabled"><a href="javascript:void(0)" onclick="nextpage(' + (nowpage - 1) + ')">&laquo; 上一页</a></li>')
    }
    // 这边是主体
    // 分为三个部分 左边：肯定是可以点右边
    // 我们要计算出左边的个数
    var leftpagecount = nowpage % row;
    if (leftpagecount == 0) {
        //最后一个位置 含有 左 中
        for (var x = 4; x>= 1; x--) {
            if (nowpage - x > 0) {
                pages.append(' <li><a href="javascript:void(0)" onclick="nextpage(this.innerText)">' + (nowpage -x) + '</a></li>');
            }
        }
        // //中间
        pages.append('<li class="am-active"><a href="javascript:void(0)" onclick="nextpage(this.innerText)">' + nowpage + '</a></li>');

    } else {
        //含有左中右
        for (var i = 0; i < leftpagecount - 1; i++) {
            pages.append(' <li><a href="javascript:void(0)" onclick="nextpage(this.innerText)">' + (nowpage - (leftpagecount-1 - i)) + '</a></li>');
        }
        // //中间
        pages.append('<li class="am-active"><a href="javascript:void(0)" onclick="nextpage(this.innerText)">' + nowpage + '</a></li>');

        //右边
        for (var j = 1; j <= 5 - leftpagecount; j++) {
            if (nowpage + j <= pagescountall) {
                pages.append(' <li><a href="javascript:void(0)" onclick="nextpage(this.innerText)">' + (nowpage + j) + '</a></li>');
            } else {
                pages.append(' <li class="am-disabled"><a href="javascript:void(0)" onclick="nextpage(this.innerText)">' + (nowpage + j) + '</a></li>');
            }
        }
        //是否可以下一步
        if (nowpage < pagescountall) {
            pages.append('<li><a href="javascript:void(0)" onclick="nextpage(' + (nowpage + 1) + ')">下一页 &raquo;</a></li>')
        } else {
            pages.append('<li class="am-disabled"><a href="javascript:void(0)" onclick="nextpage(' + (nowpage + 1) + ')">下一页 &raquo;</a></li>')
        }

    }



}


