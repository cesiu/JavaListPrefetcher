import java.util.LinkedList;

public class main{
    public static void main(String[] args){
        //System.out.println("0: "+args[0]+" 1: "+args[1]+" 2: "+args[2]+" 3: "+args[3]);
        if(args[0].equals("i")){
            long acc = 0;
            for(int i = 0; i < 100;i++){
                LinkedList<Integer> linkedlist = setup.setupLinkedListInteger(args[1]);
                if(args[2].equals("clt")){
                    cyclist_list_traversal clt = new cyclist_list_traversal(linkedlist);
                    if(args[3].equals("i")){
                         long longa =clt.iterative();
                         acc += longa;
                    }else if(args[3].equals("r")){
                        acc =  acc+clt.recursive();
                    }
                }
            }
            System.out.println("100x: "+(acc/100.0));
        }else if(args[0].equals("p")){
            long acc = 0;
            for(int i = 0; i < 100;i++){
                LinkedList<pair<Integer, Integer>> linkedlistp = setup.setupLinkedListPair(args[1]);
                if(args[2].equals("clt")){
                    cyclist_list_traversal clt = new cyclist_list_traversal(linkedlistp);
                    if(args[3].equals("i")){
                        acc+=clt.iterative();
                    }else if(args[3].equals("r")){
                        acc+=clt.recursive();
                    }
                }
            }
            System.out.println("100x: "+(acc/100.0));

        }else if(args[0].equals("a")){
            int end = 100000000;
            int[] a = new int[end];
            System.out.println("Array");
            for(int i = 0; i < end; i++){
                a[i] = i;
            }
            long before = System.currentTimeMillis();
            int y;
            for(int i = 0; i < end-1;i++){
                y=a[i];  
            }
            
            long after = System.currentTimeMillis();
            System.out.println(after-before);

        }else{
            throw new RuntimeException();
        }


    }
}
