package VOX_Giat_La.DAO;

import VOX_Giat_La.Entity.DaXong.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO  extends JpaRepository<User, Integer> {
}
