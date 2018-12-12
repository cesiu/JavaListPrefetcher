import java.util.ArrayList;

public class cyclist_jayhorn_arraylist_tests<T>{
    ArrayList<T> array;
    T x;
    public cyclist_jayhorn_arraylist_tests(ArrayList<T> arr){
        array = arr;
        testCLTi();
        testCLTr();
        testCDTi();
        testCDTr();
        testReverse();
        testEvenOdd();
    }
    public void testCLTi(){
        long acc = 0;
        for(int j = 0; j < 100;j++){
            long before = System.currentTimeMillis();
            for(int i = 0; i < 512; i++){
                x = array.get(i);
            }
            long after = System.currentTimeMillis();
            acc += (after - before);
        }
        System.out.println("CLT ArrayList Integers 256 iterative 100x Average: "+(acc/100.0));
        acc = 0;
        for(int j = 0; j < 100;j++){
            long before = System.currentTimeMillis();
            for(int i = 0; i < 524288; i++){
                x = array.get(i);
            }
            long after = System.currentTimeMillis();
            acc += (after - before);
        }
        System.out.println("CLT ArrayList Integers 500k iterative 100x Average: "+(acc/100.0));
        acc = 0;
        for(int j = 0; j < 100;j++){
            long before = System.currentTimeMillis();
            for(int i = 0; i < 1048576; i++){
                x = array.get(i);
            }
            long after = System.currentTimeMillis();
            acc += (after - before);
        }
        System.out.println("CLT ArrayList Integers 1M iterative 100x Average: "+(acc/100.0));
    }
    public void testCLTr(){
        long acc = 0;
        for(int j = 0; j < 100;j++){
            long before = System.currentTimeMillis();
            recurse(511);
            long after = System.currentTimeMillis();
            acc += (after - before);
        }
        System.out.println("CLT ArrayList Integers 256 recursive 100x Average: "+(acc/100.0));
        acc = 0;
        for(int j = 0; j < 100;j++){
            long before = System.currentTimeMillis();
            recurse(524287);
            long after = System.currentTimeMillis();
            acc += (after - before);
        }
        System.out.println("CLT ArrayList Integers 500k recursive 100x Average: "+(acc/100.0));
        acc = 0;
        for(int j = 0; j < 100;j++){
            long before = System.currentTimeMillis();
            recurse(1048575);
            long after = System.currentTimeMillis();
            acc += (after - before);
        }
        System.out.println("CLT ArrayList Integers 1M recursive 100x Average: "+(acc/100.0));
    }
    public void recurse(int len){
        if(len == 0){
            return;
        }
        x = array.get(len);
        recurse(len - 1);
    }

    public void testCDTi(){
        long acc = 0;
        for(int j = 0; j < 100;j++){
            long before = System.currentTimeMillis();
            for(int i = 0; i < 512; i+=2){
                x = array.get(i);
                x = array.get(i+1);
            }
            long after = System.currentTimeMillis();
            acc += (after - before);
        }
        System.out.println("CDT ArrayList Integers 256 iterative 100x Average: "+(acc/100.0));
        acc = 0;
        for(int j = 0; j < 100;j++){
            long before = System.currentTimeMillis();
            for(int i = 0; i < 524288; i+=2){
                x = array.get(i);
                x = array.get(i+1);
            }
            long after = System.currentTimeMillis();
            acc += (after - before);
        }
        System.out.println("CDT ArrayList Integers 500k iterative 100x Average: "+(acc/100.0));
        acc = 0;
        for(int j = 0; j < 100;j++){
            long before = System.currentTimeMillis();
            for(int i = 0; i < 1048576; i+=2){
                x = array.get(i);
                x = array.get(i+1);
            }
            long after = System.currentTimeMillis();
            acc += (after - before);
        }
        System.out.println("CDT ArrayList Integers 1M iterative 100x Average: "+(acc/100.0));
    }
    public void testCDTr(){
        long acc = 0;
        for(int j = 0; j < 100;j++){
            long before = System.currentTimeMillis();
            recurse2(511);
            long after = System.currentTimeMillis();
            acc += (after - before);
        }
        System.out.println("CDT ArrayList Integers 256 recursive 100x Average: "+(acc/100.0));
        acc = 0;
        for(int j = 0; j < 100;j++){
            long before = System.currentTimeMillis();
            recurse2(524287);
            long after = System.currentTimeMillis();
            acc += (after - before);
        }
        System.out.println("CDT ArrayList Integers 500k recursive 100x Average: "+(acc/100.0));
        acc = 0;
        for(int j = 0; j < 100;j++){
            long before = System.currentTimeMillis();
            recurse2(1048575);
            long after = System.currentTimeMillis();
            acc += (after - before);
        }
        System.out.println("CDT ArrayList Integers 1M recursive 100x Average: "+(acc/100.0));
    }
    public void recurse2(int len){
        if(len <= 1){
            return;
        }
        x = array.get(len);
        x = array.get(len-1);
        recurse2(len - 2);
    }
    public void testReverse(){
        ArrayList<T> newArr;
        long acc = 0;
        for(int j = 0; j < 100;j++){
            newArr = new ArrayList<T>();
            long before = System.currentTimeMillis();
            for(int i = 511; i > 0; i--){
                x = array.get(i);
                newArr.add(x);
            }
            long after = System.currentTimeMillis();
            acc += (after - before);
        }
        System.out.println("CRev ArrayList Integers 256 iterative 100x Average: "+(acc/100.0));
        acc = 0;
        for(int j = 0; j < 100;j++){
            newArr = new ArrayList<T>();
            long before = System.currentTimeMillis();
            for(int i = 524187; i > 0; i--){
                x = array.get(i);
                newArr.add(x);
            }
            long after = System.currentTimeMillis();
            acc += (after - before);
        }
        System.out.println("CRev ArrayList Integers 500k iterative 100x Average: "+(acc/100.0));
        acc = 0;
        for(int j = 0; j < 100;j++){
            newArr = new ArrayList<T>();
            long before = System.currentTimeMillis();
            for(int i = 1048576-1; i > 0; i--){
                x = array.get(i);
                newArr.add(x);
            }
            long after = System.currentTimeMillis();
            acc += (after - before);
        }
        System.out.println("CRev ArrayList Integers 1M iterative 100x Average: "+(acc/100.0));
    }
    
    public void testEvenOdd(){
        long acc = 0;
        for(int j = 0; j <100; j++){
            long before = System.currentTimeMillis();
            odd(511);
            long after = System.currentTimeMillis();
            acc += (after - before);
        }
        System.out.println("Jayhorn EvenOdd ArrayList Integers 512 iterative 100x Average: "+(acc/100.0));
        acc = 0;
        for(int j = 0; j <100; j++){
            long before = System.currentTimeMillis();
            odd(524187);
            long after = System.currentTimeMillis();
            acc += (after - before);
        }
        System.out.println("Jayhorn EvenOdd ArrayList Integers 500k iterative 100x Average: "+(acc/100.0));
        acc = 0;
        for(int j = 0; j <100; j++){
            long before = System.currentTimeMillis();
            odd(1028575);
            long after = System.currentTimeMillis();
            acc += (after - before);
        }
        System.out.println("Jayhorn EvenOdd ArrayList Integers 1M iterative 100x Average: "+(acc/100.0));
        
    }
    public void odd(int pos){
        if(pos == 0){
            return;
        }
        x = array.get(pos);
        even(pos-1);
    }
    public void even(int pos){
        if(pos == 0){
            return;
        }
        x = array.get(pos);
        odd(pos-1);

    }


}
