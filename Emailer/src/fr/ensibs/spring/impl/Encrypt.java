package fr.ensibs.spring.impl;

import fr.ensibs.spring.interfaces.IEncrypt;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mehdi
 */
public class Encrypt implements IEncrypt {

    private BigInteger n, d, e;
    private int bitlen;

    @Override
    public String bigIntegerToBinaryString(BigInteger number) {
        if (number == null || number.compareTo(BigInteger.ZERO) < 0) {
            try {
                throw new Exception("Le nombre ne peut etre converti en binaire");
            } catch (Exception ex) {
                Logger.getLogger(Encrypt.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        String result = null;
        if (number.compareTo(BigInteger.ZERO) >= 0) {
            result = "";
            BigInteger val = number;
            while (val.compareTo(BigInteger.ONE) > 0) {
                result = val.mod(BigInteger.valueOf(2)) + result;
                val = val.divide(BigInteger.valueOf(2));
            }
            result = val + result;
            if ((result.length() % 8) != 0) {
                int size = 8 - (result.length() % 8);
                for (int i = 1; i <= size; i++) {
                    result = "0" + result;
                }
            }
        }
        return result;
    }

    @Override
    public String numberToString(BigInteger number) {
        String binary = bigIntegerToBinaryString(number);
        if (!isBinaryRepresentation(binary)) {
            try {
                throw new Exception("L'entier ne peut etre converti en chaine de caractere");
            } catch (Exception ex) {
                Logger.getLogger(Encrypt.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        String param = binary;
        if ((param.length() % 8) != 0) {
            int count = 8 - (param.length() % 8);
            for (int i = 1; i <= count; i++) {
                param = "0" + param;
            }
        }

        int begin = 0, incr = 8;
        String result = "";
        while (begin < param.length()) {
            String aux = param.substring(begin, begin + incr);
            int value = binaryStringToInt(aux);
            if (value < 0 || value > 255) {
                try {
                    throw new Exception("L'entier ne peut etre converti en chaine de caractere ASCii");
                } catch (Exception ex) {
                    Logger.getLogger(Encrypt.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            result += ((char) value);
            begin += incr;
        }
        return result;
    }

    /**
     * Retourne la conversion en decimal du parametre si il existe, sinon -1
     *
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public int binaryStringToInt(String param) {
        if (!isBinaryRepresentation(param)) {
            try {
                throw new Exception("Erreur Lors de la conversion binaire-entier");
            } catch (Exception ex) {
                Logger.getLogger(Encrypt.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        int pow = 0;
        int result = 0;
        for (int i = param.length() - 1; i >= 0; i--) {
            result += (param.charAt(i) == '1' ? 1 : 0) * ((int) Math.pow(2, pow));
            pow++;
        }
        return result;
    }

    /**
     * Generate a new public and private key set.
     *
     * @param bits
     */
    @Override
    public void generateKey(int bits) {
        bitlen = bits;
        SecureRandom r = new SecureRandom();
        BigInteger p = new BigInteger(bitlen / 2, 100, r);
        BigInteger q = new BigInteger(bitlen / 2, 100, r);
        n = p.multiply(q);
        BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        e = new BigInteger("3");
        while (m.gcd(e).intValue() > 1) {
            e = e.add(new BigInteger("2"));
        }
        d = e.modInverse(m);
    }

    /**
     * Check if the parameter is a binary representation
     *
     * @param param
     * @return
     */
    @Override
    public boolean isBinaryRepresentation(String param) {
        if (param != null && param.length() > 0) {
            boolean result = true;
            for (int i = 0; i < param.length(); i++) {
                if (param.charAt(i) != '0' && param.charAt(i) != '1') {
                    result = false;
                    break;
                }
            }
            return result;

        } else {
            return false;
        }
    }

    /**
     * Encrypt the given message.
     */
    @Override
    public BigInteger encrypt(BigInteger message) {
        return message.modPow(e, n);
    }

    /**
     * Encrypt the given message.
     */
    @Override
    public String decrypt(String message) {
        return new String((new BigInteger(message)).modPow(d, n).toByteArray());
    }

}
