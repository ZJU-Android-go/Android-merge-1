package cc.vipazoo.www.ui.model;

import java.util.ArrayList;

public class Triplets {
    private String doc_id;
    private int send_id;
    private String title;
    private String sent_ctx;
    private ArrayList<Triplet> triplets;

    public void setDoc_id(String doc_id) {
        this.doc_id = doc_id;
    }

    public void setSend_id(int send_id) {
        this.send_id = send_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSent_ctx(String sent_ctx) {
        this.sent_ctx = sent_ctx;
    }

    public void setTriplets(ArrayList<Triplet> triplets) {
        this.triplets = triplets;
    }

    public String getDoc_id() {
        return doc_id;
    }

    public int getSend_id() {
        return send_id;
    }

    public String getTitle() {
        return title;
    }

    public String getSent_ctx() {
        return sent_ctx;
    }

    public ArrayList<Triplet> getTriplets() {
        return triplets;
    }
}
