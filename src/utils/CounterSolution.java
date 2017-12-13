package utils;

public class CounterSolution {

	public CounterSolution() {
		// TODO Auto-generated constructor stub
	}

	public int digitCounts(int k, int n) {
        // write your code here
        int sum = 0; 
        char newk = (char)(k + 48);
        char[] array;
        String s;
        for(int i = 0; i <= n; i++){
            s = String.valueOf(i);
            array = s.toCharArray();
            for(int j = 0; j < array.length; j++){
                if(array[j] == newk)
                    sum++;
            }
        }
        return sum;
    }
    
}
