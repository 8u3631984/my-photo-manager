package my.photo_manager.controller;

import lombok.NonNull;
import my.photo_manager.photo.PhotoDTO;
import my.photo_manager.service.PhotoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/photo")
public class PhotoController {

    private final PhotoService photoService;

    protected PhotoController(@NonNull PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/getAll")
    public List<PhotoDTO> getAllPhotos(){
        System.out.println("in photo controller");
       return  photoService.getAll()
                .stream()
                .map(photo -> new PhotoDTO(photo.getID(), "RAW DATA"))
                .collect(Collectors.toList());
    }
}
