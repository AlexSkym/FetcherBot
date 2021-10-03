package forvo_API;

import java.io.*;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
//import com.fasterxml.jackson.dataformat.xml.XmlMapper;


/**
 * Hello world!
 *
 */

public class Reading_FromXML
{
    Reading_FromXML(){
        try {

            ObjectMapper mapper = new XmlMapper();
            InputStream inputStream = new FileInputStream(new File("/home/parallels/demo/Item_FromXML_ToXML.xml"));
            TypeReference<List<Item_FromXML_ToXML>> typeReference = new TypeReference<List<Item_FromXML_ToXML>>() {};
            List<Item_FromXML_ToXML> items = mapper.readValue(inputStream, typeReference);
            for(Item_FromXML_ToXML p : items) {
//                System.out.println("name is "+p.getFirstName()+" city is "+p.getAddress().getCity()+" first car is "+p.getCars()[0]+" age is "+p.getAge());
            }
            Item_FromXML_ToXML item = new Item_FromXML_ToXML();
/*            item.setFirstName("Jack");
            item.setLastName("Ryan");
            item.setAge(29);
            item.setAddress("");
            item.setCars("");
*/
            mapper.writeValue(new File("/home/parallels/demo/ItemsOut.xml"), item);
            inputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
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

