package com.restaurant.foodonline.config;

import com.restaurant.foodonline.entity.UserEntity;
import com.restaurant.foodonline.enums.UserRoleEnum;
import com.restaurant.foodonline.repository.UserRepository;
import com.restaurant.foodonline.util.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AdminUserConfig {
    Logger logger = LoggerFactory.getLogger(AdminUserConfig.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    RandomUtil randomUtil;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${admin.username}")
    private String useranme;

    @Value("${admin.password}")
    private String password;

    @Value("${admin.firstName}")
    private String firstName;

    @Value("${admin.lastName}")
    private String lastName;

    @Value("${admin.mobile}")
    private String mobile;

    @PostConstruct
    public void addAdminUser() {

        UserEntity userEntity = userRepository.findByUsername(useranme);
        if (userEntity == null) {
            userEntity = new UserEntity();
            userEntity.setUserId(randomUtil.generateUniqueId());
            userEntity.setUsername(useranme);
            userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(password));
            userEntity.setRole(UserRoleEnum.ADMIN);
            userEntity.setFirstName(firstName);
            userEntity.setLastName(lastName);
            userEntity.setMobile(mobile);

            userRepository.save(userEntity);
            logger.info("admin user was saved successfully.");
        }
    }
}
