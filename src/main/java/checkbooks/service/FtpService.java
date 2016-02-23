package checkbooks.service;

import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by pc8 on 24.09.15.
 */

@Service
public class FtpService {


    @Autowired
    FtpClientAdapter ftpClientAdapter;
    @Value("${ip}")
    private String IP;
    @Value("${login}")
    private String LOGIN;
    @Value("${password}")
    private String PASSWORD;

    private static Log log = LogFactory.getLog(FtpService.class.getName());

    FtpService() {

    }

    public void moveAllGoodBooks(int oldState, int newState, int bookId) throws FTPException, IOException, FTPIllegalReplyException {
     try {
         ftpClientAdapter.move("/" + oldState + "/" + bookId, "/" + newState + "/" + bookId);
     } catch (Exception e){
       log.error(e);
     }
    }

}
