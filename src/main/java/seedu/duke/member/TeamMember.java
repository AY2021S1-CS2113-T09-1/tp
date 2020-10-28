package seedu.duke.member;

public class TeamMember {
    private String name;
    private int assignedProjectId;

    public TeamMember(String name) {
        this.name = name;
        assignedProjectId = -1;
    }

    public void setAssignedProjectId(int assignedProjectId) {
        this.assignedProjectId = assignedProjectId;
    }

    public int getAssignedProjectId() {
        return assignedProjectId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
