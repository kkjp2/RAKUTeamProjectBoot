package com.example.board_test.board.dto.response;


import com.example.board_test.board.entity.RecentlyViewEntity;
import lombok.Getter;

@Getter
public class RecentlyViewResponseDTO {
    private final Long r_id;
    private final Long u_id;
    private final Long b_id;
    private final Long f_id;

    public RecentlyViewResponseDTO(RecentlyViewEntity recentlyView)
    {
        this.r_id= recentlyView.getR_id();
        this.u_id=recentlyView.getMember().getUser_key();
        this.b_id=recentlyView.getBoard().getN_id();
        this.f_id=recentlyView.getFestivalBoard().getF_id();

    }

}
