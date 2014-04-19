/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.jshandlers;

import dao.SimDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import objects.Sim;
import pack.DaoMaster;

/**
 * Из-за глупого javascript придётся создавать по целому сервлету для обработки
 * только одного URL. Этот сервлет получает ID сим-карты, извлекает из БД
 * сим-карту с нужным ID и распечатывает. То, что сервлет распечатал, скрипт
 * должен забрать.
 *
 * @author Ivan
 */
@WebServlet(name = "ShowSim", loadOnStartup = 1, urlPatterns = {"/showSim"})
public class ShowSim extends HttpServlet {

    private SimDao simDao = DaoMaster.getSimDao();

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int simId = Integer.parseInt(request.getParameter("sim_id"));
        Sim sim = simDao.getIdSim(simId);
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (sim == null) {
                out.print("Не удалось загрузить сим-карту с таким ID");
                return;
            }
            out.print("Номер сим-карты: " + sim.getSimId() + "<br>");
            // !!!!!!!!!!!!! Добавить гиперссылку на тариф !!!!!!!!!!
            out.print("Тариф: " + sim.getTariff().getNameTariff() + "<br>");
            out.print("Баланс: " + sim.getAccount() + "<br>");
        }
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
        processRequest(request, response);
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
        processRequest(request, response);
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
