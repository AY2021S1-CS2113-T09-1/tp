package seedu.ezmanager.commands.project;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.ezmanager.util.EzExceptions;
import seedu.ezmanager.member.TeamMember;
import seedu.ezmanager.project.Project;

import java.time.LocalDate;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ProjectListTest {
    static ArrayList<Project> projects;
    static ArrayList<TeamMember> teamMembers;

    @BeforeAll
    static void createProjectList() {
        projects = new ArrayList<>();
        Project projectOne = new Project("Drink Water");
        Project projectTwo = new Project("Fire");
        Project projectThree = new Project("CS2113 Tutorial");
        projects.add(projectOne);
        projects.add(projectTwo);
        projects.add(projectThree);
        projectOne.addProjectDeadline(LocalDate.parse("2020-12-31"));
        projectThree.addProjectDeadline(LocalDate.parse("2019-01-01"));
    }

    @Test
    void executeCommand_differentDeadline_listOfSortedProjectsByDeadline() throws EzExceptions {
        ProjectListCommand command = new ProjectListCommand();
        String expectedOutput = "List of Projects:\n"
                + "     1.CS2113 Tutorial (2019-01-01) \n"
                + "     2.Drink Water (2020-12-31) \n"
                + "     3.Fire";
        String actualOutput = command.executeCommand(projects, teamMembers);
        assertEquals(expectedOutput, actualOutput);
    }

}
