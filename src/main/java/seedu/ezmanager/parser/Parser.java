//@@author thatseant

package seedu.ezmanager.parser;

import seedu.ezmanager.util.EzExceptions;
import seedu.ezmanager.util.EzLogger;
import seedu.ezmanager.commands.Command;
import seedu.ezmanager.commands.ExitCommand;
import seedu.ezmanager.commands.PrintHomeViewCommand;
import seedu.ezmanager.commands.HomeCommand;
import seedu.ezmanager.commands.member.AssignMemberToProjectCommand;
import seedu.ezmanager.commands.member.TeamMemberAddCommand;
import seedu.ezmanager.commands.member.TeamMemberAssignToTaskCommand;
import seedu.ezmanager.commands.member.TeamMemberDeleteCommand;
import seedu.ezmanager.commands.member.TeamMemberHoursCommand;
import seedu.ezmanager.commands.project.ProjectDeadlineCommand;
import seedu.ezmanager.commands.project.ProjectDescriptionCommand;
import seedu.ezmanager.commands.project.ProjectDeleteCommand;
import seedu.ezmanager.commands.project.ProjectCommand;
import seedu.ezmanager.commands.project.ProjectSelectCommand;
import seedu.ezmanager.commands.project.ProjectDoneCommand;
import seedu.ezmanager.commands.task.TaskAssignPriorityCommand;
import seedu.ezmanager.commands.task.TaskDeleteCommand;
import seedu.ezmanager.commands.task.TaskListCommand;
import seedu.ezmanager.commands.task.TaskCommand;
import seedu.ezmanager.commands.task.TaskDoneCommand;
import seedu.ezmanager.commands.task.TaskAssignDeadlineCommand;
import seedu.ezmanager.commands.task.TaskEditCommand;
import seedu.ezmanager.commands.task.ActualTimeCommand;
import seedu.ezmanager.commands.task.EstimatedTimeCommand;
import seedu.ezmanager.commands.task.TaskSortCommand;
import java.util.HashMap;

