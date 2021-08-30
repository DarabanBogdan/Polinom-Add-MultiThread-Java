import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Queue {

    Node head;
    Node last;

    static class Node {

        int data;
        int power;
        Node next;
        Node prev;

        // Constructor
        Node(int d, int power) {
            data = d;
            this.power = power;
            next = null;
            prev = null;
        }
    }

    public Node getFirst(){
        return this.head;

    }
    public Node getLast(){
        return this.last;
    }

    public void insert(int data, int power) {

        if (data == 0)
            data = 1;
        Node new_node = new Node(data, power);
        new_node.next = null;

        if (this.last == null) {
            this.head = new_node;
            this.last = new_node;
        } else if(this.last.next!=null){
            Node buff =this.last;
            this.last.next.prev=buff;
            this.last=new_node;
            buff.prev=this.last;
            this.last.next=buff;

        }
        else
        {
            this.head=this.last;
            this.head.prev=new_node;
            this.last=new_node;
            new_node.next=this.head;
        }


    }

    public Node remove(){

        if(this.head!=null) {
            Node buff = this.head;
            this.head = this.head.prev;
            if(this.head==null && this.last!=null)
                this.head=this.last;


            return buff;
        }
        else
            return null;


    }






}