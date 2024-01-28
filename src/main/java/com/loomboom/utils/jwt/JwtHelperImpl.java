package com.loomboom.utils.jwt;

import static com.loomboom.contants.JwtConstants.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;
import com.loomboom.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtHelperImpl implements JwtHelper {

    private Integer validity = JWT_VALIDITY;
    private String secret = JWT_SECRET;

    private SecretKey getSigningKey() {
        byte[] secretBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(secretBytes);
    }

    public String createToken(User user) {
        Date now = new Date();
        Date expriration = new Date(now.getTime() + validity);
        return Jwts.builder().subject(user.getEmail()).issuedAt(now).expiration(expriration)
                .signWith(getSigningKey(), Jwts.SIG.HS256).compact();
    }

    public String getEmailFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimResolver.apply(claims);
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
    }

    public Boolean validateToken(String token, User user) {
        final String email = getEmailFromToken(token);
        return (email.equals(user.getEmail())) && !isTokenExpired(token);
    }

}
