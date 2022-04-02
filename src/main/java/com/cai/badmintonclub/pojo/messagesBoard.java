package com.cai.badmintonclub.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

@Data
public class messagesBoard {
    private Integer messagesId;
    private Integer memberId;
    private String spokesmanName;
    private String messagesContent;
    private String messagesReleaseDate;
}
