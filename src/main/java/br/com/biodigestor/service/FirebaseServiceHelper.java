package br.com.biodigestor.service;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

@Service
public class FirebaseServiceHelper {

    private Firestore getFirestoreInstance() {
        return FirestoreClient.getFirestore();
    }

    public CollectionReference getDevicesCollection() {
        Firestore db = getFirestoreInstance();
        return db.collection("devices");
    }

    public DocumentReference getDeviceDocument(String deviceId) {
        CollectionReference devices = getDevicesCollection();
        return devices.document(deviceId);
    }
}
