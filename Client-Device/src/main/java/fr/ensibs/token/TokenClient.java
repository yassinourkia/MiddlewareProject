package fr.ensibs.token;

import java.io.Closeable;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Scanner;

public class TokenClient implements Closeable{
	
	public IToken tokenmanager;
	public String username;
	public TokenClient(String username,IToken manager) {
		this.tokenmanager = manager;
		this.username = username;
	}
	private static void usage()
	  {
	    System.out.println("Usage: java java -jar device-client-1.0.jar <device_name> <host> <port> <rmi>");
	    System.out.println("<device_name> ip of the device ");
	    System.out.println("<host> the ip of the server host");
	    System.out.println("<port> the port of the server to listen");
	    System.out.println("<rmi> server");
	    System.out.println("An application to interract with a device");
	    System.exit(-1);
	  }
	
	public static void main(String[] args) throws Exception
	  {
	    if (args.length != 4 ||  args[0].equals("-h")) {
	      usage();
	    }
	    else
	    {
		    if (System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}
				String url = "rmi://"+args[1]+":"+Integer.parseInt(args[2])+"/"+args[3];
				System.out.println(url);
				Remote r = Naming.lookup(url);	
				if(r == null)
					System.out.println("Nothings");
					if (r instanceof IToken) {
						IToken manager = (IToken)r;
						TokenClient instance = new TokenClient(args[0], manager);
					    instance.run();
					}
					else 
						System.out.println("probleme ici");
			
	    }
		    
	  }
	 public void run() throws Exception
	  {
	    System.out.println("running "+this.username);
	    Token token = this.tokenmanager.generateToken(Role.Customer);
	    System.out.println(token.token);
	    
	  }
	@Override
	public void close() throws IOException {
		
		
	}
}
