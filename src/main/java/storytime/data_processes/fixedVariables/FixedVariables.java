package storytime.data_processes.fixedVariables;

import java.util.ArrayList;
import java.util.List;

public class FixedVariables {

    /**
     * Aliases allowed to make this functionality of the bot work
     */
    enum AliasesAllowed{
        ALIASES(".,reading", ".,read", ".,r",
                ".,storytime", ".,storyT",
                ".,story", ".,st");

        String[] complexes; //it is necessary to use "aliasesAllowed.complexes"

        AliasesAllowed(String... complexes) {
            this.complexes = complexes;
        }
    }



    /*
    Public methods
     */



    /**
     * get complexes of the aliases
     * @return
     */
    List<String> getAliasComplexes(){
        List<String> list = new ArrayList<>();

        AliasesAllowed aliasesAllowed = AliasesAllowed.ALIASES;
        for (String complex:
             aliasesAllowed.complexes) {
            list.add(complex);
        }

        return list;
    }


}
