package VOX_Giat_La.Controller;

import VOX_Giat_La.DTO.SalaryDetailDTO;
import VOX_Giat_La.DTO.StoreStorageDTO;
import VOX_Giat_La.Models.Storage;
import VOX_Giat_La.Models.StoreStorage;
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
@RequestMapping("${api.prefix}/storestorage")
@RequiredArgsConstructor
public class StoreStorageController {
    private final IStoreStorageService storeStorageService;
    private final IStorageService storageService;
    @GetMapping("/list") // http://localhost:2330/VOX/storestorage/list
    public ResponseEntity<?> getAllStoreStorage() {
        List<StoreStorage> storeStorageList = storeStorageService.getListStoreStorage();
        return ResponseEntity.ok(storeStorageList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findStoreStorageByID(@PathVariable int id) throws Exception {
        StoreStorage storeStorage = storeStorageService.getStoreStorageByID(id);
        return ResponseEntity.ok(storeStorage);
    }

    @PostMapping("/insert") //  http://localhost:2330/VOX/storestorage/insert
    public ResponseEntity<?> insertStoreStorage(@Valid @RequestBody StoreStorageDTO storeStorageDTO, BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            StoreStorage storeStorage = storeStorageService.createStoreStorage(storeStorageDTO);
            return ResponseEntity.ok(storeStorage);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/update/{id}") //   http://localhost:2330/VOX/storestorage/update
    public ResponseEntity<String> updateStoreStorage(@PathVariable int id,@Valid @RequestBody StoreStorageDTO storeStorageDTO) throws Exception {
        StoreStorage storeStorage = storeStorageService.updateStoreStorage(id,storeStorageDTO);
        return ResponseEntity.ok("Cập nhật StoreStorage");
    }

    @DeleteMapping("/delete/{id}") //    http://localhost:2330/VOX/storestorage/delete
    public void deleteStoreStorage(@PathVariable int id) {
        storeStorageService.deleteStoreStorage(id);
    }
}
