/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.ClientDAO;
import dao.DaoException;
import dao.UserDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import objects.User;
import pack.DaoMaster;
import security.SecurityBean;
import static pack.PathConstants.*;
import static pack.LogManager.LOG;

/**
 *
 * @author Ivan
 */
@WebServlet(name = "LoginServlet", loadOnStartup = 1, urlPatterns = {
    LOGIN,
    LOGOUT
})
public class LoginServlet extends HttpServlet {

    private UserDAO userDao = DaoMaster.getUserDao();
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
            case LOGOUT: {
                logout(request, response);
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
            case LOGIN: {
                login(request, response);
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

    /**
     * Попытка авторизации пользователя. В случае успеха перенаправляет на
     * домашнюю страницу (специфична для типа пользователя). Что делать в случае
     * неудачи - нужно определиться.
     *
     * @param request берём из методов doGet/doPost
     * @param response берём из методов doGet/doPost
     * @throws ServletException
     * @throws IOException
     */
    private void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user;
        try {
            user = userDao.getUserByUserName(username);
        } catch (DaoException ex) {
            LOG.error("Ошибка заагрузки пользователя.", ex);
            throw ex;
        }
        if (user == null || !user.getUserPassword().equals(password)) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Имя пользователя или пароль неверны.");
                if (user == null) {
                    LOG.debug("Юзер не найден.");
                }
            }
            return;
            // выкинуть на страницу с ошибкой
        }
        HttpSession session = request.getSession(true);
        session.setAttribute("currentUser", user);
        switch (user.getIdRole()) {
            case SecurityBean.ADMIN: {
                // это админ (логично)
                break;
            }
            case SecurityBean.CLIENT: {
                try {
                    clientDao.getClientByID(user.getIdClient());
                } catch (DaoException ex) {
                    LOG.error("Ошибка загрузки клиента.", ex);
                    throw ex;
                }
                response.sendRedirect(request.getContextPath() + CLIENT_HOME);
                return;
            }
            case SecurityBean.LEGAL_ENTITY: {
                // юр. лицо
                break;
            }
            default: {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("Юзер неизвестной категории.");
                }
                break;
            }
        }
        response.sendRedirect(request.getContextPath());
    }

    /**
     * Выход пользователя и перенаправление на корневой каталог сайта.
     *
     * @param request берём из методов doGet/doPost
     * @param response берём из методов doGet/doPost
     * @throws ServletException
     * @throws IOException
     */
    private void logout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        session.setAttribute("currentUser", null);
        response.sendRedirect(request.getContextPath());
    }
}
