package pieces;


/**
 * 
 * @author Sara Crespo
 * @author Ricky Lopez
 *
 * Piece superclass
 */
public class Piece {

		
	public boolean isWhite;
	boolean isFirstMove;
	public String pieceType;
	static boolean enPassante;
	
	/**
	 * 1 arg constructor
	 * @param x True if current player is white, False if current player is black
	 */
	public Piece(boolean x) {
		this.isWhite = x;
		this.isFirstMove = true;
		this.pieceType = "Piece";
		enPassante = false;
	}
	
	public void move(Piece[][] board, String currPosition, String destination) {
		
	}


	public String toString() {
		String type = "";
		
		return type;
	}
	
	public int move(String input, Piece[][] board, boolean player) {
		return 0;
	}

	/**
	 * Checks to see if a piece is in EnPassante, changes field of piece accordingly
	 * @param move The string instructions inputed by user
	 * @param board the two-dimensional array board that holds all the piece information
	 * @param player True if white player, False if black player
	 */
	public void checkEnPassante(String move, Piece[][]board, boolean player) {

        int nextColumn = move.charAt(3);
        nextColumn = nextColumn - 97;

        int nextRow = 8 - Character.getNumericValue(move.charAt(4));
        if(player) {        //if player is white.
            if(nextRow == 2) {
                if(board[nextRow+1][nextColumn] != null) { // if there is a piece directly under the next move
                    if( (board[nextRow+1][nextColumn].pieceType.equals("Pawn")) && (!board[nextRow+1][nextColumn].isWhite) ) {        //if the piece is a black pawn
                        if(Piece.enPassante) { //if the pawn has just moved 2 spaces.
                            board[nextRow+1][nextColumn] = null;
                            Piece.enPassante = false;
                        }
                    }
                }

            }
        }else {            //if player is black.
            if(nextRow == 5) {
                if(board[nextRow-1][nextColumn] != null) { // if there is a piece directly above the next move
                    if(board[nextRow-1][nextColumn].pieceType == "Pawn" && board[nextRow-1][nextColumn].isWhite) {        //if the piece is a white pawn
                        if(Piece.enPassante) { //if the pawn has just moved 2 spaces.
                            board[nextRow-1][nextColumn] = null;
                            Piece.enPassante = false;
                        }
                    }
                }

            }
        }
        Piece.enPassante = false;
        return;
    }
	
