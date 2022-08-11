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
@TableName("tb_menu")
@ApiModel(value = "Menu对象", description = "")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("id")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("名称")
      private String name;

      @ApiModelProperty("路径")
      private String path;

      @ApiModelProperty("图表")
      private String icon;

      @ApiModelProperty("描述")
      private String description;

    
    public Integer getId() {
        return id;
    }

      public void setId(Integer id) {
          this.id = id;
      }
    
    public String getName() {
        return name;
    }

      public void setName(String name) {
          this.name = name;
      }
    
    public String getPath() {
        return path;
    }

      public void setPath(String path) {
          this.path = path;
      }
    
    public String getIcon() {
        return icon;
    }

      public void setIcon(String icon) {
          this.icon = icon;
      }
    
    public String getDescription() {
        return description;
    }

      public void setDescription(String description) {
          this.description = description;
      }

    @Override
    public String toString() {
        return "Menu{" +
              "id=" + id +
                  ", name=" + name +
                  ", path=" + path +
                  ", icon=" + icon +
                  ", description=" + description +
              "}";
    }
}
