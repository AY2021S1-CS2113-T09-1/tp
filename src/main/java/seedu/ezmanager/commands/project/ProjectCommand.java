package seedu.ezmanager.commands.project;

import seedu.ezmanager.util.EzExceptions;
import seedu.ezmanager.commands.Command;
import seedu.ezmanager.member.TeamMember;
import seedu.ezmanager.project.Project;
import seedu.ezmanager.ui.Ui;

import java.util.ArrayList;
import java.util.HashMap;

import static seedu.ezmanager.parser.Parser.getHashValue;

public class ProjectCommand extends Command {

    private String description;
    HashMap<String, String> params;

    public ProjectCommand(HashMap<String, String> params) throws EzExceptions {
        this.params = params;
        this.parse();
    }

    public void parse() throws EzExceptions {
        this.description = getHashValue(params, "n");
    }

    public String executeCommand(ArrayList<Project> projects, ArrayList<TeamMember> teamMembers) {
        Project newProject = new Project(description);
        projects.add(newProject);
        return Ui.printProjectCreatedMessage(newProject.getProjectName());
    }

    public Boolean isExit() {
        return false;
    }
}
