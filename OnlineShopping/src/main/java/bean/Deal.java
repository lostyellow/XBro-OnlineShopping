package bean;

//这个是针对transactions表格的数据封装
public class Deal {
    private Integer product_id;
    private String status;
    private String time;
    private Float amount;

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Deal [product_id=" + product_id + ", status=" + status + ", time=" + time + ", amount=" + amount + "]";
    }

    public Deal() {
        super();
        // TODO Auto-generated constructor stub
    }


}
