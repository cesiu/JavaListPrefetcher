import java.util.LinkedList;

public class main{
    public static void main(String[] args){
        System.out.println("0: "+args[0]+" 1: "+args[1]+" 2: "+args[2]+" 3: "+args[3]);
        if(args[0].equals("i")){
            LinkedList<Integer> linkedlist = setup.setupLinkedListInteger(args[1]);
            if(args[2].equals("clt")){
                cyclist_list_traversal clt = new cyclist_list_traversal(linkedlist);
                if(args[3].equals("i")){
                    System.out.println(clt.iterative());
                }else if(args[3].equals("r")){
                    System.out.println(clt.recursive());
                }
            }
        }else if(args[0].equals("p")){
            LinkedList<pair<Integer, Integer>> linkedlistp = setup.setupLinkedListPair(args[1]);
            if(args[2].equals("clt")){
                cyclist_list_traversal clt = new cyclist_list_traversal(linkedlistp);
                if(args[3].equals("i")){
                    System.out.println(clt.iterative());
                }else if(args[3].equals("r")){
                    System.out.println(clt.recursive());
                }
            }

        }else{
            throw new RuntimeException();
        }


    }
}
