package com.practicespringboot.__InventoryMangementSystem_Practice_Project.service.implementation;

import com.practicespringboot.__InventoryMangementSystem_Practice_Project.dto.UserDTO;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.dto.requestDTO.UserRequestDTO;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.entity.Role;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.entity.User;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.entity.UserRole;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.exception.RoleNotFoundException;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.exception.UserNotFoundException;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.repository.RoleRepository;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.repository.UserRepository;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.repository.UserRoleRepository;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.service.UserService;
import com.practicespringboot.__InventoryMangementSystem_Practice_Project.utils.UserRoleId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    @Autowired
    UserServiceImpl(UserRoleRepository userRoleRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
    }

//    @Override
//    public UserDTO createUser(UserRequestDTO userRequestDTO) {
//        User user = modelMapper.map(userRequestDTO, User.class);
//        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
//        user.setRoles(userRequestDTO.getRoles().stream().map(
//                (role)->roleRepository.findRoleByName(role).orElseThrow(
//                        ()->new RoleNotFoundException("Roles not found.")
//                )
//        ).collect(Collectors.toSet()));
//        User savedUser = userRepository.save(user);
//        return modelMapper.map(savedUser, UserDTO.class);
//    }

    @Override
    public UserDTO createUser(UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setUsername(userRequestDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));

        // Fetch roles from DB and store in Set<Role>
        Set<Role> roleSet = new HashSet<>();
        Set<UserRole> userRoleSet = new HashSet<>();

        for (String roleName : userRequestDTO.getRoles()) {
            Role role = roleRepository.findRoleByName(roleName)
                    .orElseThrow(() -> new RoleNotFoundException("Role not found: " + roleName));

            roleSet.add(role);

            // Create UserRole entry
            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(role);
            userRole.setId(new UserRoleId()); // Auto-generate Embedded ID

            userRoleSet.add(userRole);
        }

        user.setRoles(roleSet); // Maintain Many-to-Many relationship
        user.setUserRoles(userRoleSet); // Maintain UserRole mapping

        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDTO.class);
    }



    @Override
    public UserDTO getUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(
                ()-> new UserNotFoundException("User not found with id: " + id)
        );
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        userDTO.setRoles(user.getRoles().stream().map(
                (Role::getName)
        ).collect(Collectors.toSet()));
        return userDTO;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOS = users.stream().map((user)->modelMapper.map(user, UserDTO.class)).toList();

        return userDTOS.stream()
                .peek(userDTO -> {
                    Optional<User> matchedUser = users.stream()
                            .filter(user -> user.getId().equals(userDTO.getId()))
                            .findFirst();

                    matchedUser.ifPresent(user -> {
                        Set<String> roleNames = user.getRoles().stream()
                                .map(Role::getName)
                                .collect(Collectors.toSet());
                        userDTO.setRoles(roleNames);
                    });

                }).toList();
    }

    @Override
    public UserDTO updateUserById(Integer id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow(
                ()->new UserNotFoundException("User not found with id: " + id)
        );
        user.setUsername(userDTO.getUsername());
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public String deleteUserById(Integer id) {
        userRoleRepository.deleteByUserId(id);
        userRepository.deleteById(id);
        return "User Deleted Successful.";
    }

}
