package cc.vipazoo.www.ui.model;

import java.util.ArrayList;

public class Triplets {
    private String doc_id;
    private int sent_id;
    private String title;
    private String sent_ctx;
    private ArrayList<Triplet> triples;

    public Triplets() {
        doc_id = null;
        sent_id = 0;
        title = null;
        sent_ctx = null;
        triples = new ArrayList<>();
    }

    public void setDoc_id(String doc_id) {
        this.doc_id = doc_id;
    }

    public void setSent_id(int send_id) {
        this.sent_id = sent_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSent_ctx(String sent_ctx) {
        this.sent_ctx = sent_ctx;
    }

    public void setTriplets(ArrayList<Triplet> triplets) {
        this.triples = triplets;
    }

    public String getDoc_id() {
        return doc_id;
    }

    public int getSent_id() {
        return sent_id;
    }

    public String getTitle() {
        return title;
    }

    public String getSent_ctx() {
        return sent_ctx;
    }

    public ArrayList<Triplet> getTriplets() {
        return triples;
    }
}
