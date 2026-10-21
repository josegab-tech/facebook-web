<jsp:directive.page contentType="text/html; charset=UTF-8" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Usuários - Facebook CRUD</title>
	<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-icons.css">
</head>
<body>

	<div class="container">
		<div class="row mt-3">
			
			<div class="col-md-8">
				<a class="btn btn-secondary" 
				   href="${pageContext.request.contextPath}/">
					Home
				</a>
			</div>
		</div>
				
		<div class="row mt-3">
			<div class="col-md-2"></div>
			
			<div class="col-md-8">
				<h2>Usuários</h2>
				<table class="table table-striped">
					<thead>
						<tr>
							<th scope="col">Id</th>
							<th scope="col">Nome</th>
							<th scope="col">Sexo</th>
							<th scope="col">Email</th>
							<th scope="col">Ações</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="usuario" items="${users_list}">
							<tr>
								<th scope="row">${usuario.getId()}</th>
								<td>${usuario.getName()}</td>
								<td>${usuario.getGender()}</td>
								<td>${usuario.getEmail()}</td>
								<td>
								   <a href="${pageContext.request.contextPath}/users/update?userId=${usuario.getId()}" 
									   class="bi bi-pencil-square"></a>
								   <a href="${pageContext.request.contextPath}/users/delete?userId=${usuario.getId()}" 
								   	   class="bi bi-trash"></a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
				<a href="users_form.jsp" class="btn btn-primary">
					Cadastrar Usuário
				</a>
			</div>
			
			<div class="col-md-2"></div>
		</div>
	</div>

	<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>