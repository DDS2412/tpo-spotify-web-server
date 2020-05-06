package tpo.rest;

import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.model_objects.specification.TrackSimplified;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tpo.dtos.TrackManipulationDto;
import tpo.services.PlaylistService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/playlists")
@CrossOrigin(allowCredentials = "true")
public class PlaylistController {
    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping("")
    public ResponseEntity<PlaylistSimplified[]> getPlaylists(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(playlistService.getListOfCurrentUsersPlaylists(token));
    }

    @PostMapping("/add")
    public ResponseEntity addTrack(@RequestHeader("Authorization") String token,
                                                         @RequestBody @Valid TrackManipulationDto trackManipulationDto){
        playlistService.addTracksToPlaylist(token, trackManipulationDto);

        return ResponseEntity.ok("");
    }

    @PostMapping("/remove")
    public ResponseEntity removeTrack(@RequestHeader("Authorization") String token,
                                                            @RequestBody @Valid TrackManipulationDto trackManipulationDto){
        playlistService.removeTrackFromPlaylist(token, trackManipulationDto);

        return ResponseEntity.ok("");
    }

    @GetMapping("/tracks")
    public ResponseEntity<TrackSimplified[]> getTracksFromPlayList(@RequestHeader("Authorization") String token,
                                                                   @RequestParam("playlist_id") String id){
        return ResponseEntity.ok(playlistService.getTracksFromPlayList(token, id));
    }
}
