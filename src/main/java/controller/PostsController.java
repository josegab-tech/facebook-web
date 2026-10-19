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
import model.Post;
import model.User;
import model.dao.DAOFactory;
import model.dao.PostDAO;
import model.dao.UserDAO;

@WebServlet(urlPatterns = {"/posts", 
                           "/posts/form", 
                           "/posts/save", 
                           "/posts/update", 
                           "/posts/delete"})
public class PostsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {

        // Log no console para depuração
        String action = req.getRequestURI();
        System.out.println("URL: " + action);

        switch (action) {
        case "/facebook/posts": {
            loadPosts(req);
            
            RequestDispatcher rd = req.getRequestDispatcher("/posts.jsp");
            rd.forward(req, resp);
            break;
        }
        case "/facebook/posts/form": {
            showForm(req);
            
            RequestDispatcher rd = req.getRequestDispatcher("/posts_form.jsp");
            rd.forward(req, resp);
            break;
        }
        case "/facebook/posts/save": {
            saveOrUpdatePost(req);
            
            resp.sendRedirect("/facebook/posts");
            break;
        }
        case "/facebook/posts/update": {
            loadPost(req);
            
            RequestDispatcher rd = req.getRequestDispatcher("/posts_form.jsp");
            rd.forward(req, resp);
            break;
        }
        case "/facebook/posts/delete": {
            deletePost(req);
            
            resp.sendRedirect("/facebook/posts");
            break;
        }
        default:
            String message = String.format("URL não suportada: '%s'.", action);
            System.out.println(message);
            throw new IllegalArgumentException(message);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        String action = req.getRequestURI();
        System.out.println("URL (POST): " + action);

        if ("/facebook/posts/save".equals(action)) {
            saveOrUpdatePost(req);
            resp.sendRedirect("/facebook/posts");
        } else {
            resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }
    }

    private void deletePost(HttpServletRequest req) {
        String postIdStr = req.getParameter("postId");
        int postId = Integer.parseInt(postIdStr);
        
        Post postToBeDeleted = new Post();
        postToBeDeleted.setId(postId);

        PostDAO dao = DAOFactory.createDAO(PostDAO.class);
        try {
            dao.delete(postToBeDeleted);
        } catch (ModelException e) {
            e.printStackTrace();
        }
    }

    private void loadPost(HttpServletRequest req) {
        String postIdStr = req.getParameter("postId");
        int postId = Integer.parseInt(postIdStr);

        PostDAO postDao = DAOFactory.createDAO(PostDAO.class);
        UserDAO userDao = DAOFactory.createDAO(UserDAO.class);

        Post postToBeUpdated = null;
        List<User> users = List.of();

        try {
            postToBeUpdated = postDao.findById(postId);
            users = userDao.listAll(); // Carrega os usuários para o <select>
        } catch (ModelException e) {
            e.printStackTrace();
        }

        req.setAttribute("post", postToBeUpdated);
        req.setAttribute("users_list", users);
    }

    private void showForm(HttpServletRequest req) {
        UserDAO userDao = DAOFactory.createDAO(UserDAO.class);
        List<User> users = List.of();

        try {
            users = userDao.listAll(); // Busca a lista de usuários para alimentar o <select>
        } catch (ModelException e) {
            e.printStackTrace();
        }

        req.setAttribute("users_list", users);
    }

    private void saveOrUpdatePost(HttpServletRequest req) {
        String postIdStr = req.getParameter("post_id");
        boolean isUpdate = false;

        Post post = new Post();
        if (postIdStr != null && !postIdStr.isBlank() && !postIdStr.equals("0")) {
            post.setId(Integer.parseInt(postIdStr));
            isUpdate = true;
        }

        // Recupera os campos enviados pelo formulário
        String content = req.getParameter("content");
        String userIdStr = req.getParameter("user_id");

        int userId = Integer.parseInt(userIdStr);
        User user = new User(userId);

        post.setContent(content);
        post.setUser(user);

        PostDAO dao = DAOFactory.createDAO(PostDAO.class);

        try {
            if (isUpdate) {
                dao.update(post);
            } else {
                dao.save(post);
            }
        } catch (ModelException e) {
            e.printStackTrace();
        }
    }

    private void loadPosts(HttpServletRequest req) {
        PostDAO dao = DAOFactory.createDAO(PostDAO.class);

        List<Post> posts = List.of();
        try {
            posts = dao.listAll();
        } catch (ModelException me) {
            me.printStackTrace();
        }

        req.setAttribute("posts_list", posts);
    }
}