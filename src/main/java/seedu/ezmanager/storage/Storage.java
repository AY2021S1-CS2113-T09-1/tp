package seedu.ezmanager.storage;

import seedu.ezmanager.util.EzExceptions;
import seedu.ezmanager.member.TeamMember;
import seedu.ezmanager.project.Project;
import seedu.ezmanager.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Storage {
    private static File f;
    private static ArrayList<Project> temp = new ArrayList<>();

    public Storage(String filePath) {
        f = new File(filePath);
    }

    /*
        protected String projectName;
        protected boolean isDone;
        private ArrayList<Task> tasks;
        private String projectDescription;
        private LocalDate projectDeadline;
        private static ArrayList<TeamMember> members;
     */
    public static ArrayList<Project> loadProjects(ArrayList<TeamMember> members) throws EzExceptions {
        try {
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            ArrayList<Project> projects = new ArrayList<>();
            String currentLine;
            while (s.hasNext()) {
                currentLine = s.nextLine();
                if (currentLine.contains("Project")) {
                    String projectName = currentLine.replaceFirst("Project", "").trim();
                    boolean status = Boolean.parseBoolean(s.nextLine().split(" ")[1]);
                    String projectDescription = s.nextLine().replaceFirst("projectDescription", "");
                    String deadline = s.nextLine().replaceFirst("projectDeadline", "").trim();

                    Project newProject = new Project(projectName);
                    if (status) {
                        newProject.markAsDone();
                    }

                    newProject.addDescription(projectDescription.trim());


                    if (!deadline.equals("null")) {
                        LocalDate deadlineDate = LocalDate.parse(deadline);
                        newProject.addProjectDeadline(deadlineDate);
                    }

                    // begin adding tasks to project
                    currentLine = s.nextLine().trim();
                    while (!currentLine.equals("endTasks")) {
                        if (currentLine.equals("startTasks")) {
                            currentLine = s.nextLine();
                            continue;
                        }

                        int taskDescriptionEndIndex = currentLine.indexOf("|");

                        if (taskDescriptionEndIndex != -1) {
                            Task newTask = new Task(currentLine.substring(0, taskDescriptionEndIndex - 1).trim());
                            String taskString = currentLine.substring(taskDescriptionEndIndex + 2);

                            // is task done?
                            int taskDoneStartIndex = taskString.indexOf("tS");
                            int taskDoneEndIndex = taskString.indexOf("tE");
                            if (taskDoneStartIndex != -1) {
                                String isTaskDoneString = taskString.substring(taskDoneStartIndex + 2, taskDoneEndIndex)
                                        .trim();
                                boolean isTaskDone = isTaskDoneString.equals("1") ? true : false;
                                if (isTaskDone) {
                                    newTask.markAsDone();
                                }

                            }

                            // extract deadline
                            int deadlineStartIndex = taskString.indexOf("dS");
                            int deadlineEndIndex = taskString.indexOf("dE");
                            if (deadlineStartIndex != -1 && (deadlineEndIndex - deadlineStartIndex) > 4) {
                                newTask.addDeadline(
                                        LocalDate.parse(
                                                taskString.substring(deadlineStartIndex + 3, deadlineEndIndex
                                                )
                                        )
                                );
                            }

                            taskString = taskString.substring(taskString.indexOf("|") + 1);

                            int priorityStartIndex = taskString.indexOf("pS");
                            int priorityEndIndex = taskString.indexOf("pE");
                            if (priorityStartIndex != -1 && (priorityEndIndex - priorityStartIndex) > 4) {
                                String priority = taskString.substring(priorityStartIndex + 3, priorityEndIndex).trim();
                                if (priority.length() >= 1) {
                                    newTask.setPriority(Integer.parseInt(priority));
                                }
                            }

                            int estimateStartIndex = taskString.indexOf("eMS");
                            int estimateEndIndex = taskString.indexOf("eME");
                            if (estimateStartIndex != -1 && (estimateEndIndex - estimateStartIndex) > 6) {
                                String estimate = taskString.substring(estimateStartIndex + 3, estimateEndIndex);
                                if (estimate.length() > 1) {
                                    newTask.addEstimate(Integer.parseInt(estimate.trim()));
                                }
                            }

                            int actualStartIndex = taskString.indexOf("aMS");
                            int actualEndIndex = taskString.indexOf("aME");
                            if (actualStartIndex != -1 && (actualEndIndex - actualStartIndex) > 6) {
                                String actual = taskString.substring(actualStartIndex + 3, actualEndIndex);
                                if (actual.length() > 1) {
                                    newTask.addActual(Integer.parseInt(actual.trim()));
                                }
                            }

                            currentLine = s.nextLine();
                            if (currentLine.equals("tMS")) {
                                currentLine = s.nextLine();
                                while (!currentLine.equals("tME")) {
                                    String memberName = currentLine;
                                    TeamMember member = getMember(memberName, members);
                                    newTask.setMember(member);
                                    currentLine = s.nextLine();
                                }
                                currentLine = s.nextLine();
                            }

                            newProject.addTask(newTask);
                        }

                        if (currentLine.equals("endTasks")) {
                            break;
                        }
                    }
                    // end adding tasks to project

                    // begin adding team members
                    currentLine = s.nextLine();
                    int projectMemberStartIndex = currentLine.indexOf("pMS");
                    if (projectMemberStartIndex != -1) {
                        while (!currentLine.equals("pME")) {
                            if (currentLine.equals("pMS")) {
                                currentLine = s.nextLine();
                                continue;
                            }
                            TeamMember member = getMember(currentLine, members);
                            if (member != null) {
                                newProject.addTeamMemberToProject(member);
                                member.assignProject(newProject);
                            }
                            currentLine = s.nextLine();
                        }
                    }
                    // end adding team members

                    projects.add(newProject);
                    if (s.hasNextLine()) {
                        currentLine = s.nextLine();
                    }
                }
                /*
                else {
                    if (s.hasNextLine()) {
                        currentLine = s.nextLine();
                    }
                }
                 */
            }

            return projects;
        } catch (FileNotFoundException | EzExceptions e) {
            //System.out.println("Creating file...");
        }

        return temp;
    }

    public ArrayList<TeamMember> loadTeamMembers() {
        try {
            Scanner s = new Scanner(f);
            String currentLine = s.nextLine();
            ArrayList<TeamMember> members = new ArrayList<>();

            while (!currentLine.equals("Project") && currentLine.length() > 1) {
                if (currentLine.trim().equals("Members")) {
                    currentLine = s.nextLine();
                    continue;
                }
                TeamMember newMember = new TeamMember(currentLine.trim());
                members.add(newMember);
                currentLine = s.nextLine();
            }

            return members;
        } catch (FileNotFoundException | NoSuchElementException e) {
            System.out.println("Creating file...");
        }
        return new ArrayList<>();
    }

    public static TeamMember getMember(String name, ArrayList<TeamMember> members) {
        for (TeamMember member : members) {
            if (member.getName().equals(name)) {
                return member;
            }
        }

        return null;
    }

    public static void save(ArrayList<Project> projects, ArrayList<TeamMember> members) throws IOException {
        // empty current saved items;
        FileWriter clear = new FileWriter(f);
        clear.write("");
        clear.close();

        FileWriter fw = new FileWriter(f, true);
        fw.write("Members \n");
        for (TeamMember member : members) {
            fw.write(member.saveFormat() + "\n");
        }

        fw.write("\n");

        for (Project project : projects) {
            fw.write(project.saveFormat() + "\n");
        }

        fw.close();
    }

}
