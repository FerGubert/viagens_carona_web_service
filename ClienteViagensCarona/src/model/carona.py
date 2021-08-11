import json

class Carona:

    def getId(self):
        return self.id

    def setId(self, id):
        self.id = id

    def getNome(self):
        return self.nome

    def setNome(self, nome):
        self.nome = nome

    def getOrigem(self):
        return self.origem

    def setOrigem(self, origem):
        self.origem = origem

    def getDestino(self):
        return self.destino

    def setDestino(self, destino):
        self.destino = destino

    def getData(self):
        return self.data

    def setData(self, data):
        self.data = data

    def getNumPassageiros(self):
        return self.numPassageiros

    def setNumPassageiros(self, numPassageiros):
        self.numPassageiros = numPassageiros

    def getTipo(self):
        return self.tipo

    def setTipo(self, tipo):
        self.tipo = tipo

    def getContato(self):
        return self.contato

    def setContato(self, contato):
        self.contato = contato

    def toJSON(self):
        return json.dumps(self, default=lambda o: o.__dict__,
                          sort_keys=True, indent=4)
