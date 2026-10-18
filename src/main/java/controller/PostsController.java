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
import model.dao.DAOFactory;
import model.dao.PostDAO;

@WebServlet(urlPatterns = {"/posts"})
public class PostsController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		//TODO: Log - apagar ao final
		String action = req.getRequestURI();
		System.out.println("URL: " + action);
		
		switch (action) {
		case "/facebook/posts": {
			loadPosts(req);
			
			RequestDispatcher rd = req.getRequestDispatcher("/posts.jsp");
			rd.forward(req, resp);
			break;
		}
		default:
			String message = String.format("URL não suportada: '%s'.", action);
			
			System.out.println(message);
			throw new IllegalArgumentException(message);
		}
	}
	
	private void loadPosts(HttpServletRequest req) {
		PostDAO dao = DAOFactory.createDAO(PostDAO.class);
		
		List<Post> posts = List.of();
		try {
			posts = dao.listAll();
		} catch (ModelException me) {
			// TODO Log no servidor
			me.printStackTrace();
		}
		
		req.setAttribute("posts_list", posts);
	}
}
