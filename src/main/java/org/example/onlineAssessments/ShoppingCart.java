package org.example.onlineAssessments;

import org.example.BaseDataStructures.SinglyLinkedListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShoppingCart {
    public SinglyLinkedListNode getShoppingCar(SinglyLinkedListNode head, List<List<String>> queries) {

        for(List<String> query : queries) {
            String action = query.get(0);

            String itemName = query.get(1);

            if(action.equals("PUSH_HEAD")) {
                SinglyLinkedListNode newNode = new SinglyLinkedListNode(itemName);
                newNode.next = head;
                head = newNode;
            }
            else if(action.equals("PUSH_TAIL")) {
                SinglyLinkedListNode newNode = new SinglyLinkedListNode(itemName);
                if(head == null) head = newNode;
                else {
                    SinglyLinkedListNode curr = head;
                    while(curr.next != null) {
                        curr = curr.next;
                    }
                    curr.next = newNode;
                }
            }
            else if(action.equals("POP_HEAD") && head != null) {
                head = head.next;
            }
        }

        return head;
    }

    public static void main(String[] args) {
        SinglyLinkedListNode head = new SinglyLinkedListNode("item_1");
        head.next = new SinglyLinkedListNode("item_2");
        head.next.next = new SinglyLinkedListNode("item_3");
        head.next.next.next = new SinglyLinkedListNode("item_4");
        List<List<String>> queries1 = new ArrayList<>();
        List<String> list1_1 = Arrays.asList("PUSH_HEAD", "item_0");
        List<String> list1_2 = Arrays.asList("PUSH_TAIL", "item_5");
        List<String> list1_3 = Arrays.asList("POP_HEAD", "-");
        queries1.add(list1_1); queries1.add(list1_2); queries1.add(list1_3);

        ShoppingCart shoppingCart = new ShoppingCart();
        SinglyLinkedListNode shoppingCar = shoppingCart.getShoppingCar(head, queries1);


        return;
    }

}

