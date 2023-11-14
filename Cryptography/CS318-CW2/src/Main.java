import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

public class Main extends CryptoLib {

    public static void main(String[] args) throws InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException{
        PuzzleCreator pc = new PuzzleCreator();

        pc.createPuzzles();

        pc.encryptPuzzlesToFile("puzzles.bin");
        pc.readFile("puzzles.bin");



        byte[] dk = pc.createRandomKey();
        SecretKey ak = pc.generateAESKeyByte();

        Puzzle p = new Puzzle(3, ak);

        System.out.println(Arrays.toString(pc.encryptPuzzle(dk, p)));

        System.out.println(pc.createPuzzles().size());

        System.out.println(Arrays.toString(pc.generateAESKeyByte().getEncoded()));

    }
}
