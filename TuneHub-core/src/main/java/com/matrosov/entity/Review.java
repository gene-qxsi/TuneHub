package com.matrosov.entity;

import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {
    Integer id;
    User author;
    Song song;
    Integer rating;
    String comment;
    LocalDateTime review_date;
}
