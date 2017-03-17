package es.ucm.fdi.tp.was;

import static org.junit.Assert.*;

import org.junit.Test;

public class WasTest {

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
	
	public void InitalWolf(){
		WasState state = new WasState();
		int [][] board=state.getBoard();
		assertEquals("The wolf only has one action", 1, state.validActions(0).size());
		board[state.getBoard().length-1][0]=-1;
		board[state.getBoard().length-2][1]=0;
		state= new WasState(state, board,true, 0);
		assertEquals("The wolf only has four actions", 4, state.validActions(0).size());
		
	}
	
	public void InitalSheep(){
		WasState state = new WasState();
		int [][] board=state.getBoard();
		assertEquals("The sheep only has seven actions", 7, state.validActions(1).size());
		for(int i =0; i < state.validActions(1).size(); i++){ //Posible cambiar por 7.
			board[0][state.getBoard().length-1]=-1;
			board[0][state.getBoard().length-2]=1;	
		}
		state= new WasState(state, board,true, 1);
		assertEquals("The Sheep only has four actions", 4, state.validActions(0).size());
	}

}
