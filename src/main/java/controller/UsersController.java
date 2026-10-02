package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelException;
import model.User;
import model.dao.DAOFactory;
import model.dao.UserDAO;
import model.utils.PasswordEncryptor;

@WebServlet(urlPatterns = {"/users", "/users/save", "/users/update", "/users/delete"})
public class UsersController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        String action = req.getRequestURI();
        System.out.println("URL: " + action);
        
        RequestDispatcher rd = null;
        
        switch (action) {
            case "/facebook/users": {
                loadUsers(req);
                rd = req.getRequestDispatcher("/users.jsp");
                rd.forward(req, resp);
                break;
            }
            
            case "/facebook/users/save": {
                saveOrUpdateUser(req);
                resp.sendRedirect("/facebook/users");
                break;
            }
            
            case "/facebook/users/update": {
                loadUser(req);
                rd = req.getRequestDispatcher("/user_form.jsp");
                rd.forward(req, resp);
                break;
            }
            
            case "/facebook/users/delete": {
                deleteUser(req);
                resp.sendRedirect("/facebook/users");
                break;
            }
                
            default:
                String message = String.format("URL não suportada: '%s'.", action);
                System.out.println(message);
                throw new IllegalArgumentException(message);
        }
    }
    
    private void deleteUser(HttpServletRequest req) {
        String userIdStr = req.getParameter("userId"); // Espera userId (com I maiúsculo)
        
        if (userIdStr != null && !userIdStr.isBlank()) {
            int userId = Integer.parseInt(userIdStr);
            User userToBeDeleted = new User(userId);
            UserDAO dao = DAOFactory.createDAO(UserDAO.class);
            
            try {
                dao.delete(userToBeDeleted);
            } catch (ModelException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void loadUser(HttpServletRequest req) {
        String userIdStr = req.getParameter("userId"); // Espera userId (com I maiúsculo)
        
        if (userIdStr != null && !userIdStr.isBlank()) {
            int userId = Integer.parseInt(userIdStr);
            UserDAO dao = DAOFactory.createDAO(UserDAO.class);
            User userToBeUpdated = null;
            
            try { 
                userToBeUpdated = dao.findById(userId);
            } catch (ModelException e) {
                e.printStackTrace();
            }
            
            req.setAttribute("user", userToBeUpdated);
        }
    }
    
    private void saveOrUpdateUser(HttpServletRequest req) {
        String userIdStr = req.getParameter("user_id"); // Espera user_id (com underline)
        boolean isUpdate = false;
        
        User user = null;
        if (userIdStr == null || userIdStr.isBlank()) {
            user = new User();
        } else {
            user = new User(Integer.parseInt(userIdStr));
            isUpdate = true;
        }
        
        String userName = req.getParameter("user_name");
        String userGender = req.getParameter("user_gender");
        String userEmail = req.getParameter("user_email");
        String userPW = req.getParameter("user_pw");
        
        user.setName(userName);
        user.setGender(userGender);
        user.setEmail(userEmail);
        
        String hashPW = PasswordEncryptor.hashPassword(userPW);
        user.setPassword(hashPW);
        
        UserDAO dao = DAOFactory.createDAO(UserDAO.class);
        
        try {
            if (isUpdate) {
                dao.update(user);
            } else {
                dao.save(user);
            }
        } catch (ModelException e) {
            e.printStackTrace();
        }
    }

    private void loadUsers(HttpServletRequest req) {
        UserDAO dao = DAOFactory.createDAO(UserDAO.class);
        List<User> users = List.of();
        
        try {
            users = dao.listAll();
        } catch (ModelException me) {
            me.printStackTrace();
        }
        req.setAttribute("users_list", users);
    }
}