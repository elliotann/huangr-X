package com.easysoft.member.backend.model;

import javax.persistence.*;

/**
 * 角色功能
 * User: andy
 * Date: 14-5-12
 * Time: 上午11:58
 *
 * @since:
 */
@Entity
@Table(name="t_role_auth")
public class RoleAuth {
    public enum AuthType{
        FUNCTION,
        DATA
    }
    private Integer id;
    private AuthType authType;
    private Role role;
    private Integer funOrDataId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name = "auth_type", length = 10)
    @Enumerated(EnumType.STRING)
    public AuthType getAuthType() {
        return authType;
    }

    public void setAuthType(AuthType authType) {
        this.authType = authType;
    }

    public Integer getFunOrDataId() {
        return funOrDataId;
    }

    public void setFunOrDataId(Integer funOrDataId) {
        this.funOrDataId = funOrDataId;
    }
    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = false)
    @JoinColumn(name = "role_id")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
