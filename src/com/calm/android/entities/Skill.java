package com.calm.android.entities;

import java.util.ArrayList;
import com.google.gson.Gson;


public class Skill {

    public String subject;
    public ArrayList<Integer> levels;

    public Skill (String subject, ArrayList<Integer> levels){
        this.subject = subject;
        this.levels = new ArrayList<Integer>();
        if(levels==null){
            this.levels = new ArrayList<Integer>();
        }
        for(int i = 0; i<levels.size();i++){
            this.levels.add(levels.get(i));
        }
    }

    public String getSubject(){		return subject;}
    public ArrayList<Integer> getLevels(){		return levels;}

    public void setSubject (String subject){		this.subject = subject;}
    public void setLevels (ArrayList<Integer> newLevels){
        if(newLevels==null){
            newLevels = new ArrayList<Integer>();
        }
    //levels.clear();
        for(int i = 0; i<newLevels.size();i++){
            this.levels.add(newLevels.get(i));
        }    }

    public String toString(){
        return new Gson().toJson(this);
    }
}
