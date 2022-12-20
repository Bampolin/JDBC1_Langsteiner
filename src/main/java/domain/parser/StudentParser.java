package domain.parser;


import domain.Student;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class StudentParser {
    /**
     * Converts all lines of a csv-formatted file to the students. Invalid lines are skipped
     *
     * @param path the path of the csv-file
     */
    public Collection<Student> readFromCsv(Path path) throws IOException {
        List<Student> result = new LinkedList<>();
        return Files.lines(path)
                .filter(Student::isValid)
                .map(Student::of)
                .toList();
    }
}