package me.tapeline.hummingbird.ui.menus;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import me.tapeline.hummingbird.filesystem.FS;
import me.tapeline.hummingbird.view.editor.EditorController;

public class FileDeleteMenuItem extends MenuItem {

    public File file;

    public FileDeleteMenuItem(EditorController controller, File file) {
        super("Delete");
        this.file = file;
        this.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FS.delete(file);
                controller.updateFileTree();
            }
        });
    }

}
