public class LinkedListCycle {

	/*
	 * 给定一个链表，判断它是否有环。
	 * 样例
	 * 给出 -21->10->4->5, tail connects to node index 1，返回 true
     * @param head: The first node of linked list.
     * @return: True if it has a cycle, or false
     */
    public boolean hasCycle(ListNode head) {
        // write your code here
    	boolean result = isCycle(head);
    	return result;
    }
    
    /* 给定一个链表，如果链表中存在环，则返回到链表中环的起始节点，如果没有环，返回null。
     * @param head: The first node of linked list.
     * @return: The node where the cycle begins. if there is no cycle, return null
     */
    public ListNode detectCycle(ListNode head) {
        // write your code here
    	ListNode result = findEntranceOfCycle(head);
    	return result;
    }
    
    /**
     * 算法思想： 给定两个指针，分别为fast和slow,
     * 			开始fast = slow = head,
     * 			fast每次移动两个节点，slow每次移动一个节点
     * 			容易知道如果链表当中存在环的话，某个时刻fast将会追上slow
     *          若某一时刻fast=slow就证明链表当中存在环
     * @param head
     * @return
     */
    private boolean isCycle(ListNode head) {
    	if(head == null || head.next == null) {
    		return false;
    	}
    	ListNode fast = null, slow = null;
    	fast = slow = head;
    	while(fast != null && fast.next != null) {
    		fast = fast.next.next;
    		slow = slow.next;
    		if(fast == slow) {
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * 找到链表环的入口节点
     * 算法思想： 假设链表总长度为L(L>=0);
     * 			表头到入口点长度为a(a>=0且a<L);
     * 			环的长度为r(r>=0且r<=L);
     *          入口点到相遇点（相遇点其实就为isCycle()中slow=fast时slow指向的最后节点的位置）距离为x(x>=0且x<r);
     *          相遇点到入口点的距离为t(t>=0且t<r);
     *          注意x和t的区别;
     *          容易得到  r=x+t; ===> x=r-t;
     *          假设到slow和fast相遇时,slow走了S步，则fast走了2S步 ===> 2S=S + nr(n>=1); ===> S=nr;
     *          fast走的距离同时也应该为a+x+nr; ===> 2S = a+x+nr; ===> a=nr-x;
     *          即 a= nr-x = nr-r+t = (n-1)r+t;
     *          可以想象得到，如果第一个指针first从链表头节点开始遍历，而另一个指针second从相遇点（此时入口点到相遇点距离为x）开始遍历，
     *          当第一个指针走了a步，第二个指针在环内就应该走了(n-1)r+t步，而（n-1）r+t+x=nr，即刚好回到环的起点；
     *          此时有first=second;
     *          因此当first=second时，它们指向的那个节点即为环的入口节点
     * @param head
     * @return
     */
    private ListNode findEntranceOfCycle(ListNode head) {
    	ListNode meetNode = findMeetOfCycle(head); //相遇点
    	ListNode firstNode = head;
    	ListNode secondNode = meetNode;
    	if(meetNode != null) {
    		while(firstNode != secondNode) {
    			firstNode = firstNode.next;
    			secondNode = secondNode.next;
    		}
    		return firstNode;
    	}
    	return null;
    }
    
    private ListNode findMeetOfCycle(ListNode head) {
    	if(head == null || head.next == null) {
    		return null;
    	}
    	ListNode fast = null, slow = null;
    	fast = slow = head;
    	while(fast != null && fast.next != null) {
    		fast = fast.next.next;
    		slow = slow.next;
    		if(fast == slow) {
    			return slow;
    		}
    	}
    	return null;
    }
    
    class ListNode {
		int val;
		ListNode next;
		ListNode(int val) {
			this.val = val;
			this.next = null;
		}
	}
    
    
}
