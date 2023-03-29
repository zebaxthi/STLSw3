package com.uco.stloan.jwt;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import io.jsonwebtoken.Claims;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JwtTokenUtilTest {

    private static final String USERNAME = "testuser";
    private static final String TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0dXNlciIsImlhdCI6MTYxODczNzQ5MCwiZXhwIjoxNjE4NzQxODkwfQ.WZHvo6b-_AKRJfIWTKUwF6Nnbh6LJmP4o4D4xDY_4d4";

    @Mock
    private UserDetails userDetails;

    @InjectMocks
    private JwtTokenUtil jwtTokenUtil;

    @Test
    void getUsernameFromToken() {
        String username = jwtTokenUtil.getUsernameFromToken(TOKEN);
        assertEquals(USERNAME, username);
    }

    @Test
    void getIssuedAtDateFromToken() {
        Date issuedAt = jwtTokenUtil.getIssuedAtDateFromToken(TOKEN);
        assertNotNull(issuedAt);
    }

    @Test
    void getExpirationDateFromToken() {
        Date expirationDate = jwtTokenUtil.getExpirationDateFromToken(TOKEN);
        assertNotNull(expirationDate);
    }

    @Test
    void getClaimFromToken() {
        String subject = jwtTokenUtil.getClaimFromToken(TOKEN, Claims::getSubject);
        assertEquals(USERNAME, subject);
    }

    @Test
    void generateToken() {
        when(userDetails.getUsername()).thenReturn(USERNAME);
        String token = jwtTokenUtil.generateToken(userDetails);
        assertNotNull(token);
        assertTrue(token.length() > 0);
    }

    @Test
    void canTokenBeRefreshed() {
        boolean canRefresh = jwtTokenUtil.canTokenBeRefreshed(TOKEN);
        assertTrue(canRefresh);
    }

    @Test
    void validateToken() {
        when(userDetails.getUsername()).thenReturn(USERNAME);
        boolean isValid = jwtTokenUtil.validateToken(TOKEN, userDetails);
        assertTrue(isValid);
    }
}