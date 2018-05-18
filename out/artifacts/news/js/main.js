$(document).ready(function() {
    $(".checkAll").click(function () {
        // 判断是否全选/反选
        $('input:checkbox').each(function () {
            if ($(this).attr("checked")) {
                $('input:checkbox').each(function () {
                    $(this).attr('checked', false);
                });
            } else {
                $(this).attr('checked', true);
            }
        });
    });

});

    $(function (){
        $("[data-toggle='popover']").popover();
    });
//修改
function edit() {
    if ($('.check').is(':checked')) {
        //判断选中的个数
        if ($("input[type='checkbox']:checked").length>1){
            alert("只能选中一条数据哦!")
            location.reload(true);
            return false;
        }
        // 修改选中的数据
        //发送ajax,获得栏目名,回显
        //判断复选框是否选中
        if($(".check").is(":checked")){
            //获取选中的id值
            var  id = $("input[type='checkbox']:checked");
        }
        var url = 'getColumnName'
        $.ajax({
            type: 'POST',
            contentType:"application/x-www-form-urlencoded; charset=utf-8",
            url: url,
            data: {c_id:id.val()},
            success:function (data) {
                $("#columnName").val(data);
                //设置
                $("#c_id").val(id.val());
            }
        });
    } else {
        alert("请选中数据:)")
        location.reload(true);
    }
}

$(function(){
    $(".panel-heading").click(function(e){
        /*切换折叠指示图标*/
        $(this).find("span").toggleClass("glyphicon-chevron-down");
    });
});

// 显示右边的内容区域
function  turnpage() {
    $("#context").hide();
}
//提交添加表单
function subFormAdd(){
    $("#submitForm").submit;
}
//提交修改表单
function subFormEdit(){
    $("#subFormEdit").submit;
    location.reload();
}
//删除栏目
function deleteColumn(){
    var  id = $("input[type='checkbox']:checked");
    $.ajax({
        type:"POST",
        url:"deleteColumn",
        data:{c_id:id.val()},
        success:function () {
            location.reload();
        }
    });
    if ($('.check').is(':checked')==false) {
        alert("请选中数据:)");
    }
}
