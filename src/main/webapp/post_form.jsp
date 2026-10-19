<jsp:directive.page contentType="text/html; charset=UTF-8" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Cadastro de Post - Facebook CRUD</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-icons.css">
</head>
<body>
	<div class="container">
		<div class="row mt-3">
			<div class="col-md-2"></div>
			
			<div class="col-md-8">
				<h1>Cadastro / Edição de Post</h1>
				
				<form action="${pageContext.request.contextPath}/post/save" method="POST">
					<!-- Campo oculto para o ID do Post -->
					<input type="hidden"
								id="post_id_id"
								name="post_id"
								value="${post.id}">
					<!-- Select para escolher o Autor -->
					<div class="mb-3">
						<label for="post_user_id" class="form-label">Autor (Usuário)</label>
						<select id="post_user_id" name="user_id" class="form-select" required>
							<option value="">Selecione um Usuário</option>
							<c:forEach var="u" items="${users_list}">
								<option value="${u.id}" ${post.user.id == u.id ? 'selected' : ''}>
									${u.name} (${u.email})
								</option>
							</c:forEach>
						</select>
					</div>
					
					<!-- Conteúdo da Publicação -->
					<div class="mb-3">
						<label for="post_content_id" class="form-label">Conteúdo do Post</label>
						<textarea id="post_content_id"
									name="content"
									class="form-control"
									rows="4"
									required>${post.content}</textarea>
					</div>
					
					<button type="submit" class="btn btn-primary">Salvar</button>	
					<a href="${pageContext.request.contextPath}/post" class="btn btn-secondary">Cancelar</a>
				</form>
			</div>
			
			<div class="col-md-2"></div>
		</div>
	
	
</body>
</html>