package com.example.paranikontrolet.data.repository

import com.example.paranikontrolet.domain.repository.FirebaseFirestoreDatabase
import com.example.paranikontrolet.utils.Constants
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.*
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseFirestoreDatabaseImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : FirebaseFirestoreDatabase {


    override suspend fun saveUser(
        getFirebaseUserUid: String,
        email: String,
        name: String,
        password: String
    ) {

        val user = hashMapOf<String, Any>(
            Constants.ID to getFirebaseUserUid,
            Constants.E_MAIL to email,
            Constants.NICKNAME to name,
            Constants.PASSWORD to password
        )
        firestore.collection(Constants.COLLECTION_PATH_USER)
            .document(getFirebaseUserUid)
            .set(user).await()
    }

    override suspend fun saveBudget(hashMap: HashMap<String,Any>) {
        firestore.collection(Constants.COLLECTION_PATH_BUDGET)
            .add(hashMap).await()
    }
    override suspend fun getBudgetDocuments(userId: String): QuerySnapshot {
            return firestore.collection(Constants.COLLECTION_PATH_BUDGET)
                .whereEqualTo(Constants.USER_ID,userId).get().await()
    }

    override suspend fun deleteBudget(documentId: String): Task<Void> {
       return firestore.collection(Constants.COLLECTION_PATH_BUDGET).document(documentId).delete()
    }

    override suspend fun updateBudget(hashMap: HashMap<String, Any>,documentId: String): Task<Void> {
        return firestore.collection(Constants.COLLECTION_PATH_BUDGET).document(documentId).update(hashMap)
    }
}