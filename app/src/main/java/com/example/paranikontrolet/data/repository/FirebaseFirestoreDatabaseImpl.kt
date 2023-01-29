package com.example.paranikontrolet.data.repository

import com.example.paranikontrolet.domain.repository.FirebaseFirestoreDatabase
import com.example.paranikontrolet.utils.Constants
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseFirestoreDatabaseImpl @Inject constructor(
private val firestore: FirebaseFirestore
) : FirebaseFirestoreDatabase{


    override suspend fun saveUser(
        getFirebaseUserUid: String,
        email: String,
        name: String,
        password: String
    ) {

        val user = hashMapOf<String,Any>(
            Constants.ID to getFirebaseUserUid,
            Constants.E_MAIL to email,
            Constants.NICKNAME to name,
            Constants.PASSWORD to password
        )
        firestore.collection(Constants.COLLECTION_PATH)
            .document(getFirebaseUserUid)
            .set(user).await()
    }
}