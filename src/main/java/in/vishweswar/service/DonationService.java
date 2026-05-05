package in.vishweswar.service;

import in.vishweswar.model.Donation;
import in.vishweswar.model.User;
import in.vishweswar.repository.DonationRepository;
import in.vishweswar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DonationService {

    private final DonationRepository donationRepository;
    private final UserRepository userRepository;

    private User getCurrentUser() {
        String email = (String) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return userRepository.findByEmail(email);
    }

    public Donation saveDonation(Donation donation) {

        User user = getCurrentUser();

        donation.setUser(user);
        donation.setDate(LocalDate.now());

        return donationRepository.save(donation);
    }

    public List<Donation> getUserDonations(Long userId) {
        User user = getCurrentUser();
        return donationRepository.findByUser(user);
    }

    public Donation getDonationById(Long id) {
        return donationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invalid id"));
    }

    public void deleteDonation(Long id, String email) {

        User user = getCurrentUser();

        if (!user.getRole().equalsIgnoreCase("admin")) {
            throw new RuntimeException("Only admin can delete donation");
        }

        donationRepository.deleteById(id);
    }
}