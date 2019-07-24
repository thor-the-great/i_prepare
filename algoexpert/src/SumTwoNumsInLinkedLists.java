import list.ListNode;

public class SumTwoNumsInLinkedLists {

  public static ListNode sumTwoLinkedLists(ListNode input1, ListNode input2) {

    ListNode res = new ListNode(-1);
    ListNode n = res;
    int dig = 0;
    while(input1 != null || input2 != null) {
      if (input1 != null) {
        dig+=input1.val;
        input1 = input1.next;
      }
      if (input2 != null) {
        dig+=input2.val;
        input2 = input2.next;
      }
      n.next = new ListNode(dig % 10);
      n = n.next;
      dig /= 10;
    }

    if (dig > 0)
      n.next = new ListNode(dig);

    return res.next;
  }
}
