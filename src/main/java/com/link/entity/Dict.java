package com.link.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Link
 * @Description
 * @date 2022-08-11 11:56
 */
@TableName("tb_dict")
@ApiModel(value = "Dict对象", description = "")
@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Dict {
    private String name;
    private String value;
    private String type;
}
