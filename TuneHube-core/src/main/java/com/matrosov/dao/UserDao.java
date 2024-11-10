package com.matrosov.dao;

import com.matrosov.entity.Gender;
import com.matrosov.entity.Role;
import com.matrosov.entity.User;
import com.matrosov.util.ConnectionManager;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserDao implements Dao<Integer, User> {
    private static final String SELECT_ALL_SQL = """
            SELECT id,
                   username,
                   password,
                   email,
                   birthday,
                   role,
                   gender,
                   created_at
            FROM users
            """;
    private static final String SELECT_BY_ID_SQL = SELECT_ALL_SQL + """
            WHEN id = ?
            """;
    private static final String INSERT_SQL = """
            INSERT INTO users (username, password, email, birthday, role, gender, created_at)
            VALUES (?,?,?,?,?,?,?)
            """;
    private static final String DELETE_SQL = """
            DELETE FROM users
            WHEN id = ?
            """;
    private static final String UPDATE_BY_ID_SQL = """
            UPDATE users
            SET name = ?,
                birthday = ?,
                email = ?,
                password = ?,
                role = ?,
                gender = ?
            WHERE id = ?
            """;

    @Override
    @SneakyThrows
    public List<User> findAll() {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<User> users = new ArrayList<>();
            while (resultSet.next())
                users.add(build(resultSet));

            return users;
        }
    }

    @Override
    @SneakyThrows
    public Optional<User> findById(Integer id) {
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
    public void update(User entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_BY_ID_SQL)) {
            prepareStatementToUpsert(preparedStatement, entity);
            preparedStatement.setObject(7, entity.getId());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    @SneakyThrows
    public User save(User entity) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            prepareStatementToUpsert(preparedStatement, entity);

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            entity.setId(generatedKeys.getInt("id"));

            return entity;
        }
    }

    private void prepareStatementToUpsert(PreparedStatement preparedStatement, User entity) throws SQLException {
        preparedStatement.setObject(1, entity.getUsername());
        preparedStatement.setObject(2, Date.valueOf(entity.getBirthday()));
        preparedStatement.setObject(3, entity.getEmail());
        preparedStatement.setObject(4, entity.getPassword());
        preparedStatement.setObject(5, entity.getRole().name());
        preparedStatement.setObject(6, entity.getGender().name());
        preparedStatement.setObject(7, entity.getCreated_at());
    }

    @SneakyThrows
    private static User build(ResultSet resultSet) {
        return User.builder()
                .id(resultSet.getInt("id"))
                .email(resultSet.getString("email"))
                .password(resultSet.getString("password"))
                .role(Role.valueOf(resultSet.getObject("role", String.class)))
                .username(resultSet.getString("username"))
                .created_at(resultSet.getObject("created_at", Timestamp.class).toLocalDateTime())
                .gender(Gender.valueOf(resultSet.getObject("gender", String.class)))
                .birthday(resultSet.getObject("birthday", java.sql.Date.class).toLocalDate())
                .build();
    }
}

















