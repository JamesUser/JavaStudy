package org.study.thread;

public class QuickSort {
	
	
	
	public void sort(int left,int right,int... numbers) {
		if(left >= right) {
			return;
		}
		
		int t= 0;
		int i = left;
		int j = right;
		int temp = numbers[left];
		
		while(i != j) {
			while(numbers[j] >= temp && i < j) {
				j--;
			}
			
			while(numbers[i] <= temp && i < j) {
				i++;
			}
			if (i < j) {
				t = numbers[i];
				numbers[i] = numbers[j];
				numbers[j] = t;
			}
		}
		
		// 将基准数归位
		numbers[left] = numbers[i];
		numbers[i] = temp;
 
		sort(left, i - 1, numbers);
		sort(i + 1, right, numbers);
	}
	
	public static void main(String[] args) {
		int[] numbers = new int[] { 4, 3, 6, 2, 7, 1, 5 };
 
		new QuickSort().sort(0, numbers.length - 1, numbers);
 
		System.out.print("after: ");
		for (int i = 0; i < numbers.length; i++) {
			System.out.print(numbers[i] + "  ");
		}
		System.out.println();
	}

	
}
