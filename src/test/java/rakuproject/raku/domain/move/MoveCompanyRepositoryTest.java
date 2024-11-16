package rakuproject.raku.domain.move;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import rakuproject.raku.RakuApplication;
import rakuproject.raku.domain.move.entity.MoveCompanyEntity;
import rakuproject.raku.domain.move.entity.UploadFileEntity;
import rakuproject.raku.domain.move.repository.MoveCompanyRepository;
import rakuproject.raku.domain.move.repository.UploadFileRepository;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = RakuApplication.class)
@AutoConfigureMockMvc
public class MoveCompanyRepositoryTest {

    @Autowired
    private MoveCompanyRepository moveCompanyRepository;

    @Autowired
    private UploadFileRepository uploadFileRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Transactional
    @Rollback(false)
    void testCreateCompanyWithFiles() throws Exception {
        // 准备地区和服务列表
        List<String> japanRegions = Arrays.asList("全地域", "東京都", "大阪府", "愛知県", "埼玉県", "千葉県", "兵庫県", "北海道", "福岡県",
                "静岡県", "茨城県", "広島県", "京都府", "宮城県", "新潟県", "長野県", "岐阜県", "群馬県",
                "栃木県", "岡山県", "神奈川県");
        List<String> services = Arrays.asList("梱包サービス", "清掃サービス", "保管サービス", "ピアノ運搬", "家具組み立て", "安全設置");

        Random random = new Random();
        IntStream.rangeClosed(3, 30).forEach(i -> {
            // 随机选择地区和服务
            String selectedRegions = getRandomSelection(japanRegions, random, 5, 10);
            String selectedServices = getRandomSelection(services, random, 4, 7);

            try {
                // 创建随机图片文件
                String fileName = "imgs" + (random.nextInt(13) + 1) + ".png";
                byte[] imageData = Files.readAllBytes(Paths.get("src/test/resources/" + fileName));
                MockMultipartFile file = new MockMultipartFile("uploadFiles", fileName, "image/png", imageData);

                // 创建唯一的公司名称和邮箱
                String companyName = "Test Company " + i;
                String email = "contact" + i + "@testcompany.com";

                // 模拟创建公司并上传文件
                mockMvc.perform(MockMvcRequestBuilders.multipart("/move/company/createWithFiles")
                                .file(file)
                                .param("name", companyName)
                                .param("ceo", "John Doe " + i)
                                .param("phone", "123-456-789" + i)
                                .param("email", email)
                                .param("postalCode", "123-456" + i)
                                .param("address", "Tokyo, Japan")
                                .param("detailedAddress", "Shibuya District, Building 5-8")
                                .param("businessNumber", "56789-123" + i)
                                .param("service", selectedServices)
                                .param("city", selectedRegions)
                                .param("introduction", "Company " + i + " introduction")
                                .contentType(MediaType.MULTIPART_FORM_DATA))
                        .andExpect(status().isCreated());

                // 验证创建的公司
                MoveCompanyEntity createdCompany = moveCompanyRepository.findAll()
                        .stream()
                        .filter(company -> companyName.equals(company.getName()))
                        .findFirst()
                        .orElseThrow(() -> new AssertionError("未找到刚刚创建的公司实体: " + companyName));

                // 验证上传的文件信息
                List<UploadFileEntity> uploadedFiles = uploadFileRepository.findByCompanyId(createdCompany);
                assertFalse(uploadedFiles.isEmpty(), "Uploaded files should be associated with the created company");

            } catch (Exception e) {
                e.printStackTrace();
                fail("测试过程中发生异常: " + e.getMessage());
            }
        });
    }

    /**
     * 从列表中随机选择元素。
     */
    private String getRandomSelection(List<String> list, Random random, int min, int max) {
        Collections.shuffle(list);
        int count = random.nextInt(max - min + 1) + min;
        return list.stream().limit(count).collect(Collectors.joining(","));
    }

}

