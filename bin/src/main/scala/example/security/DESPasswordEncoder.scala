package example.security;

import javax.xml.bind.DatatypeConverter;

import org.springframework.security.crypto.password.PasswordEncoder;

class DESPasswordEncoder()
 extends org.springframework.security.crypto.argon2.Argon2PasswordEncoder
 with PasswordEncoder {

 val utils: DESUtils = new DESUtils()

    override def encode(rawPassword: CharSequence): String = {
      try {
        val plainText = rawPassword.toString();
        val rsaText = utils.encrypt(plainText);
        return super.encode(DatatypeConverter.printHexBinary(rsaText));
      } catch{
        case e: Exception => e.printStackTrace()
      }
      return super.encode(rawPassword);
    }

    override def matches(rawPassword: CharSequence, encodedPassword: String): Boolean =
    {
     try {
        val plainText = rawPassword.toString();
        val rsaText = utils.encrypt(plainText);
        val plain = DatatypeConverter.printHexBinary(rsaText);
       return super.matches(plain, encodedPassword);
       } catch{
         case e: Exception => e.printStackTrace()
       }
     return false;
    }
}
