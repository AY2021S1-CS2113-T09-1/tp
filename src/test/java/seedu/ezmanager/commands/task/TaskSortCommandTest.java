package seedu.ezmanager.commands.task;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.ezmanager.util.EzExceptions;
import seedu.ezmanager.member.TeamMember;
import seedu.ezmanager.project.Project;
import seedu.ezmanager.task.Task;
import seedu.ezmanager.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;


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
    public void execute_command_sortTaskList_Priority() throws EzExceptions {
        HashMap<String, String> params = new HashMap<>();
        params.put("s", "p");
        String expectedOutput = "List of Tasks:"
                + "\n     " + "1" + "." + "Task Two | 23/12/2020 | Actual: 2 hours 10 minutes|priority: 1"
                + "\n     " + "2" + "." + "Task Three | 14/11/2020 | Actual: 3 hours 30 minutes|priority: 2"
                + "\n     " + "3" + "." + "Task One | 13/12/2020 | Actual: 1 hours 30 minutes|priority: 3";

        TaskSortCommand tasksSorter = new TaskSortCommand(params,0);
        tasksSorter.executeCommand(projects,teamMembers);
        String actualOutput = Ui.printTaskListMessage(projects.get(0));

        assertEquals(expectedOutput,actualOutput);
    }

    @Test
    public void execute_command_sortTaskList_Deadline() throws EzExceptions {
        HashMap<String, String> params = new HashMap<>();
        params.put("s", "d");
        String expectedOutput = "List of Tasks:"
                + "\n     " + "1" + "." + "Task Three | 14/11/2020 | Actual: 3 hours 30 minutes|priority: 2"
                + "\n     " + "2" + "." + "Task One | 13/12/2020 | Actual: 1 hours 30 minutes|priority: 3"
                + "\n     " + "3" + "." + "Task Two | 23/12/2020 | Actual: 2 hours 10 minutes|priority: 1";

        TaskSortCommand tasksSorter = new TaskSortCommand(params,0);
        tasksSorter.executeCommand(projects,teamMembers);
        String actualOutput = Ui.printTaskListMessage(projects.get(0));

        assertEquals(expectedOutput,actualOutput);
    }

    @Test
    public void execute_command_sortTaskList_ActualTime() throws EzExceptions {
        HashMap<String, String> params = new HashMap<>();
        params.put("s", "a");
        String expectedOutput = "List of Tasks:"
                + "\n     " + "1" + "." + "Task One | 13/12/2020 | Actual: 1 hours 30 minutes|priority: 3"
                + "\n     " + "2" + "." + "Task Two | 23/12/2020 | Actual: 2 hours 10 minutes|priority: 1"
                + "\n     " + "3" + "." + "Task Three | 14/11/2020 | Actual: 3 hours 30 minutes|priority: 2";

        TaskSortCommand tasksSorter = new TaskSortCommand(params,0);
        tasksSorter.executeCommand(projects,teamMembers);
        String actualOutput = Ui.printTaskListMessage(projects.get(0));

        assertEquals(expectedOutput,actualOutput);
    }

    @Test
    public void executeCommand_invalidProjectId_exception() throws EzExceptions {
        HashMap<String, String> params = new HashMap<>();
        String expectedOutput = "Certain Parameters are missing!";
        Throwable actualOutputException = assertThrows(EzExceptions.class, () -> {
            new TaskSortCommand(params,0);
        });
        assertEquals(expectedOutput,actualOutputException.toString());
    }
}