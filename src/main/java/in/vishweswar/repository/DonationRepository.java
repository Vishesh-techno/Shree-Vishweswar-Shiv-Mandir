package in.vishweswar.repository;

import in.vishweswar.model.Donation;
import in.vishweswar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {

    List<Donation> findByUser(User user);
}
