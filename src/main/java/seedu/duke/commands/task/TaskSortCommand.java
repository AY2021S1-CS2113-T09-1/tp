package seedu.duke.commands.task;

import seedu.duke.DukeExceptions;
import seedu.duke.member.TeamMember;
import seedu.duke.project.Project;
import seedu.duke.commands.Command;
import seedu.duke.task.Task;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static seedu.duke.Parser.getHashValue;

public class TaskSortCommand extends Command {
    private int projectIndex;
    //HashMap<String, String> params;
    private String sortingType;

    public TaskSortCommand(String sortingType, int projectIndex)
            throws DukeExceptions {
        this.sortingType = sortingType;
        this.projectIndex = projectIndex;
        this.parse();
    }

    public void parse() throws DukeExceptions {
        try {
            sortingType = sortingType.replaceFirst("/","");
        } catch (NumberFormatException e) {
            throw new DukeExceptions("invalidTaskID");
        }
    }

    public String executeCommand(ArrayList<Project> projects, ArrayList<TeamMember> teamMembers) throws DukeExceptions {
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
            System.out.println("TaskList sorted based on priority");

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
            System.out.println("TaskList sorted based on deadline");
            break;
        case "t":
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
            System.out.println("TaskList sorted based on completion time");
            break;
        default: throw new DukeExceptions("InvalidSortingParameter");
        }

        return "";
    }

    public Boolean isExit() {
        return false;
    }

}
