package checkbooks.service;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FileService {
    private static Log log = LogFactory.getLog(FileService.class.getName());

    @Value("${directory}")
    private String DIRECTORY;

    public File getPage(int bookId, String page) {
        File file = new File(DIRECTORY + "//" + bookId + "//" + page);
        if (!file.exists()) {
            throw new RuntimeException("Could not find file");
        }
        return file;
    }

    public void deleteFiles(Integer bookId) {
        File file = new File(DIRECTORY + "//" + bookId);

        deleteFile(file);
    }

    public void  deleteAllFiles(){
        File file = new File(DIRECTORY);
        for(File f : file.listFiles()) {
            if (!f.getName().equals("1")){
                deleteFile(f);
            }
          }
    }

    private void deleteFile(File file) {
        if (!file.exists())
            return;
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    deleteFile(f);
                }
            }
        }
        if (!file.delete()) {
            log.warn("File " + file.getName() + " wasn't deleted");
        }
    }
}
