package forvo_API.data_processes.xml_processes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import forvo_API.data_processes.ItemForXML;
import forvo_API.data_processes.xml_processes.Reader_and_Writer_ForXML;

import java.io.*;
import java.util.List;

public class ReaderForXML implements Reader_and_Writer_ForXML {

    /**
     * Constructor
     */
    public ReaderForXML(){ }



    /**
     * Read the values from XML File
     *
     * @return This return a "{@code List<ItemForXML>}" containing every value got from the XML file
     */
    public List<ItemForXML> read_XMLFile() {
        List<ItemForXML> theItems = null;

        try {
            ObjectMapper mapper = new XmlMapper();

            InputStream inputStream = new FileInputStream(new File(path+name));
            TypeReference<List<ItemForXML>> typeReference = new TypeReference<>() {};

            theItems = mapper.readValue(inputStream, typeReference);

            System.out.println("-success when reading the XML file");
            inputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        return theItems;
    }

}
