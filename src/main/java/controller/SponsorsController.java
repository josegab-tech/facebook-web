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
import model.Sponsor;
import model.dao.DAOFactory;
import model.dao.SponsorDAO;

@WebServlet(urlPatterns = {"/sponsors"})
public class SponsorsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {

        String action = req.getRequestURI();
        System.out.println("URL requisitada: " + action);

        
        if ("/facebook/sponsors".equals(action)) {
            loadSponsors(req);
            
            
            RequestDispatcher rd = req.getRequestDispatcher("/sponsors.jsp");
            rd.forward(req, resp);
        }
    }

    private void loadSponsors(HttpServletRequest req) {
       
        SponsorDAO dao = DAOFactory.createDAO(SponsorDAO.class);

        List<Sponsor> sponsors = List.of();
        try {
            
            sponsors = dao.listAll();
        } catch (ModelException me) {
            me.printStackTrace();
        }

        
        req.setAttribute("sponsors_list", sponsors);
    }
}