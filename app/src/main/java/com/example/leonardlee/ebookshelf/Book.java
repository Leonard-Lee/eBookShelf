package com.example.leonardlee.ebookshelf;

import java.util.Date;

/**
 * Created by leonardlee on 08/05/2017.
 */

public class Book {
    // auto generate id
    private int id;

    private String bookname;
    private String path;
    private int size;
    private float longitude;
    private float latitude;
    private Date saveTime;

    public Book() {

    }

    public Book(String bookname, String path, int size, float longitude, float latitude, Date saveTime) {
        this.bookname = bookname;
        this.path = path;
        this.size = size;
        this.longitude = longitude;
        this.latitude = latitude;
        this.saveTime = saveTime;
    }

    // getter & setter for name
    public void setBookname(String name) {
        this.bookname = name;
    }

    public String getBookname() {
        return this.bookname;
    }

    // getter & setter for path
    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

    // getter & setter for size
    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }

    // getter & setter for longitude
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLongitude() {
        return this.longitude;
    }

    // getter & setter for latitude
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLatitude() {
        return this.latitude;
    }

    // getter & setter for save date
    public void setSaveTime(Date saveTime) {
        this.saveTime = saveTime;
    }

    public Date getSaveTime() {
        return this.saveTime;
    }
}
