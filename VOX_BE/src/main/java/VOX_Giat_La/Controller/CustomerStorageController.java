package VOX_Giat_La.Controller;

import VOX_Giat_La.DTO.CustomerStorageDTO;
import VOX_Giat_La.Exeception.DataNotFoundException;
import VOX_Giat_La.Models.CustomerStorage;
import VOX_Giat_La.Respones.Storage.CustomerStorageListRespone;
import VOX_Giat_La.Respones.Storage.CustomerStorageRespone;
import VOX_Giat_La.Service.BillDetails.BillDetailsService;
import VOX_Giat_La.Service.CustomerStorage.CustomerStorageService;
import VOX_Giat_La.Service.Storage.IStorageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/customerstorage")
@RequiredArgsConstructor
public class CustomerStorageController {
    private final CustomerStorageService customerStorageService;
    private final BillDetailsService billDetailsService;
    private final IStorageService storageService;
    @GetMapping("/list") // http://localhost:2330/VOX/customerstorage/list
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER') or hasRole('ROLE_EMPLOYEE')")
    public ResponseEntity<?> getAllCustomerStorage(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        PageRequest pageRequest = PageRequest.of(page,limit, Sort.by("cusItemID").descending());
        Page<CustomerStorageRespone> customerStorageResponePage = customerStorageService.getListCustomerStorage(pageRequest);
        int totalPages = customerStorageResponePage.getTotalPages();
        List<CustomerStorageRespone> customerStorageRespones = customerStorageResponePage.getContent();
        return ResponseEntity.ok(CustomerStorageListRespone.builder()
                .customerStorageRespones(customerStorageRespones)
                .totalPages(totalPages)
                .build());
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

    @GetMapping("/BillDetails/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER') or hasRole('ROLE_EMPLOYEE')")
    public CustomerStorage getByBillDetailID(@PathVariable int id) throws DataNotFoundException {
        return customerStorageService.getCustomerStoragebyBillDetail(id);
    }
}
