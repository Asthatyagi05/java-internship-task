package student.tracker;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker {
    static ArrayList<String> studentNames = new ArrayList<>();
    static ArrayList<Integer> studentMarks = new ArrayList<>();
    static ArrayList<String> studentGrade = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Student Grade Tracker");
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add student");
            System.out.println("2. Delete student");
            System.out.println("3. Update student marks");
            System.out.println("4. Show all students");
            System.out.println("5. Show Grade summary");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = getIntInput();
            switch (choice) {
                case 1 -> addStudent();
                case 2 -> deleteStudent();
                case 3 -> updateStudent();
                case 4 -> showAllStudents();
                case 5 -> showSummary();
                case 6 -> {
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice, try again.");
            }
        }
    }

    private static int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static void addStudent() {
        scanner.nextLine(); // consume leftover newline
        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty!");
            return;
        }
        System.out.print("Enter marks (0–100): ");
        int marks = getIntInput();
        if (marks < 0 || marks > 100) {
            System.out.println("Marks must be between 0 and 100");
            return;
        }
        String grade = convertMarksToGrade(marks);
        studentNames.add(name);
        studentMarks.add(marks);
        studentGrade.add(grade);
        System.out.println("Student added successfully.");
    }

    private static void deleteStudent() {
        scanner.nextLine(); // consume newline
        if (studentNames.isEmpty()) {
            System.out.println("No students to delete.");
            return;
        }
        System.out.print("Enter the name of the student to delete: ");
        String name = scanner.nextLine().trim();
        int index = studentNames.indexOf(name);
        if (index == -1) {
            System.out.println("Student not found.");
        } else {
            studentNames.remove(index);
            studentMarks.remove(index);
            studentGrade.remove(index);
            System.out.println("Student " + name + " deleted successfully.");
        }
    }

    private static void updateStudent() {
        scanner.nextLine(); // consume newline
        if (studentNames.isEmpty()) {
            System.out.println("No students to update.");
            return;
        }
        System.out.print("Enter the name of the student to update: ");
        String name = scanner.nextLine().trim();
        int index = studentNames.indexOf(name);
        if (index == -1) {
            System.out.println("Student not found.");
            return;
        }
        System.out.print("Enter new marks (0–100): ");
        int newMarks = getIntInput();
        if (newMarks < 0 || newMarks > 100) {
            System.out.println("Marks must be between 0 and 100");
            return;
        }
        studentMarks.set(index, newMarks);
        studentGrade.set(index, convertMarksToGrade(newMarks));
        System.out.println("Marks updated successfully for " + name);
    }

    private static void showAllStudents() {
        if (studentNames.isEmpty()) {
            System.out.println("No students added yet.");
            return;
        }
        System.out.println("\nAll Students:");
        for (int i = 0; i < studentNames.size(); i++) {
            System.out.println((i + 1) + ". " + studentNames.get(i)
                    + " | Marks: " + studentMarks.get(i)
                    + " | Grade: " + studentGrade.get(i));
        }
    }

    private static void showSummary() {
        if (studentMarks.isEmpty()) {
            System.out.println("No students to summarize.");
            return;
        }
        int sum = 0, max = studentMarks.get(0), min = studentMarks.get(0);
        for (int marks : studentMarks) {
            sum += marks;
            if (marks > max) max = marks;
            if (marks < min) min = marks;
        }
        double avg = (double) sum / studentMarks.size();
        System.out.printf("Average Marks: %.2f%n", avg);
        System.out.println("Highest Marks: " + max);
        System.out.println("Lowest Marks: " + min);
    }

    private static String convertMarksToGrade(int marks) {
        if (marks >= 90) return "A+";
        else if (marks >= 80) return "A";
        else if (marks >= 70) return "B+";
        else if (marks >= 60) return "B";
        else if (marks >= 50) return "C";
        else if (marks >= 40) return "D";
        else return "F";
    }
}
