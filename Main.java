import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Database.createNewDatabase();
        Database.createNewTable();
        
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Buscar libros");
            System.out.println("2. Agregar libro a favoritos");
            System.out.println("3. Mostrar todos los libros guardados");
            System.out.println("4. Buscar por autor");
            System.out.println("5. Salir");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Ingrese el término de búsqueda:");
                    String query = scanner.nextLine();
                    try {
                        String response = BookAPIClient.searchBooks(query);
                        BookParser.parseBooks(response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.println("Ingrese el título del libro:");
                    String title = scanner.nextLine();
                    System.out.println("Ingrese los autores del libro:");
                    String authors = scanner.nextLine();
                    Database.insertBook(title, authors);
                    break;
                case 3:
                    Database.selectAllBooks();
                    break;
                case 4:
                    System.out.println("Ingrese el nombre del autor:");
                    String author = scanner.nextLine();
                    Database.searchBooksByAuthor(author);
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
