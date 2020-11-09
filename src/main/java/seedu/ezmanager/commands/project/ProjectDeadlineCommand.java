package seedu.ezmanager.commands.project;

import seedu.ezmanager.util.EzExceptions;
import seedu.ezmanager.commands.Command;
import seedu.ezmanager.member.TeamMember;
import seedu.ezmanager.project.Project;
import seedu.ezmanager.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import static seedu.ezmanager.parser.Parser.getHashValue;

public class ProjectDeadlineCommand extends Command {
    private int projectIndex;
    private LocalDate date;
    HashMap<String, String> params;

    public ProjectDeadlineCommand(HashMap<String, String> params) throws EzExceptions {
        this.params = params;
        this.parse();
    }

    public void parse() throws EzExceptions {
        try {
            projectIndex = Integer.parseInt(getHashValue(params, "p")) - 1;
            date = LocalDate.parse(getHashValue(params, "d"));
        } catch (NumberFormatException e) {
            throw new EzExceptions("invalidProjectID");
        } catch (StringIndexOutOfBoundsException | DateTimeParseException e) {
            throw new EzExceptions("WrongDateFormat");
        }
    }

    public String executeCommand(ArrayList<Project> projects,
                                 ArrayList<TeamMember> teamMembers) throws EzExceptions {
        try {
            Project project = projects.get(projectIndex);
            project.addProjectDeadline(date);
            int projectCounter = 0;
            int i = 0;
            while (projectCounter < projects.size()) {
                if (projects.get(i).getProjectDeadline() == null) {
                    Project projectWithNullDeadline = projects.get(i);
                    projects.remove(i);
                    projects.add(projectWithNullDeadline);
                } else {
                    i++;
                }
                projectCounter++;
            }
            Collections.sort(projects);
            return Ui.printProjectDeadlineAddedMessage(projects, project, date, teamMembers);
        } catch (IndexOutOfBoundsException e) {
            throw new EzExceptions("invalidProjectID");
        }
    }

    public Boolean isExit() {
        return false;
    }

}
