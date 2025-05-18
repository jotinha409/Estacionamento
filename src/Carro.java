import java.time.LocalDateTime;

public class Carro {
    private final String placa;
    private String modelo;
    private String marca;
    private String tipo;
    private final LocalDateTime horaEntrada;

    public Carro(String placa, String modelo, String marca, String tipo) {
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.tipo = tipo;
        this.horaEntrada = LocalDateTime.now();
    }

    // Construtor extra para manter horaEntrada original
    public Carro(String placa, String modelo, String marca, String tipo, LocalDateTime horaEntrada) {
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.tipo = tipo;
        this.horaEntrada = horaEntrada;
    }

    // getters e setters para modelo, marca e tipo
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPlaca() {
        return placa;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    @Override
    public String toString() {
        return String.format("%s %s (Placa: %s, Tipo: %s)", marca, modelo, placa, tipo);
    }
}
