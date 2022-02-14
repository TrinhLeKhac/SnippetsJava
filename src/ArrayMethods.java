import java.util.Arrays;
import java.util.Random;

public class ArrayMethods {

    public static <T> T[] arrayConcat(T[] first, T[] second) {
        var result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    public static <T> T[] nArrayConcat(T[] first, T[]... rest) {
        var totalLength = first.length;
        for(var arr: rest) {
            totalLength += arr.length;
        }
        var result = Arrays.copyOf(first, totalLength);
        var offset = first.length;
        for(var arr: rest) {
            System.arraycopy(arr, 0, result, offset, arr.length);
            offset += arr.length;
        }
        return result;
    }

    public static <T> boolean allEqual(T[] arr) {
        return Arrays.stream(arr).distinct().count() == 1;
    }

    public static int findMax(int[] arr) {
        return Arrays.stream(arr).reduce(Integer.MIN_VALUE, Integer::max);
    }

    public static void main(String[] args) {
        int[] arr = Utils.generateArray(10, 100);
        Utils.printArray(arr);
        int maxValue = findMax(arr);
        System.out.println(maxValue);
    }
}
