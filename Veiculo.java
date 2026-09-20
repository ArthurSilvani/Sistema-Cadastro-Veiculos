import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.TreeUI;

List<Veiculo> veiculos = new ArrayList<>();
// Array do tipo vetor - uma lista que vai receber string <String>

public class Veiculo {

    private String marca;
    private String modelo;
    private int ano;
    private String placa;

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
        if (ano < 1900 || ano > LocalDate.now().getYear() + 1) {
            // throw serve para você interromper a execução de um método na hora e avisar
            // quem chamou ele que algo deu errado
            // — em vez de deixar o programa continuar rodando com um estado inválido.
            throw new IllegalArgumentException(
                    "Ano inválido. Informe um valor entre 1900 e " + (LocalDate.now().getYear() + 1) + ".");
        }
        this.ano = ano;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        String placaNormalizada = placa.trim().toUpperCase();
        if (!validaPlaca(placaNormalizada)) {
            throw new IllegalArgumentException(
                    "Placa inválida. Use o formato ABC1234, ABC-1234 (antigo) ou ABC1D23 (Mercosul).");
        }
        this.placa = placaNormalizada;
    }

    private boolean validaPlaca(String placa) {
        String regex = "^([A-Z]{3}-?[0-9]{4}|[A-Z]{3}[0-9][A-Z][0-9]{2})$";
        return placa.matches(regex);
    }

    public boolean placaJaCadastrada(String placa) {
    for (Veiculo veiculo : veiculos) {
        if (veiculo.getPlaca().equals(placa)) {
            return true;
        }
    }
    return false;
}
}

void main() {
    String menu = """

            ====== Cadastro de Veiculo POO ======
            1- Cadastrar veiculo
            2- Listar veiculo
            3- Consultar veiculo
            0- Sair
            """;
    int opcao;
    do {
        IO.println(menu);

        opcao = Input.readInt("Digite uma opção acima: ");

        switch (opcao) {
            case 1 -> {
                cadastrarVeiculo();
            }
            case 2 -> {
                listarVeiculos();
            }
            case 3 -> {
                ConsultarVeiculo();
            }
            case 0 -> IO.println("Até logo!!");
            default -> IO.println("Opção invalida");
        }
    } while (opcao != 0);
}

void cadastrarVeiculo() {

    Veiculo novoVeiculo = new Veiculo();
    novoVeiculo.setMarca(IO.readln("Digite a marca do Veículo: "));
    novoVeiculo.setModelo(IO.readln("Digite a modelo do Veículo: "));

    boolean anoValido = false;
    while (!anoValido) {
        try {
            novoVeiculo.setAno(Integer.parseInt(IO.readln("Digite o ano do seu veículo: ")));
            anoValido = true;
        } catch (IllegalArgumentException e) {
            IO.println(e.getMessage());
        }
    }
    boolean placaValida = false;
    while (!placaValida) {
        try {
            novoVeiculo.setPlaca(IO.readln("Digite a placa do veiculo: "));
            if (placaJaCadastrada(novoVeiculo.getPlaca())) {
                IO.println("A placa já está cadastrada em nosso sistema!");
            } else {
                placaValida = true;
            }
        } catch (IllegalArgumentException e) {
            IO.println(e.getMessage());
        }
    }

    veiculos.add(novoVeiculo);
    IO.println("Veículo cadastrado com sucesso!");
}

boolean placaJaCadastrada(String placa) {
    for (Veiculo veiculo : veiculos) {
        if (veiculo.getPlaca().equals(placa)) {
            return true;
        }
    }
    return false;
}

void listarVeiculos() {

    IO.println("---------VEICULOS CADASTRADOS--------");

    if (veiculos.isEmpty()) {
        IO.println("Não há veiculos cadastrados");
    }
    // USANDO FOR-EACH
    int contador = 1;
    for (Veiculo veiculo : veiculos) {

        IO.println("\n--- Veículo " + contador + " ---");
        IO.println(veiculo.getMarca());
        IO.println(veiculo.getModelo());
        IO.println(veiculo.getAno());
        IO.println(veiculo.getPlaca());
        contador++;
    }
}

void ConsultarVeiculo() {
    listarVeiculos();
    String placa = IO.readln("Informe a placa do seu Veículo: ").trim().toUpperCase();

    boolean encontrado = false;

    for (Veiculo veiculo : veiculos) {
        if (veiculo.getPlaca().equals(placa)) {
            IO.println("\n--- Veículo encontrado ---");
            IO.println(veiculo.getMarca());
            IO.println(veiculo.getModelo());
            IO.println(veiculo.getAno());
            IO.println(veiculo.getPlaca());
            encontrado = true;
            break;
        }
    }
    if (!encontrado) {
        IO.println("Nenhum veículo encontrado com essa placa.");
    }
}
