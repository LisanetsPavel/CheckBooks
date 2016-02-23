package checkbooks.service;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by pc8 on 25.09.15.
 */
public class MD5Seevice {

    public String toMD5(String st){
        String md5Hex = DigestUtils.md5Hex(st);

        return md5Hex;
    }
}
