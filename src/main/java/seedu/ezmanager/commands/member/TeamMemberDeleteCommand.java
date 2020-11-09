package seedu.ezmanager.commands.member;

import seedu.ezmanager.util.EzExceptions;
import seedu.ezmanager.commands.Command;
import seedu.ezmanager.member.TeamMember;
import seedu.ezmanager.project.Project;
import seedu.ezmanager.task.Task;
import seedu.ezmanager.ui.Ui;

import java.util.ArrayList;
import java.util.HashMap;

import static seedu.ezmanager.parser.Parser.getHashValue;

public class TeamMemberDeleteCommand extends Command {
    private int memberIndex;
    HashMap<String, String> params;
    private int projectIndex;

    public TeamMemberDeleteCommand(HashMap<String, String> params, int projectIndex) throws EzExceptions {
        this.params = params;
        this.parse();
        this.projectIndex = projectIndex;
    }

    public void parse() throws EzExceptions {
        try {
            memberIndex = Integer.parseInt(getHashValue(params, "m")) - 1;
        } catch (NumberFormatException e) {
            throw new EzExceptions("invalidTeamMemberID");
        }
    }

    @Override
    public String executeCommand(ArrayList<Project> projects,
                                 ArrayList<TeamMember> teamMembers) throws EzExceptions {
        if (teamMembers.size() == 0) {
            throw new EzExceptions("emptyTeamMembersList");
        }
        try {
            // Removing of Members in HomeView
            if (projectIndex == -1) {
                TeamMember memberToBeRemoved = teamMembers.get(memberIndex);
                teamMembers.remove(memberIndex);
                for (Project project : projects) {
                    for (int i = 0; i < project.getTeamMembers().size(); i++) {
                        TeamMember member = project.getTeamMembers().get(i);
                        if (member == memberToBeRemoved) {
                            project.getTeamMembers().remove(i);
                            i--;
                        }
                    }
                    removingMemberFromTask(project, memberToBeRemoved);
                }
                return Ui.printMemberRemovedInHomeViewMessage(memberToBeRemoved.getName());
            // Removing of members in ProjectView
            } else {
                Project project = projects.get(projectIndex);
                TeamMember memberToBeRemoved = project.getTeamMembers().get(memberIndex);
                project.getTeamMembers().remove(memberIndex);
                removingMemberFromTask(project, memberToBeRemoved);
                memberToBeRemoved.getAssignedProjects().remove(project);
                return Ui.printMemberRemovedInProjectViewMessage(memberToBeRemoved.getName(), project.getProjectName());
            }
        } catch (IndexOutOfBoundsException e) {
            throw new EzExceptions("invalidTeamMemberID");
        }
    }

    private void removingMemberFromTask(Project project, TeamMember memberToBeRemoved) {
        for (Task task : project.getTaskList()) {
            for (int j = 0; j < task.getMembers().size(); j++) {
                TeamMember member = task.getMembers().get(j);
                if (member == memberToBeRemoved) {
                    task.getMembers().remove(j);
                    j--;
                }
            }
        }
    }

    @Override
    public Boolean isExit() {
        return false;
    }
}
