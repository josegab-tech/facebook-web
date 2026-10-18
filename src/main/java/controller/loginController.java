package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ModelException;
import model.User;
import model.dao.DAOFactory;
import model.dao.UserDAO;
import model.utils.PasswordEncryptor;

@WebServlet(urlPatterns = {"/login", "/logout"})
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        String contextPath = req.getContextPath();
        String action = req.getServletPath();

        // Se for logout, invalida a sessão
        if ("/logout".equals(action)) {
            HttpSession session = req.getSession(false);
            if (session != null) {
                session.invalidate();
            }
        }

        // Redireciona de forma dinâmica para o formulário de login
        resp.sendRedirect(contextPath + "/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {

        String contextPath = req.getContextPath();
        String userEmail = req.getParameter("user_login");
        String userPW = req.getParameter("user_pw");

        // 1. Validação de campos nulos ou em branco + RETURN obrigatório
        if (userEmail == null || userPW == null || userEmail.isBlank() || userPW.isBlank()) {
            resp.sendRedirect(contextPath + "/login.jsp?erro=true");
            return; // Impede que o restante do código continue executando
        }

        // 2. Busca do usuário no BD
        UserDAO dao = DAOFactory.createDAO(UserDAO.class);
        User userToLogin = null;
        
        try {
            userToLogin = dao.findByEmail(userEmail);
        } catch (ModelException e) {
            e.printStackTrace();
        }

        if (userToLogin == null) {
            resp.sendRedirect(contextPath + "/login.jsp?erro=true");
            return;
        }

        // 3. Verificação de senha e criação da sessão
        if (PasswordEncryptor.checkPassword(userPW, userToLogin.getPasswordHash())) {
            // Corrigido "usuer_logged" -> "user_logged"
            req.getSession().setAttribute("user_logged", userToLogin);
            resp.sendRedirect(contextPath + "/");
        } else {
            resp.sendRedirect(contextPath + "/login.jsp?erro=true");
        }
    }
}