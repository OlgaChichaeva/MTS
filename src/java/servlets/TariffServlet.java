/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

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
import objects.ServiceInTariff;
import objects.Tariff;
import pack.DaoMaster;

/**
 *
 * @author Ivan
 */
@WebServlet(name = "TariffServlet", loadOnStartup = 1, urlPatterns = {
    "/SelectAllTariff/",
    "/TariffFilter/",
    "/ShowTariff/"
})
public class TariffServlet extends HttpServlet {

    private final TariffDao tariffDao = DaoMaster.getTariffDao();
    private final ServiceInTariffDao servInTarDao = DaoMaster.getServiceInTariffDao();
    
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
        request.getRequestDispatcher("/tariff/showTariffList.jsp").forward(request, response);
    }

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
        request.getRequestDispatcher("/tariff/showTariff.jsp").forward(request, response);
    }
}
