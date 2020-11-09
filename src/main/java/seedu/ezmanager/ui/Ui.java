package seedu.ezmanager.ui;

import seedu.ezmanager.member.TeamMember;
import seedu.ezmanager.project.Project;
import seedu.ezmanager.task.Task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.Period;
import static seedu.ezmanager.util.Util.MINUTES_IN_HOUR_DOUBLE;

/**
 * Text UI of the application.
 */
public class Ui {

    private static final String MESSAGE_SINGLE_LINE = "_________________________________________"
            + "____________________________________________";
    private static final String MESSAGE_WELCOME = "Hello from EzManager!\n"
            + "What can I do for you?";
    private static final String MESSAGE_GOODBYE = "See you again!";
    private static final String MESSAGE_LOGO = " _____         ___     ___\n"
            + "|  ___|       |   \\  /   |\n"
            + "| |___  _____ |    \\/    | ______    ______  ______    ______  ______   _____  _____\n"
            + "|  ___||___ / |  |\\  /|  ||  __  |  |  __  ||  __  |  |  __  ||  __  | / ___ \\|  ___|\n"
            + "| |___   / /_ |  | \\/ |  || |__| |_ | |  | || |__| |_ | |  | || |__| ||   ___/|  |\n"
            + "|_____| /____||__|    |__||________||_|  |_||________||_|  |_||____  ||______||__|\n"
            + "                                                                   | |\n"
            + "                                                               ____| |\n"
            + "                                                              |______|\n";

    /**
     * Print welcome message together with EzManage Logo.
     */
    public void printWelcome() {
        System.out.println(MESSAGE_SINGLE_LINE);
        System.out.println(MESSAGE_LOGO);
        System.out.println(MESSAGE_WELCOME);
        System.out.println(MESSAGE_SINGLE_LINE);
    }

    /**
     * Print goodbye message.
     * @return Goodbye message String.
     */
    public static String printGoodbyeMessage() {
        return MESSAGE_GOODBYE;
    }

    /**
     * Print a String of lines to separate commands and outputs.
     */
    public void printLine() {
        System.out.println(MESSAGE_SINGLE_LINE);
    }

    /**
     * Print the output after every execution of command.
     * @param output String of output messages after every execution of command.
     */
    public static void printOutput(String output) {
        System.out.println(output);
    }

    /**
     * Return member added message.
     * @param name Name of added member.
     * @return String of member added message.
     */
    public static String printMemberAddedMessage(String name) {
        return "Team member \"" + name + "\" has been added";
    }

    /**
     * Return member in home view removed message.
     * @param name Name of removed member.
     * @return String of member in home view being removed message.
     */
    public static String printMemberRemovedInHomeViewMessage(String name) {
        return "Team member \"" + name + "\" has been removed from program entirely";
    }

    /**
     * Return member in project view removed message.
     * @param name Name of removed member.
     * @param projectName Name of project that member is removed from.
     * @return String of member in project view being removed message.
     */
    public static String printMemberRemovedInProjectViewMessage(String name, String projectName) {
        return "Team member \"" + name + "\" has been removed from Project \"" + projectName + "\"";
    }

    /**
     * Return project deleted message.
     * @param project Removed Project object.
     * @return String of project being deleted message.
     */
    public static String printProjectDeletedMessage(Project project) {
        return "Project \"" + project.getProjectName() + "\" deleted";
    }

    public static String printTaskListMessage(Project project) {

        project.sortTasksList();
        int numberOfTasks = project.getTaskList().size();

        String output = "List of Tasks:";

        for (int i = 0; i < numberOfTasks; i++) {

            output += "\n     " + (i + 1) + "." + project.getTask(i)
                    + ((project.getTask(i).getPriority() != 0) ? "|"
                    + "priority: " + project.getTask(i).getPriority() : "");
        }

        return output;
    }

    /**
     * Return project created message.
     * @param projectName Name of specified project.
     * @return String of project being created message.
     */
    public static String printProjectCreatedMessage(String projectName) {
        return "Project \"" + projectName + "\" created!";
    }

