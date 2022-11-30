package datastructure.Array;

import java.util.Arrays;

public class LoopQueue<E> implements Queue<E> {
    private E[] data;
    private int front,tail;
    private int size;

    public LoopQueue(int capacity){
        data = (E[])new Object[capacity+1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue(){
        this(10);
    }

    public int getCapacity(){
        return data.length-1;
    }
    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front==tail;
    }

    @Override
    public void enqueue(E e) throws IllegalAccessException {
        if((tail+1)%data.length==front)
            resize(getCapacity()*2);
        data[tail] = e;
        tail = (tail+1)%data.length;
        size++;
    }

    @Override
    public E dequeue() throws IllegalAccessException {
        if(isEmpty())
            throw new IllegalArgumentException("");
        E ret = data[front];
        data[front] = null;
        front = (front+1)%data.length;
        size--;
        if(size==getCapacity()/4&&getCapacity()/2!=0)
            resize(getCapacity()/2);
        return ret;
    }

    @Override
    public E getFront() throws IllegalAccessException {
        if(isEmpty())
            throw new IllegalArgumentException("");
        return data[front];
    }

    private void resize(int newCapacity){
        E[] newData = (E[])new Object[newCapacity+1];
        for(int i = 0 ;i < size ;i ++) {
            newData[i] = data[(i + front) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public String toString() {
        return "com.Array.LoopQueue{" +
                "data=" + Arrays.toString(data) +
                ", front=" + front +
                ", tail=" + tail +
                ", size=" + size +
                '}';
    }
}
