package domain;

import persistence.H2Driver;
import persistence.JdbcStudentRepository;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            JdbcStudentRepository j = new JdbcStudentRepository(new H2Driver().getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}