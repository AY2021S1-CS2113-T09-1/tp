package seedu.ezmanager.member;

import seedu.ezmanager.util.EzExceptions;
import seedu.ezmanager.project.Project;
import seedu.ezmanager.task.Task;

import java.util.ArrayList;

public class TeamMember {
    private String name;
    private ArrayList<Project> assignedProjects;
    private ArrayList<Task> tasks;

    public TeamMember(String name) {
        this.name = name;
        tasks = new ArrayList<>();
        this.assignedProjects = new ArrayList<>();
    }

    public void assignProject(Project project) throws EzExceptions {
        for (Project assignedProject : assignedProjects) {
            if (assignedProject == project) {
                throw new EzExceptions("projectAlreadyAssigned");
            }
        }
        assignedProjects.add(project);
    }
    
    public void setTask(Task selectedTask) {
        tasks.add(selectedTask);
    }
    
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public ArrayList<Project> getAssignedProjects() {
        return assignedProjects;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public String saveFormat() {
        String memberString = "";
        memberString += this.toString();
        /*
        memberString += "\npS\n";
        if (assignedProjects.size() > 0) {
            for (Project project : assignedProjects) {
                memberString += project.getProjectName();
            }
        }
        memberString += "\npE";
        */
        return memberString;
    }
}
