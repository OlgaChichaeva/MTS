/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pack;

import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import objects.PhoneNumber;
import objects.Service;
import objects.Traffic;
import static pack.LogManager.LOG;

/**
 *
 * @author Ivan
 */
public class PDFHelper {

    private static final String SEP = File.separator;
    private static long deleteInterval = 3600000;
    private static String trafficReportPath = "trafficReports";
    private static boolean working = true;
    private static DeleteThread trafficReportThread;
    private final static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd.MM.yyyy'_'HH_mm_ss");

    static {
        configure("123"); // пока файл не важен
    }
    private static final FilenameFilter filter = new FilenameFilter() {
        @Override
        public boolean accept(final File dir,
                final String name) {
            return name.matches(".*\\.pdf");
        }
    };

    /**
     * Настройка параметров класса на основании значений из файла. Пока что
     * чтение из файла не реализовано, применяются настройки по умолчанию.
     *
     * @param propertiesFile файл, хранящий настройки
     */
    public static void configure(String propertiesFile) {
        // потом будет
        deleteInterval = 120000;
        setTrafficReportPath("trafficReports");
        trafficReportThread = new DeleteThread(getTrafficReportPath(), "Поток удаления отчётов трафика");
    }

    /**
     * @return the working
     */
    public static boolean isWorking() {
        return working;
    }

    /**
     * @param aWorking the working to set
     */
    public static void setWorking(boolean aWorking) {
        working = aWorking;
        if (aWorking) {
            configure("123");
        }
    }

    /**
     * @return the trafficReportPath
     */
    public static String getTrafficReportPath() {
        return trafficReportPath;
    }

    /**
     * @param aTrafficReportPath the trafficReportPath to set
     */
    public static void setTrafficReportPath(String aTrafficReportPath) {
        trafficReportPath = aTrafficReportPath;
    }

    /**
     *
     * @param phone
     * @param trafficList
     * @return
     */
    public static String createTrafficReport(PhoneNumber phone, List<Traffic> trafficList)
            throws PDFException {
        try {
            String filename = dateTimeFormat.format(new Date(System.currentTimeMillis())) + ".pdf";

            Document document = new Document(PageSize.A4, 50, 50, 50, 50);

            File path = new File(trafficReportPath);
            if (!path.exists()) {
                boolean created = path.mkdirs();
                if (LOG.isDebugEnabled()) {
                    LOG.debug("Создание папки " + path.getAbsolutePath() + (created ? " удалось." : " не удалось."));
                }
            }
            File pdfReport = new File(trafficReportPath + SEP + filename);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfReport));
            document.open();

            // Генерация PDF -----------------------------------
            Font font = new Font(BaseFont.createFont("fonts" + SEP + "tahoma.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED));

            String title = "Отчёт использования услуг для номера " + HTMLHelper.phoneToString(phone.getNumber());
            Paragraph PDFtitle = new Paragraph(title, font);
            document.add(PDFtitle);
            PdfPTable table = new PdfPTable(4);
            table.setSpacingBefore(25);
            table.setSpacingAfter(25);
            Font headerFont = font;
            headerFont.setFamily("tahoma.ttf");
            table.addCell(new Phrase("Время", headerFont));
            table.addCell(new Phrase("Услуга", headerFont));
            table.addCell(new Phrase("Количество", headerFont));
            table.addCell(new Phrase("Стоимость", headerFont));
            for (Traffic traffic : trafficList) {
                Service service = traffic.getService();
                table.addCell(new Phrase(HTMLHelper.makeDateTime(traffic.getDate()), font));
                table.addCell(new Phrase(service.getNameService(), font));
                table.addCell(new Phrase(traffic.getAmount() + " " + service.getTypeService().getMeasure(), font));
                table.addCell(new Phrase(Double.toString(traffic.getCost()), font));
            }
            document.add(table);
            document.close();
            // -------------------------------------------------

            return pdfReport.getAbsolutePath();
        } catch (IOException | DocumentException ex) {
            LOG.error("Ошибка при создании PDF-файла.", ex);
            throw new PDFException(ex);
        }
    }

    private PDFHelper() {
    }

    //---------------------------------------------------    
    /**
     * Поток, удаляющий все файлы из папки.
     */
    private static class DeleteThread extends Thread {

        private File folder;
        private boolean running = true;

        public DeleteThread(String folder) {
            this.folder = new File(folder);
            setDaemon(true);
            start();
        }

        public DeleteThread(String folder, String name) {
            setName(name);
            setDaemon(true);
            this.folder = new File(folder);
            start();
        }
        
        public void setFolder(String folder) {
            this.folder = new File(folder);
        }

        /**
         * Удаляет все файлы из папки каждые deleteInterval миллисекунд, если
         * параметр working hfdty true.
         */
        @Override
        public void run() {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Поток удаления файлов запущен.");
            }
            while (running) {
                try {
                    Thread.sleep(deleteInterval); // Временной интервал между удалениями
                } catch (InterruptedException ex) {
                    if (LOG.isDebugEnabled()) {
                        LOG.debug("Поток удаления " + getName() + " прерван.", ex);
                    }
                }

                if (!isWorking()) { // Если PDFHelper отключён, ничего не делаем.
                    continue;
                }

                if (LOG.isDebugEnabled()) {
                    LOG.debug("Удаление файлов.");
                }
                // Удаляем все файлы из папки.
                File[] files = folder.listFiles(filter);
                try {
                    for (final File file : files) {
                        if (!file.delete()) {
                            if (LOG.isDebugEnabled()) {
                                LOG.debug("Невозможно удалить файл " + file.getAbsolutePath() + ".");
                            }
                        }
                    }
                } catch (NullPointerException ex) {
                    if (LOG.isDebugEnabled()) {
                        LOG.debug("Ошибка чтения файлов из папки " + folder.getAbsolutePath() + ".");
                    }
                }
            }
        }
    }
}
