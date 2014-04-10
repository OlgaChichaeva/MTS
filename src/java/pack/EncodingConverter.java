/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pack;

import java.io.UnsupportedEncodingException;

/**
 * Класс для исправления ошибок кодировки.
 * @author Ivan
 */
public class EncodingConverter {
    /**
     * Чтобы писать меньше. Конвертирует из ISO-8859-1 в UTF-8.
     * @param source исходная строка
     * @return строку в кодировке UTF-8
     */
    public static String convert(String source) {
        return convertFromISO88591(source);
    }
    
    /**
     * Конвертирует из ISO-8859-1 в UTF-8. Нужно использовать ТОЛЬКО при обработке
     * POST-запросов, при GET с кодировкой проблем нет.
     * @param source исходная строка в кодировке ISO-8859-1
     * @return строку в кодировке UTF-8 
     */
    public static String convertFromISO88591(String source) {
        try {
            return new String(source.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            return source;
        }
    }
}
