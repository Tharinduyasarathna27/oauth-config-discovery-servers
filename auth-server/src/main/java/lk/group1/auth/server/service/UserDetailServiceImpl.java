package lk.group1.auth.server.service;

import lk.group1.auth.server.exception.BodyContentNotValidException;
import lk.group1.auth.server.exception.CustomDataIntergrityVoilationException;
import lk.group1.auth.server.exception.DataNotFoundException;
import lk.group1.auth.server.model.AuthUserDetails;
import lk.group1.auth.server.model.User;
import lk.group1.auth.server.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserDetailsRepository userDetailsRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
       Optional<User> optionalUser=userDetailsRepository.findByUserName(name);

        optionalUser.orElseThrow(()-> new UsernameNotFoundException("username or password wrong"));

        UserDetails userDetails=new AuthUserDetails(optionalUser.get());

        new AccountStatusUserDetailsChecker().check(userDetails);

        return userDetails;
    }



    public User save(User user){

        if (user.getPassword().trim().isEmpty()){
            throw new BodyContentNotValidException("Can't enter empty password");
        }

        if (user.getEmail().trim().isEmpty()){
            throw new BodyContentNotValidException("Can't enter empty email");
        }

        //encrypt password and save it in db
        user.setPassword("{bcrypt}"+bCryptPasswordEncoder.encode(user.getPassword()));

        //check whether email is already exist.
        Optional<User> optional= userDetailsRepository.findByEmail(user.getEmail());
       if(optional.isPresent()){
           throw new CustomDataIntergrityVoilationException("Email Already Exists");
       }else{
           return   userDetailsRepository.save(user);
       }
    }

    // delete user by id
    public  void deteteById(Integer id){
        userDetailsRepository.deleteById(id);
    }


    // find user by id
    public Optional<User> findById(Integer id){

        Optional<User> optionalUser = userDetailsRepository.findById(id);
        if (optionalUser.isPresent()){
            return userDetailsRepository.findById(id);
        }else {
            throw new DataNotFoundException("User does not exist");
        }

    }

    public User fetchUsers(User user) {
        Optional<User> optional= userDetailsRepository.findById(user.getId());
        if(optional.isPresent()){
            return optional.get();
        }else{
            throw new DataNotFoundException("User does not exist");
        }
    }



//    public Optional<User> findByuser_name(String username) {
//        return userDetailsRepository.findByuser_name(username);
//    }


//    // fetch for testing
//    public List<User> fetchAllUsers(){
//        return userDetailsRepository.findAll();
//    }
//
//    public Optional<User> findByUserEmail(String email) {
//
//        Optional<User> optional= userDetailsRepository.findByEmail(email);
//        if(optional.isPresent()){
//            throw new CustomDataIntergrityVoilationException("already");
//        }else{
//            return null;
//        }
//
//    }


}
