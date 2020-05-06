package tpo.services;

import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.model_objects.specification.TrackSimplified;
import tpo.dtos.TrackManipulationDto;

public interface PlaylistService {
    PlaylistSimplified[] getListOfCurrentUsersPlaylists(String token);

    void addTracksToPlaylist(String token, TrackManipulationDto trackManipulationDto);

    void removeTrackFromPlaylist(String token, TrackManipulationDto trackManipulationDto);

    TrackSimplified[] getTracksFromPlayList(String token, String playlistId);
}
