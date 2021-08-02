/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sherlock.dvdlibrary.dto;

/**
 *
 * @author Sherlock
 */
public class Dvd {
    private String title;
    private String releaseDate;
    private String mpaaRating;
    private String director;
    private String studio;
    private String userComment;
    
    public Dvd(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
    
    public String getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }
    
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
    
    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }
    
        public String getUserComment() {
        return userComment;
    }

    public void setuserComment(String userComment) {
        this.userComment = userComment;
    }
    
    
}
