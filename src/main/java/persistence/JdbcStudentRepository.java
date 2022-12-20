package persistence;

import domain.Gender;
import domain.Student;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record JdbcStudentRepository(Connection connection) implements StudentRepository {

    @Override
    public void deleteAll() throws SQLException {
        connection.createStatement().executeUpdate("delete from students");
    }

    @Override
    public Optional<Student> findByNumberAndClass(int number, String schoolClass) throws SQLException {
        findAll().filter(s -> s.getSchoolClass().equals(schoolClass));








        return null;
    }

    @Override
    public Stream<Student> findAll() throws SQLException {
        ResultSet result = connection.createStatement().executeQuery("""
            select * from students;
        """);
        List<Student> s = new LinkedList<>();

        while (result.next()) {
            String firstName = result.getString("first_name");
            String lastName = result.getString("last_name");
            int studentNumber = result.getInt("student_number");
            String schoolclass = result.getString("class");
            Gender gender = Gender.valueOf(result.getString("gender"));

            s.add(new Student(firstName, lastName, gender, studentNumber, schoolclass));
        }

        return s.stream();
    }

    @Override
    public SortedSet<Student> findStudentsByClass(String schoolClass) throws SQLException {
        return new TreeSet(findAll()
                .filter(s -> s.getClass().equals(schoolClass))
                .collect(Collectors.toSet()));
    }

    @Override
    public Set<Student> findStudentsByGender(Gender gender) throws SQLException {
        return new TreeSet<> (findAll()
                .filter(s -> s.getGender() == gender)
                .collect(Collectors.toSet()));
    }

    @Override
    public Map<String, Integer> findClasses() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select CLASS, count(*) from STUDENTS GROUP BY CLASS");

        ResultSet res = preparedStatement.executeQuery();
        Map<String, Integer> map = new TreeMap<>();

        while (res.next()) {
            map.put(res.getString("class"), res.getInt("count(*)"));
        }

        return map;
    }

    @Override
    public Student save(Student student) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into students (last_name, first_name, gender, student_number, class)" +
                " values (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                student.setId(generatedKeys.getInt(1));
            }
        }

        preparedStatement.setString(1, student.getFirstName());
        preparedStatement.setString(2, student.getLastName());
        preparedStatement.setString(3, student.getGender().toString());
        preparedStatement.setInt(4, student.getNumber());
        preparedStatement.setString(5, student.getSchoolClass());
        preparedStatement.executeUpdate();

        return student;
    }
}