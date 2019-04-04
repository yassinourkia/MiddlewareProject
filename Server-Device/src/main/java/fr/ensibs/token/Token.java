package fr.ensibs.token;

import net.jini.core.entry.Entry;

public class Token implements Entry{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String token;
	String username;
	public Token() {
		
	}

	public Token(String token)
	{
		this.token=token;
	}
}
