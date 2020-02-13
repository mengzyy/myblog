
var articleId = "";

//填充文章
function putInArticle(data) {
    $('.zhy-article-top').html('');
    $('.zhy-article-footer').html('');
    var articleTop = $('<article-top><div class="article-title">' +
        '<h1>' + data.articleTitle + '</h1>' +
        '</div>' +
        '<div class="article-info row">' +
        '<div class="article-info article-info-type am-badge am-badge-success">' +
        data.articleType +
        '</div>' +
        '<div class="article-info article-info-publishDate">' +
        '<i class="am-icon-calendar"><a class="articleCategoryColor" href="/archives?archive=' + data.publishDate + '"> ' + data.publishDate + '</a></i>' +
        '</div>' +
        '<div class="article-info article-info-originalAuthor">' +
        '<i class="am-icon-user"> ' + data.originalAuthor + '</i>' +
        '</div>' +
        '<div class="article-info article-info-categories">' +
        '<i class="am-icon-folder"> <a class="articleCategoryColor" href="/categories?category=' + data.articleCategories + '">' + data.articleCategories + '</a></i>' +
        '</div>' +
        '</div></article-top><div class="article-i-say">' +
        '多年以后，愿你的城市，有清风，有烈酒，也有人是你的归途。<span class="article-i-say-me">--- 张海洋</span>' +
        '</div>');
    $('.zhy-article-top').append(articleTop);
    $("#mdText").text(data.articleContent);
    var wordsView;
    wordsView = editormd.markdownToHTML("wordsView", {
        htmlDecode: "true", // you can filter tags decode
        emoji: true,
        taskList: true,
        tex: true,
        flowChart: true,
        sequenceDiagram: true
    });
    var articleFooter = $('<div class="end-logo">' +
        '<i class="am-icon-btn am-success am-icon-lg">完</i>' +
        '</div>' +
        '<div class="show-weixin">' +
        '<p><i class="weiXinQuoteLeft am-icon-quote-left "></i></p><br>' +
        '<p class="show-weixin-pic">' +
        '<img src="https://zhy-myblog.oss-cn-shenzhen.aliyuncs.com/static/img/weixin.jpg">' +
        '</p>' +
        '<p class="show-weixin-pic">欢迎关注我的微信公众号：zhyocean1314</p>' +
        '<p><i class="weiXinQuoteRight am-icon-quote-right "></i></p>' +
        '</div>' +
        '<div>' +
        '<ul class="post-copyright">' +
        '<li><strong>本文作者：</strong><span id="authorFooter">' + data.originalAuthor + '</span></li>' +
        '<li><strong>原文链接：</strong><span id="urlFooter"><a href="' + data.articleUrl + '">' + data.articleUrl + '</a></span></li>' +
        '<li><strong>版权声明：</strong> 本博客所有文章除特别声明外，均采用<span id="copyRightFooter"><a href="https://creativecommons.org/licenses/by/3.0/cn/" target="_blank"> CC BY 3.0 CN协议</a></span>进行许可。转载请署名作者且注明文章出处。</li>' +
        '</ul>' +
        '</div>' +
        '<div class="article-tags">' +

        '</div>' +
        '<hr>' +
        '<div class="two-article">' +
        '<span class="article-last">' +

        '</span>' +
        '<span class="article-next">' +
        '</span>' +
        '</div>');
    $('.zhy-article-footer').append(articleFooter);
    var tags = $('<div class="tags"></div>');
    for(var i=0;i<data.articleTags.length;i++){
        var tag = $('<i class="am-icon-tag"></i><a class="articleTagColor" href="/tags?tag=' + data.articleTags[i] + '"> ' + data.articleTags[i] + '</a>');
        tags.append(tag);
    }
    $('.article-tags').append(tags);
    if(data.lastStatus == "200"){
        var articleLast200 = $('<i class="am-icon-angle-left am-icon-sm"></i>&nbsp;&nbsp;<a class="lastAndNext" href="' + data.lastArticleUrl +'">' + data.lastArticleTitle + '</a>');
        $('.article-last').append(articleLast200);
    } else {
        var articleLast500 = $('<i class="am-icon-angle-left am-icon-sm"></i>&nbsp;&nbsp;<a  class="lastAndNext">' + data.lastInfo + '</a>');
        $('.article-last').append(articleLast500);
    }
    if(data.nextStatus == "200"){
        var articleNext200 = $('<a class="lastAndNext" href="' + data.nextArticleUrl +'">' + data.nextArticleTitle + '</a>' + '&nbsp;&nbsp;<i class="am-icon-angle-right am-icon-sm"></i>');
        $('.article-next').append(articleNext200);
    } else {
        var articleNext500 = $('<a  class="lastAndNext">' + data.nextInfo + '</a>' + '&nbsp;&nbsp;<i class="am-icon-angle-right am-icon-sm"></i>');
        $('.article-next').append(articleNext500);
    }
    var likeBtn = $('<div class="likeBtn am-btn am-btn-danger">' +
        '<div class="likeHeart">' +
        '<i class="am-icon-heart-o">&nbsp;&nbsp;喜欢</i>' +
        '</div>' +
        '<div class="likesNum">' +
        '<span> ' + data.likes + '</span>' +
        '</div>' +
        '</div>');
    $('.other').append(likeBtn);
    if(data.isLiked == 1){
        $('.likeBtn').css({
            "background-color": "#EA6F5A",
            "color":"white"
        });
        $('.likesNum').css({
            "border-left": "1px solid white"
        });
        $('.likeHeart').find('i').removeClass("am-icon-heart-o");
        $('.likeHeart').find('i').addClass("am-icon-heart");
    }
    $('.other').append($('<div class="social-share" data-initialized="true" data-url="https://www.zhyocean.cn/article/' + data.articleId  + '"  data-title="' + data.articleTitle + '">' +
        '<a href="#" class="social-share-icon icon-qq" data-am-popover="{content: \'分享至QQ好友\', trigger: \'hover focus\'}"></a>' +
        '<a href="#" class="social-share-icon icon-qzone" data-am-popover="{content: \'分享至QQ空间\', trigger: \'hover focus\'}"></a>' +
        '<a href="#" class="social-share-icon icon-wechat"></a>' +
        '<a href="#" class="social-share-icon icon-weibo" data-am-popover="{content: \'分享至微博\', trigger: \'hover focus\'}"></a>' +
        '</div>'))

    //选中所有需放大的图片加上data-src属性
    $("#wordsView img").each(function(index){
        if(!$(this).hasClass("emoji")){
            var a=$(this).attr('src');
            $(this).attr("data-src",a);

            $(this).addClass("enlargePicture");
        }
    });
    //放大图片框架
    lightGallery(document.getElementById('wordsView'));
}

