import java.util.LinkedList;

public class cyclist_list_traversal<T>{
    LinkedList<T> ll;
    public cyclist_list_traversal(LinkedList<T> linkedlist){
        ll = linkedlist;
    }

    public long iterative(){
        long before = System.currentTimeMillis();
        while(ll.poll() != null){}
        long after = System.currentTimeMillis();

        return after - before;
    }
    public long recursive(){
        long before = System.currentTimeMillis();
        getNext();
        long after = System.currentTimeMillis();

        return after - before;
    }
    private void getNext(){
        if(ll.poll() == null){
            return;
        }
        getNext();
    }
}
