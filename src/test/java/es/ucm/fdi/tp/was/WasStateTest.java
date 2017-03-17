package es.ucm.fdi.tp.was;

import static org.junit.Assert.*;

import org.junit.Test;

public class WasStateTest {

	@Test
	public void WolfWinner(){
		WasState state = new WasState();
		int [][] board;
		for(int i =1; i < state.getBoard().length; i= i+2){
			board= state.getBoard();
			board[0][i]= 0;
			WasState newstate= new WasState(state, board,true, 0);
			assertEquals("The winner has to be the wolf", true, newstate.isWinner(0));	
		}
	}
	
	
	public void SheepWinner(){
		WasState state = new WasState();
		int [][] board;
		for(int i=0; i < state.getBoard().length; i++){
			for(int j= 0; j < state.getBoard().length; j++){
				board= state.getBoard();
				if(board[i][j] ==0){
					if(state.validActions(0).isEmpty()){
						WasState newstate= new WasState(state, board,true, 1);
						assertEquals("The winner has to be the sheep", true, newstate.isWinner(1));
					}	
				}
			}	
		}
	}

}
