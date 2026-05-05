package in.vishweswar.service;

import in.vishweswar.model.Gallery;
import in.vishweswar.model.User;
import in.vishweswar.repository.GalleryRepository;
import in.vishweswar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GalleryService {

    private final GalleryRepository galleryRepository;
    private final UserRepository userRepository;

    private User getCurrentUser() {
        String email = (String) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return userRepository.findByEmail(email);
    }

    public Gallery addImage(Gallery gallery) {

        User user = getCurrentUser();

        if (!user.getRole().equalsIgnoreCase("admin")) {
            throw new RuntimeException("Only Admin Can Add Images");
        }

        return galleryRepository.save(gallery);
    }

    public List<Gallery> getAllImages() {
        return galleryRepository.findAll();
    }

    public void deleteImage(Long id) {

        User user = getCurrentUser();

        if (!user.getRole().equalsIgnoreCase("admin")) {
            throw new RuntimeException("Only Admin can delete Image");
        }

        galleryRepository.deleteById(id);
    }
}