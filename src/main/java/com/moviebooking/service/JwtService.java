package com.moviebooking.service;

import com.moviebooking.dto.JwtResponse;
import com.moviebooking.entity.Customer;
import com.moviebooking.repository.CustomerRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.nio.file.attribute.UserPrincipal;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Component
public class JwtService{

    @Autowired
    private CustomerRepository customerRepository;

    private static final String SECRET_KEY = "482B4D6250655368566D597133743677397A24432646294A404E635266546A57";

	private static final String AUTHORITIES_KEY = "role";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
//    @Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Optional<UserEntity> custuser = userRepository.findByUsername(username);
//		return custuser.map(UserPrincipal::new).orElseThrow(() -> new UsernameNotFoundException("Bad credentials"));
//	}

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public JwtResponse generateToken(String name){
        Map<String,Object> claims= new HashMap<>();
//        String token = createToken(claims,userName);
//        Customer customer = customerRepository.findByUserName(userName).get();
//        return new JwtResponse(customer,token);
        System.out.println(name);
    	String compact = Jwts.builder().setSubject(name)
				.setClaims(claims)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + (1000 * 60 * 15)))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
        System.out.println(name);
        Customer customer = customerRepository.findByUserName(name).get();
      return new JwtResponse(customer,compact);
//		return compact;
    }

    private String createToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
                .signWith(getSignKey(),SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}