public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int count = 0;
		SearchMatrixII sea = new SearchMatrixII();
		int[][] matrix = {{62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80},
				{63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81},
				{64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82},
				{65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83},
				{66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84},
				{67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85}
		};
		count = sea.searchMatrix(matrix, 81);	
		System.out.println(count);
	}
}
