package playright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayrightTests {

    @Test
    public void testPlayright() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch();
            Page page = browser.newPage();
            page.navigate("https://www.example.com");

            assertThat(page.title()).isEqualTo("Example Domain");
            //page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("example.png")));

            browser.close();
        }
    }
}
