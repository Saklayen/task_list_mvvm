package com.example.tasklistmvvm.Model;

public class Scorer {

    String name;
    String score;
    String position;

    public Scorer(String name, String score, String position) {
        this.name = name;
        this.score = score;
        this.position = position;
    }

    public Scorer() {
    }

    public String getName() {
        return name;
    }

    public String getScore() {
        return score;
    }

    public String getPosition() {
        return position;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
