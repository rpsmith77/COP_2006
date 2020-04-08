import java.util.Scanner;

/*
 * This program determines and display the level of a given class/course.
 */
public class ClassLevel {

    private enum Level {Invalid, Graduate, Senior, Junior, Sophomore, Freshman}

    private enum Size {Invalid, Small, Normal, Large, Overflow}

    public static void main(String[] args) {
        final int INVALID_CUTOFF = 9000;
        final int GRADUATE_CUTOFF = 5000;
        final int SENIOR_CUTOFF = 4000;
        final int JUNIOR_CUTOFF = 3000;
        final int SOPHOMORE_CUTOFF = 2000;
        final int FRESHMAN_CUTOFF = 1000;

        // read the class prefix and number
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter the course code (three letters): ");
        String courseCode = keyboard.next();
        System.out.println("Please enter the course number for " + courseCode + ":");
        int courseNumber = keyboard.nextInt();
        System.out.println("Please enter the enrollment (number of students) in " + courseCode + " " + courseNumber + ": ");
        int enrolledStudents = keyboard.nextInt();
        keyboard.close();

        // store class level
        Level levelOfClass = Level.Invalid;

        // store size type
        Size sizeOfClass = Size.Invalid;

        // determine the course level
        if (courseNumber >= INVALID_CUTOFF || courseNumber < 0) {
            levelOfClass = Level.Invalid;
        } else if (courseNumber >= GRADUATE_CUTOFF) {
            // System.out.println("Graduate");
            levelOfClass = Level.Graduate;

            // determine and display the class type based on enrollment
            if (enrolledStudents >= 0 && enrolledStudents <= 5) {
                sizeOfClass = Size.Small;
            } else if (enrolledStudents > 5 && enrolledStudents <= 15) {
                sizeOfClass = Size.Normal;
                ;
            } else if (enrolledStudents > 15 && enrolledStudents <= 20) {
                sizeOfClass = Size.Large;
            } else if (enrolledStudents > 20) {
                sizeOfClass = Size.Overflow;
            } else {
                sizeOfClass = Size.Invalid;
            }
        } else if (courseNumber >= SENIOR_CUTOFF) {
            levelOfClass = Level.Senior;
            if (enrolledStudents >= 0 && enrolledStudents <= 10) {
                sizeOfClass = Size.Small;
            } else if (enrolledStudents > 10 && enrolledStudents <= 20) {
                sizeOfClass = Size.Normal;
            } else if (enrolledStudents > 20 && enrolledStudents <= 35) {
                sizeOfClass = Size.Large;
            } else if (enrolledStudents > 35) {
                sizeOfClass = Size.Overflow;
            } else {
                sizeOfClass = Size.Invalid;
            }
        } else if (courseNumber >= JUNIOR_CUTOFF) {
            //System.out.println("Upper:Division:Junior");
            levelOfClass = Level.Junior;
            if (enrolledStudents >= 0 && enrolledStudents <= 15) {
                sizeOfClass = Size.Small;
            } else if (enrolledStudents > 15 && enrolledStudents <= 25) {
                sizeOfClass = Size.Normal;
                ;
            } else if (enrolledStudents > 25 && enrolledStudents <= 45) {
                sizeOfClass = Size.Large;
            } else if (enrolledStudents > 45) {
                sizeOfClass = Size.Overflow;
            } else {
                sizeOfClass = Size.Invalid;
            }
        } else if (courseNumber >= SOPHOMORE_CUTOFF) {
            //System.out.println("Lower:Division:Sophomore");
            levelOfClass = Level.Sophomore;
            if (enrolledStudents >= 0 && enrolledStudents <= 20) {
                sizeOfClass = Size.Small;
            } else if (enrolledStudents > 20 && enrolledStudents <= 40) {
                sizeOfClass = Size.Normal;
                ;
            } else if (enrolledStudents > 40 && enrolledStudents <= 60) {
                sizeOfClass = Size.Large;
            } else if (enrolledStudents > 60) {
                sizeOfClass = Size.Overflow;
            } else {
                sizeOfClass = Size.Invalid;
            }
        } else if (courseNumber >= FRESHMAN_CUTOFF) {
            //System.out.println("Freshman");
            levelOfClass = Level.Freshman;

            if (enrolledStudents >= 0 && enrolledStudents <= 25) {
                sizeOfClass = Size.Small;
            } else if (enrolledStudents > 25 && enrolledStudents <= 50) {
                sizeOfClass = Size.Normal;
                ;
            } else if (enrolledStudents > 50 && enrolledStudents <= 75) {
                sizeOfClass = Size.Large;
            } else if (enrolledStudents > 75) {
                sizeOfClass = Size.Overflow;
            } else {
                sizeOfClass = Size.Invalid;
            }
        }

        // display the course level
        System.out.println("The level of " + courseCode + " is as follows:");

        // display class level
        switch (levelOfClass) {
            case Invalid:
                System.out.println("This class does not exist in our system.");
                break;
            case Graduate:
                System.out.println("This class is offered by our Graduate School.");
                break;
            case Senior:
                System.out.println("This class is offered typically in the 4th year");
                break;
            case Junior:
                System.out.println("This class is offered typically in the 3rd year");
                break;
            case Sophomore:
                System.out.println("This class is offered typically in the 2nd year");
                break;
            case Freshman:
                System.out.println("This class is offered typically in the 1st year");
                break;
            default:
                break;
        }

        // display class size
        switch (sizeOfClass) {
            case Invalid:
                System.out.println("Invalid Class Size");
                break;
            case Small:
                System.out.println("This class has a small amount of students");
                break;
            case Normal:
                System.out.println("This class has a normal amount of students");
                break;
            case Large:
                System.out.println("This class has a large amount of students");
                break;
            case Overflow:
                System.out.println("This is an overflow class");
                break;
            default:
                break;


        }


    }
}