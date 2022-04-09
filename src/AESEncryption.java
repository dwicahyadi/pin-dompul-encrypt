import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import jakarta.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Scanner;

public class AESEncryption {
    public static String encrypt(String inputString, String encryptionKey, String
            modeCipher, String modeKey) throws Exception {
        byte[] array_byte = new byte[32];
        int i = 0;
        while (i < encryptionKey.length()) {
            array_byte[i] = (byte) encryptionKey.charAt(i);
            i++;
        }
        if (i < 32) {
            while (i < 32) {
                array_byte[i] = (byte) i;
                i++;
            }
        }

        Cipher c = Cipher.getInstance(modeCipher);
        Key key = new SecretKeySpec(array_byte, modeKey);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(inputString.getBytes());
        return DatatypeConverter.printBase64Binary(encVal);
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("masukan PIN Dompul:");
            String str = scanner.nextLine();
            String pin = encrypt(str, "EB3BBFC3B185CCB7DBD29B2B82AE5", "AES", "AES");
            System.out.println(pin);

            AESEncryption.main(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}