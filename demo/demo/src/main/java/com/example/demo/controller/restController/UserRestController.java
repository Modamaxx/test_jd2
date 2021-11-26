package com.example.demo.controller.restController;

import com.example.demo.config.Jwt.JwtProvider;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.filter.ESortDirection;
import com.example.demo.dto.filter.ProductFilter;
import com.example.demo.dto.filter.UserFilter;
import com.example.demo.dto.models.UserDto;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.service.api.IUserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
    private final IUserService userService;
    @Autowired
    private JwtProvider jwtProvider;

    public UserRestController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> get(@PathVariable Long id) {
        User user = userService.get(id);
        long microseconds = user.getDtUpdate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        UserDto userDto = new UserDto();
        userDto.setUser(user);
        userDto.setMicroseconds(microseconds);

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<Page<User>> getAll(@RequestParam(value = "page", required = false) Integer page,
                                                @RequestParam(value = "size", required = false) Integer size) {

        UserFilter filter = new UserFilter();
        filter.setPage(page);
        filter.setSize(size);
        filter.setSortDirection(ESortDirection.DESC);

        Page<User> products = userService.getAll(filter);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PostMapping("/auth")
    public String auth(@RequestBody LoginDto loginDto) {
        User user = userService.findByLoginAndPassword(loginDto.getLogin(), loginDto.getPassword());
        String token = jwtProvider.generateToken(user.getLogin());
        return token;

    }

    @PutMapping("/{id}/dt_update/{dt_update}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody User updateUser,
                                    @PathVariable("dt_update") long dtUpdate) {
        userService.update(updateUser, id, dtUpdate);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}/dt_update/{dt_update}")
    public ResponseEntity<?> delete(@PathVariable Long id, @PathVariable("dt_update") long dtUpdate) {
        userService.delete(id,dtUpdate);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }

}
