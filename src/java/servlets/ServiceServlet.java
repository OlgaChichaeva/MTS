/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import objects.Service;
import dao.ServiceDao;
import objects.TypeService;
import dao.TypeServiceDao;
import factory.TableFactory;
import filters.ServiceFilter;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oracle.OracleTableFactory;
//import pack.EncodingConverter;
import static pack.EncodingConverter.convert; // Чтобы писать меньше

/**
 *
 * @author Ольга
 */
@WebServlet(name = "ContrillerServlet", loadOnStartup = 1,
        urlPatterns = {
    "/SelectAllService/",
    "/ServiceAdd/",
    "/ServiceDelete/",
    "/ServiceUpdate/",
    "/ServiceFilter/"
})
public class ServiceServlet extends HttpServlet {

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

    protected void selectAllService(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Service> services = serviceDao.getAllServices();
        goToSelect(services, request, response);
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

    protected void serviceFilter(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServiceFilter filter = new ServiceFilter();
        String idType = convert(request.getParameter("ID_type"));
        String nameService = convert(request.getParameter("name_service"));
        String cost = convert(request.getParameter("cost"));
        System.out.println("id_type=" + idType);
        System.out.println("name_service=" + nameService);
        System.out.println("cost=" + cost);
        filter.setCost(cost);
        filter.setNameService(nameService);
        filter.setTypeService(idType);

        List<Service> services = serviceDao.getFilteredServices(filter);
        goToSelect(services, request, response);
    }

    protected void goToSelect(List<Service> services, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<TypeService> typeServices = serviceTypeDao.getAllType();
        request.getSession().setAttribute("ServiceList", services);
        request.getSession().setAttribute("TypeServiceList", typeServices);
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
        String userPath = request.getServletPath();
        response.setContentType("text/html;charset=UTF-8");

        switch (userPath) {
            case "/SelectAllService/": {
                selectAllService(request, response);
                break;
            }
            case "/ServiceAdd/": {
                serviceAdd(request, response);
                break;
            }
            case "/ServiceDelete/": {
                serviceDelete(request, response);
                break;
            }
            case "/ServiceUpdate/": {
                serviceUpdate(request, response);
                break;
            }
            case "/ServiceFilter/": {
                serviceFilter(request, response);
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
