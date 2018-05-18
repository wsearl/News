    $(document).ready(function () {
        $("#selectColumn").change(function () {
            //获得option值,默认全查 all
            var value = this.options[this.options.selectedIndex].value;
            window.location.href="getNewsByColumn?value="+value;
        })

        //把下拉菜单的值传入p_c_id
        $("#TabelselectColumn").change(function () {
            var value = this.options[this.options.selectedIndex].value;
            $("#pcid").val(value);
        })


        //把下拉菜单的值传入p_c_id
        $("#TabelselectColumnByEdit").change(function () {
            var value = this.options[this.options.selectedIndex].value;
            $("#pcidEdit").val(value);
        })
    })
//删除新闻 逻辑删除
function  deleteNews(){

    var id_array=new Array();
    //获得name为id的复选框的值 循环遍历
    $('input[name="id"]:checked').each(function(){
        id_array.push($(this).val());//向数组中添加元素
    });

    var idstr=id_array.join(',');//将数组元素连接起来以构建一个字符串
    $.ajax({
        type:"POST",
        url:"deleteNews",
        data:{pid:idstr},
        success:function () {
            location.reload();
        }
    });
    if ($('.check').is(':checked')==false) {
        alert("请选中数据:)");
    }
}
//修改新闻
function editNews() {
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
        //发送请求,从后台查找数据
        var url = 'getNewsById'
        $.ajax({
            type: 'POST',
            contentType:"application/x-www-form-urlencoded; charset=utf-8",
            url: url,
            data: {p_id:id.val()},
            success:function (result) {
                //回显新闻标题
                $("#title").val(result.title)
                //回显内容
                $("#contentEdit").val(result.content)
                //回显作者
                $("#writer").val(result.writer)
                //回显下拉菜单
                $("#TabelselectColumnByEdit").find("option[value="+result.p_c_id+"]").prop("selected",true);
                var val = $("#TabelselectColumnByEdit option:selected").val();
                //如果所属栏目没有改变则不变化设置选来的值
                $("#pcidEdit").val(val);
                //设置pid
                $("#pid").val(result.p_id);
                console.log(result)
            }
        });
    } else {
        alert("请选中数据:)")
        location.reload(true);
    }
}