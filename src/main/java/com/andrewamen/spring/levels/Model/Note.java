package com.andrewamen.spring.levels.Model;

/**
 *
 * @author andrewamen
 */
public class Note {

    private final long id;
    private String body;
    
    public Note(){
        this.id = -1;
        this.body = "THIS NOTE DOES NOT EXIST";
    }

    public Note(long id, String body) {
        this.id = id;
        this.body = body;
    }
    

    public long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}