package seedu.ezmanager;

import org.junit.jupiter.api.Test;

import seedu.ezmanager.member.TeamMember;
import seedu.ezmanager.project.Project;
import seedu.ezmanager.task.Task;
import seedu.ezmanager.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UiTest {
    static Ui ui = new Ui();

    /**
     * Display friendly message when there are no members assigned
     * or no tasks in the project.
     */
    @Test
    void emptyProjectView() throws EzExceptions {
        Project projectOne = new Project("Project One");
        String actualOutput = Ui.projectViewMessage(projectOne);
        String expectedOutput = "Project \"Project One\"\n"
                + "\n"
                + "Description:\n"
                + "<project description empty>\n"
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
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Check output when a new task has been added.
     */
    @Test
    void newTaskAdded() throws EzExceptions {
        Project projectOne = new Project("Project One");
        Task newTask = new Task("First Task");
        projectOne.addTask(newTask);
        String actualOutput = Ui.projectViewMessage(projectOne);
        String expectedOutput = "Project \"Project One\"\n"
                + "\n"
                + "Description:\n"
                + "<project description empty>\n"
                + "\n"
                + " ---------------------\n"
                + "| TASK LIST           |\n"
                + " ---------------------\n"
                + "Index  Status   Description        Deadline        Priority      Estimated Hrs     Actual Hrs   | "
                + "Members Involved\n"
                + "------------------------------------------------------------------------------------------------|-"
                + "-----------------\n"
                + "1      (N)      First Task         -               -             -                 -            "
                + "| -\n"
                + "\n"
                + " \n"
                + "\n"
                + " ---------------------\n"
                + "| MEMBERS LIST        |\n"
                + " ---------------------\n"
                + "No team members have been assigned to this project.";
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Check alignment when all fields are filled.
     */
    @Test
    void checkAlignmentWhenPopulated() throws EzExceptions {
        Project projectOne = new Project("Project One");
        Task newTask = new Task("First Task");
        projectOne.addTask(newTask);
        newTask.addDeadline(LocalDate.parse("2020-10-20"));
        String actualOutput = Ui.projectViewMessage(projectOne);
        String expectedOutput = "Project \"Project One\"\n"
                + "\n"
                + "Description:\n"
                + "<project description empty>\n"
                + "\n"
                + " ---------------------\n"
                + "| TASK LIST           |\n"
                + " ---------------------\n"
                + "Index  Status   Description        Deadline        Priority      Estimated Hrs     Actual Hrs   "
                + "| Members Involved\n"
                + "------------------------------------------------------------------------------------------------"
                + "|------------------\n"
                + "1      (N)      First Task         20/10/2020      -             -                 -            "
                + "| -\n"
                + "\n"
                + " \n"
                + "\n"
                + " ---------------------\n"
                + "| MEMBERS LIST        |\n"
                + " ---------------------\n"
                + "No team members have been assigned to this project.";
        assertEquals(expectedOutput, actualOutput);

        newTask.setPriority(3);
        actualOutput = Ui.projectViewMessage(projectOne);
        expectedOutput = "Project \"Project One\"\n"
                + "\n"
                + "Description:\n"
                + "<project description empty>\n"
                + "\n"
                + " ---------------------\n"
                + "| TASK LIST           |\n"
                + " ---------------------\n"
                + "Index  Status   Description        Deadline        Priority      Estimated Hrs     Actual Hrs   "
                + "| Members Involved\n"
                + "------------------------------------------------------------------------------------------------"
                + "|------------------\n"
                + "1      (N)      First Task         20/10/2020      3             -                 -            "
                + "| -\n"
                + "\n"
                + " \n"
                + "\n"
                + " ---------------------\n"
                + "| MEMBERS LIST        |\n"
                + " ---------------------\n"
                + "No team members have been assigned to this project.";
        assertEquals(expectedOutput, actualOutput);

        newTask.addEstimate(80);
        actualOutput = Ui.projectViewMessage(projectOne);
        expectedOutput = "Project \"Project One\"\n"
                + "\n"
                + "Description:\n"
                + "<project description empty>\n"
                + "\n"
                + " ---------------------\n"
                + "| TASK LIST           |\n"
                + " ---------------------\n"
                + "Index  Status   Description        Deadline        Priority      Estimated Hrs     Actual Hrs   "
                + "| Members Involved\n"
                + "------------------------------------------------------------------------------------------------"
                + "|------------------\n"
                + "1      (N)      First Task         20/10/2020      3             1.3               -            "
                + "| -\n"
                + "\n"
                + " \n"
                + "\n"
                + " ---------------------\n"
                + "| MEMBERS LIST        |\n"
                + " ---------------------\n"
                + "No team members have been assigned to this project.";
        assertEquals(expectedOutput, actualOutput);

        TeamMember memberOne = new TeamMember("Sam");
        projectOne.addTeamMemberToProject(memberOne);
        newTask.setMember(memberOne);

        actualOutput = Ui.projectViewMessage(projectOne);
        expectedOutput = "Project \"Project One\"\n"
                + "\n"
                + "Description:\n"
                + "<project description empty>\n"
                + "\n"
                + " ---------------------\n"
                + "| TASK LIST           |\n"
                + " ---------------------\n"
                + "Index  Status   Description        Deadline        Priority      Estimated Hrs     Actual Hrs   "
                + "| Members Involved\n"
                + "------------------------------------------------------------------------------------------------"
                + "|------------------\n"
                + "1      (N)      First Task         20/10/2020      3             1.3               -            "
                + "| Sam\n"
                + "\n"
                + " \n"
                + "\n"
                + " ---------------------\n"
                + "| MEMBERS LIST        |\n"
                + " ---------------------\n"
                + "1. Sam\n";
        assertEquals(expectedOutput, actualOutput);

        TeamMember memberTwo = new TeamMember("Chris");
        projectOne.addTeamMemberToProject(memberTwo);
        newTask.setMember(memberTwo);

        actualOutput = Ui.projectViewMessage(projectOne);
        expectedOutput = "Project \"Project One\"\n"
                + "\n"
                + "Description:\n"
                + "<project description empty>\n"
                + "\n"
                + " ---------------------\n"
                + "| TASK LIST           |\n"
                + " ---------------------\n"
                + "Index  Status   Description        Deadline        Priority      Estimated Hrs     Actual Hrs   "
                + "| Members Involved\n"
                + "------------------------------------------------------------------------------------------------"
                + "|------------------\n"
                + "1      (N)      First Task         20/10/2020      3             1.3               -            "
                + "| Sam, Chris\n"
                + "\n"
                + " \n"
                + "\n"
                + " ---------------------\n"
                + "| MEMBERS LIST        |\n"
                + " ---------------------\n"
                + "1. Sam\n"
                + "2. Chris\n";
        assertEquals(expectedOutput, actualOutput);

        newTask.markAsDone();
        actualOutput = Ui.projectViewMessage(projectOne);
        expectedOutput = "Project \"Project One\"\n"
                + "\n"
                + "Description:\n"
                + "<project description empty>\n"
                + "\n"
                + " ---------------------\n"
                + "| TASK LIST           |\n"
                + " ---------------------\n"
                + "Index  Status   Description        Deadline        Priority      Estimated Hrs     Actual Hrs   "
                + "| Members Involved\n"
                + "------------------------------------------------------------------------------------------------"
                + "|------------------\n"
                + "1      (Y)      First Task         20/10/2020      3             1.3               -            "
                + "| Sam, Chris\n"
                + "\n"
                + " \n"
                + "\n"
                + " ---------------------\n"
                + "| MEMBERS LIST        |\n"
                + " ---------------------\n"
                + "1. Sam\n"
                + "2. Chris\n";
        assertEquals(expectedOutput, actualOutput);

        newTask.addActual(120 + 40);
        actualOutput = Ui.projectViewMessage(projectOne);
        expectedOutput = "Project \"Project One\"\n"
                + "\n"
                + "Description:\n"
                + "<project description empty>\n"
                + "\n"
                + " ---------------------\n"
                + "| TASK LIST           |\n"
                + " ---------------------\n"
                + "Index  Status   Description        Deadline        Priority      Estimated Hrs     Actual Hrs   "
                + "| Members Involved\n"
                + "------------------------------------------------------------------------------------------------"
                + "|------------------\n"
                + "1      (Y)      First Task         20/10/2020      3             1.3               2.7          "
                + "| Sam, Chris\n"
                + "\n"
                + " \n"
                + "\n"
                + " ---------------------\n"
                + "| MEMBERS LIST        |\n"
                + " ---------------------\n"
                + "1. Sam\n"
                + "2. Chris\n";
        assertEquals(expectedOutput, actualOutput);
    }


}