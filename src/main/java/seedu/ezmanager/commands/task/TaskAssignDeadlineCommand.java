//@@author thatseant

package seedu.ezmanager.commands.task;

import seedu.ezmanager.util.EzExceptions;
import seedu.ezmanager.util.EzLogger;
import seedu.ezmanager.commands.Command;
import seedu.ezmanager.member.TeamMember;
import seedu.ezmanager.project.Project;
import seedu.ezmanager.task.Task;
import seedu.ezmanager.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

import static seedu.ezmanager.parser.Parser.getHashValue;
import static seedu.ezmanager.util.Util.DATE_KEY;
import static seedu.ezmanager.util.Util.EMPTY_PROJECT_LIST;
import static seedu.ezmanager.util.Util.INVALID_TASK_ID;
import static seedu.ezmanager.util.Util.TASK_INDEX_KEY;
import static seedu.ezmanager.util.Util.USER_JAVA_INDEX_DIFF;
import static seedu.ezmanager.util.Util.WRONG_DATE_FORMAT;

public class TaskAssignDeadlineCommand extends Command {
    private int projectIndex;
    private int taskIndex;
    private LocalDate date;
    HashMap<String, String> params;

    public TaskAssignDeadlineCommand(HashMap<String, String> params, int projectIndex) throws EzExceptions {
        this.params = params;
        this.projectIndex = projectIndex;
        this.parse();
    }

    public void parse() throws EzExceptions {
        try {
            taskIndex = Integer.parseInt(getHashValue(params, TASK_INDEX_KEY)) - USER_JAVA_INDEX_DIFF;
            date = LocalDate.parse(getHashValue(params, DATE_KEY));
        } catch (NumberFormatException e) {
            throw new EzExceptions(INVALID_TASK_ID);
        } catch (StringIndexOutOfBoundsException | DateTimeParseException e) {
            EzLogger.log(Level.WARNING, "Wrong Date Format: " + date);
            throw new EzExceptions(WRONG_DATE_FORMAT);
        }
    }

    public String executeCommand(ArrayList<Project> projects,
                                 ArrayList<TeamMember> teamMembers) throws EzExceptions {
        EzLogger.log(Level.INFO, "Executing Command");
        if (projects.size() == 0) {
            throw new EzExceptions(EMPTY_PROJECT_LIST);
        }
        try {
            Project project = projects.get(projectIndex);
            Task task = project.getTaskList().get(taskIndex);
            EzLogger.log(Level.INFO, "Task Retrieved");
            task.addDeadline(date);
            EzLogger.log(Level.INFO, "Deadline added to task.");
            return Ui.printTaskDeadlineMessage(date, task.getDescription());
        } catch (IndexOutOfBoundsException e) {
            EzLogger.log(Level.WARNING, "Task Not Found.");
            throw new EzExceptions(INVALID_TASK_ID);
        }
    }

    public Boolean isExit() {
        return false;
    }
}
