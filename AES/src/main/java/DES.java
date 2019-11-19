
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class DES {
    private static byte[] iv = {1,2,3,4,5,6,7,8};
    public static String encryptDES(String encryptString, String encryptKey) throws Exception {
        IvParameterSpec zeroIv = new IvParameterSpec(iv);
        SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
        byte[] encryptedData = cipher.doFinal(encryptString.getBytes());

        return Base_64.encode(encryptedData);
    }
    public static String decryptDES(String decryptString, String decryptKey) throws Exception {
        byte[] byteMi = Base_64.decode(decryptString);
        IvParameterSpec zeroIv = new IvParameterSpec(iv);
        SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
        byte decryptedData[] = cipher.doFinal(byteMi);

        return new String(decryptedData);
    }

    public static void main(String[] args) {
//		String a ="中国";
        String a ="{'emailName':'1905501537@qq.com','emailkey':'','nameOnCards':'','platform':'','emailPwd':'kplan123','param':'','validCode':'','pageSize':'','lastTime':'','appemailkey':'','password':'','clientId':'105','pageIndex':'','cmd':'','autoId':'','userId':null,'name':'','billId':'','ivdPassword':''}";
		String b ="D93eEqPsd0qMdmkSnWEhsPiC/wqT4/Gwizr44aKEiIY=";
        try {
            String aaa = DES.encryptDES(a, "yx51app1");
            System.out.println(aaa);
            System.out.println(DES.decryptDES(aaa, "yx51app1"));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

