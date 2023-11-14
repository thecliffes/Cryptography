import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Merkle {


   public static void main(String[] args) {
      int[] myNum = {3,5,6,7,8,9};
      System.out.println(binarySearch(8,myNum));
   }

   public static int binarySearch (int x, int[] xs) {
      int lowerBound = 0;
      int upperBound = xs.length -1;
      while ( lowerBound < upperBound) {
         int mid = lowerBound/2 + upperBound/2;
         if (x == xs[mid]) {
            return mid;
         } else if (x < xs[mid]) {
            upperBound = mid;
         } else {
            lowerBound = mid;
         }
      }
      return -1;
   }
}


