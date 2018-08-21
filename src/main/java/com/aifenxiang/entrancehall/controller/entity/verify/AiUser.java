package com.aifenxiang.entrancehall.controller.entity.verify;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author: zj
 * @create: 2018-08-21 22:28
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AiUser  implements UserDetails {

    private int id;

    private String username;

    private String password;

    private List<AiRole> aiRoles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<AiRole> aiRoles = getAiRoles();
        for (AiRole aiRole : aiRoles){
            authorities.add(new SimpleGrantedAuthority(aiRole.getName()));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
