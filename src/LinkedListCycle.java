public class LinkedListCycle {

	/*
	 * ����һ�������ж����Ƿ��л���
	 * ����
	 * ���� -21->10->4->5, tail connects to node index 1������ true
     * @param head: The first node of linked list.
     * @return: True if it has a cycle, or false
     */
    public boolean hasCycle(ListNode head) {
        // write your code here
    	boolean result = isCycle(head);
    	return result;
    }
    
    /* ����һ��������������д��ڻ����򷵻ص������л�����ʼ�ڵ㣬���û�л�������null��
     * @param head: The first node of linked list.
     * @return: The node where the cycle begins. if there is no cycle, return null
     */
    public ListNode detectCycle(ListNode head) {
        // write your code here
    	ListNode result = findEntranceOfCycle(head);
    	return result;
    }
    
    /**
     * �㷨˼�룺 ��������ָ�룬�ֱ�Ϊfast��slow,
     * 			��ʼfast = slow = head,
     * 			fastÿ���ƶ������ڵ㣬slowÿ���ƶ�һ���ڵ�
     * 			����֪����������д��ڻ��Ļ���ĳ��ʱ��fast����׷��slow
     *          ��ĳһʱ��fast=slow��֤�������д��ڻ�
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
     * �ҵ���������ڽڵ�
     * �㷨˼�룺 ���������ܳ���ΪL(L>=0);
     * 			��ͷ����ڵ㳤��Ϊa(a>=0��a<L);
     * 			���ĳ���Ϊr(r>=0��r<=L);
     *          ��ڵ㵽�����㣨��������ʵ��ΪisCycle()��slow=fastʱslowָ������ڵ��λ�ã�����Ϊx(x>=0��x<r);
     *          �����㵽��ڵ�ľ���Ϊt(t>=0��t<r);
     *          ע��x��t������;
     *          ���׵õ�  r=x+t; ===> x=r-t;
     *          ���赽slow��fast����ʱ,slow����S������fast����2S�� ===> 2S=S + nr(n>=1); ===> S=nr;
     *          fast�ߵľ���ͬʱҲӦ��Ϊa+x+nr; ===> 2S = a+x+nr; ===> a=nr-x;
     *          �� a= nr-x = nr-r+t = (n-1)r+t;
     *          ��������õ��������һ��ָ��first������ͷ�ڵ㿪ʼ����������һ��ָ��second�������㣨��ʱ��ڵ㵽���������Ϊx����ʼ������
     *          ����һ��ָ������a�����ڶ���ָ���ڻ��ھ�Ӧ������(n-1)r+t��������n-1��r+t+x=nr�����պûص�������㣻
     *          ��ʱ��first=second;
     *          ��˵�first=secondʱ������ָ����Ǹ��ڵ㼴Ϊ������ڽڵ�
     * @param head
     * @return
     */
    private ListNode findEntranceOfCycle(ListNode head) {
    	ListNode meetNode = findMeetOfCycle(head); //������
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