	//returns true if spot is in check, false if spot is not in check
	/*public boolean checkIfCheck(Piece[][] board, boolean player, int rowOfKing, int columnOfKing) {
		
		//if xBoard[i][j] is true, that means that position (i,j) is taken by your own piece already or is threatened by an enemy piece
		boolean[][] xBoard = new boolean[8][8];
		
		Piece[][] board2 = new Piece[8][8];
		for(int i = 0; i < 8; i++)
			  for(int j = 0; j < 8; j++)
			    board2[i][j] = board[i][j];
		
		//white player turn
		if (player == true) {
			
			for(int i = 0; i < 8; i++) {							
				for(int j = 0; j < 8; j++) {
					if(board[i][j] != null) {
						if ((board[i][j].isWhite == true) && (board[i][j].pieceType.equals("King") == false)) {		//put an X in all positions that have your own pieces (except king)
							xBoard[i][j] = true;
						}
						
						else if ((board[i][j].isWhite == false)) {				//put an X in all positions threatened by piece at position (i,j)
							for(int i2 = 0; i2 < 8; i2++) {							
								for(int j2 = 0; j2 < 8; j2++) {
									
		//LOOP START
									boolean done = false;

									while (done == false) {
										int row = i2;
										int column = j2;
										
										String ctc = "";
										if (column == 0) { ctc = "a"; }
										else if (column == 1) { ctc = "b"; }
										else if (column == 2) { ctc = "c"; }
										else if (column == 3) { ctc = "d"; }
										else if (column == 4) { ctc = "e"; }
										else if (column == 5) { ctc = "f"; }
										else if (column == 6) { ctc = "g"; }
										else if (column == 7) { ctc = "h"; }
										
										String startC = "";
										
										if (j == 0) { startC = "a"; }
										else if (j == 1) { startC = "b"; }
										else if (j == 2) { startC = "c"; }
										else if (j == 3) { startC = "d"; }
										else if (j == 4) { startC = "e"; }
										else if (j == 5) { startC = "f"; }
										else if (j == 6) { startC = "g"; }
										else if (j == 7) { startC = "h"; }
										
										String line = startC + i + " " + ctc + row;
										
										if (board2[row][column].move(line, board2, player) != -1) {		//if valid move
											xBoard[row][column] = true;
										}
										
										for(int x = 0; x < 8; x++)					//reset board2 for next move
											  for(int y = 0; y < 8; y++)
											    board2[x][y] = board[x][y];
										
										if ((i2 == 7) && (j2 == 7)) {
											done = true;
										}
									}
		//LOOP END
									
								}
							}

							
							
						}  //out of put X in all positions threatened
					}
				}
			}
			
			
		}
		
		//black player turn
		else {
			
			for(int i = 0; i < 8; i++) {							
				for(int j = 0; j < 8; j++) {
					if(board[i][j] != null) {
						if ((board[i][j].isWhite == false) && (board[i][j].pieceType.equals("King") == false)) {		//put an X in all positions that have your own pieces (except king)
							xBoard[i][j] = true;
						}
						
						else if ((board[i][j].isWhite == true)) {				//put an X in all positions threatened by piece at position (i,j)
							for(int i2 = 0; i2 < 8; i2++) {							
								for(int j2 = 0; j2 < 8; j2++) {
									
		//LOOP START
									boolean done = false;
									
									while (done == false) {
										//System.out.println("loop");
										int row = i2;
										int column = j2;
										
										String i2c = Integer.toString(i);
										String row2c = Integer.toString(row);
										
										String ctc = "";
										
										if (column == 0) { ctc = "a"; }
										else if (column == 1) { ctc = "b"; }
										else if (column == 2) { ctc = "c"; }
										else if (column == 3) { ctc = "d"; }
										else if (column == 4) { ctc = "e"; }
										else if (column == 5) { ctc = "f"; }
										else if (column == 6) { ctc = "g"; }
										else if (column == 7) { ctc = "h"; }
										
										String startC = "";
										
										if (j == 0) { startC = "a"; }
										else if (j == 1) { startC = "b"; }
										else if (j == 2) { startC = "c"; }
										else if (j == 3) { startC = "d"; }
										else if (j == 4) { startC = "e"; }
										else if (j == 5) { startC = "f"; }
										else if (j == 6) { startC = "g"; }
										else if (j == 7) { startC = "h"; }
										
										String line = startC + i2c + " " + ctc + row2c;
										
										
										System.out.println(line);
										if (board2[row][column] != null) {
											if (board2[row][column].move(line, board2, player) != -1) {		//if valid move
												xBoard[row][column] = true;
											}
										}
										//System.out.println("after move");
										
										for(int x = 0; x < 8; x++)					//reset board2 for next move
											  for(int y = 0; y < 8; y++)
											    board2[x][y] = board[x][y];
										
					
										//if ((i2 == 7) && (j2 == 7)) {
											done = true;
											i=7;
										//}
										
										//System.out.println("end of loop");
									}
									//System.out.println("out of loop");
		//LOOP END
									
								}
							}

							
							
						}  //out of put X in all positions threatened
					}
				}
			}
		}
		
		if (xBoard[rowOfKing][columnOfKing] == true) {
			return true;
		}
		
		return false;
		
	}*/

