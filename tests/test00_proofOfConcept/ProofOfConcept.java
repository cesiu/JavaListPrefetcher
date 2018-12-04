import csc515.plugin.*;

import java.util.LinkedList;

public class ProofOfConcept {
    public static void main(String[] args) {
        LinkedList<Integer> lst = new LinkedList<>();

        for (int i = 0; i < 512000; i++) {
            lst.add(i);
        }

        long before = System.currentTimeMillis();
        sum(lst, 0);
        long after = System.currentTimeMillis();

        System.out.println("Delta: " + (after - before));
    }

    public static int sum(LinkedList<Integer> lst, int acc) {
        if (lst.size() == 0) {
            return acc;
        }
        else {
            acc += lst.poll();
            return sum(lst, acc);
        }
    }
}
