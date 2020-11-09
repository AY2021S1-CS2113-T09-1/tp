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

/**
 * Assign TeamMember object to Task object.
 */
public class TeamMemberAssignToTaskCommand extends Command {

    private int taskIndex;
    private int projectIndex;
    private int memberIndex;
    HashMap<String, String> params;

    public TeamMemberAssignToTaskCommand(HashMap<String, String> params, int projectIndex)
            throws EzExceptions {
        this.params = params;
        this.projectIndex = projectIndex;
        this.parse();
    }

    /**
     * Parse user parameter inputs for execution.
     *
     * @throws EzExceptions Invalid index when parameter values entered is not an integer.
     */
    public void parse() throws EzExceptions {
        try {
            taskIndex = Integer.parseInt(getHashValue(params, "t")) - 1;
            memberIndex = Integer.parseInt(getHashValue(params, "m")) - 1;
        } catch (NumberFormatException e) {
            throw new EzExceptions("indexNonInteger");
        }
    }

    /**
     * Assign specified member to specified task.
     * 1. Adds Task to ArrayList of Task in specified TeamMember.
     * 2. Adds TeamMember to ArrayList of TeamMember in specified Task.
     * 3. Prints member assigned to task message.
     *
     * @param projects ArrayList of Projects.
     * @param teamMembers ArrayList of TeamMembers in the program.
     * @return Print member assigned to task message.
     * @throws EzExceptions Empty Task list if the list is empty or
     *     Invalid Task ID if when parameter values provided is outside
     *     the range of the Task list.
     */
    public String executeCommand(ArrayList<Project> projects,
                                 ArrayList<TeamMember> teamMembers) throws EzExceptions {
        Task selectedTask;
        TeamMember teamMember;
        assert projectIndex >= 0 : "projectIndex should be minus one "
                + "of the current project it was selected";
        Project project = projects.get(projectIndex);
        try {
            if (project.getTaskList().isEmpty()) {
                throw new EzExceptions("emptyTaskList");
            }
            selectedTask = project.getTask(taskIndex);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new EzExceptions("invalidTaskID");
        }

        try {
            if (project.getTeamMembers().isEmpty()) {
                throw new EzExceptions("emptyTeamMembersList");
            }
            teamMember = project.getTeamMembers().get(memberIndex);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new EzExceptions("invalidTeamMemberID");
        }
        selectedTask.setMember(teamMember);
        teamMember.setTask(selectedTask);
        return Ui.printMemberAssignedToTaskMessage(teamMember.getName(), selectedTask.getTaskDescription());
    }

    public Boolean isExit() {
        return false;
    }
}
