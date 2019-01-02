package usertype;

public class Operator {
    private String workNum;
    private Boolean baseP;
    private Boolean stockP;
    private Boolean saleP;
    private Boolean wmP;
    private String name;
    private String gender;
    private String age;
    private String birthday;
    private String id_num;
    private String phone_num;
    private String address;

    public Operator(String workNum, Boolean baseP, Boolean stockP, Boolean saleP, Boolean wmP) {
        this.workNum = workNum;
        this.baseP = baseP;
        this.stockP = stockP;
        this.saleP = saleP;
        this.wmP = wmP;
    }

    public Operator(String workNum, Boolean baseP, Boolean stockP, Boolean saleP, Boolean wmP, String name, String gender, String age, String birthday, String id_num, String phone_num, String address) {
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


    public String getWorkNum() {
        return workNum;
    }

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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
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

}
