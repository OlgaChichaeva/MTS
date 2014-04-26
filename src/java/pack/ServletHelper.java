/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pack;

import dao.ClientContrDAO;
import javax.servlet.http.HttpServletRequest;
import objects.ClientContr;
import objects.User;
import security.SecurityBean;

/**
 * Класс содержит статические методы, которые могут пригодиться всем сервлетам.
 * @author Ivan
 */
public class ServletHelper {

    /**
     * Получает пользователя из сессии, к которой относится запрос
     * @param request запрос
     * @return пользователь (может быть null, если в сессии нет юзера)
     */
    public static User getUser(HttpServletRequest request) {
        return (User) request.getSession(true).getAttribute("currentUser");
    }
    
    /**
     * Проверяет, имеет ли право пользоватеь смотреть информацию о сим-карте.
     *
     * @param user пользоватеь
     * @param simId ИД сим-карты
     * @return true, если есть право просмотра, иначе false
     */
    public static boolean isUserAcceptedForSim(User user, int simId, ClientContrDAO clientContrDao) {
        switch (user.getIdRole()) {
            case SecurityBean.CLIENT: {
                // Проверяем, есть ли среди договоров клиента договор на эту сим-карту.
                for (ClientContr contr : clientContrDao.getContrsByClientID(user.getIdClient())) {
                    if (contr.getSimID() == simId) {
                        return true;
                    }
                }
                return false;
            }
            case SecurityBean.LEGAL_ENTITY: {
                break; // Временно
            }
            case SecurityBean.ADMIN: {
                return true;
            }
        }
        return false;
    }
    
    private ServletHelper() {}
}
