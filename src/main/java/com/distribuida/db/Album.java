package com.distribuida.db;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "albums")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "singer_id")
    private Integer singerId;

    @Column(name = "title")
    private String title;

    @Column(name = "release_date")
    private Date releaseDate;

    @Column(name = "version")
    private Integer version;

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

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", singerId=" + singerId +
                ", title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", version=" + version +
                '}';
    }
}
