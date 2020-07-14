package pieces;

public class Knight extends Piece{
	

	
	public Knight(Boolean isWhite) {
		
		super(isWhite);
		this.pieceType = "Knight";
		
	}
	
	@Override
	public String toString()  {
		String type = "";
		
		if (isWhite == true) {
			type = "wN";
		}
		
		else {
			type = "bN";
		}
		
		return type;
	}
	
	@Override
	public int move(String line, Piece[][] board, boolean player) {
		
		int currColumn = line.charAt(0);
		currColumn = currColumn - 97;
		int nextColumn = line.charAt(3);
		nextColumn = nextColumn - 97;
		
		int currRow = 8 - Character.getNumericValue(line.charAt(1));
		int nextRow = 8 - Character.getNumericValue(line.charAt(4));
		
		//System.out.println("0");
		if((currColumn == nextColumn) && (currRow == nextRow)) { return -1; }			//if curr and next are same positions
		//System.out.println("1");
		if(board[currRow][currColumn] == null) {return -1;}		//Checks the position to ensure there is a piece there.
		//System.out.println("2");
		if(board[currRow][currColumn].isWhite != player) {return -1;} //Checks if the piece being moved is the same color as the color the player is in the game.
		//System.out.println("3");
		if (board[nextRow][nextColumn] != null) {
			if(board[nextRow][nextColumn].isWhite == player) {return -1;}	//next position is taken piece of the same team
		}
		
		
		//System.out.println("bext");
		if(((currRow + 2 == nextRow) || (currRow - 2 == nextRow)) && ((currColumn + 1 == nextColumn) || (currColumn - 1 == nextColumn))) {			//if knight is moving up/down 2 spaces and left/right 1 space
			//System.out.println("up/down 2 left/right 1");
			
			board[nextRow][nextColumn] = board[currRow][currColumn];
			board[currRow][currColumn] = null;
			if(checkIfCheck(board, player, 23, 23) ) { // if the move puts itself in check.
				board[currRow][currColumn] = board[nextRow][nextColumn];
				board[nextRow][nextColumn] = null;
				return -1;
			}
			board[nextRow][nextColumn].isFirstMove = false;
			
			checkEnPassante(line, board, player); return 0;
		}
		else if(((currColumn + 2 == nextColumn) || (currColumn - 2 == nextColumn)) && ((currRow + 1 == nextRow) || (currRow - 1 == nextRow))) {				//if knight is moving left/right 2 spaces and up/down 1 space
			
			board[nextRow][nextColumn] = board[currRow][currColumn];
			board[currRow][currColumn] = null;
			if(checkIfCheck(board, player, 23, 23) ) { // if the move puts itself in check.
				board[currRow][currColumn] = board[nextRow][nextColumn];
				board[nextRow][nextColumn] = null;
				return -1;
			}
			board[nextRow][nextColumn].isFirstMove = false;
			
			checkEnPassante(line, board, player); return 0;
		}
		
		return -1;
	}
	
}
