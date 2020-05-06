package tpo.services;

import com.wrapper.spotify.model_objects.specification.Album;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;

public interface AlbumService {
    AlbumSimplified[] getAlbums(String token, String artistId);

    Album getAlbum(String token, String id);
}
