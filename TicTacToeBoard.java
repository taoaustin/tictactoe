public class TicTacToeBoard {

	private CellValue[] board;

	// records the number of moves played so far   
	private int level;

	private GameState gameState;



	public TicTacToeBoard(){

		this.board = new CellValue[9];
		for (int i = 0; i < board.length; i++)
			board[i] = CellValue.EMPTY;
		this.level = 0;
		this.gameState = GameState.PLAYING;
	}	

	public int getLevel(){
		return level;
	}

	public GameState getGameState(){
		return gameState;
	}

	public CellValue nextCellValue(){
		if (level%2 == 0)
			return CellValue.X;
		else
			return CellValue.O;
	}

	public CellValue valueAt(int i) {

		if (i < 0 || i >= board.length) {
			System.out.println("This index is invalid");
			return CellValue.EMPTY;
		}
		else {
			return board[i];
		}
	}

   
  	// plays the next move at cell i of the board array, checks for valid input
	public void play(int i) {

		if (i < 0 || i >= board.length) {
			System.out.println("The value should be between 1 and "+board.length);
		}
		else if (level == board.length) {
			System.out.println("No more moves are possible");
		}	
		else if (board[i] != CellValue.EMPTY) {
			System.out.println("This space has already been played");
		}
		else {
			board[i] = nextCellValue();
			if (gameState == GameState.PLAYING)
				setGameState();
			else
				System.out.println("Game has already ended");
			level++;
		}
	}


   	// checks/sets the current gameState, changes it when necessary
	private void setGameState(){

		int i;
		
		//FOR ROWS
		for (i = 0; i < 7; i+=3) {
			if ( board[i] == board[i+1] && board[i+1] == board[i+2]){
				if (board[i] == CellValue.X)
					gameState = GameState.XWIN;
				else if (board[i] == CellValue.O)
					gameState = GameState.OWIN;
			}
		}
		
		//FOR COLUMNS
		for (i = 0; i < 3; i++) {
			if ( board[i] == board[i+3] && board[i+3] == board[i+6]){
				if (board[i] == CellValue.X)
					gameState = GameState.XWIN;
				else if (board[i] == CellValue.O)
					gameState = GameState.OWIN;
			}
		}
		
		//FOR DIAGONALS
		if ((board[0] == board[4] && board[4] == board[8]) || (board[2] == board[4] && board[4] == board[6])) {
			if (board[4] == CellValue.X)
				gameState = GameState.XWIN;
			else if (board[4] == CellValue.O)
				gameState = GameState.OWIN;
		}
		
		// FOR DRAW
		if (gameState == GameState.PLAYING) {
			if (level+1 == board.length) {
				gameState = GameState.DRAW;
			}
		}
		return;
	}


	// returns string representation of the TicTacToe game
	public String toString(){

		String horizontal = "-----------";
		String result = "";
		int count = 0;
		for (int i = 0; i < 3; i++) {
			if (i != 0) {
				result += horizontal+"\n";
			}
			for (int j = 0; j < 3; j++) {
				if (j != 2) 
					result += cellValueToString(board[count])+"|";
				else
					result += cellValueToString(board[count]);
				count++;
			}
			result += "\n";
		}
		return result;
	}
	
	private String cellValueToString(CellValue val) {
		if (val == CellValue.EMPTY)
			return "   ";
		else if (val == CellValue.X)
			return " X ";
		else 
			return " O ";
	}
	private CellValue stringToCellValue(String val) {
		if ("   ".equals(val))
			return CellValue.EMPTY;
		else if (" X ".equals(val))
			return CellValue.X;
		else
			return CellValue.O;
	}

}