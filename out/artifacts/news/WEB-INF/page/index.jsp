<%@ page contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title></title>
	<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="js/main.js"></script>
</head>
	<style>
		a:hover{
			text-decoration:none;
		}
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
								<form action="${pageContext.request.contextPath}/index" method="post" class="navbar-form navbar-left" role="search">
									<div class="form-group">
										<input type="text" class="form-control" placeholder="搜索栏目名" name="c_name">
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
									<li class="active">栏目管理</li>
								</ol>
								<thead>
								<tr>
									<th>
										<label><input type="checkbox" value="" class="checkAll" >栏目id</label>
									</th>
									<th>栏目名</th>
									<th>是否可用</th>
								</tr>
								<div class="container">
									<bottom class="btn btn-warning " data-toggle="modal" data-target="#myModal">添加</bottom>
									<bottom onclick="edit()"  data-toggle="modal" data-target="#myModal1" class="btn btn-success" >编辑</bottom>
									<bottom id="delete" class="btn btn-danger" onclick="deleteColumn()" title="还原"
											data-trigger="hover" data-toggle="popover" data-placement="right"
											data-content="再次选中被删除的数据即可还原">删除</bottom>
								</div>
								</thead>

								<form id="pageQuery" action="pageQuery.action" method="post">
									<%--隐藏域--%>
									<tbody>
									<c:forEach items="${page.columnList}" var="items">
									<tr>
										<td>
											<label class="columnName" ><input class="check" type="checkbox" value="${items.c_id}">${items.c_id}</label>
										</td>
										<td>${items.c_name}</td>
										<td>${items.c_state==1?"可用":"不可用"}</td>
									</tr>
										</c:forEach>
									</tbody>
								</form>
							</table>
							<h4>总<b>${page.totalPage}</b>页/当前页<b>${param.page==null?1:param.page}</b>共<b>${page.total}</b>条</h4>
							<div class="col-md-12 text-right">
								<ul class="pagination">
									<li><a href="${pageContext.request.contextPath}/index?page=${param.page-1<=1?1:param.page-1}">&laquo;</a></li>
									<li><a href="${pageContext.request.contextPath}/index?page=${1>page.totalPage?page.totalPage:1}">1</a></li>
									<li><a href="${pageContext.request.contextPath}/index?page=${2>page.totalPage?page.totalPage:2}">2</a></li>
									<li><a href="${pageContext.request.contextPath}/index?page=${3>page.totalPage?page.totalPage:3}">3</a></li>
									<li><a href="${pageContext.request.contextPath}/index?page=${4>page.totalPage?page.totalPage:4}">4</a></li>
									<li><a href="${pageContext.request.contextPath}/index?page=${5>page.totalPage?page.totalPage:5}">5</a></li>
									<li><a href="${pageContext.request.contextPath}/index?page=${param.page+1>=page.totalPage?page.totalPage:param.page+1}">&raquo;</a></li>
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
						 <b>添加栏目</b>
					  </h4>
				  </div>
					  <form class="form-horizontal " role="form" action="${pageContext.request.contextPath}/addColumn" method="post">
				  <div class="modal-body">
						  <div class="form-group">
							  <label for="firstname" class="col-sm-2 control-label">栏目名称</label>
							  <div class="col-sm-10">
								  <input type="text" class="form-control" id="firstname"
										 placeholder="请输入栏目名" name="c_name">
							  </div>
						  </div>
				  </div>
				  <div class="modal-footer">
					  <button type="button" class="btn btn-default" data-dismiss="modal">
						  关闭
					  </button>
					  <button   id="submitForm" onclick="subFormAdd()" class="btn btn-primary">
						  添加
					  </button>
				  </div>
					  </form>

			  </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
	  </div><!-- /.modal -->

	  <!-- 模态框（Modal） 编辑 -->
	  <div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true">
		  <div class="modal-dialog">
			  <div class="modal-content">
				  <div class="modal-header">
					  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
					  </button>
					  <h4 class="modal-title" id="myModalLabel1">
						  <b>修改栏目</b>
					  </h4>
				  </div>
				  <form class="form-horizontal " role="form" action="${pageContext.request.contextPath}/editColumnName" method="post">
						<%--隐藏域 用于 修改 栏目名--%>
					    <input type="hidden" name="c_id" id="c_id" >

					       <div class="modal-body">

						    <div class="form-group">
							  <label for="firstname" class="col-sm-2 control-label">栏目名称</label>
							  <div class="col-sm-10">
								  <input type="text" class="form-control" id="columnName"
										 placeholder="请输入栏目名" name="c_name" value="">
							  </div>
						  </div>
					  </div>
					  <div class="modal-footer">
						  <button type="button" class="btn btn-default" data-dismiss="modal">
							  关闭
						  </button>
						  <button   id="submitForm1" onclick="subFormEdit()" class="btn btn-primary">
							  修改
						  </button>
					  </div>
				  </form>

			  </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
	  </div><!-- /.modal -->
  </body>
