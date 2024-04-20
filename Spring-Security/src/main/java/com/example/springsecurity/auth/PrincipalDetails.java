package com.example.springsecurity.auth;


// 시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행
// 로그인 진행이 완료가 되면 따로 시큐리티가 가지고 있는 session을 만들어 줍니다. (Security ContextHolder에 저장)
// Authentication 타입 객체를 저장
// Authentication 안에 User정보가 있어야 됨.
// User오브젝트타입 => UserDetails 타입 객체

// Security Session => Authentication => UserDetails(PrincipalDetails클래스)

import com.example.springsecurity.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class PrincipalDetails implements UserDetails {

    private User user;

    public PrincipalDetails(User user) {
        this.user = user;
    }

    // 해당 유저의 권한을 리턴하는 곳
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return collection;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정이 잠겼는지
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 기간만료
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정이 사용가능한지 ex) 휴먼계정
    @Override
    public boolean isEnabled() {
        return true;
    }
}
