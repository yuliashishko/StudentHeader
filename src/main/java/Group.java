public class Group {
    private GroupId groupId;
    private String name;

    public Group(GroupId groupId, String name) {
        this.groupId = groupId;
        this.name = name;
    }

    public GroupId getGroupId() {
        return groupId;
    }

    public String getName() {
        return name;
    }
}
