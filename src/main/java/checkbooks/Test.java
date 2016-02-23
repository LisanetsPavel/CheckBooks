package checkbooks;

import checkbooks.service.BookService;
import checkbooks.service.DownloadingService;
import checkbooks.service.FtpClientAdapter;
import checkbooks.service.MD5Seevice;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;

/**
 * Created by pc8 on 17.09.15.
 */
@Component
public class Test {


    public static void main(String[] args) throws Exception{

//        System.out.println(client.currentDirectory());
//       /*    client.changeDirectory("/10");
//        client.changeDirectory("/10/5742");
//
//        System.out.println(Arrays.toString(client.listNames()));
//     for  (String image : client.listNames()) {
//            client.download("/10/5742/"+ image , new File("/home/pc8/Tmp/" + image));
//        }*/
//        System.out.println(Arrays.toString(client.listNames()));
//
//        client.rename("/10/5742", "/20/5742");

//        FtpClientAdapter ftpClientAdapter = new FtpClientAdapter("convertbookuser", "ktnf.obqntktdbpjh");

//        ftpClientAdapter.move("/20/5", "/10/5");


    }
}
