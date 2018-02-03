package com.example.sharonhains.subbook;

import java.util.Date;

/**
 * Created by Sharon Hains on 2018-01-23.
 */

public class Subscription {



    private String name;
    private Date date;
    private String comment;
    private int charge;

    public Subscription (String name){//, Date date, String comment, int charge){
        this.name = name;
        //this.date = date;
        //this.comment = comment;
        //this.charge = charge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws NameTooLongException {

        if (name.length() > 20){
            throw new NameTooLongException();
        }
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) throws CommentTooLongException {

        if (comment.length() > 30){
            throw new CommentTooLongException();
        }
        this.comment = comment;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) throws NegativeValueException {

        if (charge < 0){
            throw new NegativeValueException();
        }
        this.charge = charge;
    }

    @Override
    public String toString(){
        return name;
    }

}
