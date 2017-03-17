package es.ucm.fdi.tp.was;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.print.attribute.standard.Finishings;

import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.ttt.TttAction;
import es.ucm.fdi.tp.ttt.TttState;

/**Clase WasState que hereda de GameState con los argumentos WasState y WasAction**/
public class WasState extends GameState<WasState, WasAction> {
	
private static final long serialVersionUID = -2641387354190472377L;
	/**Declaracion de atributos de clase**/
	private int turn; /** Atributo que usaremos para saber el turno**/
    private boolean finished; /**Atributo que usaremos para saber si se ha terminado o no**/
    private int[][] board; /**Tablero que usaremos para el juego**/
    private int winner; /**Atributo que usaremos para saber quien ha ganado**/

    private static final int dim= 8;

    final static int EMPTY = -1;
    /**Constructora de la clase sin atributos**/
    public WasState() {
        super(2); 
        board = new int[WasState.dim][]; /** Primero creamos el tablero con la dimension. **/
        for (int i=0; i<WasState.dim; i++) { 
            board[i] = new int[WasState.dim];
            for (int j=0; j<WasState.dim; j++) board[i][j] = EMPTY; /**Inicializamos el tablero entero a vacio(-1)*/
            
        }
        for (int i=1; i<WasState.dim; i++){ /** Inicializamos las ovejas*/
        	board[0][i]=1;
        	i++;
        }

        board[7][0] = 0; /**Inicializamos el lobo*/
        
        this.turn = 0; /**Inicializamos el turno*/
        this.winner = -1;/**Inicializamos el ganador a menos -1, no hay ganador*/
        this.finished = false;/**Inicializamos si ha terminado el juego a false*/
    }
	/**Constructora de clase con atributos
	 * 
	 * @param prev Estado anterior
	 * @param board Tablero
	 * @param finished Variable para controlar si el juego terminó o no
	 * @param winner Quien es el ganador
	 */
    public WasState(WasState prev, int[][] board,boolean finished, int winner) {
    	super(2);
    	//this.dim = prev.dim;
    	  this.board = board;
          this.turn = (prev.turn + 1) % 2;
          this.finished = finished;
          this.winner = winner;
      }  
  


	@Override
	public int getTurn() { /**Metodo que devuelve de quien es el turno**/
		// TODO Auto-generated method stub
		return this.turn;
	}
	
	@Override
	/**Creamos una lista para los movimientos validos, recibe por parametro el jugador que tenga el turno**/
	public List<WasAction> validActions(int playerNumber) {
		// TODO Auto-generated method stub
		/**Creamos una lista de Validos para saber que movimientos tiene que sean validos**/
		 ArrayList<WasAction> valid = new ArrayList<>(); 
		 /**Controlamos que ocurriria si el juego acabase**/
	        if (finished) {
	            return valid;
	        }
	        /**Si no ha terminado vamos a recorrer todo el tablero para ver que movimientos posibles tiene el jugador**/
	        for (int i = 0; i < WasState.dim; i++) {
	            for (int j = 0; j < WasState.dim; j++) {
	            	int ficha=at(i, j);
	                if (ficha!= EMPTY){
	                	if(ficha == playerNumber)
	                		valid.addAll(damePosibles(playerNumber, i, j));//Añadimos las posubles de cada jugador no miramos vacias
	                }
	            }
	        }
	        if(valid.isEmpty()) this.finished= true;
	        return valid;/** devolvemos una lista de validos*/
	  }
	/**Creamos una lista para coger un movimiento posible
	 * 
	 * @param playerNumber Quien tiene el turno si la oveja o el lobo
	 * @param i fila
	 * @param j columna
	 * @return
	 */
	private List<WasAction> damePosibles(int playerNumber, int i, int j) {
		// TODO Auto-generated method stub
		/**Creamos una lista de posibles para saber que movimientos son posibles**/
		List<WasAction> posibles = new ArrayList<>();
		/**Debido a que el lobo tiene 2 movimientos mas que las ovejas vamos a tratarlos a parte**/
		if(playerNumber == 0){ 
			if((i-1) >= 0 && (j-1) >= 0 && board[i-1][j-1] == EMPTY){
				/**Guardamos pos anterior y pos nueva**/
				posibles.add(new WasAction(playerNumber, i, j, i-1, j-1)); 
			}
			if((i-1) >= 0 && (j+1) < 8 && board[i-1][j+1] == EMPTY){
				/**Guardamos pos anterior pos nueva**/
				posibles.add(new WasAction(playerNumber, i, j, i-1, j+1));
			}
		}
		/**Controlamos los movimientos comunes de las ovejas y de el lobo**/
		if((i+1) < 8 && (j+1) < 8 && board[i+1][j+1] == EMPTY){
			/**Guardamos pos anterior pos nueva**/
			posibles.add(new WasAction(playerNumber, i, j, i+1, j+1)); 
		}
		if((i+1)  < 8 && (j-1) >= 0 && board[i+1][j-1] == EMPTY){
			/**Guardamos pos anterior pos nueva**/
			posibles.add(new WasAction(playerNumber, i, j, i+1, j-1));
		}
		
		return posibles;
	}

	private int at(int i, int j) { /** Metodo que devuelve una posicion del tablero el tablero **/
		// TODO Auto-generated method stub
		return board[i][j];
	}

	@Override
	public boolean isFinished() { /**Metodo que devuelve si se ha terminado o no**/
		// TODO Auto-generated method stub
		return this.finished;
	}

	@Override
	public int getWinner() { /**Metodo que devuelve quien es el ganador**/
		// TODO Auto-generated method stub
		return this.winner;
	}
	
	  public int[][] getBoard() { /**Metodo que devuelve el tablero**/
		  int[][] copy = new int[board.length][];
	      for (int i=0; i<board.length; i++) copy[i] = board[i].clone();
	      return copy;
	    }
   /**Metodo que comprobara si hay un ganador o no**/
	public boolean isWinner(int turn2){
		// TODO Auto-generated method stub
		/**Primero comprobamos si ha ganado el lobo**/
		if(turn2 == 0){
			for(int i =0; i < WasState.dim; i++){
				if(at(0,i)==0){/** Te comprueba si en esa poscion esta el lobo**/
					winner=0;
					finished=true;
					return true;
				}
				}
		}else {
			if(validActions(0).isEmpty()){
				winner=1;
				finished= true;
				return true;
				
			} /**Deveulve que no hay movimientos posibles**/
			
		}
		if(validActions(1).isEmpty()){
			winner=0;
			finished=true;
			return true;
		}
		return false;
		
	}
		   
    public String toString() { /**Metodo que escribe el tablero**/
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<board.length; i++) {
            sb.append("|");
            for (int j=0; j<board.length; j++) {
                sb.append(board[i][j] == EMPTY ? " . " : board[i][j]== 0 ? " W " : " S ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
	  
}
