package org.example.models;

public class Job {
    private long id;
    private String position;
    private  String profession;

    private int experience;
    private  String description;

    public Job(String position, String profession, int experience, String description) {
        this.position = position;
        this.profession = profession;
        this.experience = experience;
        this.description = description;
    }
public Job(){}

    public Job(long aLong, String string, String string1, int anInt, String string2) {
    }

    public Job(long aLong, String string, String string1, int anInt) {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", position='" + position + '\'' +
                ", profession='" + profession + '\'' +
                ", experience=" + experience +
                ", description='" + description + '\'' +
                '}';
    }


}