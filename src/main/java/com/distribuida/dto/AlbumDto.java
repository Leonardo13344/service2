package com.distribuida.dto;

import java.time.LocalDate;
import java.util.Date;

public class AlbumDto {

    private Integer id;
    private Integer singerId;
    private String title;
    private LocalDate releaseDate;
    private Integer version;
    private String singerFullName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSingerId() {
        return singerId;
    }

    public void setSingerId(Integer singerId) {
        this.singerId = singerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getSingerFullName() {
        return singerFullName;
    }

    public void setSingerFullName(String singerFullName) {
        this.singerFullName = singerFullName;
    }
}
