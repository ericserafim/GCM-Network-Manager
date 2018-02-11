package ericserafim.br.com.gcmnetwork.firebase;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public final class Storage {

    public static void insert(String chave, String value) {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(chave);

        myRef.setValue(value);
    }
}
