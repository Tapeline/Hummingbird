package me.tapeline.hummingbird.view.editor;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import me.tapeline.hummingbird.App;
import me.tapeline.hummingbird.filesystem.project.Project;
import me.tapeline.hummingbird.ui.editor.tabs.AbstractEditorTab;
import me.tapeline.hummingbird.view.common.Dialogs;
import me.tapeline.hummingbird.view.common.HMStage;

import java.io.IOException;

public class EditorStage extends HMStage {

    public Project project;
    public EditorController controller = null;

    public EditorStage(App app, Project project) {
        super(StageStyle.DECORATED);
        this.project = project;
        this.controller = new EditorController(this);

        Scene scene = app.loadScene(this, "editor-view", controller);
        controller.initCustomItems(this);
        setOnCloseRequest((e) -> {
            for (Tab tab : controller.workspaceTabs.getTabs()) {
                if (tab instanceof AbstractEditorTab) {
                    try {
                        ((AbstractEditorTab) tab).save(this);
                    } catch (Exception ex) {
                        Dialogs.exception(ex);
                    }
                }
            }
        });

        controller.rebuildFileTree(project, project.root);

        setTitle("Editor - " + project.root.getName());
        setScene(scene);
        show();
    }

    public EditorController getController() {
        return controller;
    }
}
