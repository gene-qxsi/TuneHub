package com.matrosov.dao;

import com.matrosov.entity.Review;
import com.matrosov.util.ConnectionManager;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.*;

@NoArgsConstructor(access = PRIVATE)
public class ReviewDao implements Dao<Integer, Review> {
    UserDao userDao = UserDao.getInstance();
    SongDao songDao = SongDao.getInstance();

    private static final String DELETE_ALL_SQL = """
            DELETE FROM song
            """;
    private static final String SELECT_ALL_SQL = """
            SELECT id,
                   author,
                   song,
                   rating,
                   comment,
                   review_date
            FROM song
            """;
    private static final String SELECT_BY_ID_SQL = SELECT_ALL_SQL + """
            WHERE id = ?
            """;
    private static final String INSERT_SQL = """
            INSERT INTO song (author, song, rating, comment, review_date)
            VALUES (?,?,?,?)
            """;
    private static final String DELETE_SQL = """
            DELETE FROM song
            WHERE id = ?
            """;
    private static final String UPDATE_BY_ID_SQL = """
            UPDATE song
            SET author = ?,
                song = ?,
                rating = ?,
                comment = ?,
                review_date = ?
            WHERE id = ?
            """;

    @Override
    @SneakyThrows
    public List<Review> findAll() {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<Review> users = new ArrayList<>();
            while (resultSet.next())
                users.add(build(resultSet));

            return users;
        }
    }

    @Override
    @SneakyThrows
    public Optional<Review> findById(Integer id) {
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
    public void update(Review entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_BY_ID_SQL)) {
            prepareStatementToUpsert(preparedStatement, entity);
            preparedStatement.setObject(6, entity.getId());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    @SneakyThrows
    public Review save(Review entity) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            prepareStatementToUpsert(preparedStatement, entity);

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            entity.setId(generatedKeys.getInt("id"));
            entity = findById(entity.getId()).orElse(null);
            return entity;
        }
    }

    private void prepareStatementToUpsert(PreparedStatement preparedStatement, Review entity) throws SQLException {
        preparedStatement.setObject(1, entity.getAuthor());
        preparedStatement.setObject(2, entity.getSong().getId());
        preparedStatement.setObject(3, entity.getRating());
        preparedStatement.setObject(4, entity.getComment());
        preparedStatement.setObject(5, entity.getReview_date());
    }

    @SneakyThrows
    private Review build(ResultSet resultSet) {
        return Review.builder()
                .id(resultSet.getInt("id"))
                .author(userDao.findById(resultSet.getInt("author")).orElse(null))
                .song(songDao.findById(resultSet.getInt("song")).orElse(null))
                .rating(resultSet.getInt("rating"))
                .comment(resultSet.getString("comment"))
                .review_date(resultSet.getObject("review_date", LocalDateTime.class))
                .build();
    }

    public static ReviewDao getInstance() {
        return new ReviewDao();
    }

    @SneakyThrows
    public boolean deleteAll() {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALL_SQL)) {

            return preparedStatement.executeUpdate() > 0;
        }
    }
}
