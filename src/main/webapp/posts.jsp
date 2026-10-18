<jsp:directive.page contentType="text/html; charset=UTF-8" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Posts - Facebook CRUD</title>
	<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-icons.css">
</head>
<body>

	<div class="container">
	<div class="col-md-5  mt-3">
	<a class="btn btn-secondary" 
				   href="${pageContext.request.contextPath}/">
					Home
		</a>
	</div>
	
		<div class="row">
			<h2>Posts</h2>
			<table class="table table-striped">
				<thead>
					<tr>
						<th scope="col">Id</th>
						<th scope="col">Conteúdo</th>
						<th scope="col">Data</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="post" items="${posts_list}">
						<tr>
							<th scope="row">${post.getId()}</th>
							<td>${post.getContent()}</td>
							<td>${post.getPostDate()}</td>
						</tr>
					</c:forEach>
				</tbody>
				</table>
				
		</div>
		<a href="post_form.jsp" class="btn btn-primary">
					Cadastrar Post
				</a>
	</div>
	

	<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>