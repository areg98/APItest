package com.swapi.pojo;

public class UserUpdateResponse {

    private String name;
    private String job;
    private String updatedAt;

    public UserUpdateResponse(String updatedAt) {
        this.updatedAt = updatedAt;
    }
    public UserUpdateResponse() {
    }


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

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
