import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EditPartnerForm {
    private final static MainFormDAO dao = new MainFormDAO();
    public static final String EMAIL_PATTERN = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";


    public static void showEditDialog(final Integer partner) {

        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Добавить партнера");

        ComboBox<String> typeComboBox = new ComboBox<>();
        var partnerTypes = dao.getAllPartnerTypes();
        typeComboBox.getItems().addAll(partnerTypes);
        typeComboBox.setValue(partnerTypes.get(0));

        TextField nameField = new TextField();
        Spinner<Integer> ratingSpinner = new Spinner<>(0, Integer.MAX_VALUE, 0);
        TextField addressField = new TextField();
        TextField innField = new TextField();
        TextField directorField = new TextField();
        TextField phoneField = new TextField();
        TextField emailField = new TextField();


        phoneField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[\\d\\s]*")) {
                phoneField.setText(oldValue);
            }
        });

        innField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !newValue.matches("\\d*")) {
                innField.setText(oldValue);
            }
        });


        emailField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(EMAIL_PATTERN)) {
                emailField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            } else {
                emailField.setStyle("");
            }
        });

        Button saveButton = new Button("Сохранить");
        saveButton.setOnAction(e -> {
            if (!emailField.getText().matches(EMAIL_PATTERN)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Некорректные данные");
                alert.setHeaderText("Неверный формат поля email");
                alert.setContentText("Поле email должно содержать корерктный адрес электронной почты, например, test@test.ru ");


                alert.showAndWait();
            } else {
                if (nameField.getText().isBlank() ||
                    addressField.getText().isBlank() ||
                    directorField.getText().isBlank() ||
                    phoneField.getText().isBlank() ||
                    emailField.getText().isBlank() ||
                    innField.getText().isBlank()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Некорректные данные");
                    alert.setHeaderText("Не все поля введены на форме");
                    alert.setContentText("Все поля на этой форме являются обязательными");
                    alert.showAndWait();
                } else {
                    MainFormDTO dto = new MainFormDTO(partner == null ? 0 : partner,
                            typeComboBox.getValue(),
                            nameField.getText(),
                            directorField.getText(),
                            addressField.getText(),
                            emailField.getText(),
                            phoneField.getText(),
                            innField.getText(),
                            ratingSpinner.getValue(),
                            0);
                    if (partner == null) {
                        dao.createNew(dto);
                    } else {
                        dao.updateExisting(dto);
                    }
                    dialog.close();
                }
            }
        });

        Button closeButton = new Button("Закрыть");
        closeButton.setOnAction(e -> {

            dialog.close();
        });

        if (partner != null) {
            var partnerRecord = dao.getById(partner);
            typeComboBox.setValue(partnerRecord.getType());
            nameField.setText(partnerRecord.getName());
            ratingSpinner.getValueFactory().setValue(partnerRecord.getRating());
            addressField.setText(partnerRecord.getAddress());
            directorField.setText(partnerRecord.getDirector());
            phoneField.setText(partnerRecord.getPhone());
            emailField.setText(partnerRecord.getEmail());
            innField.setText(partnerRecord.getInn());
        }

        GridPane grid = new GridPane();

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        grid.add(new Label("Тип:"), 0, 0);
        grid.add(typeComboBox, 1, 0);
        grid.add(new Label("Название:"), 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(new Label("Рейтинг:"), 0, 2);
        grid.add(ratingSpinner, 1, 2);
        grid.add(new Label("Адрес:"), 0, 3);
        grid.add(addressField, 1, 3);
        grid.add(new Label("Директор:"), 0, 4);
        grid.add(directorField, 1, 4);
        grid.add(new Label("Телефон:"), 0, 5);
        grid.add(phoneField, 1, 5);
        grid.add(new Label("Email:"), 0, 6);
        grid.add(emailField, 1, 6);
        grid.add(new Label("ИНН:"), 0, 7);
        grid.add(innField, 1, 7);
        grid.add(saveButton, 0, 8);
        grid.add(closeButton, 1, 8);

        Scene dialogScene = new Scene(grid, 400, 350);

        dialog.setScene(dialogScene);
        dialog.showAndWait();
    }

}