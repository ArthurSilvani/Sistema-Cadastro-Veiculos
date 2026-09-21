import java.util.ArrayList;
import java.util.List;

public class Main {
    
    List<Veiculo> veiculos = new ArrayList<>();

    public void executar() {
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
                consultarVeiculo();
            }
            case 0 -> IO.println("Até logo!!");
            default -> IO.println("Opção invalida");
        }
    } while (opcao != 0);
}

    public void cadastrarVeiculo() {
        Veiculo novoVeiculo = new Veiculo();

        novoVeiculo.setMarca(IO.readln("Digite a marca do Veículo: "));
        novoVeiculo.setModelo(IO.readln("Digite o modelo do Veículo: "));

        // Validação de ANO
        boolean anoValido = false;
        while (!anoValido) {
            int ano = Input.readInt("Digite o ano do seu veículo: ");
            if (novoVeiculo.AnoValido(ano)) {
                novoVeiculo.setAno(ano);
                anoValido = true;
            } else {
                IO.println("Ano inválido. Informe um valor entre 1900 e o ano atual +1.");
            }
        }

        // Validação de PLACA
        boolean placaValida = false;
        while (!placaValida) {
            String placa = IO.readln("Digite a placa do veiculo: ").trim().toUpperCase();

            if (!novoVeiculo.PlacaValida(placa)) {
                IO.println("Placa inválida. Use o formato ABC1234, ABC-1234 (antigo) ou ABC1D23 (Mercosul).");
            } else if (placaJaCadastrada(placa)) {
                IO.println("A placa já está cadastrada em nosso sistema!");
            } else {
                novoVeiculo.setPlaca(placa);
                placaValida = true;
            }
        }

        veiculos.add(novoVeiculo);
        IO.println("Veículo cadastrado com sucesso!");
    }

    public boolean placaJaCadastrada(String placa) {
        //FOR EACH
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().equalsIgnoreCase(placa)) {
                return true;
            }
        }
        return false;
    }

    public void listarVeiculos() {
        IO.println("---------VEICULOS CADASTRADOS--------");

        if (veiculos.isEmpty()) {
            IO.println("Não há veiculos cadastrados");
            return;
        }

        int contador = 1;
        for (Veiculo veiculo : veiculos) {
            IO.println("\n--- Veículo " + contador + " ---");
            IO.println("Marca: " + veiculo.getMarca());
            IO.println("Modelo: " + veiculo.getModelo());
            IO.println("Ano: " + veiculo.getAno());
            IO.println("Placa: " + veiculo.getPlaca());
            contador++;
        }
    }

    public void consultarVeiculo() {
        if (veiculos.isEmpty()) {
            IO.println("Não há veículos cadastrados para consultar.");
            return;
        }

        String placa = IO.readln("Informe a placa do seu Veículo: ").trim().toUpperCase();
        boolean encontrado = false;

        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().equalsIgnoreCase(placa)) {
                IO.println("\n--- Veículo encontrado ---");
                IO.println("Marca: " + veiculo.getMarca());
                IO.println("Modelo: " + veiculo.getModelo());
                IO.println("Ano: " + veiculo.getAno());
                IO.println("Placa: " + veiculo.getPlaca());
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            IO.println("Nenhum veículo encontrado com essa placa.");
        }
    }
}