package com.amity.account.pojo.po;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by Amity on 2020/12/23 11:00
 */
public class BasePO implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 最后修改人
     */
    private String lastModifiedBy;

    /**
     * 最后修改时间
     */
    private LocalDateTime lastModifiedTime;

    private Integer enabled;

    private Integer version;

    private LocalDateTime disabledTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public LocalDateTime getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(LocalDateTime lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public LocalDateTime getDisabledTime() {
        return disabledTime;
    }

    public void setDisabledTime(LocalDateTime disabledTime) {
        this.disabledTime = disabledTime;
    }
}
