package cn.edu.sustech.cs307.service;

import cn.edu.sustech.cs307.dto.*;
import cn.edu.sustech.cs307.dto.prerequisite.Prerequisite;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@ParametersAreNonnullByDefault
public interface CourseService {
    /**
     * Add one course according to following parameters.
     * If some of parameters are invalid, throw {@link cn.edu.sustech.cs307.exception.IntegrityViolationException}
     *
     * @param courseId represents the id of course. For example, CS307, CS309
     * @param courseName the name of course
     * @param credit the credit of course
     * @param classHour The total teaching hour that the course spends.
     * @param grading the grading type of course
     * @param prerequisite The root of a {@link Prerequisite} expression tree.
     */
    void addCourse(String courseId, String courseName, int credit, int classHour,
                   Course.CourseGrading grading, @Nullable Prerequisite prerequisite);

    /**
     * Add one course section according to following parameters:
     * If some parameters are invalid, throw {@link cn.edu.sustech.cs307.exception.IntegrityViolationException}
     *
     * @param courseId represents the id of course. For example, CS307, CS309
     * @param semesterId the id of semester
     * @param sectionName the name of section {@link CourseSection}
     * @param totalCapacity the total capacity of section
     * @return the CourseSection id of new inserted line, if adding process is successful.
     */
    int addCourseSection(String courseId, int semesterId, String sectionName, int totalCapacity);


    /**
     * Add one course section class according to following parameters:
     * If some parameters are invalid, throw {@link cn.edu.sustech.cs307.exception.IntegrityViolationException}
     * @return the CourseSectionClass id of new inserted line.
     */
    int addCourseSectionClass(int sectionId, int instructorId, DayOfWeek dayOfWeek, Set<Short> weekList,
                              short classStart, short classEnd, String location);


    /**
     * To remove an entity from the system, related entities dependent on this entity (usually rows referencing the row to remove through foreign keys in a relational database)
     * shall be removed together.
     * More specifically, remove all related courseSection, all related CourseSectionClass and all related select course records
     * when a course has been removed
     */
    void removeCourse(String courseId);

    /**
     *  To remove an entity from the system, related entities dependent on this entity (usually rows referencing the row to remove through foreign keys in a relational database)
     *  shall be removed together.
     *   More specifically, remove all related CourseSectionClass and all related select course records
     *   when a courseSection has been removed
     * @param sectionId
     */
    void removeCourseSection(int sectionId);

    /**
     *  To remove an entity from the system, related entities dependent on this entity (usually rows referencing the row to remove through foreign keys in a relational database)
     *  shall be removed together.
     *  More specifically, only remove course section class
     */
    void removeCourseSectionClass(int classId);

    List<Course> getAllCourses();

    /**
     * Return all satisfied CourseSections.
     * We will compare the all other fields in CourseSection besides the id.
     * @param courseId if the key is non-existent, please throw an EntityNotFoundException.
     * @param semesterId if the key is non-existent, please throw an EntityNotFoundException.
     */
    List<CourseSection> getCourseSectionsInSemester(String courseId, int semesterId);

    /**
     * If there is no Course about specific id, throw EntityNotFoundException.
     * @param sectionId if the key is non-existent, please throw an EntityNotFoundException.
     */
    Course getCourseBySection(int sectionId);

    /**
     * @param sectionId the id of {@code CourseSection}
     *                  if the key is non-existent, please throw an EntityNotFoundException.
     */
    List<CourseSectionClass> getCourseSectionClasses(int sectionId);

    /**
     * @param classId if the key is non-existent, please throw an EntityNotFoundException.
     */
    CourseSection getCourseSectionByClass(int classId);

    /**
     * @param courseId  if the key is non-existent, please throw an EntityNotFoundException.
     * @param semesterId if the key is non-existent, please throw an EntityNotFoundException.
     */
    List<Student> getEnrolledStudentsInSemester(String courseId, int semesterId);
}
