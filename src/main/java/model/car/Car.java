package model.car;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Car {
    private final SimpleStringProperty num;
    private final SimpleStringProperty manufacturer;
    private final SimpleStringProperty color;
    private final SimpleStringProperty c_name;
    private final SimpleIntegerProperty c_age;
    private final SimpleStringProperty c_gender;
    private final SimpleStringProperty c_phone;

    public Car(String num, String manufacturer, String color, String c_name, int c_age, String c_gender, String c_phone) {
        this.num = new SimpleStringProperty(num);
        this.manufacturer = new SimpleStringProperty(manufacturer);
        this.color = new SimpleStringProperty(color);
        this.c_name = new SimpleStringProperty(c_name);
        this.c_age = new SimpleIntegerProperty(c_age);
        this.c_gender = new SimpleStringProperty(c_gender);
        this.c_phone = new SimpleStringProperty(c_phone);
    }

    public String getNum() {
        return num.get();
    }

    public SimpleStringProperty numProperty() {
        return num;
    }

    public void setNum(String num) {
        this.num.set(num);
    }

    public String getManufacturer() {
        return manufacturer.get();
    }

    public SimpleStringProperty manufacturerProperty() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer.set(manufacturer);
    }

    public String getColor() {
        return color.get();
    }

    public SimpleStringProperty colorProperty() {
        return color;
    }

    public void setColor(String color) {
        this.color.set(color);
    }

    public String getC_name() {
        return c_name.get();
    }

    public SimpleStringProperty c_nameProperty() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name.set(c_name);
    }

    public int getC_age() {
        return c_age.get();
    }

    public SimpleIntegerProperty c_ageProperty() {
        return c_age;
    }

    public void setC_age(int c_age) {
        this.c_age.set(c_age);
    }

    public String getC_gender() {
        return c_gender.get();
    }

    public SimpleStringProperty c_genderProperty() {
        return c_gender;
    }

    public void setC_gender(String c_gender) {
        this.c_gender.set(c_gender);
    }

    public String getC_phone() {
        return c_phone.get();
    }

    public SimpleStringProperty c_phoneProperty() {
        return c_phone;
    }

    public void setC_phone(String c_phone) {
        this.c_phone.set(c_phone);
    }
}
