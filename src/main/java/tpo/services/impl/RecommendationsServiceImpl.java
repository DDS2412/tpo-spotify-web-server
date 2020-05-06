package tpo.services.impl;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.TrackSimplified;
import com.wrapper.spotify.requests.data.browse.GetRecommendationsRequest;
import org.springframework.stereotype.Service;
import tpo.services.ArtistService;
import tpo.services.RecommendationsService;
import tpo.services.TrackService;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class RecommendationsServiceImpl extends BaseSpotifyAPIServiceImpl implements RecommendationsService {
    private final ArtistService artistService;
    private final TrackService trackService;

    public RecommendationsServiceImpl(ArtistService artistService, TrackService trackService) {
        this.artistService = artistService;
        this.trackService = trackService;
    }

    @Override
    public TrackSimplified[] getMusicRecommendationsByArtists(String token, Integer limit){
        try {
            String seed_artists = Arrays
                    .stream(artistService.getPersonalArtists(token, 10))
                    .limit(10)
                    .map(Artist::getId)
                    .collect(Collectors.joining(","));
            if(!seed_artists.isEmpty()){
                return this.getMusicRecommendations(token, limit, seed_artists, "");
            }

            return new TrackSimplified[0];
        } catch (SpotifyWebApiException | IOException ex) {
            return new TrackSimplified[0];
        }
    }

    // Получение рекомендаций на основе треков
    @Override
    public TrackSimplified[] getMusicRecommendationsByTrack(String token, Integer limit) {
        try {
            String seed_tracks = Arrays
                    .stream(trackService.getPersonalTracks(token))
                    .limit(5)
                    .map(TrackSimplified::getId)
                    .collect(Collectors.joining(","));
            if(!seed_tracks.isEmpty()){
                return this.getMusicRecommendations(token, limit, "", seed_tracks);
            }

            return new TrackSimplified[0];
        } catch (SpotifyWebApiException | IOException ex) {
            System.out.println(ex);

            return new TrackSimplified[0];
        }
    }

    @Override
    public TrackSimplified[] getMusicRecommendationsBy(String token, Integer limit, String type) {
        switch (type){
            case "artists": {
                return this.getMusicRecommendationsByArtists(token, limit);
            } case "tracks": {
                return this.getMusicRecommendationsByTrack(token, limit);
            } default:{
                return new TrackSimplified[0];
            }
        }
    }

    private TrackSimplified[]  getMusicRecommendations(String token, Integer limit, String seed_artists, String seed_tracks)
            throws IOException, SpotifyWebApiException {

        GetRecommendationsRequest getRecommendationsRequest = getSpotifyApi(token)
                .getRecommendations()
                .seed_artists(seed_artists)
                .seed_tracks(seed_tracks)
                .limit(limit)
                .build();

        return getRecommendationsRequest.execute().getTracks();
    }
}
