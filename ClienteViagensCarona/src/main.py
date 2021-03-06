import requests

from src.model.carona import Carona
from src.th import Th

URL_API = "http://localhost:8086/ServidorViagensCarona/rest/service/"

def menu():
    print("##################################################################################################################");
    print("------------------------- AÇÕES ----------------------- # ---------------------- NOTIFICAÇÕES --------------------");
    print("\t\t\t\t\t\t\t#");
    print("\tOLA, SEJA BEM VINDO AO TALK TALK CAR\t\t#");
    print("\t\t\t\t\t\t\t#");
    print("\t   ESCOLHA UM DAS OPÇÕES ABAIXO\t\t\t#");
    print("\t\t\t\t\t\t\t#");
    print("1 - QUERO REGISTRAR UM INTERESSE EM UMA CARONA\t\t#");
    print("2 - QUERO CONSULTAR CARONAS DISPONIVEIS\t\t\t#");
    print("3 - QUERO CANCELAR INTERESSE EM UMA CARONA\t\t#");
    print("0 - SAIR\t\t\t\t\t\t#");
    print("\t\t\t\t\t\t\t#");
    opcao = input("DIGITE A OPÇÃO QUE DESEJA\n")
    return opcao

def pedirDadosCarona(carona):
    print("\n")
    print("############### REGISTRO DE INTERESSE ##################")
    print("\t\t\t\t\t\t\t#")
    print("\t\tDIGITE OS DADOS ABAIXO\t\t\t#\n")

    carona.setNome(input("NOME : \t\t\t\t\t\t\t#\n"))
    print("\t\t\t\t\t\t\t#\n")
    carona.setOrigem(input("ORIGEM : \t\t\t\t\t\t#\n"))
    print("\t\t\t\t\t\t\t#\n")
    carona.setDestino(input("DESTINO : \t\t\t\t\t\t#\n"))
    print("\t\t\t\t\t\t\t#\n")
    carona.setData(input("DATA DA VIAGEM : \t\t\t\t\t#\n"))
    print("\t\t\t\t\t\t\t#\n")
    carona.setContato(input("CONTATO (CELULAR): \t\t\t\t\t#\n"))
    print("\t\t\t\t\t\t\t#\n")
    interesse = input("\nESTÁ INTERESSADO EM UMA CARONA OU PASSAGEIRO?\t\t#\n")

    if (interesse == 'carona'):
        carona.setTipo(0);
    else:
        carona.setTipo(1);
        print("\t\t\t\t\t\t\t#\n");
        carona.setNumPassageiros(input("\nDIGITE O NUMERO DE PASSAGEIROS QUE DESEJA DAR CARONA\t#\n"));

def pedirDadosConsultaCarona(carona):
    print("\n")
    print("############### CONSULTA DE CARONAS ##################")
    print("\t\t\t\t\t\t\t#\n")
    print("\t\tDIGITE OS DADOS ABAIXO\t\t\t#\n")

    carona.setOrigem(input("Origem:"))
    print("\t\t\t\t\t\t\t#\n")
    carona.setDestino(input("Destino:"))
    print("\t\t\t\t\t\t\t#\n")
    carona.setData(input("Data:"))
    print("\t\t\t\t\t\t\t#\n")

def pedirDadosCancelamento(carona):
    print("\n")
    print("######## CANCELAMENTO DE REGISTRO DE INTERESSE ##########")
    print("\t\t\t\t\t\t\t#\n")
    print("\t\tDIGITE OS DADOS ABAIXO\t\t\t#\n")

    carona.setId(input("ID:"))
    print("\t\t\t\t\t\t\t#\n")

if __name__ == '__main__':

    controleThread = 0

    carona = Carona()
    opcao = menu()

    while(opcao != '0'):

        if (opcao == '1'): # registrar interesse

            endpoint = 'registrarInteresse';
            pedirDadosCarona(carona)  # solicita dados da carona

            nomeCliente = carona.getNome()
            if (controleThread == 0):
                th = Th(nomeCliente)
                th.start()
                controleThread = 1

            if (carona.getTipo() == 0):
                numPassageiros = 0
            else:
                numPassageiros = carona.getNumPassageiros()

            jsonOb = {'nome': carona.getNome(),
                      'origem': carona.getOrigem(),
                      'destino' : carona.getDestino(),
                      'data' : carona.getData(),
                      'contato' : carona.getContato(),
                      'tipo' : carona.getTipo(),
                      'numPassageiros' : numPassageiros}

            endereco = URL_API + endpoint # monta a url
            response = requests.post(endereco, json=jsonOb) # faz a requisição

            # verifica a resposta recebida
            if (response.text == "0"):
                print("OPERAÇÃO NÃO FOI REALIZADA")
            else:
                print("\nOPERAÇÃO FOI REALIZADA COM SUCESSO. SEU ID É " + response.text + ".")

        elif (opcao == '2'):  # consultar caronas

            endpoint = 'consultarCaronas';
            pedirDadosConsultaCarona(carona)

            endereco = URL_API + endpoint # monta a url
            payload = {'origem': carona.getOrigem(),
                       'destino': carona.getDestino(),
                       'data': carona.getData()}

            response = requests.get(endereco, params=payload)  # faz requisicao
            print(response.text)

        elif (opcao == '3'):  # cancelar registro de interesse

            endpoint = 'cancelarRegistroInteresse';
            pedirDadosCancelamento(carona)

            endereco = URL_API + endpoint # monta a url
            payload = {'id': carona.getId()}

            response = requests.delete(endereco, params=payload)  # faz requisicao
            print(response.text)

        opcao = menu()
