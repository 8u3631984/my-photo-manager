package my.photo_manager.services;

import my.photo_manager.model.photo.Photo;
import my.photo_manager.repository.PhotoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PhotoServiceTest {


    @Mock
    private PhotoRepository repository;

    @Mock
    private PhotoMetaDataService metaDataService;

    private PhotoService photoService;

    @BeforeEach
    void init() {
        photoService = new PhotoService(repository, metaDataService);
    }

    @Test
    void shouldReturnAllPhotoObjects() {
        photoService.getAll();

        verify(repository).findAll();
    }

    @Test
    void shouldReturnHashValueOfPhotoFile() throws IOException {
        var testPhotoFile = Paths.get("src", "test", "resources", "TestPhotos", "Photo1.jpg").toFile();
        var hashValue = photoService.getHashValue(testPhotoFile);

        assertThat(hashValue).isNotNull();
        assertThat(hashValue).isNotEmpty();
    }


    @Test
    void shouldBuildPhotoObject() {
        var testPhotoFile = Paths.get("src", "test", "resources", "TestPhotos", "Photo1.jpg").toFile();
        var photoObject = photoService.buildPhotoObject(testPhotoFile);

        assertThat(photoObject).isNotNull();
        assertThat(photoObject.getFilePath()).isNotNull();
        assertThat(photoObject.getFilePath()).isNotEmpty();
        assertThat(photoObject.getHashValue()).isNotNull();
        assertThat(photoObject.getHashValue()).isNotEmpty();
    }

    @Test
    void shouldSavePhotoObject() {
        photoService.savePhotoObject(mock(Photo.class));

        verify(repository).saveAndFlush(any(Photo.class));
    }

    @Test
    void shouldNotSavePhotoObjectWhen() {
        photoService.savePhotoObject(mock(Photo.class));

        verify(repository).saveAndFlush(any(Photo.class));
    }

    @Test
    void shouldBuildAndSavePhotoObject() {
        var testPhotoFile = Paths.get("src", "test", "resources", "TestPhotos", "Photo1.jpg").toFile();
        photoService.buildAndSavePhotoObject(testPhotoFile);

        verify(repository).saveAndFlush(any(Photo.class));
    }
}