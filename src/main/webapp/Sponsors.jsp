<jsp:directive.page contentType="text/html; charset=UTF-8" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Patrocinadores - Facebook CRUD</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
</head>
<body>
	<div class="container mt-4">
		<div class="d-flex justify-content-between align-items-center mb-3">
			<h1>Patrocinadores</h1>
			<div>
				
				<a href="${pageContext.request.contextPath}/" class="btn btn-secondary">Voltar</a>
			</div>
		</div>

		<table class="table table-bordered table-striped">
			<thead class="table-dark">
				<tr>
					<th>ID</th>
					<th>Nome</th>
					<th>Investimento (R$)</th>
					<th>Usuário Responsável</th>
				</tr>
			</thead>
			<tbody>
				
				<c:forEach var="s" items="${sponsors_list}">
					<tr>
						<td>${s.id}</td>
						<td>${s.name}</td>
						<td>${s.investment}</td>
						<td>${s.user.name}</td> 
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>