	/*
	public boolean checkIfCheckmate (Piece[][] board, boolean player, int rowOfKing, int columnOfKing) {

		int tempR = rowOfKing;
		int tempC = columnOfKing;
		
		boolean isCheckmate = true;
		
		//check current position
		if ((tempR >= 0) && (tempR <= 7) && (tempC >= 0) && (tempC <= 7)) {
			if (checkIfCheck(board, player, tempR, tempC) == false){ isCheckmate == false; }
		}

		//check bottom position
		tempR = rowOfKing + 1;
		tempC = columnOfKing;
		if ((tempR >= 0) && (tempR <= 7) && (tempC >= 0) && (tempC <= 7)) {
		if ((tempR >= 0) && (tempR <= 7) && (tempC >= 0) && (tempC <= 7)) {
			if ((checkIfCheck(board, player, tempR, tempC) == false) && (board[tempR][tempC] != null)){ 
				if (board[tempR][tempC].isWhite != player) { isCheckmate = false; }
			}
			else if ((checkIfCheck(board, player, tempR, tempC) == false) && (board[tempR][tempC] == null)){ 
				isCheckmate = false;
			}
		}

		//check bottom-right position
		tempR = rowOfKing + 1;
		tempC = columnOfKing + 1;
		if ((tempR >= 0) && (tempR <= 7) && (tempC >= 0) && (tempC <= 7)) {
		if ((tempR >= 0) && (tempR <= 7) && (tempC >= 0) && (tempC <= 7)) {
			if ((checkIfCheck(board, player, tempR, tempC) == false) && (board[tempR][tempC] != null)){ 
				if (board[tempR][tempC].isWhite != player) { isCheckmate = false; }
			}
			else if ((checkIfCheck(board, player, tempR, tempC) == false) && (board[tempR][tempC] == null)){ 
				isCheckmate = false;
			}
		}
		
		//check bottom-left position
		tempR = rowOfKing + 1;
		tempC = columnOfKing - 1;
		if ((tempR >= 0) && (tempR <= 7) && (tempC >= 0) && (tempC <= 7)) {
		if ((tempR >= 0) && (tempR <= 7) && (tempC >= 0) && (tempC <= 7)) {
			if ((checkIfCheck(board, player, tempR, tempC) == false) && (board[tempR][tempC] != null)){ 
				if (board[tempR][tempC].isWhite != player) { isCheckmate = false; }
			}
			else if ((checkIfCheck(board, player, tempR, tempC) == false) && (board[tempR][tempC] == null)){ 
				isCheckmate = false;
			}
		}

		//check right position
		tempR = rowOfKing;
		tempC = columnOfKing + 1;
		if ((tempR >= 0) && (tempR <= 7) && (tempC >= 0) && (tempC <= 7)) {
		if ((tempR >= 0) && (tempR <= 7) && (tempC >= 0) && (tempC <= 7)) {
			if ((checkIfCheck(board, player, tempR, tempC) == false) && (board[tempR][tempC] != null)){ 
				if (board[tempR][tempC].isWhite != player) { isCheckmate = false; }
			}
			else if ((checkIfCheck(board, player, tempR, tempC) == false) && (board[tempR][tempC] == null)){ 
				isCheckmate = false;
			}
		}

		//check left position
		tempR = rowOfKing;
		tempC = columnOfKing - 1;
		if ((tempR >= 0) && (tempR <= 7) && (tempC >= 0) && (tempC <= 7)) {
		if ((tempR >= 0) && (tempR <= 7) && (tempC >= 0) && (tempC <= 7)) {
			if ((checkIfCheck(board, player, tempR, tempC) == false) && (board[tempR][tempC] != null)){ 
				if (board[tempR][tempC].isWhite != player) { isCheckmate = false; }
			}
			else if ((checkIfCheck(board, player, tempR, tempC) == false) && (board[tempR][tempC] == null)){ 
				isCheckmate = false;
			}
		}

		//check top-left position
		tempR = rowOfKing - 1;
		tempC = columnOfKing - 1;
		if ((tempR >= 0) && (tempR <= 7) && (tempC >= 0) && (tempC <= 7)) {
		if ((tempR >= 0) && (tempR <= 7) && (tempC >= 0) && (tempC <= 7)) {
			if ((checkIfCheck(board, player, tempR, tempC) == false) && (board[tempR][tempC] != null)){ 
				if (board[tempR][tempC].isWhite != player) { isCheckmate = false; }
			}
			else if ((checkIfCheck(board, player, tempR, tempC) == false) && (board[tempR][tempC] == null)){ 
				isCheckmate = false;
			}
		}

		//check top-right position
		tempR = rowOfKing - 1;
		tempC = columnOfKing + 1;
		if ((tempR >= 0) && (tempR <= 7) && (tempC >= 0) && (tempC <= 7)) {
		if ((tempR >= 0) && (tempR <= 7) && (tempC >= 0) && (tempC <= 7)) {
			if ((checkIfCheck(board, player, tempR, tempC) == false) && (board[tempR][tempC] != null)){ 
				if (board[tempR][tempC].isWhite != player) { isCheckmate = false; }
			}
			else if ((checkIfCheck(board, player, tempR, tempC) == false) && (board[tempR][tempC] == null)){ 
				isCheckmate = false;
			}
		}

		//check top position
		tempR = rowOfKing - 1;
		tempC = columnOfKing;
		if ((tempR >= 0) && (tempR <= 7) && (tempC >= 0) && (tempC <= 7)) {
		if ((tempR >= 0) && (tempR <= 7) && (tempC >= 0) && (tempC <= 7)) {
			if ((checkIfCheck(board, player, tempR, tempC) == false) && (board[tempR][tempC] != null)){ 
				if (board[tempR][tempC].isWhite != player) { isCheckmate = false; }
			}
			else if ((checkIfCheck(board, player, tempR, tempC) == false) && (board[tempR][tempC] == null)){ 
				isCheckmate = false;
			}
		}

		if (isCheckmate) {
			return true;
		}
		
		return false;
	}*/
	
