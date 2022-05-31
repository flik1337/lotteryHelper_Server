package cn.flik1337.lotteryHelper.entity;

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
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 地址id
     */
    @TableId(value = "a_id",type = IdType.AUTO)
    private Long aId;

    private Long sendId;

    /**
     * 收货人手机
     */
    private String aPhone;

    /**
     * 收货人姓名
     */
    private String aName;

    /**
     * 省
     */
    private String aProvince;

    /**
     * 市
     */
    private String aCity;

    /**
     * 区
     */
    private String aDistrict;

    /**
     * 邮编
     */
    private String aCode;

    /**
     * 详细地点
     */
    private String aDetail;

    /**
     * 店铺名称
     */
    private String shopName;

    public Long getaId() {
        return aId;
    }

    public void setaId(Long aId) {
        this.aId = aId;
    }
    public String getaPhone() {
        return aPhone;
    }

    public void setaPhone(String aPhone) {
        this.aPhone = aPhone;
    }
    public String getaName() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }
    public String getaProvince() {
        return aProvince;
    }

    public void setaProvince(String aProvince) {
        this.aProvince = aProvince;
    }
    public String getaCity() {
        return aCity;
    }

    public void setaCity(String aCity) {
        this.aCity = aCity;
    }
    public String getaDistrict() {
        return aDistrict;
    }

    public void setaDistrict(String aDistrict) {
        this.aDistrict = aDistrict;
    }

    public String getaDetail() {
        return aDetail;
    }

    public void setaDetail(String aDetail) {
        this.aDetail = aDetail;
    }
    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getaCode() {
        return aCode;
    }

    public void setaCode(String aCode) {
        this.aCode = aCode;
    }

    public Long getSendId() {
        return sendId;
    }

    public void setSendId(Long sendId) {
        this.sendId = sendId;
    }

    @Override
    public String toString() {
        return "Address{" +
                "aId=" + aId +
                ", sendId=" + sendId +
                ", aPhone='" + aPhone + '\'' +
                ", aName='" + aName + '\'' +
                ", aProvince='" + aProvince + '\'' +
                ", aCity='" + aCity + '\'' +
                ", aDistrict='" + aDistrict + '\'' +
                ", aCode='" + aCode + '\'' +
                ", aDetail='" + aDetail + '\'' +
                ", shopName='" + shopName + '\'' +
                '}';
    }
}
