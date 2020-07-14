

	package pieces;

	public class Pawn extends Piece{
		
		Boolean isFirstMove;
			
		public Pawn(Boolean isWhite) {
				
			super(isWhite);	
			this.pieceType = "Pawn";
			this.isFirstMove = true;		
		}
		
		@Override
		public String toString()  {
			String type = "";
			
			if (isWhite == true) {
				type = "wp";
			}
			
			else {
				type = "bp";
			}
			
			return type;
		}
		
		@Override
		public int move(String move, Piece[][] board, boolean player) {

			boolean checkForPromotion = false;
			boolean isEnPassante = false;
			
			int currColumn = move.charAt(0);
			currColumn = currColumn - 97;
			
			int nextColumn = move.charAt(3);
			nextColumn = nextColumn - 97;
			
			int currRow = 8 - Character.getNumericValue(move.charAt(1));
				
			int nextRow = 8 - Character.getNumericValue(move.charAt(4));
			
			if((currColumn == nextColumn) && (currRow == nextRow)) { return -1; }			//if curr and next are same positions
			
			if(board[currRow][currColumn] == null) {return -1;}		//Checks the position to ensure there is a piece there.
			
			if(board[currRow][currColumn].isWhite != player) {return -1;} //Checks if the piece being moved is the same color as the color the player is in the game.
			

			
			if(!player) { //if player is black			
				if(currRow >= nextRow) {return -1;} // if the move given requires the pawn to move upwards or directly to the right, which is not allowed.
				
				if(isFirstMove) { //if this is the first move for the pawn.
					if( (currColumn == nextColumn) && ((currRow+1 == nextRow) || (currRow+2 == nextRow)) ) { //if the pawn is moving vertically down one or two spaces
						if(board[nextRow][nextColumn] == null) { //if there is no piece in the position prior.
							board[nextRow][nextColumn] = board[currRow][currColumn];
							board[currRow][currColumn] = null;
							if(checkIfCheck(board, player, 23, 23) ) { // if the move puts itself in check.
								board[currRow][currColumn] = board[nextRow][nextColumn];
								board[nextRow][nextColumn] = null;
								return -1;
							}
							isFirstMove = false;
							checkForPromotion = true;
							if(currRow+2 == nextRow) {
								isEnPassante = true;
							}
						}
					/* If the move is a capture */
					}else if((currColumn-1 == nextColumn) || (currColumn+1 == nextColumn)) { //if pawn is moving away one column.
						if(currRow+1 == nextRow) { //if pawn is only moving down one row.
							if((board[nextRow][nextColumn] != null) && (board[nextRow][nextColumn].isWhite)) { //check if there is a piece to be captured, and check to ensure the piece color is white.
								board[nextRow][nextColumn] = board[currRow][currColumn];
								board[currRow][currColumn] = null;
								if(checkIfCheck(board, player, 23, 23) ) { // if the move puts itself in check.
									board[currRow][currColumn] = board[nextRow][nextColumn];
									board[nextRow][nextColumn] = null;
									return -1;
								}
								isFirstMove = false;
								checkForPromotion = true;
							}
						}
					}
				}else { //if this is not the first move for the pawn.
					if( (currColumn == nextColumn) && (currRow+1 == nextRow)) { //if the pawn is moving vertically down one space.
						if(board[nextRow][nextColumn] == null) {	//if there is no piece in the position prior.
							board[nextRow][nextColumn] = board[currRow][currColumn];
							board[currRow][currColumn] = null;
							if(checkIfCheck(board, player, 23, 23) ) { // if the move puts itself in check.
								board[currRow][currColumn] = board[nextRow][nextColumn];
								board[nextRow][nextColumn] = null;
								return -1;
							}
							checkForPromotion = true;
						}
					/* If the move is a capture */
					}else if((currColumn-1 == nextColumn) || (currColumn+1 == nextColumn)) { //if pawn is moving away one column.
						if(currRow+1 == nextRow) { //if pawn is only moving down one row.
							if((board[nextRow][nextColumn] != null) && (board[nextRow][nextColumn].isWhite)) { //check if there is a piece to be captured, and check to ensure the piece color is white.
								board[nextRow][nextColumn] = board[currRow][currColumn];
								board[currRow][currColumn] = null;
								if(checkIfCheck(board, player, 23, 23) ) { // if the move puts itself in check.
									board[currRow][currColumn] = board[nextRow][nextColumn];
									board[nextRow][nextColumn] = null;
									return -1;
								}
								checkForPromotion = true;
							}
						}
					}
				}

			
			}else {			//if player is white
				if(currRow <= nextRow) {return -1;} // if the move given requires the pawn to move downwards or directly to the right, which is not allowed.
				
				if(this.isFirstMove) { //if this is the first move for the pawn.
					if( (currColumn == nextColumn) && ((currRow-1 == nextRow) || (currRow-2 == nextRow)) ) { //if the pawn is moving vertically up one or two spaces
						if(board[nextRow][nextColumn] == null) { //if there is no piece in the position prior.
							board[nextRow][nextColumn] = board[currRow][currColumn];
							board[currRow][currColumn] = null;
							if(checkIfCheck(board, player, 23, 23) ) { // if the move puts itself in check.
								board[currRow][currColumn] = board[nextRow][nextColumn];
								board[nextRow][nextColumn] = null;
								return -1;
							}
							isFirstMove = false;
							checkForPromotion = true;
						}
					/* If the move is a capture */
					}else if((currColumn-1 == nextColumn) || (currColumn+1 == nextColumn)) { //if pawn is moving away one column.
						if(currRow-1 == nextRow) { //if pawn is only moving up one row.
							if((board[nextRow][nextColumn] != null) && (!board[nextRow][nextColumn].isWhite)) { //check if there is a piece to be captured, and check to ensure the piece color is black.
								board[nextRow][nextColumn] = board[currRow][currColumn];
								board[currRow][currColumn] = null;
								if(checkIfCheck(board, player, 23, 23) ) { // if the move puts itself in check.
									board[currRow][currColumn] = board[nextRow][nextColumn];
									board[nextRow][nextColumn] = null;
									return -1;
								}
								isFirstMove = false;
								checkForPromotion = true;
							}
						}
					}
				}else { //if this is not the first move for the pawn.
					if( (currColumn == nextColumn) && (currRow-1 == nextRow)) { //if the pawn is moving vertically up one space.
						if(board[nextRow][nextColumn] == null) {	//if there is no piece in the position prior.
							board[nextRow][nextColumn] = board[currRow][currColumn];
							board[currRow][currColumn] = null;
							if(checkIfCheck(board, player, 23, 23) ) { // if the move puts itself in check.
								board[currRow][currColumn] = board[nextRow][nextColumn];
								board[nextRow][nextColumn] = null;
								return -1;
							}
							checkForPromotion = true;
						}
					/* If the move is a capture */
					}else if((currColumn-1 == nextColumn) || (currColumn+1 == nextColumn)) { //if pawn is moving away one column.
						if(currRow-1 == nextRow) { //if pawn is only moving up one row.
							if((board[nextRow][nextColumn] != null) && (!board[nextRow][nextColumn].isWhite)) { //check if there is a piece to be captured, and check to ensure the piece color is black.
								board[nextRow][nextColumn] = board[currRow][currColumn];
								board[currRow][currColumn] = null;
								if(checkIfCheck(board, player, 23, 23) ) { // if the move puts itself in check.
									board[currRow][currColumn] = board[nextRow][nextColumn];
									board[nextRow][nextColumn] = null;
									return -1;
								}
								checkForPromotion = true;
							}
						}
					}
				}
			}
			
			
			if(checkForPromotion) { //checks if the pawn is due for a promotion.
				if(nextRow == 0 || nextRow == 7) {
					if(move.length() == 7) {
						if(move.charAt(6) == 'N') {
							board[nextRow][nextColumn] = new Knight(player);
						}else if(move.charAt(6) == 'B') {
							board[nextRow][nextColumn] = new Bishop(player);
						}else if(move.charAt(6) == 'R') {
							board[nextRow][nextColumn] = new Rook(player);
						}else if(move.charAt(6) == 'Q') {
							board[nextRow][nextColumn] = new Queen(player);
						}
					}else {
						board[nextRow][nextColumn] = new Queen(player);
					}
				}else {
					if(move.length() == 7) {return -1;} // player requests for a promotion when pawn is not due for one.
				}
				checkEnPassante(move, board, player); 
				if(isEnPassante) {
					enPassante = true;
				}
				return 0;
				
			}
			
			return -1;
		}
			
	}