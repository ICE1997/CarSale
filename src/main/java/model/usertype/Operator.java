package model.usertype;

import java.util.LinkedList;

public class Operator {
    private String workNum;
    private Boolean baseP;
    private Boolean stockP;
    private Boolean saleP;
    private Boolean wmP;
    private String name;
    private boolean gender;
    private String age;
    private String birthday;
    private String id_num;
    private String phone_num;
    private String address;
    private LinkedList<Boolean> permissions;

    public Operator(String workNum, String name, boolean gender, String age, String birthday, String id_num, String phone_num, String address,Boolean baseP, Boolean stockP, Boolean saleP, Boolean wmP) {
        this.workNum = workNum;
        this.baseP = baseP;
        this.stockP = stockP;
        this.saleP = saleP;
        this.wmP = wmP;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.birthday = birthday;
        this.id_num = id_num;
        this.phone_num = phone_num;
        this.address = address;
    }

    public Operator(String workNum, Boolean baseP, Boolean stockP, Boolean saleP, Boolean wmP) {
        this.workNum = workNum;
        this.baseP = baseP;
        this.stockP = stockP;
        this.saleP = saleP;
        this.wmP = wmP;
    }

    public Operator(String workNum, String name, boolean gender, String age, String birthday, String id_num, String phone_num, String address, LinkedList<Boolean> permissions) {
        this.workNum = workNum;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.birthday = birthday;
        this.id_num = id_num;
        this.phone_num = phone_num;
        this.address = address;
        this.permissions = permissions;
        baseP = permissions.get(0);
        stockP = permissions.get(1);
        saleP = permissions.get(2);
        wmP = permissions.get(3);
    }

    public String getWorkNum() {
        return workNum;
    }

    public void setWorkNum(String workNum) {
        this.workNum = workNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGirl() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getId_num() {
        return id_num;
    }

    public void setId_num(String id_num) {
        this.id_num = id_num;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LinkedList<Boolean> getPermissions() {
        return permissions;
    }

    public void setPermissions(LinkedList<Boolean> permissions) {
        this.permissions = permissions;
    }

//将被弃用
    public Boolean getBaseP() {
        return baseP;
    }

    public void setBaseP(Boolean baseP) {
        this.baseP = baseP;
    }

    public Boolean getStockP() {
        return stockP;
    }

    public void setStockP(Boolean stockP) {
        this.stockP = stockP;
    }

    public Boolean getSaleP() {
        return saleP;
    }

    public void setSaleP(Boolean saleP) {
        this.saleP = saleP;
    }

    public Boolean getWmP() {
        return wmP;
    }

    public void setWmP(Boolean wmP) {
        this.wmP = wmP;
    }
}
