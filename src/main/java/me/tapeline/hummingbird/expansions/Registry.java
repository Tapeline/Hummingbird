package me.tapeline.hummingbird.expansions;

import java.util.ArrayList;
import java.util.List;
import me.tapeline.hummingbird.App;
import me.tapeline.hummingbird.expansions.autocompletion.AbstractCodeAutocompleter;
import me.tapeline.hummingbird.expansions.customactions.AbstractPluginAction;
import me.tapeline.hummingbird.expansions.customactions.AbstractPluginShortcut;
import me.tapeline.hummingbird.expansions.filetype.AbstractFileType;
import me.tapeline.hummingbird.expansions.highlighter.AbstractSyntaxHighlighter;
import me.tapeline.hummingbird.expansions.projecttype.AbstractProjectGenerator;
import me.tapeline.hummingbird.expansions.themes.AbstractTheme;

public class Registry {
    public static AbstractTheme currentTheme;
    public static List<AbstractTheme> themes = new ArrayList();
    public static List<AbstractFileType> fileTypes = new ArrayList();
    public static List<me.tapeline.hummingbird.expansions.syntaxchecker.AbstractSyntaxChecker> syntaxCheckers = new ArrayList();
    public static List<AbstractSyntaxHighlighter> syntaxHighlighters = new ArrayList();
    public static List<AbstractProjectGenerator> projectGenerators = new ArrayList();
    public static List<AbstractPluginAction> pluginActions = new ArrayList();
    public static List<AbstractPluginShortcut> pluginShortcuts = new ArrayList();
    public static List<AbstractCodeAutocompleter> codeAutocompleters = new ArrayList<>();
    public static List<Plugin> plugins = new ArrayList();

    public static void registerPlugin(Plugin pl) {
        plugins.add(pl);
        if (pl.providedThemes() != null) {
            themes.addAll(pl.providedThemes());
        }

        if (pl.providedFileTypes() != null) {
            fileTypes.addAll(pl.providedFileTypes());
        }

        if (pl.providedSyntaxCheckers() != null) {
            syntaxCheckers.addAll(pl.providedSyntaxCheckers());
        }

        if (pl.providedSyntaxes() != null) {
            syntaxHighlighters.addAll(pl.providedSyntaxes());
        }

        if (pl.providedProjectGenerators() != null) {
            projectGenerators.addAll(pl.providedProjectGenerators());
        }

        if (pl.providedActions() != null) {
            pluginActions.addAll(pl.providedActions());
        }

        if (pl.providedShortcuts() != null) {
            pluginShortcuts.addAll(pl.providedShortcuts());
        }

        if (pl.providedAutocompleters() != null) {
            codeAutocompleters.addAll(pl.providedAutocompleters());
        }
    }


    public static AbstractTheme getCurrentTheme() {
        for (AbstractTheme t : themes)
            if (t.name().equals(App.cfg.theme))
                return t;
        return themes.get(0);
    }

    public static void applyTheme(AbstractTheme a) {
        currentTheme = a;
        a.onApply();
    }

}
