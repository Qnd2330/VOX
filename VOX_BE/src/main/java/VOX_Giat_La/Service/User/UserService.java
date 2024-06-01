package VOX_Giat_La.Service.User;

import VOX_Giat_La.Components.JwtTokenUtil;
import VOX_Giat_La.DTO.UserDTO;
import VOX_Giat_La.Exeception.DataNotFoundException;
import VOX_Giat_La.Models.Roles;
import VOX_Giat_La.Models.StoreStorage;
import VOX_Giat_La.Models.User;
import VOX_Giat_La.Repositories.RolesRepos;
import VOX_Giat_La.Repositories.UserRepos;
import VOX_Giat_La.Respones.UserRespone;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
    private final UserRepos userRepos;
    private final RolesRepos rolesRepos;
    private final PasswordEncoder passwordEncoder;

    private final JwtTokenUtil jwtTokenUtil;

    private final AuthenticationManager authenticationManager;
    @Override
    public User createUser(UserDTO userDTO) throws Exception {
        String phone = userDTO.getPhoneNumber();
        if(userRepos.existsByPhoneNumber(phone)){
            throw  new DataIntegrityViolationException("Số điện thoại đã tồn tại");
        }
        User newUser = User.builder()
                .userName(userDTO.getUserName())
                .userPassword(userDTO.getUserPassword())
                .phoneNumber(userDTO.getPhoneNumber())
                .userGender(userDTO.getUserGender())
                .userAddress(userDTO.getUserAddress())
                .userBirthDate(userDTO.getUserBirthDate())
                .build();
        Roles roles = rolesRepos.findById(userDTO.getRoleID()) .orElseThrow(()-> new DataNotFoundException("Không tìm thấy ROLE "));
        newUser.setRole(roles);
        String password = userDTO.getUserPassword();
        String encodedPassword = passwordEncoder.encode(password);
        newUser.setUserPassword(encodedPassword);
        return userRepos.save(newUser);
    }

    @Override
    public String login(String phoneNumber, String password) throws Exception {
        Optional<User> user  =  userRepos.findByPhoneNumber(phoneNumber);
        if(user.isEmpty()){
            throw new DataNotFoundException("SĐT hoặc Mật khẩu không đúng !");
        }
        User exitingUser = user.get();
        if(!passwordEncoder.matches(password,exitingUser.getPassword())){
            throw new BadCredentialsException("Sai số điện thoại hoặc mật khẩu ");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(phoneNumber,password,exitingUser.getAuthorities());
        authenticationManager.authenticate(authenticationToken);
        return jwtTokenUtil.generateToken(exitingUser);
    }

    @Override
    public Optional<User> getByPhoneNumber(String phoneNumber) throws Exception {
        Optional<User> exitingUser = userRepos.findByPhoneNumber(phoneNumber);
        return exitingUser;
    }

    @Override
    public Page<UserRespone> getListUser(PageRequest pageRequest) {
        return userRepos.findAll(pageRequest).map(user -> UserRespone.fromUser(user));
    }

    @Override
    public void deleteUser(int id) {
        Optional<User> optionalUser = userRepos.findById(id);
        optionalUser.ifPresent(userRepos::delete);
    }

}
