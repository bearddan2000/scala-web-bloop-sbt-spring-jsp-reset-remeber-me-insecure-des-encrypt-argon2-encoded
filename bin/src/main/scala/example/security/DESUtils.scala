package example.security;

import java.io._;
import java.util._;
import java.security._;
import javax.crypto._;
import javax.crypto.spec._;
import org.apache.commons.codec.binary.Base64;

class DESUtils ()
{

  val encryptCipher: Cipher = {
    try { genKey();}
    catch {
      case e: Exception => {
        e.printStackTrace()
        null
      }
    }
  }

  /**
   * Construct a new object which can be utilized to encrypt
   * with a DES encryption algorithm.
   *
   * @param key The secret key used in the crypto operations.
   * @throws Exception If an error occurs.
   *
   */
  @throws(classOf[Exception])
  def genCipher(key: SecretKey): Cipher = {
      val cipher = Cipher.getInstance("DES");
      cipher.init(Cipher.ENCRYPT_MODE, key);
      return cipher
  }

  @throws(classOf[Exception])
  def genKey(): Cipher = {
      //Generate the secret key
      val password = "abcd1234";
      val key = new DESKeySpec(password.getBytes());
      val keyFactory = SecretKeyFactory.getInstance("DES");
      val secretKey = keyFactory.generateSecret(key);
      val salt = java.util.Base64.getEncoder().encodeToString(secretKey.getEncoded());
      println("Salt String: "+salt);
      genCipher(secretKey)
  }

  @throws(classOf[Exception])
  def encrypt(message: String): Array[Byte] = {
      // Encode the string into bytes using utf-8
      val unencryptedByteArray: Array[Byte] = message.getBytes("UTF8");

      // Encrypt
      val encryptedBytes: Array[Byte] = encryptCipher.doFinal(unencryptedByteArray);

      // Encode bytes to base64 to get a string
      Base64.encodeBase64(encryptedBytes);
  }

}
