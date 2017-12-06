package net.jo.chat;

/**
 * Created by Johnny on 2017/12/3.
 */

public class JoMsg {
    public static final int MSG_TEXT = 1;
    public static final int MSG_IMAGE = 2;

    private String text;
    private String name;
    private String photoUrl;

    public JoMsg() {} // Needed for Firebase

    public JoMsg(String text, String name, String photoUrl) {
        this.text = text;
        this.name = name;
        this.photoUrl = photoUrl;
    }

    public int getType() {
        return (photoUrl == null) ? MSG_TEXT : MSG_IMAGE;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
