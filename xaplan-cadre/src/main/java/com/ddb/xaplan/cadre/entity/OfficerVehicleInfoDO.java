package com.ddb.xaplan.cadre.entity;

import com.ddb.xaplan.cadre.enums.VehicleStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Author:Zan Yang
 * Title:车辆信息
 */
@Entity
@Table(name = "officer_vehicle_info")
@Where(clause = "is_deleted=0")
public class OfficerVehicleInfoDO extends  BaseEntity{
    private OfficerBasicInfoDO officerBasicInfo;
    private String model;
    private Boolean isImported;
    private String type;
    private String owner;
    private VehicleStatus status;
    private String plateNumber;
    @JsonBackReference
    @OneToOne
    @JoinColumn
    public OfficerBasicInfoDO getOfficerBasicInfo() {
        return officerBasicInfo;
    }

    public void setOfficerBasicInfo(OfficerBasicInfoDO officerBasicInfo) {
        this.officerBasicInfo = officerBasicInfo;
    }

    @Column
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
    @Column
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Column
    public VehicleStatus getStatus() {
        return status;
    }

    public void setStatus(VehicleStatus status) {
        this.status = status;
    }

    @Column
    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Boolean getIsImported() {
        return isImported;
    }

    public void setIsImported(Boolean isImported) {
        this.isImported = isImported;
    }
}
