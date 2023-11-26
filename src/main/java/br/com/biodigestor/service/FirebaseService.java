package br.com.biodigestor.service;

import br.com.biodigestor.form.HomeForm;
import br.com.biodigestor.model.Device;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class FirebaseService {
    private final FirebaseServiceHelper firebaseServiceHelper;

    @Autowired
    public FirebaseService(FirebaseServiceHelper firebaseServiceHelper) {
        this.firebaseServiceHelper = firebaseServiceHelper;
    }

    public List<Device> listar() throws ExecutionException, InterruptedException {
        CollectionReference devices = firebaseServiceHelper.getDevicesCollection();

        List<Device> deviceList = new ArrayList<>();

        ApiFuture<QuerySnapshot> future = devices.get();
        QuerySnapshot documents = future.get();

        for (QueryDocumentSnapshot document : documents) {
            Device device = document.toObject(Device.class);
            device.setId(document.getId());
            deviceList.add(device);
        }

//        devices.get().get().forEach(document -> {
//            Device device = document.toObject(Device.class);
//            deviceList.add(device);
//        });

        return deviceList;
    }

    public void inserir(HomeForm form) throws ExecutionException, InterruptedException {
        CollectionReference devices = firebaseServiceHelper.getDevicesCollection();

        Device device = new Device();
        device.setData(String.valueOf(new Date()));
        device.setPressao(form.getPressao());
        device.setVazao(form.getVazao());

        ApiFuture<DocumentReference> result = devices.add(device);
        System.out.println("ID do Documento Criado: " + result.get().getId());
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