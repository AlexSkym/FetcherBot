package forvo_API;

public class Item_FromXML_ToXML {

    private String id = "";         //<id>1314393</id>
    private String word = "";       //<word>hola</word>
    private String original = "";   //<original>hola</original>
    private String addtime = "";    //<addtime>2011-11-01 12:12:40</addtime>
    private String hits = "";       //<hits>2514</hits>
    private String username = "";   //<username>joelnakuro</username>
    private String sex = "";        //<sex>m</sex>
    private String country = "";    //<country>Spain</country>
    private String code = "";       //<code>ca</code>
    private String langname = "";   //<langname>Catalan</langname>
    private String pathmp3 = "";    //<pathmp3>https://apifree.forvo.com/audio/3d2d1o3l1n3d352m3g292p23272i3p3o21231g3e1k2k2a253j322o2m361j3626363p251p32242d3p1j3i3m2i3i3o343g2a3i2d1n2a2h3q2q2q2q2f293c3e1p2n3h1h2935273h3k1i332j253n36321j1o35382d2q2p2h1t1t_24312j3j2q313a231m1i1p34233f2i2a2n262b1f3m211t1t</pathmp3>
    private String pathogg = "";    //<pathogg>https://apifree.forvo.com/audio/2o2e3j2q333537361b3d2c2g312d2f273f3o3n321m1k2e1l1b3i2l3o2g2l2n3l293k1g3k3d28343a2h2d2m2a2d3q1j3d3j2h393q1g2h3g1b1j2o3f2h363127311i3c3l3d2q223j2i231o2g232j2b1b37362937262o3n1t1t_3b3o25221h3j372e1h361g2j2j3c2m2g1j2824223d371t1t</pathogg>
    private String rate = "";       //<rate>-1</rate>
    private String num_votes = "";  //<num_votes>1</num_votes>
    private String num_positive_votes = ""; //<num_positive_votes>0</num_positive_votes>


    Item_FromXML_ToXML() {

    }

    @Override
    public String toString() {
        return "Item_FromXML_ToXML{" +
                "id='" + id + '\'' +
                ", word='" + word + '\'' +
                ", original='" + original + '\'' +
                ", addtime='" + addtime + '\'' +
                ", hits='" + hits + '\'' +
                ", username='" + username + '\'' +
                ", sex='" + sex + '\'' +
                ", country='" + country + '\'' +
                ", code='" + code + '\'' +
                ", langname='" + langname + '\'' +
                ", pathmp3='" + pathmp3 + '\'' +
                ", pathogg='" + pathogg + '\'' +
                ", rate='" + rate + '\'' +
                ", num_votes='" + num_votes + '\'' +
                ", num_positive_votes='" + num_positive_votes + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getHits() {
        return hits;
    }

    public void setHits(String hits) {
        this.hits = hits;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLangname() {
        return langname;
    }

    public void setLangname(String langname) {
        this.langname = langname;
    }

    public String getPathmp3() {
        return pathmp3;
    }

    public void setPathmp3(String pathmp3) {
        this.pathmp3 = pathmp3;
    }

    public String getPathogg() {
        return pathogg;
    }

    public void setPathogg(String pathogg) {
        this.pathogg = pathogg;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getNum_votes() {
        return num_votes;
    }

    public void setNum_votes(String num_votes) {
        this.num_votes = num_votes;
    }

    public String getNum_positive_votes() {
        return num_positive_votes;
    }

    public void setNum_positive_votes(String num_positive_votes) {
        this.num_positive_votes = num_positive_votes;
    }
}