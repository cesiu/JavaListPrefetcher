import java.util.LinkedList;
import java.util.ArrayList;

public class main{
    public static void main(String[] args){
        System.out.println("0: "+args[0]);//+" 1: "+args[1]+" 2: "+args[2]+" 3: "+args[3]);
        if(args[0].equals("ll")){
        int j = 0;
            for(j = 1; j < 4; j++){
                long acc = 0;
                for(int i = 0; i < 100;i++){
                    LinkedList<Integer> linkedlist = setup.setupLinkedListInteger(j);
                        cyclist_list_traversal clt = new cyclist_list_traversal(linkedlist);
                            long longa =clt.iterative();
                            acc += longa;
                }
                if(j == 1){
                    System.out.println("CLT Ints 256 iterative 100x Average: "+(acc/100.0));
                }
                if(j == 2){
                    System.out.println("CLT Ints 500k iterative 100x Average: "+(acc/100.0));
                }
                if(j == 3){
                    System.out.println("CLT Ints 1M iterative 100x Average: "+(acc/100.0));
                }
            }
            for(j = 1; j < 4; j++){
                long acc = 0;
                for(int i = 0; i < 100;i++){
                    LinkedList<Integer> linkedlist = setup.setupLinkedListInteger(j);
                        cyclist_list_traversal clt = new cyclist_list_traversal(linkedlist);
                            long longa =clt.recursive();
                            acc += longa;
                }
                if(j == 1){
                    System.out.println("CLT Ints 256 recursive 100x Average: "+(acc/100.0));
                }
                if(j == 2){
                    System.out.println("CLT Ints 500k recursive 100x Average: "+(acc/100.0));
                }
                if(j == 3){
                    System.out.println("CLT Ints 1M recursive 100x Average: "+(acc/100.0));
                }
            }
            for(j = 1; j < 4; j++){
                long acc = 0;
                for(int i = 0; i < 100;i++){
                       LinkedList<pair<Integer, Integer>> linkedlistp = setup.setupLinkedListPair(j);
                        cyclist_list_traversal clt = new cyclist_list_traversal(linkedlistp);
                            long longa =clt.iterative();
                            acc += longa;
                }
                if(j == 1){
                    System.out.println("CLT Pair 256 iterative 100x Average: "+(acc/100.0));
                }
                if(j == 2){
                    System.out.println("CLT Pair 500k iterative 100x Average: "+(acc/100.0));
                }
                if(j == 3){
                    System.out.println("CLT Pair 1M Iterative 100x Average: "+(acc/100.0));
                }
            }
            for(j = 1; j < 4; j++){
                long acc = 0;
                for(int i = 0; i < 100;i++){
                       LinkedList<pair<Integer, Integer>> linkedlistp = setup.setupLinkedListPair(j);
                        cyclist_list_traversal clt = new cyclist_list_traversal(linkedlistp);
                            long longa =clt.recursive();
                            acc += longa;
                }
                if(j == 1){
                    System.out.println("CLT Pair 256 recursive 100x Average: "+(acc/100.0));
                }
                if(j == 2){
                    System.out.println("CLT Pair 500k recursive 100x Average: "+(acc/100.0));
                }
                if(j == 3){
                    System.out.println("CLT Pair 1M recursive 100x Average: "+(acc/100.0));
                }
            }
        }else if(args[0].equals("ar")){
            new cyclist_jayhorn_array_tests();
        }else if(args[0].equals("al")){
            ArrayList<Integer> arr = new ArrayList<Integer>();
            for(int i = 0; i < 1048577;i++){
                arr.add(i);
            }
            new cyclist_jayhorn_arraylist_tests(arr);
        }else if(args[0].equals("ll2")){
        int j = 0;
            for(j = 1; j < 4; j++){
                long acc = 0;
                for(int i = 0; i < 100;i++){
                    LinkedList<Integer> linkedlist = setup.setupLinkedListInteger(j);
                        cyclist_list_traversal clt = new cyclist_list_traversal(linkedlist);
            long before = System.currentTimeMillis();
                            clt.evenodd(j);
            long after = System.currentTimeMillis();
                            acc += (after - before);
                }
                if(j == 1){
                    System.out.println("CLT Ints 256 iterative 100x Average: "+(acc/100.0));
                }
                if(j == 2){
                    System.out.println("CLT Ints 500k iterative 100x Average: "+(acc/100.0));
                }
                if(j == 3){
                    System.out.println("CLT Ints 1M iterative 100x Average: "+(acc/100.0));
                }
            }
 

        }

    }
}
