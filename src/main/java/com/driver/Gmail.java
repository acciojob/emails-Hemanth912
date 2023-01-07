package com.driver;

import org.apache.commons.lang3.tuple.Triple;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
//    private ArrayList<ArrayList<ArrayList<Date, String, String>>> Inbox;
//    private ArrayList<Triple<Date, String, String>> Trash;
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)

    private ArrayList<Triple<Date, String,String>> Inbox;
    private ArrayList<Triple<Date, String, String>> Trash;
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
        this.Inbox = new ArrayList<>();
        this.Trash = new ArrayList<>();
    }

    public void receiveMail(Date date, String sender, String message){
        if(this.Inbox.size() == this.inboxCapacity){
//            mail = this.Inbox.get(0);
            Triple<Date,String,String> oldest = Inbox.get(0);
            this.Inbox.remove(0);
            this.Trash.add(oldest);
        }
        Triple<Date,String,String> mail = Triple.of(date, sender, message);
        this.Inbox.add(mail);
    }

    public void deleteMail(String message){
        int idx = -1;

        for(int i = 0; i < this.Inbox.size(); i++){
            if(message.equals(this.Inbox.get(i).getRight())){
                idx = i;
                break;
            }
        }
        if(idx != -1){
            this.Trash.add(this.Inbox.get(idx));
            this.Inbox.remove(idx);
        }
    }

    public String findLatestMessage(){

        if(this.Inbox.isEmpty()){
            return null;
        }
        else {
            return this.Inbox.get(this.Inbox.size() - 1).getRight();
        }
    }

    public String findOldestMessage(){

        if(this.Inbox.isEmpty()){
            return null;
        }
        else {
            return this.Inbox.get(0).getRight();
        }
    }

    public int findMailsBetweenDates(Date start, Date end){
        int count = 0;
        for(int i = 0; i < this.Inbox.size(); i++){
            if(this.Inbox.get(i).getLeft().compareTo(start) >=0 && this.Inbox.get(i).getLeft().compareTo(end) <= 0){
                count++;
            }
        }
        return count;
    }

    public int getInboxSize(){
        return this.Inbox.size();
    }

    public int getTrashSize(){
        return this.Trash.size();
    }

    public void emptyTrash(){
        this.Trash.clear();
    }

    public int getInboxCapacity() {
        return this.inboxCapacity;
    }
}