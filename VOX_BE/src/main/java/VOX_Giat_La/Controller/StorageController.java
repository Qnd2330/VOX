package VOX_Giat_La.Controller;

import VOX_Giat_La.DTO.StorageDTO;
import VOX_Giat_La.Models.Storage;
import VOX_Giat_La.Service.Storage.IStorageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> getAllStorage() {
        List<Storage> storage =  storageService.getListStorage();
        return ResponseEntity.ok(storage);
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
