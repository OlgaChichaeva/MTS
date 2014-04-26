/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.PhoneNumberDAO;
import dao.SimDao;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import objects.PhoneNumber;
import objects.Sim;
import objects.User;
import pack.DaoMaster;
import pack.HTMLHelper;
import static pack.PathConstants.*;
import static pack.LogManager.LOG;
import security.SecurityBean;

/**
 *
 * @author Ivan
 */
@WebServlet(name = "SimServlet", loadOnStartup = 1, urlPatterns = {
    CHOOSE_SIM
})
public class SimServlet extends HttpServlet {
    
    private final SimDao simDao = DaoMaster.getSimDao();
    private final PhoneNumberDAO phoneNumberDao = DaoMaster.getPhoneNumberDao();
    
    /**
     * Загружает сим-карты и телефоны, загружает их в Map и перенаправляет на
     * страницу выбора сим-карт.
     *
     * @param request берём из методов doGet/doPost
     * @param response берём из методов doGet/doPost
     * @throws ServletException
     * @throws IOException
     */
    private void chooseSim(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = HTMLHelper.getUser(request);
        SecurityBean.checkAccept(user, SecurityBean.CLIENT, SecurityBean.LEGAL_ENTITY);
        List<Sim> simList = null;
        Map<Sim, PhoneNumber> simAndNumbers = null;
        if (user.getIdRole() == SecurityBean.CLIENT) {
            simList = simDao.getSimListByClientID(user.getIdClient());
        }
        if (simList != null) {
            simAndNumbers = new HashMap<>();
            for (Sim sim : simList) {
                simAndNumbers.put(sim, phoneNumberDao.getNumberBySimID(sim.getSimId()));
            }
        }
        request.setAttribute("simAndNumbers", simAndNumbers); // Кладём список всех контрактов в запрос.
        request.getRequestDispatcher("/WEB-INF/showService/chooseSim.jsp").forward(request, response);
    }

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
        response.setContentType("text/html;charset=UTF-8");
        switch(userPath) {
            case CHOOSE_SIM: {
                chooseSim(request, response);
                break;
            }
            default: {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("Действия для пути [" + userPath + "] не определены, либо ожидается POST.");
                }
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
        String userPath = request.getServletPath();
        response.setContentType("text/html;charset=UTF-8");
        switch(userPath) {
            default: {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("Действия для пути [" + userPath + "] не определены.");
                }
                break;
            }
        }
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
