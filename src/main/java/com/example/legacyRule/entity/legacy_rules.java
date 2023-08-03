package com.example.legacyRule.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "armour_schema.legacy_rules")
public class legacy_rules {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lr_id")
    private Integer lrId;

    @Column(name = "lr_name")
    private String lrName;

    @Column(name = "lr_calling_gt")
    private String lrCallingGt;

    @Column(name = "lr_map_gt")
    private String lrMapGt;

    @Column(name = "lr_alpha_blocked")
    private Integer lrAlphaBlocked;

    @Column(name = "lr_oa")
    private String lrOa;

    @Column(name = "lr_da")
    private String lrDa;

    @Column(name = "lr_content")
    private String lrContent;

    @Column(name = "lr_action_id")
    private Integer lrActionId;

    @Column(name = "lr_deleted_by")
    private Integer lrDeletedBy;

    @Column(name = "lr_created_at")
    private String lrCreatedAt;

    @Column(name = "lr_updated_at")
    private String lrUpdatedAt;

    @Column(name = "lr_deleted_at")
    private String lrDeletedAt;

    @Column(name = "lr_created_by")
    private Integer lrCreatedBy;

    @Column(name = "lr_status")
    private Integer lrStatus;

    @Column(name = "lr_type")
    private String lrType;

    @Column(name = "lr_updated_by")
    private Integer lrUpdatedBy;

    @Column(name = "lr_recalled_at")
    private String lrRecalledAt;

    @Column(name = "lr_recalled_by")
    private Integer lrRecalledBy;

    @Column(name = "lr_msisdn")
    private Integer lrMsisdn;

    @Column(name = "lr_systemid")
    private Integer lrSystemid;

}
