import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.BitSet;

public class Base64EncodeDecode {

    public static String encodeBase64(String input) {
        return Base64.getEncoder().encodeToString(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String decodeBase64(String input) {
        return new String(Base64.getDecoder().decode(input.getBytes(StandardCharsets.UTF_8)));
    }
}
