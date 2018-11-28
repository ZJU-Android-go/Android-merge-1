package cc.vipazoo.www.ui.model;

import java.util.ArrayList;

public class Entities {
    private String doc_id;
    private int sent_id;
    private ArrayList<Entity> entities;

    public Entities() {
        doc_id = null;
        sent_id = 0;
        entities = new ArrayList<>();
    }

    public void setDoc_id(String doc_id) {
        this.doc_id = doc_id;
    }

    public void setSend_id(int send_id) {
        this.sent_id = send_id;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }

    public String getDoc_id() {
        return doc_id;
    }

    public int getSend_id() {
        return sent_id;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }
}
