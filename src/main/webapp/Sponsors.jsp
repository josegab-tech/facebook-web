<jsp:directive.page contentType="text/html; charset=UTF-8" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Patrocinadores - Facebook CRUD</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body>
	<div class="container mt-4">
		<div class="d-flex justify-content-between align-items-center mb-3">
			<h1>Patrocinadores</h1>
			<div>
				<a href="${pageContext.request.contextPath}/sponsors/form" class="btn btn-primary">
					<i class="bi bi-plus-lg"></i> Novo Patrocinador
				</a>
				<a href="/facebook" class="btn btn-secondary">Home</a>
			</div>
		</div>

		<table class="table table-bordered table-striped">
			<thead class="table-dark">
				<tr>
					<th>ID</th>
					<th>Nome</th>
					<th>Investimento (R$)</th>
					<th>Usuário Responsável</th>
					<th class="text-center">Ações</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="s" items="${sponsors_list}">
					<tr>
						<td>${s.id}</td>
						<td>${s.name}</td>
						<td>${s.investment}</td>
						<td>${s.user.name}</td>
						<td class="text-center">
    					<a href="${pageContext.request.contextPath}/sponsors/update?sponsorId=${s.id}" class="bi bi-pencil-square" title="Editar"></a>
    					<a href="${pageContext.request.contextPath}/sponsors/delete?sponsorId=${s.id}" class="bi bi-trash" onclick="return confirm('Deseja realmente excluir este patrocinador?');" title="Excluir"></a>
						</td>
						
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>