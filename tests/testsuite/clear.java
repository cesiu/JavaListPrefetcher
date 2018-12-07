import java.util.ArrayList;
public class clear{
    public static void main(String[] args){
        ArrayList<Integer> ar = new ArrayList<>();
        for(int i = 0; i<1000000; i++){
            ar.add(100000+i);
        }
    }
}
