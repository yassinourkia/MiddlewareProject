package fr.ensibs.token;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import fr.ensibs.river.RiverLookup;
import net.jini.core.entry.UnusableEntryException;
import net.jini.core.transaction.TransactionException;
import net.jini.space.JavaSpace;

/**
* An implementation of a device managed by the system, characterized
* by an identifier, a name, and having a status which value changes
* along the system lifetime. This class is implemented to be deployed
* in a centralized application
*/
public class TokenManager extends UnicastRemoteObject implements IToken
{
	
  
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/**
  * 
  */
  public Role status;
  /**
   * 
   */
  public int tokenSize;
  RiverLookup finder ;
  JavaSpace space ;
  
  public TokenManager() throws Exception {
	 RiverLookup finder = new RiverLookup();
	 space = (JavaSpace) finder.lookup("localhost", 9065 , JavaSpace.class);
	 
		
  }
  
  public static void main(String[] args) throws Exception {
	  TokenManager manager = new TokenManager();
	  //Token token =manager.generateToken(Role.Seller);
	  //System.out.println(token.token);
	  //System.out.println("Tokens in the space");
	  //manager.getTokens();
	 //manager.deleteToken(new Token("customerN8RSC6VMH4HX92WHDA6VQBDSHNP8Q0"));
	 if(manager.TokenIsValide(new Token("customerN8RSC6VMH4HX92WHDA6VQBDSHNP8Q0")))
		 System.out.println("Token is valid");
	 else
		 System.out.println("Token is not valid");
	  
  }

  @Override
  public Token generateToken(Role role) throws Exception {
	  StringBuilder builder = new StringBuilder();
		do {
		int count = 30;
		String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		while (count-- != 0) {
		int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
		builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		} while(TokenIsValide(new Token(builder.toString())));
		if(role == Role.Seller) {
			String token = "seller"+builder.toString();
			Token entry = new Token(token);
			space.write(entry, null, Long.MAX_VALUE);
			return entry;	
		}
		else {
			String token ="customer"+builder.toString();
			Token entry = new Token(token);
			space.write(entry, null, Long.MAX_VALUE);
			return entry;
		}
  	
  }


  @Override
  public boolean TokenIsValide(Token token) throws RemoteException {
  	Token temp;
	try {
		temp = (Token) space.read(token, null, JavaSpace.NO_WAIT);
		return temp !=null;
		
	} catch (UnusableEntryException | TransactionException | InterruptedException e) {
		e.printStackTrace();
		return false;
	}
  	
  }


  @Override
  public void deleteToken(Token token) throws RemoteException {
	  Token temp;
		try {
			temp = (Token) space.take(token, null, JavaSpace.NO_WAIT);
			if( temp !=null)
				System.out.println("Token is deleted");
			else
				System.out.println("The input token is invalid");
			
		} catch (UnusableEntryException | TransactionException | InterruptedException e) {
			e.printStackTrace();
		}
  	
  }


  @Override
  public Role getRole() throws RemoteException {
  	
  	return null;
  }
  
  public void getTokens() throws RemoteException, UnusableEntryException, TransactionException, InterruptedException{
	  int count =0;
	  ArrayList<Token> tokens = new ArrayList<>();
	  Token token = new Token();
	  Token temp ;
	  while ( (temp=(Token) space.take( token , null , JavaSpace.NO_WAIT )) != null )
	  {
		  System.out.println(temp.token);
		  //space.write(temp, null, Long.MAX_VALUE);
		  count ++ ;
	  }
	  System.out.println("there's "+count+" token in the tupple space");
	  
	  
  }
  



}
