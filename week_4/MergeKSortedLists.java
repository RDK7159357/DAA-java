package week_4;

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { 
        this.val = val; 
    }
}

public class MergeKSortedLists {

    // Merges two sorted linked lists and returns the merged list.
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while(l1 != null && l2 != null) {
            if(l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        if(l1 != null) tail.next = l1;
        if(l2 != null) tail.next = l2;
        return dummy.next;
    }

    // Merges an array of k sorted linked lists using a divide and conquer approach.
    public static ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0)
            return null;
        
        int interval = 1;
        int total = lists.length;
        while(interval < total) {
            for (int i = 0; i + interval < total; i = i + interval * 2) {
                lists[i] = mergeTwoLists(lists[i], lists[i + interval]);
            }
            interval *= 2;
        }
        return lists[0];
    }
    
    // Utility method to print the linked list.
    public static void printList(ListNode head) {
        while(head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    // Sample main method to test the mergeKLists function.
    public static void main(String[] args) {
        // Example: Merge 3 sorted lists.
        // List 1: 1 -> 4 -> 7
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(7);
        
        // List 2: 2 -> 5 -> 8
        ListNode l2 = new ListNode(2);
        l2.next = new ListNode(5);
        l2.next.next = new ListNode(8);
        
        // List 3: 3 -> 6 -> 9
        ListNode l3 = new ListNode(3);
        l3.next = new ListNode(6);
        l3.next.next = new ListNode(9);
        
        ListNode[] lists = new ListNode[]{l1, l2, l3};
        ListNode mergedHead = mergeKLists(lists);
        
        System.out.println("Merged Sorted List:");
        printList(mergedHead);
    }
}
