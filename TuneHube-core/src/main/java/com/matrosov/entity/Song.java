package com.matrosov.entity;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Value
@Builder
public class Song {
    Integer id;
    String title;
    User artist;
    String album;
    String genre;
    LocalDate release_date;
    User uploaded_by;
    LocalDateTime upload_date;
}
