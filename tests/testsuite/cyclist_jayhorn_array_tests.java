
public class cyclist_jayhorn_array_tests{
    int[] array = new int[1048576];
    int x;
    public void cyclist_jayhorn_arraylist_test(){
        for(int i = 0; i < 1048576; i++){
            array[i] = i;
        }
    }
    public void testCLTi(){
        long acc = 0;
        for(int j = 0; j < 100;j++){
            long before = System.currentTimeMillis();
            for(int i = 0; i < 512; i++){
                x = array[i];
            }
            long after = System.currentTimeMillis();
            acc += (after - before);
        }
        System.out.println("CLT array Integers 256 iterative 100x Average: "+(acc/100.0));
        acc = 0;
        for(int j = 0; j < 100;j++){
            long before = System.currentTimeMillis();
            for(int i = 0; i < 524288; i++){
                x = array[i];
            }
            long after = System.currentTimeMillis();
            acc += (after - before);
        }
        System.out.println("CLT array Integers 500k iterative 100x Average: "+(acc/100.0));
        acc = 0;
        for(int j = 0; j < 100;j++){
            long before = System.currentTimeMillis();
            for(int i = 0; i < 1048576; i++){
                x = array[i];
            }
            long after = System.currentTimeMillis();
            acc += (after - before);
        }
        System.out.println("CLT array Integers 1M iterative 100x Average: "+(acc/100.0));
    }
    public void testCLTr(){
        long acc = 0;
        for(int j = 0; j < 100;j++){
            long before = System.currentTimeMillis();
            recurse(511);
            long after = System.currentTimeMillis();
            acc += (after - before);
        }
        System.out.println("CLT array Integers 256 recursive 100x Average: "+(acc/100.0));
        acc = 0;
        for(int j = 0; j < 100;j++){
            long before = System.currentTimeMillis();
            recurse(524287);
            long after = System.currentTimeMillis();
            acc += (after - before);
        }
        System.out.println("CLT array Integers 500k recursive 100x Average: "+(acc/100.0));
        acc = 0;
        for(int j = 0; j < 100;j++){
            long before = System.currentTimeMillis();
            recurse(1048575);
            long after = System.currentTimeMillis();
            acc += (after - before);
        }
        System.out.println("CLT array Integers 1M recursive 100x Average: "+(acc/100.0));
    }
    public void recurse(int len){
        if(len == 0){
            return;
        }
        x = array[len];
        recurse(len - 1);
    }

    public void testCDTi(){
        long acc = 0;
        for(int j = 0; j < 100;j++){
            long before = System.currentTimeMillis();
            for(int i = 0; i < 512; i+=2){
                x = array[i];
                x = array[i+1];
            }
            long after = System.currentTimeMillis();
            acc += (after - before);
        }
        System.out.println("CDT array Integers 256 iterative 100x Average: "+(acc/100.0));
        acc = 0;
        for(int j = 0; j < 100;j++){
            long before = System.currentTimeMillis();
            for(int i = 0; i < 524288; i+=2){
                x = array[i];
                x = array[i+1];
            }
            long after = System.currentTimeMillis();
            acc += (after - before);
        }
        System.out.println("CDT array Integers 500k iterative 100x Average: "+(acc/100.0));
        acc = 0;
        for(int j = 0; j < 100;j++){
            long before = System.currentTimeMillis();
            for(int i = 0; i < 1048576; i+=2){
                x = array[i];
                x = array[i+1];
            }
            long after = System.currentTimeMillis();
            acc += (after - before);
        }
        System.out.println("CDT array Integers 1M iterative 100x Average: "+(acc/100.0));
    }
    public void testCDTr(){
        long acc = 0;
        for(int j = 0; j < 100;j++){
            long before = System.currentTimeMillis();
            recurse2(511);
            long after = System.currentTimeMillis();
            acc += (after - before);
        }
        System.out.println("CDT array Integers 256 recursive 100x Average: "+(acc/100.0));
        acc = 0;
        for(int j = 0; j < 100;j++){
            long before = System.currentTimeMillis();
            recurse2(524287);
            long after = System.currentTimeMillis();
            acc += (after - before);
        }
        System.out.println("CDT array Integers 500k recursive 100x Average: "+(acc/100.0));
        acc = 0;
        for(int j = 0; j < 100;j++){
            long before = System.currentTimeMillis();
            recurse2(1048575);
            long after = System.currentTimeMillis();
            acc += (after - before);
        }
        System.out.println("CDT array Integers 1M recursive 100x Average: "+(acc/100.0));
    }
    public void recurse2(int len){
        if(len <= 1){
            return;
        }
        x = array[len];
        x = array[len-1];
        recurse2(len - 2);
    }
    public void testReverse(){
        int[] newArr;
        long acc = 0;
        for(int j = 0; j < 100;j++){
            newArr = new int[512];
            long before = System.currentTimeMillis();
            for(int i = 511; i > 0; i--){
                x = array[i];
                newArr[i] = x;
            }
            long after = System.currentTimeMillis();
            acc += (after - before);
        }
        System.out.println("CRev array Integers 256 iterative 100x Average: "+(acc/100.0));
        acc = 0;
        for(int j = 0; j < 100;j++){
            newArr = new int[524188];
            long before = System.currentTimeMillis();
            for(int i = 524187; i > 0; i--){
                x = array[i];
                newArr[i] = x;
            }
            long after = System.currentTimeMillis();
            acc += (after - before);
        }
        System.out.println("CRev array Integers 500k iterative 100x Average: "+(acc/100.0));
        acc = 0;
        for(int j = 0; j < 100;j++){
            newArr = new int[1048576];
            long before = System.currentTimeMillis();
            for(int i = 1048576-1; i > 0; i--){
                x = array[i];
                newArr[i] = x;
            }
            long after = System.currentTimeMillis();
            acc += (after - before);
        }
        System.out.println("CRev array Integers 1M iterative 100x Average: "+(acc/100.0));
    }
    
    public void testEvenOdd(){
        long acc = 0;
        for(int j = 0; j <100; j++){
            long before = System.currentTimeMillis();
            odd(511);
            long after = System.currentTimeMillis();
            acc += (after - before);
        }
        System.out.println("Jayhorn EvenOdd array Integers 512 iterative 100x Average: "+(acc/100.0));
        acc = 0;
        for(int j = 0; j <100; j++){
            long before = System.currentTimeMillis();
            odd(524187);
            long after = System.currentTimeMillis();
            acc += (after - before);
        }
        System.out.println("Jayhorn EvenOdd array Integers 500k iterative 100x Average: "+(acc/100.0));
        acc = 0;
        for(int j = 0; j <100; j++){
            long before = System.currentTimeMillis();
            odd(1028575);
            long after = System.currentTimeMillis();
            acc += (after - before);
        }
        System.out.println("Jayhorn EvenOdd array Integers 1M iterative 100x Average: "+(acc/100.0));
        
    }
    public void odd(int pos){
        if(pos == 0){
            return;
        }
        x = array[pos];
        even(pos-1);
    }
    public void even(int pos){
        if(pos == 0){
            return;
        }
        x = array[pos];
        odd(pos-1);

    }
}