    /**
     * Return project description added message.
     * @param project Specified Project object.
     * @return String of project description being added message.
     */
    public static String printProjectDescriptionAddedMessage(Project project) {
        return "Project description added \"" + project.getDescription() + "\".";
    }

    /**
     * Return project marked as done message.
     * @param projectName Name of specified project that is marked as done.
     * @return String of project marked as done message.
     */
    public static String printProjectDoneMessage(String projectName) {
        return "Project \"" + projectName + "\" is done!";
    }

    /**
     * Return project deadline added message as well as newly sorted home view.
     * @param projects ArrayList of Projects.
     * @param project Specified Project object that has deadline to be added.
     * @param date deadline of specified project to be added.
     * @param members ArrayList of TeamMembers in the program.
     * @return String of project deadline being added message as well as
     *     newly sorted home view display.
     */
    public static String printProjectDeadlineAddedMessage(ArrayList<Project> projects, Project project,
                                                          LocalDate date, ArrayList<TeamMember> members) {
        String output =  "Deadline " + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                + " added to Project " + project.getProjectName() + "\n\n";
        output += printHomeView(projects, members);
        return output;
    }

    /**
     * Return task created message.
     * @param taskName Name of added task.
     * @return String of task created message.
     */
    public static String printTaskCreatedMessage(String taskName) {
        return "Task \"" + taskName + "\" created!";
    }

    /**
     * Return estimated time added to task message.
     * @param taskName Name of task with estimated time to be added.
     * @param hours Hours part of estimated time.
     * @param minutes Minutes part of estimated time.
     * @return String of estimated time being added to task message.
     */
    public static String printEstimateAddedMessage(String taskName, int hours, int minutes) {
        return "Task \"" + taskName + "\" has estimated time of " + hours + " hours and " + minutes + " minutes";
    }

    /**
     * Return actual time added to task message.
     * @param taskName Name of task with actual time to be added.
     * @param hours Hours part of actual time.
     * @param minutes Minutes part of actual time.
     * @return String of actual time being added to task message.
     */
    public static String printActualDurationAddedMessage(String taskName, int hours, int minutes) {
        return "Task \"" + taskName + "\" took " + hours + " hours and " + minutes + " minutes to be completed.";
    }

    /**
     * Return task marked as done message.
     * @param taskName Name of specified task that is marked as done.
     * @return String of task being marked as done message.
     */
    public static String printTaskDoneMessage(String taskName) {
        return "Task \"" + taskName + "\" is done!";
    }

    /**
     * Retunr task deleted message.
     * @param taskName Name of removed task.
     * @return String of task being deleted message.
     */
    public static String printTaskDeletedMessage(String taskName) {
        return "Task \"" + taskName + "\" removed!";
    }

    private static String printProjectListHeadingInHomeView() {
        String output = "EZ Manager Home View\n";
        output += "\n ----------------------";
        output += "\n| PROJECT LIST         |";
        output += "\n ----------------------\n";
        output += "\nIndex   Status   Project Name             Project Description                "
                + "Deadline     Tasks Completed     Remarks";
        output += "\n---------------------------------------------------------------------------"
                + "---------------------------------------------------------------------------";
        return output;
    }

    private static String printProjectListInHomeView(ArrayList<Project> projects) {
        String output = "";
        int projectIndex = 1;
        String paddedProjectIndex;
        String paddedProjectStatus;
        String paddedProjectName;
        String paddedProjectDescription;
        String paddedProjectDeadline;
        String paddedTaskCompleted;
        String remarks;
        for (Project project : projects) {
            paddedProjectIndex = String.format("%-8s", projectIndex + ".");
            paddedProjectStatus = printProjectStatusInHomeView(project);
            paddedProjectName = printProjectNameInHomeView(project);
            paddedProjectDescription = printProjectDescriptionInHomeView(project);
            paddedProjectDeadline = printProjectDeadlineInHomeView(project);
            paddedTaskCompleted = printTaskCompletedInProjectInHomeView(project);
            remarks = printRemarksInHomeView(project);
            output += "\n" + paddedProjectIndex + paddedProjectStatus + paddedProjectName + paddedProjectDescription
                    + paddedProjectDeadline + paddedTaskCompleted + remarks;
            projectIndex++;
        }
        return output;
    }

