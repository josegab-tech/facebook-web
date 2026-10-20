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
						<th scope="col">Nome</th>
						<th scope="col">Conteúdo</th>
						<th scope="col">Data</th>
						<th scope="col">Ações</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="post" items="${posts_list}">
						<tr>
							<th scope="row">${post.getId()}</th>
							<td>${post.getUser().getName()}</td>
							<td>${post.getContent()}</td>
							<td>${post.getPostDate()}</td>
							<td>
							<a href="${pageContext.request.contextPath}/posts/update?postId=${post.id}" class="bi bi-pencil-square"></a>
							<a href="${pageContext.request.contextPath}/posts/delete?postId=${post.id}" class="bi bi-trash" onclick="return confirm('Deseja realmente excluir este post?');"></a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
				</table>
				
		</div>
		
	<a href="${pageContext.request.contextPath}/posts/form" class="btn btn-primary">Cadastrar Post</a>
	</div>
	

	<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>