package es.ucm.fdi.tp.was;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.ttt.TttAction;
import es.ucm.fdi.tp.ttt.TttState;

/** Creamos la clase WasAction que implementa la lista GameAction que recibe como parametros 
  la clase wasAction y WasState **/
public class WasAction  implements GameAction<WasState, WasAction> {

	/**
	 Desclaracion de atributos de la clase
	 
	 **/
	private static final long serialVersionUID = 1L;
	private int playerNumber; /**Usaremos esta variable para identificar el turno**/
	private int row; /**Usaremos esta variable para identificar la nueva fila**/
	private int rowOld; /**Usaremos esta variable para identificar la antigua fila**/
	private int col; /**Usaremos esta variable para identificar la nueva columna**/
	private int colOld; /**Usaremos esta variable para identificar la antigua columna**/
	
	/**Constructora de la clase
	 * 
	 * @param playerNumber Que jugador es, si ovejao lobo
	 * @param i columna nueva
	 * @param j fila nueva
	 * @param k columna antigua
	 * @param l fila antigua
	 */
	public WasAction(int playerNumber, int i, int j, int k, int l) {
		// TODO Auto-generated constructor stub
		this.playerNumber=playerNumber;
		this.row = l;
		this.rowOld = j;
		this.col=k;
		this.colOld =i;
	}

	@Override
	public int getPlayerNumber() { /**Metodo que devuelve el turno**/
		// TODO Auto-generated method stub
		return this.playerNumber;
	}

	@Override
	
	/** Metodo Applyto que le entra por parametro el estado **/
	public WasState applyTo(WasState state) {
		// TODO Auto-generated method stub
		 if (playerNumber != state.getTurn()) {
	            throw new IllegalArgumentException("Not the turn of this player");
	        }
		int [][] board = state.getBoard(); /**Inicializamos el tablero**/
		board[colOld][rowOld]= state.EMPTY; /**Vaciamos la antigua posicion del tablero**/
		board[col][row]= playerNumber; /**Llenamos la nueva posicion del tablero**/
		
		
		WasState next; /**Creamos una variable del tipo WasState**/
		next = new WasState(state, board, false,-1);
		 if (next.isWinner(playerNumber)) {       
	            next = new WasState(state, board,true, state.getTurn());
	        } else {
	            next = new WasState(state, board, false, -1);
	        }
	    return next;	
	}
	
	public String toString() {  /**Muestra por pantalla las opciones**/
        return "place " + (playerNumber ==0 ? "W " : " S") + "("+rowOld + ", " + colOld + ")"+ "at("+row + ", "+col + ")";
    }

}
