package br.com.utfpr.viagenscarona;

import java.util.ArrayList;

import br.com.utfpr.viagenscarona.model.Usuario;
import br.com.utfpr.viagenscarona.model.Carona;

public class ServidorImpl{
	
	private ArrayList<Usuario> usuarios;                // guarda os usuarios
    private ArrayList<Carona> caronas;                  // guarda as caronas
    private int idCarona;                               // id da carona adicionada
    
    public ServidorImpl(){
        usuarios = new ArrayList<Usuario>();
        caronas = new ArrayList<Carona>();
        idCarona = 1;
    }
    
    public int registrarInteresse(){

        /*Usuario usuario = new Usuario();
        usuario = validacaoUsuarioExistente(carona);

        if(Objects.isNull(usuario.getNome()))
            return 0;

        String msg = criaMensagem(carona);
        if(!validaAssinatura(assinatura, msg, usuario))
            return 0;

        Carona novaCarona = new Carona();
        preencheCarona(novaCarona, carona, idCarona);

        boolean registro = caronas.add(novaCarona);
        if(!registro)
            return 0;

        idCarona++;
        
        ArrayList<Carona> caronasNotificadas = new ArrayList<Carona>();
        notificaClientes(carona, caronasNotificadas, novaCarona, usuario);
        removeCaronasNotificadasCheias(carona, caronasNotificadas);

        return novaCarona.getId();*/
    	return 1;

    }
	
}