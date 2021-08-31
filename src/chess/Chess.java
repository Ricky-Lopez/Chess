package chess;
import pieces.*;
import java.util.Scanner;



/**
 * 
 * @author Sara Crespo
 * @author Ricky Lopez
 * 
 * Main class for Chess Project
 *
 */
public class Chess {

	/**
	 * main method for Chess.  Ends when there is a draw, resignation, or checkmate
	 * @param args
	 */
	public static void main(String[] args) {
		Piece[][] board = new Piece[8][8];
		boolean wantsDraw = false;
		boolean end = false;
		boolean checkmate = false;
		boolean player = true;													//true = white, false = black
		
		initializeBoard(board);
		
		printBoard(board);
		
		/*
		System.out.println(
				"bR bN bB bQ bK bB bN bR 8" + "\n" +
				"bp bp bp bp bp bp bp bp 7" + "\n" +
				"   ##    ##    ##    ## 6" + "\n" +
				"##    ##    ##    ##    5" + "\n" +
				"   ##    ##    ##    ## 4" + "\n" +
				"##    ##    ##    ##    3" + "\n" +
				"wp wp wp wp wp wp wp wp 2" + "\n" +
				"wR wN wB wQ wK wB wN wR 1" + "\n" +
				" a  b  c  d  e  f  g  h");
		
		System.out.println();*/
		
		System.out.print("White's move: ");
		
		Scanner scanner = new Scanner(System.in);
		String line = scanner.nextLine();
		
		String[] tokens = line.split(" ");
		
		System.out.println();
		
		if(checkIfResign(tokens[0], player)) {return;} //Player resigns- match ends
		
		//check if valid move
		
		
		boolean invalid_move = true;
		while (invalid_move == true) {
			int row = 8 - Character.getNumericValue(line.charAt(1));
			int column = line.charAt(0);
			column = column - 97;
			
			if (board[row][column] != null) {
				if (board[row][column].move(line, board, player) == -1) {		//invalid move- ask for new input
					System.out.println("Illegal move, try again");
					
					if (player == true) { 										//current player => white
						System.out.print("White's move: ");
					}
					
					else { 														//current player => black
						System.out.print("Black's move: ");
					}
					
					line = scanner.nextLine();
					tokens = null;
					tokens = line.split(" ");
					
					System.out.println();
					
					if(checkIfResign(tokens[0], player)) {return;}			//Player resigns- match ends
					
				}
				
				else {															//valid move
					
					if(checkIfResign(tokens[0], player)) {return;}			//Player resigns- match ends
					invalid_move = false;
				}
			}
			else {															//valid move
				System.out.println("Illegal move, try again");
				
				if (player == true) { 										//current player => white
					System.out.print("White's move: ");
				}
				
				else { 														//current player => black
					System.out.print("Black's move: ");
				}
				
				line = scanner.nextLine();
				tokens = null;
				tokens = line.split(" ");
				
				System.out.println();
				
				if(checkIfResign(tokens[0], player)) {return;}			//Player resigns- match ends
			}
		}
		
		if (tokens.length == 3) {									//checks if player asks for a draw
			if (tokens[2].equals("draw?")) {
				wantsDraw = true;
			}
		}
		else if (tokens.length == 4 ) {
			if (tokens[2].equals("draw?") || tokens[3].equals("draw?")) {
				wantsDraw = true;
			}
		}
		
//=====START OF WHILE LOOP=============================================================================================================================
		
		while (end == false) {
			printBoard(board);											//creates ASCII string board from board object
			
			if (player == true) { 										//current player => black
				player = false; 
				System.out.print("Black's move: ");
			}
			
			else { 														//current player => white
				player = true; 
				System.out.print("White's move: ");
			}
			
			line = scanner.nextLine();
			tokens = null;
			tokens = line.split(" ");
			
			System.out.println();
			
			if(tokens[0].equals("resign")){								//Player resigns- match ends
				//System.out.println("**match ends in resign");
				if (player == true) {
					System.out.println("Black wins");					//black wins
				}
				else {
					System.out.println("White wins");					//white wins
				}
				
				return;
			}

			
			//check if valid move

			invalid_move = true;
			while (invalid_move == true) {
				int row = 8 - Character.getNumericValue(line.charAt(1));
				int column = line.charAt(0);
				column = column - 97;
				
				if (board[row][column] != null) {
					if (board[row][column].move(line, board, player) == -1) {		//invalid move- ask for new input
						System.out.println("Illegal move, try again");
						
						if (player == true) { 											//current player => white
							System.out.print("White's move: ");
						}
						
						else { 														//current player => black
							System.out.print("Black's move: ");
						}
						
						line = scanner.nextLine();
						tokens = null;
						tokens = line.split(" ");
						
						System.out.println();
						
						if(tokens[0].equals("resign")){								//Player resigns- match ends
							//System.out.println("**match ends in resign");
							if (player == true) {
								System.out.println("Black wins");					//black wins
							}
							else {
								System.out.println("White wins");					//white wins
							}
							
							return;
						}
						
						//check if player accepts draw
						if(wantsDraw == true) {
							if (tokens[0].equals("draw")) {							//match ends in a draw
								//System.out.println("**match ends in draw");
								System.out.println("draw");
								return;
							}
						}
						else {
							wantsDraw = false;
						}
						
					}
					
					else {
						invalid_move = false;
					}
				}
				else {
					System.out.println("Illegal move, try again");
					
					if (player == true) { 										//current player => white
						System.out.print("White's move: ");
					}
					
					else { 														//current player => black
						System.out.print("Black's move: ");
					}
					
					line = scanner.nextLine();
					tokens = null;
					tokens = line.split(" ");
					
					System.out.println();
					
					if(tokens[0].equals("resign")){								//Player resigns- match ends
						//System.out.println("**match ends in resign");
						if (player == true) {
							System.out.println("Black wins");					//black wins
						}
						else {
							System.out.println("White wins");					//white wins
						}
						
						return;
					}
					
					//check if player accepts draw
					if(wantsDraw == true) {
						if (tokens[0].equals("draw")) {							//match ends in a draw
							//System.out.println("**match ends in draw");
							System.out.println("draw");
							return;
						}
					}
					else {
						wantsDraw = false;
					}
				}
			}
			
			
			//check if player accepts draw
			if(wantsDraw == true) {
				if (tokens[0].equals("draw")) {							//match ends in a draw
					//System.out.println("**match ends in draw");
					System.out.println("draw");
					return;
				}
			}
			else {
				wantsDraw = false;
			}
			

            int kingRow = 0;
            int kingColumn = 0;
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
            
            checkmate = checkIfCheckmate(board, player, kingRow, kingColumn);                      //create method to check if board is in checkmate- assigns checkmate boolean appropriately
			
			if (checkmate == true) {									//match ends in a checkmate
				//System.out.println("**match ends in checkmate");
				System.out.println("Checkmate");
				
				if (player == true) {
					System.out.println("Black wins");					//white wins
				}
				else {
					System.out.println("White wins");					//black wins
				}
				
				return;
			}
			
            kingRow = 0;
            kingColumn = 0;
            D: for(int b = 0; b < 8; b++) {
                for(int c = 0; c < 8; c++) {
                    if (board[b][c] != null) {
                        if ( board[b][c].isWhite != player && board[b][c].pieceType.equals("King")) {
                            kingRow = b;
                            kingColumn = c;

                            break D;
                        }
                    }
                }
            }
            
            checkmate = checkIfCheckmate(board, !player, kingRow, kingColumn);                      //create method to check if board is in checkmate- assigns checkmate boolean appropriately
            
			if (checkmate == true) {									//match ends in a checkmate
				//System.out.println("**match ends in checkmate");
				System.out.println("Checkmate");
				
				if (player == true) {
					System.out.println("White wins");					//white wins
				}
				else {
					System.out.println("Black wins");					//black wins
				}
				
				return;
			}
			
			if (tokens.length == 3) {									//checks if player asks for a draw
				if (tokens[2].equals("draw?")) {
					wantsDraw = true;
				}
			}
			else if (tokens.length == 4 ) {
				if (tokens[2].equals("draw?") || tokens[3].equals("draw?")) {
					wantsDraw = true;
				}
			}
			
			 kingRow = 0;
	         kingColumn = 0;
	        A: for(int b = 0; b < 8; b++) {
	            for(int c = 0; c < 8; c++) {
	                if (board[b][c] != null) {
	                    if ( board[b][c].isWhite == player && board[b][c].pieceType.equals("King")) {
	                        kingRow = b;
	                        kingColumn = c;

	                        break A;
	                    }
	                }
	            }
	        }
			
			
			
			if(checkIfCheck(board, player, kingRow, kingColumn)){	//checks to see if anyone is in check.
					System.out.println("Check");
					System.out.println();
			}
			
			kingRow = 0;
	        kingColumn = 0;
	        A: for(int b = 0; b < 8; b++) {
	            for(int c = 0; c < 8; c++) {
	                if (board[b][c] != null) {
	                    if ( board[b][c].isWhite != player && board[b][c].pieceType.equals("King")) {
	                        kingRow = b;
	                        kingColumn = c;

	                        break A;
	                    }
	                }
	            }
	        }
			
			if(checkIfCheck(board, !player, kingRow, kingColumn)){
					System.out.println("Check");
					System.out.println();
			}
			
			
			
		}
		
		scanner.close();
		
//====OUT OF WHILE LOOP==================================================================================================================================

}	//out of main method
	

	
	/**
	 * Initializes all the pieces into the 2d array board
	 * @param board The two-dimensional array board that holds all the piece information
	 */
	public static void initializeBoard(Piece[][] board) {
		//black pieces
			//rooks
		board[0][0] = new Rook(false);
		board[0][7] = new Rook(false);
			//knights
		board[0][1] = new Knight(false);
		board[0][6] = new Knight(false);
			//bishops
		board[0][2] = new Bishop(false);
		board[0][5] = new Bishop(false);
			//queen
		board[0][3] = new Queen(false);
			//king
		board[0][4] = new King (false);
			//pawns
		for (int i = 0; i < 8; i++) {
			board[1][i] = new Pawn(false);
		}
		
		
		//white pieces
			//rooks
		board[7][0] = new Rook(true);
		board[7][7] = new Rook(true);
			//knights
		board[7][1] = new Knight(true);
		board[7][6] = new Knight(true);
			//bishops
		board[7][2] = new Bishop(true);
		board[7][5] = new Bishop(true);
			//queen
		board[7][3] = new Queen(true);
			//king
		board[7][4] = new King (true);
			//pawns
		for (int i = 0; i < 8; i++) {
			board[6][i] = new Pawn(true);
		}
		
	}
	
	
	
