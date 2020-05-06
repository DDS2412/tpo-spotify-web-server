package tpo.services;

import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.Track;

public interface SearchService {
    Track[] searchTracks(String token, String query, Integer offset, Integer limit);

    Artist[] searchArtists(String token, String query, Integer offset, Integer limit);

    Object[] search(String token, String query, Integer offset, Integer limit, String type);
}
