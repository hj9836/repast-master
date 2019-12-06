package com.aaa.repast.admin.project.tool.redisTools.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CanTeenDateVo implements Serializable {
    private Long catId;
    private String catName;
    private Long shopId;
    private List<MemberProduct> data;



}
