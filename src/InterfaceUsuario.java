import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class InterfaceUsuario {
    private final Scanner scanner;
    private final Estacionamento estacionamento;
    private Usuario usuario;

    private final SistemaLogin sistemaLogin = new SistemaLogin();

    public InterfaceUsuario(Scanner scanner) {
        this.scanner = scanner;
        this.estacionamento = new Estacionamento(10);
    }

    public void iniciar() {
        while (true) {
            System.out.println("=== Sistema de Login ===");
            boolean autenticado = false;
            while (!autenticado) {
                System.out.println("1. Cadastrar");
                System.out.println("2. Login");
                System.out.print("Escolha: ");
                String opcao = scanner.nextLine();

                switch (opcao) {
                    case "1":
                        autenticado = cadastrarUsuario();
                        break;
                    case "2":
                        autenticado = loginUsuario();
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            }
            menuPrincipal();
        }
    }

    private boolean cadastrarUsuario() {
        System.out.print("Digite um nome de usuário: ");
        String nome = scanner.nextLine();
        System.out.print("Digite uma senha: ");
        String senha = scanner.nextLine();

        if (sistemaLogin.cadastrarUsuario(nome, senha)) {
            System.out.println("Usuário cadastrado com sucesso!");
            this.usuario = new Usuario(nome);
            return true;
        } else {
            System.out.println("Usuário já existe!");
            return false;
        }
    }

    private boolean loginUsuario() {
        System.out.print("Digite seu nome de usuário: ");
        String nome = scanner.nextLine();
        System.out.print("Digite sua senha: ");
        String senha = scanner.nextLine();

        if (sistemaLogin.autenticar(nome, senha)) {
            System.out.println("Login bem-sucedido!");
            this.usuario = new Usuario(nome);
            return true;
        } else {
            System.out.println("Usuário ou senha incorretos.");
            return false;
        }
    }
    private void menuPrincipal() {
        boolean rodando = true;
        while (rodando) {
            mostrarMenu();
            int opcao = lerOpcao();
            switch (opcao) {
                case 1:
                    adicionarCarro();
                    break;
                case 2:
                    removerCarro();
                    break;
                case 3:
                    estacionamento.mostrarVagasOcupadas();
                    break;
                case 4:
                    estacionamento.mostrarVagasLivres();
                    break;
                case 5:
                    estacionamento.totalVeiculos();
                    break;
                case 6:
                    System.out.println("Usuário " + usuario.getNome() + " fez logoff.");
                    this.usuario = null;
                    rodando = false; // Sai do menuPrincipal(), voltando para o menu de login
                    break;
                case 7:
                    editarCarro();
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }



    private void mostrarMenu() {
        System.out.println("\n--- Controle de vagas da Faculdade ---");
        System.out.println("===== MENU =====");
        System.out.println("\t1. Inserir carro");
        System.out.println("\t2. Remover carro");
        System.out.println("\t3. Mostrar vagas ocupadas");
        System.out.println("\t4. Mostrar vagas livres");
        System.out.println("\t5. Mostrar total de veículos");
        System.out.println("\t6. Logoff");
        System.out.println("\t7. Editar Carro");
    }


    private int lerOpcao() {
        while (true) {
            System.out.print("Escolha uma opção: ");
            String entrada = scanner.nextLine();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
            }
        }
    }

    private void adicionarCarro() {
        System.out.println(" ==== Adicionar carro ==== ");
        System.out.print("Placa: ");
        String placa = scanner.nextLine();
        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();
        System.out.print("Marca do Veículo: ");
        String marca = scanner.nextLine();
        System.out.print("Tipo (pequeno, grande, moto): ");
        String tipo = scanner.nextLine();

        estacionamento.mostrarVagasLivres();

        int vaga = lerNumeroVaga();

        Carro carro = new Carro(placa, modelo, marca, tipo);
        if (estacionamento.inserirCarro(carro, vaga, usuario)) {
            System.out.println("Carro adicionado com sucesso.");
            System.out.printf("Carro estacionado na vaga %d pelo usuário %s.\nHora de entrada: %s%n",
                    vaga, usuario.getNome(), carro.getHoraEntrada().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        }
    }

    private void removerCarro() {
        System.out.print("Placa do carro para remover: ");
        String placa = scanner.nextLine();
        if (estacionamento.removerCarro(placa, usuario)) {
            System.out.println("Carro removido com sucesso.");
        }
    }

    private int lerNumeroVaga() {
        while (true) {
            System.out.print("Número da vaga: ");
            String entrada = scanner.nextLine();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
            }
        }
    }

    private void editarCarro() {
        System.out.print("Digite a placa do carro que deseja editar: ");
        String placa = scanner.nextLine();

        System.out.print("Novo modelo: ");
        String modelo = scanner.nextLine();
        System.out.print("Nova marca: ");
        String marca = scanner.nextLine();
        System.out.print("Novo tipo (pequeno, grande, moto): ");
        String tipo = scanner.nextLine();

        if (estacionamento.editarCarro(placa, modelo, marca, tipo)) {
            System.out.println("Carro atualizado com sucesso!");
        } else {
            System.out.println("Falha ao atualizar o carro.");
        }
    }

}