import java.net.SocketImpl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

public class StringMethods {
    public static boolean isPalindrome(String s) {
        var sb = new StringBuilder();
        for(var c: s.toCharArray()) {
            if(Character.isLetter(c)) {
                sb.append(c);
            }
        }
        var forward = sb.toString().toLowerCase();
        var backward = sb.reverse().toString().toLowerCase();
        return forward.equals(backward);
    }

    public static String reverseString(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    public static Date stringToDate(String date, String format) throws ParseException {
        var simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.parse(date);
    }

    public boolean isAnagram(String s1, String s2) {
        var l1 = s1.length();
        var l2 = s2.length();
        var arr1 = new int[256];
        var arr2 = new int[256];
        if(l1 != l2) {
            return false;
        }
        for(var i = 0; i < l1; i++) {
            arr1[s1.charAt(i)]++;
            arr2[s2.charAt(i)]++;
        }
        return Arrays.equals(arr1, arr2);
    }


}
