import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;


 
public class Thread implements Runnable {
	private static ServerSocket server = null;
	private static Socket client = null;
	private static BufferedReader in = null;
	private static String line;
	private static boolean isConnected=true;
	private static Robot robot;
	private static final int SERVER_PORT = 8080;
	
	public void run() {
		
		 try{
		    	System.out.println("Socket open. IP: "+ Inet4Address.getLocalHost().getHostAddress()+ " port:"+SERVER_PORT);
		    	System.out.println("Waiting for Client to connect...");
		    	
		    	robot = new Robot();
				server = new ServerSocket(SERVER_PORT); 
				client = server.accept(); 
				in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				
			}catch (IOException e) {
				System.out.println("Error in opening Socket");
				System.exit(-1);
			}catch (AWTException e) {
				System.out.println("Error in creating robot instance");
				System.exit(-1);
			}
		    System.out.println("Connected.\n"+client);
		    
		    while(isConnected){
		        try{
		        	line = in.readLine(); 
		        	System.out.println(line); 
		        	
		        	//keyboard
		        	if(line.contains("#1!")) 
		        	{ 
		        		line = line.split("#1!")[1];
		        		
		        		if(line.contains("kID")){
			        		int kid=Integer.parseInt(line.split("_")[1]);	
			        		robot.keyPress(kid);
			        		robot.keyRelease(kid);
			        	}	
			        	
			        	else if(line.contains("kSPEC")){
			        		String kSPEC = line.split("_")[1];	
			        		
			        		switch(kSPEC) {
			        		
			        		case "ENTER": 
			        			robot.keyPress(KeyEvent.VK_ENTER);
			        			robot.keyRelease(KeyEvent.VK_ENTER);
			        			break;
			        			
			        		case "SHIFT":
			         				robot.keyPress(KeyEvent.VK_SHIFT);	
			        				robot.keyRelease(KeyEvent.VK_SHIFT);
			        			break;	        		
			        		
			        		case "HOME":
			        			robot.keyPress(KeyEvent.VK_HOME);
			        			robot.keyRelease(KeyEvent.VK_HOME);	
			        			break;
			        		
				    		case "DELETE":
				    			robot.keyPress(KeyEvent.VK_DELETE);
				    			robot.keyRelease(KeyEvent.VK_DELETE);	
				    			break;
				    			
				    		case "END":
				    			robot.keyPress(KeyEvent.VK_END);
				    			robot.keyRelease(KeyEvent.VK_END);	
				    			break;
				    			
				    		case "INSERT":
				    			robot.keyPress(KeyEvent.VK_INSERT);
				    			robot.keyRelease(KeyEvent.VK_INSERT);
				    			break;
				    			
				    		case "MINUS":
				    			robot.keyPress(KeyEvent.VK_MINUS);
				    			robot.keyRelease(KeyEvent.VK_MINUS);
				    			break;
				    			
				    		case "PLUS":
				    			robot.keyPress(KeyEvent.VK_EQUALS);
				    			robot.keyRelease(KeyEvent.VK_EQUALS);
				    			break;
				    			
				    		case "OB":
				    			robot.keyPress(KeyEvent.VK_OPEN_BRACKET);
				    			robot.keyRelease(KeyEvent.VK_OPEN_BRACKET);
				    			break;
				    			
				    		case "CB":
				    			robot.keyPress(KeyEvent.VK_CLOSE_BRACKET);
				    			robot.keyRelease(KeyEvent.VK_CLOSE_BRACKET);
				    			break;
				    			
				    		case "COLON":
				    			robot.keyPress(KeyEvent.VK_SEMICOLON);
				    			robot.keyRelease(KeyEvent.VK_SEMICOLON);
				    			break;
				    			
				    		case "QUOTE":
				    			robot.keyPress(KeyEvent.VK_QUOTE);
				    			robot.keyRelease(KeyEvent.VK_QUOTE);
				    			break;
				    			//
				    		case "SLASH":
				    			robot.keyPress(KeyEvent.VK_SLASH);
				    			robot.keyRelease(KeyEvent.VK_SLASH);
				    			break;
				    			
				    		case "DOT":
				    			robot.keyPress(KeyEvent.VK_PERIOD);
				    			robot.keyRelease(KeyEvent.VK_PERIOD);
				    			break;
				    			
				    		case "COMMA":
				    			robot.keyPress(KeyEvent.VK_COMMA);
				    			robot.keyRelease(KeyEvent.VK_COMMA);
				    			break;
				    			
				    		case "BSLASH": 
				    			robot.keyPress(KeyEvent.VK_BACK_SLASH);
				    			robot.keyRelease(KeyEvent.VK_BACK_SLASH);
				    			break;
				    			
				    		case "WINDOWS":
				    			robot.keyPress(KeyEvent.VK_WINDOWS);
				    			robot.keyRelease(KeyEvent.VK_WINDOWS);
				    			break;
				        		
			        		} //switch
			        	}//if
		        	}
		        		
		        	//touchpad	
		        	else if(line.contains("#2!")) 
		        	{
		        		line = line.split("#2!")[1];
		        		
		        		switch(line) {
		        		
		        		case "LMB":
							robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
							robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);	
							break;
							
		        		case "RMB":
							robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
							robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);	
							break;
							
		        		case "MMB":
							robot.mousePress(InputEvent.BUTTON2_DOWN_MASK);
							robot.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);	
							break;	
							
						default:
							float movex=Float.parseFloat(line.split(",")[0]);
							float movey=Float.parseFloat(line.split(",")[1]);
							Point point = MouseInfo.getPointerInfo().getLocation();
							float nowx=point.x;
							float nowy=point.y;
							robot.mouseMove((int)(nowx+movex),(int)(nowy+movey));		
							break;
		        		}
		        	}       	
				
			        if(line.contains("EXIT")) {
			        	try {			   		
			        		server.close();
			        		client.close();
			        	}catch (IOException e) {
							System.out.println("Error in closing Socket");
			        	}
			        	
			        System.out.println("Server closed.");
			        System.out.println("Client closed.");
					isConnected=false;
					}
				
		        } catch (IOException e) {
					System.out.println("Read failed");
					//System.exit(-1);
		        }

	      	}
	
	}
	
	
}
