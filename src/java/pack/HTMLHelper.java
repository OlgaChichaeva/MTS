/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pack;

/**
 * Класс для помощи в составлении HTML-страниц.
 * Берёт на себя рутинную работу. Всё, что придётся
 * часто использовать в JSP-шках, желательно спрятать сюда.
 * @author Ivan
 */
public class HTMLHelper {
    
    /**
     * Путь к папке с заголовочными jsp.
     */
    public static final String HEADERS = "/WEB-INF/headers";
    
    /**
     * Путь к папке со страницами, разграничивающими доступ.
     */
    public static final String SECURITY = "/WEB-INF/security";
    
    /**
     * Путь к странице, дающей клиенту доступ.
     */
    public static final String ACCEPT_CLIENT = SECURITY + "/acceptClient.jsp";
    
    /**
     * Путь к странице, дающей юр. лицу доступ.
     */
    public static final String ACCEPT_LEGAL = SECURITY + "/acceptLegalEntity.jsp";
    
    /**
     * Путь к странице, дающей юр. лицу доступ.
     */
    public static final String CHECK_ACCEPT = SECURITY + "/checkAccept.jsp";
    
    /**
     * Путь к заголовочной странице, используемой по умолчанию.
     */
    public static final String DEFAULT_HEADER = HEADERS + "/defaultHeader.jsp";
    
    /**
     * Путь к заголовочной странице для клиентов.
     */
    public static final String CLIENT_HEADER = HEADERS + "/clientHeader.jsp";
    
    /**
     * Путь к заголовочной странице для админа.
     */
    public static final String ADMIN_HEADER = HEADERS + "/adminHeader.jsp";
    
    /**
     * Путь к заголовочной странице для юр. лиц.
     */
    public static final String LEGAL_HEADER = HEADERS + "/legalEntityHeader.jsp";
    
    /**
     * Путь странице автоматического выбора заголовка
     */
    public static final String CHOOSE_HEADER = HEADERS + "/chooseHeader.jsp";
    
    /**
     * Получаем кнопки Update и Delete
     * @param updatePath путь для update
     * @param deletePath путь для delete
     * @param name имя параметра
     * @param id ID записи, для которой создаются кнопки
     * @return HTML-код, содержащий две кнопки
     */
    public static String makeUpdateAndDelete(String updatePath, String deletePath, String name, int id) {
        String buttons = 
                "<table>"
                 + "<tr>"
                        + "<td>"
                            + "<form name=\"Data Input Form\" action=\"" + updatePath + "\" method=\"POST\">"  
                                + "<input type=\"submit\" value=\"update\" />" 
                                + "<input type = \"hidden\" name = \"" + name + "\" value = \"" + id + "\"/>"
                            + "</form>"
                        + "</td>"
                        + "<td>"
                            + "<form name=\"Data Input Form\" action=\"" + deletePath + "\" method=\"POST\">"  
                                + "<input type=\"submit\" value=\"delete\" />" 
                                + "<input type = \"hidden\" name = \"" + name + "\" value = \"" + id + "\"/>"
                            + "</form>"
                        + "</td>"
                    + "</tr>"
                + "</table>";
        return buttons;    
    }
    
    /**
     * Метод можно использовать, если строка может быть null и
     * требуется выводить вместо null пустую строку ("")
     * @param source исходная строка
     * @return исходную строку, если она не null, иначе ""
     */
    public static String fromNull(String source) {
        if (source == null) {
            return "";
        } else {
            return source;
        }
    }
}
