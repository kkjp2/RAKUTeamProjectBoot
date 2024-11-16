package rakuproject.raku.domain.move;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rakuproject.raku.domain.move.service.ReactionService;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

@SpringBootTest
public class ReactionDataTest {

    @Autowired
    private ReactionService reactionService;

    @Test
    public void generateRandomReactionsForComments() {
        Random random = new Random();

        // 评论ID范围：1到20，用户ID范围：1到100
        IntStream.rangeClosed(1, 20).forEach(commentId -> {
            int likeCount = random.nextInt(99) + 1; // 每条评论的随机点赞数量（1到999）
            int dislikeCount = random.nextInt(40) + 1; // 每条评论的随机点踩数量（1到999）

            // 添加点赞
            Set<Long> usedLikeUsers = new HashSet<>(); // 用于存储已使用的用户ID，避免重复
            IntStream.rangeClosed(1, likeCount).forEach(i -> {
                Long userId = getRandomUserId(usedLikeUsers, random);
                try {
                    reactionService.likeComment((long) commentId, userId);
                    usedLikeUsers.add(userId); // 记录用户ID
                } catch (Exception e) {
                    System.err.println("用户ID：" + userId + " 对评论ID：" + commentId + " 的点赞失败，原因：" + e.getMessage());
                }
            });

            // 添加点踩
            Set<Long> usedDislikeUsers = new HashSet<>(); // 用于存储已使用的用户ID，避免重复
            IntStream.rangeClosed(1, dislikeCount).forEach(i -> {
                Long userId = getRandomUserId(usedDislikeUsers, random);
                try {
                    reactionService.dislikeComment((long) commentId, userId);
                    usedDislikeUsers.add(userId); // 记录用户ID
                } catch (Exception e) {
                    System.err.println("用户ID：" + userId + " 对评论ID：" + commentId + " 的点踩失败，原因：" + e.getMessage());
                }
            });
        });

        System.out.println("随机生成点赞和点踩数据完成！");
    }

    /**
     * 获取随机用户ID，确保同一操作中不重复
     */
    private Long getRandomUserId(Set<Long> usedUsers, Random random) {
        Long userId;
        do {
            userId = (long) (random.nextInt(100) + 1); // 用户ID范围1到100
        } while (usedUsers.contains(userId));
        return userId;
    }

    @Test
    public void deleteAllReactions() {
        reactionService.deleteAllReactions();
        System.out.println("所有点赞和点踩数据已删除！");
    }
}
