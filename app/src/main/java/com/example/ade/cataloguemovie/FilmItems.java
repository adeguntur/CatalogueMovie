package com.example.ade.cataloguemovie;

import org.json.JSONObject;

/**
 * Created by Ade on 18/02/2018.
 */

public class FilmItems {
    private int id;
    private String title;
    private String overview;
    private String relase_date;

    public FilmItems(JSONObject object) {
        try {
            int id = object.getInt("id");
            String title = object.getString("title");
            String overview = object.getString("overview");
            String relase_date = object.getString("relase_date");
            this.id = id;
            this.title = title;
            this.overview = overview;
            this.relase_date = relase_date;
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

    public String getRelase_date() {
        return relase_date;
    }

    public void setRelase_date(String relase_date) {
        this.relase_date = relase_date;
    }
}
