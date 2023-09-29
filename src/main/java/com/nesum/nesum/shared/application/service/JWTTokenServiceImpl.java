package com.nesum.nesum.shared.application.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.nesum.nesum.user.application.dto.UserDTO;

import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class JWTTokenServiceImpl implements TokenService{
    
    final String KEY_NAME_SUBJECT = "name";

    @Override
    public String generateToken(UserDTO userDTO) {
        var subject = createSubject(userDTO);

        var calendar = Calendar.getInstance();
        var tokenDate = calendar.getTime();
        calendar.add(Calendar.HOUR, 1);
        var tokenExp = calendar.getTime();
        return Jwts.builder()
            .setId(UUID.randomUUID().toString())
            .setSubject(subject.toString())
            .setIssuedAt(tokenDate)
            .setExpiration(tokenExp)
            .compact();
    }

    private Object createSubject(UserDTO user) {
        var subject = new HashMap<String, Object>();
        subject.put(KEY_NAME_SUBJECT, user.getName());
        return subject;
    }
    
}
