package com.project.board;

public class BoardData {
    private String title;
    private String content;
    private String name;
    private String registrationTime;
    private String modificationTime;

    public BoardData() {

    }

    public BoardData(String title, String content, String name, String registrationTime, String modificationTime) {
        this.title = title;
        this.content = content;
        this.name = name;
        this.registrationTime = registrationTime;
        this.modificationTime = modificationTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(String registrationTime) {
        this.registrationTime = registrationTime;
    }

    public String getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(String modificationTime) {
        this.modificationTime = modificationTime;
    }
}
