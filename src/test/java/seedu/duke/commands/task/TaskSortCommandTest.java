package seedu.duke.commands.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.duke.DukeExceptions;
import seedu.duke.commands.member.AssignMemberToProjectCommand;
import seedu.duke.commands.project.ProjectCommand;
import seedu.duke.commands.project.ProjectSelectCommand;
import seedu.duke.member.TeamMember;
import seedu.duke.project.Project;
import seedu.duke.task.Task;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TaskSortCommandTest {
    static ArrayList<Project> projects;
    static ArrayList<TeamMember> teamMembers;
    static Ui ui = new Ui();

    @BeforeAll
    static void createProjectL() {
        projects = new ArrayList<>();
        Project projectOne = new Project("Project One");
        Task taskOne = new Task("Task One");
        Task taskTwo = new Task("Task Two");
        Task taskThree = new Task("Task Three");
        projectOne.addTask(taskOne);
        projectOne.addTask(taskTwo);
        projectOne.addTask(taskThree);
        projects.add(projectOne);
        projectOne.getTask(0).setPriority(3);
        projectOne.getTask(1).setPriority(1);
        projectOne.getTask(2).setPriority(2);
        projectOne.getTask(0).addDeadline(LocalDate.parse("2020-12-13"));
        projectOne.getTask(1).addDeadline(LocalDate.parse("2020-12-23"));
        projectOne.getTask(2).addDeadline(LocalDate.parse("2020-11-14"));
        projectOne.getTask(0).addActual(90);
        projectOne.getTask(1).addActual(130);
        projectOne.getTask(2).addActual(210);
    }

    @Test
    public void execute_command_sortTaskList_Priority() throws DukeExceptions {
        String sortingType = "p/";
        String expectedOutput = "List of Tasks:"
                + "\n     " + "1" + "." + "Task Two | 23/12/2020 | Actual: 2 hours 10 minutes|priority: 1"
                + "\n     " + "2" + "." + "Task Three | 14/11/2020 | Actual: 3 hours 30 minutes|priority: 2"
                + "\n     " + "3" + "." + "Task One | 13/12/2020 | Actual: 1 hours 30 minutes|priority: 3";

        TaskSortCommand tasksSorter = new TaskSortCommand(sortingType,0);
        tasksSorter.executeCommand(projects,teamMembers);
        String actualOutput = Ui.printTaskListMessage(projects.get(0));

        assertEquals(expectedOutput,actualOutput);
    }

    @Test
    public void execute_command_sortTaskList_Deadline() throws DukeExceptions {
        String sortingType = "d/";
        String expectedOutput = "List of Tasks:"
                + "\n     " + "1" + "." + "Task Three | 14/11/2020 | Actual: 3 hours 30 minutes|priority: 2"
                + "\n     " + "2" + "." + "Task One | 13/12/2020 | Actual: 1 hours 30 minutes|priority: 3"
                + "\n     " + "3" + "." + "Task Two | 23/12/2020 | Actual: 2 hours 10 minutes|priority: 1";

        TaskSortCommand tasksSorter = new TaskSortCommand(sortingType,0);
        tasksSorter.executeCommand(projects,teamMembers);
        String actualOutput = Ui.printTaskListMessage(projects.get(0));

        assertEquals(expectedOutput,actualOutput);
    }

    @Test
    public void execute_command_sortTaskList_ActualTime() throws DukeExceptions {
        String sortingType = "t/";
        String expectedOutput = "List of Tasks:"
                + "\n     " + "1" + "." + "Task One | 13/12/2020 | Actual: 1 hours 30 minutes|priority: 3"
                + "\n     " + "2" + "." + "Task Two | 23/12/2020 | Actual: 2 hours 10 minutes|priority: 1"
                + "\n     " + "3" + "." + "Task Three | 14/11/2020 | Actual: 3 hours 30 minutes|priority: 2";

        TaskSortCommand tasksSorter = new TaskSortCommand(sortingType,0);
        tasksSorter.executeCommand(projects,teamMembers);
        String actualOutput = Ui.printTaskListMessage(projects.get(0));

        assertEquals(expectedOutput,actualOutput);
    }

    @Test
    public void executeCommand_invalidProjectId_assignMemberToProject() throws DukeExceptions {
        String sortingType = "q/";
        TaskSortCommand tasksSorter = new TaskSortCommand(sortingType,0);
        String expectedOutput = "You have entered the wrong sorting parameter";
        Throwable actualOutputException = assertThrows(DukeExceptions.class, () -> {
            tasksSorter.executeCommand(projects,teamMembers);
        });
        assertEquals(expectedOutput,actualOutputException.toString());
    }
}