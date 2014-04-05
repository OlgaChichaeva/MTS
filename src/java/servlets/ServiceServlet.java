/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Service.Service;
import Service.Service;
import Service.ServiceDao;
import Service.ServiceDao;
import TypeService.TypeService;
import TypeService.TypeServiceDao;
import factory.TableFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oracle.OracleTableFactory;

/**
 *
 * @author Ольга
 */
@WebServlet(name = "ContrillerServlet", loadOnStartup = 1, urlPatterns = {"/SelectAllServiceServlet/", "/ServiceAddServlet/", "/ServiceDeleteServlet/", "/ServiceUpdateServlet/"})
public class ServiceServlet  extends HttpServlet  {

    
    private TableFactory factory = new OracleTableFactory();
    private ServiceDao serviceDao = factory.makeService();
     private TypeServiceDao serviceTypeDao = factory.makeTypeService();
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
        processRequest(request, response);
        if (userPath.equals("/SelectAllServiceServlet/")) {
            response.setContentType("text/html;charset=UTF-8");

            List<Service> services = serviceDao.getAllServices();
            List<TypeService> typeServices = serviceTypeDao.getAllType();

            request.getSession().setAttribute("ServiceList", services);
            request.getSession().setAttribute("TypeServiceList", typeServices);

            PrintWriter out = response.getWriter();
            response.sendRedirect("/MTSweb/showService/showService.jsp");

        }
        if (userPath.equals("/ServiceAddServlet/")) {
            response.setContentType("text/html;charset=UTF-8");
            Service service = new Service();
            int IdType = Integer.parseInt(request.getParameter("ID_type"));
            String nameService = request.getParameter("name_service");
            double cost = Double.parseDouble(request.getParameter("cost"));
            // int idService = Integer.parseInt(request.getParameter("ID_Service"));
            //service.setIdService(idService);
            service.setIdType(IdType);
            service.setNameService(nameService);
            service.setCost(cost);
            serviceDao.addService(service);
        }
        if (userPath.equals("/ServiceDeleteServlet/")) {
            response.setContentType("text/html;charset=UTF-8");
            System.out.println(request.getParameter("ID_Service"));
            int idService = Integer.parseInt(request.getParameter("ID_Service"));
            serviceDao.deleteService(idService);
        }
        if (userPath.equals("/ServiceUpdateServlet/")) {
            response.setContentType("text/html;charset=UTF-8");

            Service service = new Service();
            int IdType = Integer.parseInt(request.getParameter("ID_type"));
            String nameService = request.getParameter("name_service");
            double cost = Double.parseDouble(request.getParameter("cost"));
            int idService = Integer.parseInt(request.getParameter("ID_Service"));
            service.setIdService(idService);
            service.setIdType(IdType);
            service.setNameService(nameService);
            service.setCost(cost);
            serviceDao.updateService(service);

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
        processRequest(request, response);
         doGet(request, response);

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
