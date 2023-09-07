package src.tools;

public class LinkedList {
    Node head;
    int numNodes;

    public class Node {
        double data;
        Node next;

        Node(double data) {
            this.data = data;
            this.next = null;
        }
    }

    public LinkedList insert(LinkedList list, double data) {
        Node newNode = new Node(data);

        if (list.head == null) {
            list.head = newNode;
        }
        else {
            Node last = list.head;

            while (last.next != null) {
                last = last.next;
            }

            last.next = newNode;
        }

        list.numNodes += 1;

        return list;
    }
}
