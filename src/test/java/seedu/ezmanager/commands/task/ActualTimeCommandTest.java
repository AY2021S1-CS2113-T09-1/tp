package seedu.ezmanager.commands.task;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.ezmanager.util.EzExceptions;
import seedu.ezmanager.project.Project;
import seedu.ezmanager.task.Task;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ActualTimeCommandTest {
    static ArrayList<Project> projects;

    @BeforeAll
    static void createProjectsTasks() {
        projects = new ArrayList<>();
        Project projectOne = new Project("Drink Water");
        projects.add(projectOne);

        Project projectTwo = new Project("Fire");
        Task taskOne = new Task("test1");
        Task taskTwo = new Task("test2");
        taskTwo.markAsDone();
        projectTwo.addTask(taskOne);
        projectTwo.addTask(taskTwo);
        projects.add(projectTwo);

        Project projectThree = new Project("CS2113 Tutorial");
        projects.add(projectThree);
    }

    @Test
    void executeCommand_validIndexAndDone_addActualTime() throws EzExceptions {
        HashMap<String, String> params = new HashMap<>();
        params.put("t", "2");
        params.put("h", "4");
        params.put("m", "3");
        ActualTimeCommand newCommand = new ActualTimeCommand(params, 1);
        String actualOutput = newCommand.executeCommand(projects, new ArrayList<>());

        String expectedOutput = "Task \"test2\" took 4 hours and 3 minutes to be completed.";
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void executeCommand_invalidTaskIndex_invalidIdException() throws EzExceptions {
        HashMap<String, String> params = new HashMap<>();
        params.put("t", "5");
        params.put("h", "4");
        params.put("m", "3");
        ActualTimeCommand newCommand = new ActualTimeCommand(params, 1);
        Throwable actualOutputException = assertThrows(EzExceptions.class,
            () -> newCommand.executeCommand(projects, new ArrayList<>()));
        String expectedOutput = "Task ID does not exist!";
        assertEquals(expectedOutput, actualOutputException.toString());
    }

    @Test
    void executeCommand_taskNotDone_notDoneException() throws EzExceptions {
        HashMap<String, String> params = new HashMap<>();
        params.put("t", "1");
        params.put("h", "4");
        params.put("m", "3");
        ActualTimeCommand newCommand = new ActualTimeCommand(params, 1);
        Throwable actualOutputException = assertThrows(EzExceptions.class,
            () -> newCommand.executeCommand(projects, new ArrayList<>()));
        String expectedOutput = "Task must be marked as done before adding actual duration taken to complete!";
        assertEquals(expectedOutput, actualOutputException.toString());
    }

}