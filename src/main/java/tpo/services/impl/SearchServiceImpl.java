package tpo.services.impl;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.search.simplified.SearchArtistsRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;
import org.springframework.stereotype.Service;
import tpo.services.SearchService;

import java.io.IOException;

@Service
public class SearchServiceImpl extends BaseSpotifyAPIServiceImpl implements SearchService {

    @Override
    public Object[] search(String token, String query, Integer offset, Integer limit, String type) {
        switch (type){
            case "track": {
                return searchTracks(token, query, offset, limit);
            } case "artist": {
                return searchArtists(token, query, offset, limit);
            } default:{
                return new Object[0];
            }
        }
    }

    @Override
    public Track[] searchTracks(String token, String query, Integer offset, Integer limit) {
        SpotifyApi spotifyApi = getSpotifyApi(token);

        SearchTracksRequest searchTracksRequest = spotifyApi
                .searchTracks(query)
                .offset(offset)
                .limit(limit)
                .build();

        try {
            return searchTracksRequest.execute().getItems();
        } catch (IOException | SpotifyWebApiException ex){
            System.out.println(ex);
            return new Track[0];
        }
    }

    @Override
    public Artist[] searchArtists(String token, String query, Integer offset, Integer limit) {
        SpotifyApi spotifyApi = getSpotifyApi(token);

        SearchArtistsRequest searchArtistsRequest = spotifyApi
                .searchArtists(query)
                .offset(offset)
                .limit(limit)
                .build();

        try {
            return searchArtistsRequest.execute().getItems();
        } catch (IOException | SpotifyWebApiException ex){
            System.out.println(ex);
            return new Artist[0];
        }
    }
}
