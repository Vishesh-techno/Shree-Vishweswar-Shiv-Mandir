package in.vishweswar.controller;

import in.vishweswar.model.Contact;
import in.vishweswar.service.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
@AllArgsConstructor
public class ContactController {

    public ContactService contactService;

    @PostMapping
    public Contact postContact(@RequestBody Contact contact){
        return contactService.postContact(contact);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Contact> getContact(){
        return contactService.getContact();
    }
}
