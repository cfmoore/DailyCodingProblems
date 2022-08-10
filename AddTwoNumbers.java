/**
 * You are given two non-empty linked lists representing two non-negative integers. 
 * The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        int templ1 = 0;
        int templ2 = 0;
        ListNode head = new ListNode();
        ListNode temp = new ListNode();
        while(l1!= null || l2!= null)
        { 
            if(l1 != null)
            {
                templ1 = l1.val;
                l1 = l1.next;
            }   
            else
                templ1 = 0;
            if(l2 != null)
            {
                templ2 = l2.val;
                l2 = l2.next;
            }
            else
                templ2 = 0;
            if(head.next == null)
            {
                head.val = templ1+templ2;
                if(head.val>=10)
                {
                    head.val = head.val-10;
                    carry = 1;
                }
                if(l1 == null && l2 == null)
                {
                   if(carry==1)
                   {
                        head.next = new ListNode(carry);
                        temp = head.next;
                   }
                   break;
               }
                head.next = new ListNode();
                temp = head.next;
            }
           else
            {
                temp.val = templ1+templ2+carry;
                carry = 0;
                if(temp.val>=10)
                {
                    temp.val = temp.val-10;
                    carry = 1;
                }
               if(l1 == null && l2 == null)
               {
                   if(carry==1)
                   {
                        temp.next = new ListNode(carry);
                        temp = temp.next;
                   }
                   break;
               }
               temp.next = new ListNode();
               temp = temp.next;
            }
        }
        return head;
    }
}
