package seedu.ezmanager.commands.project;

import seedu.ezmanager.util.EzExceptions;
import seedu.ezmanager.commands.Command;
import seedu.ezmanager.member.TeamMember;
import seedu.ezmanager.project.Project;
import seedu.ezmanager.ui.Ui;

import java.util.ArrayList;
import java.util.HashMap;

import static seedu.ezmanager.parser.Parser.getHashValue;

public class ProjectDeleteCommand extends Command {

    private int projectIndex;
    HashMap<String, String> params;

    public ProjectDeleteCommand(HashMap<String, String> params)
            throws EzExceptions {
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

    public String executeCommand(ArrayList<Project> projects, ArrayList<TeamMember> teamMembers) throws EzExceptions {
        if (projects.size() == 0) {
            throw new EzExceptions("emptyProjectList");
        }
        try {
            Project projectToBeDeleted = projects.get(projectIndex);
            projects.remove(projectIndex);
            ArrayList<TeamMember> membersInProjectToBeDeleted = projectToBeDeleted.getTeamMembers();
            for (TeamMember member : membersInProjectToBeDeleted) {
                member.getAssignedProjects().remove(projectToBeDeleted);
            }
            return Ui.printProjectDeletedMessage(projectToBeDeleted);
        } catch (IndexOutOfBoundsException e) {
            throw new EzExceptions("invalidProjectID");
        }
    }

    public Boolean isExit() {
        return false;
    }
}
