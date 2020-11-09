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

class EstimatedTimeCommandTest {
    static ArrayList<Project> projects;

    @BeforeAll
    static void createProjectsTasks() {
        projects = new ArrayList<>();
        Project projectOne = new Project("Drink Water");
        Project projectTwo = new Project("Fire");
        Task taskOne = new Task("test1");
        Task taskTwo = new Task("test2");
        projectTwo.addTask(taskOne);
        projectTwo.addTask(taskTwo);
        Project projectThree = new Project("CS2113 Tutorial");
        projects.add(projectOne);
        projects.add(projectTwo);
        projects.add(projectThree);
    }

    @Test
    void executeCommand_validTaskIndex_addEstimatedTime() throws EzExceptions {
        HashMap<String, String> params = new HashMap<>();
        params.put("t", "2");
        params.put("h", "4");
        params.put("m", "3");
        EstimatedTimeCommand newCommand = new EstimatedTimeCommand(params, 1);
        String actualOutput = newCommand.executeCommand(projects, new ArrayList<>());

        String expectedOutput = "Task \"test2\" has estimated time of 4 hours and 3 minutes";
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void executeCommand_invalidTaskIndex_invalidIdException() throws EzExceptions {
        HashMap<String, String> params = new HashMap<>();
        params.put("t", "5");
        params.put("h", "4");
        params.put("m", "3");
        EstimatedTimeCommand newCommand = new EstimatedTimeCommand(params, 1);
        Throwable actualOutputException = assertThrows(EzExceptions.class,
            () -> newCommand.executeCommand(projects, new ArrayList<>()));
        String expectedOutput = "Task ID does not exist!";
        assertEquals(expectedOutput, actualOutputException.toString());
    }

}