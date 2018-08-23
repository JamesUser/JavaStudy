package org.study.test;

import org.study.data.SingleLinkedList;

public class StudyTest {
	public static void main(String[] args) {
		SingleLinkedList<String> list = new SingleLinkedList<String>();
		list.add("aa");
		list.add("bb");
		list.add("cc");
		list.add("dd");
		
		list.addByIndex(1, "11");
		list.remove(3);
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.getValue(i));
		}
	}
}
