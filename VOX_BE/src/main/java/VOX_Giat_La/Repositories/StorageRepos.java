package VOX_Giat_La.Repositories;

import VOX_Giat_La.Models.Storage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageRepos extends JpaRepository<Storage, Integer> {
    boolean existsByStoragePosition(String storagePosition);
}
