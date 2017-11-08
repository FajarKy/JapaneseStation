package com.hafiizh.japanesestation.data.local.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HAFIIZH on 10/23/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

@Entity(tableName = "popular")
public class PopularEntity {
    @PrimaryKey(autoGenerate = true)
    private int id_popular;
    @SerializedName("id")
    private int id;
    @SerializedName("image")
    private String image;
    @SerializedName("tag")
    private String tag;
    @SerializedName("tag_link")
    private String tag_link;
    @SerializedName("title")
    private String title;
    @SerializedName("title_link")
    private String title_link;
    @SerializedName("sub_title")
    private String sub_title;
    @SerializedName("author")
    private String author;
    @SerializedName("author_link")
    private String author_link;
    @SerializedName("image_author")
    private String image_author;
    @SerializedName("time_publish")
    private String time_publish;
    @SerializedName("day_publish")
    private String day_publish;
    @SerializedName("time_update")
    private String time_update;
    @SerializedName("day_update")
    private String day_update;

    public int getId_popular() {
        return id_popular;
    }

    public void setId_popular(int id_popular) {
        this.id_popular = id_popular;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag_link() {
        return tag_link;
    }

    public void setTag_link(String tag_link) {
        this.tag_link = tag_link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle_link() {
        return title_link;
    }

    public void setTitle_link(String title_link) {
        this.title_link = title_link;
    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor_link() {
        return author_link;
    }

    public void setAuthor_link(String author_link) {
        this.author_link = author_link;
    }

    public String getImage_author() {
        return image_author;
    }

    public void setImage_author(String image_author) {
        this.image_author = image_author;
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
}
