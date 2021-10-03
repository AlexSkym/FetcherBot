package forvo_API;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * This class have been got from https://www.youtube.com/watch?v=F3hWv6PimB8
 */
public class GettingXMLFile_fromAUrl_example {

    public static void main(String[] args) {

        GettingXMLFile_fromAUrl_example oobj_Test_HTTP_XML=new GettingXMLFile_fromAUrl_example();
        oobj_Test_HTTP_XML.get_response();

    }
    public void get_response(){
        try {
            String api_key="1d41a16fe2fc7be20028f9b634997dda";
            String format="xml";
            String word = "hola";
            String url = "https://apifree.forvo.com/key/"+api_key+"/format/"+format+"/action/standard-pronunciation/word/"+word;
            System.out.println("url: "+url);
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
            //print in String
            System.out.println(response.toString());    //the variable "response" contains everything I need
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .parse(new InputSource(new StringReader(response.toString())));
            NodeList errNodes = doc.getElementsByTagName("items"); //"items" the name of the first tag; "item" is the second one.
            if (errNodes.getLength() > 0) {
                Element err = (Element)errNodes.item(0);
                System.out.println("item -"+err.getElementsByTagName("item").item(0).getTextContent());
                err = (Element) err.getElementsByTagName("item");
                System.out.println("word -"+err.getElementsByTagName("word").item(0).getTextContent());
                System.out.println("sex -"+err.getElementsByTagName("sex").item(0).getTextContent());
                System.out.println("country -"+err.getElementsByTagName("country").item(0).getTextContent());
                System.out.println("pathmp3 -"+err.getElementsByTagName("pathmp3").item(0).getTextContent());
            } else {
                // success
                System.out.println("else");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}

