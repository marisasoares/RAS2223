package com.rasbet.model;

import java.time.LocalDateTime;

public class Notification {

    /**
     * Identificador da notificação
     * */
    private int id;

    /**
     * Email do utilizador a quem se destina a notificação
     * */
    private String email;
    /**
     * Conteúdo da notificação
     * */
    private String content;
    /**
     * Flag que diz se a notificação já foi lida
     * */
    private boolean isRead;

    /**
     * Data da notificação
     * */
    private LocalDateTime date;


    public Notification(int id,String email, String content, boolean isRead,LocalDateTime date) {
        this.id = id;
        this.email = email;
        this.content = content;
        this.isRead = isRead;
        this.date = date;
    }

    public Notification(String email, String content,LocalDateTime date) {
        this.id = Utils.geraIdentificadorUnicoInteger(Utils.notifications);
        Utils.notifications.put(id,id);
        this.email = email;
        this.content = content;
        this.isRead = false;
        this.date = date;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean getIsRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getFormattedDate(){
        return date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear() +  " às " + date.getHour()+ ":" + String.format("%02d",date.getMinute());
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                ", isRead=" + isRead +
                ", date=" + date +
                '}';
    }
}
