package discord_bot;

import java.util.ArrayList;
import java.util.List;

public enum Aliases {

    FORVO_ALIASES(".,fetchPronunciation", ".,fetchPronunciacion",
            ".,fetchPro", ".,fetchPr", ".,fetchP", ".,fp",
            "pronunciation", ".,pronunciacion",".,pronounce", ".,pronuncia",
            ".,pron", ".,pro", ".,pr", ".,p"),

    TEXTFETCHING_ALIASES(".,ftxt", ".,ftext", ".,fetchtxt", ".,fetchtext"),

    STORYTIME_ALIASES(".,reading", ".,read", ".,r",
            ".,storytime", ".,storyT",
            ".,story", ".,st");


    String[] aliases;

    Aliases(String... alias) {
        this.aliases = alias;
    }

    Aliases(){

    }



}
