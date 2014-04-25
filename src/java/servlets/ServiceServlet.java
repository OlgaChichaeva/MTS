/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.PhoneNumberDAO;
import objects.Service;
import dao.ServiceDao;
import dao.ServiceInSimDAO;
import dao.SimDao;
import objects.TypeService;
import dao.TypeServiceDao;
import filters.ServiceFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import objects.PhoneNumber;
import objects.ServiceInSim;
import objects.Sim;
import objects.User;
import pack.DaoMaster;
import static pack.EncodingConverter.convert; // Чтобы писать меньше
import pack.HTMLHelper;
import security.SecurityBean;
import static pack.PathConstants.*;
import static pack.LogManager.LOG;

/**
 * Сервлет для работы с услугами.
 *
 * @author Ольга
 */
@WebServlet(name = "ContrillerServlet", loadOnStartup = 1,
        urlPatterns = {
    SELECT_ALL_SERVICE,
    SERVICE_ADD,
    SERVICE_DELETE,
    SERVICE_UPDATE,
    SERVICE_FILTER,
    SERVICE_ADD_FORM,
    SERVICE_UPDATE_FORM,
    ADD_SERVICE_TO_SIM,
    CHOOSE_SIM,
    REMOVE_SERVICE_FROM_SIM
})
public class ServiceServlet extends HttpServlet {

    private final ServiceDao serviceDao = DaoMaster.getServiceDao();
    private final TypeServiceDao serviceTypeDao = DaoMaster.getTypeServiceDao();
    private final SimDao simDao = DaoMaster.getSimDao();
    private final PhoneNumberDAO phoneNumberDao = DaoMaster.getPhoneNumberDao();
    private final ServiceInSimDAO serviceInSimDao = DaoMaster.getServiceInSimDao();

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
        SecurityBean.checkAccept(HTMLHelper.getUser(request));
        List<TypeService> typeServices = serviceTypeDao.getAllType();
        request.setAttribute("TypeServiceList", typeServices);
        request.getRequestDispatcher("/WEB-INF/showService/addService.jsp").forward(request, response);
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
        SecurityBean.checkAccept(HTMLHelper.getUser(request));
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
        response.sendRedirect(request.getContextPath() + SELECT_ALL_SERVICE);
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
        SecurityBean.checkAccept(HTMLHelper.getUser(request));
        int idService = Integer.parseInt(convert(request.getParameter("ID_Service")));
        serviceDao.deleteService(idService);
        response.sendRedirect(request.getContextPath() + SELECT_ALL_SERVICE);
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
        SecurityBean.checkAccept(HTMLHelper.getUser(request));
        List<TypeService> typeServices = serviceTypeDao.getAllType();
        Service serviceToUpdate = serviceDao.getService(Integer.parseInt(request.getParameter("ID_Service")));
        request.setAttribute("TypeServiceList", typeServices);
        request.setAttribute("serviceToUpdate", serviceToUpdate);
        request.getRequestDispatcher("/WEB-INF/showService/update.jsp").forward(request, response);
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
        SecurityBean.checkAccept(HTMLHelper.getUser(request));
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
        response.sendRedirect(request.getContextPath() + SELECT_ALL_SERVICE);
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
        request.getRequestDispatcher("/WEB-INF/showService/showService.jsp").forward(request, response);
    }

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
        User user = HTMLHelper.getUser(request);
        SecurityBean.checkAccept(user, SecurityBean.CLIENT, SecurityBean.LEGAL_ENTITY);
        List<Sim> simList = null;
        Map<Sim, PhoneNumber> simAndNumbers = null;
        if (user.getIdRole() == SecurityBean.CLIENT) {
            simList = simDao.getSimListByClientID(user.getIdClient());
        }
        if (simList != null) {
            simAndNumbers = new HashMap<>();
            for (Sim sim : simList) {
                simAndNumbers.put(sim, phoneNumberDao.getNumberBySimID(sim.getSimId()));
            }
        }
        request.setAttribute("simAndNumbers", simAndNumbers); // Кладём список всех контрактов в запрос.
        request.getRequestDispatcher("/WEB-INF/showService/chooseSim.jsp").forward(request, response);
    }

    /**
     * Добавляет выбранную услугу к сим-карте.
     *
     * @param request берём из методов doGet/doPost
     * @param response берём из методов doGet/doPost
     */
    private void addServiceToSim(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idSim = Integer.parseInt(request.getParameter("sim_id"));
        int idService = Integer.parseInt(request.getParameter("ID_service"));
        ServiceInSim sis = new ServiceInSim();
        sis.setIdService(idService);
        sis.setIdSim(idSim);
        serviceInSimDao.insert(sis);
        response.sendRedirect(request.getContextPath() + SELECT_ALL_SERVICE);
    }

    /**
     * Отключает услугу от сим-карты.
     *
     * @param request
     * @param response
     */
    private void removeServiceFromSim(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idSim = Integer.parseInt(request.getParameter("sim_id"));
        int idService = Integer.parseInt(request.getParameter("ID_service"));
        ServiceInSim sis = new ServiceInSim();
        sis.setIdService(idService);
        sis.setIdSim(idSim);
        serviceInSimDao.deleteConcreteServiceInSim(sis);
        response.sendRedirect(request.getContextPath() + SELECT_ALL_SERVICE);
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
            case SELECT_ALL_SERVICE: {
                selectAllService(request, response);
                break;
            }
            case SERVICE_FILTER: {
                serviceFilter(request, response);
                break;
            }
            case SERVICE_ADD_FORM: {
                serviceAddForm(request, response);
                break;
            }
            case CHOOSE_SIM: {
                chooseSim(request, response);
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

        switch (userPath) {
            case SERVICE_ADD: {
                serviceAdd(request, response);
                break;
            }
            case SERVICE_DELETE: {
                serviceDelete(request, response);
                break;
            }
            case SERVICE_UPDATE: {
                serviceUpdate(request, response);
                break;
            }
            case SERVICE_UPDATE_FORM: {
                serviceUpdateForm(request, response);
                break;
            }
            case ADD_SERVICE_TO_SIM: {
                addServiceToSim(request, response);
                break;
            }
            case REMOVE_SERVICE_FROM_SIM: {
                removeServiceFromSim(request, response);
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
