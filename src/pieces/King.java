package pieces;


/**
 * 
 * @author Sara Crespo
 * @author Ricky Lopez
 *
 * Class for King piece
 * Subclass of Piece
 */
public class King extends Piece{
	

	boolean isFirstMove;
	
	/**
	 * 1 arg constructor
	 * @param x True if current player is white, False if current player is black
	 */
	public King(boolean isWhite) {
		
		super(isWhite);
		this.pieceType = "King";
		this.isFirstMove = true;
	}
	
	/**
	 * Makes piece into the correct string for ASCII chess board
	 * @return String form of piece
	 */
	@Override
	public String toString()  {
		String type = "";
		
		if (isWhite == true) {
			type = "wK";
		}
		
		else {
			type = "bK";
		}
		
		return type;
	}
	
	
	/**
	 * Moves a piece based on input by user
	 * @param line The string instructions inputed by user
	 * @param board the two-dimensional array board that holds all the piece information
	 * @param player True if white player, False if black player
	 * @return integer -1 if move is invalid and 0 if move is valid
	 */
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
		
		
		

		
		//if trying to capture enemy piece
		if(board[nextRow][nextColumn] != null) {
			//if trying to capture enemy piece
			if(board[nextRow][nextColumn].isWhite != player) {
				if((nextColumn > currColumn + 1) || (nextColumn < currColumn - 1)) { return -1; }			//checks if king is only moving 1 position away
				if((nextRow > currRow + 1) || (nextRow < currRow - 1)) { return -1; }	
				
				if(checkIfCheck(board, player, nextRow, nextColumn) == true) { return -1; }		//return -1 if King would be in check if it moved there
				
				if(!player) { 				//black player
					
					if(checkIfCheck(board, player, 23, 23) ) { // if the move puts itself in check.
						return -1;
					}
					
					board[nextRow][nextColumn] = board[currRow][currColumn];
					board[currRow][currColumn] = null;
					board[nextRow][nextColumn].isFirstMove = false;
					
					checkEnPassante(line, board, player); return 0;
				}
				
				else {						//white player
					
					if(checkIfCheck(board, player, 23, 23) ) { // if the move puts itself in check.
						return -1;
					}
					
					board[nextRow][nextColumn] = board[currRow][currColumn];
					board[currRow][currColumn] = null;
					board[nextRow][nextColumn].isFirstMove = false;
					
					checkEnPassante(line, board, player); return 0;
				}
			}		//out of capture
			
			else {
				return -1;
			}
		}
			
