package fr.ensibs.token;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface IToken extends Remote
{
	Token generateToken(Role role) throws RemoteException, Exception;
	boolean TokenIsValide(Token token) throws RemoteException;
	void deleteToken(Token token) throws RemoteException;
	Role getRole() throws RemoteException;
	void getTokens() throws Exception;
	

}
