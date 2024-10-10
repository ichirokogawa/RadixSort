import java.util.Arrays;
import java.util.Random;

public class RadixSort {

    public static void main(String[] args) {

        var arr = generateRandomArray(0, 1, 9999);

        System.out.println("Ori:");
        System.out.println(Arrays.toString(arr));

        radixSort(arr);

        System.out.println("Dest:");
        System.out.println(Arrays.toString(arr));
    }

    /**
     * @param arr 非負の整数Array
     */
    private static void radixSort(int[] arr) {
        if (arr.length <= 2) {
            return;
        }

        var max = getMax(arr);
        for (var exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(arr, exp);
        }
    }

    @SuppressWarnings("SameParameterValue")
    private static int[] generateRandomArray(int size, int min, int max) {
        if (size < 0){
            throw new IllegalArgumentException("size cannot be less than 0");
        }

        if (min > max){
            throw new IllegalArgumentException("min cannot be greater than max");
        }

        if (min < 0) {
            throw new IllegalArgumentException("min cannot be less than 0");
        }

        var random = new Random();
        var arr = new int[size];
        for (var i = 0; i < size; i++) {
            arr[i] = random.nextInt(min, max + 1);
        }
        return arr;
    }


    private static int getMax(int[] arr) {
        var max = arr[0];
        for (var num : arr) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    private static void countingSortByDigit(int[] arr, int exp) {
        var n = arr.length;
        var output = new int[n];
        var count = new int[10];

        for (int j : arr) {
            var digit = (j / exp) % 10;
            ++count[digit];
        }

        for (var i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (var i = n - 1; i >= 0; i--) {
            var digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            --count[digit];
        }

        System.arraycopy(output, 0, arr, 0, n);
    }
}