    private static String printProjectStatusInHomeView(Project project) {
        String projectStatus;
        if (project.isProjectDone()) {
            projectStatus = "Y";
        } else {
            projectStatus = "N";
        }
        String paddedProjectStatus = String.format("%-9s", projectStatus);
        return paddedProjectStatus;
    }

    private static String printProjectNameInHomeView(Project project) {
        String projectName = project.getProjectName();
        if (projectName.length() >= 25) {
            projectName = projectName.substring(0, 21) + "...";
        }
        String paddedProjectName = String.format("%-25s", projectName);
        return paddedProjectName;
    }

    private static String printProjectDescriptionInHomeView(Project project) {
        String paddedProjectDescription;
        if (!project.getDescription().equals("<project description empty>")) {
            String projectDescription = project.getDescription();
            if (projectDescription.length() >= 35) {
                projectDescription = projectDescription.substring(0, 31) + "...";
            }
            paddedProjectDescription = String.format("%-35s", projectDescription);
        } else {
            paddedProjectDescription = String.format("%-35s", "-");
        }
        return paddedProjectDescription;
    }

    private static String printProjectDeadlineInHomeView(Project project) {
        String paddedProjectDeadline;
        if (project.getProjectDeadline() != null) {
            String projectDeadline = project.getProjectDeadline().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            paddedProjectDeadline = String.format("%-13s", projectDeadline);
        } else {
            paddedProjectDeadline = String.format("%-13s", "-");
        }
        return paddedProjectDeadline;
    }

    private static String printTaskCompletedInProjectInHomeView(Project project) {
        String taskCompleted = project.getNumberOfFinishedTask() + "/"
                + project.getNumberOfTask();
        String paddedTaskCompleted = String.format("%-20s", taskCompleted);
        return paddedTaskCompleted;
    }

    private static String printRemarksInHomeView(Project project) {
        String remarks = "-";
        LocalDate dateOfTaskWithNearestDeadline = null;
        Task taskWithNearestDeadline = null;
        if (!project.getTaskList().isEmpty()) {
            ArrayList<Task> tasks = project.getTaskList();
            taskWithNearestDeadline = checkForTaskWithNearestDeadline(tasks);
            if (taskWithNearestDeadline != null) {
                dateOfTaskWithNearestDeadline = taskWithNearestDeadline.getDeadline();
            }
            LocalDate currentDate = LocalDate.now();
            if (dateOfTaskWithNearestDeadline != null && !taskWithNearestDeadline.getStatus()) {
                //find the difference in the number of days from current days to deadline
                Period period = Period.between(currentDate, dateOfTaskWithNearestDeadline);
                if (period.getDays() <= 5 && period.getMonths() == 0 && period.getYears() == 0) {
                    remarks = "!!!WARNING!!! Task \"" + taskWithNearestDeadline.getDescription()
                            + "\" has " + period.getDays() + " day(s) before deadline and still not done!!";
                } else {
                    remarks = "Task \"" + taskWithNearestDeadline.getDescription()
                            + "\" has an upcoming deadline at " + taskWithNearestDeadline.getDateString()
                            + " and still not done!!";
                }
            }
        }
        return remarks;
    }

    private static Task checkForTaskWithNearestDeadline(ArrayList<Task> tasks) {
        LocalDate dateOfTaskWithNearestDeadline = null;
        Task taskWithNearestDeadline = null;
        for (Task task : tasks) {
            LocalDate deadlineOfTask = task.getDeadline();
            if (deadlineOfTask == null) {
                continue;
            } else if (dateOfTaskWithNearestDeadline == null) {
                dateOfTaskWithNearestDeadline = deadlineOfTask;
                taskWithNearestDeadline = task;
            } else if (deadlineOfTask.compareTo(dateOfTaskWithNearestDeadline) < 0) {
                dateOfTaskWithNearestDeadline = deadlineOfTask;
                taskWithNearestDeadline = task;
            }
        }
        return taskWithNearestDeadline;
    }

