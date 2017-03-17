package es.ucm.fdi.tp.launcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import es.ucm.fdi.tp.base.console.ConsolePlayer;
import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GamePlayer;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.base.player.RandomPlayer;
import es.ucm.fdi.tp.base.player.SmartPlayer;
import es.ucm.fdi.tp.ttt.TttState;
import es.ucm.fdi.tp.was.WasState;

public class Main {
	/**Metodo main, que recibe por argumento varios String.
	 * 
	 * @param args Varios argumentos
	 */
	
	public static void test(){}
	
	
	
	public static void main(String... args) { 
		/**Creamos una Lista de timpo GamePlayer**/
		List<GamePlayer> listaJugadores = new ArrayList<>();
		GameState<?, ?> juego= createInitialState(args[0]);
		if(juego == null) { /**Si el juego es null mostramos error**/
			System.err.println("El juego no existe");
			System.exit(1);
		}
		/**Recorremos los argumentos**/
		for(int i =1; i < args.length; i++){
			/**Creamos el jugador**/
			GamePlayer jugador = createInitialPlayer(args[0], args[i], "player"+i);
			/**Si es nulo mostramos error**/
			if(jugador == null){
				System.err.println("El jugador no existe");
				System.exit(1);
			}
			else listaJugadores.add(jugador); /**Si no es nulo lo añadimos a la lista de jugadores**/
		}
		playGame(juego, listaJugadores); /**Llamamos a la funcion playgame**/

	}
    /**Metodo que sirve para iniciar cualquiera de los juegos comprobando si el juego e
     * esta en nuestra lista, en ese caso lo inicamos, en el contrario devolvemos null y
     * en el main lanzamos el error.
     * @param gameName Nombre del Juego
     * @return
     */
	public static GameState<?,?>createInitialState(String gameName){
		/**Si el gamename es was creamos un nuevo objeto de WasState**/
		if(gameName.equalsIgnoreCase("was")) return new WasState();
		/**Si el gamename es ttt creamos un nuevo ojjeto de TttState**/
		else if(gameName.equalsIgnoreCase("ttt")) return new TttState(3);
		/**Si no es ninguno de estos devuelve null**/
		else {
			return null;
		}
	}
    /**Metodo del tipo GamePlayer para crear el jugador inicial.
     * 
     * @param gameName nombre del Juego
     * @param playerType Tipo de jugador.
     * @param playerName Nombre del Jugador
     * @return
     */
	public static GamePlayer createInitialPlayer(String gameName, String playerType, String playerName){
		/**Si el  tipo es smart crea un objeto nuevo de SmartPlayer, un objeto inteligente**/
		if(playerType.equalsIgnoreCase("smart")) return new SmartPlayer(playerName, 5);
		/**Si el tipo es conseola, recoge por pantalla y crea un objeto nuevo, se introduce manualmente.**/
		else if(playerType.equalsIgnoreCase("console")){
			Scanner scaner = new Scanner(System.in);
			return new ConsolePlayer(playerName, scaner);
		}
		/**Si el tipo es random creamos un objeto nuevo de RandomPlayer, aleatorio.**/
		else if(playerType.equalsIgnoreCase("random"))return new RandomPlayer(playerName);
		else return null;		
	}
	/**
	 * Launcher main, used to test game functionality. You can use it as an inspiration,
	 * but we expect you to build your own main-class.
	 */
	public static <S extends GameState<S, A>, A extends GameAction<S, A>> int playGame(GameState<S, A> initialState,
			List<GamePlayer> players) {
		int playerCount = 0;
		for (GamePlayer p : players) {
			p.join(playerCount++); // welcome each player, and assign
			// playerNumber
		}
		@SuppressWarnings("unchecked")
		S currentState = (S) initialState;
        /**Mientras no haya acabade ejecutamos el cuerpo del while**/
		while (!currentState.isFinished()) {
			// request move
			/** ?? **/
			A action = players.get(currentState.getTurn()).requestAction(currentState);
			/**Aplicamos el movimiento y mostramos el estado **/
			currentState = action.applyTo(currentState);
			System.out.println("After action:\n" + currentState);
			/**Si el estado siguiente es fin ejecutamos el cuerpo del if**/
			if (currentState.isFinished()) {
				// game over
				String endText = "The game ended: ";
				/**Mostramos por pantalla quien es el ganador**/
				int winner = currentState.getWinner();
				if (winner == -1) {
					endText += "draw!";
				} else {
					endText += "player " + (winner + 1) + " (" + players.get(winner).getName() + ") won!";
				}
				System.out.println(endText);
			}
		}
		return currentState.getWinner();
	}



	public boolean argumens(String[] args) {
	if( args.length > 3) return false;
	else return true;
	}
}
