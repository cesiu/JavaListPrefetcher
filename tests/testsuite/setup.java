import java.util.LinkedList;
import java.util.ArrayList;

public class setup{
    public static LinkedList<Integer> setupLinkedListInteger(int arg){
        LinkedList<Integer> linkedIntegers = new LinkedList<Integer>();
        int x;
        if(arg == 1){
            x = 512;
        }else if(arg ==  2){
            x = 524288;
        }else if(arg == 3){
            x = 1048576;
        }else{
            System.out.println("err: arg = "+ arg);
            throw new RuntimeException();
        }
        for(int i = 0; i < x; i++){
            linkedIntegers.add(i);
        }
        return linkedIntegers;
    }
    public static LinkedList<pair<Integer,Integer>> setupLinkedListPair(int arg){
        LinkedList<pair<Integer,Integer>> linkedIntegers = new LinkedList<pair<Integer,Integer>>();
        int x;
        if(arg == 1){
            x = 512;
        }else if(arg == 2){
            x = 524288;
        }else if(arg == 3){
            x = 1048576;
        }else{
            System.out.println("err: arg = "+ arg);
            throw new RuntimeException();
        }
        for(int i = 0; i < x; i++){
            linkedIntegers.add(new pair(i, i-1));
        }
        return linkedIntegers;
    }
    public static ArrayList<Integer> setupArrayListInteger(int arg){
        int x;
        ArrayList<Integer> arraylist = new ArrayList<>();
        if(arg == 1){
            x = 512;
        }else if(arg ==  2){
            x = 524288;
        }else if(arg == 3){
            x = 1048576;
        }else{
            System.out.println("err: arg = "+ arg);
            throw new RuntimeException();
        }
        for(int i = 0; i < x; i++){
            arraylist.add(i);
        }
        return arraylist;
    }
}
