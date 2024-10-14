package my.photo_manager.services;

import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import my.photo_manager.filter.IFilter;
import my.photo_manager.filter.PhotoDimensionFilter;
import my.photo_manager.filter.PhotoLocationFilter;
import my.photo_manager.model.photo.Photo;
import my.photo_manager.repository.PhotoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Log4j2
public class PhotoFilterService {

    private final PhotoRepository repository;
    private final List<IFilter> filterList = Lists.newArrayList();

    public List<Photo> filter() {
        return repository.findAll()
                .stream()
                .filter(photo -> photo.getMetaData().containsSearchString("New York"))
                .collect(Collectors.toList());
    }

    public List<IFilter> getFilterList() {
        return filterList;
    }

    public void updateFilterList(@NonNull Photo photo) {

        Predicate<IFilter> existsFilter = (newFilter) -> filterList.stream().anyMatch(filter -> filter.getText().equals(newFilter.getText()));
        Lists.newArrayList(new PhotoDimensionFilter(photo), new PhotoLocationFilter(photo));

        Stream.of(new PhotoDimensionFilter(photo),
                new PhotoLocationFilter(photo)).filter(filter -> existsFilter.negate().test(filter)).forEach(filter -> {
            filterList.add(filter);
            log.info("add {} to filterlist", kv("filter", filter));
        });
    }
}
