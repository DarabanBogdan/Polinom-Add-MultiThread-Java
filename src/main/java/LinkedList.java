import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class LinkedList {

    Node head;

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

    public Node finOneBefore(int power){

        Node curent=head;
        while(curent.next!=null){
            if(curent.power<power)
                return curent;
            curent=curent.next;
        }
        return curent;
    }

    public int findOne(int power){

        Node curent=head;
        while(curent!=null){
            if(curent.power==power)
                return curent.data;
            curent=curent.next;
        }
        return -1;

    }

    public void insert(int data, int power) {
        // Create a new node with given data
        if(data==0)
            data=1;
        Node new_node = new Node(data, power);
        new_node.next = null;

        // If the Linked List is empty,
        // then make the new node as head
        if (head == null) {
            head = new_node;
        } else {
            // Else traverse till the last node
            // and insert the new_node there
            Node last = head;
            while (last.next != null && last.power > new_node.power) {
                last = last.next;
            }
            if(last.power < new_node.power){
                if(last.prev==null){
                    head=new_node;
                    head.next=last;}
                else
                    new_node.next = last;
                if(last.prev!=null)
                last.prev.next= new_node;
                else
                    last=new_node;


            }else
            if (last.power == new_node.power) {
                last.data += new_node.data;

            }else if (last.next != null) {
                if (last.next.power == new_node.power) {
                    last.next.data += new_node.data;

                }
                else {
                    last.next.prev = new_node;
                    new_node.next = last.next;
                    last.next = new_node;
                    new_node.prev = last;
                }}
                 else  if (last.next == null) {
                    last.next = new_node;
                    new_node.prev = last;
                }
            }


        }

    public Node getHead(){
        return head;

    }



    public void printList(String nume) throws FileNotFoundException, UnsupportedEncodingException {
        Node currNode = head;
        PrintWriter writer = new PrintWriter(nume, "UTF-8");

        while (currNode != null) {

            if (currNode.data > 0)
                writer.print("+");
            writer.print(currNode.data);
            writer.print("x^" + currNode.power);
            currNode = currNode.next;
        }
        writer.close();
    }

    // **************DELETION BY KEY**************


    public void delete( int power) {
        // Store head node
        Node currNode = head, prev = null;

        //
        // CASE 1:
        // If head node itself holds the key to be deleted

        if (currNode != null && currNode.data == power) {
            head = currNode.next; // Changed head

        }

        //
        // CASE 2:
        // If the key is somewhere other than at head
        //

        // Search for the key to be deleted,
        // keep track of the previous node
        // as it is needed to change currNode.next
        while (currNode != null && currNode.data != power) {
            // If currNode does not hold key
            // continue to next node
            prev = currNode;
            currNode = currNode.next;
        }

        // If the key was present, it should be at currNode
        // Therefore the currNode shall not be null
        if (currNode != null) {
            // Since the key is at currNode
            // Unlink currNode from linked list
            prev.next = currNode.next;
            currNode.next.prev = prev;
        }

        //
        // CASE 3: The key is not present
        //

        // If key was not present in linked list
        // currNode should be null

    }
}