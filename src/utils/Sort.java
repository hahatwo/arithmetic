package utils;

public class Sort {

	public Sort() {
		// TODO Auto-generated constructor stub
	}

	public int[] quickSort(int[] s, int l, int r) {
		
		if (l < r)  
	    {  
	        //Swap(s[l], s[(l + r) / 2]); //将中间的这个数和第一个数交换 参见注1  
	        int i = l, j = r, x = s[l];  
	        while (i < j)  
	        {  
	            while(i < j && s[j] >= x) // 从右向左找第一个小于x的数  
	                j--;    
	            if(i < j) {
	            	s[i] = s[j];
	            	i++;
	            }  
	                
	              
	            while(i < j && s[i] < x) // 从左向右找第一个大于等于x的数  
	                i++;    
	            if(i < j) {
	            	 s[j] = s[i];  
	            	 j--;
	            }  
	               
	        }  
	        s[i] = x;  
	        quickSort(s, l, i - 1); // 递归调用   
	        quickSort(s, i + 1, r);  
	    }  
	return s;
	}
}
