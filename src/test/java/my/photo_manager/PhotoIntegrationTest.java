package my.photo_manager;

import my.photo_manager.config.PhotoConfiguration;
import my.photo_manager.repository.PhotoRepository;
import my.photo_manager.services.PhotoFilterService;
import my.photo_manager.services.PhotoMetaDataService;
import my.photo_manager.services.PhotoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Paths;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.awaitility.Awaitility.await;
import static org.mockito.BDDMockito.given;


@SpringBootTest
class PhotoIntegrationTest {

    private PhotoService photoService;

    @Mock
    private PhotoConfiguration configuration;

    @Autowired
    private PhotoRepository repository;

    @Autowired
    private PhotoMetaDataService metaDataService;

    @Autowired
    private PhotoFilterService filterService;

    @BeforeEach
    void init() {
        photoService = new PhotoService(configuration, repository, metaDataService, filterService);
    }

    @Test
    void shouldImportPhotosFromSourceFolder() {
        given(configuration.getSource()).willReturn(List.of(Paths.get("src", "test", "resources", "TestPhotos").toString()));

        photoService.importPhotos();
        await()
                .atMost(20, SECONDS)
                .until(() -> photoService.getAll().size() > 0);

        assertThat(photoService.getAll().size()).isEqualTo(3);
    }
}