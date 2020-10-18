package seedu.duke.commands.task;

import seedu.duke.Duke;
import seedu.duke.DukeExceptions;
import seedu.duke.commands.Command;
import seedu.duke.commands.project.ProjectSelectCommand;
import seedu.duke.project.Project;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.HashMap;

import static seedu.duke.Parser.getHashValue;

public class TaskSelectCommand extends Command {

    private int taskIndex;
    private int projectIndex;
    HashMap<String, String> params;

    public TaskSelectCommand(HashMap<String, String> params, int projectIndex)
            throws DukeExceptions {
        this.params = params;
        this.projectIndex = projectIndex;
        this.parse();
    }

    public void parse() throws DukeExceptions {
        try {
            taskIndex = Integer.parseInt(getHashValue(params, "t")) - 1;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeExceptions("invalidTaskID");
        }
    }

    public String executeCommand(ArrayList<Project> projects) throws DukeExceptions {
        if (projects.size() == 0) {
            throw new DukeExceptions("emptyProjectList");
        }
        String selectedTask = projects.get(projectIndex).selectTask(taskIndex);
        return Ui.printTaskSelectedMessage(selectedTask);
    }

    public Boolean isExit() {
        return false;
    }

}
