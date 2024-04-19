package VOX_Giat_La.Controller;

import VOX_Giat_La.DTO.CustomerStorageDTO;
import VOX_Giat_La.Models.CustomerStorage;
import VOX_Giat_La.Models.StoreStorage;
import VOX_Giat_La.Service.CustomerStorage.CustomerStorageService;
import VOX_Giat_La.Service.Storage.IStorageService;
import VOX_Giat_La.Service.StoreStorage.IStoreStorageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/customerstorage")
@RequiredArgsConstructor
public class CustomerStorageController {
    private final CustomerStorageService customerStorageService;
    private final IStorageService storageService;
    @GetMapping("/list") // http://localhost:2330/VOX/customerstorage/list
    public ResponseEntity<?> getAllCustomerStorage() {
        List<CustomerStorage> customerStorageList = customerStorageService.getListCustomerStorage();
        return ResponseEntity.ok(customerStorageList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findCustomerStorageByID(@PathVariable int id) throws Exception {
        CustomerStorage customerStorage = customerStorageService.getCustomerStoragebyID(id);
        return ResponseEntity.ok(customerStorage);
    }

    @PostMapping("/insert") //  http://localhost:2330/VOX/customerstorage/insert
    public ResponseEntity<?> insertCustomerStorage(@Valid @RequestBody CustomerStorageDTO customerStorageDTO, BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            CustomerStorage customerStorage = customerStorageService.createCustomerStorage(customerStorageDTO);
            return ResponseEntity.ok(customerStorage);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/update/{id}") //   http://localhost:2330/VOX/customerstorage/update
    public ResponseEntity<String> updateCustomerStorage(@PathVariable int id, @Valid @RequestBody CustomerStorageDTO customerStorageDTO) throws Exception {
        CustomerStorage customerStorage = customerStorageService.updateCustomerStorage(id,customerStorageDTO);
        return ResponseEntity.ok("Cập nhật CustomerStorage");
    }

    @DeleteMapping("/delete/{id}") //    http://localhost:2330/VOX/customerstorage/delete
    public void deleteCustomerStorage(@PathVariable int id) {
        customerStorageService.deleteCustomerStorage(id);
    }
}
