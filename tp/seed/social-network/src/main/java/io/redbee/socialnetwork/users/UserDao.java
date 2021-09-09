package io.redbee.socialnetwork.users;

import io.redbee.socialnetwork.shared.exception.RepositoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Component
public class UserDao {
    private final NamedParameterJdbcTemplate template;

    public UserDao(@Qualifier("usersTemplate") NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class);

    private static final String getQuery = "SELECT " +
            "id, " +
            "mail, " +
            "encrypted_password, " +
            "status, " +
            "creation_date, " +
            "creation_user, " +
            "modification_date, " +
            "modification_user " +
            "FROM users";

    private static final String insertQuery = "" +
            "INSERT INTO users (mail, encrypted_password, status, creation_date, creation_user, modification_date, modification_user) " +
            "VALUES (:mail, :encrypted_password, :status, :creation_date, :creation_user, :modification_date, :modification_user) ";

    private static final String updateQuery = "" +
            "UPDATE users " +
            "SET mail               = :mail, " +
            "    encrypted_password = :encrypted_password, " +
            "    status             = :status, " +
            "    modification_date  = :modification_date, " +
            "    modification_user  = :modification_user " +
            "WHERE id = :id";

    public Optional<User> save(User user) {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            template.update(insertQuery, userToParamMap(user), keyHolder);
            LOGGER.info("save: user {} saved", user.getMail());

            int id = (int) Objects.requireNonNull(keyHolder.getKeys().get("id"));
            return this.getById(id);
        } catch (Exception e) {
            LOGGER.info("save: error {}, saving user {}", e.getMessage(), user.getMail());
            throw new RepositoryException();
        }
    }

    public User update(User user) {
        try {
            template.update(updateQuery, userToParamMap(user));
            LOGGER.info("update: user {} updated", user.getMail());
            return user;
        } catch (Exception e) {
            LOGGER.info("update: error {}, updating user {}", e.getMessage(), user.getId());
        }
        return user;
    }

    public List<User> get() {
        try {
            List<User> result = template.query(getQuery, new UserRowMapper());
            LOGGER.info("get: users found: {}", result);
            return result;
        } catch (DataAccessException e) {
            LOGGER.info("get: error {} searching users", e.getMessage());
            throw new RepositoryException();
        }
    }

    public Optional<User> getById(Integer id) {
        try {
            Optional<User> result = Optional.ofNullable(
                    template.queryForObject(
                            getQuery + " WHERE id = :id",
                            Map.of("id", id),
                            new UserRowMapper()
                    )
            );
            LOGGER.info("getById: user found: {}", result);
            return result;
        } catch (ResourceAccessException e) {
            LOGGER.info("getById: user with id {} not found", id);
            return Optional.empty();
        } catch (DataAccessException e) {
            LOGGER.info("getById: error {} searching user with id: {}", e.getMessage(), id);
            throw new RepositoryException();
        }
    }

    public Optional<User> getByMail(String mail) {
        try {
            Optional<User> result = Optional.ofNullable(
                    template.queryForObject(
                            getQuery + " WHERE mail = :mail",
                            Map.of("mail", mail),
                            new UserRowMapper()
                    )
            );
            LOGGER.info("getByMail: user found {}", result);
            return result;
        } catch (ResourceAccessException e) {
            LOGGER.info("getByMail: user with id {} not found", mail);
            return Optional.empty();
        } catch (DataAccessException e) {
            LOGGER.info("getByMail: error {} searching user with id: {}", e.getMessage(), mail);
            throw new RepositoryException();
        }
    }

    private MapSqlParameterSource userToParamMap(User user) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", user.getId());
        params.addValue("mail", user.getMail());
        params.addValue("encrypted_password", user.getEncryptedPassword());
        params.addValue("status", user.getStatus());
        params.addValue("creation_date", user.getCreationDate());
        params.addValue("creation_user", user.getCreationUser());
        params.addValue("modification_date", user.getModificationDate());
        params.addValue("modification_user", user.getModificationUser());

        return params;
    }

}
