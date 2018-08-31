package org.study.data;

/**
 * @author 867123881
 *�������javaʵ��
 */
public class RBTree<T> {
	private RBNode<T> root;			//���ڵ�
	private static final boolean RED = false;
	private static final boolean BLACK = true;
	
	public class RBNode<T>{
		
		boolean color;			//�ڵ����ɫ
		T key;					//�ڵ�ļ�ֵ
		RBNode<T> parent;		//�ڵ�ĸ��ڵ�
		RBNode<T> left;			//�ڵ�����ӽڵ�
		RBNode<T> right;		//�ڵ�����ӽڵ�
		
		public RBNode(boolean color,T key,RBNode<T> parent,RBNode<T> left,RBNode<T> right) {
			this.color = color;
			this.key = key;
			this.parent = parent;
			this.left = left;
			this.right = right;
		}
	}
	
	
	/**
	 * @param x
	 * ����
	 * 
	 *          a						 a
	 *         / \						/ \
	 * 		  x	  b					   y   b
	 * 		 / \		------>	      / \
	 *      c   y                    x   e
	 *         / \					/ \
	 * 		  d   e				   c   d
	 */
	public void leftRotate(RBNode<T> x) {
		
		//��ȡx�ڵ�����ӽڵ�
		RBNode<T> y = x.right;
		
		//��y�ڵ�����ӽڵ���Ϊx�ڵ�����ӽڵ�
		x.right = y.left;
		if(y.left != null) {
			y.left.parent = x;			//y�ڵ�����ӽڵ㲻Ϊ��ʱ�����丸�ڵ���Ϊx�ڵ�
		}
		
		//��x�ĸ��ڵ���Ϊy�ĸ��ڵ�
		y.parent = x.parent;
		
		//����x���ڵ����ָ��y�ڵ�
		if(x.parent == null) {
			root = y;					//x�ĸ��ڵ�Ϊ�գ���y�ڵ�Ϊ���ڵ�
		}else if(x.parent.left == x) {	//x���丸�ڵ�����ӽڵ�
			x.parent.left = y;
		}else if(x.parent.right == x) {
			x.parent.right = y;
		}
		
		x.parent = y;
		
		y.left = x;
		
	}
	
	
	
	/**
	 * @param x
	 * ����
	 * 
	 *          a						 a
	 *         / \						/ \
	 * 		  x	  b					   y   b
	 * 		 / \		------>	      / \
	 *      y   c                    d   x
	 *     / \					    	/ \
	 * 	  d   e				           e   c
	 */
	public void rightRotate(RBNode<T> x) {
		
		//��ȡx�ڵ�����ӽڵ�
		RBNode<T> y = x.left;
		
		//��y�ڵ�����ӽڵ���Ϊx�ڵ�����ӽڵ�
		x.left = y.right;
		if(y.right != null) {
			y.right.parent = x;
		}
		
		y.parent = x.parent;
		if(x.parent == null) {
			root = y;
		}else if(x.parent.right == x) {
			x.parent.right = y;
		}else if(x.parent.left == x) {
			x.parent.left = y;
		}
		
		y.right = x;
		x.parent = y;
	}
	
	
}
