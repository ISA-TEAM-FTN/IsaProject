package com.isa.service.user;

import com.isa.config.SecurityUtils;
import com.isa.domain.dto.ChangePasswordDTO;
import com.isa.domain.dto.UserDTO;
import com.isa.domain.model.CenterAccount;
import com.isa.domain.model.User;
import com.isa.enums.Role;
import com.isa.repository.CenterAccountRepository;
import com.isa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CenterAccountRepository centerAccountRepository;

    @Override
    @Transactional
    public User register(UserDTO userDTO) {

        User user = userRepository.getByEmail(userDTO.getEmail());

        if(user != null) {
            return null;
        }

        user = new User();
        user.setEmail(userDTO.getEmail());;
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(Role.ADMIN_CENTER);
        user.setAddress(userDTO.getAddress());
        user.setCity(userDTO.getCity());
        user.setCountry(userDTO.getCountry());
        user.setFirstLogin(true);
        user.setPhone(userDTO.getPhone());
        CenterAccount centerAccount = new CenterAccount();
        centerAccount.setAddress("Address");
        centerAccount.setName("Name");
        centerAccount.setCity("City");
        centerAccount.setDescription("Description");
        centerAccount.setCountry("Country");
        centerAccount.setStartTime(LocalTime.MIDNIGHT);
        centerAccount.setEndTime(LocalTime.NOON);
        
        centerAccountRepository.save(centerAccount);
        
        user.setCenterAccount(centerAccount);

        return userRepository.save(user);
    }

    @Override
    public User add(UserDTO userDTO) {

        User user = userRepository.getByEmail(userDTO.getEmail());

        if(user != null) {
            return null;
        }

        user = new User();
        user.setEmail(userDTO.getEmail());;
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(userDTO.getRole());
        user.setAddress(userDTO.getAddress());
        user.setCity(userDTO.getCity());
        user.setCountry(userDTO.getCountry());
        user.setFirstLogin(true);
        user.setPhone(userDTO.getPhone());

        return userRepository.save(user);
    }

    @Override
    public Role stringToRole(String role) {

        return Role.valueOf(role);
    }

    @Override
    public User getCurrentUser() {

        String email = SecurityUtils.getCurrentUserLogin().get();

        return userRepository.getByEmail(email);
    }

    @Override
    public User changePassword(ChangePasswordDTO changePasswordDTO) {
        User user = getCurrentUser();

        if(user == null) {
            return null;
        }

        user.setPassword(passwordEncoder.encode(changePasswordDTO.getPassword()));
        user.setFirstLogin(false);

        return userRepository.save(user);
    }

    @Override
    public User updateProfile(UserDTO userDTO) {
        User user = getCurrentUser();

        if(user == null) {
            return null;
        }

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setAddress(userDTO.getAddress());
        user.setCity(userDTO.getCity());
        user.setCountry(userDTO.getCountry());
        user.setFirstLogin(true);
        user.setPhone(userDTO.getPhone());

        return userRepository.save(user);
    }

    @Override
    public List<User> findAllByRole(Role role) {
        return userRepository.findAllByRole(role);
    }

    @Override
    public List<User> search(String term, Role role) {
        return userRepository.findAllByRoleAndFirstNameContainsOrLastNameContaining(role, term, term);
    }
}
