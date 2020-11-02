package seedu.duke.commands.project;

import seedu.duke.DukeExceptions;
import seedu.duke.commands.Command;
import seedu.duke.member.TeamMember;
import seedu.duke.project.Project;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;

import static seedu.duke.Parser.getHashValue;

public class ProjectDescriptionCommand extends Command {

    private String projectDescription;
    private int projectIndex;
    HashMap<String, String> params;

    public ProjectDescriptionCommand(HashMap<String, String> params) throws DukeExceptions {
        this.params = params;
        this.parse();
    }

    public void parse() throws DukeExceptions {
        projectDescription = getHashValue(params, "d");
        try {
            projectIndex = Integer.parseInt(getHashValue(params, "p")) - 1;
        } catch (NumberFormatException e) {
            throw new DukeExceptions("paramValueIncorrect");
        }
    }

    public String executeCommand(ArrayList<Project> projects, ArrayList<TeamMember> teamMembers) throws DukeExceptions {
        try {
            Project project = projects.get(projectIndex);
            project.addDescription(projectDescription);
            return Ui.printProjectDescriptionAddedMessage(project);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeExceptions("invalidProjectID");
        }
    }

    public Boolean isExit() {
        return false;
    }
}
