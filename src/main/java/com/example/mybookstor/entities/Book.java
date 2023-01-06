package com.example.mybookstor.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "book")
@Setter
@Getter
@RequiredArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private String isbn;
    private String bookTitle;
    private String author;
    private Long bookPrice;
    private String genre;
    private String imagePath;
    private Date dateAdded;
    private Date dateUpdated;
    private Date dateDelete;

    private String createdBy;
    private String updatedBy;
    private String deletedBy;


    @PrePersist
    public void setDateAdded() {
        if(this.dateAdded==null)
            this.dateAdded= new Date(System.currentTimeMillis());
    }


    @PreUpdate
    public void setDateUpdated() {
        if(this.dateAdded !=null)
            this.dateUpdated = new Date(System.currentTimeMillis());
    }

    @PreRemove
    public void setDateDelete() {
        this.dateDelete = new Date(System.currentTimeMillis());
    }
}
