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

public class TaskEditCommand extends Command {

    private int taskIndex;
    private String taskName;
    private int projectIndex;
    HashMap<String, String> params;

    public TaskEditCommand(HashMap<String, String> params, int projectIndex)
            throws EzExceptions {
        this.params = params;
        this.projectIndex = projectIndex;
        this.parse();
    }

    public void parse() throws EzExceptions {
        try {
            taskIndex = Integer.parseInt(getHashValue(params, "t")) - 1;
            taskName = getHashValue(params, "n");
        } catch (NumberFormatException e) {
            throw new EzExceptions("invalidTaskID");
        }
    }

    public String executeCommand(ArrayList<Project> projects, ArrayList<TeamMember> teamMembers) throws EzExceptions {
        if (projects.size() == 0) {
            throw new EzExceptions("emptyProjectList");
        }
        try {
            Task selectedTask = projects.get(projectIndex).getTask(taskIndex);
            String oldTaskName = selectedTask.getTaskDescription();
            selectedTask.setTaskDescription(taskName);
            return Ui.printTaskNameUpdatedMessage(oldTaskName, taskName);
        } catch (IndexOutOfBoundsException e) {
            throw new EzExceptions("invalidTaskID");
        }
    }

    public Boolean isExit() {
        return false;
    }
}
