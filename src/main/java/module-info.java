module by.radeflex.musiccatalog {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires jaudiotagger;
    requires jave.core;

    opens by.radeflex.musiccatalog to javafx.fxml;
    exports by.radeflex.musiccatalog;
    exports by.radeflex.musiccatalog.ui.controller;
    opens by.radeflex.musiccatalog.ui.controller to javafx.fxml;
    exports by.radeflex.musiccatalog.ui.entity;
    opens by.radeflex.musiccatalog.ui.entity to javafx.fxml;
    exports by.radeflex.musiccatalog.ui.window;
    opens by.radeflex.musiccatalog.ui.window to javafx.fxml;
}