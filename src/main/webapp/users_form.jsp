<jsp:directive.page contentType="text/html; charset=UTF-8" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Gestão de Usuários</title>
	<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-icons.css">
</head>
<body>
	<div class="container">
		<div class="row mt-3">
			<div class="col-md-2"></div>
			
			<div class="col-md-8">
				<h1>Cadastro de Usuário</h1>
			
				<form action="/facebook/users/save" 
				      method="GET" 
				      class="">
				      
				      <input type="hidden" 
				             id="user_id_id" 
				             name="user_id"
				             value="${user.getId()}">
				      
				      <div class="mb-3">
				      	<label for="user_name_id" 
				      	       class="form-label">
				      	       Nome
				      	</label>
				      	<input id="user_name_id"
				      		   type="text" 
				      	       name="user_name"
				      	       class="form-control"
				      	       value="${user.getName()}">	
				      </div>
				      
				      <div class="mb-3">
				      	<label>
				      		Gênero
				      	</label>
				      	
				      	<div class="mb-1">
				      		<input id="user_gender_m_id"
				      			   value="M" 
				      		       type="radio" 
				      		       name="user_gender"
				      		       class="form-check-input"
				      		       ${user.getGender().equals("M") ? "checked" : ""} />
				      		<label class="form-check-label" 
				      	       for="user_gender_m_id">
				      	       Masculino
				      		</label>
				      	</div>
				      	
				      	<div class="mb-1">
				      		<input id="user_gender_f_id"
				      			   value="F"
				      		       type="radio" 
				      		       name="user_gender"
				      		       class="form-check-input"
				      		       ${user.getGender().equals("F") ? "checked" : "" } />
				      		<label class="form-check-label" 
				      	       for="user_gender_f_id">
				      	       Feminino
				      		</label>
				      	</div>
				      	
				      </div>
				      
				      <div class="mb-3">
				      	<label for="user_email_id" 
				      	       class="form-label">
				      	       Email
				      	</label>
				      	<input id="user_email_id"
				      		   type="email" 
				      	       name="user_email"
				      	       class="form-control"
				      	       value="${user.getEmail()}">
				      </div>
				      
				      <div class="mb-3">
				      	<label for="user_pw_id" 
				      	       class="form-label">
				      	       Senha
				      	</label>
				      	<input id="user_pw_id"
				      		   type="password" 
				      	       name="user_pw"
				      	       class="form-control">
				      </div>
					
					  <button type="submit" 
					          class="btn btn-primary">
					  	Cadastrar
					  </button>
				</form>
			</div>
			
			<div class="col-md-2"></div>
		</div>
	</div>
</body>
</html>