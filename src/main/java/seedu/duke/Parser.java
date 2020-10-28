package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.commands.ExitCommand;
import seedu.duke.commands.HomeCommand;
import seedu.duke.commands.member.AssignMemberToProjectCommand;
import seedu.duke.commands.member.TeamMemberAddCommand;
import seedu.duke.commands.member.TeamMemberAssignToTaskCommand;
import seedu.duke.commands.member.TeamMemberDeleteCommand;
import seedu.duke.commands.member.TeamMembersListCommand;
import seedu.duke.commands.project.ProjectDeadlineCommand;
import seedu.duke.commands.project.ProjectDescriptionCommand;
import seedu.duke.commands.project.ProjectDeleteCommand;
import seedu.duke.commands.project.ProjectCommand;
import seedu.duke.commands.project.ProjectListCommand;
import seedu.duke.commands.project.ProjectSelectCommand;
import seedu.duke.commands.project.ProjectDoneCommand;
import seedu.duke.commands.task.TaskAssignPriorityCommand;
import seedu.duke.commands.task.TaskSelectCommand;
import seedu.duke.commands.task.TaskDeleteCommand;
import seedu.duke.commands.task.TaskListCommand;
import seedu.duke.commands.task.TaskAssignDeadlineCommand;
import seedu.duke.commands.task.TaskDoneCommand;
import seedu.duke.commands.task.ActualTimeCommand;
import seedu.duke.commands.task.EstimatedTimeCommand;
import seedu.duke.commands.task.TaskCommand;

import seedu.duke.ui.Ui;
import java.util.HashMap;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private static final String INPUT_COMMAND_BYE = "bye";
    private static int projectIndex = -1;
    private static int taskIndex = -1;

    /**
     * Parses user input into project command for execution.
     *
     * @param inputCommand Full user input command string
     * @return Command object corresponding to the input command of the user
     */
    public static Command parse(String inputCommand) throws DukeExceptions {
        Command commandType;
        if (inputCommand.equals(INPUT_COMMAND_BYE)) {
            commandType = new ExitCommand();
        } else {
            commandType = checkAction(inputCommand); //----------ADD TRY CATCH (EXCEPTION)
        }
        return commandType;
    }

    public static void setProjectIndex(int newIndex) {
        projectIndex = newIndex;
    }

    public static int getProjectIndex() {
        return projectIndex;
    }

    public static HashMap<String, String> getParams(String paramsString) {
        HashMap<String, String> inputParams = new HashMap<>();
        Pattern p = Pattern.compile(".\\/.+?(?=\\s.\\/.+)|.\\/.+");
        String test = paramsString;
        Matcher m = p.matcher(test);
        while (m.find()) {
            String[] keyAndValue = m.group().split("/");
            String paramType = keyAndValue[0];
            String paramValue = keyAndValue[1];
            inputParams.put(paramType, paramValue);
        }
        return inputParams;
    }

    public static String getHashValue(HashMap<String, String> hashmap, String key) throws DukeExceptions {
        if (!hashmap.containsKey(key)) {
            throw new DukeExceptions("default");
        } else {
            return hashmap.get(key);
        }
    }

    /**
     * Parses user input related to tasks into command for execution.
     *
     * @param inputCommand Full user input command string
     * @return Command object corresponding to the input command of the user
     */
    public static Command checkAction(String inputCommand) throws DukeExceptions {
        Command commandType = null;
        String[] inputs = inputCommand.split("\\s+", 2);
        String taskType = inputs[0];

        HashMap<String, String> params = new HashMap<>();
        if (inputs.length == 2) {
            String paramsString = inputs[1];
            params = getParams(paramsString);
        }

        boolean isHomeView = (projectIndex == -1); //In main project list view

        switch (taskType) {
        case "list":
            commandType = (isHomeView)
                    ? new ProjectListCommand() : new TaskListCommand(projectIndex);
            break;
        case "select":
            commandType = (isHomeView)
                    ? new ProjectSelectCommand(params) : new TaskSelectCommand(params, projectIndex);
            break;
        case "description":
            if (isHomeView) {
                throw new DukeExceptions("mustBeInProjectView");
            }
            commandType = new ProjectDescriptionCommand(params, projectIndex);
            break;
        case "project":
            if (!isHomeView) {
                throw new DukeExceptions("mustBeInHomeView");
            }
            commandType = new ProjectCommand(params);
            break;
        case "task":
            if (isHomeView) {
                throw new DukeExceptions("mustBeInProjectView");
            }
            commandType = new TaskCommand(params, projectIndex);
            break;
        case "done":
            if (isHomeView) {
                commandType = new ProjectDoneCommand(params, projectIndex);
                break;
            }
            commandType = new TaskDoneCommand(params, projectIndex);
            break;
        case "deadline":
            //commandType = getDeadlineCommand(params, isHomeView);
            commandType = (isHomeView)
                    ? new ProjectDeadlineCommand(params) : new TaskAssignDeadlineCommand(params, projectIndex);
            break;
        case "delete":
            commandType = (isHomeView)
                    ? new ProjectDeleteCommand(params) : new TaskDeleteCommand(params, projectIndex);
            break;
        case "actual":
            commandType = new ActualTimeCommand(params, projectIndex);
            break;
        case "estimate":
            commandType = new EstimatedTimeCommand(params, projectIndex);
            break;
        case "home":
            commandType = new HomeCommand(projectIndex);
            break;
        case "member":
            commandType = new TeamMemberAddCommand(params);
            break;
        case "members":
            commandType = new TeamMembersListCommand(isHomeView, projectIndex);
            break;
        case "remove":
            commandType = new TeamMemberDeleteCommand(params);
            break;
        case "assign":
            if (isHomeView) {
                commandType = new AssignMemberToProjectCommand(params, isHomeView);
            } else {
                commandType = new TeamMemberAssignToTaskCommand(params, projectIndex);
            }
            break;
        case "priority":
            if (isHomeView) {
                throw new DukeExceptions("mustBeInProjectView");
            }
            commandType = new TaskAssignPriorityCommand(params, projectIndex);
            break;
        default: throw new DukeExceptions("default");
        }
        return commandType;
    }



}


