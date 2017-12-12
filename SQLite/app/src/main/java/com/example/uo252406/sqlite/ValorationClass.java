package com.example.uo252406.sqlite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.RatingBar;

/**
 * Created by UO252406 on 31/10/2017.
 */

public class ValorationClass {

    private String comment;
    private String course;
    private int rating;

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {

        return comment;
    }

    public String getCourse() {
        return course;
    }

    public int getRating() {
        return rating;
    }


    public String toString(){
        return "Subject: "+getCourse()+" Rating: "+getRating() +" Comment: "+getComment();
    }

}
