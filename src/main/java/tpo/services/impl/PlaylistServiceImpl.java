package tpo.services.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.special.SnapshotResult;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.model_objects.specification.TrackSimplified;
import com.wrapper.spotify.requests.data.playlists.AddTracksToPlaylistRequest;
import com.wrapper.spotify.requests.data.playlists.GetListOfCurrentUsersPlaylistsRequest;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistsTracksRequest;
import com.wrapper.spotify.requests.data.playlists.RemoveTracksFromPlaylistRequest;
import org.springframework.stereotype.Service;
import tpo.dtos.TrackManipulationDto;
import tpo.services.PlaylistService;
import tpo.utils.TypeConverter;

import java.io.IOException;
import java.util.Arrays;

@Service
public class PlaylistServiceImpl extends BaseSpotifyAPIServiceImpl implements PlaylistService {
    @Override
    public PlaylistSimplified[] getListOfCurrentUsersPlaylists(String token) {
        GetListOfCurrentUsersPlaylistsRequest getListOfCurrentUsersPlaylistsRequest = getSpotifyApi(token)
                .getListOfCurrentUsersPlaylists()
                .build();
        try {
            return getListOfCurrentUsersPlaylistsRequest.execute().getItems();
        } catch (IOException | SpotifyWebApiException ex){
            System.out.println(ex);

            return new PlaylistSimplified[0];
        }
    }

    // Добавление трека к плейлисту
    @Override
    public void addTracksToPlaylist(String token, TrackManipulationDto trackManipulationDto) {
        try {
            AddTracksToPlaylistRequest addTracksToPlaylistRequest = getSpotifyApi(token)
                    .addTracksToPlaylist(trackManipulationDto.getPlaylistId(), new String[]{ trackManipulationDto.getUri()})
                    .build();

            SnapshotResult snapshotResult = addTracksToPlaylistRequest.execute();
        } catch (IOException | SpotifyWebApiException ex) {
            System.out.println(ex);
        }
    }

    // Удаление трека из плейлиста
    @Override
    public void removeTrackFromPlaylist(String token, TrackManipulationDto trackManipulationDto) {
        try{
            JsonArray jsonArray = new JsonParser().parse(String.format("[{\"uri\":\"%s\"}]", trackManipulationDto.getUri())).getAsJsonArray();

            RemoveTracksFromPlaylistRequest removeTracksFromPlaylistRequest = getSpotifyApi(token)
                    .removeTracksFromPlaylist(trackManipulationDto.getPlaylistId(), jsonArray)
                    .build();

            removeTracksFromPlaylistRequest.execute();
        } catch (SpotifyWebApiException | IOException ex) {
            System.out.println(ex);
        }
    }

    // Получения списка треков из плейлиста
    public TrackSimplified[] getTracksFromPlayList(String token, String playlistId) {
        try {
            GetPlaylistsTracksRequest getPlaylistsTracksRequest = getSpotifyApi(token)
                    .getPlaylistsTracks(playlistId)
                    .build();

            return Arrays
                    .stream(Arrays
                            .stream(getPlaylistsTracksRequest.execute().getItems())
                            .map(PlaylistTrack::getTrack)
                            .toArray(Track[]::new))
                    .map(TypeConverter::convertTrackToSimplified)
                    .toArray(TrackSimplified[]::new);

        } catch (SpotifyWebApiException | IOException ex) {
            return new TrackSimplified[0];
        }
    }
}
