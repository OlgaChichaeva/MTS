/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.ClientDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import objects.Client;
import objects.User;
import pack.DaoMaster;

/**
 *
 * @author Ivan
 */
@WebServlet(name = "ClientServlet", loadOnStartup = 1, urlPatterns = {"/clientHome/"})
public class ClientServlet extends HttpServlet {

    private ClientDAO clientDao = DaoMaster.getClientDao();

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath = request.getServletPath();
        switch (userPath) {
            case "/clientHome/" : {
                clientHome(request, response);
                break;
            }
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //
    }

    /**
     * Загружает информацию о клиенте, связанном с данным юзером,
     * и перенаправляет на домашнюю для клиента страницу.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void clientHome(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            // что-нибудь сделать
            return;
        }
        Client client = clientDao.getClientByID(user.getIdClient());
        if (client == null) {
            // что-нибудь сделать
            return;
        }
        session.setAttribute("currentClient", client);
        request.getRequestDispatcher("/WEB-INF/clientHome.jsp").forward(request, response);
    }
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
