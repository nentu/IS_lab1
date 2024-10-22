package org.lab1.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.lab1.data.CRUD;
import org.lab1.web.bean.auth.UserBean;

import javax.persistence.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Random;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
    public static String PAPER = "N@#2BJ";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // Primary key, auto-generated

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nick;

    @Column(nullable = false)
    private boolean isAdmin;

    @Column(nullable = false)
    private String salt = generateString();

    @Column(nullable = false)
    private boolean isRequest = true;

    public void setPassword(String password) {
        this.password = PAPER + password + salt;
    }

    public static String generateString() {
        return "adad";
//        byte[] array = new byte[7]; // length is bounded by 7
//        new Random().nextBytes(array);
//        return new String(array, StandardCharsets.UTF_8);
    }
}
