import interfaces.Document;
import utils.DocumentFactory;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        try {
            Document document = DocumentFactory.createDocument("text");

            document.open();
            document.save();
            document.close();

            document = DocumentFactory.createDocument("presentacion");
            document.open();
            document.save();
            document.close();

            document = DocumentFactory.createDocument("calculo");
            document.open();
            document.save();
            document.close();

        }catch ( IllegalArgumentException e) {
            System.out.println(e.getMessage());

        }

    }
}