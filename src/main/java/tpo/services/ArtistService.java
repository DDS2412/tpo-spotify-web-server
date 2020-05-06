package tpo.services;

import com.wrapper.spotify.model_objects.specification.Artist;

public interface ArtistService {
    Artist getArtist(String token, String id);

    Artist[] getPersonalArtists(String token, Integer limit);
}
