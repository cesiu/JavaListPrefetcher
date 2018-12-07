import java.util.LinkedList;

public class setup{
    public static LinkedList<Integer> setupLinkedListInteger(String arg){
        LinkedList<Integer> linkedIntegers = new LinkedList<Integer>();
        int x;
        if(arg.equals("1")){
            x = 512;
        }else if(arg.equals("2")){
            x = 524288;
        }else if(arg.equals("3")){
            x = 1048576*4;
        }else{
            throw new RuntimeException();
        }
        for(int i = 0; i < x; i++){
            linkedIntegers.add(i);
        }
        return linkedIntegers;
    }
    public static LinkedList<pair<Integer,Integer>> setupLinkedListPair(String arg){
        LinkedList<pair<Integer,Integer>> linkedIntegers = new LinkedList<pair<Integer,Integer>>();
        int x;
        if(arg.equals("1")){
            x = 512;
        }else if(arg.equals("2")){
            x = 524288;
        }else if(arg.equals("3")){
            x = 1048576;
        }else{
            throw new RuntimeException();
        }
        for(int i = 0; i < x; i++){
            linkedIntegers.add(new pair(i, i-1));
        }
        return linkedIntegers;
    }
}
