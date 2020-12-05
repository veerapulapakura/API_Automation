package com.qa.data;

//pojo - plain old java object
public class Users {
    String name;
    String job;
    String id;
    String createdAt;


    public Users(){

    }

    public Users(String name, String Job){
        this.name= name;
        this.job= Job;

    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }



    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }



}
