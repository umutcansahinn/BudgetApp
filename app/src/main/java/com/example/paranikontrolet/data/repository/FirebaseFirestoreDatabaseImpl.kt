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

    override suspend fun saveBudget(
        amount: Float,
        isIncome: Boolean,
        isRegular: Boolean,
        type: String
    ) {
        val budget = hashMapOf<String, Any>(
            Constants.AMOUNT to amount,
            Constants.IS_INCOME to isIncome,
            Constants.IS_REGULAR to isRegular,
            Constants.TYPE to type,
            Constants.DATE to "daha sonra yapılacak"
        )
        firestore.collection(Constants.COLLECTION_PATH_BUDGET)
            .add(budget).await()
    }

    override suspend fun getBudgetDocuments(): Task<QuerySnapshot> {
            return firestore.collection(Constants.COLLECTION_PATH_BUDGET).get()
    }
}