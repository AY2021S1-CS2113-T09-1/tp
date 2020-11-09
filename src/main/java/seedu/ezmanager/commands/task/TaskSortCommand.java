package seedu.ezmanager.commands.task;

import seedu.ezmanager.util.EzExceptions;
import seedu.ezmanager.member.TeamMember;
import seedu.ezmanager.project.Project;
import seedu.ezmanager.commands.Command;
import seedu.ezmanager.task.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import static seedu.ezmanager.parser.Parser.getHashValue;

public class TaskSortCommand extends Command {
    private int projectIndex;
    HashMap<String, String> params;
    private String sortingType;

    public TaskSortCommand(HashMap<String, String> params, int projectIndex)
            throws EzExceptions {
        this.params = params;
        this.projectIndex = projectIndex;
        this.parse();
    }

    public void parse() throws EzExceptions {
        try {
            sortingType = getHashValue(params, "s");
        } catch (NumberFormatException e) {
            throw new EzExceptions("invalidTaskID");
        }
    }

    public String executeCommand(ArrayList<Project> projects, ArrayList<TeamMember> teamMembers) throws EzExceptions {
        Project project = projects.get(projectIndex);
        switch (sortingType) {
        case "p":
            Collections.sort(project.getTaskList(), new Comparator<Task>() {
                @Override
                public int compare(Task o1, Task o2) {
                    if (o1.getPriority() == 0) {
                        return 1;
                    }

                    if (o2.getPriority() == 0) {
                        return -1;
                    }

                    return o1.getPriority() - o2.getPriority(); //sort based on priority
                }
            });
            System.out.println("Task List sorted based on priority");

            break;
        case "d":
            Collections.sort(project.getTaskList(), new Comparator<Task>() {
                    @Override
                    public int compare(Task o1, Task o2) {

                        if (o1.getDeadline() == null) {
                            return 1;
                        }

                        if (o2.getDeadline() == null) {
                            return -1;
                        }
                        return o1.getDeadline().compareTo(o2.getDeadline());

                    }
                });
            System.out.println("Task List sorted based on deadline");
            break;
        case "a":
            Collections.sort(project.getTaskList(), new Comparator<Task>() {
                @Override
                public int compare(Task o1, Task o2) {
                    if (o1.getActual() == 0) {
                        return 1;
                    }

                    if (o2.getActual() == 0) {
                        return -1;
                    }
                    return o1.getActual() - o2.getActual(); //sort based on actual time
                }
            });
            System.out.println("Task List sorted based on actual time taken");
            break;
        case "e":
            Collections.sort(project.getTaskList(), new Comparator<Task>() {
                @Override
                public int compare(Task o1, Task o2) {
                    if (o1.getEstimate() == 0) {
                        return 1;
                    }

                    if (o2.getEstimate() == 0) {
                        return -1;
                    }
                    return o1.getEstimate() - o2.getEstimate(); //sort based on actual time
                }
            });
            System.out.println("Task List sorted based on estimated time taken");
            break;
        default: throw new EzExceptions("InvalidSortingParameter");
        }

        return "";
    }

    public Boolean isExit() {
        return false;
    }

}
