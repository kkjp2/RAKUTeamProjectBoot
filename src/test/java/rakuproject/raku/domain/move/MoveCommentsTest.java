package rakuproject.raku.domain.move;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rakuproject.raku.domain.move.dto.MoveReviewDTO;
import rakuproject.raku.domain.move.service.MoveReviewService;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@SpringBootTest
public class MoveCommentsTest {

    @Autowired
    private MoveReviewService moveReviewService;

    private static final List<String> COMMENTS = List.of(
            "引越し作業はとてもスムーズで、スタッフの対応も親切でした。重たい家具を丁寧に運んでくれただけでなく、設置場所のアドバイスまでしてくれて助かりました。",
            "時間通りに到着し、手際よく作業を進めてくれました。スタッフは礼儀正しく、最後まできちんと対応していただきました。また利用したいと思います。",
            "初めて引越しサービスを利用しましたが、期待以上の対応でした。特に貴重品の扱いに注意を払っていただき、安心して任せることができました。",
            "料金が少し高いと感じましたが、その分サービスの質が非常に良かったです。家電の設置まで対応してくれて、本当に助かりました。",
            "スタッフの対応が非常に良く、特に挨拶や礼儀正しい態度に感心しました。新居の床や壁に傷がつかないように細心の注意を払って作業してくれました。"
    );

    private static final List<String> REGIONS = List.of(
            "東京都", "大阪府", "愛知県", "埼玉県", "千葉県", "兵庫県", "北海道", "福岡県",
            "静岡県", "茨城県", "広島県", "京都府", "宮城県", "新潟県", "長野県", "岐阜県", "群馬県",
            "栃木県", "岡山県", "神奈川県"
    );

    @Test
    public void addRandomJapaneseComments() {
        Random random = new Random();

        // 生成 100 条随机评论
        IntStream.rangeClosed(1, 100).forEach(i -> {
            MoveReviewDTO dto = new MoveReviewDTO();

            // 随机生成公司ID和用户ID，范围为 1 到 20
            dto.setCompanyId((long) (random.nextInt(20) + 1));
            dto.setUserKey((long) (random.nextInt(20) + 1));

            // 随机评分 1 到 5
            dto.setRating((long)(random.nextInt(5) + 1));

            // 随机生成评论内容（从 COMMENTS 列表中选取）
            String comment = COMMENTS.get(random.nextInt(COMMENTS.size()));
            dto.setComment(comment);

            // 随机服务日期（过去30天内的某一天）
            dto.setServiceDate(LocalDate.now().minusDays(random.nextInt(30)));

            // 随机价格（1000到10000范围）
            dto.setPrice(String.valueOf((double) (random.nextInt(9000) + 1000))); // 转为 String


            // 随机地区（从 REGIONS 列表中选取）
            String region = REGIONS.get(random.nextInt(REGIONS.size()));
            dto.setRegion(region);

            // 调用服务层方法添加评论
            moveReviewService.addComment(dto);
        });
    }
}