		//if trying to castle w/ your own piece- Ending position of king has to be empty
		else if ((board[nextRow][nextColumn] == null) && (board[currRow][currColumn].isFirstMove == true) 
				&& ( ( (nextRow == 0) && (nextColumn == 6) ) || ( (nextRow == 0) && (nextColumn == 2) ) || ( (nextRow == 7) && (nextColumn == 6) ) || ( (nextRow == 7) && (nextColumn == 2) ) )   
				) {
			
			if(checkIfCheck(board, player, currRow, currColumn) == true) { return -1; }	//return -1 if King is in check
			
			if(!player) { 		//black player
				
				if ((nextRow == 0) && (nextColumn == 6)) {								//king moving right (into position g8)
					if (board[0][7] != null) {											
						if ((board[0][7].pieceType.equals("Rook") == false) || (board[0][7].isFirstMove == false)) {		//return -1 if there is no rook/rook has moved already
							return -1;
						}
					}
					else {																//return -1 if there is no rook in board[0][7]
						return -1;
					}
					
					if ((board[0][5] != null) || (board[0][6] != null)) {				//return -1 if path is blocked
						return -1;
					}
					
					if(checkIfCheck(board, player, 0, 5) == true) { return -1; }		//return -1 if King would be in check if it castled
					if(checkIfCheck(board, player, 0, 6) == true) { return -1; }		//return -1 if King would be in check if it castled
					
					
					//allow castling
					board[nextRow][nextColumn] = board[currRow][currColumn];			//move king
					board[currRow][currColumn] = null;
					board[nextRow][nextColumn].isFirstMove = false;
					
					board[0][7].isFirstMove = false;
					board[0][5] = board[0][7];			//move rook
					board[0][7] = null;
					
					checkEnPassante(line, board, player); return 0;
				}
				
				else if ((nextRow == 0) && (nextColumn == 2)) {							//king moving left (into position c8)
					if (board[0][0] != null) {											
						if ((board[0][0].pieceType.equals("Rook") == false) || (board[0][0].isFirstMove == false)) {		//return -1 if there is no rook/rook has moved already
							return -1;
						}
					}
					else {																//return -1 if there is no rook in board[0][0]
						return -1;
					}
					
					if ((board[0][1] != null) || (board[0][2] != null) || (board[0][3] != null)) {				//return -1 if path is blocked
						return -1;
					}
					
					if(checkIfCheck(board, player, 0, 2) == true) { return -1; }		//return -1 if King would be in check if it castled
					if(checkIfCheck(board, player, 0, 3) == true) { return -1; }		//return -1 if King would be in check if it castled
					
					
					//allow castling
					board[nextRow][nextColumn] = board[currRow][currColumn];			//move king
					board[currRow][currColumn] = null;
					board[nextRow][nextColumn].isFirstMove = false;
					
					board[0][0].isFirstMove = false;
					board[0][3] = board[0][0];			//move rook
					board[0][0] = null;
					
					checkEnPassante(line, board, player); return 0;
				}
				
				
				//if black player: king not going to [0][2] or [0][6]
				else {
					return -1;
				}
			}
			
			
			else {			//white player
				if ((nextRow == 7) && (nextColumn == 6)) {								//king moving right (into position g1)
					if (board[7][7] != null) {											
						if ((board[7][7].pieceType.equals("Rook") == false) || (board[7][7].isFirstMove == false)) {		//return -1 if there is no rook/rook has moved already
							return -1;
						}
					}
					else {																//return -1 if there is no rook in board[7][7]
						return -1;
					}
					
					if ((board[7][5] != null) || (board[7][6] != null)) {				//return -1 if path is blocked
						return -1;
					}
					
					if(checkIfCheck(board, player, 7, 5) == true) { return -1; }		//return -1 if King would be in check if it castled
					if(checkIfCheck(board, player, 7, 6) == true) { return -1; }		//return -1 if King would be in check if it castled
					
					
					//allow castling
					board[nextRow][nextColumn] = board[currRow][currColumn];			//move king
					board[currRow][currColumn] = null;
					board[nextRow][nextColumn].isFirstMove = false;
					
					board[7][7].isFirstMove = false;
					board[7][5] = board[7][7];			//move rook
					board[7][7] = null;
					
					checkEnPassante(line, board, player); return 0;
				}
				
				else if ((nextRow == 7) && (nextColumn == 2)) {							//king moving left (into position c1)
					if (board[7][0] != null) {											
						if ((board[7][0].pieceType.equals("Rook") == false) || (board[7][0].isFirstMove == false)) {		//return -1 if there is no rook/rook has moved already
							return -1;
						}
					}
					else {																//return -1 if there is no rook in board[7][0]
						return -1;
					}
					
					if ((board[7][1] != null) || (board[7][2] != null) || (board[7][3] != null)) {				//return -1 if path is blocked
						return -1;
					}
					
					if(checkIfCheck(board, player, 7, 2) == true) { return -1; }		//return -1 if King would be in check if it castled
					if(checkIfCheck(board, player, 7, 3) == true) { return -1; }		//return -1 if King would be in check if it castled
					
					
					//allow castling
					board[nextRow][nextColumn] = board[currRow][currColumn];			//move king
					board[currRow][currColumn] = null;
					board[nextRow][nextColumn].isFirstMove = false;
					
					board[7][0].isFirstMove = false;
					board[7][3] = board[7][0];			//move rook
					board[7][0] = null;
					
					checkEnPassante(line, board, player); return 0;
				}
				
				
				//if white player: king not going to [7][2] or [7][6]
				else {
					return -1;
				}
			}
		}  //out of castling

			
		
		
		//if not castling
		//next space is null and king has moved already
		else {
			if((nextColumn > currColumn + 1) || (nextColumn < currColumn - 1)) { return -1; }			//checks if king is only moving 1 position away
			if((nextRow > currRow + 1) || (nextRow < currRow - 1)) { return -1; }	
			if(checkIfCheck(board, player, nextRow, nextColumn) == true) { return -1; }		//return -1 if King would be in check if it castled
			
			if(!player) { 				//black player
				
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
			
			else {						//white player
				
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
		}
	}
	
	
	
	

}
