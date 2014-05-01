/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pack;

import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.CMYKColor;
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
    private static boolean working = false;
    private static DeleteThread trafficReportThread;
    private static boolean started = false;
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
        deleteInterval = 10000;
        setTrafficReportPath("trafficReports");
        if (trafficReportThread != null) {
            trafficReportThread.stopDeleteThread();
        }
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

            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(trafficReportPath + SEP + filename));
            document.open();

            // Генерация PDF -----------------------------------
            String title = "Отчёт использования услуг для номера " + HTMLHelper.phoneToString(phone.getNumber());
            Paragraph PDFtitle = new Paragraph(title, FontFactory.getFont(FontFactory.HELVETICA, 18));
            Chapter chapter = new Chapter(0);
            Section reportSection = chapter.addSection(PDFtitle);
            PdfPTable table = new PdfPTable(4);
            table.setSpacingBefore(25);
            table.setSpacingAfter(25);
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            table.addCell(new Phrase("Время", headerFont));
            table.addCell(new Phrase("Услуга", headerFont));
            table.addCell(new Phrase("Количество", headerFont));
            table.addCell(new Phrase("Стоимость", headerFont));
            for (Traffic traffic : trafficList) {
                Service service = traffic.getService();
                table.addCell(HTMLHelper.makeDateTime(traffic.getDate()));
                table.addCell(service.getNameService());
                table.addCell(traffic.getAmount() + service.getTypeService().getMeasure());
                table.addCell(Double.toString(traffic.getCost()));
            }
            reportSection.add(table);
            document.add(chapter);
            document.close();
            // -------------------------------------------------

            return filename;
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

        private final File folder;
        private boolean deletingInProcess = false;
        private boolean running = true;

        public DeleteThread(String folder) {
            this.folder = new File(folder);
            start();
        }

        public DeleteThread(String folder, String name) {
            setName(name);
            this.folder = new File(folder);
            start();
        }

        /**
         * Останавливает поток удаления файлов. Если на момент вызова метода
         * происходит удаление файла, то поток завершится, когда файл будет
         * удалён.
         */
        public void stopDeleteThread() {
            new Thread() {
                @Override
                public void run() {
                    setDaemon(true);
                    while (deletingInProcess);
                    running = false;
                }
            }.start();
        }

        /**
         * Удаляет все файлы из папки каждые deleteInterval миллисекунд, если
         * параметр working hfdty true.
         */
        @Override
        public void run() {
            setDaemon(true);
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

                // Удаляем все файлы из папки.
                File[] files = folder.listFiles(filter);
                try {
                    for (final File file : files) {
                        deletingInProcess = true;
                        if (!file.delete()) {
                            if (LOG.isDebugEnabled()) {
                                LOG.debug("Невозможно удалить файл " + file.getAbsolutePath() + ".");
                            }
                        }
                        deletingInProcess = false;
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
