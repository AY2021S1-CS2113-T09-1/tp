package seedu.ezmanager.commands.project;

import seedu.ezmanager.util.EzExceptions;
import seedu.ezmanager.commands.Command;
import seedu.ezmanager.member.TeamMember;
import seedu.ezmanager.project.Project;
import java.util.ArrayList;
import java.util.Collections;

import seedu.ezmanager.ui.Ui;

/**
 * Prints a list of existing tasks.
 */
public class ProjectListCommand extends Command {

    public String executeCommand(ArrayList<Project> projects, ArrayList<TeamMember> teamMembers) throws EzExceptions {
        if (projects.size() == 0) {
            throw new EzExceptions("emptyProjectList");
        } else {
            try {
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
                return Ui.printProjectListMessage(projects);
            } catch (IndexOutOfBoundsException e) {
                throw new EzExceptions("invalidProjectID");
            }
        }
    }

    public Boolean isExit() {
        return false;
    }
}

