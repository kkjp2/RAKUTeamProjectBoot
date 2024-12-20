package rakuproject.raku.domain.announcement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import rakuproject.raku.domain.announcement.entity.AnnEntity;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AnnViewResponse {
    private List<AnnEntity> announcements;
}
