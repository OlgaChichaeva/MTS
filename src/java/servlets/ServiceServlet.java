/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import objects.Service;
import dao.ServiceDao;
import objects.TypeService;
import dao.TypeServiceDao;
import filters.ServiceFilter;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pack.DaoMaster;
import static pack.EncodingConverter.convert; // Чтобы писать меньше

/**
 * Сервлет для работы с услугами.
 *
 * @author Ольга
 */
@WebServlet(name = "ContrillerServlet", loadOnStartup = 1,
        urlPatterns = {
    "/SelectAllService/",
    "/ServiceAdd/",
    "/ServiceDelete/",
    "/ServiceUpdate/",
    "/ServiceFilter/",
    "/ServiceAddForm/",
    "/ServiceUpdateForm/"
})
public class ServiceServlet extends HttpServlet {

    private final ServiceDao serviceDao = DaoMaster.getServiceDao();
    private final TypeServiceDao serviceTypeDao = DaoMaster.getTypeServiceDao();

    /**
     * Перенаправляет на страницу со списком всех сервисов. Сначала получает
     * список из ДАО, потом переходит на страницу.
     *
     * @param request берём из методов doGet/doPost
     * @param response берём из методов doGet/doPost
     * @throws ServletException
     * @throws IOException
     */
    protected void selectAllService(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Service> services = serviceDao.getAllServices();
        goToSelect(services, request, response);
    }

    /**
     * Перенаправляет на страницу добавления услуги. Нужен для того, чтобы
     * заполнять список типов не заранее, а только когда это потребуется
     *
     * @param request берём из методов doGet/doPost
     * @param response берём из методов doGet/doPost
     * @throws ServletException
     * @throws IOException
     */
    protected void serviceAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<TypeService> typeServices = serviceTypeDao.getAllType();
        request.setAttribute("TypeServiceList", typeServices);
        request.getRequestDispatcher("/showService/addService.jsp").forward(request, response);
    }

    /**
     * Добавляет услугу со значениями, взятыми из запроса. Затем перенаправляет
     * на страницу вывода всех услуг.
     *
     * @param request берём из методов doGet/doPost
     * @param response берём из методов doGet/doPost
     * @throws ServletException
     * @throws IOException
     */
    protected void serviceAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Service service = new Service();
        int idType = Integer.parseInt(convert(request.getParameter("ID_type")));
        String nameService = convert(request.getParameter("name_service"));
        double cost = Double.parseDouble(convert(request.getParameter("cost")));
        boolean optional = request.getParameter("optional") != null; // Если параметр не null, значит флажок был выбран
        service.setIdType(idType);
        service.setNameService(nameService);
        service.setCost(cost);
        service.setOptional(optional);
        serviceDao.addService(service);
        response.sendRedirect(request.getContextPath() + "/SelectAllService/");
    }

    /**
     * Удаляет услугу с ID, полученным из запроса. Затем перенаправляет на
     * страницу вывода всех услуг.
     *
     * @param request берём из методов doGet/doPost
     * @param response берём из методов doGet/doPost
     * @throws ServletException
     * @throws IOException
     */
    protected void serviceDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idService = Integer.parseInt(convert(request.getParameter("ID_Service")));
        serviceDao.deleteService(idService);
        response.sendRedirect("/MTSweb/SelectAllService/");
    }

    /**
     * Перенаправляет на страницу обновления услуги. Нужен для того, чтобы
     * заполнять список типов не заранее, а только когда это потребуется
     *
     * @param request берём из методов doGet/doPost
     * @param response берём из методов doGet/doPost
     * @throws ServletException
     * @throws IOException
     */
    protected void serviceUpdateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<TypeService> typeServices = serviceTypeDao.getAllType();
        Service serviceToUpdate = serviceDao.getService(Integer.parseInt(request.getParameter("ID_Service")));
        request.setAttribute("TypeServiceList", typeServices);
        request.setAttribute("serviceToUpdate", serviceToUpdate);
        request.getRequestDispatcher("/showService/update.jsp").forward(request, response);
    }

    /**
     * Обновляет услугу в согласии со значениями из параметров запроса, потом
     * перенаправляет на страницу вывода всех услуг.
     *
     * @param request берём из методов doGet/doPost
     * @param response берём из методов doGet/doPost
     * @throws ServletException
     * @throws IOException
     */
    protected void serviceUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Service service = new Service();
        int idType = Integer.parseInt(convert(request.getParameter("ID_type")));
        String nameService = convert(request.getParameter("name_service"));
        double cost = Double.parseDouble(convert(request.getParameter("cost")));
        int idService = Integer.parseInt(convert(request.getParameter("ID_Service")));
        boolean optional = request.getParameter("optional") != null; // Если параметр не null, значит флажок был выбран
        service.setIdService(idService);
        service.setIdType(idType);
        service.setNameService(nameService);
        service.setCost(cost);
        service.setOptional(optional);
        serviceDao.updateService(service);
        response.sendRedirect("/MTSweb/SelectAllService/");
    }

    /**
     * Перенаправляет на страницу с отфильтрованным списком сервисов. Сначала
     * получает список из ДАО, потом переходит на страницу.
     *
     * @param request берём из методов doGet/doPost
     * @param response берём из методов doGet/doPost
     * @throws ServletException
     * @throws IOException
     */
    protected void serviceFilter(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServiceFilter filter = new ServiceFilter();
        String idType = request.getParameter("ID_type");
        String nameService = request.getParameter("name_service");
        String cost = request.getParameter("cost");
        filter.setCost(cost);
        filter.setNameService(nameService);
        filter.setTypeService(idType);

        List<Service> services = serviceDao.getFilteredServices(filter);
        goToSelect(services, request, response);
    }

    /**
     * Перенаправляет на страницу показа списка сервисов.
     *
     * @param services список, который нужно отобразить
     * @param request берём из методов doGet/doPost
     * @param response берём из методов doGet/doPost
     * @throws ServletException
     * @throws IOException
     */
    private void goToSelect(List<Service> services, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("ServiceList", services);
        request.getRequestDispatcher("/showService/showService.jsp").forward(request, response);
    }

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
            case "/ServiceFilter/": {
                serviceFilter(request, response);
                break;
            }
            case "/ServiceAddForm/": {
                serviceAddForm(request, response);
                break;
            }
            default: {
                //doPost(request, response);
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

        switch (userPath) {
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
            case "/ServiceUpdateForm/": {
                serviceUpdateForm(request, response);
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
