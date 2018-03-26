public class WordSearch {

	/*
	 * ����һ����ά����ĸ���һ�����ʣ�Ѱ����ĸ���������Ƿ����������ʡ�
	 * ���ʿ����ɰ�˳������ڵ�Ԫ����ĸ��ɣ��������ڵ�Ԫָ����ˮƽ���ߴ�ֱ�������ڡ�ÿ����Ԫ�е���ĸ���ֻ��ʹ��һ�Ρ� ���� 
	 * ����board = ["ABCE", "SFCS", "ADEE" ] word = "ABCCED"�� ->���� true, word = "SEE"��-> ����
	 * true, word = "ABCB"�� -> ���� false.
	 * @param board: A list of lists of character
	 * @param word: A string
	 * @return: A boolean
	 */
	public boolean exist(char[][] board, String word) {
		// write your code here
		if (board == null) {
			return false;
		}
		boolean res = false;
		boolean[][] visited = new boolean[board.length][board[0].length];
        for(int i = 0; i < board.length; i++) {
        	for(int j = 0; j < board[0].length; j++) {
        		res = dfs(board, visited, word, i, j, 0);
        		if(res) 
        			return res;
        	}
        }
		return res;
	}

	/**
	 * �㷨˼�룺 ������ȱ���
	 * @param board  ��ά��ĸ��
	 * @param word       ����
	 * @param row  ��ǰ��ĸ���ڵ���
	 * @param col ��ǰ��ĸ���ڵ���
	 * @param index ��ǰ��ĸ�ڵ����е�����λ��
	 * @return
	 */
	private boolean dfs(char[][] board, boolean[][] visited, String word,
			int row, int col, int index) {
		if (index == word.length()) {
			return true;
		}
		if (row < 0 || col < 0 || row >= board.length || col >= board[0].length
				|| board[row][col] != word.charAt(index)) {
			return false;
		}
		if (!visited[row][col] && board[row][col] == word.charAt(index)) {
			visited[row][col] = true;
			boolean res = dfs(board, visited, word, row - 1, col, index + 1)
					|| dfs(board, visited, word, row, col - 1, index + 1)
					|| dfs(board, visited, word, row + 1, col, index + 1)
					|| dfs(board, visited, word, row, col + 1, index + 1);
			visited[row][col] = false; //����
			return res;
		}
		return false;
	}
}
