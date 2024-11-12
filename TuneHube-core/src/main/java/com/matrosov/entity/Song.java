package com.matrosov.entity;

import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
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
