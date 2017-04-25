package com.social.servicesImpl;

import com.social.entities.BasicUser;
import com.social.entities.User;
import com.social.errors.Errors;
import com.social.models.bindingModels.RegistrationModel;
import com.social.models.viewModels.UserInFriendListViewModel;
import com.social.repositories.BasicUserRepository;
import com.social.repositories.UserRepository;
import com.social.services.BasicUserService;
import com.social.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BasicUserServiceImpl implements BasicUserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private BasicUserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void register(RegistrationModel registrationModel) {
        BasicUser user = this.modelMapper.map(registrationModel, BasicUser.class);
        String encryptedPassword = this.bCryptPasswordEncoder.encode(registrationModel.getPassword());
        user.setPassword(encryptedPassword);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.addRole(this.roleService.getDefaultRole());
        this.userRepository.save(user);
    }

    @Override
    public List<UserInFriendListViewModel> getFriendList(User user) {
        List<UserInFriendListViewModel> friendList = new ArrayList<>();
        User userWithFriendsFetched = userRepository.getUserAndFetchFriends(user.getId());
        if(userWithFriendsFetched != null){
            for (User friend : userWithFriendsFetched.getFriends()) {
                UserInFriendListViewModel friendModel = modelMapper.map(friend, UserInFriendListViewModel.class);
                friendList.add(friendModel);
            }
        }
        return friendList;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findOneByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException(Errors.INVALID_CREDENTIALS);
        }

        return user;
    }


}
