package domain;

import persistence.H2Driver;
import persistence.JdbcStudentRepository;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try (var connection = new H2Driver().getConnection()) {
            var statement = connection.createStatement();
            statement.executeUpdate("drop table if exists students");
            statement.executeUpdate("""
        CREATE TABLE students(
            id identity, 
            last_name varchar(255),
            first_name varchar(255),
            gender varchar(30),
            student_number int,
            class varchar(5),
            primary key (id),
            unique (class, student_number)
        );
            """);
            System.out.println(statement.executeQuery("""
        select * from students;
            """));
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}