package pieces;

public class Rook extends Piece{
	
	boolean isFirstMove;
	
	public Rook(Boolean isWhite) {
		
		super(isWhite);
		this.pieceType = "Rook";
		this.isFirstMove = true;
		
	}
	
	@Override
	public String toString()  {
		String type = "";
		
		if (isWhite == true) {
			type = "wR";
		}
		
		else {
			type = "bR";
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
		
		//if castling
		if(board[nextRow][nextColumn] != null) {
			if((board[nextRow][nextColumn].pieceType.equals("King") == true) && (board[nextRow][nextColumn].isWhite == player)) {
				
			}
			else if (board[nextRow][nextColumn].isWhite != player) {
				boolean notBlocked = true;
				
				if ((currRow == nextRow) && (currColumn != nextColumn)) {	
					if (currColumn < nextColumn) {	//rook moving right
						for(int i = currColumn + 1; i < nextColumn; i++) {
							if(board[currRow][i] != null) {
								notBlocked = false;
							}
						}
					}
					else {							//rook moving left		(currColumn > nextColumn)
						for(int i = nextColumn + 1; i < currColumn; i++) {
							if(board[currRow][i] != null) {
								notBlocked = false;
							}
						}
					}
				}
				else if ((currRow != nextRow) && (currColumn == nextColumn)) {
					//System.out.println("currColumn == nextColumn");
					if (currRow < nextRow) {	//rook moving down
						for(int i = currRow + 1; i < nextRow; i++) {
							if(board[i][currColumn] != null) {
								notBlocked = false;
							}
						}
					}
					else {							//rook moving up
						for(int i = nextRow + 1; i < currRow; i++) {
							if(board[i][currColumn] != null) {
								notBlocked = false;
							}
						}
					}
				}
				
				else {		//else row == column or rook is trying to move diagonal
					System.out.println("else else");
					return -1;
				}
				
				if (notBlocked == true) {
					
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
			else {		//if trying to castle with a non-king
				return -1;
			}
		}
		
		
		//if not castling
		else {
			//System.out.println("not castling");
			boolean notBlocked = true;
			
			if ((currRow == nextRow) && (currColumn != nextColumn)) {	
				//System.out.println("currRow == nextRow");
				if (currColumn < nextColumn) {	//rook moving right
					for(int i = currColumn + 1; i <= nextColumn; i++) {
						if(board[currRow][i] != null) {
							notBlocked = false;
						}
					}
				}
				else {							//rook moving left		(currColumn > nextColumn)
					for(int i = nextColumn; i < currColumn; i++) {
						if(board[currRow][i] != null) {
							notBlocked = false;
						}
					}
				}
			}
			else if ((currRow != nextRow) && (currColumn == nextColumn)) {
				//System.out.println("currColumn == nextColumn");
				if (currRow < nextRow) {	//rook moving down
					for(int i = currRow + 1; i <= nextRow; i++) {
						if(board[i][currColumn] != null) {
							notBlocked = false;
						}
					}
				}
				else {							//rook moving up
					for(int i = nextRow; i < currRow; i++) {
						if(board[i][currColumn] != null) {
							notBlocked = false;
						}
					}
				}
			}
			
			else {		//else row == column or rook is trying to move diagonal
				//System.out.println("else else");
				return -1;
			}
			
			if (notBlocked == true) {
				
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
		return -1;
	}
}
