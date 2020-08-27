
/**
 * @author q
 * @create 2020-08-16 16:01
 * circle LLD
 */
public class LinkedListDeque<T> implements Deque<T> {
    //define the node
    public class IntNode{
        public IntNode prev;
        public T item;
        public IntNode next;

        //initialize
        public IntNode(T i){
            item = i;
        }
        public IntNode(){}
    }

    //define field of LLD
    private IntNode sentinel = new IntNode();
    private int size;

    //initialize
    public LinkedListDeque(){
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }
    public LinkedListDeque(LinkedListDeque other){
        this();
        IntNode p = other.getSentinel().next;
        while(p != other.getSentinel()){
            this.addLast(p.item);
            p = p.next;
        }
    }

    //add to the front
    public void addFirst(T item){
        size += 1;
        IntNode p = new IntNode(item);
        p.next = sentinel.next;
        sentinel.next.prev = p;
        p.prev = sentinel;
        sentinel.next = p;
    }

    //add to the last
    public void addLast(T item){
        size += 1;
        IntNode p = new IntNode(item);
        p.prev = sentinel.prev;
        sentinel.prev.next = p;
        p.next = sentinel;
        sentinel.prev = p;
    }

    //true is empty
    public boolean isEmpty(){
        if (sentinel.next == sentinel) return true;
        return false;
    }

    //return size
    public int size(){
        return size;
    }

    //print all elements
    public void printDeque(){
        if (isEmpty() == true) {
            System.out.println("no element");
            return;
        }
        IntNode p = sentinel.next;
        while(p != sentinel){
            System.out.print(" "+ p.item);
            p = p.next;
        }
        System.out.println();
    }

    //Removes and returns the item at the front of the deque. If no such item exists, returns null.
    public T removeFirst(){
        if (isEmpty() == true) return null;
        size -= 1;
        IntNode p = sentinel.next;
        sentinel.next = p.next;
        p.next.prev = sentinel;
        return p.item;
    }

    //Removes and returns the item at the back of the deque. If no such item exists, returns null.
    public T removeLast(){
        if (isEmpty() == true) return null;
        size -= 1;
        IntNode p = sentinel.prev;
        sentinel.prev = p.prev;
        p.prev.next = sentinel;
        return p.item;
    }

    //Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    // If no such item exists, returns null.
    public T get(int index){
        if (index+1 > size()) return null;
        IntNode p = sentinel;
        for (int i=0; i <= index; i++){
            p = p.next;
        }
        return p.item;
    }

    //get sentinel
    public IntNode getSentinel() {
        return sentinel;
    }
}
