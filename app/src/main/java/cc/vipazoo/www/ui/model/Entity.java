package cc.vipazoo.www.ui.model;

import com.google.gson.annotations.SerializedName;

public class Entity {
    @SerializedName("EntityName")
    private String name;
    @SerializedName("Start")
    private int start;
    @SerializedName("End")
    private int end;
    @SerializedName("Nertag")
    private String tag;

    public void setName(String name) {
        this.name = name;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public String getTag() {
        return tag;
    }
}
