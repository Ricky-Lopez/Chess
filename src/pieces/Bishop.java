package pieces;
import java.lang.Math; 


public class Bishop extends Piece{
	
	
	public Bishop(Boolean isWhite) {
		
		super(isWhite);
		this.pieceType = "Bishop";
		
	}
	
	@Override
	public String toString()  {
		String type = "";
		
		if (isWhite == true) {
			type = "wB";
		}
		
		else {
			type = "bB";
		}
		
		return type;
	}
	
	@Override
	public int move(String move, Piece[][] board, boolean player) {
		
		boolean ascending;
		boolean rightward;
		
		int currColumn = move.charAt(0);
		currColumn = currColumn - 97;
		
		int nextColumn = move.charAt(3);
		nextColumn = nextColumn - 97;
		
		int currRow = 8 - Character.getNumericValue(move.charAt(1));
		int nextRow = 8 - Character.getNumericValue(move.charAt(4));
		
		if(currRow < nextRow) {ascending = false;} else {ascending = true;}
		if(currColumn > nextColumn) {rightward = false;} else {rightward = true;}
		
		if((currColumn == nextColumn) && (currRow == nextRow)) { return -1; }			//if curr and next are same positions
		
		if(board[currRow][currColumn] == null) {return -1;}		//Checks the position to ensure there is a piece there.
		
		if(board[currRow][currColumn].isWhite != player) {return -1;} //Checks if the piece being moved is the same color as the color the player is in the game.
		
		if(!player) {	//if player is black
			if(Math.abs(currRow - nextRow) == Math.abs(currColumn - nextColumn) ) { //if the move goes the same amount of spaces both vertically and horizontally.
				int difference = Math.abs(currRow - nextRow);
				
				if(ascending) {		//ascending
					if(rightward) { //rightward
						for(int i = 1; i < difference; i++) {	// checks the spaces between curr position and next position.
							if(board[currRow-i][currColumn+i] != null) { //if a space is occupied, then it is an illegal move.
								return -1;
							}
						}
						if( board[nextRow][nextColumn] != null) { //if the next position is occupied
							if(!board[nextRow][nextColumn].isWhite) { //if the next position contains a black piece
								return -1;
							}
						}
						
						board[nextRow][nextColumn] = board[currRow][currColumn];
						board[currRow][currColumn] = null;
						if(checkIfCheck(board, player, 23, 23) ) { // if the move puts itself in check.
							board[currRow][currColumn] = board[nextRow][nextColumn];
							board[nextRow][nextColumn] = null;
							return -1;
						}
						checkEnPassante(move, board, player); return 0;
					}else {		//leftward
						for(int i = 1; i < difference; i++) {	// checks the spaces between curr position and next position.
							if(board[currRow-i][currColumn-i] != null) { //if a space is occupied, then it is an illegal move.
								return -1;
							}
						}
						if( board[nextRow][nextColumn] != null) { //if the next position is occupied
							if(!board[nextRow][nextColumn].isWhite) { //if the next position contains a black piece
								return -1;
							}
						}
						
						board[nextRow][nextColumn] = board[currRow][currColumn];
						board[currRow][currColumn] = null;
						if(checkIfCheck(board, player, 23, 23) ) { // if the move puts itself in check.
							board[currRow][currColumn] = board[nextRow][nextColumn];
							board[nextRow][nextColumn] = null;
							return -1;
						}
						checkEnPassante(move, board, player); return 0;
					}
				}else { //descending
					if(rightward) { //rightward
						for(int i = 1; i < difference; i++) {	// checks the spaces between curr position and next position.
							if(board[currRow+i][currColumn+i] != null) { //if a space is occupied, then it is an illegal move.
								return -1;
							}
						}
						if( board[nextRow][nextColumn] != null) { //if the next position is occupied
							if(!board[nextRow][nextColumn].isWhite) { //if the next position contains a black piece
								return -1;
							}
						}
						
						board[nextRow][nextColumn] = board[currRow][currColumn];
						board[currRow][currColumn] = null;
						if(checkIfCheck(board, player, 23, 23) ) { // if the move puts itself in check.
							board[currRow][currColumn] = board[nextRow][nextColumn];
							board[nextRow][nextColumn] = null;
							return -1;
						}
						checkEnPassante(move, board, player); return 0;
					}else {		//leftward
						for(int i = 1; i < difference; i++) {	// checks the spaces between curr position and next position.
							if(board[currRow+i][currColumn-i] != null) { //if a space is occupied, then it is an illegal move.
								return -1;
							}
						}
						if( board[nextRow][nextColumn] != null) { //if the next position is occupied
							if(!board[nextRow][nextColumn].isWhite) { //if the next position contains a black piece
								return -1;
							}
						}
						
						board[nextRow][nextColumn] = board[currRow][currColumn];
						board[currRow][currColumn] = null;
						if(checkIfCheck(board, player, 23, 23) ) { // if the move puts itself in check.
							board[currRow][currColumn] = board[nextRow][nextColumn];
							board[nextRow][nextColumn] = null;
							return -1;
						}
						checkEnPassante(move, board, player); return 0;
					}
				}
			}
		}else { //player is white
			if(Math.abs(currRow - nextRow) == Math.abs(currColumn - nextColumn) ) { //if the move goes the same amount of spaces both vertically and horizontally.
				int difference = Math.abs(currRow - nextRow);
				if(ascending) {	//ascending
					if(rightward) { //rightward
						for(int i = 1; i < difference; i++) {	// checks the spaces between curr position and next position.
							if(board[currRow-i][currColumn+i] != null) { //if a space is occupied, then it is an illegal move.
								return -1;
							}
						}
						if( board[nextRow][nextColumn] != null) { //if the next position is occupied
							if(board[nextRow][nextColumn].isWhite) { //if the next position contains a white piece
								return -1;
							}
						}
						
						board[nextRow][nextColumn] = board[currRow][currColumn];
						board[currRow][currColumn] = null;
						if(checkIfCheck(board, player, 23, 23) ) { // if the move puts itself in check.
							board[currRow][currColumn] = board[nextRow][nextColumn];
							board[nextRow][nextColumn] = null;
							return -1;
						}
						checkEnPassante(move, board, player); return 0;
					}else {		//leftward
						for(int i = 1; i < difference; i++) {	// checks the spaces between curr position and next position.
							if(board[currRow-i][currColumn-i] != null) { //if a space is occupied, then it is an illegal move.
								return -1;
							}
						}
						if( board[nextRow][nextColumn] != null) { //if the next position is occupied
							if(board[nextRow][nextColumn].isWhite) { //if the next position contains a white piece
								return -1;
							}
						}
						
						board[nextRow][nextColumn] = board[currRow][currColumn];
						board[currRow][currColumn] = null;
						if(checkIfCheck(board, player, 23, 23) ) { // if the move puts itself in check.
							board[currRow][currColumn] = board[nextRow][nextColumn];
							board[nextRow][nextColumn] = null;
							return -1;
						}
						checkEnPassante(move, board, player); return 0;
					}
				}else { //descending
					if(rightward) { //rightward
						for(int i = 1; i < difference; i++) {	// checks the spaces between curr position and next position.
							if(board[currRow+i][currColumn+i] != null) { //if a space is occupied, then it is an illegal move.
								return -1;
							}
						}
						if( board[nextRow][nextColumn] != null) { //if the next position is occupied
							if(board[nextRow][nextColumn].isWhite) { //if the next position contains a white piece
								return -1;
							}
						}
						
						board[nextRow][nextColumn] = board[currRow][currColumn];
						board[currRow][currColumn] = null;
						if(checkIfCheck(board, player, 23, 23) ) { // if the move puts itself in check.
							board[currRow][currColumn] = board[nextRow][nextColumn];
							board[nextRow][nextColumn] = null;
							return -1;
						}
						checkEnPassante(move, board, player); return 0;
					}else {		//leftward
						for(int i = 1; i < difference; i++) {	// checks the spaces between curr position and next position.
							if(board[currRow+i][currColumn-i] != null) { //if a space is occupied, then it is an illegal move.
								return -1;
							}
						}
						if( board[nextRow][nextColumn] != null) { //if the next position is occupied
							if(board[nextRow][nextColumn].isWhite) { //if the next position contains a white piece
								return -1;
							}
						}
						
						board[nextRow][nextColumn] = board[currRow][currColumn];
						board[currRow][currColumn] = null;
						if(checkIfCheck(board, player, 23, 23) ) { // if the move puts itself in check.
							board[currRow][currColumn] = board[nextRow][nextColumn];
							board[nextRow][nextColumn] = null;
							return -1;
						}
						checkEnPassante(move, board, player); return 0;
					}
				}
			}
		}
		
		return -1;
		
	}
	
	
	
	
}
