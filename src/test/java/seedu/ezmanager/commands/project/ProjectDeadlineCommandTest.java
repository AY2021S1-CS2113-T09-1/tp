package seedu.ezmanager.commands.project;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.ezmanager.util.EzExceptions;
import seedu.ezmanager.member.TeamMember;
import seedu.ezmanager.project.Project;
import seedu.ezmanager.ui.Ui;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProjectDeadlineCommandTest {
    static ArrayList<Project> projects;
    static ArrayList<TeamMember> teamMembers;
    static Ui ui = new Ui();
    static HashMap<String, String> params;

    @BeforeAll
    static void createProjectList() {
        projects = new ArrayList<>();
        Project projectOne = new Project("Drink Water");
        Project projectTwo = new Project("Fire");
        Project projectThree = new Project("CS2113 Tutorial");
        projects.add(projectOne);
        projects.add(projectTwo);
        projects.add(projectThree);
        teamMembers = new ArrayList<>();
        params = new HashMap<>();
    }

    /**
     * Test: Add deadline to the second project with valid project ID and deadline format.
     */
    @Test
    void executeCommand_validProjectId_addDeadlineToProject() throws EzExceptions {
        params.put("p", "1");
        params.put("d", "2030-12-31");
        ProjectDeadlineCommand command = new ProjectDeadlineCommand(params);
        String expectedOutput = "Deadline 31/12/2030 added to Project Drink Water\n"
                + "\n"
                + "EZ Manager Home View\n"
                + "\n"
                + " ----------------------\n"
                + "| PROJECT LIST         |\n"
                + " ----------------------\n"
                + "\n"
                + "Index   Status   Project Name             Project Description                "
                + "Deadline     Tasks Completed     Remarks\n"
                + "-----------------------------------------------------------------------------"
                + "-------------------------------------------------------------------------\n"
                + "1.      N        Drink Water              -                                  "
                + "31/12/2030   0/0                 -\n"
                + "2.      N        Fire                     -                                  "
                + "-            0/0                 -\n"
                + "3.      N        CS2113 Tutorial          -                                  "
                + "-            0/0                 -\n"
                + "\n"
                + " ----------------------\n"
                + "| MEMBERS LIST         |\n"
                + " ----------------------\n"
                + "\n"
                + "Index   Member Name                   Projects Involved        Hours spent across tasks\n"
                + "---------------------------------------------------------------------------------------";
        String actualOutput = command.executeCommand(projects, teamMembers);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void executeCommand_invalidProjectId_exceptionThrown() throws EzExceptions {
        params.put("p", "-5");
        params.put("d", "2020-12-31");
        ProjectDeadlineCommand command = new ProjectDeadlineCommand(params);
        String expectedOutput = "Project ID does not exist!";
        Throwable actualOutputException = assertThrows(EzExceptions.class, () -> {
            command.executeCommand(projects, teamMembers);
        });
        assertEquals(expectedOutput, actualOutputException.toString());
    }

    @Test
    void executeCommand_invalidDeadlineFormat_addDeadlineToProject() throws EzExceptions {
        params.put("p", "2");
        params.put("d", "2020-31-31");
        String expectedOutput = "Date must be specified in format YYYY-MM-DD";
        Throwable actualOutputException = assertThrows(EzExceptions.class, () -> {
            ProjectDeadlineCommand command = new ProjectDeadlineCommand(params);
        });
        assertEquals(expectedOutput, actualOutputException.toString());
    }

}
