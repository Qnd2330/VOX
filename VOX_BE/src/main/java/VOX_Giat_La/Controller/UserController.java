package VOX_Giat_La.Controller;

import VOX_Giat_La.DTO.UserDTO;
import VOX_Giat_La.DTO.UserLoginDTO;
import VOX_Giat_La.Service.User.IUserService;
import VOX_Giat_La.Service.User.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/user")
@RequiredArgsConstructor
public class UserController {
private final IUserService userService;
    @PostMapping("/register") //http://localhost:2330/VOX/user/register
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO, BindingResult result){
        try{
            if (result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            if(!userDTO.getUserPassword().equals(userDTO.getRetypePassword())){
                return ResponseEntity.badRequest().body("Mật khẩu xác thực không đúng !!!");
            }
            userService.createUser(userDTO);
            return ResponseEntity.ok("Đăng ký thành công!");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/login") //http://localhost:2330/VOX/user/login
    public ResponseEntity<String> login(@Valid @RequestBody UserLoginDTO userLoginDTO){
        return ResponseEntity.ok("some token");
    }
}
