package com.example.isa;

import com.example.isa.configuration.Principal;
import com.example.isa.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@Transactional
public class AbstractTest {

    @Autowired
    protected MockMvc mockMvc;

    protected void setAuthentication(User user) {
        final Principal principal = new Principal(user.getId(), user.getUserType());
        final Authentication authentication = new UsernamePasswordAuthenticationToken(principal, null, List.of((GrantedAuthority) user.getUserType()::name));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    protected void clearAuthentication() {
        SecurityContextHolder.clearContext();
    }
}
