package org.study.data;

/**
 * @author 867123881
 *单向链表
 * @param <T>
 */
public class SingleLinkedList<T> {
	
	private DNode<T> mHead;
	
	private int mCount;
	
	private class DNode<T>{
		
		public DNode<T> next;
		
		public T value;
		
		public DNode(T value) {
			this.value = value;
		}
	}
	
	
	public SingleLinkedList() {
		mCount = 0;
	}
	
	
	public int size() {
		return mCount;
	}
	
	
	public boolean isEmpty() {
		return mCount == 0;
	}
	
	
	/**
	 * @param node
	 * 将节点插入到最后
	 */
	public void addNode(DNode<T> node) {
		if(mCount == 0) {
			mHead = node;
			mCount++;
			return;
		}
		
		DNode<T> temp = mHead;
		while(temp.next != null) {
			temp = temp.next;
		}
		temp.next = node;
		mCount ++;
	}
	
	
	public void add(T value) {
		DNode<T> node = new DNode<T>(value);
		addNode(node);
	}
	
	
	
	
	public DNode<T> getNode(int index){
		if(index < 0 || index > mCount - 1) {
			throw new IndexOutOfBoundsException();
		}
		
		int length = 0;
		DNode<T> temp = mHead;
		while(temp.next != null && index != length) {
			temp = temp.next;
			length++;
		}
		
		return temp;
	}
	public T getValue(int index) {
		return getNode(index).value;
	}
	public void addByIndex(int index,T value) {
		if(index < 0 || index > mCount) {
			throw new IndexOutOfBoundsException();
		}
		
		DNode<T> node = new DNode<>(value);
		
		if(index == 0) {
			DNode<T> temp = mHead;
			mHead = node;
			mHead.next = temp;
			mCount ++;
			return ;
		}

		DNode<T> preNode = getNode(index - 1);
		DNode<T> postNode = getNode(index);
		preNode.next = node;
		node.next = postNode;
		mCount ++;
	}
	public void remove(int index) {
		if(index < 0 || index > mCount -1) {
			throw new IndexOutOfBoundsException();
		}
		
		if(index == 0) {
			mHead = getNode(index + 1);
			mCount--;
			return;
		}
		
		int length = 0;
		DNode<T> temp = mHead;
		while(temp.next != null && index != length) {
			temp = temp.next;
			length++;
		}
		
		DNode<T> preNode = getNode(index - 1);
		preNode.next = temp.next;
		mCount --;
	}
}
