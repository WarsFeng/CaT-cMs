package cat.wars.cms.manager_course;

import io.github.swagger2markup.GroupBy;
import io.github.swagger2markup.Language;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;
import java.nio.file.Paths;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 7/11/19
 * Time: 2:36 PM
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class ExportSwaggerTest {

    /**
     * Generate markdown
     *
     * @throws Exception
     */
    @Test
    public void generateMarkdownDocs() throws Exception {
        //    ���Markdown��ʽ
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.MARKDOWN)
                .withOutputLanguage(Language.ZH)
                .withPathsGroupedBy(GroupBy.TAGS)
                .withGeneratedExamples()
                .withoutInlineSchema()
                .build();

        Swagger2MarkupConverter.from(new URL("http://localhost:20100/v2/api-docs"))
                .withConfig(config)
                .build()
                .toFolder(Paths.get("./docs/markdown/generated"));
    }

    /**
     * ����Markdown��ʽ�ĵ�,�����ܳ�һ���ļ�
     *
     * @throws Exception
     */
    @Test
    public void generateMarkdownDocsToFile() throws Exception {
        //    ���Markdown�����ļ�
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.MARKDOWN)
                .withOutputLanguage(Language.ZH)
                .withPathsGroupedBy(GroupBy.TAGS)
                .withGeneratedExamples()
                .withoutInlineSchema()
                .build();

        Swagger2MarkupConverter.from(new URL("http://localhost:8082/v2/api-docs"))
                .withConfig(config)
                .build()
                .toFile(Paths.get("./docs/markdown/generated/all"));
    }
}
