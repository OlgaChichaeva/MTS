/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.ClientContrDAO;
import dao.DaoException;
import dao.PhoneNumberDAO;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import objects.Client;
import objects.ClientContr;
import objects.PhoneNumber;
import objects.User;
import pack.DaoMaster;
import static pack.PathConstants.*;
import static pack.LogManager.LOG;
import security.SecurityBean;

/**
 *
 * @author Ivan
 */
@WebServlet(name = "ClientServlet", loadOnStartup = 1, urlPatterns = {
    CLIENT_HOME,})
public class ClientServlet extends HttpServlet {

    private final ClientContrDAO clientContrDao = DaoMaster.getClientContrDao();
    private final PhoneNumberDAO phoneNumberDao = DaoMaster.getPhoneNumberDao();

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
            case CLIENT_HOME: {
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
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    /**
     * Загружает информацию о клиенте, связанном с данным юзером, и
     * перенаправляет на домашнюю для клиента страницу.
     *
     * @param request берём из методов doGet/doPost
     * @param response берём из методов doGet/doPost
     * @throws ServletException
     * @throws IOException
     */
    private void clientHome(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Юзера нет в сессии.");
            }
            SecurityBean.denyAccess();
            return;
        } else if (user.getClient() == null) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Юзер не является клиентом.");
            }
            SecurityBean.denyAccess();
            return;
        }
        Map<ClientContr, PhoneNumber> phonesMap = new HashMap<>();
        try {
            List<ClientContr> contrs = clientContrDao.getContrsByClientID(user.getIdClient());
            if (!contrs.isEmpty()) {

                // Можем взять клиента из первого элмента, так как проверили, что список не пустой.
                Client client = contrs.get(0).getClient();
                session.setAttribute("currentClient", client);

                // Соотносим телефонные номера с договорами.
                for (ClientContr contr : contrs) {
                    phonesMap.put(contr, phoneNumberDao.getNumberBySimID(contr.getSimID()));
                }

            } else {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("У клиента №" + user.getIdClient() + " нет договоров.");
                }
            }
        } catch (DaoException ex) {
            LOG.error("Ошибка загрузки списка договров клиента.", ex);
            throw ex;
        }
        request.setAttribute("phonesMap", phonesMap); // Кладём список всех контрактов в запрос.
        request.getRequestDispatcher("/WEB-INF/clientHome.jsp").forward(request, response);
    }
}
