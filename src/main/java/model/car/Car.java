package model.car;

public class Car {
    private String manufacturer;
    private String model;
    private int origin_price;
    private String color;

    public Car(String manufacturer, String model, int origin_price, String color) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.origin_price = origin_price;
        this.color = color;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getOrigin_price() {
        return origin_price;
    }

    public void setOrigin_price(int origin_price) {
        this.origin_price = origin_price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
