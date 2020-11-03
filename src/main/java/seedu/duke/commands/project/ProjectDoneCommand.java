package seedu.duke.commands.project;

import seedu.duke.DukeExceptions;
import seedu.duke.commands.Command;
import seedu.duke.member.TeamMember;
import seedu.duke.project.Project;
import seedu.duke.task.Task;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.HashMap;

import static seedu.duke.Parser.getHashValue;

public class ProjectDoneCommand extends Command {

    private int projectIndex;
    HashMap<String, String> params;

    public ProjectDoneCommand(HashMap<String, String> params, int projectIndex) throws DukeExceptions {
        this.params = params;
        this.parse();
    }

    public void parse() throws DukeExceptions {
        try {
            projectIndex = Integer.parseInt(getHashValue(params, "p")) - 1;
        } catch (NumberFormatException e) {
            throw new DukeExceptions("invalidProjectID");
        }

    }

    public String executeCommand(ArrayList<Project> projects,
                                 ArrayList<TeamMember> members) throws DukeExceptions {
        try {
            Project project = projects.get(projectIndex);
            project.markAsDone();
            return Ui.printProjectDoneMessage(project.getProjectName());
        }  catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeExceptions("invalidProjectID");
        }
    }

    public Boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ProjectDoneCommand) {
            ProjectDoneCommand taskCommand = (ProjectDoneCommand) obj;
            return (this.projectIndex == taskCommand.projectIndex);
        } else {
            return false;
        }
    }
}
