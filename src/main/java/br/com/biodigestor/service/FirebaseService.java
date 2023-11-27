package br.com.biodigestor.service;

import br.com.biodigestor.form.DadosForm;
import br.com.biodigestor.form.HomeForm;
import br.com.biodigestor.model.Dado;
import br.com.biodigestor.model.Device;
import br.com.biodigestor.model.Usuario;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.ExecutionException;

import static java.util.Objects.isNull;

@Service
public class FirebaseService {
    private final FirebaseServiceHelper firebaseServiceHelper;

    @Autowired
    public FirebaseService(FirebaseServiceHelper firebaseServiceHelper) {
        this.firebaseServiceHelper = firebaseServiceHelper;
    }

    public void salvarDados(DadosForm form) throws ExecutionException, InterruptedException {
        CollectionReference dados = firebaseServiceHelper.getDadosCollection();

        Dado dado = new Dado();
        dado.setData(DateUtils.formatDate(new Date(), "dd/MM/yyy"));
        dado.setFluxoEntrada(form.isFluxoEntrada());
        dado.setFluxoSaida(form.isFluxoSaida());
        dado.setVazaoEntrada(form.getVazaoEntrada());
        dado.setVazaoSaida(form.getVazaoSaida());
        dado.setPressao(form.getPressao());

        ApiFuture<DocumentReference> result = dados.add(dado);
        System.out.println("ID do Documento Criado: " + result.get());
    }

    public Usuario obterDadosUsuarioLogado(String username) {
        CollectionReference usersCollection = firebaseServiceHelper.getusuariosCollection();

        Query query = usersCollection.whereEqualTo("username", username);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        Usuario usuario = null;

        try {
            for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
                usuario = document.toObject(Usuario.class);
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        if (isNull(usuario)) {
            throw new RuntimeException("Não foi possível recuperar o e-mail.");
        }

        return usuario;
    }

    public void salvarFotoPerfil(Usuario usuario) throws ExecutionException, InterruptedException {
        DocumentReference usuarioDocument = firebaseServiceHelper.getUsuarioDocument(usuario.getUsername());

        ApiFuture<WriteResult> result = usuarioDocument.set(usuario);
        System.out.println("Resultado da atualização: " + result.get());
    }

    public void deletar(String deviceId) throws ExecutionException, InterruptedException {
        DocumentReference deviceRef = firebaseServiceHelper.getDeviceDocument(deviceId);
        ApiFuture<WriteResult> result = deviceRef.delete();
        System.out.println("Resultado da exclusão: " + result.get());
    }

    public void editar(String deviceId, HomeForm form) throws ExecutionException, InterruptedException {
        DocumentReference deviceRef = firebaseServiceHelper.getDeviceDocument(deviceId);

        Device device = new Device();
        device.setId(deviceId);
        device.setVazao(form.getVazao());
        device.setPressao(form.getPressao());
        device.setData(String.valueOf(new Date()));

        ApiFuture<WriteResult> result = deviceRef.set(device);
        System.out.println("Resultado da atualização: " + result.get());
    }
}