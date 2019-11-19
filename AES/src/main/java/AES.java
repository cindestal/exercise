
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.Security;

/**
 *  AES 加解密工具
 */
public class AES {

    static final String UTF8 = "UTF-8";
    static final String AES = "AES";
    static final String SHA1PRNG = "SHA1PRNG";
    static final String ECB_PKCS5 = "AES/ECB/PKCS5Padding";
    static final String CBC_PKCS5 = "AES/CBC/PKCS5Padding";
    static final String CBC_PKCS7 = "AES/CBC/PKCS7Padding";
    private static byte[] iv = {0x12, 0x22, 0x11, 0x56, 0x32, 0x33, 0x68, 0x75, 0x06, 0x55, 0x41, 0x42, 0x29, 0x09, 0x45, 0x61};
    private static Logger logger = LoggerFactory.getLogger(AES.class);


//    /**
//     * 默认AES加密模式
//     * @param text
//     * @param key
//     * @return
//     */
//    public static byte[] encrypt(String text, String key) {
//        try {
//            return doFinal(Cipher.ENCRYPT_MODE, CBC_PKCS5, text.getBytes(UTF8), key.getBytes(UTF8));
//        } catch (Exception e) {
//            logger.error("UnsupportedEncodingException text:{}", text);
//            logger.error(e);
//            return null;
//        }
//    }

//    /**
//     * 默认AES解密模式
//     * @param text
//     * @param key
//     * @return
//     */
//    public static String decrypt(byte[] text, String key) {
//        try {
//            return new String(doFinal(Cipher.DECRYPT_MODE, CBC_PKCS5, text, key.getBytes(UTF8)));
//        } catch (Exception e) {
//            logger.error("UnsupportedEncodingException text:{}", text);
//            logger.error(e);
//            return null;
//        }
//    }

//    private static byte[] doFinal(int encOrDec, String mode, byte[] text, byte[] key) {
//        initialize();
//        try {
//            SecureRandom rand=SecureRandom.getInstance(SHA1PRNG);
//            rand.setSeed(key);
//            KeyGenerator kGen = KeyGenerator.getInstance(AES);
//            kGen.init(128, rand);
//            SecretKey secretKey = kGen.generateKey();
//            SecretKeySpec keySpec = new SecretKeySpec(secretKey.getEncoded(), AES);
//            Cipher cipher = Cipher.getInstance(mode);
//            if (ECB_PKCS5.equals(mode)) {
//                cipher.init(encOrDec, keySpec);
//            } else if (CBC_PKCS5.equals(mode) || CBC_PKCS7.equals(mode)) {
//                IvParameterSpec ivSpec = new IvParameterSpec(iv);
//                cipher.init(encOrDec, keySpec, ivSpec);
//            } else {
//                throw new RuntimeException("unsupported encrypt mode!" + mode);
//            }
//            return cipher.doFinal(text);
//        } catch (GeneralSecurityException e) {
//            e.printStackTrace();
//            logger.error(e);
//            return null;
//        }
//    }

    protected static byte[] hex2byte(String hex) {
        int len = hex.length() / 2;
        if (len % 2 != 0) {
            throw new IllegalArgumentException();
        }
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++)
            result[i] = Integer.valueOf(hex.substring(2 * i, 2 * i + 2), 16).byteValue();
        return result;
    }


    public static void initialize(){
        if (initialized) return;
        Security.addProvider(new BouncyCastleProvider());
        initialized = true;
    }
    public static boolean initialized = false;


    public static String encrypt(String key, String input){
        try {
            return Base64.encode(doFinal(Cipher.ENCRYPT_MODE, hex2byte(key), input.getBytes(UTF8)));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            logger.error("AES UnsupportedEncodingException:", e);
            return null;
        }
    }

    public static String decrypt(String key, String input) {
        try {
            return new String(doFinal(Cipher.DECRYPT_MODE, hex2byte(key), Base64.decode(input)), UTF8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            logger.error("AES UnsupportedEncodingException:", e);
            return null;
        }
    }

    private static byte[] doFinal(Integer mode, byte[] key, byte[] text) {
        try {
            initialize();
            SecretKeySpec aeskey = new SecretKeySpec(key, 0, key.length, AES);
            IvParameterSpec cbcIv = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance(CBC_PKCS7);
            cipher.init(mode, aeskey, cbcIv);
            return cipher.doFinal(text);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            logger.error("AES GeneralSecurityException:", e);
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        /*
         * 此处使用AES-128-ECB加密模式，key需要为16位。
         */
        String cKey = "54865cf9888df35c82763d91033c3bbf";
        // 需要加密的字串
        String cSrc = "www.gowhere.so";
        System.out.println(cSrc);
        // 加密
        String enString = encrypt(cKey,cSrc);
        System.out.println("加密后的字串是：" + enString);

        String enS = "C14v5guHkIKKXgbiPGoaP97EaS7mKED4WZ1lskFBEcWq4fIFJ0wX8h/BKcczjgzzhZFSeENV/KXzJ1aYCIF+7rWQ9QtV1mbZaw91DA6A0P/UhY6zX6kcUmNZTkgFK3/G";

        // 解密
        String DeString = decrypt(cKey,enS);
        System.out.println("解密后的字串是：" + DeString);
    }

}
