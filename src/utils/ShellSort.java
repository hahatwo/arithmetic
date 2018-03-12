package utils;

public class ShellSort {

	/**
	 * ϣ����������
	 * @param a ����������
	 * @param dk ��С����
	 */
	public void shellInsertSort(int[] a, int dk) {
		for(int i=dk; i<a.length; i++) {
			if(a[i] < a[i-dk]) {
				int x = a[i];
				int j = i - dk;
				while((j>=0) && (x < a[j])) {  //Ԫ�غ���
					a[j+dk] = a[j];
					j-=dk;
				}
				a[j+dk] = x;
			}
		}
		print(a);
	}
	
	public void print(int[] a) {
		for(int i=0; i<a.length; i++) {
			System.out.print(a[i]+"   ");
		}
		System.out.println();
		System.out.println("------------------");
	}
	
	public void shellSort(int[] a) {
		int dk = a.length/2;
		while(dk>0) {
			shellInsertSort(a, dk);
			dk = dk/2;
		}
	}
}