    private static String printMemberListHeadingInHomeView() {
        String output = "\n\n ----------------------";
        output += "\n| MEMBERS LIST         |";
        output += "\n ----------------------\n";
        output += "\nIndex   Member Name                   Projects Involved        Hours spent across tasks";
        output += "\n---------------------------------------------------------------------------------------";
        return output;
    }

    private static String printMemberListInHomeView(ArrayList<TeamMember> teamMembers) {
        String output = "";
        int memberIndex = 1;
        for (TeamMember member : teamMembers) {
            String paddedMemberIndex = String.format("%-8s", memberIndex + ".");
            String paddedMemberName = printMemberNameInHomeView(member);
            output += "\n" + paddedMemberIndex + paddedMemberName
                    + printMemberAssignedProjectsInHomeView(member);
            output += System.lineSeparator();
            memberIndex++;
        }
        return output;
    }

    private static String printMemberNameInHomeView(TeamMember member) {
        String memberName = member.getName();
        if (member.getName().length() >= 30) {
            memberName = member.getName().substring(0, 26) + "...";
        }
        String paddedMemberName = String.format("%-30s", memberName);
        return paddedMemberName;
    }

    private static String printMemberAssignedProjectsInHomeView(TeamMember member) {
        String output = "";
        double hoursWorked = 0;
        String paddedProjectName;
        hoursWorked = hoursWorkedByMembers(member);
        if (!member.getAssignedProjects().isEmpty()) {
            for (int i = 0; i < member.getAssignedProjects().size(); i++) {
                String assignedProjectName = member.getAssignedProjects().get(i).getProjectName();
                if (assignedProjectName.length() >= 25) {
                    assignedProjectName = assignedProjectName.substring(0, 18) + "...";
                }
                paddedProjectName = String.format("%-22s", assignedProjectName);

                if (i == 0) {
                    output += "1. " + paddedProjectName + hoursWorked;
                } else {
                    output += "\n                                      "
                            + (i + 1) + ". " + paddedProjectName;
                }
            }
        } else {
            output += String.format("%-25s", "-") + hoursWorked;
        }
        return output;
    }

    private static double hoursWorkedByMembers(TeamMember member) {
        double hoursWorked = 0;
        for (int i = 0; i < member.getTasks().size(); i++) {
            Task task = member.getTasks().get(i);
            hoursWorked += task.getActual() / MINUTES_IN_HOUR_DOUBLE;
        }
        return hoursWorked;
    }

    /**
     * Returns the home view display which consists of a project list and a member list.
     * Additional information such as project description, project deadline, remarks
     * and member's assigned project will also be displayed.
     * @param projects ArrayList of Projects.
     * @param teamMembers ArrayList of TeamMembers in the program.
     * @return Home view display
     */
    public static String printHomeView(ArrayList<Project> projects, ArrayList<TeamMember> teamMembers) {
        String output = printProjectListHeadingInHomeView();
        output += printProjectListInHomeView(projects);
        output += printMemberListHeadingInHomeView();
        output += printMemberListInHomeView(teamMembers);
        return output;
    }

    /**
     * Return task deadline added message.
     * @param date deadline of specified project to be added.
     * @param taskName Name of specified task with deadline to be added.
     * @return String of task deadline being added message.
     */
    public static String printTaskDeadlineMessage(LocalDate date, String taskName) {
        return "Deadline " + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                + " added to Task " + taskName;
    }

    /**
     * Return task name updated message.
     * @param oldTaskName previous task name.
     * @param newTaskName new task name to be updated.
     * @return String of task name being updated message.
     */
    public static String printTaskNameUpdatedMessage(String oldTaskName, String newTaskName) {
        return "Task " + "\"" + oldTaskName + "\" has been updated to \"" + newTaskName + "\"";
    }

