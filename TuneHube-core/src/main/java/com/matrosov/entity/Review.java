package com.matrosov.entity;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class Review {
    Integer id;
    String author;
    Song song;
    Integer rating;
    String comment;
    LocalDateTime review_date;
}
