package utils;

public class Sort {

	public Sort() {
		// TODO Auto-generated constructor stub
	}

	public int[] quickSort(int[] s, int l, int r) {
		
		if (l < r)  
	    {  
	        //Swap(s[l], s[(l + r) / 2]); //���м��������͵�һ�������� �μ�ע1  
	        int i = l, j = r, x = s[l];  
	        while (i < j)  
	        {  
	            while(i < j && s[j] >= x) // ���������ҵ�һ��С��x����  
	                j--;    
	            if(i < j) {
	            	s[i] = s[j];
	            	i++;
	            }  
	                
	              
	            while(i < j && s[i] < x) // ���������ҵ�һ�����ڵ���x����  
	                i++;    
	            if(i < j) {
	            	 s[j] = s[i];  
	            	 j--;
	            }  
	               
	        }  
	        s[i] = x;  
	        quickSort(s, l, i - 1); // �ݹ����   
	        quickSort(s, i + 1, r);  
	    }  
	return s;
	}
}
