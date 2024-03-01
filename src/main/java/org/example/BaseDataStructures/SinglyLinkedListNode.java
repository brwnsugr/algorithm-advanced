package org.example.BaseDataStructures;


public class SinglyLinkedListNode {
    Object val;
    public SinglyLinkedListNode next;
    public SinglyLinkedListNode() {}
    public SinglyLinkedListNode(Object val) {this.val = val;}
    public SinglyLinkedListNode(Object val, SinglyLinkedListNode next) {
        this.val = val;
        this.next = next;
    }
}
