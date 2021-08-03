package forvo_API.data_processes.xml_processes;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class WriterForXML implements Reader_and_Writer_ForXML {


    /**
     * Write and XML file from a string obtained from a website
     * @param url it's necessary a url to get a xml file filled out.
     */
    public WriterForXML(String url) throws IOException {
        //url to String
        String string_xml = urlOfTheXML_ToString(url);

        //sorting the xml file by using the String (only to see it better by my own)
        String string_xmlSorted = sortingXMLFile_throughTheString(string_xml);

        //xml (in a String format) to an xml file
        stringToDom(string_xmlSorted);
    }

/*
    void write_XMLFile(List<ItemForXML> list){

        ObjectMapper mapper = new XmlMapper();
        try{
            InputStream inputStream = new FileInputStream(new File(path+name));

            ItemForXML item = new ItemForXML();

            //item.setFirstName("Jack");
            //item.setLastName("Ryan");
            //item.setAge(29);
            //item.setAddress("");
            //item.setCars("");

            mapper.writeValue(new File(path+name), item);
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
*/

    /**
     * Getting the xml string from a website
     * @param url from where it'll find the xml of the webpage
     * @return the xml file in a String format
     * @throws IOException
     */
    private String urlOfTheXML_ToString(String url) throws IOException {
        URL obj = new URL(url);
        //fixing the error: 403
        HttpURLConnection con = (HttpURLConnection) obj.openConnection(); //https://stackoverflow.com/questions/3869372/java-io-ioexception-server-returned-http-response-code-403-for-url#answer-5202215
        //con.setRequestMethod("GET");
        //con.setInstanceFollowRedirects(false); // idk
        con.addRequestProperty("User-Agent", "Mozilla/4.76"); //https://stackoverflow.com/questions/3869372/java-io-ioexception-server-returned-http-response-code-403-for-url#answer-5202215
        //System.setProperty("http.agent", "Chrome");

        int responseCode = con.getResponseCode();
        System.out.println("Response Code : " + responseCode);  //https://www.clickminded.com/status-code-200/ = 200 is fine, perfect!
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        System.out.println("response= "+response.toString());

        //getting the method below
        return response.toString();
    }

    /**
     * Sorting the xml file by using the String (only to see it better by my own)
     * @param string_xml the xml string disorganized (as it comes).
     * @return the "xml string" sorted out;
     */
    private String sortingXMLFile_throughTheString(String string_xml){
        String string_xmlSorted = "";

        string_xmlSorted = string_xml.replaceAll(">",">\n");

        return string_xmlSorted;
    }

    /** Parse from String to XML and write/create that XML file.
     *
     * @param xmlSource the String which contents an XML in its format
     */
    private void stringToDom(String xmlSource) throws IOException {
        java.io.FileWriter fw = new java.io.FileWriter(path+name);
        fw.write(xmlSource);
        fw.close();
    }


}
