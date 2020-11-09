package seedu.ezmanager.commands.member;

import seedu.ezmanager.util.EzExceptions;
import seedu.ezmanager.commands.Command;
import seedu.ezmanager.member.TeamMember;
import seedu.ezmanager.project.Project;
import seedu.ezmanager.ui.Ui;

import java.util.ArrayList;
import java.util.HashMap;

import static seedu.ezmanager.parser.Parser.getHashValue;

/**
 * Add a new TeamMember object to the program.
 */
public class TeamMemberAddCommand extends Command {

    private String name;
    HashMap<String, String> params;

    public TeamMemberAddCommand(HashMap<String, String> params) throws EzExceptions {
        this.params = params;
        this.parse();
    }

    /**
     * Parse user parameter inputs for execution.
     *
     * @throws EzExceptions if there are missing parameter values.
     */
    public void parse() throws EzExceptions {
        name = getHashValue(params, "n");
    }

    /**
     * Adds a new TeamMember object to the ArrayList of TeamMember in the program.
     * Prints member added message.
     *
     * @param projects ArrayList of Projects.
     * @param teamMembers ArrayList of TeamMembers in the program.
     * @return Print member added message.
     */
    public String executeCommand(ArrayList<Project> projects, ArrayList<TeamMember> teamMembers) {
        TeamMember newMember = new TeamMember(name);
        teamMembers.add(newMember);
        return Ui.printMemberAddedMessage(name);
    }

    public Boolean isExit() {
        return false;
    }
}
