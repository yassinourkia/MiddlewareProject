package fr.ensibs.spring.interfaces;

import java.math.BigInteger;

/**
 *
 * @author Mehdi
 */
public interface IEncrypt {

    public String bigIntegerToBinaryString(BigInteger number);

    public String numberToString(BigInteger number);

    public int binaryStringToInt(String param);

    public void generateKey(int bits);

    public boolean isBinaryRepresentation(String param);

    public BigInteger encrypt(BigInteger message);
    
    public  String decrypt(String message);
}
