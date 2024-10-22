package org.lab1.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.lab1.data.CRUD;
import org.lab1.web.bean.auth.UserBean;

import javax.persistence.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
    public static String PAPER = "N@#2BJ";

    public static String hashString(String string)  {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] hashBytes = md.digest(string.getBytes(StandardCharsets.UTF_8));
        StringBuilder md5Hex = new StringBuilder();
        for (byte b : hashBytes) {
            md5Hex.append(String.format("%02x", b));
        }
        return md5Hex.toString();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // Primary key, auto-generated

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false, unique = true)
    private String password;

    @Column(nullable = false)
    private String nick;

    @Column(nullable = false)
    private boolean isAdmin;

//    @Column(nullable = false)
//    private String salt = generateString();

    @Column(nullable = false)
    private boolean isRequest = true;

    public void setPassword(String password) {
        this.password = hashString(password);
    }

//    public static String generateString() {
//        return "adad";
////        byte[] array = new byte[7]; // length is bounded by 7
////        new Random().nextBytes(array);
////        return new String(array, StandardCharsets.UTF_8);
//    }
}