import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static int projectIndex = -1;


    public static void setProjectIndex(int newIndex) {
        projectIndex = newIndex;
    }

    public static int getProjectIndex() {
        return projectIndex;
    }

    public static HashMap<String, String> getParams(String paramsString) throws EzExceptions {
        EzLogger.log(Level.INFO, "Getting parameters from parser");
        HashMap<String, String> inputParams = new HashMap<>();
        Pattern p = Pattern.compile("./.+?(?=\\s./.+)|./.+"); //Regex to extract parameter terms
        Matcher m = p.matcher(paramsString);
        while (m.find()) {
            EzLogger.log(Level.INFO, "Parameter: " + m.group() + " found.");
            String[] keyAndValue = m.group().split("/");
            if (keyAndValue.length != 2) {
                EzLogger.log(Level.WARNING, "Parameter: " + m.group() + "contains multiple slashes!");
                throw new EzExceptions("forwardSlashError");
            }
            String paramType = keyAndValue[0].toLowerCase();
            String paramValue = keyAndValue[1];
            if (inputParams.containsKey(paramType)) {
                EzLogger.log(Level.WARNING, "Parameter: " + m.group() + "is a duplicate.");
                throw new EzExceptions("duplicateParams");
            }
            inputParams.put(paramType, paramValue);
        }
        return inputParams;
    }

    public static String getHashValue(HashMap<String, String> hashmap, String key) throws EzExceptions {
        if (!hashmap.containsKey(key)) {
            EzLogger.log(Level.WARNING, "Parameter: " + key + "is missing.");
            throw new EzExceptions("missingParameters");
        } else {
            return hashmap.get(key);
        }
    }


    /**
     * Parses user input into project command for execution.
     *
     * @param inputCommand Full user input command string
     * @return Command object corresponding to the input command of the user
     */
    public static Command parse(String inputCommand) throws EzExceptions {
        EzLogger.log(Level.INFO, "Parsing Command");
        String[] inputWords = inputCommand.split("\\s+", 2); //Splits command into type and parameters
        String commandType = inputWords[0].toLowerCase();

        EzLogger.log(Level.INFO, "Command Type:" + commandType);

        HashMap<String, String> params = new HashMap<>();
        if (inputWords.length == 2) {
            params = getParams(inputWords[1]);
        }

        boolean isHomeView = (projectIndex == -1); //In main project list view

        EzLogger.log(Level.INFO, "Project Index currently :" + projectIndex);

        Command command = getCommand(isHomeView, commandType, params, projectIndex, inputWords);
        return command;
    }

    public static Command getCommand(boolean isHomeView, String commandType, HashMap<String, String> params,
                                     int projectIndex, String[] inputWords) throws EzExceptions {
        Command command;

        switch (commandType) {
        case "list":
            if (inputWords.length == 2) {
                throw new EzExceptions("incorrectListCommand");
            }
            command = (isHomeView)
                    ? new PrintHomeViewCommand() : new TaskListCommand(projectIndex);
            break;
        case "select":
            if (!isHomeView) {
                throw new EzExceptions("mustBeInHomeView");
            }
            command = new ProjectSelectCommand(params);
            break;
        case "description":
            if (!isHomeView) {
                throw new EzExceptions("mustBeInHomeView");
            }
            command = new ProjectDescriptionCommand(params);
            break;
        case "project":
            if (!isHomeView) {
                throw new EzExceptions("mustBeInHomeView");
            }
            command = new ProjectCommand(params);
            break;
        case "task":
            if (isHomeView) {
                throw new EzExceptions("mustBeInProjectView");
            }
            command = new TaskCommand(params, projectIndex);
            break;
        case "edit":
            if (isHomeView) {
                throw new EzExceptions("mustBeInProjectView");
            }
            command = new TaskEditCommand(params, projectIndex);
            break;
        case "done":
            command = (isHomeView)
                    ? new ProjectDoneCommand(params, projectIndex) : new TaskDoneCommand(params, projectIndex);
            break;
        case "deadline":
            command = (isHomeView)
                    ? new ProjectDeadlineCommand(params) : new TaskAssignDeadlineCommand(params, projectIndex);
            break;
        case "delete":
            command = (isHomeView)
                    ? new ProjectDeleteCommand(params) : new TaskDeleteCommand(params, projectIndex);
            break;
        case "actual":
            command = new ActualTimeCommand(params, projectIndex);
            break;
        case "estimate":
            command = new EstimatedTimeCommand(params, projectIndex);
            break;
        case "home":
            command = new HomeCommand(projectIndex);
            break;
        case "member":
            if (!isHomeView) {
                throw new EzExceptions("mustBeInHomeView");
            }
            command = new TeamMemberAddCommand(params);
            break;
        case "remove":
            command = new TeamMemberDeleteCommand(params, projectIndex);
            break;
        case "assign":
            command = (isHomeView)
                    ? new AssignMemberToProjectCommand(params, projectIndex)
                    : new TeamMemberAssignToTaskCommand(params, projectIndex);
            break;
        case "priority":
            if (isHomeView) {
                throw new EzExceptions("mustBeInProjectView");
            }
            command = new TaskAssignPriorityCommand(params, projectIndex);
            break;
        case "bye":
            command = new ExitCommand();
            break;
        case "sort":
            if (isHomeView) {
                throw new EzExceptions("mustBeInProjectView");
            }
            command = new TaskSortCommand(params, projectIndex);
            break;
        case "hours":
            if (!isHomeView) {
                throw new EzExceptions("mustBeInHomeView");
            }
            command = new TeamMemberHoursCommand(params, projectIndex);
            break;
        default:
            EzLogger.log(Level.WARNING, "Unrecognised Command :" + commandType);
            throw new EzExceptions("unrecognisedCommand");
        }

        return command;
    }



}


