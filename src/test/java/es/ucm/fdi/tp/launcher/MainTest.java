package es.ucm.fdi.tp.launcher;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class MainTest {

	@Test
	public void test() {
		String args[] ={"was", "console", "console", "rand"};
		Main main = new Main();
		assertEquals("The number of arguments are not correct", false, main.argumens(args));
	}
	
	
	public void testGameName(){
	String args []={"chees", "console", "console"};
	Main main = new Main();
	assertEquals("The number of arguments are not correct", false, main.argumens(args));
	
		}
}
