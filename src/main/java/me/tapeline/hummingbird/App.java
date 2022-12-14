package me.tapeline.hummingbird;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.imageio.ImageIO;
import me.tapeline.hummingbird.configuration.Configuration;
import me.tapeline.hummingbird.configuration.Configure;
import me.tapeline.hummingbird.core.CorePlugin;
import me.tapeline.hummingbird.expansions.Registry;
import me.tapeline.hummingbird.resources.IconLoader;
import me.tapeline.hummingbird.resources.StylesheetManager;
import me.tapeline.hummingbird.splash.SplashScreen;
import me.tapeline.hummingbird.utils.AppExitWatcher;
import me.tapeline.hummingbird.view.common.Dialogs;
import me.tapeline.hummingbird.view.common.HMStage;
import me.tapeline.hummingbird.view.welcome.WelcomeStage;
import me.tapeline.hummingbirdplugin.quail.QuailPlugin;

public class App extends Application {

    public static App instance;
    public static Configuration cfg;
    public static String configPath = "config.yml";
    public List<Window> openedWindows = new ArrayList();
    public boolean wakelock = false;
    public static String stylesheet;
    public static String stylesheetFile = System.getProperty("user.dir") + "/root.css";

    static {
        try {
            stylesheet = new File(System.getProperty("user.dir") + "/root.css").toURL().toExternalForm();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public void acquireWakelock() {
        this.wakelock = true;
    }

    public void releaseWakelock() {
        this.wakelock = false;
    }

    public void start(Stage stage) throws Exception {
        instance = this;
        String iconsFolder = "images";
        String fontsFolder = "fonts";
        BufferedImage splashImage = ImageIO.read(App.class.getResource(iconsFolder + "/splash.png"));
        SplashScreen splashScreen = new SplashScreen(640, 365, splashImage);
        splashScreen.setVisible(true);
        Locale.setDefault(Locale.ENGLISH);
        cfg = (Configuration) Configure.configureYaml(configPath, Configuration.class);
        Exception iconException = IconLoader.loadIcons(iconsFolder);
        if (iconException != null) {
            throw iconException;
        } else {
            System.out.println("Startup");
            Exception fontException = null;//FontLoader.loadFonts(fontsFolder);
            if (fontException != null) {
                throw fontException;
            } else {
                Registry.registerPlugin(new CorePlugin());
                Registry.registerPlugin(new QuailPlugin());
                Registry.applyTheme(Registry.getCurrentTheme());
                Registry.fileTypes.sort((o1, o2) -> Integer.compare(o2.weight(), o1.weight()));

                try {
                    Thread.sleep(650L);
                } catch (InterruptedException ignored) {}

                splashScreen.setVisible(false);
                splashScreen.dispose();

                StylesheetManager.buildStylesheet(stylesheetFile);

                Dialogs.warn("Warning", "Developer preview", "Do not use in production");

                ///SettingsStage settings = new SettingsStage(this);
                /*EditorStage editor = new EditorStage(this, new Project(new File("testProj")));
                this.openedWindows.add(editor);*/
                WelcomeStage welcomeStage = new WelcomeStage(this);
                openedWindows.add(welcomeStage);
                System.out.println("Startup finished");
                AppExitWatcher watcher = new AppExitWatcher(this);
            }
        }
    }

    public Scene loadScene(HMStage stage, String id, Object controller) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view/" + id + ".fxml"));
            fxmlLoader.setController(controller);
            Scene scene = new Scene(fxmlLoader.load());
            applyCurrentThemeToScene(scene);

            return scene;
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static void applyCurrentThemeToScene(Scene scene) {
        StylesheetManager.buildStylesheet(stylesheetFile);
        scene.getStylesheets().clear();
        scene.getStylesheets().add(stylesheet);
        /*if (Registry.getCurrentTheme() != null) {
            String stylesheetPath = Registry.getCurrentTheme().cssPath();
            if (stylesheetPath != null && stylesheetPath.startsWith("$")) {
                scene.getStylesheets().add(App.class.getResource(stylesheetPath.substring(1))
                        .toExternalForm());
            } else if (stylesheetPath != null) {
                scene.getStylesheets().add(stylesheetPath);
            }
        }*/
    }

    public static void applyCurrentThemeToDialog(DialogPane scene) {
        /*if (Registry.getCurrentTheme() != null) {
            String stylesheetPath = Registry.getCurrentTheme().cssPath();
            if (stylesheetPath != null && stylesheetPath.startsWith("$")) {
                scene.getStylesheets().add(App.class.getResource(stylesheetPath.substring(1))
                        .toExternalForm());
            } else if (stylesheetPath != null) {
                scene.getStylesheets().add(stylesheetPath);
            }
        }*/
        StylesheetManager.buildStylesheet(stylesheetFile);
        scene.getStylesheets().clear();
        scene.getStylesheets().add(stylesheet);
    }

    public static void main(String[] args) {
        launch();
    }
}
