package cn.edu.haue.graduate.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by 杨晋升 on 2018/5/30.
 */
public class PasswordEncrypter {
    public static BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
