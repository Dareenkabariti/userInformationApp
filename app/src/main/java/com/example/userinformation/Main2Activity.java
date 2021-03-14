package com.example.userinformation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity implements UserAdapter.ItemClickListener {
    private FirebaseFirestore firebaseFirestore;
    private RecyclerView mFireStoreList;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<User> items ;
    UserAdapter[] myListData;
    UserAdapter adapter;
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    RecyclerView rv;
    ImageView delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        rv = findViewById(R.id.rvRest);
        items=new ArrayList<User>();
        adapter=new UserAdapter(this,items,this);
        delete =findViewById(R.id.delete);
        GetAllProducts();


    }


    private void GetAllProducts() {

         db.collection("Users").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        if (documentSnapshots.isEmpty()) {
                            Log.d("drn", "onSuccess: LIST EMPTY");
                            return;
                        } else {
                            for (DocumentSnapshot documentSnapshot : documentSnapshots) {
                                if (documentSnapshot.exists()) {
                                    String id = documentSnapshot.getId();
                                    String username = documentSnapshot.getString("User Name");
                                    String usernumber = documentSnapshot.getString("User Number");
                                    String useraddress = documentSnapshot.getString("User Address");
                                    Log.e("name", username.toString());
                                    Log.e("name", usernumber.toString());

                                    User user = new User(id, username, useraddress, usernumber);
                                    items.add(user);

                                    rv.setLayoutManager(layoutManager);
                                    rv.setHasFixedSize(true);
                                    rv.setAdapter(adapter);
                                  ;
                                    adapter.notifyDataSetChanged();
                                    Log.e("LogDATA", items.toString());

                                }
                            }
                        }


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("LogDATA", "get failed with ");


                    }
                });
    }

    public void deleteUser(final User user){

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("Delete user");
        builder1.setMessage("Are you sure? ");
        builder1.setCancelable(true);
        builder1.setIcon(R.drawable.ic_delete);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        db.collection("Users").document(user.getId())
                                .delete()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        items.remove(user);
                                        adapter.notifyDataSetChanged();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.e("LogDATA", "get failed with delete");

                            }
                        });
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();


    }

    @Override
    public void onItemClick(int position, String id) {
        deleteUser(items.get(position));
    }
}


