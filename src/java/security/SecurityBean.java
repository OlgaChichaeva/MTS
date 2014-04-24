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
 * Предназначен для использования в сервлетах.
 * Как пользоваться:
 * 1) Если неоходимо предоставить доступ нескольким ролям и запретить всем остальным,
 * то нужно вызвать метод checkAccept, указав роли, которым полагается доступ
 * (админу доступ предоставляется по умолчанию).
 * 2) Если нужно закрыть доступ по каким-либо собственным правилам, то
 * используйте метод denyAccess.
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
    
    @Deprecated
    public SecurityBean() {
        acceptedRoles = new HashSet<>();
        acceptedRoles.add(ADMIN);
    }
    
    /**
     * Запрещает доступ, бросая исключение.
     */
    public static void denyAccess() {
        throw new SecurityException();
    }
    
    /**
     * Проверяет доступ юзера к странице. Если доступа нет,
     * то бросает исключение. Если user == null, то считается, что доступа нет.
     * @param user Пользователь, которого нужно проверить
     * @param idRoles роли, которым нужно предоставить доступ.
     * Админу доступ предоставлен по умолчанию.
     */
    public static void checkAccept(User user, int ... idRoles) {
        if (user == null) {
            denyAccess();
            return;
        }
        int userRole = user.getIdRole();
        if (userRole == ADMIN) {
            return;
        }
        for (int role : idRoles) {
            if (userRole == role) {
                return;
            }
        }
        denyAccess();
    }
    
    /**
     * Добавить роль в список допустимых. Название метода не addRole, чтобы
     * допустить использование jsp:setProperty.
     * @param role ID роли
     */
    @Deprecated
    public void setRole(int role) {
        acceptedRoles.add(role);
    }
    
    /**
     * Убрать админа из допустимых. (на всякий случай)
     */
    @Deprecated
    public void restrictAdmin() {
        acceptedRoles.remove(ADMIN);
    }
    
    /**
     * Проверить, разрешён ли доступ данному пользователю.
     * @param user пользователь, которого нужно проверить.
     * @return true, если доступ разрешён, иначе false
     */
    @Deprecated
    public boolean isUserAccepted(User user) {
        if (user == null) {
            return false;
        }
        int userRole = user.getIdRole();
        for (int role : acceptedRoles) {
            if (userRole == role) {
                return true;
            }
        }
        return false;
    }
}

/**
 * Старая документация:
 * 
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
