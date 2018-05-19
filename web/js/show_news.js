$(document).ready(function () {
    var urlParam= window.location.search;
    var loc = urlParam.substring(urlParam.lastIndexOf('=')+1, urlParam.length);
    //页面加载完后,加载新闻
    $.ajax({
        url:"showNewsById/"+loc,
        type:"GET",
        dataType:"json",
        success:function (result) {
            console.log(result)
              $("#contentNews").append("<div>"+result.content+"</div>")
              $("#titleNews").append(result.title)
        }
    })
})