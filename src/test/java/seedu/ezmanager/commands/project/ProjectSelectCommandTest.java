package seedu.ezmanager.commands.project;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.ezmanager.util.EzExceptions;
import seedu.ezmanager.member.TeamMember;
import seedu.ezmanager.project.Project;
import seedu.ezmanager.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProjectSelectCommandTest {
    static ArrayList<TeamMember> teamMembers;
    static ArrayList<Project> projects;
    static Ui ui = new Ui();

    @BeforeAll
    static void testSetup() {
        projects = new ArrayList<>();
        Project projectOne = new Project("Project One");
        Project projectTwo = new Project("Project Two");
        Project projectThree = new Project("Project Three");
        projects.add(projectOne);
        projects.add(projectTwo);
        projects.add(projectThree);
        projectTwo.addProjectDeadline(LocalDate.parse("2020-12-31"));
    }

    @Test
    void executeCommand_validProjectIdWithDescription_additionalInformation() throws EzExceptions {
        HashMap<String, String> params = new HashMap<>();
        params.put("p","2");
        ProjectSelectCommand selectProject = new ProjectSelectCommand(params);
        String expectedOutput =  "Project \"Project Two\"\n"
                + "\n"
                + "Description:"
                + "\n"
                + "<project description empty>"
                + "\n"
                + "\n"
                + " ---------------------\n"
                + "| TASK LIST           |\n"
                + " ---------------------\n"
                + "\n"
                + "No tasks have been added to this project.\n"
                + " \n"
                + "\n"
                + " ---------------------\n"
                + "| MEMBERS LIST        |\n"
                + " ---------------------\n"
                + "No team members have been assigned to this project.";
        String actualOutput = selectProject.executeCommand(projects, teamMembers);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void executeCommand_invalidProjectIdWithDescription_additionalInformation() throws EzExceptions {
        HashMap<String, String> params = new HashMap<>();
        params.put("p","-5");
        ProjectSelectCommand selectProject = new ProjectSelectCommand(params);
        String expectedOutput = "Project ID does not exist!";
        Throwable actualOutputException = assertThrows(EzExceptions.class, () -> {
            selectProject.executeCommand(projects, teamMembers);
        });
        assertEquals(expectedOutput, actualOutputException.toString());
    }
}
