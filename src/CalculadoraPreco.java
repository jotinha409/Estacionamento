import java.time.Duration;
import java.time.LocalDateTime;

public class CalculadoraPreco {

    public static double calcularPreco(Carro carro, LocalDateTime horaSaida) {
        LocalDateTime horaEntrada = carro.getHoraEntrada();
        long horas = Duration.between(horaEntrada, horaSaida).toHours();
        if (horas == 0) horas = 1;

        double valorHora;

        switch (carro.getTipo().toLowerCase()) {
            case "pequeno":
                valorHora = 16.00;
                break;
            case "grande":
                valorHora = 25.00;
                break;
            case "moto":
                valorHora = 8.00;
                break;
            default:
                throw new IllegalArgumentException("Tipo de carro desconhecido: " + carro.getTipo());
        }

        return valorHora * horas;
    }
}
