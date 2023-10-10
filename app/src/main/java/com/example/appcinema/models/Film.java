package com.example.appcinema.models;

import java.util.List;

public class Film {
    private String _id;
    private String name;
    private int status;
    private String thumbnail;
    private String description;
    private List<String> category;
    private List<String> director;
    private List<String> actor;
    private Long createDate;
    private Long updateDate;

    public Film() {
    }

    public Film(String _id, String name, int status, String thumbnail, String description, List<String> category, List<String> director, List<String> actor, Long createDate, Long updateDate) {
        this._id = _id;
        this.name = name;
        this.status = status;
        this.thumbnail = thumbnail;
        this.description = description;
        this.category = category;
        this.director = director;
        this.actor = actor;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> categoryItem) {
        this.category = category;
    }

    public List<String> getDirector() {
        return director;
    }

    public void setDirector(List<String> director) {
        this.director = director;
    }

    public List<String> getActor() {
        return actor;
    }

    public void setActor(List<String> actor) {
        this.actor = actor;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }
}
