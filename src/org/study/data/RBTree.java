package org.study.data;

/**
 * @author 867123881
 *红黑树的java实现
 */
public class RBTree<T> {
	private RBNode<T> root;			//根节点
	private static final boolean RED = false;
	private static final boolean BLACK = true;
	
	public class RBNode<T>{
		
		boolean color;			//节点的颜色
		T key;					//节点的键值
		RBNode<T> parent;		//节点的父节点
		RBNode<T> left;			//节点的左子节点
		RBNode<T> right;		//节点的右子节点
		
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
	 * 左旋
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
		
		//获取x节点的右子节点
		RBNode<T> y = x.right;
		
		//将y节点的左子节点设为x节点的右子节点
		x.right = y.left;
		if(y.left != null) {
			y.left.parent = x;			//y节点的左子节点不为空时，将其父节点设为x节点
		}
		
		//将x的父节点设为y的父节点
		y.parent = x.parent;
		
		//处理x父节点如何指向y节点
		if(x.parent == null) {
			root = y;					//x的父节点为空，则y节点为根节点
		}else if(x.parent.left == x) {	//x是其父节点的左子节点
			x.parent.left = y;
		}else if(x.parent.right == x) {
			x.parent.right = y;
		}
		
		x.parent = y;
		
		y.left = x;
		
	}
	
	
	
	/**
	 * @param x
	 * 右旋
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
		
		//获取x节点的左子节点
		RBNode<T> y = x.left;
		
		//将y节点的右子节点设为x节点的左子节点
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
