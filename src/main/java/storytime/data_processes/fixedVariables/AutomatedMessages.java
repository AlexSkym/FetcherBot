package storytime.data_processes.fixedVariables;

public class AutomatedMessages {

    public AutomatedMessages() {

    }

    /**
     * A success message when everything was fine at the end.
     * @return a string with a successful message
     */
    public String getSuccessMessage() {
        return "__There you go! GL!__";
    }



    /*
    Error messages
     */


    /**
     * When the user string sentence is misspelled
     * @return a string with an error.
     */
    public final String getErrorMessage_sentenceTypo(){
        return "Error:\n" +
                "*The sentence is misspelled.\n" +
                "*La oración está mal escrita.";
    }

    /**
     * when the user uses the command correctly but forgot the attachment ".txt"
     * return a string with an error.
     */
    public String getErrorMessage_AttachmentNotFound(){
        return "Error:\n" +
                "*In order to use this command rightly, you should send a file**.txt**.\n" +
                "*Para usar este comando correctamente, usted debería mandar un archivo**.txt**.\n";
    }


    /**
     * when the user sends an attachment with null content
     * @return a string with an error.
     */
    public String getErrorMessage_AttachmentNullPoint(){
        return "Error:\n" +
                "*The content of the text file is null.\n" +
                "*El contenido de el archivo de texto es nulo.";
    }

    /**
     * when reading the file it found an unexpected error... maybe trying to find that file.
     * return a string with an error.
     */
    public String getErrorMessage_UnexpectedError(){
        return "Error:\n" +
                "*Unexpected error.\n" +
                "*Error inesperado.";
    }



}
