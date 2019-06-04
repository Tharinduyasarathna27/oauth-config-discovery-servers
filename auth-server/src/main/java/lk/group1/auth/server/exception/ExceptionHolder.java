package lk.group1.auth.server.exception;

import java.util.Date;

public class ExceptionHolder {

    private Date date;
    private String message;
    private String details;


    public ExceptionHolder(Date date, String message, String details) {
        this.date = date;
        this.message = message;
        this.details = details;
    }

    public Date getDate() {
        return date;
    }



    public String getMessage() {
        return message;
    }



    public String getDetails() {
        return details;
    }









}
