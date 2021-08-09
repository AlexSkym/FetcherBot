package zUtil;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * Encoding and decoding a string
 *  It's necessary because sometimes a string cannot be sent to Discord as the same encode as I use "System.out.println()"
 */
public class AddingNewStringUnicode {

    public AddingNewStringUnicode() {

    }


    /**
     * Encoding and decoding a string
     * @return a string into UTF-8
     */
    public String newUnicode_UTF_8(String str){
        ByteBuffer buffer = StandardCharsets.UTF_8.encode(str);
        String encoded_String = StandardCharsets.UTF_8.decode(buffer).toString();

        System.out.println("EcondedString.UTF-8: " +encoded_String);
        return encoded_String;
    }

    /**
     * Encoding and decoding a string
     *  It's necessary because sometimes a string cannot be sent to Discord as the same encode as I use "System.out.println()"
     * @return a string into UTF-8 or US-ASCII or ISO-8859-1
     */
    public String newUnicode_ISO_8859_1(String str){
        ByteBuffer buffer = StandardCharsets.ISO_8859_1.encode(str);
        String encoded_String = StandardCharsets.ISO_8859_1.decode(buffer).toString();

        System.out.println("EcondedString.ISO-8859_1: " +encoded_String);
        return encoded_String;
    }

}
