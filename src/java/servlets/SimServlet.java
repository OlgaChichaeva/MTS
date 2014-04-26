/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.PhoneNumberDAO;
import dao.SimDao;
import dao.TrafficDao;
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
import objects.Tariff;
import objects.Traffic;
import objects.User;
import pack.DaoMaster;
import pack.HTMLHelper;
import static pack.PathConstants.*;
import static pack.LogManager.LOG;
import pack.MessageBean;
import pack.ServletHelper;
import security.SecurityBean;

/**
 *
 * @author Ivan
 */
@WebServlet(name = "SimServlet", loadOnStartup = 1, urlPatterns = {
    CHOOSE_SIM,
    CHANGE_TARIFF,
    SHOW_TRAFFIC
})
public class SimServlet extends HttpServlet {
    
    private final SimDao simDao = DaoMaster.getSimDao();
    private final PhoneNumberDAO phoneNumberDao = DaoMaster.getPhoneNumberDao();
    private final TrafficDao trafficDao = DaoMaster.getTrafficDao();
    
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
        User user = ServletHelper.getUser(request);
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
        request.getRequestDispatcher("/WEB-INF/sim/chooseSim.jsp").forward(request, response);
    }
    
    /**
     * Меняет тариф на сим-карте. Затем перенаправляет на страницу с тарифом.
     * @param request
     * @param response 
     */
    private void changeTariff(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int tariffId = Integer.parseInt(request.getParameter("ID_tariff"));
        int simId = Integer.parseInt(request.getParameter("sim_id"));
        Sim sim = simDao.getIdSim(simId);
        Tariff tariff = new Tariff();
        tariff.setIdTariff(tariffId);
        sim.setTariff(tariff);
        simDao.update(sim);
        request.getSession(true).setAttribute(MessageBean.ATTR_NAME, new MessageBean("Тариф успешно изменён."));
        response.sendRedirect(request.getContextPath() + SHOW_TARIFF + "?ID_tariff=" + tariffId);
    }
    
    /**
     * Перенаправляет на страницу трафика для сим-карты, полученной из запроса.
     * @param request
     * @param response 
     */
    private void showTraffic(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int simId = Integer.parseInt(request.getParameter("sim_id"));
        List<Traffic> trafficList = trafficDao.getBySimId(simId);
        request.setAttribute("trafficList", trafficList);
        request.getRequestDispatcher("/WEB-INF/sim/showTraffic.jsp").forward(request, response);
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
            case CHANGE_TARIFF: {
                changeTariff(request, response);
                break;
            }
            case SHOW_TRAFFIC: {
                showTraffic(request, response);
                break;
            }
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
