package in.vishweswar.controller;

import in.vishweswar.model.Donation;
import in.vishweswar.service.DonationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donation")
@RequiredArgsConstructor
public class DonationController {

    private DonationService donationService;

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/donate")
    public Donation donate(@RequestBody Donation donation){
        return donationService.saveDonation(donation);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/user")
    public List<Donation> getUserDonation(@RequestParam Long userId){
        return donationService.getUserDonations(userId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public Donation getDonationById(@PathVariable Long id){
        return donationService.getDonationById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteDonation(@PathVariable Long id,
                                 @RequestParam String email){
        donationService.deleteDonation(id, email);
        return "Deleted Successfully";
    }
}