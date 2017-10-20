package com.ddb.xaplan.cadre.entity;

import com.ddb.xaplan.cadre.enums.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by 陈亚兰 on 2017/10/17.
 * 家庭成员信息表
 */
@Entity
@Table(name="officer_family_member_info")
@Where(clause = "is_deleted=0")
public class OfficerFamilyMemberInfoDO extends BaseEntity{

    private static final long serialVersionUID = 667242011618513702L;

    private OfficerBasicInfoDO officerBasicInfoDO;//干部信息

    private String name;//姓名

    private String idCard;//身份证号

    private String passport;//护照号码

    private String specPassport;//港澳台通行证

    private FamilyRelationship familyRelationship;//关系

    private Date birthday;//出生日期

    private String nation;//国籍

    private String culture;//民族

    private EducationLevel educationLevel;//学历

    private PoliticsStatus politicsStatus;//政治面貌

    private String organization;//工作单位名称

    private TitleLevel titleLevel;//职级

    private String title;//现任职务

    private boolean isHighTitle;//是否担任高级职务

    private UnitProperty orgType;//单位性质

    private boolean isRegistered;//是否有备案信息

    private boolean isMigration;//是否移居国（境）外

    private String migrationArea;//移居国家（地区）

    private String livingCity;//现居住城市

    private String migrationId;//移居证件号码

    private MigrationType migrationType;//移居类别

    private Date migrationDate;//移居时间

    private boolean locooy;//是否连续在国（境）外连续工作、生活一年以上

    private String locooyArea;//所在国家（地区）

    private String locooyCity;//工作、生活城市

    private Date locooyStartDate;//起始时间

    private boolean marridForeign;//是否与外国人、无国际人、港澳台居民通婚

    private String spouse;//配偶姓名

    private String spouseArea;//国家(地区)

    private String spouseOrg;//工作（学习）单位

    private String spouseTitle;//职务

    private Date marriageDate;//结婚登记时间

    @JsonBackReference
    @OneToOne
    @JoinColumn
    public OfficerBasicInfoDO getOfficerBasicInfoDO() {
        return officerBasicInfoDO;
    }

    public void setOfficerBasicInfoDO(OfficerBasicInfoDO officerBasicInfoDO) {
        this.officerBasicInfoDO = officerBasicInfoDO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getSpecPassport() {
        return specPassport;
    }

    public void setSpecPassport(String specPassport) {
        this.specPassport = specPassport;
    }

    public FamilyRelationship getFamilyRelationship() {
        return familyRelationship;
    }

    public void setFamilyRelationship(FamilyRelationship familyRelationship) {
        this.familyRelationship = familyRelationship;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public EducationLevel getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(EducationLevel educationLevel) {
        this.educationLevel = educationLevel;
    }

    public PoliticsStatus getPoliticsStatus() {
        return politicsStatus;
    }

    public void setPoliticsStatus(PoliticsStatus politicsStatus) {
        this.politicsStatus = politicsStatus;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public TitleLevel getTitleLevel() {
        return titleLevel;
    }

    public void setTitleLevel(TitleLevel titleLevel) {
        this.titleLevel = titleLevel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isHighTitle() {
        return isHighTitle;
    }

    public void setHighTitle(boolean highTitle) {
        isHighTitle = highTitle;
    }

    public UnitProperty getOrgType() {
        return orgType;
    }

    public void setOrgType(UnitProperty orgType) {
        this.orgType = orgType;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public void setRegistered(boolean registered) {
        isRegistered = registered;
    }

    public boolean isMigration() {
        return isMigration;
    }

    public void setMigration(boolean migration) {
        isMigration = migration;
    }

    public String getMigrationArea() {
        return migrationArea;
    }

    public void setMigrationArea(String migrationArea) {
        this.migrationArea = migrationArea;
    }

    public String getLivingCity() {
        return livingCity;
    }

    public void setLivingCity(String livingCity) {
        this.livingCity = livingCity;
    }

    public String getMigrationId() {
        return migrationId;
    }

    public void setMigrationId(String migrationId) {
        this.migrationId = migrationId;
    }

    public MigrationType getMigrationType() {
        return migrationType;
    }

    public void setMigrationType(MigrationType migrationType) {
        this.migrationType = migrationType;
    }

    public Date getMigrationDate() {
        return migrationDate;
    }

    public void setMigrationDate(Date migrationDate) {
        this.migrationDate = migrationDate;
    }

    public boolean isLocooy() {
        return locooy;
    }

    public void setLocooy(boolean locooy) {
        this.locooy = locooy;
    }

    public String getLocooyArea() {
        return locooyArea;
    }

    public void setLocooyArea(String locooyArea) {
        this.locooyArea = locooyArea;
    }

    public String getLocooyCity() {
        return locooyCity;
    }

    public void setLocooyCity(String locooyCity) {
        this.locooyCity = locooyCity;
    }

    public Date getLocooyStartDate() {
        return locooyStartDate;
    }

    public void setLocooyStartDate(Date locooyStartDate) {
        this.locooyStartDate = locooyStartDate;
    }

    public boolean isMarridForeign() {
        return marridForeign;
    }

    public void setMarridForeign(boolean marridForeign) {
        this.marridForeign = marridForeign;
    }

    public String getSpouse() {
        return spouse;
    }

    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }

    public String getSpouseArea() {
        return spouseArea;
    }

    public void setSpouseArea(String spouseArea) {
        this.spouseArea = spouseArea;
    }

    public String getSpouseOrg() {
        return spouseOrg;
    }

    public void setSpouseOrg(String spouseOrg) {
        this.spouseOrg = spouseOrg;
    }

    public String getSpouseTitle() {
        return spouseTitle;
    }

    public void setSpouseTitle(String spouseTitle) {
        this.spouseTitle = spouseTitle;
    }

    public Date getMarriageDate() {
        return marriageDate;
    }

    public void setMarriageDate(Date marriageDate) {
        this.marriageDate = marriageDate;
    }
}
