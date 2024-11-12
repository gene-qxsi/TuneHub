package com.matrosov.dao;

import com.matrosov.entity.Gender;
import com.matrosov.entity.Role;
import com.matrosov.entity.Song;
import com.matrosov.entity.User;
import com.matrosov.util.ConnectionManager;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.*;

@NoArgsConstructor(access = PRIVATE)
public class SongDao implements Dao<Integer, Song> {

    private static final String DELETE_ALL_SQL = """
            DELETE FROM song
            """;
    private final UserDao userDao = UserDao.getInstance();

    private static final String SELECT_ALL_SQL = """
            SELECT id,
                    title,
                    artist,
                    album,
                    genre,
                    release_date,
                    uploaded_by,
                    upload_date
            FROM song
            """;

    private static final String SELECT_BY_ID_SQL = SELECT_ALL_SQL + """
            WHERE id = ?
            """;
    private static final String INSERT_SQL = """
            INSERT INTO song (title, artist, album, genre, release_date, uploaded_by, upload_date)
            VALUES (?,?,?,?,?,?,?)
            """;
    private static final String DELETE_SQL = DELETE_ALL_SQL + """
            WHERE id = ?
            """;
    private static final String UPDATE_BY_ID_SQL = """
            UPDATE song
            SET title = ?,
                artist = ?,
                album = ?,
                genre = ?,
                release_date = ?,
                uploaded_by = ?,
                uploaded_date = ?
            WHERE id = ?
            """;

    @Override
    @SneakyThrows
    public List<Song> findAll() {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<Song> songs = new ArrayList<>();
            while (resultSet.next())
                songs.add(build(resultSet));

            return songs;
        }
    }

    @Override
    @SneakyThrows
    public Optional<Song> findById(Integer id) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next() ?
                    Optional.of(build(resultSet))
                    : Optional.empty();
        }
    }

    @Override
    @SneakyThrows
    public boolean delete(Integer id) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    @SneakyThrows
    public void update(Song entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_BY_ID_SQL)) {
            prepareStatementToUpsert(preparedStatement, entity);
            preparedStatement.setObject(8, entity.getId());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    @SneakyThrows
    public Song save(Song entity) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            prepareStatementToUpsert(preparedStatement, entity);

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            entity.setId(generatedKeys.getInt("id"));
            entity = getInstance().findById(entity.getId()).orElse(null);
            return entity;
        }
    }

    private void prepareStatementToUpsert(PreparedStatement preparedStatement, Song entity) throws SQLException {
        preparedStatement.setObject(1, entity.getTitle());
        preparedStatement.setObject(2, entity.getArtist().getId());
        preparedStatement.setObject(3, entity.getAlbum());
        preparedStatement.setObject(4, entity.getGenre());
        preparedStatement.setObject(5, entity.getRelease_date());
        preparedStatement.setObject(6, entity.getUploaded_by());
        preparedStatement.setObject(7, entity.getUpload_date());
    }


    @SneakyThrows
    private Song build(ResultSet resultSet) {
        return Song.builder()
                .id(resultSet.getInt("id"))
                .title(resultSet.getString("title"))
                .artist(userDao.findById(resultSet.getInt("artist")).orElse(null))
                .album(resultSet.getString("album"))
                .genre(resultSet.getString("genre"))
                .release_date(resultSet.getObject("release_date", LocalDate.class))
                .uploaded_by(userDao.findById(resultSet.getInt("uploaded_by")).orElse(null))
                .upload_date(resultSet.getObject("upload_date", LocalDateTime.class))
                .build();
    }

    public static SongDao getInstance() {
        return new SongDao();
    }

    @SneakyThrows
    public boolean deleteAll() {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALL_SQL)) {

            return preparedStatement.executeUpdate() > 0;
        }
    }
}
