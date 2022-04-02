package com.cai.badmintonclub.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class messages {
    @TableId(value = "messages_id")
    private Integer messagesid;
    @TableField("messages_kind")
    private String messageskind;
    @TableField("messages_title")
    private String messagestitle;
    @TableField("messages_content")
    private String messagescontent;
    @TableField("messages_releasedate")
    private String messagesreleasedate;
}