    /**
     * Returns the project view display.
     * @param project Specified Project object that we are currently in.
     * @return Project View Display
     */
    public static String projectViewMessage(Project project) {
        try {
            String projectTitle = "Project \"" + project.getProjectName() + "\"";
            String projectDescription = "\n" + "Description:" + "\n" + project.getDescription();
            String taskListTitle = "\n ---------------------\n| TASK LIST           |\n ---------------------";
            String membersListTitle = "\n ---------------------\n| MEMBERS LIST        |\n ---------------------";
            String indexSpaces = "      "; // 6
            String statusSpaces = "      "; // 6
            String descriptionSpaces = "                   "; // 19
            String deadlineSpaces = "                "; // 16
            String prioritySpaces = "              "; // 14
            String estimatedSpaces = "                  "; // 18
            String actualSpaces = "             "; // 13
            String membersSpaces = "                "; // 16
            String tableLabel = "Index  Status   Description        "
                                + "Deadline        Priority      Estimated Hrs     Actual Hrs   | Members Involved\n"
                                + "------------------------------------------------"
                                + "------------------------------------------------|------------------";
            String taskLines = generateTaskEntries(project, indexSpaces, statusSpaces, descriptionSpaces,
                    deadlineSpaces, prioritySpaces, estimatedSpaces, actualSpaces);

            ArrayList<TeamMember> members = project.getTeamMembers();
            String membersListLines = getMembersList(members);

            return projectTitle + "\n" + projectDescription + "\n" + taskListTitle + "\n"
                    + (project.getTaskList().size() > 0 ? tableLabel : "") + taskLines
                    + "\n \n" + membersListTitle + "\n" + membersListLines;
        } catch (Error e) {
            System.out.println(e.getMessage());
        }
        return "hi";
    }

    private static String getMembersList(ArrayList<TeamMember> members) {
        String membersListLines = "";
        if (members.size() > 0) {
            for (int j = 0; j < members.size(); j++) {
                membersListLines += (j + 1) + ". " + members.get(j).getName() + "\n";
            }
        } else {
            membersListLines += "No team members have been assigned to this project.";
        }
        return membersListLines;
    }

    private static String generateTaskEntries(Project project, String indexSpaces, String statusSpaces,
                                              String descriptionSpaces, String deadlineSpaces, String prioritySpaces,
                                              String estimatedSpaces, String actualSpaces) {
        Integer i = 0;
        int index;
        String currentTaskLine = "";
        String taskLines = "\n";
        if (project.getTaskList().size() > 0) {
            for (; i < project.getTaskList().size(); i++) {
                index = i + 1;
                Task currentTask = project.getTaskList().get(i);
                String status = currentTask.isDone() ? "(Y)" : "(N)";
                String description = currentTask.getTaskDescription();
                int cutoffIndexForDescription = 15;
                description = truncateDescription(description, cutoffIndexForDescription);
                currentTaskLine = index + indexSpaces + status + statusSpaces + description
                        + (descriptionSpaces.substring(0, descriptionSpaces.length() - description.length()));

                currentTaskLine = deadlineForTask(deadlineSpaces, currentTaskLine, currentTask);

                currentTaskLine = priorityForTask(prioritySpaces, currentTaskLine, currentTask);

                currentTaskLine = estimatedTimeForTask(estimatedSpaces, currentTaskLine, currentTask);

                currentTaskLine = actualTimeForTask(actualSpaces, currentTaskLine, currentTask);

                currentTaskLine = membersInvolvedInTasks(currentTaskLine, currentTask);

                taskLines += (currentTaskLine + "\n");
            }
        } else {
            taskLines += "No tasks have been added to this project.";
        }
        return taskLines;
    }

    private static String actualTimeForTask(String actualSpaces, String currentTaskLine, Task currentTask) {
        Integer actual = currentTask.getActual();
        String actualString = "";
        if (actual > 1) {
            int hours = (actual / 60);
            int minutes = actual - hours * 60;
            double ratio = minutes / 60.0;
            actualString = String.format("%.1f", hours + ratio);
            currentTaskLine += actualString;
        } else {
            actualString = "-";
            currentTaskLine += actualString;
        }

        currentTaskLine += (actualSpaces.substring(0, actualSpaces.length()
                - actualString.length()));
        return currentTaskLine;
    }

