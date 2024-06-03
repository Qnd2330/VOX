package VOX_Giat_La.Controller;

import VOX_Giat_La.DTO.UserDTO;
import VOX_Giat_La.DTO.UserLoginDTO;
import VOX_Giat_La.Models.Storage;
import VOX_Giat_La.Models.User;
import VOX_Giat_La.Respones.*;
import VOX_Giat_La.Service.User.IUserService;
import VOX_Giat_La.Service.User.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

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
        try {
            String token = userService.login(userLoginDTO.getPhoneNumber(),userLoginDTO.getUserPassword());

            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/list") // http://localhost:2330/VOX/user/list
    public ResponseEntity<UserListRespone> getAllUser(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        PageRequest pageRequest = PageRequest.of(page,limit, Sort.by("userID").descending());
        Page<UserRespone>  userPage = userService.getListUser(pageRequest);
        int totalPages = userPage.getTotalPages();
        List<UserRespone> user = userPage.getContent();
        return ResponseEntity.ok(UserListRespone.builder()
                .userResponeList(user)
                .totalPages(totalPages)
                .build());
    }

    @DeleteMapping("/delete/{id}") //    http://localhost:2330/VOX/user/delete
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("Đã xóa thành công User "+ id);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getUserDetail(@PathVariable int id) throws Exception {
        try {
            UserDetailRespone userDetailRespone = userService.getUser(id);
            return ResponseEntity.ok(userDetailRespone);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Không tìm thấy user");
        }
    }
}
