package domain;

public enum OrderStatus {

    ACCEPTED(1), CONFIRMED(2), FORMING(3), SENT(4), COMPLETE(5), CANCELED(6);

    private int id;

    OrderStatus(int id) {
        this.id = id;
    }
}
