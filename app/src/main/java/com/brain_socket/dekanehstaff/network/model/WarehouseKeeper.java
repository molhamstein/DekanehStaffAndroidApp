package com.brain_socket.dekanehstaff.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class WarehouseKeeper implements Serializable {

    @SerializedName("balance")
    @Expose
    private Integer balance;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("hideHistory")
    @Expose
    private Boolean hideHistory;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("clientType")
    @Expose
    private String clientType;
    @SerializedName("creationDate")
    @Expose
    private String creationDate;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("roleIds")
    @Expose
    private List<String> roleIds = null;
    @SerializedName("privilegeIds")
    @Expose
    private List<Object> privilegeIds = null;
    @SerializedName("roles")
    @Expose
    private List<Role> roles = null;
    private final static long serialVersionUID = 1825929745689652842L;

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getHideHistory() {
        return hideHistory;
    }

    public void setHideHistory(Boolean hideHistory) {
        this.hideHistory = hideHistory;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
    }

    public List<Object> getPrivilegeIds() {
        return privilegeIds;
    }

    public void setPrivilegeIds(List<Object> privilegeIds) {
        this.privilegeIds = privilegeIds;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

}
