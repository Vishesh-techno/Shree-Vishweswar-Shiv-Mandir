package in.vishweswar.service;

import in.vishweswar.model.Contact;
import in.vishweswar.model.User;
import in.vishweswar.repository.ContactRepository;
import in.vishweswar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;
    private final UserRepository userRepository;

    private User getCurrentUser() {
        String email = (String) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return userRepository.findByEmail(email);
    }

    public Contact postContact(Contact contact) {
        User user = getCurrentUser();
        contact.setUser(user);
        return contactRepository.save(contact);
    }

    public List<Contact> getContact() {
        User user = getCurrentUser();

        if (!user.getRole().equalsIgnoreCase("admin")) {
            throw new RuntimeException("Only admin can view contacts");
        }

        return contactRepository.findAll();
    }
}