    private static String estimatedTimeForTask(String estimatedSpaces, String currentTaskLine, Task currentTask) {
        Integer estimate = currentTask.getEstimate();
        String estimateString = "";
        if (estimate > 1) {
            int hours = (estimate / 60);
            int minutes = estimate - hours * 60;
            double ratio = minutes / 60.0;
            estimateString = String.format("%.1f", hours + ratio);
            currentTaskLine += estimateString;
        } else {
            estimateString = "-";
            currentTaskLine += estimateString;
        }
        currentTaskLine += (estimatedSpaces.substring(0, estimatedSpaces.length()
                - estimateString.length()));
        return currentTaskLine;
    }

    private static String priorityForTask(String prioritySpaces, String currentTaskLine, Task currentTask) {
        String priority = String.valueOf(currentTask.getPriority());
        if (!priority.equals("0")) {
            currentTaskLine += (priority);
        } else {
            priority = "-";
            currentTaskLine += "-";
        }
        currentTaskLine += (prioritySpaces.substring(0, prioritySpaces.length() - priority.length()));
        return currentTaskLine;
    }

    private static String deadlineForTask(String deadlineSpaces, String currentTaskLine, Task currentTask) {
        String deadline = currentTask.getDateString();

        if (deadline.length() > 0) {
            currentTaskLine += (deadline);
        } else {
            deadline = "-";
            currentTaskLine += "-";
        }

        currentTaskLine += (deadlineSpaces.substring(0, deadlineSpaces.length() - deadline.length()));
        return currentTaskLine;
    }


    private static String membersInvolvedInTasks(String currentTaskLine, Task currentTask) {
        ArrayList<TeamMember> members = currentTask.getMembers();
        currentTaskLine += "|";
        int j;
        if (members.size() == 0) {
            currentTaskLine += " -";
        } else {
            for (j = 0; j < members.size(); j++) {
                boolean isOnlyOneMember = members.size() <= 1 ? true : false;
                boolean isLastMember = j == (members.size() - 1) ? true : false;
                TeamMember member = members.get(j);
                if (member != null) {
                    if (!isOnlyOneMember && !isLastMember) {
                        currentTaskLine += " " + member.getName() + ",";
                    } else if (isOnlyOneMember | isLastMember) {
                        currentTaskLine += " " + member.getName();
                    }
                }
            }
        }
        return currentTaskLine;
    }

    private static String truncateDescription(String description, int cutoffIndexForDescription) {
        // truncate description if it is too long
        if (description.length() > cutoffIndexForDescription) {
            description = description.substring(0, cutoffIndexForDescription) + "...";
        }
        return description;
    }

    /**
     * Return member assigned to task message.
     * @param memberName Name of member to be assigned.
     * @param taskName Name of task assigned to member.
     * @return String of member being assigned to task message.
     */
    public static String printMemberAssignedToTaskMessage(String memberName, String taskName) {
        return "Member \"" + memberName + "\" has been assigned to \"" + taskName + "\"";
    }

    /**
     * Return member assigned to project message.
     * @param memberName Name of member to be assigned.
     * @param projectName Name of project assigned to member.
     * @return String of member being assigned to project message.
     */
    public static String printMemberAssignedToProjectMessage(String memberName, String projectName) {
        return memberName + " assigned to Project \"" + projectName + "\"";
    }

    /**
     * Return priority assigned to task message.
     * @param priority Priority to be added.
     * @param taskName Name of task with priority to be added.
     * @return String of priority being assigned to task message.
     */
    public static String printPriorityAssignedToTaskMessage(int priority, String taskName) {
        return "Priority \"" + priority + "\" has been assigned to \"" + taskName + "\"";
    }

    /**
     * Return total hours worked by specified member in each tasks of every project.
     * @param memberName Name of specified member.
     * @param hoursWorked total hours worked by member.
     * @return String of total hours worked by specified member.
     */
    public static String printHoursWorkedMessage(String memberName, double hoursWorked) {
        return memberName + " worked for " + String.format("%.1f", hoursWorked) + " hours.";
    }

}