	/**
	 * Prints the ASCII chess board into console
	 * @param board The two-dimensional array board that holds all the piece information
	 */
	public static void printBoard(Piece[][] board) {			//sad boy hours incoming
		
		String[][] sBoard = new String[8][8];
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j] != null) {						//if spot isn't empty
					sBoard[i][j] = board[i][j].toString();
				}
				
				else {											//spot is empty- check if it is "##"
					if (i % 2 == 0) {
						if (j % 2 == 0) {
							sBoard[i][j] = "  ";
						}
						else {
							sBoard[i][j] = "##";
						}
					}
					
					else {
						if (j % 2 == 0) {
							sBoard[i][j] = "##";
						}
						else {
							sBoard[i][j] = "  ";
						}
					}
				}
			}
		}
		
		//Print board
		System.out.println(
				sBoard[0][0] + " " + sBoard[0][1] + " " + sBoard[0][2] + " " + sBoard[0][3] + " " + sBoard[0][4] + " " + sBoard[0][5] + " " + sBoard[0][6] + " " + sBoard[0][7] + " 8" + "\n" +
				sBoard[1][0] + " " + sBoard[1][1] + " " + sBoard[1][2] + " " + sBoard[1][3] + " " + sBoard[1][4] + " " + sBoard[1][5] + " " + sBoard[1][6] + " " + sBoard[1][7] + " 7" + "\n" +
				sBoard[2][0] + " " + sBoard[2][1] + " " + sBoard[2][2] + " " + sBoard[2][3] + " " + sBoard[2][4] + " " + sBoard[2][5] + " " + sBoard[2][6] + " " + sBoard[2][7] + " 6" + "\n" +
				sBoard[3][0] + " " + sBoard[3][1] + " " + sBoard[3][2] + " " + sBoard[3][3] + " " + sBoard[3][4] + " " + sBoard[3][5] + " " + sBoard[3][6] + " " + sBoard[3][7] + " 5" + "\n" +
				sBoard[4][0] + " " + sBoard[4][1] + " " + sBoard[4][2] + " " + sBoard[4][3] + " " + sBoard[4][4] + " " + sBoard[4][5] + " " + sBoard[4][6] + " " + sBoard[4][7] + " 4" + "\n" +
				sBoard[5][0] + " " + sBoard[5][1] + " " + sBoard[5][2] + " " + sBoard[5][3] + " " + sBoard[5][4] + " " + sBoard[5][5] + " " + sBoard[5][6] + " " + sBoard[5][7] + " 3" + "\n" +
				sBoard[6][0] + " " + sBoard[6][1] + " " + sBoard[6][2] + " " + sBoard[6][3] + " " + sBoard[6][4] + " " + sBoard[6][5] + " " + sBoard[6][6] + " " + sBoard[6][7] + " 2" + "\n" +
				sBoard[7][0] + " " + sBoard[7][1] + " " + sBoard[7][2] + " " + sBoard[7][3] + " " + sBoard[7][4] + " " + sBoard[7][5] + " " + sBoard[7][6] + " " + sBoard[7][7] + " 1" + "\n" +
				" a  b  c  d  e  f  g  h");
		
		System.out.println();
		
		return;
	}
	
	
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	//TODO: Finish checkIfValidInput()
	public static boolean checkIfValidInput(String[] input) {
		
		if(input.length == 0) { return false; }		//empty input
		
		if(input.length == 1 && input[0] == "resign") { return true; }	//resignation input
		
		return true;
	}
	
	
	
	//TODO: Finish checkIfValidMove()
	public static void checkIfValidMove() {
		return;
	}
	
	
	
	/**
	 * Checks if specified String from the first token of System.in is a resignation, and ends the game if so.
	 * @param token the first token received from System.In
	 * @param player that has initiative
	 */
	public static boolean checkIfResign(String token, boolean player) {
		if(token.equals("resign")){								//Player resigns- match ends
			//System.out.println("**match ends in resign");
			if (player == true) {
				System.out.println("Black wins");					//black wins
			}
			else {
				System.out.println("White wins");					//white wins
			}
			
			return true;
		}
		
		return false;
	}
	
	
	
	/**
	 * Checks if the king is in Checkmate
	 * @param board The two-dimensional array board that holds all the piece information
	 * @param player True if white player, False if black player
	 * @param rowOfKing Integer row that the king is in
	 * @param columnOfKing Integer column that the king is in
	 * @return True if the king is in checkmate, false if king is not in checkmate
	 */
	/**
	 * Checks if the king is in Checkmate
	 * @param board The two-dimensional array board that holds all the piece information
	 * @param player True if white player, False if black player
	 * @param rowOfKing Integer row that the king is in
	 * @param columnOfKing Integer column that the king is in
	 * @return True if the king is in checkmate, false if king is not in checkmate
	 */
	public static boolean checkIfCheckmate (Piece[][] board, boolean player, int rowOfKing, int columnOfKing) {

		int tempR = rowOfKing;
		int tempC = columnOfKing;
		
		boolean isCheckmate = true;
		
		//check current position
		if ((tempR >= 0) && (tempR <= 7) && (tempC >= 0) && (tempC <= 7)) {			//if king is inside the board
			//System.out.println("tempR: " + tempR + " | tempC: " + tempC);
			if (checkIfCheck(board, player, tempR, tempC) == false){ isCheckmate = false; }
		}
		//System.out.println("current position");
		
		//check bottom position
		tempR = rowOfKing + 1;
		tempC = columnOfKing;

		if ((tempR >= 0) && (tempR <= 7) && (tempC >= 0) && (tempC <= 7)) {
			
			//System.out.println("tempR: " + tempR + " | tempC: " + tempC);
			if ((checkIfCheck(board, player, tempR, tempC) == false) && (board[tempR][tempC] != null)){ 
				//System.out.println("if");
				if (board[tempR][tempC].isWhite != player) { 
					if (isKingNear(board, player, tempR, tempC) == false) {
						isCheckmate = false;
					}
				}
				
			}
			else if ((checkIfCheck(board, player, tempR, tempC) == false) && (board[tempR][tempC] == null)){ 
				//System.out.println("else if");
				if (isKingNear(board, player, tempR, tempC) == false) {
					isCheckmate = false;
				}
			}
		}
		//System.out.println("Bottom: " + isCheckmate);
		
		//check bottom-right position
		tempR = rowOfKing + 1;
		tempC = columnOfKing + 1;

		if ((tempR >= 0) && (tempR <= 7) && (tempC >= 0) && (tempC <= 7)) {
			if ((checkIfCheck(board, player, tempR, tempC) == false) && (board[tempR][tempC] != null)){ 
				if (board[tempR][tempC].isWhite != player) { 
					if (isKingNear(board, player, tempR, tempC) == false) {
						isCheckmate = false;
					}
				}
				
			}
			else if ((checkIfCheck(board, player, tempR, tempC) == false) && (board[tempR][tempC] == null)){ 
				if (isKingNear(board, player, tempR, tempC) == false) {
					isCheckmate = false;
				}
			}
		}
		//System.out.println("Bottom-right: " + isCheckmate);
		
		//check bottom-left position
		tempR = rowOfKing + 1;
		tempC = columnOfKing - 1;

		if ((tempR >= 0) && (tempR <= 7) && (tempC >= 0) && (tempC <= 7)) {
			if ((checkIfCheck(board, player, tempR, tempC) == false) && (board[tempR][tempC] != null)){ 
				if (board[tempR][tempC].isWhite != player) { 
					if (isKingNear(board, player, tempR, tempC) == false) {
						isCheckmate = false;
					}
				}
				
			}
			else if ((checkIfCheck(board, player, tempR, tempC) == false) && (board[tempR][tempC] == null)){ 
				if (isKingNear(board, player, tempR, tempC) == false) {
					isCheckmate = false;
				}
			}
		}
		//System.out.println("Bottom-left: " + isCheckmate);

		//check right position
		tempR = rowOfKing;
		tempC = columnOfKing + 1;

		if ((tempR >= 0) && (tempR <= 7) && (tempC >= 0) && (tempC <= 7)) {
			if ((checkIfCheck(board, player, tempR, tempC) == false) && (board[tempR][tempC] != null)){ 
				if (board[tempR][tempC].isWhite != player) { 
					if (isKingNear(board, player, tempR, tempC) == false) {
						isCheckmate = false;
					}
				}
				
			}
			else if ((checkIfCheck(board, player, tempR, tempC) == false) && (board[tempR][tempC] == null)){ 
				if (isKingNear(board, player, tempR, tempC) == false) {
					isCheckmate = false;
				}
			}
		}
		//System.out.println("Right: " + isCheckmate);

		//check left position
		tempR = rowOfKing;
		tempC = columnOfKing - 1;

		if ((tempR >= 0) && (tempR <= 7) && (tempC >= 0) && (tempC <= 7)) {
			if ((checkIfCheck(board, player, tempR, tempC) == false) && (board[tempR][tempC] != null)){ 
				if (board[tempR][tempC].isWhite != player) { 
					if (isKingNear(board, player, tempR, tempC) == false) {
						isCheckmate = false;
					}
				}
				
			}
			else if ((checkIfCheck(board, player, tempR, tempC) == false) && (board[tempR][tempC] == null)){ 
				if (isKingNear(board, player, tempR, tempC) == false) {
					isCheckmate = false;
				}
			}
		}
		//System.out.println("Left: " + isCheckmate);

		//check top-left position
		tempR = rowOfKing - 1;
		tempC = columnOfKing - 1;

		if ((tempR >= 0) && (tempR <= 7) && (tempC >= 0) && (tempC <= 7)) {
			if ((checkIfCheck(board, player, tempR, tempC) == false) && (board[tempR][tempC] != null)){ 
				if (board[tempR][tempC].isWhite != player) { 
					if (isKingNear(board, player, tempR, tempC) == false) {
						isCheckmate = false;
					}
				}
				
			}
			else if ((checkIfCheck(board, player, tempR, tempC) == false) && (board[tempR][tempC] == null)){ 
				if (isKingNear(board, player, tempR, tempC) == false) {
					isCheckmate = false;
				}
			}
		}
		//System.out.println("Top-left: " + isCheckmate);

		//check top-right position
		tempR = rowOfKing - 1;
		tempC = columnOfKing + 1;
		
		if ((tempR >= 0) && (tempR <= 7) && (tempC >= 0) && (tempC <= 7)) {
			if ((checkIfCheck(board, player, tempR, tempC) == false) && (board[tempR][tempC] != null)){ 
				if (board[tempR][tempC].isWhite != player) { 
					if (isKingNear(board, player, tempR, tempC) == false) {
						isCheckmate = false;
					}
				}
				
			}
			else if ((checkIfCheck(board, player, tempR, tempC) == false) && (board[tempR][tempC] == null)){ 
				if (isKingNear(board, player, tempR, tempC) == false) {
					isCheckmate = false;
				}
			}
		}
		//System.out.println("Top-right: " + isCheckmate);
		
		//check top position
		tempR = rowOfKing - 1;
		tempC = columnOfKing;
	
		if ((tempR >= 0) && (tempR <= 7) && (tempC >= 0) && (tempC <= 7)) {
			if ((checkIfCheck(board, player, tempR, tempC) == false) && (board[tempR][tempC] != null)){ 
				if (board[tempR][tempC].isWhite != player) { 
					if (isKingNear(board, player, tempR, tempC) == false) {
						isCheckmate = false;
					}
				}
				
			}
			else if ((checkIfCheck(board, player, tempR, tempC) == false) && (board[tempR][tempC] == null)){ 
				if (isKingNear(board, player, tempR, tempC) == false) {
					isCheckmate = false;
				}
			}
		}
		//System.out.println("Top: " + isCheckmate);

		if (isCheckmate == true) {
			return true;
		}
		
		return false;
	}
	
	
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
	
	
	/**
	 * Checks if the king is in Check
	 * @param board The two-dimensional array board that holds all the piece information
	 * @param player True if white player, False if black player
	 * @param rowOfKing Integer row that the king is in
	 * @param columnOfKing Integer column that the king is in
	 * @return True if the king is in check, false if king is not in check
	 */
	public static boolean checkIfCheck(Piece[][] board, boolean player, int kingRow, int kingColumn) {
		
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
	
	
	/**
	 * Checks if the enemy king is threatening any of the nearby spaces
	 * @param board The two-dimensional array board that holds all the piece information
	 * @param player True if white player, False if black player
	 * @param rowOfKing Integer row that the king is in
	 * @param columnOfKing Integer column that the king is in
	 * @return True if there is an enemy king nearby, false otherwise
	 */
	public static boolean isKingNear (Piece[][] board, boolean player, int rowOfKing, int columnOfKing) {
		boolean isNear = false;
		
		int tempR = rowOfKing;
		int tempC = columnOfKing;
		
		//check current position
		if ((tempR >= 0) && (tempR <= 7) && (tempC >= 0) && (tempC <= 7)) {
			if (board[tempR][tempC] != null){ 
				if (board[tempR][tempC].pieceType.equals("King") && (board[tempR][tempC].isWhite != player)){ isNear = true; }
			}
		}

		//check bottom position
		tempR = rowOfKing + 1;
		tempC = columnOfKing;

		if ((tempR >= 0) && (tempR <= 7) && (tempC >= 0) && (tempC <= 7)) {
			if (board[tempR][tempC] != null){ 
				if (board[tempR][tempC].pieceType.equals("King") && (board[tempR][tempC].isWhite != player)){ isNear = true; }
			}
		}

		//check bottom-right position
		tempR = rowOfKing + 1;
		tempC = columnOfKing + 1;

		if ((tempR >= 0) && (tempR <= 7) && (tempC >= 0) && (tempC <= 7)) {
			if (board[tempR][tempC] != null){ 
				if (board[tempR][tempC].pieceType.equals("King") && (board[tempR][tempC].isWhite != player)){ isNear = true; }
			}
		}
		
		//check bottom-left position
		tempR = rowOfKing + 1;
		tempC = columnOfKing - 1;

		if ((tempR >= 0) && (tempR <= 7) && (tempC >= 0) && (tempC <= 7)) {
			if (board[tempR][tempC] != null){ 
				if (board[tempR][tempC].pieceType.equals("King") && (board[tempR][tempC].isWhite != player)){ isNear = true; }
			}
		}

		//check right position
		tempR = rowOfKing;
		tempC = columnOfKing + 1;

		if ((tempR >= 0) && (tempR <= 7) && (tempC >= 0) && (tempC <= 7)) {
			if (board[tempR][tempC] != null){ 
				if (board[tempR][tempC].pieceType.equals("King") && (board[tempR][tempC].isWhite != player)){ isNear = true; }
			}
		}

		//check left position
		tempR = rowOfKing;
		tempC = columnOfKing - 1;

		if ((tempR >= 0) && (tempR <= 7) && (tempC >= 0) && (tempC <= 7)) {
			if (board[tempR][tempC] != null){ 
				if (board[tempR][tempC].pieceType.equals("King") && (board[tempR][tempC].isWhite != player)){ isNear = true; }
			}
		}

		//check top-left position
		tempR = rowOfKing - 1;
		tempC = columnOfKing - 1;

		if ((tempR >= 0) && (tempR <= 7) && (tempC >= 0) && (tempC <= 7)) {
			if (board[tempR][tempC] != null){ 
				if (board[tempR][tempC].pieceType.equals("King") && (board[tempR][tempC].isWhite != player)){ isNear = true; }
			}
		}

		//check top-right position
		tempR = rowOfKing - 1;
		tempC = columnOfKing + 1;
		
		if ((tempR >= 0) && (tempR <= 7) && (tempC >= 0) && (tempC <= 7)) {
			if (board[tempR][tempC] != null){ 
				if (board[tempR][tempC].pieceType.equals("King") && (board[tempR][tempC].isWhite != player)){ isNear = true; }
			}
		}

		//check top position
		tempR = rowOfKing - 1;
		tempC = columnOfKing;
	
		if ((tempR >= 0) && (tempR <= 7) && (tempC >= 0) && (tempC <= 7)) {
			if (board[tempR][tempC] != null){ 
				if (board[tempR][tempC].pieceType.equals("King") && (board[tempR][tempC].isWhite != player)){ isNear = true; }
			}
		}

		if (isNear) {
			return true;
		}
		
		return false;
	}
	

}  //out of class
















