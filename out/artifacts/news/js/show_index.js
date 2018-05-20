$(document).ready(function () {
    //页面加载完后,加载栏目
  $.ajax({
      url:"showColumn",
      type:"GET",
      dataType:"json",
      success:function (result) {
          //循环遍历栏目名
          for(var i = 0;i<result.length;i++){
              $("#ColumnName").append("<a id='"+result[i].c_id+"' onclick='activeId("+result[i].c_id+")' href=\"index.html?c_id="+result[i].c_id+"\" class=\"list-group-item \">"+result[i].c_name+"</a>")
            $("#1").addClass("active")
          }
        console.log(result)
      },
      error:function () {
          alert("内部请求数据失败:(")
      }
  });
    //对应栏目的url值  ?c_id = xx
    (function ($) {
        $.getUrlParam = function (name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return unescape(r[2]); return null;
        }
    })(jQuery);
    var xx = $.getUrlParam('c_id');
    //页面加载完后,加载新闻
    $.ajax({
        url:"showNews",
        data:{c_id:xx},
        type:"GET",
        dataType:"json",
        success:function (result) {
            //定义时间
            Date.prototype.format = function(fmt) {
                var o = {
                    "M+" : this.getMonth()+1,                 //月份
                    "d+" : this.getDate(),                    //日
                    "h+" : this.getHours(),                   //小时
                    "m+" : this.getMinutes(),                 //分
                    "s+" : this.getSeconds(),                 //秒
                    "q+" : Math.floor((this.getMonth()+3)/3), //季度
                    "S"  : this.getMilliseconds()             //毫秒
                };
                if(/(y+)/.test(fmt)) {
                    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
                }
                for(var k in o) {
                    if(new RegExp("("+ k +")").test(fmt)){
                        fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
                    }
                }
                return fmt;
            }
            //定义div 为div填充新闻所需内容
            for(var i=0;i<result.length;i++){
                var d = new Date(result[i].date);
                var time1 = new Date(d).format("yyyy-MM-dd hh:mm:ss");
                var parentdiv=$(' <div  class="news-list-item clearfix">  ' +
                    '<div class="col-xs-5"><img src="http://localhost:8080/img/'+result[i].img+'"> </div>' +
                    '<div class="col-xs-7">' +
                    '<div><a href="news.html?p_id='+result[i].p_id+'" class="title">'+result[i].title+'</a></div>' +
                    '<div class="info" >' +
                    '<span><span class="avatar"><img src="img/logo.jpg" ></span>'+result[i].writer+'</span>' +
                    '<span>&nbsp25k评论&nbsp</span><span>'+time1+'</span>' +
                    '</div>' +
                    '</div>' +
                    '</div>');
                $("#boxNews").append(parentdiv);

            }
            console.log(result)
        }
    })

})