//@@author thatseant

package seedu.ezmanager.commands.task;

import seedu.ezmanager.util.EzExceptions;
import seedu.ezmanager.util.EzLogger;
import seedu.ezmanager.commands.Command;
import seedu.ezmanager.member.TeamMember;
import seedu.ezmanager.project.Project;
import seedu.ezmanager.task.Task;
import seedu.ezmanager.ui.Ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

import static seedu.ezmanager.util.Util.TASK_NAME_KEY;
import static seedu.ezmanager.parser.Parser.getHashValue;

public class TaskCommand extends Command {

    private int projectIndex;
    private String description;
    HashMap<String, String> params;

    public TaskCommand(HashMap<String, String> params, int projectIndex) throws EzExceptions {
        assert projectIndex >= 0 : "projectIndex must be positive integer!";
        this.params = params;
        this.projectIndex = projectIndex;
        this.parse();
    }

    public void parse() throws EzExceptions {
        this.description = getHashValue(params, TASK_NAME_KEY);
    }

    public String executeCommand(ArrayList<Project> projects, ArrayList<TeamMember> teamMembers) {
        EzLogger.log(Level.INFO, "Executing Command");
        Project project = projects.get(projectIndex);
        EzLogger.log(Level.INFO, "Project Retrieved");
        Task newTask = new Task(description);
        EzLogger.log(Level.INFO, "Task Created");
        project.addTask(newTask);
        EzLogger.log(Level.INFO, "Task Added");
        return Ui.printTaskCreatedMessage(newTask.toString());
    }

    public Boolean isExit() {
        return false;
    }
}
