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
import model.User;
import model.dao.DAOFactory;
import model.dao.SponsorDAO;
import model.dao.UserDAO;

@WebServlet(urlPatterns = {"/sponsors", 
                           "/sponsors/form", 
                           "/sponsors/save", 
                           "/sponsors/update", 
                           "/sponsors/delete"})
public class SponsorsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {

        String action = req.getRequestURI();
        System.out.println("URL (GET): " + action);

        switch (action) {
        case "/facebook/sponsors": {
            loadSponsorsList(req); // Carrega a LISTA completa
            RequestDispatcher rd = req.getRequestDispatcher("/sponsors.jsp");
            rd.forward(req, resp);
            break;
        }
        case "/facebook/sponsors/form": {
            showForm(req);
            RequestDispatcher rd = req.getRequestDispatcher("/sponsors_form.jsp");
            rd.forward(req, resp);
            break;
        }
        case "/facebook/sponsors/update": {
            loadSingleSponsor(req); // Carrega UM patrocinador individual
            RequestDispatcher rd = req.getRequestDispatcher("/sponsors_form.jsp");
            rd.forward(req, resp);
            break;
        }
        case "/facebook/sponsors/delete": {
            deleteSponsor(req);
            resp.sendRedirect("/facebook/sponsors");
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

        if ("/facebook/sponsors/save".equals(action)) {
            saveOrUpdateSponsor(req);
            resp.sendRedirect("/facebook/sponsors");
        } else {
            resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }
    }

    private void deleteSponsor(HttpServletRequest req) {
        String sponsorIdStr = req.getParameter("sponsorId");
        if (sponsorIdStr != null && !sponsorIdStr.isBlank()) {
            int sponsorId = Integer.parseInt(sponsorIdStr);
            
            Sponsor sponsorToBeDeleted = new Sponsor();
            sponsorToBeDeleted.setId(sponsorId);

            SponsorDAO dao = DAOFactory.createDAO(SponsorDAO.class);
            try {
                dao.delete(sponsorToBeDeleted);
            } catch (ModelException e) {
                e.printStackTrace();
            }
        }
    }


    private void loadSingleSponsor(HttpServletRequest req) {
        String sponsorIdStr = req.getParameter("sponsorId");
        if (sponsorIdStr != null && !sponsorIdStr.isBlank()) {
            int sponsorId = Integer.parseInt(sponsorIdStr);

            SponsorDAO sponsorDao = DAOFactory.createDAO(SponsorDAO.class);
            UserDAO userDao = DAOFactory.createDAO(UserDAO.class);

            try {
                Sponsor sponsorToBeUpdated = sponsorDao.findById(sponsorId);
                List<User> users = userDao.listAll();

                req.setAttribute("sponsor", sponsorToBeUpdated);
                req.setAttribute("users_list", users);
            } catch (ModelException e) {
                e.printStackTrace();
            }
        }
    }

    private void showForm(HttpServletRequest req) {
        UserDAO userDao = DAOFactory.createDAO(UserDAO.class);
        List<User> users = List.of();

        try {
            users = userDao.listAll();
        } catch (ModelException e) {
            e.printStackTrace();
        }

        req.setAttribute("users_list", users);
    }

    private void saveOrUpdateSponsor(HttpServletRequest req) {
        String sponsorIdStr = req.getParameter("sponsor_id");
        boolean isUpdate = false;

        Sponsor sponsor = new Sponsor();
        if (sponsorIdStr != null && !sponsorIdStr.isBlank() && !sponsorIdStr.equals("0")) {
            sponsor.setId(Integer.parseInt(sponsorIdStr));
            isUpdate = true;
        }

        String name = req.getParameter("sponsor_name");
        String investmentStr = req.getParameter("sponsor_investment");
        String userIdStr = req.getParameter("user_id");

        if (userIdStr != null && !userIdStr.isBlank()) {
            int userId = Integer.parseInt(userIdStr);
            double investment = Double.parseDouble(investmentStr);
            
            User user = new User(userId);

            sponsor.setName(name);
            sponsor.setInvestment(investment);
            sponsor.setUser(user);

            SponsorDAO dao = DAOFactory.createDAO(SponsorDAO.class);

            try {
                if (isUpdate) {
                    dao.update(sponsor);
                } else {
                    dao.save(sponsor);
                }
            } catch (ModelException e) {
                e.printStackTrace();
            }
        }
    }

   
    private void loadSponsorsList(HttpServletRequest req) {
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