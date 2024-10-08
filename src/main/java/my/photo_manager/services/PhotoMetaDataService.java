package my.photo_manager.services;

import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import my.photo_manager.model.metadata.PhotoMetaData;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.io.File;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Service
@Log4j2
public class PhotoMetaDataService {

    public PhotoMetaData buildPhotoMetaData(@NonNull File photoFile) {
        var photoMetaData = PhotoMetaData.builder()
                .withHeight(getHeight())
                .withWidth(getWidth())
                .withCreationTimeStamp(getCreationTimeStamp())
                .withCountry(getCountry())
                .withCity(getCity())
                .withPostCode(getPostalCode())
                .withStreet(getStreet())
                .withHouseNumber(getHouseNumber())
                .build();
        log.debug("build {}", kv("photoMetaData", photoMetaData));

        return photoMetaData;
    }

    protected int getHeight() {
        return 0;
    }

    protected int getWidth() {
        return 0;
    }

    protected String getCreationTimeStamp() {
        return Strings.EMPTY;
    }

    protected String getCountry() {
        return Strings.EMPTY;
    }

    protected String getCity() {
        return Strings.EMPTY;
    }

    protected String getPostalCode() {
        return Strings.EMPTY;
    }

    protected String getStreet() {
        return Strings.EMPTY;
    }

    protected String getHouseNumber() {
        return Strings.EMPTY;
    }
}
