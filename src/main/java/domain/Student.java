package domain;

import java.util.Objects;

public final class Student {
    private Integer id;
    private final String lastName;
    private final String firstName;
    private final Gender gender;
    private final int number;
    private final String schoolClass;

    public Student(String lastName, String firstName, Gender gender, int number, String schoolClass) {
        if (lastName.isBlank())
            throw new IllegalArgumentException("Last name cannot be blank");
        if (firstName.isBlank())
            throw new IllegalArgumentException("First name cannot be blank");
        if (number < 1 || number > 36)
            throw new IllegalArgumentException("Number out of range, must be of [1,36]");

        if (schoolClass.isBlank())
            throw new IllegalArgumentException("Student must be part of a class");
        int first = 0;
        try {
            first = Integer.parseInt(schoolClass.substring(0, 1));
        } catch (Exception e) {
        }
        if (first < 1 || first > 5)
            throw new IllegalArgumentException("Illegal Class");

        if (!schoolClass.endsWith("HIF"))
            throw new IllegalArgumentException("Illegal Class");
        if (schoolClass.length() > 5)
            throw new IllegalArgumentException("Illegal Class");

        if (!"ABC".contains(schoolClass.substring(1, 2)))
            throw new IllegalArgumentException("Illegal Class");


        this.lastName = lastName;
        this.firstName = firstName;
        this.gender = gender;
        this.number = number;
        this.schoolClass = schoolClass;
    }

    public Student(Integer id, String lastName, String firstName, Gender gender, int number, String schoolClass) {
        this(lastName, firstName, gender, number, schoolClass);
        this.id = id;
    }


    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", gender=" + gender +
                ", number=" + number +
                ", schoolClass='" + schoolClass + '\'' +
                '}';
    }
}