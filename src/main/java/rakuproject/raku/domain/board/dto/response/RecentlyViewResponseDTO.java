package rakuproject.raku.domain.board.dto.response;



import lombok.Getter;
import rakuproject.raku.domain.board.entity.RecentlyViewEntity;

@Getter
public class RecentlyViewResponseDTO {
    private final Long r_id;
    private final Long u_id;
    private final Long b_id;
    private final Long f_id;

    public RecentlyViewResponseDTO(RecentlyViewEntity recentlyView)
    {
        this.r_id= recentlyView.getRId();
        this.u_id=recentlyView.getMember().getUserKey();
        this.b_id=recentlyView.getBoard().getNId();
        this.f_id=recentlyView.getFestivalBoard().getFbId();

    }

}
