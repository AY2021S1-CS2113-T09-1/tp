package seedu.ezmanager.commands;

import seedu.ezmanager.parser.Parser;
import seedu.ezmanager.member.TeamMember;
import seedu.ezmanager.project.Project;
import seedu.ezmanager.ui.Ui;

import java.util.ArrayList;

public class HomeCommand extends Command {

    private int projectIndex;

    public HomeCommand(int projectIndex) {
        this.projectIndex = projectIndex;
    }

    @Override
    public String executeCommand(ArrayList<Project> projects, ArrayList<TeamMember> teamMembers) {
        if (projectIndex == -1) {
            return Ui.printHomeView(projects, teamMembers);
        } else {
            Parser.setProjectIndex(-1);
            return Ui.printHomeView(projects, teamMembers);
        }
    }

    @Override
    public Boolean isExit() {
        return false;
    }
}
