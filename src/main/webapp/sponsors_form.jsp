<jsp:directive.page contentType="text/html; charset=UTF-8" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Cadastrar Patrocinador - Facebook CRUD</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
</head>
<body>
	<div class="container mt-4">
		<h1>${sponsor != null ? 'Editar' : 'Novo'} Patrocinador</h1>

		<form action="${pageContext.request.contextPath}/sponsors/save" method="post" class="mt-3">
    <input type="hidden" name="sponsor_id" value="${sponsor.id != null ? sponsor.id : 0}">

    <div class="mb-3">
        <label for="sponsor_name" class="form-label">Nome do Patrocinador:</label>
        <input type="text" class="form-control" id="sponsor_name" name="sponsor_name" value="${sponsor.name}" required>
    </div>

    <div class="mb-3">
        <label for="sponsor_investment" class="form-label">Investimento (R$):</label>
        <input type="number" step="0.01" class="form-control" id="sponsor_investment" name="sponsor_investment" value="${sponsor.investment}" required>
    </div>

    <div class="mb-3">
        <label for="user_id" class="form-label">Usuário Responsável:</label>
        <select class="form-select" id="user_id" name="user_id" required>
            <option value="">Selecione um usuário...</option>
            <c:forEach var="u" items="${users_list}">
                <option value="${u.id}" ${sponsor.user.id == u.id ? 'selected' : ''}>${u.name}</option>
            </c:forEach>
        </select>
    </div>

    <button type="submit" class="btn btn-primary">Salvar</button>
    <a href="${pageContext.request.contextPath}/sponsors" class="btn btn-secondary">Cancelar</a>
</form>
	</div>
</body>
</html>