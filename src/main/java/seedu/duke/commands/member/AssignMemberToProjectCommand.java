package seedu.duke.commands.member;

import java.lang.reflect.Array;
import java.util.HashMap;
import seedu.duke.DukeExceptions;
import seedu.duke.commands.Command;
import seedu.duke.member.TeamMember;
import seedu.duke.project.Project;

import static seedu.duke.Parser.getHashValue;
import java.util.ArrayList;

public class AssignMemberToProjectCommand extends Command {
    private int memberIndex;
    private int projectIndex;
    private boolean isHomeView;
    private HashMap<String, String> paramsList;

    public AssignMemberToProjectCommand(HashMap<String,String> paramsList, boolean isHomeView)
            throws DukeExceptions {
        this.paramsList = paramsList;
        this.isHomeView = isHomeView;
        parse();
    }

    public void parse() throws DukeExceptions {
        if (isHomeView) {
            try {
                projectIndex = Integer.parseInt(getHashValue(paramsList,"p")) - 1;
                memberIndex = Integer.parseInt(getHashValue(paramsList,"m")) - 1;
            } catch (NumberFormatException e) {
                throw new DukeExceptions("paramValueIncorrect");
            }
        } else {
            throw new DukeExceptions("default");
        }
    }

    @Override
    public String executeCommand(ArrayList<Project> projects,
                                 ArrayList<TeamMember> teamMembers) throws DukeExceptions {
        if (memberIndex >= teamMembers.size() || memberIndex < 0) {
            throw new DukeExceptions("invalidTeamMemberID");
        }
        if (projectIndex >= projects.size() || projectIndex < 0) {
            throw new DukeExceptions("invalidProjectID");
        }

        TeamMember requiredMember = teamMembers.get(memberIndex);
        requiredMember.assignProject(projects.get(projectIndex));
        projects.get(projectIndex).addTeamMemberToProject(requiredMember);
        return requiredMember + " assigned to Project \"" + projects.get(projectIndex).getProjectName() + "\"";
    }

    public Boolean isExit() {
        return false;
    }
}
