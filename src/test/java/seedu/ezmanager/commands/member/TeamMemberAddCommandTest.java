package seedu.ezmanager.commands.member;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.ezmanager.util.EzExceptions;
import seedu.ezmanager.member.TeamMember;
import seedu.ezmanager.project.Project;
import seedu.ezmanager.ui.Ui;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TeamMemberAddCommandTest {
    static ArrayList<Project> projects;
    static ArrayList<TeamMember> teamMembers;
    static Ui ui = new Ui();
    static HashMap<String, String> params;

    @BeforeAll
    static void createProjectList() {
        projects = new ArrayList<>();
        teamMembers = new ArrayList<>();
        params = new HashMap<>();
    }

    @Test
    public void executeCommand_validMemberName_memberAddedMessage() throws EzExceptions {
        params.put("n", "John Doe");
        TeamMemberAddCommand command = new TeamMemberAddCommand(params);
        String expectedOutput = Ui.printMemberAddedMessage("John Doe");
        String actualOuput = command.executeCommand(projects, teamMembers);
        assertEquals(expectedOutput, actualOuput);
    }

}