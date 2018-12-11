import java.util.ArrayList;

public class ProofOfConcept {
    public static void main(String[] args) {
        ArrayList<Integer> lst = new ArrayList<>();

        for (int i = 0; i < 512001; i++) {
            lst.add(i);
        }

        long before = System.currentTimeMillis();
        sum(lst, 0, 0);
        long after = System.currentTimeMillis();

        System.out.println("Delta: " + (after - before));
    }

    public static int sum(ArrayList<Integer> lst, int idx, int acc) {
        if (idx == lst.size() - 1) {
            return acc;
        }
        else {
            acc += lst.get(idx);
            return sum(lst, idx + 1, acc);
        }
    }
}
