package my.photo_manager.web;

import lombok.ToString;
import my.photo_manager.filter.FilterCategory;

public record FilterDTO(FilterCategory category, String text) {
}
