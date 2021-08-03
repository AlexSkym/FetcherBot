package forvo_scraping;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.net.MalformedURLException;

public class main {

    String stringURL = "https://forvo.com/word/hola/#es";

    public static void main(String[] args) {

    try (final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_78)) {

//        HtmlPage page = webClient.getPage("https://forvo.com/word/hola/#es");
        HtmlPage page = webClient.getPage("https://voca.ro/13KPCoEARVxM");


        //HtmlButton button = (HtmlButton) page.getByXPath("//div[@ofLink statusGrey']").get(1) ;
        //page = button.click();

//       HtmlElement htmlElement = page.getFirstByXPath("//Button__button");
//       HtmlElement htmlElement = page.getFirstByXPath("//div[]");
//        HtmlElement htmlElement = page.getElementByName("Descargar");
        HtmlElement htmlElement = (HtmlElement) page.getByXPath("div[@Button__button]").get(1);

        if (htmlElement.getTextContent().toLowerCase().contains("descargar")) {
            htmlElement.click();
            System.out.println("si cliquea");
        }else{
            System.out.println("no cliquea");
        }

        //to receive a download.
        HtmlInput a;


        System.out.println();

    } catch (MalformedURLException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }


    }

}
