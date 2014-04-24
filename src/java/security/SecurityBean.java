/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import java.util.Collection;
import java.util.HashSet;
import objects.User;

/**
 * Класс служит для регулирования доступа к страницам.
 * Как использовать: сначала при помощи метода setRole
 * добавить ID всех ролей, которые должны иметь доступ
 * (админ входит по умолчанию). Затем выполнить проверку,
 * является ли роль конкретного пользователя допустимой (метод isUserAccepted).
 * 
 * Как можно использовать в jsp:
 * Сначала берём (или, в случае отсутствия, создаём новый) объект класса:
 * <jsp:useBean id="security" class="security.SecurityBean" scope="request" />
 * Затем добавляем в него все нужные роли:
 * <jsp:setProperty name="security" property="role" value="ID оли" />
 * И наконец проверяем в скриплете:
 * <%
 *      if (!isUserAccepted(user) {
 *          // не пустить
 *      }
 * %>
 * Вместо scope="request" можно написать scope="page", если и заполнение,
 * и проверка происходят на одной странице (т.е. без jsp:include).
 * @author Ivan
 */
public class SecurityBean {
    /**
     * ID некоторых ролей.
     */
    public static final int NOT_LOGGED = -1;
    public static final int ADMIN = 1;
    public static final int CLIENT = 2;
    public static final int LEGAL_ENTITY = 3;
    
    private Collection<Integer> acceptedRoles;
    
    public SecurityBean() {
        acceptedRoles = new HashSet<>();
        acceptedRoles.add(ADMIN);
    }
    
    public static void denyAccess() {
        throw new SecurityException();
    }
    
    /**
     * Добавить роль в список допустимых. Название метода не addRole, чтобы
     * допустить использование jsp:setProperty.
     * @param role ID роли
     */
    public void setRole(int role) {
        acceptedRoles.add(role);
    }
    
    /**
     * Убрать админа из допустимых. (на всякий случай)
     */
    public void restrictAdmin() {
        acceptedRoles.remove(ADMIN);
    }
    
    /**
     * Проверить, разрешён ли доступ данному пользователю.
     * @param user пользователь, которого нужно проверить.
     * @return true, если доступ разрешён, иначе false
     */
    public boolean isUserAccepted(User user) {
        if (user == null) {
            return false;
        }
        for (int role : acceptedRoles) {
            if (user.getIdRole() == role) {
                return true;
            }
        }
        return false;
    }
}
