package seedu.ezmanager.commands.member;

import seedu.ezmanager.util.EzExceptions;
import seedu.ezmanager.commands.Command;
import seedu.ezmanager.member.TeamMember;
import seedu.ezmanager.project.Project;
import seedu.ezmanager.task.Task;
import seedu.ezmanager.ui.Ui;

import java.util.ArrayList;
import java.util.HashMap;

import static seedu.ezmanager.parser.Parser.getHashValue;
import static seedu.ezmanager.util.Util.INVALID_MEMBER_ID;
import static seedu.ezmanager.util.Util.MEMBER_INDEX_KEY;
import static seedu.ezmanager.util.Util.MINUTES_IN_HOUR_DOUBLE;
import static seedu.ezmanager.util.Util.USER_JAVA_INDEX_DIFF;

public class TeamMemberHoursCommand extends Command {

    private int memberIndex;
    HashMap<String, String> params;

    public TeamMemberHoursCommand(HashMap<String, String> params, int projectIndex)
            throws EzExceptions {
        assert projectIndex == -1 : "projectIndex must be -1";
        this.params = params;
        this.parse();
    }

    public void parse() throws EzExceptions {
        try {
            memberIndex = Integer.parseInt(getHashValue(params, MEMBER_INDEX_KEY)) - USER_JAVA_INDEX_DIFF;
        } catch (NumberFormatException e) {
            throw new EzExceptions(INVALID_MEMBER_ID);
        }
    }

    public String executeCommand(ArrayList<Project> projects, ArrayList<TeamMember> members) throws EzExceptions {
        try {
            double hoursWorked = 0;
            assert memberIndex >= 0 : memberIndex;
            TeamMember member = members.get(memberIndex);
            for (int i = 0; i < member.getTasks().size(); i++) {
                Task task = member.getTasks().get(i);
                hoursWorked += task.getActual() / MINUTES_IN_HOUR_DOUBLE;
            }
            return Ui.printHoursWorkedMessage(member.getName(), hoursWorked);
        } catch (IndexOutOfBoundsException e) {
            throw new EzExceptions(INVALID_MEMBER_ID);
        }
    }

    public Boolean isExit() {
        return false;
    }

}
