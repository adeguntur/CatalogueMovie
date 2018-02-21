package com.example.ade.cataloguemovie;

import org.json.JSONObject;

/**
 * Created by Ade on 18/02/2018.
 */

public class FilmItems {

    private int id;
    private String title;
    private String overview;
    private String release_date;
    private String poster;


    public FilmItems(JSONObject object) {
        try {
            int id = object.getInt("id");
            String poster = object.getString("poster_path");
            String title = object.getString("title");
            String overview = object.getString("overview");
            String release_date = object.getString("release_date");

            this.id = id;
            this.poster = poster;
            this.title = title;
            this.overview = overview;
            this.release_date = release_date;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        String[] date = release_date.split("-");
        String year = date[0];
        String month = parseDate(date[1]);
        String day = date[2];

        return month + " " + day + " , " + year;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    private String parseDate(String Date) {
        switch (Date) {
            case "1":
                return "January";
            case "2":
                return "February";
            case "3":
                return "March";
            case "4":
                return "April";
            case "5":
                return "May";
            case "6":
                return "June";
            case "7":
                return "July";
            case "8":
                return "August";
            case "9":
                return "September";
            case "10":
                return "October";
            case "11":
                return "November";
            case "12":
                return "December";
            default:
                return "January";
        }
    }
}
