package in.vishweswar.controller;

import in.vishweswar.model.Gallery;
import in.vishweswar.service.GalleryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gallery")
@RequiredArgsConstructor
public class GalleryController {
    private final GalleryService galleryService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public Gallery getGallery(@RequestBody Gallery gallery){
        return galleryService.addImage(gallery);
    }

    @GetMapping
    public List<Gallery> getAllImages(){
        return galleryService.getAllImages();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteImage(@PathVariable Long id){
        galleryService.deleteImage(id);
        return "Deleted SuccessFully";
    }
}
