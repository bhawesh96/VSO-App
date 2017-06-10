package com.example.bhawesh96.vso_androidapp;

/**
 * Created by bhawesh96 on 7/6/17.
 */

public class Project {
    public String id;
    public String title;
    public String desc;;
    public String img;
    public String type;

    public Project() { }

    public Project(String id, String type, String title, String desc) {
        this.id = id;
        this.title = title;
        this.desc = desc;
//        this.img = img;
        this.type = type;
    }

    public Project(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getId() {
        return id;
    }
}
