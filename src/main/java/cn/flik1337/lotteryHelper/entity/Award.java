package cn.flik1337.lotteryHelper.entity;

import cn.flik1337.lotteryHelper.common.enums.AwardLevelEnum;
import cn.flik1337.lotteryHelper.common.enums.AwardTypeEnum;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author flik1337
 * @since 2022-04-18
 */
public class Award implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 奖品id
     */
    @TableId(value = "a_id",type = IdType.AUTO)
    private Long aId;

    /**
     * 奖品名称
     */
    private String aName;

    /**
     * 奖品数量
     */
    private Integer aNumber;

    /**
     * 奖品类别
     */
    private AwardTypeEnum aType;

    /**
     * 奖品登记
     */
    private AwardLevelEnum aLevel;

    /**
     * 奖品封面
     */
    private String aCover;

    /**
     * 红包奖品金额
     */
    private Integer aMoney;

    /**
     * 兑换码，以,分隔
     */
    private String exchangeCode;

    /**
     * 规则描述
     */
    private String ruleName;

    /**
     * 所属lottery的id
     */
    private Long lId;

    /**
     * 收货方式
     */
    private Integer receiveType;

    public Long getaId() {
        return aId;
    }

    public void setaId(Long aId) {
        this.aId = aId;
    }
    public String getaName() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }
    public Integer getaNumber() {
        return aNumber;
    }

    public void setaNumber(Integer aNumber) {
        this.aNumber = aNumber;
    }
    public AwardTypeEnum getaType() {
        return aType;
    }

    public void setaType(AwardTypeEnum aType) {
        this.aType = aType;
    }
    public AwardLevelEnum getaLevel() {
        return aLevel;
    }

    public void setaLevel(AwardLevelEnum aLevel) {
        this.aLevel = aLevel;
    }
    public String getaCover() {
        return aCover;
    }

    public void setaCover(String aCover) {
        this.aCover = aCover;
    }
    public Integer getaMoney() {
        return aMoney;
    }

    public void setaMoney(Integer aMoney) {
        this.aMoney = aMoney;
    }
    public String getExchangeCode() {
        return exchangeCode;
    }

    public void setExchangeCode(String exchangeCode) {
        this.exchangeCode = exchangeCode;
    }
    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }
    public Long getlId() {
        return lId;
    }

    public void setlId(Long lId) {
        this.lId = lId;
    }
    public Integer getReceiveType() {
        return receiveType;
    }

    public void setReceiveType(Integer receiveType) {
        this.receiveType = receiveType;
    }

    @Override
    public String toString() {
        return "Award{" +
            "aId=" + aId +
            ", aName=" + aName +
            ", aNumber=" + aNumber +
            ", aType=" + aType +
            ", aLevel=" + aLevel.getName() +
            ", aCover=" + aCover +
            ", aMoney=" + aMoney +
            ", exchangeCode=" + exchangeCode +
            ", ruleName=" + ruleName +
            ", lId=" + lId +
            ", receiveType=" + receiveType +
        "}";
    }
}