	/**
	 * Checks if the king is in Check
	 * @param board The two-dimensional array board that holds all the piece information
	 * @param player True if white player, False if black player
	 * @param rowOfKing Integer row that the king is in
	 * @param columnOfKing Integer column that the king is in
	 * @return True if the king is in check, false if king is not in check
	 */
	public static boolean checkIfCheck(Piece[][] board, boolean player, int kingRow, int kingColumn) {
		
		if(kingRow == 23 && kingColumn == 23) { // Special indicator showing that we must find the King ourselves.

            D: for(int b = 0; b < 8; b++) {
                for(int c = 0; c < 8; c++) {
                    if (board[b][c] != null) {
                        if ( board[b][c].isWhite == player && board[b][c].pieceType.equals("King")) {
                            kingRow = b;
                            kingColumn = c;

                            break D;
                        }
                    }
                }
            }
			
		}
		
		int yAxis = kingRow;
		int xAxis = kingColumn;
		//System.out.println(kingRow + " " + kingColumn);
		
		
		if(true) { 
		
/*====BISHOP/KING CHECK================================================================================================================================*/
		
			while(yAxis !=  7 && xAxis != 7) {		//UPPER RIGHT DIAGONAL
				yAxis++; xAxis++;
				if(board[yAxis][xAxis] != null) { // if there is a piece along the upper right diagonal of the King.
					if(board[yAxis][xAxis].isWhite != player) { //if the piece is the opposite color of the King.
						if(board[yAxis][xAxis].pieceType.equals("Queen") || board[yAxis][xAxis].pieceType.equals("Bishop")) {
							return true;
						}
					}else { // King is safe along this diagonal.
						break;
					}
				}
			}
			
			 yAxis = kingRow;
			 xAxis = kingColumn;
			 
			
			while(yAxis != 7 && xAxis != 0) {		//UPPER LEFT DIAGONAL
				yAxis++; xAxis--;
				if(board[yAxis][xAxis] != null) { // if there is a piece along the upper left diagonal of the King.
					if(board[yAxis][xAxis].isWhite != player) { //if the piece is the opposite color of the King.
						if(board[yAxis][xAxis].pieceType.equals("Queen") || board[yAxis][xAxis].pieceType.equals("Bishop")) {
							return true;
						}
					}else { // King is safe along this diagonal.
						break;
					}
				}
			}
			
			 yAxis = kingRow;
			 xAxis = kingColumn;
			
			while(yAxis != 0 && xAxis != 7) {		//LOWER RIGHT DIAGONAL
				yAxis--; xAxis++;
				if(board[yAxis][xAxis] != null) { // if there is a piece along the lower right diagonal of the King.
					if(board[yAxis][xAxis].isWhite != player) { //if the piece is the opposite color of the King.
						if(board[yAxis][xAxis].pieceType.equals("Queen") || board[yAxis][xAxis].pieceType.equals("Bishop")) {
							return true;
						}
					}else { // King is safe along this diagonal.
						break;
					}
				}
			}
			
			 yAxis = kingRow;
			 xAxis = kingColumn;
			
			while(yAxis != 0 && xAxis != 0) {		//LOWER LEFT DIAGONAL
				yAxis--; xAxis--;
				if(board[yAxis][xAxis] != null) { // if there is a piece along the upper right diagonal of the King.
					if(board[yAxis][xAxis].isWhite != player) { //if the piece is the opposite color of the King.
						if(board[yAxis][xAxis].pieceType.equals("Queen") || board[yAxis][xAxis].pieceType.equals("Bishop")) {
							return true;
						}
					}else { // King is safe along this diagonal.
						break;
					}
				}
			}
			
			 yAxis = kingRow;
			 xAxis = kingColumn;
		
		
/*====ROOK/KING CHECK==================================================================================================================================*/
			
			while(xAxis < 7) {		//TO THE RIGHT
				xAxis++;
				if(board[yAxis][xAxis] != null) { // if there is a piece to the right of the King.
					if(board[yAxis][xAxis].isWhite != player) { //if the piece is the opposite color of the King.
						if(board[yAxis][xAxis].pieceType.equals("Queen") || board[yAxis][xAxis].pieceType.equals("Rook")) {
							return true;
						}
					}else { // King is safe along this line.
						break;
					}
				}
			}
			
			yAxis = kingRow;
			xAxis = kingColumn;
			
			while(xAxis > 0) {		//TO THE LEFT
				xAxis--;
				if(board[yAxis][xAxis] != null) { // if there is a piece to the left of the King.
					if(board[yAxis][xAxis].isWhite != player) { //if the piece is the opposite color of the King.
						if(board[yAxis][xAxis].pieceType.equals("Queen") || board[yAxis][xAxis].pieceType.equals("Rook")) {
							return true;
						}
					}else { // King is safe along this line.
						break;
					}
				}
			}
			
			yAxis = kingRow;
			xAxis = kingColumn;
			
			while(yAxis < 7) {		//DIRECTLY ABOVE
				yAxis++;
				if(board[yAxis][xAxis] != null) { // if there is a piece to the right of the King.
					if(board[yAxis][xAxis].isWhite != player) { //if the piece is the opposite color of the King,
						if(board[yAxis][xAxis].pieceType.equals("Queen") || board[yAxis][xAxis].pieceType.equals("Rook")) {
							return true;
						}
					}else { // King is safe along this line.
						break;
					}
				}
			}
			
			yAxis = kingRow;
			xAxis = kingColumn;
			
			while(yAxis > 0) {		//DIRECTLY BELOW
				yAxis--;
				if(board[yAxis][xAxis] != null) { // if there is a piece to the right of the King.
					if(board[yAxis][xAxis].isWhite != player) { //if the piece is the opposite color of the King.
						if(board[yAxis][xAxis].pieceType.equals("Queen") || board[yAxis][xAxis].pieceType.equals("Rook")) {
							return true;
						}
					}else { // King is safe along this line.
						break;
					}
				}
			}
			
			yAxis = kingRow;
			xAxis = kingColumn;
			

			
/*====KNIGHT CHECK=====================================================================================================================================*/
			
			if( (yAxis+2 < 7) && (xAxis+1 < 7) ){		//UP 2, RIGHT 1
				
				if(board[yAxis+2][xAxis+1] != null) { // if there is a piece a knight's move away from the King.
					if(board[yAxis+2][xAxis+1].isWhite != player) { //if the piece is the opposite color of the King.
						if(board[yAxis+2][xAxis+1].pieceType.equals("Knight")) {
							return true;
						}
					}
				}
			}
			
			if( (yAxis+1 < 7) && (xAxis+2 < 7) ){		//UP 1, RIGHT 2
				
				if(board[yAxis+1][xAxis+2] != null) { // if there is a piece a knight's move away from the King.
					if(board[yAxis+1][xAxis+2].isWhite != player) { //if the piece is the opposite color of the King.
						if(board[yAxis+1][xAxis+2].pieceType.equals("Knight")) {
							return true;
						}
					}
				}
			}
			
			
			if( (yAxis+2 < 7) && (xAxis-1 > 0) ){		//UP 2, LEFT 1
				if(board[yAxis+2][xAxis-1] != null) { // if there is a piece a knight's move away from the King.
					if(board[yAxis+2][xAxis-1].isWhite != player) { //if the piece is the opposite color of the King.
						if(board[yAxis+2][xAxis-1].pieceType.equals("Knight")) {
							return true;
						}
					}
				}
			}
			
			if( (yAxis+1 < 7) && (xAxis-2 > 0) ){		//UP 1, LEFT 2
				
				if(board[yAxis+1][xAxis-2] != null) { // if there is a piece a knight's move away from the King.
					if(board[yAxis+1][xAxis-2].isWhite != player) { //if the piece is the opposite color of the King.
						if(board[yAxis+1][xAxis-2].pieceType.equals("Knight")) {
							return true;
						}
					}
				}
			}
			
			if( (yAxis-2 > 0) && (xAxis+1 < 7) ){		//DOWN 2, RIGHT 1
				
				if(board[yAxis-2][xAxis+1] != null) { // if there is a piece a knight's move away from the King.
					if(board[yAxis-2][xAxis+1].isWhite != player) { //if the piece is the opposite color of the King.
						if(board[yAxis-2][xAxis+1].pieceType.equals("Knight")) {
							return true;
						}
					}
				}
			}
			
			if( (yAxis-2 > 0) && (xAxis-1 > 0) ){		//DOWN 2, LEFT 1
				
				if(board[yAxis-2][xAxis-1] != null) { // if there is a piece a knight's move away from the King.
					if(board[yAxis-2][xAxis-1].isWhite != player) { //if the piece is the opposite color of the King.
						if(board[yAxis-2][xAxis-1].pieceType.equals("Knight")) {
							return true;
						}
					}
				}
			}
			
			if( (yAxis-1 > 0) && (xAxis+2 < 7) ){		//DOWN 1, RIGHT 2
				
				if(board[yAxis-1][xAxis+2] != null) { // if there is a piece a knight's move away from the King.
					if(board[yAxis-1][xAxis+2].isWhite != player) { //if the piece is the opposite color of the King.
						if(board[yAxis-1][xAxis+2].pieceType.equals("Knight")) {
							return true;
						}
					}
				}
			}
			
			if( (yAxis-1 > 0) && (xAxis-2 > 0) ){		//DOWN 1, LEFT 2
				
				if(board[yAxis-1][xAxis-2] != null) { // if there is a piece a knight's move away from the King.
					if(board[yAxis-1][xAxis-2].isWhite != player) { //if the piece is the opposite color of the King.
						if(board[yAxis-1][xAxis-2].pieceType.equals("Knight")) {
							return true;
						}
					}
				}
			}
			
			
/*====PAWN CHECK=====================================================================================================================================*/
			 
			if(player) { //if the player is white.

				if( (yAxis-1 > 0) && (xAxis+1 < 7) ) { 		//UPPER RIGHT
					if(board[yAxis-1][xAxis+1] != null) { // if there is a piece in the upper right corner of the King.
						if(!board[yAxis-1][xAxis+1].isWhite) { // if the piece is black.
							if(board[yAxis-1][xAxis+1].pieceType.equals("Pawn")) {
								return true;
							}
						}
					}
					
				}
				
				if( (yAxis-1 > 0) && (xAxis-1 > 0) ) { 		//UPPER LEFT
					if(board[yAxis-1][xAxis-1] != null) { // if there is a piece in the upper right corner of the King.
						if(!board[yAxis-1][xAxis-1].isWhite) { // if the piece is black.
							if(board[yAxis-1][xAxis-1].pieceType.equals("Pawn")) {
								return true;
							}
						}
					}
					
				}
				
			}else { // if the player is black.
				if( (yAxis+1 < 7) && (xAxis+1 < 7) ) { 		//LOWER RIGHT
					if(board[yAxis+1][xAxis+1] != null) { // if there is a piece in the upper right corner of the King.
						if(board[yAxis+1][xAxis+1].isWhite) { // if the piece is black.
							if(board[yAxis+1][xAxis+1].pieceType.equals("Pawn")) {
								return true;
							}
						}
					}
					
				}
				
				if( (yAxis+1 < 7) && (xAxis-1 > 0) ) { 		//LOWER LEFT
					if(board[yAxis+1][xAxis-1] != null) { // if there is a piece in the upper right corner of the King.
						if(board[yAxis+1][xAxis-1].isWhite) { // if the piece is black.
							if(board[yAxis+1][xAxis-1].pieceType.equals("Pawn")) {
								return true;
							}
						}
					}
					
				}
				
			}
			
			
		}
		return false;
	}

}
	
