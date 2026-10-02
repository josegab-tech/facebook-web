<jsp:directive.page contentType="text/html; charset=UTF-8" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/bootstrap.min.css">
<title>Posts - Facebook CRUD</title>
<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-icons.css">
</head>
<body>

	<div class="container">
		<div class="row">
			<h2>Posts</h2>
			<table class="table table-striped">
			<thead>
				<tr>
					<th scope="col">Id</th>
					<th scope="col">Content</th>
					<th scope="col">Post Date</th>
					<th scope="col">User</th>
				</tr>
			</thead>
			<tbody>
				
				<c:forEach var="posts" items="${posts_list}">
				<tr>
					<th scope="row">${posts.getId()}</th>
					<td>${posts.getContent()}</td>
					<td>${posts.getPostDate()}</td>
					<td>${posts.getUser().getName()}</td>
				</tr>
				</c:forEach>
				
			</tbody>
</table>
		</div>
	</div>

<script src="${pageContext.request.contextPath}js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}js/bootstrap.min.js"></script>
</body>
</html>