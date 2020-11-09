//@@author thatseant

package seedu.ezmanager.commands.member;

import java.util.HashMap;
import seedu.ezmanager.util.EzExceptions;
import seedu.ezmanager.util.EzLogger;
import seedu.ezmanager.commands.Command;
import seedu.ezmanager.member.TeamMember;
import seedu.ezmanager.project.Project;
import seedu.ezmanager.ui.Ui;

import static seedu.ezmanager.parser.Parser.getHashValue;
import static seedu.ezmanager.util.Util.INDEX_NON_INTEGER;
import static seedu.ezmanager.util.Util.INVALID_PROJECT_ID;
import static seedu.ezmanager.util.Util.INVALID_TEAM_MEMBER_ID;
import static seedu.ezmanager.util.Util.MEMBER_INDEX_KEY;
import static seedu.ezmanager.util.Util.PROJECT_INDEX_KEY;
import static seedu.ezmanager.util.Util.USER_JAVA_INDEX_DIFF;

import java.util.ArrayList;
import java.util.logging.Level;

/**
 * Assign TeamMember object to Project object.
 */
public class AssignMemberToProjectCommand extends Command {
    private int memberIndex;
    private int projectIndex;
    private HashMap<String, String> paramsList;

    public AssignMemberToProjectCommand(HashMap<String,String> paramsList, int projectIndex)
            throws EzExceptions {
        this.paramsList = paramsList;
        assert projectIndex == -1 : "projectIndex must be -1";
        parse();
    }

    /**
     * Parse user parameter inputs for execution.
     *
     * @throws EzExceptions Invalid index when parameter values entered is not an integer.
     */
    public void parse() throws EzExceptions {
        try {
            projectIndex = Integer.parseInt(getHashValue(paramsList, PROJECT_INDEX_KEY)) - USER_JAVA_INDEX_DIFF;
            memberIndex = Integer.parseInt(getHashValue(paramsList, MEMBER_INDEX_KEY)) - USER_JAVA_INDEX_DIFF;
        } catch (NumberFormatException e) {
            EzLogger.log(Level.WARNING, "Index is not an integer.");
            throw new EzExceptions(INDEX_NON_INTEGER);
        }
    }

    /**
     * Assign specified member to specified project.
     * 1. Add TeamMember object to ArrayList of TeamMember in specified Project.
     * 2. Add Project object to ArrayList of Project in specified TeamMember.
     * 3. Prints member assigned to project message.
     *
     * @param projects ArrayList of Projects.
     * @param teamMembers ArrayList of TeamMembers in the program.
     * @return Print member assigned to project message.
     * @throws EzExceptions Invalid TeamMember ID if when parameter values provided is outside
     *     the range of the TeamMember list.
     */
    @Override
    public String executeCommand(ArrayList<Project> projects,
                                 ArrayList<TeamMember> teamMembers) throws EzExceptions {
        EzLogger.log(Level.INFO, "Executing Command");
        if (memberIndex >= teamMembers.size() || memberIndex < 0) {
            throw new EzExceptions(INVALID_TEAM_MEMBER_ID);
        }
        if (projectIndex >= projects.size() || projectIndex < 0) {
            throw new EzExceptions(INVALID_PROJECT_ID);
        }

        TeamMember teamMember = teamMembers.get(memberIndex);
        EzLogger.log(Level.INFO, "Team Member Retrieved");
        Project projectToAdd = projects.get(projectIndex);
        EzLogger.log(Level.INFO, "Project Retrieved");
        teamMember.assignProject(projectToAdd);
        projectToAdd.addTeamMemberToProject(teamMember);
        EzLogger.log(Level.INFO, "Team Member Assigned to Project");
        return Ui.printMemberAssignedToProjectMessage(teamMember.getName(), projectToAdd.getProjectName());
    }

    public Boolean isExit() {
        return false;
    }
}
