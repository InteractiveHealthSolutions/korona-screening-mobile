
package com.ihsinformatics.korona.model.question;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserRole {

    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("isRetired")
    @Expose
    private Boolean isRetired;
    @SerializedName("dateCreated")
    @Expose
    private String dateCreated;
    @SerializedName("reasonRetired")
    @Expose
    private Object reasonRetired;
    @SerializedName("roleId")
    @Expose
    private Integer roleId;
    @SerializedName("roleName")
    @Expose
    private String roleName;
    @SerializedName("rolePrivileges")
    @Expose
    private List<Object> rolePrivileges = null;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Boolean getIsRetired() {
        return isRetired;
    }

    public void setIsRetired(Boolean isRetired) {
        this.isRetired = isRetired;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Object getReasonRetired() {
        return reasonRetired;
    }

    public void setReasonRetired(Object reasonRetired) {
        this.reasonRetired = reasonRetired;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Object> getRolePrivileges() {
        return rolePrivileges;
    }

    public void setRolePrivileges(List<Object> rolePrivileges) {
        this.rolePrivileges = rolePrivileges;
    }

}
