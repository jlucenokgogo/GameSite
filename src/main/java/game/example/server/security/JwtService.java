package game.example.server.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Service("JwtService")
public class JwtService {
    private static final String SECRET_KEY = "12317654323456789008765432234567890" +
            "0987654322345678909876543";
    public String extractUserLogin(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String generateToken(UserDetailsImpl userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public boolean isTokenValid(String token, @NotNull UserDetailsImpl userDetails) {
        final String login = extractUserLogin(token);
        return login.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String generateToken(Map<String, Object> extraClaims,
                                @NotNull UserDetailsImpl userDetails) {
        var isAdmin = !userDetails.getAuthorities().stream().toList().get(0).getAuthority().equals("ROLE_CLIENT");
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .addClaims(Map.of("isAdmin", isAdmin))
                .signWith(getSingKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public <T> T extractClaim(String token, @NotNull Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSingKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private @NotNull Key getSingKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
