package seedu.ezmanager.commands.task;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.ezmanager.util.EzExceptions;
import seedu.ezmanager.member.TeamMember;
import seedu.ezmanager.project.Project;
import seedu.ezmanager.task.Task;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TaskDeleteCommandTest {
    static ArrayList<Project> projects;
    static ArrayList<TeamMember> teamMembers;
    static HashMap<String, String> params;

    /**
     * Create 1 projects list
     * Create 1 project.
     * Create 3 tasks.
     * Add the 3 tasks to that project.
     * Add that project to projects list.
     */
    @BeforeAll
    static void testSetup() {
        params = new HashMap<>();
        teamMembers = new ArrayList<>();
        projects = new ArrayList<>();
        Project projectOne = new Project("Project One");
        Task taskOne = new Task("Task One");
        Task taskTwo = new Task("Task Two");
        Task taskThree = new Task("Task Three");
        projectOne.addTask(taskOne);
        projectOne.addTask(taskTwo);
        projectOne.addTask(taskThree);
        projects.add(projectOne);
    }

    /**
     * Test: Delete the first task from the first project.
     * Check for message.
     * Check if task has been deleted from task list by checking task list length;
     */
    @Test
    void executeCommand_validTaskId_taskDescription() throws EzExceptions {
        params.put("t", "1");
        TaskDeleteCommand command = new TaskDeleteCommand(params,0);
        String expectedOutput0 = "Task \"Task One\" removed!";
        String actualOutput0 = command.executeCommand(projects, teamMembers);
        assertEquals(expectedOutput0, actualOutput0);

        int expectedOutput1 = 2;
        int actualOutput1 = projects.get(0).getTaskList().size();
        assertEquals(expectedOutput1, actualOutput1);
    }

    /**
     * Test: Input a non-existent task ID to test exception error message.
     */
    @Test
    void executeCommand_nonExistentTaskId_taskDescription() throws EzExceptions {
        params.put("t", "4");
        TaskDeleteCommand command = new TaskDeleteCommand(params,0);
        String expectedOutput = "Task ID does not exist!";
        EzExceptions exception = assertThrows(EzExceptions.class, () ->
                command.executeCommand(projects, teamMembers));
        assertEquals(expectedOutput, exception.toString());
    }

    /**
     * Test: Pass a non existent projects list to test exception error message.
     */
    @Test
    void executeCommand_nonExistentProjectsList_taskDescription() throws EzExceptions {
        params.put("t", "1");
        TaskDeleteCommand command = new TaskDeleteCommand(params,0);
        String expectedOutput = "Project list is empty!";
        EzExceptions exception = assertThrows(EzExceptions.class, () ->
                command.executeCommand(new ArrayList<Project>(), teamMembers));
        assertEquals(expectedOutput, exception.toString());
    }
}