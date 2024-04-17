package VOX_Giat_La.Service.User;

import VOX_Giat_La.DTO.UserDTO;
import VOX_Giat_La.Exeception.DataNotFoundException;
import VOX_Giat_La.Models.Roles;
import VOX_Giat_La.Models.User;
import VOX_Giat_La.Repositories.RolesRepos;
import VOX_Giat_La.Repositories.UserRepos;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.zip.DataFormatException;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
    private final UserRepos userRepos;
    private final RolesRepos rolesRepos;
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

        return userRepos.save(newUser);
    }

    @Override
    public String login(String phoneNumber, String password, Long roleId) throws Exception {
        return null;
    }
}
