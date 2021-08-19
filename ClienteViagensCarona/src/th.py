from threading import Thread
from sseclient import SSEClient

def mostraNotificacao(nome, contato, tipo):
    tipoUsuario = []

    if (tipo == 0):
        tipoUsuario.append("PASSAGEIRO")
        tipoUsuario.append("MOTORISTA")
    else:
        tipoUsuario.append("MOTORISTA")
        tipoUsuario.append("PASSAGEIRO")

    print(
        "##################################################################################################################")
    print(
        "------------------------- AÇÕES ----------------------- # ---------------------- NOTIFICAÇÕES --------------------")
    print("\t\t\t\t\t\t\t#")
    print("\t\t\t\t\t\t\t#\t\tNOTIFICACAO PARA O " + tipoUsuario[1])
    print("\t\t\t\t\t\t\t#")
    print("\t\t\t\t\t\t\t#\tOLA " + tipoUsuario[1] + " SEGUEM OS DADOS DO " + tipoUsuario[0] + ":")
    print("\t\t\t\t\t\t\t#")
    print("\t\t\t\t\t\t\t# NOME:\t\t " + nome)
    print("\t\t\t\t\t\t\t#")
    print("\t\t\t\t\t\t\t# CONTATO:\t " + contato)
    print("\t\t\t\t\t\t\t#")
    print("\t\t\t\t\t\t\t#\t\t(: tenha uma ótima viagem :)")

class Th(Thread):

    def __init__(self, nomeCliente):
        Thread.__init__(self)
        self.nomeCliente = nomeCliente

    def run(self):
            endereco = "http://localhost:8086/ServidorViagensCarona/SseResource"
            headers = {'Accept': 'text/event-stream'}
            payload = {'nomeCliente': self.nomeCliente}

            cliente = SSEClient(endereco, headers=headers, params=payload)

            for evento in cliente:
                mensagem = evento.data
                if mensagem != "":
                    nome, contato, tipo = mensagem.upper().split('/')
                    mostraNotificacao(nome, contato, tipo)