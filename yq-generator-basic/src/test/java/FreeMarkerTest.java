import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lyqq
 * @description: FreeMarkerTest
 * @date 2023/11/13 16:54
 */
public class FreeMarkerTest {

    @Test
    public void test() throws IOException, TemplateException {
        // new 出 Configuration 对象，参数为 FreeMarker 版本号
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);
        // 指定模板文件所在的路径
        configuration.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));
        // 设置模板文件使用的字符集
        configuration.setDefaultEncoding("utf-8");
//        设置数字格式
        configuration.setNumberFormat("0.#####");
//        创建模板对象，指定模板
        Template template = configuration.getTemplate("myweb.html.ftl");
//        创建数据模型
        HashMap<String, Object> modelMap = new HashMap<>();
        modelMap.put("currentYear", 2023);
        List<Map<String, Object>> menuItems = new ArrayList<>();
        HashMap<String, Object> menuItem1 = new HashMap<>();
        menuItem1.put("url", "https://www.bilibili.com/");
        menuItem1.put("label", "bilibili");
        HashMap<String, Object> menuItem2 = new HashMap<>();
        menuItem2.put("url", "https://www.huya.com/");
        menuItem2.put("label", "虎牙直播");
        menuItems.add(menuItem1);
        menuItems.add(menuItem2);
        modelMap.put("menuItems", menuItems);
//        指定生成的文件路径和名称
        Writer out = new FileWriter("myweb.html");
        template.process(modelMap,out);
        out.close();

    }
}
