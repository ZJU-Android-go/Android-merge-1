package cc.vipazoo.www.ui.model;

public class Article {
    private String title;
    private String content;
    private int sent_id;
    private String doc_id;

    public Article()
    {
        title = null;
        content = null;
        sent_id = 0;
        doc_id = null;
    }
    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getSend_id() {
        return sent_id;
    }

    public String getDoc_id() {
        return doc_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSend_id(int send_id) {
        this.sent_id = send_id;
    }

    public void setDoc_id(String doc_id) {
        this.doc_id = doc_id;
    }
}
