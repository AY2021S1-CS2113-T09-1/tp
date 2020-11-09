package seedu.ezmanager;

import seedu.ezmanager.commands.Command;
import seedu.ezmanager.member.TeamMember;
import seedu.ezmanager.parser.Parser;
import seedu.ezmanager.project.Project;
import seedu.ezmanager.storage.Storage;
import seedu.ezmanager.ui.Ui;
import seedu.ezmanager.util.EzExceptions;
import seedu.ezmanager.util.EzLogger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class EzManager {

    /**
     * Main entry-point for the java.duke.EZManager application.
     */
    private static ArrayList<TeamMember> teamMembers = new ArrayList<>();
    private static ArrayList<Project> projects =  new ArrayList<>();
    private Storage storage;
    private Ui ui;

    public EzManager(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            ArrayList<TeamMember> loadedMembers = storage.loadTeamMembers();
            for (TeamMember member : loadedMembers) {
                teamMembers.add(member);
            }

            ArrayList<Project> loadedProjects = storage.loadProjects(teamMembers);
            for (Project project : loadedProjects) {
                projects.add(project);
            }

        } catch (EzExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    public static void run() {
        Ui ui = new Ui();
        ui.printWelcome();

        EzLogger.setup();

        Scanner in = new Scanner(System.in);

        boolean isExit = false;
        while (!isExit) {
            try {
                Command commandInput = Parser.parse(in.nextLine());
                ui.printLine();
                String output = commandInput.executeCommand(projects, teamMembers);
                ui.printOutput(output);
                Storage.save(projects, teamMembers);
                isExit = commandInput.isExit();
            } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException
                    | EzExceptions | IOException e) {
                System.out.println(e);
            }
            ui.printLine();
        }

        try {
            Storage.save(projects, teamMembers);
        } catch (IOException e) {
            System.err.println("Couldn't save before terminating.");
        }

    }

    public static void main(String[] args) throws EzExceptions {
        new EzManager("ezmanager.txt").run();
        final Thread mainThread = Thread.currentThread();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                Storage.save(projects, teamMembers);
            } catch (IOException e) {
                System.err.println("Couldn't save before terminating.");
            }
        }));

    }

}
