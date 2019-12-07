package com.codegym.model;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "mobile")
public class Mobile implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long mobileId;
    @NotEmpty
    private String mobileName;
    @NotNull
    @Min(1)
    private Long mobilePrice;
    @NotEmpty
    private String mobileDescription;
    @NotNull
    private boolean mobileStatus;
    @NotEmpty
    private String mobileImage;

    @ManyToOne
    @JoinColumn (name = "mobileTypedId")
    private MobileType mobileType;

    @ManyToOne
    @JoinColumn (name = "producerId")
    private Producer producer;

//    @ManyToOne
//    @JoinColumn (name = "billDetailId")
//    private BillDetail billDetail;

    public Mobile() {
    }

//    public Mobile(String mobileName, Double mobilePrice, String mobileDescription, boolean mobileStatus, String mobileImage, MobileType mobileType, Producer producer, BillDetail billDetail) {
//        this.mobileName = mobileName;
//        this.mobilePrice = mobilePrice;
//        this.mobileDescription = mobileDescription;
//        this.mobileStatus = mobileStatus;
//        this.mobileImage = mobileImage;
//        this.mobileType = mobileType;
//        this.producer = producer;
////        this.billDetail = billDetail;
//    }

    public String getMobileImage() {
        return mobileImage;
    }

    public void setMobileImage(String mobileImage) {
        this.mobileImage = mobileImage;
    }

    public Long getMobileId() {
        return mobileId;
    }

    public void setMobileId(Long mobileId) {
        this.mobileId = mobileId;
    }

    public String getMobileName() {
        return mobileName;
    }

    public void setMobileName(String mobileName) {
        this.mobileName = mobileName;
    }

    public Long getMobilePrice() {
        return mobilePrice;
    }

    public void setMobilePrice(Long mobilePrice) {
        this.mobilePrice = mobilePrice;
    }

    public String getMobileDescription() {
        return mobileDescription;
    }

    public void setMobileDescription(String mobileDescription) {
        this.mobileDescription = mobileDescription;
    }

    public boolean isMobileStatus() {
        return mobileStatus;
    }

    public void setMobileStatus(boolean mobileStatus) {
        this.mobileStatus = mobileStatus;
    }

    public MobileType getMobileType() {
        return mobileType;
    }

    public void setMobileType(MobileType mobileType) {
        this.mobileType = mobileType;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Mobile.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }

//    public BillDetail getBillDetail() {
//        return billDetail;
//    }
//
//    public void setBillDetail(BillDetail billDetail) {
//        this.billDetail = billDetail;
//    }
}
