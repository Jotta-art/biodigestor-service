package br.com.biodigestor.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class FirebaseServiceHelper {

    private Firestore getFirestoreInstance() {
        return FirestoreClient.getFirestore();
    }

    public CollectionReference getDevicesCollection() {
        Firestore db = getFirestoreInstance();
        return db.collection("devices");
    }

    public CollectionReference getusuariosCollection() {
        Firestore db = getFirestoreInstance();
        return db.collection("usuarios");
    }

    public CollectionReference getDadosCollection() {
        Firestore db = getFirestoreInstance();
        return db.collection("dados");
    }

    public DocumentReference getDeviceDocument(String deviceId) {
        CollectionReference devices = getDevicesCollection();
        return devices.document(deviceId);
    }

    public DocumentReference getUsuarioDocument(String usuarioId) {
        CollectionReference usuarios = getusuariosCollection();
        return usuarios.document(usuarioId);
    }

    public boolean existsByUsername(String username) throws ExecutionException, InterruptedException {
        CollectionReference usersCollection = this.getusuariosCollection();

        Query query = usersCollection.whereEqualTo("username", username).limit(1);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        QuerySnapshot snapshot = querySnapshot.get();
        return !snapshot.isEmpty();
    }
}
