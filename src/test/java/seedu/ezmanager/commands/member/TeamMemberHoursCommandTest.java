//@@author thatseant

package seedu.ezmanager.commands.member;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.ezmanager.util.EzExceptions;
import seedu.ezmanager.member.TeamMember;
import seedu.ezmanager.project.Project;
import seedu.ezmanager.task.Task;
import seedu.ezmanager.ui.Ui;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TeamMemberHoursCommandTest {
    static ArrayList<Project> projects;
    static ArrayList<TeamMember> teamMembers = new ArrayList<>();
    static Ui ui = new Ui();

    @BeforeAll
    static void createMemberList() {
        TeamMember member1 = new TeamMember("Member 1");
        TeamMember member2 = new TeamMember("Member 2");
        TeamMember member3 = new TeamMember("Member 3");

        teamMembers.add(member1);
        teamMembers.add(member2);
        teamMembers.add(member3);
    }

    @Test
    public void executeCommand_validMemberId_correctHours() throws EzExceptions {
        Task task1 = new Task("task1");
        task1.addActual(274);

        Task task2 = new Task("task2");
        task2.addActual(128);

        Task task3 = new Task("task3");
        task3.addActual(45);

        Task task4 = new Task("task4");
        task4.addActual(290);

        TeamMember member3 = teamMembers.get(2);
        member3.setTask(task1);
        member3.setTask(task2);
        member3.setTask(task3);
        member3.setTask(task4);

        HashMap<String, String> params = new HashMap<>();
        params.put("m","3");

        TeamMemberHoursCommand assignProject = new TeamMemberHoursCommand(params,-1);
        String expectedOutput = "Member 3 worked for 12.3 hours.";
        String actualOutput = assignProject.executeCommand(projects,teamMembers);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void executeCommand_invalidMemberId_assignMemberToProject() throws EzExceptions {
        HashMap<String,String> params = new HashMap<>();
        params.put("m","7");
        TeamMemberHoursCommand assignProject = new TeamMemberHoursCommand(params,-1);
        String expectedOutput = "The member ID entered does not exist";
        Throwable actualOutputException = assertThrows(EzExceptions.class, () -> {
            assignProject.executeCommand(projects,teamMembers);
        });
        assertEquals(expectedOutput,actualOutputException.toString());
    }
}