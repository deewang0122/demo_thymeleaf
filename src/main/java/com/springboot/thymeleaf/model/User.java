package com.springboot.thymeleaf.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name="\"user\"")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotEmpty(message = "姓名不能为空")
    @Size(max = 10, min = 2, message = "姓名不小于2位")
    private String name;

    @NotNull
    @NotEmpty(message = "密码不能为空")
    @Size(max = 20, min = 6, message = "密码长度应不小于6位")
    @JsonIgnore
    private String password;

    @NotEmpty(message = "电话不能为空")
    @Column(unique = true, nullable = true)
    private String phone;

    @Size(max = 150, message = "不超过150个字符")
    private String documentation;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    private Date deletedAt;              //删除/注销账号时间

    private Boolean isEnable = true;        //是否启用

    /**删除操作*/
    public User(Long id, Boolean isEnable, Date deletedAt) {
        this.id = id;
        this.isEnable = isEnable;
        this.deletedAt = deletedAt;
    }
}
