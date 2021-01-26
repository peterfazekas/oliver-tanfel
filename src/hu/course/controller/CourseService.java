package hu.course.controller;

import hu.course.model.domain.Course;

import java.util.List;
import java.util.stream.Collectors;

public class CourseService {

    private final List<Course> courses;

    public CourseService(List<Course> courses) {
        this.courses = courses;
    }

    /**
     * 2. feladat: Hány bejegyzés található az állományban?
     */
    public int getCourseCount() {
        return courses.size();
    }

    /**
     * 3. feladat: A fenntartó számára fontos információ, hogy az iskolában hetente
     * összesen hány tanítási óra van. Határozza meg ezt az adatot és írassa ki a képernyőre!
     */
    public int getTotalLessonCount() {
        return courses.stream()
                .mapToInt(Course::getWeeklyLessonNumber)
                .sum();
    }

    /**
     * 4. feladat: Kérje be a felhasználótól egy tanár nevét, és írassa ki a képernyőre,
     * hogy hetente hány órában tanít!
     */
    public int getTeacherWeeklyLesson(String teacher) {
        return courses.stream()
                .filter(i -> i.getTeacher().equals(teacher))
                .mapToInt(Course::getWeeklyLessonNumber)
                .sum();
    }

    /**
     * 5. feladat: Készítse el az of.txt fájlt, amely az osztályfőnökök nevét tartalmazza osztályonként.
     */
    public List<String> getMasterTeachers() {
        return courses.stream()
                .filter(i -> "osztalyfonoki".equals(i.getSubject()))
                .map(i -> i.getClassId() + " - " + i.getTeacher())
                .collect(Collectors.toList());
    }

    /**
     * 6. feladat: Kérje be egy osztály azonosítóját, valamint egy tantárgy nevét,
     * és írassa ki a képernyőre, hogy az adott osztály a megadott tantárgyat
     * csoportbontásban vagy osztályszinten tanulja-e!
     */
    public String getCourseStatus(String classId, String subject) {
        return getCertainCourseCount(classId, subject) > 1 ? "Csoportbontásban tanulják." : "Osztályszinten tanulják." ;
    }

    private long getCertainCourseCount(String classId, String subject) {
        return courses.stream()
                .filter(i -> i.getClassId().equals(classId) && i.getSubject().equals(subject))
                .count();
    }

    /**
     * 7. feladat: A fenntartó számára az is fontos információ, hogy hány tanár dolgozik az iskolában.
     * Írassa ki ezt az adatot a képernyőre!
     */
    public long getTeacherCount() {
        return courses.stream()
                .map(Course::getTeacher)
                .distinct()
                .count();
    }
}
