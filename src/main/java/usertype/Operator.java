package usertype;

public class Operator extends BaseUserType {
    private Boolean baseP = false;
    private Boolean stockP = false;
    private Boolean saleP = false;
    private Boolean wmP = false;

    public Operator(String name,String password){
        super(name, password);
    }

    public Operator(String name, String password, Boolean baseP, Boolean stockP, Boolean saleP, Boolean wmP) {
        super(name, password);
        this.baseP = baseP;
        this.stockP = stockP;
        this.saleP = saleP;
        this.wmP = wmP;
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
}
