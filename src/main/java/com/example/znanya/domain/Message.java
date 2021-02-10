package com.example.znanya.domain;

import javax.persistence.*;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String text;
    private String tag;

    private String author;

    private String filename;


    public Message() {
    }

    public Message(String text, String tag,String user, String filename) {
        this.author = user;
        this.text = text;
        this.tag = tag;
        this.filename = filename;
    }

    public String getFilename() {
        if(filename != null) {
            return filename;
        }
        return "ava.png";
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
    public String getAuthor() {
        return author;
    }

    public String getAuthorName(){
        if(author != null){
            return author;
        }
        return "<none>";
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }


}
