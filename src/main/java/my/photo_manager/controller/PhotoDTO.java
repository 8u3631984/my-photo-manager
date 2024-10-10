package my.photo_manager.controller;

import lombok.NonNull;

public record PhotoDTO (long ID, @NonNull String rawData){
}
