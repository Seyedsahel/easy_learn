package com.example.myapplication;

public class lessonClass {

    private int id;
    private String title;
    private String dec1;
    private String dec2;

    private String dec3;
    private int pic;
    private int vid;
    private int voice;
    private boolean status=false;


    public lessonClass(String title , boolean status ,String dec1){
        this.title = title;
        this.status = status;
        this.dec1 = dec1;
    }


    public  lessonClass(int id ,String title , boolean status , String dec1 , String dec2 , String dec3 ,int pic , int voice , int vid){
        this.title = title;
        this.status = status;
        this.pic = pic;
        this.vid = vid;
        this.voice = voice;
        this.dec1 = dec1;
        this.dec2 = dec2;
        this.id = id;
    }
    public  lessonClass(int id ,String title , boolean status , String dec1 , String dec2 ,String dec3, int pic , int vid){
        this.title = title;
        this.id=id;
        this.status = status;
        this.pic = pic;
        this.vid = vid;
        this.dec1 = dec1;
        this.dec2 = dec2;
        this.dec3 = dec3;
    }
    public boolean getStatus(){
        return status;
    }
    public void setStatus(boolean status){
        this.status = status;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPic() {
        return pic;
    }
    public void setPic(int pic) {
        this.pic = pic;
    }
    public int getVid() {
        return vid;
    }
    public void setVid(int vid) {
        this.vid = vid;
    }
    public int getVoice() {
        return voice;
    }
    public void setVoice(int voice) {
        this.voice = voice;
    }
    public String getDes1() {
        return dec1;
    }
    public void setDes1(String dec1) {
        this.dec1 = dec1;
    }
    public String getDes2() {
        return dec2;
    }
    public void setDes2(String dec2) {
        this.dec2 = dec2;
    }
}


