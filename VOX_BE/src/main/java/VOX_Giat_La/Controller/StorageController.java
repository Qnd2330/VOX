package VOX_Giat_La.Controller;

import VOX_Giat_La.DTO.StorageDTO;
import VOX_Giat_La.Models.Storage;
import VOX_Giat_La.Respones.Storage.CustomerStorageListRespone;
import VOX_Giat_La.Respones.Storage.CustomerStorageRespone;
import VOX_Giat_La.Respones.Storage.StorageListRespone;
import VOX_Giat_La.Respones.Storage.StorageRespone;
import VOX_Giat_La.Service.Storage.IStorageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/storage")
@RequiredArgsConstructor
public class StorageController {
    private final IStorageService storageService;
    @GetMapping("/list") // http://localhost:2330/VOX/storage/list
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_EMPLOYEE')")
    public ResponseEntity<?> getAllStorage(@RequestParam("page")int page, @RequestParam("limit") int limit) {
        PageRequest pageRequest = PageRequest.of(page,limit,Sort.by("storageID").descending());
        Page<StorageRespone> storageRespones = storageService.getListStorage(pageRequest);
        int totalPages = storageRespones.getTotalPages();
        List<StorageRespone> storageResponeList = storageRespones.getContent();
        return ResponseEntity.ok(StorageListRespone.builder()
                .storageRespone(storageResponeList)
                .totalPages(totalPages)
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findStoreStorageByID(@PathVariable int id) {
        Storage storage = storageService.getStoragebyID(id);
        return ResponseEntity.ok(storage);
    }

    @PostMapping("/insert") //  http://localhost:2330/VOX/storage/insert
    public ResponseEntity<?> insertStorage(@Valid @RequestBody StorageDTO storageDTO, BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            Storage storage = storageService.createStorage(storageDTO);
            return ResponseEntity.ok(storage);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/update/{id}") //   http://localhost:2330/VOX/storage/update
    public ResponseEntity<String> updateStorage(@PathVariable int id, @Valid @RequestBody StorageDTO storageDTO ) throws Exception {
        storageService.updateStorage(id,storageDTO);
        return ResponseEntity.ok("Cập nhật storage");
    }

    @DeleteMapping("/delete/{id}") //    http://localhost:2330/VOX/storage/delete
    public void deleteStorage(@PathVariable int id) {
        storageService.deleteStorage(id);
    }
}
