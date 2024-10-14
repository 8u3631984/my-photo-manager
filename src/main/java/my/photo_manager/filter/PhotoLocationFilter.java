package my.photo_manager.filter;

import my.photo_manager.model.photo.Photo;

public record PhotoLocationFilter(Photo photo) implements IFilter {

    @Override
    public String getText() {
        var country = photo.getMetaData().getCountry();
        var city = photo.getMetaData().getCity();
        var postalCode = photo.getMetaData().getCity();
        var street = photo.getMetaData().getStreet();
        var houseNumber = photo.getMetaData().getHouseNumber();
        var text = country + " " + city + " " + postalCode + " " + street + " " + houseNumber;

        return text.trim();
    }

    @Override
    public FilterCategory getCategory() {
        return FilterCategory.LOCATION;
    }
}
