import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * A collection of methods to generate a puzzle object and get information regarding the puzzle object
 * @author Ben Cliffe
 */

public class Puzzle extends CryptoLib {
    /**
     * variables used in constructor
     */
    final private int puzzleNumber;
    final private SecretKey key;

    /**
     * constructor used to access any methods in class file
     * @param PuzzleNumber Unqiue int assigned to puzzle
     * @param Key Secretkey assigned to puzzle
     */
    public Puzzle(int PuzzleNumber, SecretKey Key){
        puzzleNumber = PuzzleNumber;
        key = Key;
    }

    /**
     * Getter for the variable 'puzzleNumber'
     * @return Puzzle number of the object
     */
    public int getPuzzleNumber(){
        return puzzleNumber;
    }

    /**
     * Getter for the variable 'key'
     * @return SecretKey of the object
     */
    public SecretKey getKey(){
        return key;
    }

    /**
     * Create plaintext of the puzzle in terms of a byte
     * @return ByteArray containing plaintext of object
     */
    public byte[] getPlainText(){
        byte[] plainText = new byte[16];
        for(int i=0;i<15;i++){
            plainText[i] = 0;
        }
        return plainText;
    }

    /**
     * Makes puzzle number in ByteArray form
     * @param number Number assigned to the puzzle
     * @return ByteArray equivalent of the puzzle number
     */
    public byte[] getPuzzleNumberByte(int number){
        byte[] numberByte = new byte[1];
        if(number <= 4096 && number >= 1){
            numberByte = smallIntToByteArray(number);
        }
        return numberByte;
    }

    /**
     * Creates SecretKey of the puzzle in the form of a ByteArray
     * @return ByteArray equivalent of the puzzle SecretKey
     */
    public byte[] getKeyByte(){
        byte[] keyByte;
        keyByte =  key.getEncoded();
        return keyByte;
    }

    /**
     * Creates ByteArray with information regarding everything about the puzzle
     * @return ByteArray equivalent of the puzzle object
     */
    public byte[] getPuzzleAsBytes(){
        byte[] one = getPlainText();
        byte[] two = getPuzzleNumberByte(puzzleNumber);
        byte[] three = getKeyByte();

        byte[] allByteArray = new byte[34];

        ByteBuffer buff = ByteBuffer.wrap(allByteArray);
        buff.put(one);
        buff.put(two);
        buff.put(three);

        return buff.array();
    }

}
