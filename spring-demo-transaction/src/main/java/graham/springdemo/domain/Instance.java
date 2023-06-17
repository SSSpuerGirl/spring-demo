package graham.springdemo.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@TableName("instance")
public class Instance {

    @TableId("id")
    private Integer id;
    @TableField("ins_name")
    private String insName;
    @TableField("ins_value")
    private String insValue;
}
