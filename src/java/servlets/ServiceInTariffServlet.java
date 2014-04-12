/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.ServiceDao;
import dao.ServiceInTariffDao;
import dao.TariffDao;
import factory.TableFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import objects.Service;
import objects.ServiceInTariff;
import objects.Tariff;
import oracle.OracleTableFactory;

/**
 *
 * @author Ivan
 */
//@WebServlet(name = "ServiceInTariffServlet", loadOnStartup = 1, urlPatterns = {"/ServiceInTariffServlet/","SelectSInT","/SInTAdd/","/SInTDelete/","SInTUpdate","SInTFilter"})
public class ServiceInTariffServlet extends HttpServlet {

    private TableFactory factory = new OracleTableFactory();
    private ServiceInTariffDao serviceInTariffDao = factory.makeServiceInTariff();
    private ServiceDao serviceDao = factory.makeService();
    private TariffDao tariffDao = factory.makeTariffList();
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
        response.setContentType("text/html;charset=UTF-8");
       
        
    }

    
    protected void selectAllServiceInTariff(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<ServiceInTariff> sInT = serviceDao.getAllType();
        goToSelect(sInT, request, response);
    }

    protected void serviceAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Service service = new Service();
        int idType = Integer.parseInt(convert(request.getParameter("ID_type")));
        String nameService = convert(request.getParameter("name_service"));
        double cost = Double.parseDouble(convert(request.getParameter("cost")));
        // int idService = Integer.parseInt(request.getParameter("ID_Service"));
        //service.setIdService(idService);
        service.setIdType(idType);
        service.setNameService(nameService);
        service.setCost(cost);
        serviceDao.addService(service);
    }

    protected void serviceDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //System.out.println(convert(request.getParameter("ID_Service")));
        int idService = Integer.parseInt(convert(request.getParameter("ID_Service")));
        serviceDao.deleteService(idService);
    }

    protected void serviceUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Service service = new Service();
        int idType = Integer.parseInt(convert(request.getParameter("ID_type")));
        String nameService = convert(request.getParameter("name_service"));
        double cost = Double.parseDouble(convert(request.getParameter("cost")));
        int idService = Integer.parseInt(convert(request.getParameter("ID_Service")));
        service.setIdService(idService);
        service.setIdType(idType);
        service.setNameService(nameService);
        service.setCost(cost);
        serviceDao.updateService(service);
    }
    
    protected void goToSelect(List<ServiceInTariff> sInT, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Service> service = serviceDao.getAllServices();
        List<Tariff> tariff = tariffDao.getAllTariffList();
        request.getSession().setAttribute("ServiceList", sInT);
        request.getSession().setAttribute("TypeServiceList", sInt);
        response.sendRedirect("/MTSweb/showService/showService.jsp");
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
