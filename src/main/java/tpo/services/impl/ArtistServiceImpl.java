package tpo.services.impl;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.artists.GetArtistRequest;
import com.wrapper.spotify.requests.data.personalization.simplified.GetUsersTopArtistsRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;
import org.springframework.stereotype.Service;
import tpo.services.ArtistService;

import java.io.IOException;

@Service
public class ArtistServiceImpl extends BaseSpotifyAPIServiceImpl implements ArtistService {

    @Override
    public Artist getArtist(String token, String id) {
        SpotifyApi spotifyApi = getSpotifyApi(token);

        GetArtistRequest getArtistRequest = spotifyApi
                .getArtist(id)
                .build();
        try {
            return getArtistRequest.execute();
        } catch (IOException | SpotifyWebApiException ex){
            System.out.println(ex);

            return null;
        }
    }

    public Artist[] getPersonalArtists(String token, Integer limit) {
        try {
            return this.getPersonalTopArtists(token, limit).getItems();
        } catch (SpotifyWebApiException | IOException ex) {
            return new Artist[0];
        }
    }

    private Paging<Artist> getPersonalTopArtists(String token, Integer limit) throws IOException, SpotifyWebApiException{
        GetUsersTopArtistsRequest getUsersTopArtistsRequest = getSpotifyApi(token)
                .getUsersTopArtists()
                .limit(limit)
                .build();

        return getUsersTopArtistsRequest.execute();
    }
}
