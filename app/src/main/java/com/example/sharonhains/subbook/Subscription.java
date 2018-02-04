package com.example.sharonhains.subbook;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Sharon Hains on 2018-01-23.
 */

public class Subscription {

    private String name;
    private Date date;
    private String comment;
    private String charge;
    private Format format;

    public Subscription (String name, String charge){//, Date date, String comment, int charge){
        this.name = name;
        //this.date = date;
        //this.comment = comment;
        this.charge = charge;
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

    public void setDate(Date date) throws IncorrectDateException {

        format = new SimpleDateFormat("yyyy-MM-dd");
        String stringdate = format.format(date);

        if (isValidDate(stringdate) == true) {
            this.date = date;
        }
        else {
            throw new IncorrectDateException();
        }
    }

    //Source http://www.java2s.com/Tutorial/Java/0120__Development/CheckifaStringisavaliddate.htm
    public static boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
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

    /*public int getCharge() {
        return charge;
    }

    /*public void setCharge(int charge) throws NegativeValueException {

        if (charge < 0){
            throw new NegativeValueException();
        }
        this.charge = charge;
    }*/



    @Override
    public String toString(){
        return charge;
    }

}
