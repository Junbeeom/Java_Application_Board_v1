package com.project.board;

public class Board {

    private String title;
    private String content;
    private String name;
    private String created;
    private String updated;
    private boolean deleted;
    // private boolean deleted

    public Board() {}

    public Board(String title, String content, String name, String created, String updated, boolean deleted) {
        this.title = title;
        this.content = content;
        this.name = name;
        this.created = created;
        this.updated = updated;
        this.deleted = deleted;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getName() {
        return name;
    }

    public String getCreated() {
        return created;
    }

    public String getUpdated() {
        return updated;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

}
