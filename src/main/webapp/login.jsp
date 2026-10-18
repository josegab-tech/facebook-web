<jsp:directive.page contentType="text/html; charset=UTF-8" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Login - Facebook CRUD</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
    
    <style>
	    .hidden {
	      display: none;
	    }
  	</style>
  </head>
  <body>
    <div class="d-flex flex-column min-vh-100 bg-light">
      <!-- Navbar -->
      <nav class="navbar navbar-dark bg-dark">
        <div class="container">
          <span class="navbar-brand mb-0 h1">Facebook CRUD</span>
        </div>
      </nav>

      <main class="flex-grow-1 d-flex justify-content-center align-items-center">
        
        <div class="card shadow-sm p-4" style="width: 100%; max-width: 400px;">
          
          <h2 class="text-center mb-4">Login</h2>
          
          <form action="/facebook/login" method="POST">
            <div class="mb-3">
              <label for="user_login_id" class="form-label">Login (e-mail)</label>
              <input type="text" class="form-control" id="user_login_id" name="user_login" required />
            </div>
            
            <div class="mb-3">
              <label for="user_pw_id" class="form-label">Senha</label>
              <input type="password" class="form-control" id="user_pw_id" name="user_pw" required />
            </div>
            
            <div class="d-grid mb-3">
              <button type="submit" class="btn btn-dark btn-lg">Logar</button>
            </div>
            
            <c:if test="${param.erro == 'true'}">
			    <span class="text-danger small">Usuário ou senha inválidos.</span>
			</c:if>
          </form>
        </div>
      </main>

      <footer class="bg-dark text-white text-center py-3 mt-auto">
        <div class="container">
          <jsp:useBean id="date" class="java.util.Date" />
		  <fmt:formatDate value="${date}" pattern="yyyy" var="currentYear" />
          <p class="mb-0">©<c:out value="${currentYear}" /> Facebook CRUD. Todos os direitos reservados.</p>
        </div>
      </footer>
    </div>
  </body>
</html>