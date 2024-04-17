package VOX_Giat_La.Repositories;

import VOX_Giat_La.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepos extends JpaRepository<User,Integer> {
    boolean existsByPhoneNumber(String phoneNumber);
}
