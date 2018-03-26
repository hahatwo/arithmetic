public class WordSearch {

	/*
	 * 给出一个二维的字母板和一个单词，寻找字母板网格中是否存在这个单词。
	 * 单词可以由按顺序的相邻单元的字母组成，其中相邻单元指的是水平或者垂直方向相邻。每个单元中的字母最多只能使用一次。 样例 
	 * 给出board = ["ABCE", "SFCS", "ADEE" ] word = "ABCCED"， ->返回 true, word = "SEE"，-> 返回
	 * true, word = "ABCB"， -> 返回 false.
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
	 * 算法思想： 深度优先遍历
	 * @param board  二维字母板
	 * @param word       单词
	 * @param row  当前字母所在的行
	 * @param col 当前字母所在的列
	 * @param index 当前字母在单词中的索引位置
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
			visited[row][col] = false; //回溯
			return res;
		}
		return false;
	}
}
