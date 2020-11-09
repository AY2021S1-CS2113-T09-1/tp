package seedu.ezmanager.commands.task;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.ezmanager.util.EzExceptions;
import seedu.ezmanager.project.Project;
import seedu.ezmanager.task.Task;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskCommandTest {
    static ArrayList<Project> projects;

    @BeforeAll
    static void createProjectList() {
        projects = new ArrayList<>();
        Project projectOne = new Project("Drink Water");
        projects.add(projectOne);

        Project projectTwo = new Project("Fire");
        Task taskOne = new Task("test1");
        Task taskTwo = new Task("test2");
        projectTwo.addTask(taskOne);
        projectTwo.addTask(taskTwo);
        projects.add(projectTwo);

        Project projectThree = new Project("CS2113 Tutorial");
        projects.add(projectThree);
    }

    @Test
    void executeCommand_existingTasks_taskCreated() throws EzExceptions {
        HashMap<String, String> params = new HashMap<>();
        params.put("n", "Task 3");
        TaskCommand createTask = new TaskCommand(params, 1);
        String actualOutput = createTask.executeCommand(projects, new ArrayList<>());
        String expectedOutput = "Task \"Task 3\" created!";
        assertEquals(expectedOutput, actualOutput);
    }
}