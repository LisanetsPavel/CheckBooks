package checkbooks.service;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.annotations.SourceType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


@Service
public class DownloadingService {

    private static Log log = LogFactory.getLog(DownloadingService.class.getName());

    @Value("${ip}")
    private String IP;
    @Value("${login}")
    private String LOGIN;
    @Value("${password}")
    private String PASSWORD;
    @Value("${directory}")
    private String DIRECTORY;

    private int countFileImg;


    public int getCountFileImg() {
        return countFileImg;
    }

    public InputStream getInput(String dir, String file) {
        InputStream inputStream = null;
        try {
            FtpClientAdapter ftpClientAdapter = getFtpClient(dir);
            countFileImg = ftpClientAdapter.getCountImg();
            inputStream = ftpClientAdapter.getInputStream(file + ".jpg");

        } catch (Exception e) {
            log.error(e);
        }

        return inputStream;
    }

    public Set<String> getBookPages(int bookId, String corState) {

        Set<String> pagesSet = new HashSet<>();
        FtpClientAdapter ftpClientAdapter = null;
        try {
            File bookDir = new File(DIRECTORY + "/" + bookId);
            if (!bookDir.exists() && !bookDir.mkdir()) {
                throw new RuntimeException("Couldn't create directory for book" + bookId);
            }
            ftpClientAdapter = getFTPClient(DIRECTORY, corState);
            ftpClientAdapter.get(String.valueOf(bookId) + "/");
            pagesSet.addAll(Arrays.asList(bookDir.list()));
        } catch (Exception e) {
            log.error(e);
        } finally {
            closeFTPClient(ftpClientAdapter);
        }

        removeNotJpg(pagesSet);

        return pagesSet;
    }

    private void closeFTPClient(FtpClientAdapter ftpClientAdapter) {
        try {
            if (ftpClientAdapter != null) {
                ftpClientAdapter.disconnect();
            }
        } catch (Exception e) {
            log.error(e);
        }

    }

    private FtpClientAdapter getFTPClient(String localPath, String corState) {
        FtpClientAdapter client = new FtpClientAdapter(LOGIN, PASSWORD);
        try {
            client.login(IP);
            client.cd(corState);
            client.setLocalPath(localPath);
        } catch (Exception e) {
            log.error(e);
        }
        return client;
    }

    private FtpClientAdapter getFtpClient(String bookId) {

        FtpClientAdapter client = new FtpClientAdapter(LOGIN, PASSWORD);
        try {
            client.login(IP);
            client.cd("30");
            client.cd(bookId);
            client.setLocalPath(DIRECTORY);
        } catch (Exception e) {
            log.error(e);
        }

        return client;

    }


    private void removeNotJpg(Set<String> set) {

        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String str = iterator.next();
            if (!str.endsWith("jpg")) {
                iterator.remove();
            }
        }

    }


}
