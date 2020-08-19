/**
 * @author q
 * @create 2020-08-16 20:53
 */
public class ArrayDeque<T> {
    T[] items;
    int size;
    int front;
    int rear;

    public ArrayDeque(){
        items = (T []) new Object[5];
        size = 0;
        front = 0;
        rear = 0;
    }

    public ArrayDeque(ArrayDeque other){
        items = (T []) other.items;
        size = other.size;
        front = other.front;
        rear = other.rear;
    }

    //add to first
    public void addFirst(T item){
        size += 1;
        if (front + rear == items.length) resize();
        front = (front - 1 + items.length) % items.length;
        items[front] = item;
    }

    //add to last
    public void addLast(T item){
        size += 1;
        if (front + rear == items.length) resize();
        items[rear] = item;
        rear = (rear + 1 + items.length) % items.length;
    }

    //resize
    public void resize(){
        int RFRACTOR = 2;
        T[] a = (T []) new Object[size * RFRACTOR];
        System.arraycopy(items, 0, a, 0, rear);
        front = a.length - (items.length - front);
        System.arraycopy(items, rear+1, a, front, (items.length - rear - 1));
        items = a;
    }

    //is empty?
    public boolean isEmpty(){
        if (front == rear) return true;
        return false;
    }

    //remove first
    public void removeFirst(){
        if (isEmpty() == true) {
            System.out.println("is empty");
            return;
        }
        size -= 1;
        items[front] = null;
        front = (front + 1)%items.length;
    }

    //remove last
    public void removeLast(){
        if (isEmpty() == true) {
            System.out.println("is empty");
            return;
        }
        size -= 1;
        rear = (rear - 1 + items.length)%items.length;
        items[rear] = null;
    }

    //get element
    public T get(int index){
        if (isEmpty() == true){
            System.out.println("is empty");
            return null;
        }
        return items[(front + index)%items.length];
    }

    //print
    public void printDeque(){
        if (isEmpty() == true) System.out.println("is empty");
        for (int i = 0; i < size; i++){
            System.out.print(" " + items[(front + i)%items.length]);
        }
        System.out.println();
    }


    public static void main(String[] args) {
        ArrayDeque<String> ad = new ArrayDeque<>();
        ad.addFirst("a");
        ad.addFirst("b");
        ad.addLast("c");
        ad.addLast("d");
        ad.addLast("e");
        ad.printDeque();
        System.out.println(ad.front);
        System.out.println(ad.rear);
        System.out.println(ad.size);
        System.out.println("*************************");

        ArrayDeque<String> ad1 = new ArrayDeque<>(ad);
        ad1.removeFirst();
        ad1.removeLast();
        System.out.println(ad1.front);

        ad1.printDeque();

    }

}
