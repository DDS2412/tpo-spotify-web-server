package tpo.services.impl;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Album;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.requests.data.albums.GetAlbumRequest;
import com.wrapper.spotify.requests.data.artists.GetArtistsAlbumsRequest;
import org.springframework.stereotype.Service;
import tpo.services.AlbumService;

import java.io.IOException;

@Service
public class AlbumServiceImpl extends BaseSpotifyAPIServiceImpl implements AlbumService {

    public AlbumSimplified[] getAlbums(String token, String artistId) {
        SpotifyApi spotifyApi = getSpotifyApi(token);

        GetArtistsAlbumsRequest getArtistsAlbumsRequest = spotifyApi.getArtistsAlbums(artistId)
                .build();
        try {
            return getArtistsAlbumsRequest.execute().getItems();
        } catch (IOException | SpotifyWebApiException ex){
            System.out.println(ex);

            return new AlbumSimplified[0];
        }
    }

    @Override
    public Album getAlbum(String token, String id) {
        SpotifyApi spotifyApi = getSpotifyApi(token);

        GetAlbumRequest getAlbumRequest = spotifyApi.getAlbum(id)
                .build();
        try {
            return getAlbumRequest.execute();
        } catch (IOException | SpotifyWebApiException ex){
            System.out.println(ex);

            return null;
        }
    }
}
