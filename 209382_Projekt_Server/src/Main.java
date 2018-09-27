import java.awt.AWTException;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.MouseInfo;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;



public class Main {

	
	public static void main(String[] args) {
		int count = 0;
		int maxTries = 3;
		
		

		 		
		while(true){
			try {
				Thread thread = new Thread();
				thread.run();
			}catch (Exception e) {
		        if (++count == maxTries) throw e;			
		}
		}
	}
}



