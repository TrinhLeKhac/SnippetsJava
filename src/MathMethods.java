import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MathMethods {

    public static int fibonacci(int n) {
        if(n <= 1) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    public static int factorial(int n) {
        var result = 1;
        for(var factor = 2; factor <= n; factor++) {
            result *= factor;
        }
        return result;
    }

    private static final double SPHERE_RADIUS_IN_KM = 6372.8;

    public static double findHaversineDistance(double latA, double longA, double latB, double longB) {
        if(!isValidLatitude(latA)
        || !isValidLatitude(latB)
        || !isValidLongitude(longA)
        || isValidLongitude(longB)) {
            throw new IllegalArgumentException();
        }

        // Calculate the latitude and longitude differences
        var latitudeDiff = Math.toRadians(latB - latA);
        var longitudeDiff = Math.toRadians(longB - longA);

        var latitudeA = Math.toRadians(latA);
        var latitudeB = Math.toRadians(latB);

        // Calculating the distance as per haversine formula
        var a = Math.pow(Math.sin(latitudeDiff / 2), 2) + Math.pow(Math.sin(longitudeDiff / 2), 2) * Math.cos(latitudeA) * Math.cos(latitudeB);
        var c = 2 * Math.asin(Math.sqrt(a));
        return SPHERE_RADIUS_IN_KM * c;
    }

    // Check for valid latitude value
    private static boolean isValidLatitude(double latitude) {
        return latitude >= -90 && latitude <= 90;
    }
    // Check for valid longitude value
    private static boolean isValidLongitude(double longitude) {
        return longitude >= -180 && longitude <= 180;
    }

    public static Integer[] performLottery(int numNumbers, int numbersToPick) {
        var numbers = new ArrayList<Integer>();
        for(var i = 0; i < numNumbers; i++) {
            numbers.add(i+1);
        }
        Collections.shuffle(numbers);
        return numbers.subList(0, numbersToPick).toArray(new Integer[numbersToPick]);
    }

    public static int calculateLuhnChecksum(long num) {
        if(num < 0) {
            throw new IllegalArgumentException("Non-negative numbers only");
        }
        final var numStr = String.valueOf(num);

        var sum = 0;
        var isOddPosition = true;

        for(var i = numStr.length() - 1; i >= 0; i--) {
            final var digit = Integer.parseInt(Character.toString(numStr.charAt(i)));
            final var substitudeDigit = (isOddPosition ? 2 : 1) * digit;

            final var tensPlaceDigit = substitudeDigit / 10;
            final var onesPlaceDigit = substitudeDigit % 10;
            sum += tensPlaceDigit + onesPlaceDigit;

            isOddPosition = !isOddPosition;
        }
        final var checksumDigit = (10 - (sum % 10)) % 10;
        return checksumDigit;
    }

    public static int gcd(int a, int b) {
        if( b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static boolean isPrime(int number) {
        if(number < 3) {
            return true;
        }

        if(number % 2 == 0) {
            return false;
        }

        for(var i = 3; i * i <= number; i += 2) {
            if(number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static String toBinary(long naturalNumber) {
        if(naturalNumber < 0) {
            throw new NumberFormatException("Negative Integer, only accepts positive integers");
        }
        if(naturalNumber == 0) {
            return "0";
        }
        final Stack<Long> binaryBits = Stream.iterate(naturalNumber, n -> n > 0, n -> n / 2).map(n -> n % 2)
                .collect(Stack::new, Stack::push, Stack::addAll);
        return Stream.generate(binaryBits::pop).limit(binaryBits.size()).map(String::valueOf).collect(Collectors.joining());
    }

    public static Long fromBinary(String binary) {
        binary.chars().filter(c -> c != '0' && c != '1').findFirst().ifPresent(in -> {
            throw new NumberFormatException(
                    "Binary string contains values other than '0' and '1'"
            );
        });
        return IntStream.range(0, binary.length())
                .filter(in -> binary.charAt(binary.length() - 1 - in) == '1')
                .mapToLong(in -> ((long) 0b1 << in)).sum();
    }
}
