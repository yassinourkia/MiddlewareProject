package fr.ensibs.token;
import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;



/**
 * @author OURKIA YASSIN
 * An application used to manage a single device. It can be used as a
 * standalone application as it provides a {@link #main(String[]}
 * method.
 *
 */
public class ServerToken 
{
    public static void main( String[] args )
    {
    	if (args.length != 3 ||  args[0].equals("-h")) {
      		System.out.println("Usage: java java -jar device-server-1.0.jar <host> <port> <rmi>");
		    System.out.println("<host> the ip of the server host");
		    System.out.println("<port> the port of the server to listen");
		    System.out.println("<rmi> server");
		    System.out.println("An application to interract with a device");
		    System.exit(-1);
    	}
    	else
    	{
	    	try 
	    	{
		    		LocateRegistry.createRegistry(Integer.parseInt(args[1]));
		    		TokenManager manager = new TokenManager();
		    		System.out.println("Mise en place du Security Manager ...");
		    		System.out.println(InetAddress.getLocalHost().getHostAddress());
		    		if (System.getSecurityManager() == null) {    		
		    			System.setSecurityManager(new RMISecurityManager());
		    		}   		
		    		String url = "rmi://"+args[0]+":"+args[1]+"/"+args[2];
		    		System.out.println("Enregistrement de l'objet avec l'url : " + url);
		    		Naming.rebind(url,   manager);    		
		    		System.out.println("Serveur lancé");
		    	} catch (Exception e) {e.printStackTrace();}
		}    		
    } 
}
