package checkbooks.service;


import it.sauronsoftware.ftp4j.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Arrays;
import java.util.List;

@Service
public class FtpClientAdapter {

    private String name;
    private String password;
    private FTPClient client = new FTPClient();
    private String localPath;



    public FtpClientAdapter(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public void login(String host) throws FTPException, IOException, FTPIllegalReplyException {
        this.client.connect(host);
        this.client.login(this.name, this.password);
    }

    public void disconnect() throws FTPException, IOException, FTPIllegalReplyException {
        if (this.client != null) {
            this.client.disconnect(true);
        }

    }

    public void cd(String s) throws FTPException, IOException, FTPIllegalReplyException {
        if (this.client != null) {
            this.client.changeDirectory(s);
        }

    }


    public void get(String file) throws FTPException, IOException, FTPDataTransferException, FTPIllegalReplyException, FTPAbortedException, FTPListParseException {
        if (this.client != null) {
            file = file.substring(0, file.length() - 1);
            cd(file);
            for (String image : client.listNames()) {
                client.download(image, new File(localPath + file + "/" + image));
            }

        }
    }


    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public InputStream getInputStream(String file) throws FTPException, IOException, FTPDataTransferException, FTPIllegalReplyException, FTPAbortedException {


        System.out.println(localPath + "/" + file);
        this.client.download(file, new File(localPath + "/" + file));

        InputStream inputStream = new FileInputStream(localPath + "/" + file);

        return inputStream;

    }

    public int getCountImg() throws FTPAbortedException, FTPDataTransferException, FTPException, FTPListParseException, FTPIllegalReplyException {
        int count = 0;
        try {

            List<String> list = Arrays.asList(client.listNames());
            for (String nameFile : list) {
                if (nameFile.endsWith(".jpg")) {
                    count++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void move(String oldPath, String newPath) throws FTPException, IOException, FTPIllegalReplyException {
        client.rename(oldPath, newPath);
    }

    public void put(String file) throws FTPException, IOException, FTPDataTransferException, FTPIllegalReplyException, FTPAbortedException {
        if (this.client != null) {
            this.client.upload(new File(file));
        }

    }
}

