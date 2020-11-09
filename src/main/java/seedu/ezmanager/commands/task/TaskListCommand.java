package seedu.ezmanager.commands.task;

import seedu.ezmanager.util.EzExceptions;
import seedu.ezmanager.commands.Command;
import seedu.ezmanager.member.TeamMember;
import seedu.ezmanager.project.Project;
import java.util.ArrayList;

import seedu.ezmanager.ui.Ui;

/**
 * Prints a list of existing tasks.
 */
public class TaskListCommand extends Command {
    private int projectIndex;

    public TaskListCommand(int projectIndex) {
        this.projectIndex = projectIndex;
    }

    public String executeCommand(ArrayList<Project> projects,
                                 ArrayList<TeamMember> teamMembers) throws EzExceptions {
        Project selectedProject = projects.get(projectIndex);
        String projectView = Ui.projectViewMessage(selectedProject);
        return projectView;
    }

    public Boolean isExit() {
        return false;
    }
}
