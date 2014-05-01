/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pack;

import java.io.File;
import java.io.FilenameFilter;
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
    private boolean started = false;

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
        trafficReportPath = "trafficReports";
        trafficReportThread.stopDeleteThread();
        trafficReportThread = new DeleteThread(trafficReportPath, "Поток удаления отчётов трафика");
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
     * Пусть будет (чтобы не писать Class.ForName).
     */
    public void start() {
        if (!started) {
            started = true;
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
