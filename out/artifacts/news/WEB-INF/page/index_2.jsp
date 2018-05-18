<%@ page contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <title>新闻管理</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
        <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="js/main.js"></script>
        <script src="js/main2.js"></script>
</head>
<style>
    .row{
        width: 100%;
    }

</style>

<body>
<!-- 头部导航栏 -->
<nav class="navbar navbar-default  ">

    <div class="navbar-header">
        <a class="navbar-brand" href="index.jsp" >新闻管理系统</a>
    </div>
    <div class="container-fluid">
        <ul class="nav navbar-nav navbar-right">
            <c:if test="${admin!=null}">
                <li><a>
                    <button type="button" disabled="disabled" class="btn btn-success btn-xs">
                        <span class="glyphicon glyphicon-user "></span>管理员:${admin.username}
                    </button></a></li>
                <li><a href="${pageContext.request.contextPath}/logout">
                    <button type="button" class="btn btn-danger btn-xs">
                        <span class="glyphicon glyphicon-minus"></span>注销
                    </button></a>
                </li>
            </c:if>
            <c:if test="${admin==null}">
                <li><a href="${pageContext.request.contextPath}/login">
                    <button type="button" class="btn btn-danger btn-xs">
                        <span class="glyphicon glyphicon-user"></span>登录
                    </button></a></li>
                <li><a href="${pageContext.request.contextPath}/regist">
                    <button type="button" class="btn btn-warning btn-xs">
                        <span class="glyphicon glyphicon-plus"></span>注册
                    </button></a>
                </li>
            </c:if>
        </ul>
    </div>
</nav>
<!--导航栏结束-->
<div class="row">
    <div class="col-md-2">
        <div class="well">
            <!-- 左侧列表导航 -->
            <div class="panel-group table-responsive" role="tablist">
                <div class="panel panel-primary leftMenu">
                    <!-- 利用data-target指定要折叠的分组列表 -->
                    <div class=" active panel-heading" id="collapseListGroupHeading1" data-toggle="collapse" data-target="#collapseListGroup1" role="tab" >
                        <h4 class="panel-title">
                            综合设置
                            <span class="glyphicon glyphicon-chevron-right"></span>
                        </h4>
                    </div>
                    <div id="collapseListGroup1" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="collapseListGroupHeading1">
                        <ul class="list-group">
                            <li class=" list-group-item"  onclick="turnpage()">
                                <a id="lmgl" href="${pageContext.request.contextPath}/index">栏目管理
                                    <span class="glyphicon glyphicon-list-alt"></span>
                                </a>
                            </li>
                            <li class="list-group-item">
                                <a href="${pageContext.request.contextPath}/index_2">
                                    新闻管理
                                    <span class="glyphicon glyphicon-align-left"></span>
                                </a>
                            </li>
                            <li class="list-group-item">
                                <a href="#">
                                    用户管理  <span class="glyphicon glyphicon-fire"></span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-10">
        <div class="well">
            <!--context header-->
            <nav  class="navbar navbar-default" role="navigation">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="WEB-INF/page/index.jsp">home</a>
                    </div>
                    <form action="${pageContext.request.contextPath}/index_2" method="post" class="navbar-form navbar-left" role="search">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="搜索新闻" name="title">
                        </div>
                        <button type="submit" class="btn btn-default">submit</button>
                    </form>
                </div>
            </nav>
            <!-- 这div用来装左边菜单内容 -->
            <div class="row">
                <table class="table table-striped">
                    <ol class="breadcrumb">
                        <li><a href="WEB-INF/page/index.jsp">Home</a></li>
                        <li class="active">新闻管理</li>
                    </ol>
                    <thead>
                    <tr>
                        <th>
                            <label><input type="checkbox" value="" class="checkAll" >新闻栏目</label>
                        </th>
                        <th>发布时间</th>
                        <th>作者</th>
                        <th>所属栏目</th>
                        <th>是否可用</th>
                    </tr>

                    <div class="container">
                        筛选:
                        <select id="selectColumn">
                            <option value="all">所属栏目</option>
                           <c:forEach items="${cList}" var="clist">
                            <option value="${clist.c_id}">${clist.c_name}</option>
                           </c:forEach>
                        </select>
                        <bottom class="btn btn-warning " data-toggle="modal" data-target="#myModal">添加</bottom>
                        <bottom onclick="editNews()"  data-toggle="modal" data-target="#myModal1" class="btn btn-success" >编辑</bottom>
                        <bottom id="delete" class="btn btn-danger" onclick="deleteNews()" title="还原"
                                data-trigger="hover" data-toggle="popover" data-placement="right"
                                data-content="再次选中被删除的数据即可还原">删除</bottom>
                    </div>
                    </thead>

                        <%--隐藏域--%>
                        <tbody id="tbody" >
                            <c:forEach items="${pageBean.newsList}" var="items" varStatus="status">
                                <tr>
                                    <td>
                                        <label class="columnName" ><input class="check" name="id" type="checkbox" value="${items.p_id}">${fn:substring(items.title,0,15)}..</label>
                                    </td>
                                        <td>${pageBean.toDate[status.count-1]}</td>
                                    <td>${items.writer}</td>
                                    <td>${items.c_name}</td>
                                    <td>${items.state==1?"可用":"不可用"}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                </table>
                <h4>总<b>${pageBean.totalPage}</b>页/当前页<b>${param.page==null?1:param.page}</b>共<b>${pageBean.total}</b>条</h4>
                <div class="col-md-12 text-right">
                    <ul class="pagination">
                        <li><a href="${pageContext.request.contextPath}/index_2?page=${param.page-1<=1?1:param.page-1}">&laquo;</a></li>
                        <li><a href="${pageContext.request.contextPath}/index_2?page=${1>pageBean.totalPage?pageBean.totalPage:1}">1</a></li>
                        <li><a href="${pageContext.request.contextPath}/index_2?page=${2>pageBean.totalPage?pageBean.totalPage:2}">2</a></li>
                        <li><a href="${pageContext.request.contextPath}/index_2?page=${3>pageBean.totalPage?pageBean.totalPage:3}">3</a></li>
                        <li><a href="${pageContext.request.contextPath}/index_2?page=${4>pageBean.totalPage?pageBean.totalPage:4}">4</a></li>
                        <li><a href="${pageContext.request.contextPath}/index_2?page=${5>pageBean.totalPage?pageBean.totalPage:5}">5</a></li>
                        <li><a href="${pageContext.request.contextPath}/index_2?page=${param.page+1>=pageBean.totalPage?pageBean.totalPage:param.page+1}">&raquo;</a></li>
                    </ul>
                </div>
            </div>

        </div>
    </div>
