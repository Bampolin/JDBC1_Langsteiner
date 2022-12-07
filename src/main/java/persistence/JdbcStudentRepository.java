package persistence;

import domain.Gender;
import domain.Student;
import org.h2.jdbc.JdbcResultSet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Stream;

public record JdbcStudentRepository(Connection connection) implements StudentRepository {

    @Override
    public void deleteAll() throws SQLException {
        var statement = connection.createStatement();
        statement.executeUpdate("delete from students");
    }

    @Override
    public Optional<Student> findByNumberAndClass(int number, String schoolClass) throws SQLException {
        var statement = connection.createStatement();
        return null;
    }

    @Override
    public Stream<Student> findAll() throws SQLException {
        var statement = connection.createStatement();
        ResultSet result = statement.executeQuery("""
            select * from students;
        """);





        return null;
    }

    @Override
    public SortedSet<Student> findStudentsByClass(String schoolClass) throws SQLException {
        return null;
    }

    @Override
    public Set<Student> findStudentsByGender(Gender gender) throws SQLException {
        return null;
    }

    @Override
    public Map<String, Integer> findClasses() throws SQLException {
        return null;
    }

    @Override
    public Student save(Student student) throws SQLException {
        return null;
    }
}
