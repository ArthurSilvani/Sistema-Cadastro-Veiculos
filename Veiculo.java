import java.time.LocalDate;

public class Veiculo {

    private String marca;
    private String modelo;
    private int ano;
    private String placa;

    public Veiculo() {}

    public boolean AnoValido(int ano) {
        int anoAtual = LocalDate.now().getYear();
        return ano >= 1900 && ano <= (anoAtual + 1);
    }

    public boolean PlacaValida(String placa) {
        if (placa == null) return false;
        String regex = "^([A-Z]{3}-?[0-9]{4}|[A-Z]{3}[0-9][A-Z][0-9]{2})$";
        return placa.trim().toUpperCase().matches(regex);
    }

    // Getters e Setters
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa.trim().toUpperCase();
    }
}