package cn.flik1337.lotteryHelper.dto;

public class AwardDto {
    private Integer type;
    private String name;
    private Integer level;
    private String cover;
    private Integer number;
    private Integer singleMoney;
    private Integer countMoney;
    private String exchangeCode;
    private Integer receiveTypeCode;
    private String receiveTypeName;


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getSingleMoney() {
        return singleMoney;
    }

    public void setSingleMoney(Integer singleMoney) {
        this.singleMoney = singleMoney;
    }

    public Integer getCountMoney() {
        return countMoney;
    }

    public void setCountMoney(Integer countMoney) {
        this.countMoney = countMoney;
    }

    public String getExchangeCode() {
        return exchangeCode;
    }

    public void setExchangeCode(String exchangeCode) {
        this.exchangeCode = exchangeCode;
    }

    public Integer getReceiveTypeCode() {
        return receiveTypeCode;
    }

    public void setReceiveTypeCode(Integer receiveTypeCode) {
        this.receiveTypeCode = receiveTypeCode;
    }

    public String getReceiveTypeName() {
        return receiveTypeName;
    }

    public void setReceiveTypeName(String receiveTypeName) {
        this.receiveTypeName = receiveTypeName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }


    @Override
    public String toString() {
        return "AwardDto{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", level='" + level + '\'' +
                ", cover='" + cover + '\'' +
                ", number=" + number +
                ", singleMoney=" + singleMoney +
                ", countMoney=" + countMoney +
                ", exchangeCode='" + exchangeCode + '\'' +
                ", receiveTypeCode=" + receiveTypeCode +
                ", receiveTypeName='" + receiveTypeName + '\'' +
                '}';
    }
}
