package my.photo_manager.filter;

import my.photo_manager.model.photo.Photo;

public record PhotoDimensionFilter(Photo photo) implements IFilter {
    @Override
    public String getText() {
        var height = photo.getMetaData().getHeight();
        var width = photo.getMetaData().getWidth();
        var text = height + "x" + width;

        return text.trim();
    }

    @Override
    public FilterCategory getCategory() {
        return FilterCategory.DIMENSION;
    }
}
