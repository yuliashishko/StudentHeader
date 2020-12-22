public class GroupId {
    int id;

    public GroupId(int id) {
        this.id = id;
    }
    @Override
    public int hashCode() {
        return id;
    }
    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof GroupId)) return false;
        return (((GroupId) obj).id == this.id);
    }
}
