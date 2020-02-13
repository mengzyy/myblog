var pager = jQuery('#ampagination-demo').pagination({
    // 配置参数
        maxSize: 70000, // Limit number for pagination size.      default:7
        totals: 10, //Total number of items in all pages.
        page: 1,  //select page index  1....total page
        pageSize: 5, //Maximum number of items per page. A value less than one indicates all items on one page.  default :10
        lastText: '»»', //Text for Last button.    default: '»»'
        firstText: '««', //Text for First button. default: '««'
        prevText: '«',//« //Text for Previous  button.  default:'«'
        nextText: '»', //Text for next button.   default:'»'
        rotate: true,//Whether to keep current page in the middle of  the visible ones   default:true
        directionLinks: true,// Whether to display Previous / Next buttons.  default:true
        boundaryLinks: true,// Whether to display first / last buttons.      default:true
        theme:'amazeui', // 'bootstrap' or 'amazeui'   defalut:''   default ui  only modify background color from bootstrap pagination
        btnSize:'' // 'sm'  or 'lg'  defalut : ''
}).onChangePage(function(e){



        window.alert('pager bind envent :selected page:'+e.page+'  current pageSize:'+e.pageSize+'  number of items'+e.totals)
        // console.info('pager bind envent :selected page:'+e.page+'  current pageSize:'+e.pageSize+'  number of items'+e.totals);
});


