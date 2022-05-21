package cn.cleanarch.gw.tool.infra.mapper.dataobject;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author 整洁架构
 * @version 1.0
 * @title: GatewayAccessConfDORepository
 * @date: 2022/5/21 21:16
 */
@Data
@Entity
@Table(name = "gateway_access_conf")
@EntityListeners(AuditingEntityListener.class)
public class GatewayAccessConfDO {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 网关访问key
     */
    @Column(name = "api_key")
    private String apiKey;

    /**
     * 网关访问secret
     */
    @Column(name = "api_secret")
    private String apiSecret;

    /**
     * 访问系统
     */
    @Column(name = "`system`")
    private String system;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 状态
     */
    @Column(name = "status")
    private String status;

    /**
     * 创建时间
     */
    @CreatedDate
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @LastModifiedDate
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    /**
     * 删除标识（0-正常,1-删除）
     */
    @Column(name = "del_flag")
    private String delFlag;
}
