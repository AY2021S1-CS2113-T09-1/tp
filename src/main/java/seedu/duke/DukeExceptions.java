package seedu.duke;

import java.util.HashMap;

public class DukeExceptions extends Exception {

    private HashMap<String,String> exceptionMessageList = new HashMap<>();
    private String messageType;

    public DukeExceptions(String messageType) {
        initializeMap();
        this.messageType = messageType;
    }


    public void initializeMap() {
        exceptionMessageList.put("Project","Command incomplete!, You need to include a project description.");
        exceptionMessageList.put("Delete Project","Command incomplete! You need to mention the project Id");
        exceptionMessageList.put("Task Description","Command incomplete!, You need to include a task description.");
        exceptionMessageList.put("Member","Command incorect! You need to mention Member's name");
        exceptionMessageList.put("Delete Task","Command incomplete! You need to mention the project Id");
        exceptionMessageList.put("Switch","You are already in project view");
        exceptionMessageList.put("Add Project","You cannot add tasks in project view");
        exceptionMessageList.put("Add Task","You cannot add projects in task view");
        exceptionMessageList.put("WrongDateFormat", "Date must be specified in format YYYY-MM-DD");
        exceptionMessageList.put("IndexNotFound", "Index not specified");
        //file handler exceptions
        exceptionMessageList.put("Create File","The file could not be created");
        exceptionMessageList.put("Open File","The file could not be opened");
        exceptionMessageList.put("default","OOPS!!! I'm sorry, but I don't know what that means :-(");
        exceptionMessageList.put("mustBeInHomeView","You must be in Home View to do that!");
        exceptionMessageList.put("mustBeInProjectView","You must be in Project View to do that!");
        exceptionMessageList.put("emptyProjectList","Project list is empty!");
        exceptionMessageList.put("invalidTaskID","Task ID does not exist!");
        exceptionMessageList.put("invalidTeamMemberID","Team Member ID does not exist!");
        exceptionMessageList.put("invalidProjectID","Project ID does not exist!");
        exceptionMessageList.put("taskNotDone","Task must be marked as done before adding "
                + "actual duration taken to complete!");
        exceptionMessageList.put("emptyTeamMembersList","Team Members list is empty!");
        exceptionMessageList.put("emptyTaskList","Task list is empty!");
        exceptionMessageList.put("indexNonInteger","Index must be an integer!");
        exceptionMessageList.put("multipleParams",
                "Multiple parameters detected! Please enter command in the correct format");
        exceptionMessageList.put("paramValueIncorrect","Please only provide appropriate parameters!"
                        + " e.g p/<positive integer> t/<positive integer>");
    }

    public String toString() {
        return exceptionMessageList.get(messageType);
    }
}
