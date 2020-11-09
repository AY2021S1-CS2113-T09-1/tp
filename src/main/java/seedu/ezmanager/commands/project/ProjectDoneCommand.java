package seedu.ezmanager.commands.project;

import seedu.ezmanager.util.EzExceptions;
import seedu.ezmanager.commands.Command;
import seedu.ezmanager.member.TeamMember;
import seedu.ezmanager.project.Project;
import seedu.ezmanager.ui.Ui;

import java.util.ArrayList;
import java.util.HashMap;

import static seedu.ezmanager.parser.Parser.getHashValue;

public class ProjectDoneCommand extends Command {

    private int projectIndex;
    HashMap<String, String> params;

    public ProjectDoneCommand(HashMap<String, String> params, int projectIndex) throws EzExceptions {
        this.params = params;
        this.parse();
    }

    public void parse() throws EzExceptions {
        try {
            projectIndex = Integer.parseInt(getHashValue(params, "p")) - 1;
        } catch (NumberFormatException e) {
            throw new EzExceptions("invalidProjectID");
        }

    }

    public String executeCommand(ArrayList<Project> projects,
                                 ArrayList<TeamMember> members) throws EzExceptions {
        try {
            Project project = projects.get(projectIndex);
            project.markAsDone();
            return Ui.printProjectDoneMessage(project.getProjectName());
        }  catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new EzExceptions("invalidProjectID");
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
