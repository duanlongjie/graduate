package cn.edu.haue.graduate.security;

import cn.edu.haue.graduate.entity.Admin;
import cn.edu.haue.graduate.entity.Role;
import cn.edu.haue.graduate.service.AdminService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 杨晋升 on 2018/5/30.
 */
@Service("adminUserDetailsService")
public class AdminUserDetailsService implements UserDetailsService {

    @Resource
    private AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Collection<GrantedAuthority> authorities = new ArrayList<>();

        Admin admin = adminService.getAdminByUsername(username);

        if (admin == null) {
            throw new UsernameNotFoundException("用户名不存在");
        } else {
            List<Role> roleList = admin.getRoleList();
            for (Role role : roleList) {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            }
        }
        return new User(admin.getUsername(), admin.getPassword(), authorities);
    }
}
