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

public class TaskDoneCommand extends Command {

    private int projectIndex;
    private int taskIndex;
    HashMap<String, String> params;

    public TaskDoneCommand(HashMap<String, String> params, int projectIndex) throws EzExceptions {
        assert projectIndex >= 0 : "projectIndex must be positive integer!";
        this.params = params;
        this.projectIndex = projectIndex;
        this.parse();
    }

    public void parse() throws EzExceptions {
        try {
            taskIndex = Integer.parseInt(getHashValue(params, "t")) - 1;
        } catch (NumberFormatException e) {
            throw new EzExceptions("invalidTaskID");
        }

    }

    public String executeCommand(ArrayList<Project> projects,
                                 ArrayList<TeamMember> teamMembers) throws EzExceptions {
        Project project = projects.get(projectIndex);
        try {
            Task selectedTask = project.getTask(taskIndex);
            selectedTask.markAsDone();
            return Ui.printTaskDoneMessage(selectedTask.toString());
        }  catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new EzExceptions("invalidTaskID");
        }
    }

    public Boolean isExit() {
        return false;
    }

}
