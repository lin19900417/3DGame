package com.google.administrator.dao;

/**
 * Created by Administrator on 2016/7/6.
 */
public class News {

    /**
     * id : 3575516
     * typeid : 199
     * typeid2 : 0
     * sortrank : 1467808843
     * flag :
     * ismake : 1
     * channel : 1
     * arcrank : 0
     * click : 0
     * money : 0
     * title : 苹果狠心！MacBook Air被打冷宫：要狂降价换销量
     * shorttitle : MacBook Air被打冷宫
     * color :
     * writer : pannds
     * source : 互联网
     * litpic : /uploads/allimg/160706/305-160F62042270-L-lp.jpg
     * pubdate : 1467808843
     * senddate : 1467808973
     * mid : 305
     * keywords : MacBook Air
     * lastpost : 0
     * scores : 0
     * goodpost : 0
     * badpost : 0
     * voteid : 0
     * notpost : 0
     * description : 苹果会放弃MacBook Air吗？显然不会，但不更新不做改动，这个系列跟死亡有什么区别，但苹果不这么认为。要知道，苹果对自家老款产品剩余价值的榨取在业界绝对无人能敌，直到上个月，普通版13英寸MacBook Pro才正式下架，而这款产品2012年后就没再更新过。
     * filename :
     * dutyadmin : 305
     * tackid : 0
     * mtype : 0
     * weight : 265472
     * fby_id : 0
     * game_id : 0
     * feedback : 0
     * typedir : {cmspath}/a/events
     * typename : 时事焦点
     * corank : 0
     * isdefault : -1
     * defaultname : index.html
     * namerule : {typedir}/{Y}{M}/{aid}.html
     * namerule2 : {typedir}/list_{tid}_{page}.html
     * ispart : 0
     * moresite : 0
     * siteurl :
     * sitepath : {cmspath}/a/info
     * arcurl : http://www.3dmgame.com/events/201607/3575516.html
     * typeurl : http://www.3dmgame.com/events/
     * videolist : {"0":{"body":null}}
     */

    private String id;
    private String typeid;
    private String sortrank;
    private String ismake;
    private String channel;
    private String title;
    private String shorttitle;
    private String writer;
    private String source;
    private String litpic;
    private String pubdate;
    private String senddate;
    private String mid;
    private String keywords;
    private String description;
    private String dutyadmin;
    private String weight;
    private String typedir;
    private String typename;
    private String defaultname;
    private String namerule;
    private String namerule2;
    private String sitepath;
    private String arcurl;
    private String typeurl;

    public String getId() {
        return id;
    }

    public News(){

    }
    public News(String id, String typeid, String sortrank, String ismake, String channel, String title, String shorttitle, String writer, String source, String litpic, String pubdate, String senddate, String mid, String keywords, String description, String dutyadmin, String weight, String typedir, String typename, String defaultname, String namerule, String namerule2, String sitepath, String arcurl, String typeurl) {
        this.id = id;
        this.typeid = typeid;
        this.sortrank = sortrank;
        this.ismake = ismake;
        this.channel = channel;
        this.title = title;
        this.shorttitle = shorttitle;
        this.writer = writer;
        this.source = source;
        this.litpic = litpic;
        this.pubdate = pubdate;
        this.senddate = senddate;
        this.mid = mid;
        this.keywords = keywords;
        this.description = description;
        this.dutyadmin = dutyadmin;
        this.weight = weight;
        this.typedir = typedir;
        this.typename = typename;
        this.defaultname = defaultname;
        this.namerule = namerule;
        this.namerule2 = namerule2;
        this.sitepath = sitepath;
        this.arcurl = arcurl;
        this.typeurl = typeurl;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    public String getSortrank() {
        return sortrank;
    }

    public void setSortrank(String sortrank) {
        this.sortrank = sortrank;
    }

    public String getIsmake() {
        return ismake;
    }

    public void setIsmake(String ismake) {
        this.ismake = ismake;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShorttitle() {
        return shorttitle;
    }

    public void setShorttitle(String shorttitle) {
        this.shorttitle = shorttitle;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLitpic() {
        return litpic;
    }

    public void setLitpic(String litpic) {
        this.litpic = litpic;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getSenddate() {
        return senddate;
    }

    public void setSenddate(String senddate) {
        this.senddate = senddate;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDutyadmin() {
        return dutyadmin;
    }

    public void setDutyadmin(String dutyadmin) {
        this.dutyadmin = dutyadmin;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getTypedir() {
        return typedir;
    }

    public void setTypedir(String typedir) {
        this.typedir = typedir;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getDefaultname() {
        return defaultname;
    }

    public void setDefaultname(String defaultname) {
        this.defaultname = defaultname;
    }

    public String getNamerule() {
        return namerule;
    }

    public void setNamerule(String namerule) {
        this.namerule = namerule;
    }

    public String getNamerule2() {
        return namerule2;
    }

    public void setNamerule2(String namerule2) {
        this.namerule2 = namerule2;
    }

    public String getSitepath() {
        return sitepath;
    }

    public void setSitepath(String sitepath) {
        this.sitepath = sitepath;
    }

    public String getArcurl() {
        return arcurl;
    }

    public void setArcurl(String arcurl) {
        this.arcurl = arcurl;
    }

    public String getTypeurl() {
        return typeurl;
    }

    public void setTypeurl(String typeurl) {
        this.typeurl = typeurl;
    }
}
