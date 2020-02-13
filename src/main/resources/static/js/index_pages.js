//分页请求
function dividepage(nowpage) {

    $.ajax({
        type: 'GET',
        url: '/getArticlePageList',
        dataType: 'json',
        data: {
            pageNum: nowpage
        },
        success: function (data) {
            if (data['listlen'] != 0) {
                putInArticle(data['data'], nowpage);
                scrollTo(0, 0);//回到顶部
            }
        },
        error: function () {
            alert("获得文章信息失败！");
        }
    });
}

//填充文章
//这个文章数
//主页面最多5个博客，然后可以分页
//分页数：（博客数/5）+if 博客数%5!=0? +1 :+0
function putInArticle(data, nowpage) {
    $('.articles').empty();
    var articles = $('.articles');
    $.each(data['articleList'], function (index, obj) {
        var center = $('<div class="center">' +
            '<header class="article-header">' +
            '<h1 itemprop="name">' +
            '<a class="article-title" href="/getArticleById?articleId=' + obj['articleId'] + '" target="_blank">' + obj['articleTitle'] + '</a>' +
            '</h1>' +
            '<div class="article-meta row">' +
            '<span class="articleType am-badge am-badge-success">' + obj['articleType'] + '</span>' +
            // '<div class="articlePublishDate">' +
            //
            // '<i class="am-icon-calendar"><a class="linkColor" href="/archives?archive=' + obj['publishDate'] + '"> ' + obj['publishDate'] + '</a></i>' +
            // '</div>' +
            '<div class="originalAuthor">' + '<i class="am-icon-user"> ' + obj['author'] + '</i>' + '</div>' +

            '<div class="categories">' + '<i class="am-icon-folder"><a class="linkColor" href="/category?articleCategories=' + obj['articleCategories'] + '"> ' + obj['articleCategories'] + '</a></i>' + '</div>' +

            '</div>' +
            '</header>' +

            '<div class="article-entry">' + obj['articleTabloid'] + '</div>' +

            '<div class="read-all">' + '<a href="/getArticleById?articleId=' + obj['articleId'] + '" target="_blank">阅读全文 <i class="am-icon-angle-double-right"></i></a>' + '</div>' +

            '<hr>' +

            '<div class="article-tags">' + '</div>' +

            '</div>');
        articles.append(center);
        var articleTags = $('.article-tags');
        for (var i = 0; i < obj['tagsList'].length; i++) {
            var articleTag = $('<i class="am-icon-tag"><a class="tag" href="/tag?tag=' + obj['tagsList'][i]['tagName'] + '"> ' + obj['tagsList'][i]['tagName'] + '</a></i>');
            articleTags.eq(index).append(articleTag);

        }
    })

    //下面是处理分页的请求
    $('.am-pagination.am-pagination-centered').empty();
    var pages = $('.am-pagination.am-pagination-centered');
    //文章总数
    var articlescount = data['allpagecount'];
    //当前页数
    var nownum = nowpage;
    //每页数
    var eachcount = data['eachpagecount'];

    //实现确定要分的页数 向上取整
    var pagescountall = Math.ceil(articlescount / eachcount);
    //显示
    var row = 5;

    //这边是判断是否可以上一步
    if (nownum <= pagescountall && nownum!=1) {
        pages.append('<li><a href="javascript:void(0)" onclick="nextpage(' + (nownum - 1) + ')">&laquo; 上一页</a></li>')
    } else {
        pages.append('<li class="am-disabled"><a href="javascript:void(0)" onclick="nextpage(' + (nownum - 1) + ')">&laquo; 上一页</a></li>')
    }


    // 这边是主体
    // 分为三个部分 左边：肯定是可以点右边
    // 我们要计算出左边的个数
    var leftpagecount = nowpage % row;
    if (leftpagecount == 0) {
        //最后一个位置 含有 左 中
        for (var x = 4;  x>= 1; x--) {
            if (nownum - x > 0) {
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

    }


    //是否可以下一步
    if (nownum < pagescountall) {
        pages.append('<li><a href="javascript:void(0)" onclick="nextpage(' + (nownum + 1) + ')">下一页 &raquo;</a></li>')
    } else {
        pages.append('<li class="am-disabled"><a href="javascript:void(0)" onclick="nextpage(' + (nownum + 1) + ')">下一页 &raquo;</a></li>')
    }


}


//首页初始化,ajax请求
$.ajax({
    type: 'GET',
    url: '/getArticlePageList',
    dataType: 'json',
    data: {
        pageNum: 1
    },
    success: function (data) {
        if (data['listlen'] != 0) {
            putInArticle(data, 1);
            scrollTo(0, 0);//回到顶部
        }
    },
    error: function () {
        alert("获得文章信息失败！");
    }
});


function nextpage(datanum) {
    var datanumber = Number(datanum);
    //调用js
    $.ajax({
        type: 'GET',
        url: '/getArticlePageList',
        dataType: 'json',
        data: {
            pageNum: datanumber
        },
        success: function (data) {
            if (data['listlen'] != 0) {
                putInArticle(data, datanumber);
                scrollTo(0, 0);//回到顶部
            }
        },
        error: function () {
            alert("获得文章信息失败！");
        }
    });


}