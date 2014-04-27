/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.ClientContrDAO;
import dao.ServiceInSimDAO;
import dao.ServiceInTariffDao;
import dao.SimDao;
import dao.TrafficDao;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import objects.ClientContr;
import objects.PhoneNumber;
import objects.ServiceInSim;
import objects.ServiceInTariff;
import objects.Sim;
import objects.Tariff;
import objects.Traffic;
import objects.User;
import pack.DaoMaster;
import static pack.PathConstants.*;
import static pack.LogManager.LOG;
import pack.MessageBean;
import security.SecurityBean;

/**
 *
 * @author Ivan
 */
@WebServlet(name = "SimServlet", loadOnStartup = 1, urlPatterns = {
    CHOOSE_SIM,
    CHANGE_TARIFF,
    SHOW_TRAFFIC,
    ADD_TRAFFIC_FORM,
    ADD_TRAFFIC
})
public class SimServlet extends HttpServlet {
    
    private final SimDao simDao = DaoMaster.getSimDao();
    private final ClientContrDAO clientContrDao = DaoMaster.getClientContrDao();
    private final TrafficDao trafficDao = DaoMaster.getTrafficDao();
    private final ServiceInTariffDao servInTarDao = DaoMaster.getServiceInTariffDao();
    private final ServiceInSimDAO servInSimDao = DaoMaster.getServiceInSimDao();
    
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
        if (LOG.isDebugEnabled()) {
            try {
                LOG.debug("Показ сим-карты. ID_user = " + user.getIdUser() + ", ID_role = " + user.getIdRole());
            } catch (NullPointerException ex) {
                LOG.debug("Показ сим-карты. Юзер отсутствует в сессии.");
            }
        }
        SecurityBean.checkAccept(user, SecurityBean.CLIENT, SecurityBean.LEGAL_ENTITY);
        Map<ClientContr, PhoneNumber> phonesMap = null;
        switch (user.getIdRole()) {
            case SecurityBean.CLIENT: {
                phonesMap = clientContrDao.getContrsAndNumsByClientID(user.getIdClient());
                break;
            }
            case SecurityBean.ADMIN: {
                phonesMap = clientContrDao.getAllContrsAndNums();
                break;
            }
        }
        request.setAttribute("phonesMap", phonesMap); // Кладём список всех контрактов в запрос.
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
     * Если доступ невозможен, не пускает на страницу.
     * @param request
     * @param response 
     */
    private void showTraffic(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int simId = Integer.parseInt(request.getParameter("sim_id"));
        if (!SecurityBean.isUserAcceptedForSim(ServletHelper.getUser(request), simId)) {
            SecurityBean.denyAccess();
            return;
        } 
        List<Traffic> trafficList = trafficDao.getBySimId(simId);
        request.setAttribute("trafficList", trafficList);
        request.getRequestDispatcher("/WEB-INF/sim/showTraffic.jsp").forward(request, response);
    }
    
    /**
     * Перенаправляет на страницу добавления трафика.
     * @param request
     * @param response 
     */
    private void addTrafficForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SecurityBean.checkAccept(ServletHelper.getUser(request));
        int idSim = Integer.parseInt(request.getParameter("sim_id"));
        Sim sim = simDao.getIdSim(idSim);
        List<ServiceInTariff> sitList = servInTarDao.getIdTariff(sim.getTariffId());
        List<ServiceInSim> sisList = servInSimDao.getIdSim(idSim);
        request.setAttribute("serviceInTariffList", sitList);
        request.setAttribute("serviceInSimList", sisList);
        request.getRequestDispatcher("/WEB-INF/sim/addTraffic.jsp").forward(request, response);
    }
    
    /**
     * Добавляет трафик к выбранной сим-карте
     * @param request
     * @param response 
     */
    private void addTraffic(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        SecurityBean.checkAccept(ServletHelper.getUser(request));
        if (LOG.isDebugEnabled()) {
            LOG.debug("Добавление трафика. sim_id = " + request.getParameter("sim_id")
                    + ", ID_service = " + request.getParameter("ID_service")
                    + ", amount = " + request.getParameter("amount") + ".");
        }
        int idSim = Integer.parseInt(request.getParameter("sim_id"));
        int idService = Integer.parseInt(request.getParameter("ID_service"));
        double amount = Double.parseDouble(request.getParameter("amount"));
        Traffic traffic = new Traffic();
        traffic.setIdSim(idSim);
        traffic.setIdService(idService);
        traffic.setAmount(amount);
        traffic.setDate(new Date(System.currentTimeMillis()));
        trafficDao.insert(traffic);
        request.getSession(true).setAttribute(MessageBean.ATTR_NAME, new MessageBean("Трафик добавлен."));
        response.sendRedirect(request.getContextPath() + CHOOSE_SIM + "?forTraffic=true");
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
            case ADD_TRAFFIC_FORM: {
                addTrafficForm(request, response);
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
            case ADD_TRAFFIC: {
                addTraffic(request, response);
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
