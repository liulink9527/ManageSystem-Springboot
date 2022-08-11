package com.link.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p>
 * 
 * </p>
 *
 * @author Link
 * @since 2022-08-11
 */
@TableName("tb_role")
@ApiModel(value = "Role对象", description = "")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("id")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("名称")
      private String name;

      @ApiModelProperty("描述")
      private String description;



    @Override
    public String toString() {
        return "Role{" +
              "id=" + id +
                  ", name=" + name +
                  ", description=" + description +
              "}";
    }
}
