package forvo_API.data_processes;

import forvo_API.data_processes.xml_processes.WriterForXML;
import forvo_API.data_processes.fixedVariables.MapperEnumFixedVar;
import zUtil.MyProperties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class CreatingTheURL_and_writing {

    //instance to send the url got, and get the XML file filled out.
    WriterForXML writerForXML = null;

    //example of returning: https://apifree.forvo.com/key/1d41a16fe2fc7be20028f9b634997dda/format/xml/action/word-pronunciations/word/barcelona/group-in-languages/true

    private String theUrl="";   //the url which will be returned
    MapperEnumFixedVar mapper_urv = new MapperEnumFixedVar();  //instance of the class which contains every prefix/complex

    public CreatingTheURL_and_writing(String word) throws IOException {
        theUrl = urlWithinUserSpecs(word);
        writerForXML = new WriterForXML(theUrl);
    }


    /*
    Methods which look like Constructors:
     */

    /**returns a url taking in count some specifications
     *
     * @param word
     * @return theURL to be scraped
     */
    private String urlWithinUserSpecs(String word) {

        //Linking the beginning of the url
        theUrl = addBeginningOfTheUrl();

        //Linking the our personal Key from "file.properties" file
        theUrl += addPersonalKey();

        //Linking the type of format file I want to receive
        theUrl += addTypeOfFormatFile();

        //Linking the type of service I want to receive from forvo.com
        theUrl += addTypeOfService();

        //Linking the Word that a user asked for.
        theUrl += addWord(word);

        return theUrl;
    }


    /*
     **************************************************
     Methods to implement within the above constructors
     **************************************************
     */


    /**
     * To help to shape the perfect forvo url for every user specs
     * @return the beginning of the url
     */
    private String addBeginningOfTheUrl() {
        return "https://apifree.forvo.com/";
    }

    /**
     * To help to shape the perfect forvo url for every user specs
     * @return our personal Key from "file.properties" file
     */
    private String addPersonalKey() {
        MyProperties myProperties = new MyProperties();
        return "key/"+myProperties.getMyProperties().getProperty("forvoKey") + "/";
    }

    /**
     * To help to shape the perfect forvo url for every user specs
     * @return the type of format file I want to receive
     */
    private String addTypeOfFormatFile() {
        return "format/xml/";
    }

    /**
     * To help to shape the perfect forvo url for every user specs
     * @return the type of service I want to receive from forvo.com
     */
    private String addTypeOfService() {
        return "action/word-pronunciations/";
    }

    /**
     * To help to shape the perfect forvo url for every user specs
     * @param word
     * @return the Word that a user asked for
     */
    private String addWord(String word) {
        return "word/"+word+"/";
    }


}
