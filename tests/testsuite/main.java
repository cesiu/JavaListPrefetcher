import java.util.LinkedList;

public class main{
    public static void main(String[] args){
        //System.out.println("0: "+args[0]+" 1: "+args[1]+" 2: "+args[2]+" 3: "+args[3]);
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


    }
}
