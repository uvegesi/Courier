package Courier;

public class Courier {

    private int day;
    private int deliveryNum;
    private int distance;

    public Courier(int day, int deliveryNum, int distance) {
        this.day = day;
        this.deliveryNum = deliveryNum;
        this.distance = distance;
    }

    public int getDay() {

        return day;
    }

    public int getDeliveryNum() {
        return deliveryNum;
    }

    public int getDistance() {
        return distance;
    }
}
