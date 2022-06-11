package com.project.board;

// BoardData -> Board
public class BoardData {
    private String title;
    private String content;
    private String name;
    // 변수명 변경 -> created
    private String registrationTime;
    // 변수명 변경 -> updated
    private String modificationTime;
    // private boolean deleted

    public BoardData() {}

    public BoardData(String title, String content, String name, String registrationTime, String modificationTime) {
        this.title = title;
        this.content = content;
        this.name = name;
        this.registrationTime = registrationTime;
        this.modificationTime = modificationTime;
        // deleted 매개변수 및 바디에 추가
    }

    // getter, setter 형식 맞추기
    // 분류 작업하기
    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public void setModificationTime(String modificationTime) { this.modificationTime = modificationTime; }
}
