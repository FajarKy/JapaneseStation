package com.hafiizh.japanesestation.data.local.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HAFIIZH on 10/26/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

@Entity(tableName = "detail")
public class DetailEntity {
    @PrimaryKey(autoGenerate = true)
    private int id_detail;
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("author")
    private String author;
    @SerializedName("author_image")
    private String author_image;
    @SerializedName("author_link")
    private String author_link;
    @SerializedName("time_publish")
    private String time_publish;
    @SerializedName("day_publish")
    private String day_publish;
    @SerializedName("time_update")
    private String time_update;
    @SerializedName("day_update")
    private String day_update;
    @SerializedName("viewer")
    private String viewer;
    @SerializedName("thumbnail")
    private String thumbnail;
    @SerializedName("body")
    private String body;

    public int getId_detail() {
        return id_detail;
    }

    public void setId_detail(int id_detail) {
        this.id_detail = id_detail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor_image() {
        return author_image;
    }

    public void setAuthor_image(String author_image) {
        this.author_image = author_image;
    }

    public String getAuthor_link() {
        return author_link;
    }

    public void setAuthor_link(String author_link) {
        this.author_link = author_link;
    }

    public String getTime_publish() {
        return time_publish;
    }

    public void setTime_publish(String time_publish) {
        this.time_publish = time_publish;
    }

    public String getDay_publish() {
        return day_publish;
    }

    public void setDay_publish(String day_publish) {
        this.day_publish = day_publish;
    }

    public String getTime_update() {
        return time_update;
    }

    public void setTime_update(String time_update) {
        this.time_update = time_update;
    }

    public String getDay_update() {
        return day_update;
    }

    public void setDay_update(String day_update) {
        this.day_update = day_update;
    }

    public String getViewer() {
        return viewer;
    }

    public void setViewer(String viewer) {
        this.viewer = viewer;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}