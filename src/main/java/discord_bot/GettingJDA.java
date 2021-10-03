package discord_bot;

import net.dv8tion.jda.api.JDA;

public class GettingJDA {

    private static JDA jda;

    private static GettingJDA gettingJDA;

    //constructor
    private GettingJDA() {

    }

    public static GettingJDA getInstance(){
        if (gettingJDA == null) {
            gettingJDA = new GettingJDA();
        }
        return gettingJDA;
    }



    public void setJDA(JDA jda){
        this.jda = jda;
    }

    public JDA getJDA() {
        return jda;
    }
}
