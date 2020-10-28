package seedu.duke.commands.member;

import seedu.duke.commands.Command;
import seedu.duke.member.TeamMember;
import seedu.duke.project.Project;

import java.util.ArrayList;

public class TeamMembersListCommand extends Command {

    private String name;
    private boolean isHomeView;
    private int projectIndex;

    public TeamMembersListCommand(boolean isHomeView, int projectIndex) {
        this.isHomeView = isHomeView;
        this.projectIndex = projectIndex;
    }

    public String executeCommand(ArrayList<Project> projects, ArrayList<TeamMember> teamMembers) {
        if (teamMembers.size() == 0) {
            return "No team members have been added.";
        }
        String listOfMembers = "List of members:";
        int i = 0;

        if (isHomeView) {
            for (TeamMember member : teamMembers) {
                String assignmentStatus = (member.getAssignedProjectId() == -1) ? "Not assigned to a project"
                        : "Assigned to Project \""
                        + projects.get(member.getAssignedProjectId()).getProjectName() + "\"";
                listOfMembers += "\n" + (i + 1) + ". " + member.getName() + ": \t" + assignmentStatus;
                i++;
            }
        } else {
            // There is no need for catch statement here since I am already inside a project.
            ArrayList<TeamMember> teamMembersInSelectedProject = projects.get(projectIndex).getTeamMembers();
            for (TeamMember member : teamMembersInSelectedProject) {
                listOfMembers += "\n" + (i + 1) + ". " + member.getName();
                i++;
            }
        }
        return listOfMembers;
    }

    public Boolean isExit() {
        return false;
    }
}
