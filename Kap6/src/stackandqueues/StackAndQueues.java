package stackandqueues;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class StackAndQueues {

    public static void main(String[] args) {

        Stack s = new Stack();
        s.add(4);
        s.add(8);
        s.add(1);
        s.add(6);
        s.pop();
        s.pop();
        
        System.out.println("Stack:");
        for (Object o : s) {
            System.out.println(o.toString());
        }
        
        
        
        
        Queue q = new LinkedList();
        q.add(4);
        q.add(8);
        q.add(1);
        q.add(6);
        q.remove();
        q.remove();
        
        System.out.println("Queue:");
        for (Object o : q) {
            System.out.println(o.toString());
        }
        
        
        
        
        
        
        PriorityQueue pq = new PriorityQueue();
        pq.add(4);
        pq.add(8);
        pq.add(1);
        pq.add(6);
        pq.remove();
        pq.remove();
        
        System.out.println("PriorityQueue:");
        for (Object o : pq) {
            System.out.println(o.toString());
        }
    }

}
