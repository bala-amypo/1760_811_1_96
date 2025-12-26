package com.example.demo.security;

import com.example.demo.model.UserAccount;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class JwtTokenProvider {

    // test may override this by reflection
    private String jwtSecret = "change-this-secret-key-change-this-secret-key-change";

    public JwtTokenProvider() {
    }

    private String hmacFor(String payload) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update((payload + jwtSecret).getBytes(StandardCharsets.UTF_8));
            byte[] digest = md.digest();
            return Base64.getUrlEncoder().withoutPadding().encodeToString(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateToken(UserAccount user) {
        String payload = String.format("%d|%s|%s", user.getId() == null ? -1L : user.getId(), user.getEmail(), user.getRole());
        String p64 = Base64.getUrlEncoder().withoutPadding().encodeToString(payload.getBytes(StandardCharsets.UTF_8));
        String sig = hmacFor(payload);
        return p64 + "." + sig;
    }

    public boolean validateToken(String token) {
        if (token == null) return false;
        String[] parts = token.split("\\.");
        if (parts.length != 2) return false;
        try {
            String payload = new String(Base64.getUrlDecoder().decode(parts[0]), StandardCharsets.UTF_8);
            String expected = hmacFor(payload);
            return MessageDigest.isEqual(expected.getBytes(StandardCharsets.UTF_8), parts[1].getBytes(StandardCharsets.UTF_8));
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    private String[] decodePayload(String token) {
        if (!validateToken(token)) return null;
        String[] parts = token.split("\\.");
        String payload = new String(Base64.getUrlDecoder().decode(parts[0]), StandardCharsets.UTF_8);
        // payload format: id|email|role
        return payload.split("\\|");
    }

    public String getEmail(String token) {
        try {
            String[] p = decodePayload(token);
            return p == null || p.length < 2 ? null : p[1];
        } catch (Exception e) {
            return null;
        }
    }

    public String getRole(String token) {
        try {
            String[] p = decodePayload(token);
            return p == null || p.length < 3 ? null : p[2];
        } catch (Exception e) {
            return null;
        }
    }

    public Long getUserId(String token) {
        try {
            String[] p = decodePayload(token);
            if (p == null || p.length < 1) return null;
            return Long.valueOf(Long.parseLong(p[0]));
        } catch (Exception e) {
            return null;
        }
    }
}