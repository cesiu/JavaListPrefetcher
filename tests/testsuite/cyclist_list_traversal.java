import java.util.LinkedList;

public class cyclist_list_traversal<T>{
    LinkedList<T> ll;
    public cyclist_list_traversal(LinkedList<T> linkedlist){
        ll = linkedlist;
    }

    public long iterative(){
        long before = System.currentTimeMillis();
        T x = ll.poll();
        while(x != null){
            x = ll.poll();
        }
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
        T x = ll.poll();
        if(x == null){
            return;
        }
        getNext();
    }
    public void evenodd(int num){
        if(num == 1){
            even(512);
        }
        else if(num == 2){
            even(524288);
        }
        else if(num == 3){
            even(1048576);
        }

    }
    public void odd(int pos){
        if(pos == 0){
            return;
        }
        T x = ll.poll();
        even(pos-1);
    }
    public void even(int pos){
        if(pos == 0){
            return;
        }
        T x = ll.poll();
        odd(pos-1);

    }
}
