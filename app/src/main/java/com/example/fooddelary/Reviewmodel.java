package com.example.fooddelary;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class Reviewmodel {
    String reviewtime;
    String reviewDay;
    public String getReviewDay() {
        return reviewDay;
    }
    public void setReviewDay(String reviewDay) {
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
        String Date= sdf.format(new Date());
        this.reviewDay = reviewDay;
    }
    public String getReviewtime() {
        return reviewtime;
    }

    public void setReviewtime(String reviewtime) {
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
        String Date= sdf.format(new Date());

        this.reviewtime = Date;
    }
    String review;
  public  Reviewmodel(){
    }
    public String getReview() {
        return review;
    }
    public void setReview(String review) {
        this.review = review;
    }
}
