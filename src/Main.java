import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InterfaceUsuario interfaceUsuario = new InterfaceUsuario(scanner);
        interfaceUsuario.iniciar();
        scanner.close();
    }
}