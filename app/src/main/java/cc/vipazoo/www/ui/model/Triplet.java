package cc.vipazoo.www.ui.model;

import java.util.Random;

public class Triplet {
    private String id;
    private int left_e_start;
    private int left_e_end;
    private int right_e_start;
    private int right_e_end;
    private int relation_start;
    private int relation_end;
    private String left_entity;
    private String right_entity;
    private int relation_id;
    private String status;
    public Triplet()
    {
        id = null;
        left_e_end = 0;
        left_e_start = 0;
        right_e_end = 0;
        right_e_start = 0;
        relation_start = 0;
        relation_end = 0;
        relation_id = 0;
        left_entity = null;
        right_entity = null;
        status = null;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRandomId()
    {
        String[] code = new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
                "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
                "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        String temp_id = new String();
        for(int i = 0;i < 20;i++)
        {
            Random random = new Random();
            temp_id += code[random.nextInt(62)];
        }
        this.id = temp_id;
        return;
    }

    public void setLeft_e_start(int left_e_start) {
        this.left_e_start = left_e_start;
    }

    public void setLeft_e_end(int left_e_end) {
        this.left_e_end = left_e_end;
    }

    public void setRight_e_start(int right_e_start) {
        this.right_e_start = right_e_start;
    }

    public void setRight_e_end(int right_e_end) {
        this.right_e_end = right_e_end;
    }

    public void setRelation_start(int relation_start) {
        this.relation_start = relation_start;
    }

    public void setRelation_end(int relation_end) {
        this.relation_end = relation_end;
    }

    public void setLeft_entity(String left_entity) {
        this.left_entity = left_entity;
    }

    public void setRight_entity(String right_entity) {
        this.right_entity = right_entity;
    }

    public void setRelation_id(int relation_id) {
        this.relation_id = relation_id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public int getLeft_e_start() {
        return left_e_start;
    }

    public int getLeft_e_end() {
        return left_e_end;
    }

    public int getRight_e_start() {
        return right_e_start;
    }

    public int getRight_e_end() {
        return right_e_end;
    }

    public int getRelation_start() {
        return relation_start;
    }

    public int getRelation_end() {
        return relation_end;
    }

    public String getLeft_entity() {
        return left_entity;
    }

    public String getRight_entity() {
        return right_entity;
    }

    public int getRelation_id() {
        return relation_id;
    }

    public String getStatus() {
        return status;
    }
}
