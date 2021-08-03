package forvo_API;


import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;

public class WrittingToXML {

    WrittingToXML(){

//        ObjectMapper mapper = new ObjectMapper();
        ObjectMapper mapper = new XmlMapper();
        try{
            InputStream inputStream = new FileInputStream(new File("/home/parallels/demo/persons.xml"));

            Item_FromXML_ToXML item = new Item_FromXML_ToXML();
/*            item.setFirstName("Jack");
            item.setLastName("Ryan");
            item.setAge(29);
            item.setAddress("");
            item.setCars("");
*/
            mapper.writeValue(new File("/home/parallels/demo/ItemsOut.xml"), item);
            inputStream.close();

        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
