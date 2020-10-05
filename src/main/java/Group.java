public class Group {
    private GroupId groupId;

    public Group(GroupId groupId) {
        this.groupId = groupId;
    }

    public GroupId getGroupId() {
        return groupId;
    }

    public String toStr(){
        return this.groupId.name;
    }
}
