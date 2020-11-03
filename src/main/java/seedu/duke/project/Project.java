package seedu.duke.project;

import seedu.duke.member.TeamMember;
import seedu.duke.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Collections;

public class Project implements Comparable<Project> {
    protected String projectName;
    protected boolean isDone;
    private ArrayList<Task> tasks;
    private String projectDescription;
    private LocalDate projectDeadline;
    private ArrayList<TeamMember> teamMembers;
    //private static final String TICK_MARK = "\u2713";
    //private static final String CROSS_MARK = "\u2718";

    public Project(String projectName) {
        this.projectName = projectName;
        this.isDone = false;
        this.tasks = new ArrayList<>();
        this.teamMembers = new ArrayList<>();
        this.projectDescription = "<project description empty>";
        this.projectDeadline = null;
    }


    public void sortTasksList() {
        //Collections.sort(tasks);
    }

    public int compareTo(Project project) {
        if (getProjectDeadline() == null || project.getProjectDeadline() == null) {
            return 0;
        }
        return getProjectDeadline().compareTo(project.getProjectDeadline());

    }

    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int taskIndex) {
        tasks.remove(taskIndex);
    }

    public int getNumberOfTask() {
        return tasks.size();
    }

    public int getNumberOfFinishedTask() {
        int finishedTaskCounter = 0;
        for (Task task : tasks) {
            if (task.isDone()) {
                finishedTaskCounter++;
            }
        }
        return finishedTaskCounter;
    }

    public void addDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getDescription() {
        return projectDescription;
    }

    public String getProjectName() {
        return projectName;
    }

    public void addProjectDeadline(LocalDate date) {
        this.projectDeadline = date;
    }

    public LocalDate getProjectDeadline() {
        return this.projectDeadline;
    }

    public ArrayList<TeamMember> getTeamMembers() {
        return this.teamMembers;
    }

    public void addTeamMemberToProject(TeamMember addedMember) {
        teamMembers.add(addedMember);
    }

    /**
     * Returns details of the specified task.
     *
     * @return Details of Project
     */
    @Override
    public String toString() {
        if (!projectDescription.equals("") & projectDeadline != null) {
            return "Description: " + projectDescription + " | Deadline: " + projectDeadline;
        } else if (!projectDescription.equals("") & projectDeadline == null) {
            return "Description: " + projectDescription + " | <project deadline empty>";
        } else if (projectDeadline != null) {
            return "<project description empty> | Deadline: " + projectDeadline;
        } else {
            return "<project description empty> | <project deadline empty>";
        }
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    public String selectTask(int taskId) {
        return tasks.get(taskId).toString();
    }

    public boolean getStatus() {
        if (isDone) {
            return isDone;
        }

        for (Task task : tasks) {
            if (!(task.getStatus())) {
                return false;
            }
        }

        return true;
    }

    public void markAsDone() {
        this.isDone = true;
    }
}
