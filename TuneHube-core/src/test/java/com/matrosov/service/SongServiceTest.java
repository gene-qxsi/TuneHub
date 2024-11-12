package com.matrosov.service;

import com.matrosov.dao.SongDao;
import com.matrosov.dao.UserDao;
import com.matrosov.entity.Gender;
import com.matrosov.entity.Role;
import com.matrosov.entity.Song;
import com.matrosov.entity.User;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SongServiceTest {

    SongService songService = new SongService(SongDao.getInstance());
    UserService userService = new UserService(UserDao.getInstance());

    @Test
    void findAll() {

    }

    @Test
    void deleteAll() {
        userService.save(getUser());
        songService.save(getSong());

        boolean actualResult = songService.deleteAll();

        assertThat(actualResult).isTrue();
    }

    @SneakyThrows
    private Song getSong() {
        return Song.builder()
                .id(7)
                .title("title")
                .artist(getUser())
                .album("album")
                .genre("genre")
                .release_date(LocalDate.now())
                .upload_date(LocalDateTime.now())
                .build();
    }

    @SneakyThrows
    private User getUser() {
        return User.builder()
                .id(1)
                .email("email")
                .password("password")
                .role(Role.USER)
                .username("username")
                .created_at(LocalDateTime.now())
                .gender(Gender.MALE)
                .birthday(LocalDate.of(2008, 12, 3))
                .build();
    }

}
