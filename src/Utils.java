import java.util.Arrays;
import java.util.Random;

public class Utils {

    public static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static int[] generateArray(int n, int threshold) {
        Random rnd = new Random();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = rnd.nextInt(threshold);
        }
        return arr;
    }

}
