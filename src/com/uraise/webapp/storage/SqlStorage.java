package com.uraise.webapp.storage;

import com.uraise.webapp.exception.NotExistStorageException;
import com.uraise.webapp.exception.StorageException;
import com.uraise.webapp.model.Resume;
import com.uraise.webapp.sql.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {
    public final ConnectionFactory connectionFactory;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void clear() {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM resume")
        ) {
            ps.execute();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public void save(Resume r) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO resume(uuid, full_name)  VALUES (?,?)")
        ) {
            ps.setString(1, r.getUuid());
            ps.setString(2, r.getFullName());
            ps.execute();
        } catch (SQLException e) {
            throw new StorageException(e);
        }

    }

    @Override
    public void update(Resume r) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE resume SET uuid = ?, full_name = ?")) {
            ps.setString(1, r.getUuid());
            ps.setString(2, r.getFullName());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new StorageException(e);
        }

    }

    @Override
    public Resume get(String uuid) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume r WHERE r.uuid = ?")
        ) {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            Resume r = new Resume(uuid, rs.getString("full_name"));
            return r;
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public void delete(String uuid) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM resume WHERE ?")) {
            ps.setString(1, uuid);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new StorageException(e);
        }

    }

    @Override
    public List<Resume> getAllSorted() {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume ORDER BY uuid")) {
            ResultSet rs = ps.executeQuery();
            List<Resume> resumes = new ArrayList<>();
            while (rs.next()) {
                resumes.add(new Resume(rs.getString(1), rs.getString(2)));
            }
            return resumes;
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public int size() {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT count(uuid) FROM resume GROUP BY uuid")) {
            ResultSet rs = ps.executeQuery();
            rs.last();
            return rs.getRow();
        } catch (SQLException e) {
            throw new StorageException(e);
        }

    }
}
