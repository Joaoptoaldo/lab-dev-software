module com.joaoptoaldo.projetofxmysql {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    // Abre o pacote principal
    opens com.joaoptoaldo.projetofxmysql to javafx.fxml;
    exports com.joaoptoaldo.projetofxmysql;

    opens com.joaoptoaldo.projetofxmysql.controller to javafx.fxml;
    opens com.joaoptoaldo.projetofxmysql.model to javafx.base;
}