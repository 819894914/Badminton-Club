package com.cai.badmintonclub.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class member {
    @TableId(value = "member_id",type = IdType.AUTO)
    private Integer memberid;
    @TableField("member_name")
    private String membername;
    @TableField("member_sex")
    private String membersex;
    @TableField("member_grade")
    private String membergrade;
    @TableField("member_identity")
    private String memberidentity;
    @TableField("member_phone")
    private String memberphone;
    @TableField("member_iconpath")
    private String membericon;
    @TableField("member_account")
    private String memberaccount;
    @TableField("member_password")
    private String memberpassword;
    @TableField("member_ismember")
    private Integer memberismember;
}
