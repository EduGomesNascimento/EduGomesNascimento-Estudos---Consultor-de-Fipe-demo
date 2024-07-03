package Consultor.Fipe.com.Main;

import Consultor.Fipe.com.Models.MarcaDados;
import Consultor.Fipe.com.Services.APImanager;
import Consultor.Fipe.com.Services.ConverteDados;

import java.io.*;
import java.util.*;


public class Principal {
//DECLARAÇÕES DE VARIAVEIS E MÉTODOS UTILIZADOS
    private Scanner leitura = new Scanner(System.in);
    private APImanager manager = new APImanager();
    ConverteDados conversor = new ConverteDados();

    private String endereco = "https://fipe.parallelum.com.br/api/v2/";
//FIM DECLARAÇÕES
    public void exibeMenu() throws IOException, InterruptedException {
        //TENTA EXECUTAR O CODIGO
        try {
            System.out.println("SEJA BEM VINDO! \n");
            System.out.println("PARA VER AS MARCAS DE CARRO DIGITE 1\n");
            System.out.println("PARA VER AS MARCAS DE MOTOS DIGITE 2\n");
            System.out.println("PARA VER AS MARCAS DE CAMINHOES DIGITE 3\n");

            //TENTA RECEBER O TIPO DO VEÍCULO

            String digitoMarcasComCodigo = leitura.nextLine();
            String tipoVeiculo = "";
            try {
                tipoVeiculo = "";
                if (digitoMarcasComCodigo.contains("1")) {
                    tipoVeiculo = "cars/brands/";
                } else if (digitoMarcasComCodigo.contains("2")) {
                    tipoVeiculo = "motorcycles/brands/";
                } else if (digitoMarcasComCodigo.contains("3")) {
                    tipoVeiculo = "trucks/brands/";
                } else {
                    System.out.println("opção não existente");
                }
            } catch (Exception e) {
                System.out.println("!ERRO!");
            } finally {
                //USA O TIPO DE VEICULO SELECIONADO PARA PESQUISAR COM A API
                String enderecoComTipo = endereco + tipoVeiculo;
                String json = manager.obtemDados(enderecoComTipo);
                System.out.println(json);
                System.out.println(enderecoComTipo);

                //CONVERTE JSON EM UMA LISTA

                var marcas = conversor.obterLista(json, MarcaDados.class);
                marcas.stream().sorted(Comparator.comparing(MarcaDados::codMarca));

                //CONVERTE A LISTA EM STRING

                String marcasString = marcas.toString();
                System.out.println(marcasString
                        .replace("MarcaDados[", " ")
                        .replace("]", "")
                        .replace(",", " ")
                        .replace("   ", "\n")
                        .replace("codMarca=", "Codigo da marca: ")
                        .replace("nomeMarca=", "Nome da marca: "));

                System.out.print("\n\nDigite o codigo da marca desejada: ");
                String digitoCodigo;
                digitoCodigo = leitura.nextLine();

                //USA O CÓDIGO DA MARCA PARA PESQUISAR COM A API

                String enderecoComCodigo = enderecoComTipo + digitoCodigo + "/models/";
                json = manager.obtemDados(enderecoComCodigo);
                System.out.println(json
                        .replace("{\"code\":", "código: ")
                        .replace("\"name\":", "nome: ")
                        .replace("}", "\n")
                        .replace("[", "")
                        .replace("]", "")
                        .replace("\"\n,", "\n")
                        .replace("\"", "")
                        .replace(",", "  "));

                //USA O CÓDIGO DO MODELO PARA PESQUISAR COM A API

                System.out.print("\n\nDigite o codigo do modelo desejado: ");
                String codigoModeloDesejado = leitura.nextLine();
                String enderecoComCodigoModelo = enderecoComCodigo + codigoModeloDesejado + "/years/";
                json = manager.obtemDados(enderecoComCodigoModelo);
                System.out.println(json
                        .replace("{\"code\":", "código: ")
                        .replace("\"name\":", "nome: ")
                        .replace("}", "\n")
                        .replace("[", "")
                        .replace("]", "")
                        .replace("\"\n,", "\n")
                        .replace("\"", "")
                        .replace(",", "  "));

                //USA O CÓDIGO DO MODELO COM ANO PARA PESQUISAR COM A API

                System.out.print("\n\nDigite o codigo do modelo com o ano desejado: ");

                String digitoCodigoDoModeloComAno;
                digitoCodigoDoModeloComAno = leitura.nextLine();

                String enderecoComCodigoComAno = enderecoComCodigoModelo + digitoCodigoDoModeloComAno + "/";
                json = manager.obtemDados(enderecoComCodigoComAno);
                String resultadoAno = json
                        .replace("{\"code\":", "código: ")
                        .replace("\"name\":", "nome: ")
                        .replace("}", "\n")
                        .replace("[", "")
                        .replace("]", "")
                        .replace("\"\n,", "\n")
                        .replace("\"", "");
                System.out.println(resultadoAno
                        .replace(",", " - ")
                        .replace("codeFipe", "Codigo Fipe")
                        .replace("price", "Preco")
                        .replace("vehicleType", "Tipo do veiculo")
                        .replace("brand", "marca").replace("model", "Modelo")
                        .replace("ModeloYear", "Ano do modelo")
                        .replace("fuel", "Combustivel")
                        .replace("referenceMonth", "Mês de referencia")
                        .replace("CombustivelAcronym", "inicial do combustivel")
                        .replace("{", "")
                        .replace(" - 00", ",00"));


            }
        }
        //CASO O CÓDIGO NÃO FUNCIONE COMO O ESPERADO, LANÇA UMA RuntimeException
        catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}






