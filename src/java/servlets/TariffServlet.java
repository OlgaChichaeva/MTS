/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.ClientContrDAO;
import dao.ServiceInSimDAO;
import dao.ServiceInTariffDao;
import dao.TariffDao;
import filters.TariffFilter;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import objects.ClientContr;
import objects.ServiceInSim;
import objects.ServiceInTariff;
import objects.Tariff;
import objects.User;
import pack.DaoMaster;
import security.SecurityBean;

/**
 *
 * @author Ivan
 */
@WebServlet(name = "TariffServlet", loadOnStartup = 1, urlPatterns = {
    "/SelectAllTariff/",
    "/TariffFilter/",
    "/ShowTariff/",
    "/RemoveServiceFromTariff/"
})
public class TariffServlet extends HttpServlet {

    private final TariffDao tariffDao = DaoMaster.getTariffDao();
    private final ServiceInTariffDao servInTarDao = DaoMaster.getServiceInTariffDao();
    private final ClientContrDAO clientContrDao = DaoMaster.getClientContrDao();
    private final ServiceInSimDAO sisDao = DaoMaster.getServiceInSimDao();
    
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
            case "/SelectAllTariff/": {
                selectAllTariff(request, response);
                break;
            }
            case "/TariffFilter/": {
                tariffFilter(request, response);
                break;
            }
            case "/ShowTariff/": {
                showTariff(request, response);
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
        switch (userPath) {
            case "/RemoveServiceFromTariff/": {
                removeServiceFromTariff(request, response);
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
    
    private void selectAllTariff(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Tariff> tariffList = tariffDao.getAllTariffList();
        goToSelect(tariffList, request, response);
    }
    
    /**
     * Перенаправляет на страницу показа списка тарифов.
     *
     * @param tariffList список, который нужно отобразить
     * @param request берём из методов doGet/doPost
     * @param response берём из методов doGet/doPost
     * @throws ServletException
     * @throws IOException
     */
    private void goToSelect(List<Tariff> tariffList, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("TariffList", tariffList);
        request.getRequestDispatcher("/WEB-INF/tariff/showTariffList.jsp").forward(request, response);
    }

    
    private void removeServiceFromTariff(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idService = Integer.parseInt(request.getParameter("ID_service"));
        int idTariff = Integer.parseInt(request.getParameter("ID_tariff"));
        ServiceInTariff sInT = new ServiceInTariff();
        sInT.setIdService(idService);
        sInT.setIdTariff(idTariff);
        servInTarDao.deleteConcreteServiceInTariff(sInT);
        response.sendRedirect(request.getContextPath() + "/ShowTariff/?ID_tariff="+idTariff);
    }
    
    /**
     * Ищет тарифы согласно критерию фильтра и перенаправляет на страинцу
     * вывода тарифов.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void tariffFilter(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        TariffFilter filter = new TariffFilter();
        String nameTariff = request.getParameter("name_tariff");
        String description = request.getParameter("description");
        filter.setNameTariff(nameTariff);
        filter.setDescription(description);

        List<Tariff> tariffList = tariffDao.getFilteredTariffList(filter);
        goToSelect(tariffList, request, response);
    }

    private void showTariff(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int idTariff = Integer.parseInt(request.getParameter("ID_tariff"));
        List<ServiceInTariff> servInTarList = servInTarDao.getIdTariff(idTariff);
        request.setAttribute("servInTarList", servInTarList);
        String stringSimId = request.getParameter("sim_id");
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("currentUser");
       
        List<ServiceInSim> sisList = null; // Список услуг, подключенных к сим-карте
        
        // Проверяем, имеет ли юзер право смотреть услуги для этой сим-карты
        if (stringSimId != null && user != null) {
            boolean accept = false;
            int simID = Integer.parseInt(stringSimId);
            switch (user.getIdRole()) {
                case SecurityBean.CLIENT : {
                    // Проверяем, есть ли среди договоров клиента договор на эту сим-карту.
                    int clientID = user.getIdClient();
                    for (ClientContr contr : clientContrDao.getContrsByClientID(clientID)) {
                        if (contr.getSimID()==simID) {
                            accept = true;
                            break;
                        }
                    }
                    break;
                }
                case SecurityBean.LEGAL_ENTITY : {
                    break;
                }
                case SecurityBean.ADMIN : {
                    accept = true;
                    break;
                }
            }
            if (accept) {
                sisList = sisDao.getIdSim(simID);
            } 
        }
        request.setAttribute("sisList", sisList);
        
        request.getRequestDispatcher("/WEB-INF/tariff/showTariff.jsp").forward(request, response);
    }
}
