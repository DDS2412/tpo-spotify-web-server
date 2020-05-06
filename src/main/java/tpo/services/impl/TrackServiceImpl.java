package tpo.services.impl;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.model_objects.specification.TrackSimplified;
import com.wrapper.spotify.requests.data.personalization.simplified.GetUsersTopTracksRequest;
import org.springframework.stereotype.Service;
import tpo.services.TrackService;
import tpo.utils.TypeConverter;

import java.io.IOException;
import java.util.Arrays;

@Service
public class TrackServiceImpl extends BaseSpotifyAPIServiceImpl implements TrackService {

    @Override
    public TrackSimplified[] getPersonalTracks(String token){
        return Arrays
                .stream(this.getPersonalTopTracks(token))
                .map(TypeConverter::convertTrackToSimplified)
                .toArray(TrackSimplified[]::new);
    }

    @Override
    public Track[] getPersonalTopTracks(String token) {
        GetUsersTopTracksRequest getUsersTopTracksRequest = getSpotifyApi(token)
                .getUsersTopTracks()
                .limit(10)
                .build();
        try{
            return getUsersTopTracksRequest.execute().getItems();
        } catch (IOException | SpotifyWebApiException ex) {
            System.out.println(ex);

            return new Track[0];
        }
    }
}
