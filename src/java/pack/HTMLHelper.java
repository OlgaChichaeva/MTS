/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pack;

import javax.servlet.http.HttpServletRequest;
import objects.User;

/**
 * Класс для помощи в составлении HTML-страниц.
 * Берёт на себя рутинную работу. Всё, что придётся
 * часто использовать в JSP-шках, желательно спрятать сюда.
 * @author Ivan
 */
public class HTMLHelper {
    
    private HTMLHelper() {}
    
    /**
     * Путь к папке с заголовочными jsp.
     */
    public static final String HEADERS = "/WEB-INF/headers";
    
    /**
     * Путь к папке с файлами javascript (для подключения нужно
     * указывать корневой каталог сайта).
     */
    public static final String JS = "/js";
    
    /**
     * Путь к библиотеке jquery (для подключения нужно
     * указывать корневой каталог сайта).
     */
    public static final String JQUERY = JS + "/jquery-1.5.1.js";
    
    /**
     * Путь к таблице стилей (для подключения нужно
     * указывать корневой каталог сайта).
     */
    public static final String CSS = "/css/style.css";
    
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
                "<table border=\"0\" align=\"center\">"
                 + "<tr>"
                        + "<td>"
                            + "<form name=\"Data Input Form\" action=\"" + updatePath + "\" method=\"POST\">"  
                                + "<input type=\"submit\" value=\"Редактировать\" />" 
                                + "<input type = \"hidden\" name = \"" + name + "\" value = \"" + id + "\"/>"
                            + "</form>"
                        + "</td>"
                        + "<td>"
                            + "<form name=\"Data Input Form\" action=\"" + deletePath + "\" method=\"POST\">"  
                                + "<input type=\"submit\" value=\"Удалить\" />" 
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
    
    /**
     * Возвращает строку, подключающую таблицу стилей CSS.
     * @param root корневая директория сайта
     * @return строку, подключающую css
     */
    public static String includeCSS(String root) {
        return "<link rel=\"stylesheet\" type=\"text/css\" href=\"" + root + CSS + "\" />";
    }
    
    /**
     * Преобразование телефонного номера в отформатированную строку.
     * @param phone номер
     * @return отформатированный номер в случае успеха, иначе
     * просто строковое представление номера.
     */
    public static String phoneToString(long phone) {
        final char SEP = '-';
        String temp = Long.toString(phone);
        try {
            StringBuilder stringPhone = new StringBuilder(temp);
            stringPhone.insert(9, SEP);
            stringPhone.insert(7, SEP);
            stringPhone.insert(4, SEP);
            stringPhone.insert(1, SEP);
            return stringPhone.toString();
        } catch (IndexOutOfBoundsException ex) {
            return temp;
        }
    }
}
