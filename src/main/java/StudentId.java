public class StudentId {
    int cardNumber;

    public StudentId(int cardNumber) {
        this.cardNumber = cardNumber;
    }
    @Override
    public int hashCode() {
        return cardNumber;
    }
    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof StudentId)) return false;
        return ((StudentId) obj).cardNumber == this.cardNumber;
    }
}
