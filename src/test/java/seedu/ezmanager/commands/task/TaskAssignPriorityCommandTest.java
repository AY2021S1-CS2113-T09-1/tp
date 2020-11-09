package seedu.ezmanager.commands.task;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.ezmanager.util.EzExceptions;
import seedu.ezmanager.member.TeamMember;
import seedu.ezmanager.project.Project;
import seedu.ezmanager.task.Task;
import seedu.ezmanager.ui.Ui;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TaskAssignPriorityCommandTest {
    static ArrayList<Project> projects;
    static ArrayList<TeamMember> teamMembers;
    static HashMap<String, String> params;
    static Ui ui = new Ui();

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
     * Test: Assign priority HIGH to first task.
     * Check for message.
     */
    @Test
    void executeCommand_validPriorityAndTaskId_priorityAssignedToTask() throws EzExceptions {
        params.put("p", "3");
        params.put("t", "1");
        TaskAssignPriorityCommand command = new TaskAssignPriorityCommand(params, 0);
        String expectedOutput = Ui.printPriorityAssignedToTaskMessage(3, "Task One");
        String actualOutput = command.executeCommand(projects, teamMembers);
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Test: Assign priority HIGH to non existent task.
     */
    @Test
    void executeCommand_nonExistentTaskId_invalidTaskId() throws EzExceptions {
        params.put("p", "1");
        params.put("t", "5");
        TaskAssignPriorityCommand command = new TaskAssignPriorityCommand(params, 0);
        String expectedOutput = "Task ID does not exist!";
        EzExceptions exception = assertThrows(EzExceptions.class, () ->
                command.executeCommand(projects, teamMembers));
        assertEquals(expectedOutput, exception.toString());
    }

}