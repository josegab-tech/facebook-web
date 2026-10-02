<jsp:directive.page contentType="text/html; charset=UTF-8" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Facebook CRUD</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-icons.css">
  </head>
  <body>
	<div class="d-flex flex-column min-vh-100 bg-light">
		<!-- Navbar -->
	    <nav class="navbar navbar-dark bg-dark">
	      <div class="container">
	        <span class="navbar-brand mb-0 h1">Facebook CRUD</span>
	        <a href="/facebook/logout" class="btn btn-warning">
		    	<i class="bi bi-door-closed"></i> Logout
		  	</a>
	      </div>
	    </nav>
	
	    <!-- Conteúdo principal -->
	    <main class="flex-grow-1 d-flex flex-column justify-content-center align-items-center text-center">
	      <h1 class="my-4">Bem-vindo ao Facebook CRUD</h1>
	      <div class="d-flex gap-3 flex-wrap justify-content-center">
	        <a href="/facebook/users" class="btn btn-secondary btn-lg">Usuários</a>
	        <a href="/facebook/posts"  class="btn btn-secondary btn-lg">Posts</a>
	      </div>
	    </main>
	
	    <!-- Rodapé -->
	    <footer class="bg-dark text-white text-center py-3 mt-auto">
	      <div class="container">
	        <p class="mb-0">© 2025 Facebook CRUD. Todos os direitos reservados.</p>
	      </div>
	    </footer>
	</div>
	
  </body>
</html>