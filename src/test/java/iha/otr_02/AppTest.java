package iha.otr_02;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class AppTest {	
	ArrayList<String> Contacts = null;
	
	public class RedirectOutputStream extends OutputStream {
		
		private ArrayList<String> out = new ArrayList<String>();
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    
	    public void write(int b) throws IOException {
	        
	    	char currentChar = (char) b;

	        // skip this symbol
	        if (currentChar == '\r') {
	            return;
	        }

	        // check for a new line
	        if (currentChar == '\n') {
	            flushBuffer();
	        } else {
	            baos.write( b );
	        }
	    }

	    private void flushBuffer() {
	    	out.add(baos.toString());
	    	baos.reset();
	    }
	    
	    public ArrayList<String> getBuffer() {
	    	return out;
	    }
	}
	
	@Before
	public void makeBeginnerValue(){
		Contacts = new ArrayList<String>();
		Contacts.add("Иванов И.И.");
		Contacts.add("Петров П.П.");
		Contacts.add("Сидоров С.С.");
		Contacts.add("Иванов И.");
	}
	
	@Test
    public void testInit() {
        try {
        	App.init();
        	assertTrue(true);
        } catch (Exception e) {
        	assertTrue(false);
        }
    }
	
	@Test
    public void testMain() {
		int trueTest = 0;
		
		PrintStream ps = System.out;

		RedirectOutputStream ros = new RedirectOutputStream();
		
		System.setOut(new PrintStream(ros, true));		
		String[] args = new String[1];
		args[0] = Contacts.get(1);
		App.main(args);
		System.setOut(ps);        
		
		if ((ros.getBuffer().get(0).indexOf(args[0]) >= 0) && 
			(ros.getBuffer().get(1).indexOf("+8 800 2000 700") >= 0)) 
			trueTest++;
		
		System.setOut(new PrintStream(ros, true));		
		args[0] = Contacts.get(0);
		App.main(args);
		System.setOut(ps);        
		
		if ((ros.getBuffer().get(2).indexOf(args[0]) >= 0) &&
			(ros.getBuffer().get(3).indexOf("+8 800 2000 500") >= 0) &&
			(ros.getBuffer().get(4).indexOf("+8 800 200 600") >= 0))
			trueTest++;
		
		System.setOut(new PrintStream(ros, true));		
		args[0] = Contacts.get(2);
		App.main(args);
		System.setOut(ps);        
		
		if ((ros.getBuffer().get(5).indexOf(args[0]) >= 0) && 
			(ros.getBuffer().get(6).indexOf("+8 800 2000 800") >= 0) &&
			(ros.getBuffer().get(7).indexOf("+8 800 2000 900") >= 0) &&
			(ros.getBuffer().get(8).indexOf("+8 800 2000 000") >= 0))
			trueTest++;
		
		System.setOut(new PrintStream(ros, true));		
		args[0] = Contacts.get(3);
		App.main(args);
		System.setOut(ps);        
		
		if (ros.getBuffer().get(9).indexOf(args[0]) > 0) trueTest++;

		if (trueTest == 4) {
        	assertTrue(true);
        } else {
        	assertTrue(false);
        }
    }
}