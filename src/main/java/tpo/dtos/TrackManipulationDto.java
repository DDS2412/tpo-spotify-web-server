package tpo.dtos;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TrackManipulationDto {
    private String uri;
    private String playlistId;
}
