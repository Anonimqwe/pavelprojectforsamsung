package com.example.myapplication.model;

public class Results {
    private int id;
    private String name;
    private String time;

    public Results(int id, String name, String time) {
        this.id = id;
        this.name = name;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Results{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public Results(String name, String time) {
        this.name = name;
        this.time = time;
    }

    public Results(String time) {
        this.time = time;
        name = "NoName";
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

}
