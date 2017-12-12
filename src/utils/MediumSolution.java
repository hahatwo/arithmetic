package utils;

public class MediumSolution {
    /**
     * @param nums: A list of integers.
     * @return: the median of numbers
     */
    private int[] queueArray;
    private int[] outArray;
    private int outFlag = 0;
    private int queueFlag = 0;
    
    
    public int[] medianII(int[] nums) {
        // write your code here
        int length = 0;
        queueArray = new int[nums.length];
        outArray = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            length = pushSort(queueArray, nums[i]);
            push(outArray, queueArray[(length - 1)/2]);
        }
        return outArray;
    }
    
    private void push(int[] array, int value) {
        array[outFlag] = value;
        outFlag++;
    }
    
    private int pushSort(int[] array, int value){
        if(queueFlag >= array.length){
            System.out.println("¶ÓÁÐÒÑÂú");
            return queueFlag;
        }else if(queueFlag == 0){
        	array[queueFlag] = value;
        	queueFlag++;
        }else{
            int i = queueFlag - 1 ;
            while(i >= 0){
            	if(array[i] > value){
                    array[i + 1] = array[i];
                    i--;
                 }else{
                	 break;
                 }
            }
            array[i + 1] = value;
            queueFlag++;
        }
        return queueFlag;
    }
    
}