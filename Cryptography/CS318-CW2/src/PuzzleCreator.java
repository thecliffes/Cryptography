import javax.crypto.*;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Collection of methods to generate puzzles and encrypt them
 * @author Ben Cliffe
 */
public class PuzzleCreator extends CryptoLib {

    /**
     * ArrayList of all generated puzzles
     */
    private ArrayList<Puzzle> puzzles = new ArrayList<Puzzle>();

    /**
     * constructor used to access any methods in class file
     */
    public PuzzleCreator() {

    }

    /**
     * Creates AES key in form of ByteArray
     * @return ArrayByte equivalent of AES key
     */
    public SecretKey generateAESKeyByte() {
        byte[] randomKeyBytes = new byte[16];
        Random random = new Random();
        random.nextBytes(randomKeyBytes);
        SecretKey k = null;
        try {
            k = createAESKey(randomKeyBytes);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return k;
    }

    /**
     * Creates 4096 puzzles and adds them to arraylist
     * @return ArrayList of puzzles
     */
    public ArrayList<Puzzle> createPuzzles() {
        SecretKey key = null;

        for (int i = 1; i < 4097; i++) {
            key = generateAESKeyByte();
            Puzzle ps = new Puzzle(i, key);
            puzzles.add(ps);
        }
        return puzzles;
    }

    /**
     * Creates a DES key in form of ByteArray
     * @return ByteArray equivalent of DES key
     */
    public byte[] createRandomKey() {
        byte[] DESKeyBytes = new byte[8];
        Random random = new Random();
        random.nextBytes(DESKeyBytes);

        for (int i = 2; i < 8; i++) {
            DESKeyBytes[i] = 0;
        }
        return DESKeyBytes;
    }

    /**
     * Encrypt provided puzzle with key that is provided to method
     * @param key Secret key which will be used to encrypt puzzle
     * @param puzzle Puzzle to be encrypted
     * @return ByteArray with information of the encrypted puzzle
     */

    public byte[] encryptPuzzle(byte[] key, Puzzle puzzle) {
        Cipher myCipher = null;
        try {
            myCipher = Cipher.getInstance("DES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
        try {
            myCipher.init(Cipher.ENCRYPT_MODE, createDESKey(key));
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            return myCipher.doFinal(puzzle.getPuzzleAsBytes());
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return key;
    }

    /**
     * Read a specified file and return information in terms of a 40 sized ByteArray until entire file is read
     * @param str Filename to read
     */
    public void readFile(String str) {
        FileInputStream reader = null;
        try {
            reader = new FileInputStream(str);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 4096; i++) {
            byte[] b = new byte[40];
            for (int j = 0; j < 40; j++) {
                try {
                    b[j] = (byte) reader.read();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Arrays.toString(b));
            System.out.println(b.length);
        }
    }

    /**
     * Encrypts all puzzles in a given ArrayList to a specified file
     * @param file Filename to read
     */
    public void encryptPuzzlesToFile(String file) {
        FileOutputStream writer = null;
        try {
            writer = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (Puzzle p : puzzles) {
            try {
                writer.write(encryptPuzzle(createRandomKey(), p));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Finds SecretKey corresponding to a given number
     * @param number Puzzle number used to look for SecretKey
     * @return SecretKey if number is found
     */
    public SecretKey findKey(int number) {
        SecretKey key = null;
        for (Puzzle p : puzzles) {
            if (p.getPuzzleNumber() == number) {
                key = p.getKey();
            }
        }
        return key;
    }

}