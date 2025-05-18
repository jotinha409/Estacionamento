import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Estacionamento {
    private Carro[] vagas;

    public Estacionamento(int totalVagas) {
        this.vagas = new Carro[totalVagas];
    }

    public boolean inserirCarro(Carro carro, int vaga, Usuario usuario) {
        if (vaga < 0 || vaga >= vagas.length) {
            System.out.println("Erro: Vaga inválida.");
            return false;
        }
        if (vagas[vaga] != null) {
            System.out.println("Erro: Vaga já ocupada.");
            return false;
        }
        vagas[vaga] = carro;
        return true;
    }

    public boolean removerCarro(String placa, Usuario usuario) {
        for (int i = 0; i < vagas.length; i++) {
            Carro carro = vagas[i];
            if (carro != null && carro.getPlaca().equalsIgnoreCase(placa)) {
                LocalDateTime horaEntrada = carro.getHoraEntrada();
                LocalDateTime horaSaida = LocalDateTime.now();

                double total = CalculadoraPreco.calcularPreco(carro, horaSaida);

                vagas[i] = null;

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                long horas = java.time.Duration.between(horaEntrada, horaSaida).toHours();
                if (horas == 0) horas = 1;

                System.out.printf("Veículo removido da vaga %d pelo usuário %s.%n", i, usuario.getNome());
                System.out.printf("Hora de entrada: %s%n", horaEntrada.format(formatter));
                System.out.printf("Hora de saída: %s%n", horaSaida.format(formatter));
                System.out.printf("Tempo estacionado: %d hora(s)%n", horas);
                System.out.printf("Valor a pagar: R$ %.2f%n", total);

                return true;
            }
        }
        System.out.println("Veículo não encontrado.");
        return false;
    }
    public boolean editarCarro(String placa, String novoModelo, String novaMarca, String novoTipo) {
        for (int i = 0; i < vagas.length; i++) {
            Carro carro = vagas[i];
            if (carro != null && carro.getPlaca().equalsIgnoreCase(placa)) {
                LocalDateTime horaEntradaOriginal = carro.getHoraEntrada();
                Carro carroEditado = new Carro(placa, novoModelo, novaMarca, novoTipo, horaEntradaOriginal);
                vagas[i] = carroEditado;
                System.out.printf("Carro com placa %s atualizado com sucesso na vaga %d.\n", placa, i);
                return true;
            }
        }
        System.out.println("Carro não encontrado para edição.");
        return false;
    }

    public void mostrarVagasOcupadas() {
        boolean algumaOcupada = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        for (int i = 0; i < vagas.length; i++) {
            if (vagas[i] != null) {
                Carro carro = vagas[i];
                LocalDateTime horaEntrada = carro.getHoraEntrada();
                System.out.println("Vaga " + i + ": " + carro);
                System.out.printf("Hora de entrada: %s%n", horaEntrada.format(formatter));
                algumaOcupada = true;
            }
        }
        if (!algumaOcupada) {
            System.out.println("Nenhuma vaga ocupada no momento.");
        }
    }

    public void mostrarVagasLivres() {
        System.out.print("Vagas livres: ");
        boolean temLivre = false;
        for (int i = 0; i < vagas.length; i++) {
            if (vagas[i] == null) {
                System.out.print(i + " ");
                temLivre = true;
            }
        }
        if (!temLivre) {
            System.out.print("Nenhuma vaga livre.");
        }
        System.out.println();
    }

    public void totalVeiculos() {
        int total = 0;
        for (Carro carro : vagas) {
            if (carro != null) total++;
        }
        System.out.println("Total de veículos estacionados: " + total);
    }
}