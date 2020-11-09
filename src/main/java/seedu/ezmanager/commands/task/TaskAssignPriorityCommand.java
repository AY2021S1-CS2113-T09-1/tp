package seedu.ezmanager.commands.task;

import seedu.ezmanager.util.EzExceptions;
import seedu.ezmanager.commands.Command;
import seedu.ezmanager.member.TeamMember;
import seedu.ezmanager.project.Project;
import seedu.ezmanager.task.Task;
import seedu.ezmanager.ui.Ui;

import java.util.ArrayList;
import java.util.HashMap;

import static seedu.ezmanager.parser.Parser.getHashValue;

public class TaskAssignPriorityCommand extends Command {

    private int taskIndex;
    private int projectIndex;
    private int priority;
    HashMap<String, String> params;

    public TaskAssignPriorityCommand(HashMap<String, String> params, int projectIndex)
            throws EzExceptions {
        assert projectIndex >= 0 : "projectIndex must be positive integer!";
        this.params = params;
        this.projectIndex = projectIndex;
        this.parse();
    }

    public void parse() throws EzExceptions {
        try {
            taskIndex = Integer.parseInt(getHashValue(params, "t")) - 1;
            priority = Integer.parseInt(getHashValue(params, "p"));
            if (priority < 0) {
                throw new EzExceptions("invalidPriority");
            }
        } catch (NumberFormatException e) {
            throw new EzExceptions("indexNonInteger");
        }
    }

    public String executeCommand(ArrayList<Project> projects,
                                 ArrayList<TeamMember> teamMembers) throws EzExceptions {
        if (projects.size() == 0) {
            throw new EzExceptions("emptyProjectList");
        }
        try {
            Task selectedTask = projects.get(projectIndex).getTask(taskIndex);
            selectedTask.setPriority(priority);
            return Ui.printPriorityAssignedToTaskMessage(priority, selectedTask.getTaskDescription());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new EzExceptions("invalidTaskID");
        }
    }

    public Boolean isExit() {
        return false;
    }

}
