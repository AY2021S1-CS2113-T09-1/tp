package seedu.ezmanager.commands.project;

import seedu.ezmanager.util.EzExceptions;
import seedu.ezmanager.commands.Command;
import seedu.ezmanager.member.TeamMember;
import seedu.ezmanager.project.Project;
import seedu.ezmanager.ui.Ui;

import java.util.ArrayList;
import java.util.HashMap;

import static seedu.ezmanager.parser.Parser.getHashValue;

public class ProjectDescriptionCommand extends Command {

    private String projectDescription;
    private int projectIndex;
    HashMap<String, String> params;

    public ProjectDescriptionCommand(HashMap<String, String> params) throws EzExceptions {
        this.params = params;
        this.parse();
    }

    public void parse() throws EzExceptions {
        projectDescription = getHashValue(params, "d");
        try {
            projectIndex = Integer.parseInt(getHashValue(params, "p")) - 1;
        } catch (NumberFormatException e) {
            throw new EzExceptions("indexNonInteger");
        }
    }

    public String executeCommand(ArrayList<Project> projects, ArrayList<TeamMember> teamMembers) throws EzExceptions {
        try {
            Project project = projects.get(projectIndex);
            project.addDescription(projectDescription);
            return Ui.printProjectDescriptionAddedMessage(project);
        } catch (IndexOutOfBoundsException e) {
            throw new EzExceptions("invalidProjectID");
        }
    }

    public Boolean isExit() {
        return false;
    }
}
