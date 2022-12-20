package domain;

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

    public static boolean isValid(String str) {
        if (!str.endsWith("F")) {
            return false;
        }

        String [] line = str.split(",");
        if (line.length != 5)
            return false;

        Gender g;

        if (line[2].equals("m")) {
            g = Gender.MALE;
        } else if (line[2].equals("w")) {
            g = Gender.FEMALE;
        } else if (line[2].equals("d")) {
            g = Gender.DIVERSE;
        } else {
            return false;
        }


        try {
            new Student(line[0], line[1], g, Integer.parseInt(line[3]), line[4]);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public static Student of(String csv) {
        if (isValid(csv)) {
            String [] line = csv.split(",");
            Gender g;

            if (line[2].equals("m")) {
                g = Gender.MALE;
            } else if (line[2].equals("w")) {
                g = Gender.FEMALE;
            } else {
                g = Gender.DIVERSE;
            }

            return new Student(line[0], line[1], g, Integer.parseInt(line[3]), line[4]);
        }
        return null;
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