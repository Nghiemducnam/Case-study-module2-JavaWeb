package com.codegym.model;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Table(name = "producer")
public class Producer implements Validator {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long producerId;
    @NotEmpty
    private String producerName;

    @OneToMany (targetEntity = MobileType.class)
    private Set<MobileType> mobileTypes;

    @OneToMany (targetEntity = Mobile.class)
    private Set<Mobile> mobiles;

    public Producer() {
    }

    public Producer(@NotEmpty String producerName, Set<MobileType> mobileTypes, Set<Mobile> mobiles) {
        this.producerName = producerName;
        this.mobileTypes = mobileTypes;
        this.mobiles = mobiles;
    }

    public Long getProducerId() {
        return producerId;
    }

    public void setProducerId(Long producerId) {
        this.producerId = producerId;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public Set<MobileType> getMobileTypes() {
        return mobileTypes;
    }

    public void setMobileTypes(Set<MobileType> mobileTypes) {
        this.mobileTypes = mobileTypes;
    }

    public Set<Mobile> getMobiles() {
        return mobiles;
    }

    public void setMobiles(Set<Mobile> mobiles) {
        this.mobiles = mobiles;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Producer.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
