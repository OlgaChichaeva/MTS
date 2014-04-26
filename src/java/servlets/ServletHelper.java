/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import javax.servlet.http.HttpServletRequest;
import objects.User;

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
    
    private ServletHelper() {}
}
