package VOX_Giat_La.Service.User;

import VOX_Giat_La.DTO.UserDTO;
import VOX_Giat_La.Models.User;

public interface IUserService {

        User createUser(UserDTO userDTO) throws Exception;
        String login(String phoneNumber, String password, Long roleId) throws Exception;

}
