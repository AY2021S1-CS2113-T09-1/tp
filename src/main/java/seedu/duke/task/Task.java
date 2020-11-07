package seedu.duke.task;

import seedu.duke.DukeExceptions;
import seedu.duke.member.TeamMember;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Task {
    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    protected String description;
    protected boolean isDone;
    protected LocalDate date;
    protected ArrayList<TeamMember> members = new ArrayList<>();
    protected int estimateInMinutes = 0;
    protected int actualInMinutes;
    protected int priority;

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.date = null;
        this.priority = 0;
        actualInMinutes = 0;
    }

    public void addDeadline(LocalDate date) {
        this.date = date;
    }

    public void addEstimate(int durationInMinutes) {
        this.estimateInMinutes = durationInMinutes;
    }

    public int getEstimate() {
        return estimateInMinutes;
    }

    public int getActual() {
        return actualInMinutes;
    }

    public void addActual(int durationInMinutes) {
        this.actualInMinutes = durationInMinutes;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getTaskDescription() {
        return description;
    }

    public void setTaskDescription(String newDescription) {
        description = newDescription;
    }

    public String getDateString() {
        return (date != null) ? date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "";
    }


    public LocalDate getDeadline() {
        return this.date;
    }

    public ArrayList<TeamMember> getMembers() {
        return members;
    }

    public void setMember(TeamMember newMember) throws DukeExceptions {
        for (TeamMember member : members) {
            if (newMember == member) {
                throw new DukeExceptions("TaskAlreadyAssigned");
            }
        }
        members.add(newMember);
    }

    public boolean getStatus() {
        return isDone;
    }

    /**
     * Returns details of the specified task.
     *
     * @return Details of task
     */
    @Override
    public String toString() {
        String taskStatus = description;
        if (date != null) {
            taskStatus += " | " + getDateString();
        }
        if (estimateInMinutes != 0) {
            int hours = estimateInMinutes / 60;
            int minutes = estimateInMinutes % 60;
            taskStatus += " | " + "Estimated: " + hours + " hours " + minutes + " minutes";
        }

        if (actualInMinutes != 0) {
            int hours = actualInMinutes / 60;
            int minutes = actualInMinutes % 60;
            taskStatus += " | " + "Actual: " + hours + " hours " + minutes + " minutes";
        }
        return taskStatus;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            Task task = (Task) obj;
            return ((task.description.equals(this.description))
                    && (task.isDone == this.isDone) && (task.date.equals(this.date)));
        } else {
            return false;
        }
    }


}
