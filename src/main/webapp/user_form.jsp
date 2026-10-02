<jsp:directive.page contentType="text/html; charset=UTF-8" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Cadastro de Usuário - Facebook CRUD</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-icons.css">
</head>
<body>
	<div class="container">
		<div class="row mt-3">
			<div class="col-md-2"></div>
			
			<div class="col-md-8">
				<h1>Cadastro / Edição de Usuário</h1>
				
				<form action="${pageContext.request.contextPath}/users/save" method="GET">
					
					<!-- Campo oculto passando o ID (essencial para definir se é SAVE ou UPDATE) -->
					<input type="hidden" 
						   id="user_id_id" 
						   name="user_id" 
						   value="${user.id}">
					
					<div class="mb-3">
						<label for="user_name_id" class="form-label">Nome</label>
						<input id="user_name_id" 
							   type="text" 
							   name="user_name" 
							   class="form-control" 
							   value="${user.name}">
					</div>
					
					<div class="mb-3">
						<label>Gênero</label>
						
						<div class="mb-1">
							<input id="user_gender_m_id" 
								   type="radio" 
								   name="user_gender" 
								   value="M" 
								   class="form-check-input" 
								   ${user.gender == 'M' ? 'checked' : ''}/>
							<label class="form-check-label" for="user_gender_m_id">Masculino</label>
						</div>
						
						<div class="mb-1">
							<input id="user_gender_f_id" 
								   type="radio" 
								   name="user_gender" 
								   value="F" 
								   class="form-check-input" 
								   ${user.gender == 'F' ? 'checked' : ''}/>
							<label class="form-check-label" for="user_gender_f_id">Feminino</label>
						</div>
					</div>
					
					<div class="mb-3">
						<label for="user_email_id" class="form-label">Email</label>
						<input id="user_email_id" 
							   type="email" 
							   name="user_email" 
							   class="form-control" 
							   value="${user.email}">
					</div>
					
					<div class="mb-3">
						<label for="user_pw_id" class="form-label">Senha</label>
						<input id="user_pw_id" 
							   type="password" 
							   name="user_pw" 
							   class="form-control" 
							   value="${user.getPassword()}">
					</div>
					
					<button type="submit" class="btn btn-primary">Salvar</button>
					<a href="${pageContext.request.contextPath}/users" class="btn btn-secondary">Cancelar</a>
				</form>
			</div>
			
			<div class="col-md-2"></div>
		</div>
	</div>
</body>
</html>