</div>
<!-- 模态框（Modal） 添加-->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    <b>添加新闻</b>
                </h4>
            </div>
            <form id="addNewsForm" class="form-horizontal " role="form" action="${pageContext.request.contextPath}/addNews" method="post"  enctype="multipart/form-data" >
                <div class="modal-body">
                    <div class="form-group">
                        <div class="col-sm-12">
                            <input type="text" class="form-control" id=""
                                   placeholder="请输入新闻标题" name="title">
                        </div>
                    </div>
                    <%--新闻内容--%>
                    <div class="form-group">
                        <%--<div class="col-sm-12">--%>
                        <%--<textarea name="content" id="content" cols="78" rows="10"  placeholder="请输入新闻内容" ></textarea>--%>
                        <%--</div>--%>
                            <!-- 注意， 只需要引用 JS，无需引用任何 CSS ！！！-->
                            <input type="hidden" name="content" id="contentId">
                            <div id="editor">
                                <b> 复制图片粘贴在内容里即可实现插入图片</b>
                            </div>
                            <script  src="js/wangEditor.min.js"></script>
                            <script type="text/javascript">
                                $(document).ready(function () {
                                var E = window.wangEditor
                                    var editor = new E('#editor')
                                    editor.customConfig.menus = [
                                        'head',  // 标题
                                        'bold',  // 粗体
                                        'fontSize',  // 字号
                                        'fontName',  // 字体
                                        'italic',  // 斜体
                                        'underline',  // 下划线
                                        'strikeThrough',  // 删除线
                                        'foreColor',  // 文字颜色
                                        'backColor',  // 背景颜色
                                        'emoticon',  // 表情
                                    ]
                                    editor.customConfig.uploadImgServer = '/upload'
                                    editor.create()
                                    document.getElementById('addContent').addEventListener('click', function () {
                                        // 读取 html
                                        $("#contentId").val(editor.txt.html())
                                    }, false)
                                })
                            </script>
                    </div>
                    <c:if test="${message!=null}">
                        <script type="text/javascript">
                            alert("必须插入标题图片:)");
                        </script>

                    </c:if>
                    <div class="form-group">
                        <label  class="col-sm-2 control-label">标题图片</label>
                        <input type="file" name="pictureFile"/>
                    </div>
                    <%--所属栏目--%>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">所属栏目</label>
                        <select id="TabelselectColumn">
                            <option value="all">所属栏目</option>
                            <c:forEach items="${cList}" var="clist">
                                <option value="${clist.c_id}">${clist.c_name}</option>
                            </c:forEach>
                        </select>
                        <input id="pcid" type="hidden" name="p_c_id">
                    </div>
                    <%--新闻作者--%>
                    <div class="form-group">
                        <label for="firstname1" class="col-sm-2 control-label">新闻作者</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="firstname1"
                                   placeholder="请输入新闻作者" name="writer">
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        关闭
                    </button>
                    <button  id="addContent" class="btn btn-primary">
                        添加
                    </button>
                </div>
            </form>

        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 模态框（Modal） 修改
-->
<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="myModalLabel1">
                    <b>修改新闻</b>
                </h4>
            </div>
            <form id="editNewsForm" class="form-horizontal " role="form" action="${pageContext.request.contextPath}/editNews" method="post"  enctype="multipart/form-data" >
             <input name="p_id" id="pid" type="hidden">
                <div class="modal-body">
                    <%--新闻标题--%>
                    <div class="form-group">
                        <div class="col-sm-12">
                            <input id="title" type="text" class="form-control"
                                   placeholder="请输入新闻标题" name="title">
                        </div>
                    </div>
                    <%--新闻内容--%>
                    <div class="form-group">
                        <div class="col-sm-12">
                            <textarea name="content" id="contentEdit" cols="78" rows="10"  placeholder="请输入新闻内容" ></textarea>
                        </div>
                    </div>
                    <%--新闻图片--%>
                    <div class="form-group">
                        <input type="hidden" id="img" name="img">
                        <label  class="col-sm-2 control-label">标题图片</label>
                        <input type="file" name="pictureFile"/>
                    </div>
                    <%--所属栏目--%>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">所属栏目</label>
                        <select id="TabelselectColumnByEdit">
                            <option value="all">所属栏目</option>
                            <c:forEach items="${cList}" var="clist">
                                <option value="${clist.c_id}">${clist.c_name}</option>
                            </c:forEach>
                        </select>
                        <input id="pcidEdit" type="hidden" name="p_c_id">
                    </div>
                    <%--新闻作者--%>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">新闻作者</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="writer"
                                   placeholder="请输入新闻作者" name="writer">
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        关闭
                    </button>
                    <button    class="btn btn-primary">
                        修改
                    </button>
                </div>
            </form>

        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
</body>
</html>