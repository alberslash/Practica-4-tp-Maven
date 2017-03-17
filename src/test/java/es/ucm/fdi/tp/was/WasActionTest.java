package es.ucm.fdi.tp.was;

import static org.junit.Assert.*;

import org.junit.Test;

public class WasActionTest {

	
	public void InitalWolf(){
		WasState state = new WasState();
		int [][] board=state.getBoard();
		assertEquals("The wolf only has one action", 1, state.validActions(0).size());
		
		
		board[state.getBoard().length-1][0]=-1;
		board[state.getBoard().length-2][1]=0;
		state= new WasState(state, board,false, 1);
		assertEquals("The wolf only has four actions", 4, state.validActions(0).size());
		
	}
	
	@Test
	public void InitalSheep(){
		WasState state = new WasState();
		int [][] board=state.getBoard();
		assertEquals("The sheep only has seven actions", 7, state.validActions(1).size());
		 for(int j = 0; j< (board.length-1); j++) board[0][j]=-1;
		 state= new WasState(state, board,false, 1);
		 assertEquals("The Sheep only has one action", 1, state.validActions(1).size());
		
	}
	
}
	


