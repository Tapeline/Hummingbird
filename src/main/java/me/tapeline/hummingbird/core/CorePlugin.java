package me.tapeline.hummingbird.core;

import me.tapeline.hummingbird.core.filetypes.GeneralFile;
import me.tapeline.hummingbird.core.filetypes.GeneralFolder;
import me.tapeline.hummingbird.core.themes.DarculaTheme;
import me.tapeline.hummingbird.core.themes.LightTheme;
import me.tapeline.hummingbird.expansions.Plugin;
import me.tapeline.hummingbird.expansions.Registry;
import me.tapeline.hummingbird.expansions.customactions.AbstractPluginAction;
import me.tapeline.hummingbird.expansions.customactions.AbstractPluginShortcut;
import me.tapeline.hummingbird.expansions.filetype.AbstractFileType;
import me.tapeline.hummingbird.expansions.highlighter.AbstractSyntaxHighlighter;
import me.tapeline.hummingbird.expansions.projecttype.AbstractProjectType;
import me.tapeline.hummingbird.expansions.themes.AbstractTheme;

import java.util.Arrays;
import java.util.List;

public class CorePlugin extends Plugin {

    public AbstractTheme defaultTheme;

    @Override
    public String name() {
        return "Hummingbird Core";
    }

    @Override
    public String author() {
        return "Tapeline";
    }

    @Override
    public float version() {
        return 0;
    }

    @Override
    public List<AbstractProjectType> providedProjectTypes() {
        return null;
    }

    @Override
    public List<AbstractSyntaxHighlighter> providedSyntaxes() {
        return null;
    }

    @Override
    public List<me.tapeline.hummingbird.expansions.syntaxchecker.AbstractSyntaxChecker> providedSyntaxCheckers() {
        return null;
    }

    @Override
    public List<AbstractFileType> providedFileTypes() {
        return Arrays.asList(
                new GeneralFile(),
                new GeneralFolder()
        );
    }

    @Override
    public List<AbstractTheme> providedThemes() {
        defaultTheme = new DarculaTheme();
        return Arrays.asList(
                defaultTheme,
                new LightTheme()
        );
    }

    @Override
    public List<AbstractPluginAction> providedActions() {
        return null;
    }

    @Override
    public List<AbstractPluginShortcut> providedShortcuts() {
        return null;
    }

    @Override
    public void onEnable() {
        super.onEnable();
        Registry.applyTheme(defaultTheme);
    }
}
