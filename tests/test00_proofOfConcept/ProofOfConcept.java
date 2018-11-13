import csc515.plugin.*;

public class ProofOfConcept {
    public static void main(String[] args) {
        int[] arr = new int[512000];
        arr[4096] = 8;

        long before = System.currentTimeMillis();
        lambda(arr, 0);
        long after = System.currentTimeMillis();

        System.out.println("Delta: " + (after - before));
    }

    public static void lambda(int[] arr, int idx) {
        arr[idx] = (int)(Math.pow(Math.sqrt(5), idx));
        // int y = arr[idx + 1];

        if (idx < arr.length - 2) {
            lambda(arr, idx + 1);
        }
    }
}
