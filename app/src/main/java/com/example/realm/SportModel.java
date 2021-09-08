package com.example.realm;

import io.realm.RealmObject;

public class SportModel extends RealmObject {

    private String sportName;
    private String formatSport;
    private String sportDescription;
    private String sportPicture;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSportPicture() {
        return sportPicture;
    }

    public void setSportPicture(String sportPicture) {
        this.sportPicture = sportPicture;
    }


    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public String getFormatSport() {
        return formatSport;
    }

    public void setFormatSport(String formatSport) {
        this.formatSport = formatSport;
    }

    public String getSportDescription() {
        return sportDescription;
    }

    public void setSportDescription(String sportDescription) {
        this.sportDescription = sportDescription;
    }
}
