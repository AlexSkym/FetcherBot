package google_sheets;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

public class SheetsQuickstart {
	
	//In order to send values to "Library.java"
	private static String theText = "";
	//range of cells I'll get from a sheet of my GoogleSheet.   ex.: //final String range = "Class Data!A2:E";
	private static String myOwnRange;
	
	//Starting 
    private static final String APPLICATION_NAME = "Google Sheets API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";


    /**
     * Global instance of the scopes required by this quick start.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);
    													//"/credentials.json"
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

    /**
     * Creates an authorized Credential object.
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = SheetsQuickstart.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    /**
     * Prints the names and majors of students in a sample spreadsheet:
     * https://docs.google.com/spreadsheets/d/1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms/edit
     */
    //public static void main(String... args) throws IOException, GeneralSecurityException {
    public static void Starting() throws IOException, GeneralSecurityException {
    	    	
    	// Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        							//https://docs.google.com/spreadsheets/d/101O_Vi8KFvxQjPWFjI6_yptR7OTGJH0-J5xxzGfG4LQ/edit#gid=429461805
        //final String spreadsheetId = "1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms";		//original
        final String spreadsheetId = "101O_Vi8KFvxQjPWFjI6_yptR7OTGJH0-J5xxzGfG4LQ";	//ours
        //final String range = "Class Data!A2:E";       //original
        final String range = myOwnRange;
        Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
        ValueRange response = service.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute();
        List<List<Object>> values = response.getValues();
        if (values == null || values.isEmpty()) {
            System.out.println("No data found.");
            theText = "";
            //theText = "No data found";
            theText = "Pido disculpas, no encontr� ning�n dato.";
        } else {
        	theText = "";
            for (List row : values) {
                // Print columns A and E, which correspond to indices 0 and 4.
                //System.out.printf("%s, %s\n", row.get(1), row.get(4));
            	System.out.printf("%s\n", row.get(0));	//Esta expresa el n�mero de la columna del exel, pero... 
															//hay que recordar que estoy tratando con un Array,
															//entonces es directamente 0 la primer y �nica columna que nombr� 
															//y seleccion� .
                theText += row;

                //kick off the '[' and ']'
                System.out.println("theText: " +theText);
                theText = theText.substring(1,theText.length()-1);
                System.out.println("theText: " +theText);

            }
        }
    }
    
    /**
     * Treatment of the texts: 
     * 		here I have to get the text and remove every sign: "```" 
     * @return a clean string with the size people asked for
     */
    private String cleanText (String theText) {
    	//code...
    	return theText;
    }
    
	public String getTheText() {
        return theText;
	}

	/*
	 * public String getMyOwnRange() { return myOwnRange; }
	 */
	
	
	public void setMyOwnRange(String name_of_sheet) {
		this.myOwnRange = name_of_sheet;
	}
	
}

